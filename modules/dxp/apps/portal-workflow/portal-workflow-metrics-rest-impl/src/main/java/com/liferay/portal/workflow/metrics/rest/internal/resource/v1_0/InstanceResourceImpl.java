/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0;

import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.search.aggregation.AggregationResult;
import com.liferay.portal.search.aggregation.Aggregations;
import com.liferay.portal.search.aggregation.bucket.Bucket;
import com.liferay.portal.search.aggregation.bucket.FilterAggregation;
import com.liferay.portal.search.aggregation.bucket.FilterAggregationResult;
import com.liferay.portal.search.aggregation.bucket.Order;
import com.liferay.portal.search.aggregation.bucket.TermsAggregation;
import com.liferay.portal.search.aggregation.bucket.TermsAggregationResult;
import com.liferay.portal.search.aggregation.metrics.ScriptedMetricAggregationResult;
import com.liferay.portal.search.aggregation.metrics.TopHitsAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.BucketSelectorPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.BucketSortPipelineAggregation;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.engine.adapter.search.SearchRequestExecutor;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.TermsQuery;
import com.liferay.portal.search.script.Scripts;
import com.liferay.portal.search.sort.FieldSort;
import com.liferay.portal.search.sort.SortOrder;
import com.liferay.portal.search.sort.Sorts;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinition;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Assignee;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Creator;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Instance;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.SLAResult;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Transition;
import com.liferay.portal.workflow.metrics.rest.internal.dto.v1_0.util.AssigneeUtil;
import com.liferay.portal.workflow.metrics.rest.internal.resource.exception.NoSuchInstanceException;
import com.liferay.portal.workflow.metrics.rest.internal.resource.helper.ResourceHelper;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.InstanceResource;
import com.liferay.portal.workflow.metrics.search.index.InstanceWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.search.index.name.WorkflowMetricsIndexNameBuilder;
import com.liferay.portal.workflow.metrics.service.WorkflowMetricsSLADefinitionLocalService;
import com.liferay.portal.workflow.metrics.sla.processor.WorkflowMetricsSLAStatus;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Rafael Praxedes
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/instance.properties",
	scope = ServiceScope.PROTOTYPE, service = InstanceResource.class
)
public class InstanceResourceImpl extends BaseInstanceResourceImpl {

	@Override
	public void deleteProcessInstance(Long processId, Long instanceId)
		throws Exception {

		_instanceWorkflowMetricsIndexer.deleteInstance(
			contextCompany.getCompanyId(), instanceId);
	}

	@Override
	public Instance getProcessInstance(Long processId, Long instanceId)
		throws Exception {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		TermsAggregation termsAggregation = _aggregations.terms(
			"instanceId", "instanceId");

		FilterAggregation indexFilterAggregation = _aggregations.filter(
			"tasksIndex",
			_queries.term(
				"_index",
				_taskWorkflowMetricsIndexNameBuilder.getIndexName(
					contextCompany.getCompanyId())));

		TermsAggregation assigneeIdTermsAggregation = _aggregations.terms(
			"assigneeId", "assigneeId");

		assigneeIdTermsAggregation.setMissing(-1L);
		assigneeIdTermsAggregation.setSize(10000);

		indexFilterAggregation.addChildAggregation(assigneeIdTermsAggregation);

		FilterAggregation onTimeFilterAggregation = _aggregations.filter(
			"onTime", _resourceHelper.createMustNotBooleanQuery());

		onTimeFilterAggregation.addChildAggregation(
			_resourceHelper.createOnTimeScriptedMetricAggregation());

		FilterAggregation overdueFilterAggregation = _aggregations.filter(
			"overdue", _resourceHelper.createMustNotBooleanQuery());

		overdueFilterAggregation.addChildAggregation(
			_resourceHelper.createOverdueScriptedMetricAggregation());

		TermsAggregation slaDefinitionIdTermsAggregation = _aggregations.terms(
			"slaDefinitionId", "slaDefinitionId");

		slaDefinitionIdTermsAggregation.addChildAggregation(
			_aggregations.topHits("topHits"));
		slaDefinitionIdTermsAggregation.setSize(10000);

		TermsAggregation taskNameTermsAggregation = _aggregations.terms(
			"name", "name");

		taskNameTermsAggregation.setSize(10000);

		termsAggregation.addChildrenAggregations(
			indexFilterAggregation, onTimeFilterAggregation,
			overdueFilterAggregation, slaDefinitionIdTermsAggregation,
			taskNameTermsAggregation);

		searchSearchRequest.addAggregation(termsAggregation);

		searchSearchRequest.setIndexNames(
			_instanceWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()),
			_slaInstanceResultWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()),
			_taskWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()));

		BooleanQuery booleanQuery = _createBooleanQuery(
			new Long[0], processId, new String[0]);

		searchSearchRequest.setQuery(
			booleanQuery.addMustQueryClauses(
				_queries.term("instanceId", instanceId)));

		SearchSearchResponse searchSearchResponse =
			_searchRequestExecutor.executeSearchRequest(searchSearchRequest);

		return Stream.of(
			searchSearchResponse.getSearchHits()
		).map(
			SearchHits::getSearchHits
		).flatMap(
			List::stream
		).map(
			SearchHit::getDocument
		).filter(
			document -> document.getString(
				"uid"
			).startsWith(
				"WorkflowMetricsInstance"
			)
		).findFirst(
		).map(
			this::_createInstance
		).map(
			instance -> {
				Stream.of(
					searchSearchResponse.getAggregationResultsMap()
				).map(
					aggregationResultsMap ->
						(TermsAggregationResult)aggregationResultsMap.get(
							"instanceId")
				).map(
					TermsAggregationResult::getBuckets
				).flatMap(
					Collection::stream
				).findFirst(
				).ifPresent(
					bucket -> {
						_setAssignees(bucket, instance);
						_setSLAResults(bucket, instance);
						_setSLAStatus(bucket, instance);
						_setTaskNames(bucket, instance);
						_setTransitions(instance);
					}
				);

				return instance;
			}
		).orElseThrow(
			() -> new NoSuchInstanceException(
				"No instance exists with the instance ID " + instanceId)
		);
	}

	@Override
	public Page<Instance> getProcessInstancesPage(
			Long processId, Long[] assigneeIds, Date dateEnd, Date dateStart,
			String[] slaStatuses, String[] statuses, String[] taskKeys,
			Pagination pagination)
		throws Exception {

		SearchSearchResponse searchSearchResponse = _getSearchSearchResponse(
			assigneeIds, dateEnd, dateStart, processId, slaStatuses, statuses,
			taskKeys);

		int instanceCount = _getInstanceCount(searchSearchResponse);

		if (instanceCount > 0) {
			return Page.of(
				_getInstances(
					assigneeIds, dateEnd, dateStart,
					searchSearchResponse.getCount(), pagination, processId,
					slaStatuses, statuses, taskKeys),
				pagination, instanceCount);
		}

		return Page.of(Collections.emptyList());
	}

	public void patchProcessInstance(
			Long processId, Long instanceId, Instance instance)
		throws Exception {

		getProcessInstance(processId, instanceId);

		_instanceWorkflowMetricsIndexer.updateInstance(
			LocalizedMapUtil.getLocalizedMap(instance.getAssetTitle_i18n()),
			LocalizedMapUtil.getLocalizedMap(instance.getAssetType_i18n()),
			contextCompany.getCompanyId(), instanceId,
			instance.getDateModified());
	}

	@Override
	public void patchProcessInstanceComplete(
			Long processId, Long instanceId, Instance instance)
		throws Exception {

		getProcessInstance(processId, instanceId);

		_instanceWorkflowMetricsIndexer.completeInstance(
			contextCompany.getCompanyId(), instance.getDateCompletion(),
			instance.getDuration(), instanceId, instance.getDateModified());
	}

	@Override
	public Instance postProcessInstance(Long processId, Instance instance)
		throws Exception {

		Creator creator = instance.getCreator();

		return _createInstance(
			_instanceWorkflowMetricsIndexer.addInstance(
				LocalizedMapUtil.getLocalizedMap(instance.getAssetTitle_i18n()),
				LocalizedMapUtil.getLocalizedMap(instance.getAssetType_i18n()),
				instance.getClassName(), instance.getClassPK(),
				contextCompany.getCompanyId(), null, instance.getDateCreated(),
				instance.getId(), instance.getDateModified(), processId,
				instance.getProcessVersion(), creator.getId(),
				creator.getName()));
	}

	private BooleanQuery _createAssigneeIdsExistsBooleanQuery(
		Long[] assigneeIds) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		if (ArrayUtil.contains(assigneeIds, -1L)) {
			booleanQuery.addMustNotQueryClauses(_queries.exists("assigneeId"));
		}

		return booleanQuery;
	}

	private BooleanQuery _createAssigneeIdsTermsBooleanQuery(
		Long[] assigneeIds) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		TermsQuery termsQuery = _queries.terms("assigneeId");

		termsQuery.addValues(
			Stream.of(
				assigneeIds
			).filter(
				assigneeId -> assigneeId > 0
			).map(
				String::valueOf
			).toArray(
				Object[]::new
			));

		return booleanQuery.addMustQueryClauses(termsQuery);
	}

	private BooleanQuery _createBooleanQuery(long processId) {
		BooleanQuery booleanQuery = _queries.booleanQuery();

		return booleanQuery.addMustQueryClauses(
			_queries.term("companyId", contextCompany.getCompanyId()),
			_queries.term("deleted", Boolean.FALSE),
			_queries.term("processId", processId));
	}

	private BooleanQuery _createBooleanQuery(long processId, long instanceId) {
		BooleanQuery booleanQuery = _queries.booleanQuery();

		BooleanQuery tasksBooleanQuery = _queries.booleanQuery();

		tasksBooleanQuery.addFilterQueryClauses(
			_queries.term(
				"_index",
				_taskWorkflowMetricsIndexNameBuilder.getIndexName(
					contextCompany.getCompanyId())));
		tasksBooleanQuery.addMustQueryClauses(
			_createTasksBooleanQuery(processId, instanceId));

		BooleanQuery transitionsBooleanQuery = _queries.booleanQuery();

		transitionsBooleanQuery.addFilterQueryClauses(
			_queries.term(
				"_index",
				_transitionWorkflowMetricsIndexNameBuilder.getIndexName(
					contextCompany.getCompanyId())));
		transitionsBooleanQuery.addMustQueryClauses(
			_createBooleanQuery(processId));

		return booleanQuery.addShouldQueryClauses(
			tasksBooleanQuery, transitionsBooleanQuery);
	}

	private BooleanQuery _createBooleanQuery(
		Long[] assigneeIds, long processId, String[] statuses) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.setMinimumShouldMatch(1);

		BooleanQuery instancesBooleanQuery = _queries.booleanQuery();

		instancesBooleanQuery.addFilterQueryClauses(
			_queries.term(
				"_index",
				_instanceWorkflowMetricsIndexNameBuilder.getIndexName(
					contextCompany.getCompanyId())));
		instancesBooleanQuery.addMustQueryClauses(
			_createInstancesBooleanQuery(processId, statuses));

		BooleanQuery slaInstanceResultsBooleanQuery = _queries.booleanQuery();

		slaInstanceResultsBooleanQuery.addFilterQueryClauses(
			_queries.term(
				"_index",
				_slaInstanceResultWorkflowMetricsIndexNameBuilder.getIndexName(
					contextCompany.getCompanyId())));
		slaInstanceResultsBooleanQuery.addMustQueryClauses(
			_createSLAInstanceResultsBooleanQuery(processId));

		BooleanQuery tasksBooleanQuery = _queries.booleanQuery();

		tasksBooleanQuery.addFilterQueryClauses(
			_queries.term(
				"_index",
				_taskWorkflowMetricsIndexNameBuilder.getIndexName(
					contextCompany.getCompanyId())));
		tasksBooleanQuery.addMustQueryClauses(
			_createTasksBooleanQuery(assigneeIds, processId));

		return booleanQuery.addShouldQueryClauses(
			instancesBooleanQuery, slaInstanceResultsBooleanQuery,
			tasksBooleanQuery);
	}

	private BucketSelectorPipelineAggregation
		_createBucketSelectorPipelineAggregation() {

		BucketSelectorPipelineAggregation bucketSelectorPipelineAggregation =
			_aggregations.bucketSelector(
				"bucketSelector", _scripts.script("params.instanceCount > 0"));

		bucketSelectorPipelineAggregation.addBucketPath(
			"instanceCount", "instanceCount.value");

		return bucketSelectorPipelineAggregation;
	}

	private BucketSortPipelineAggregation _createBucketSortPipelineAggregation(
		Pagination pagination) {

		BucketSortPipelineAggregation bucketSortPipelineAggregation =
			_aggregations.bucketSort("bucketSort");

		FieldSort keyFieldSort = _sorts.field("_key");

		keyFieldSort.setSortOrder(SortOrder.ASC);

		bucketSortPipelineAggregation.addSortFields(keyFieldSort);

		bucketSortPipelineAggregation.setFrom(pagination.getStartPosition());
		bucketSortPipelineAggregation.setSize(pagination.getPageSize());

		return bucketSortPipelineAggregation;
	}

	private TermsQuery _createCompletedTermsQuery(String[] statuses) {
		TermsQuery termsQuery = _queries.terms("completed");

		for (String status : statuses) {
			if (Objects.equals(Instance.Status.COMPLETED.getValue(), status)) {
				termsQuery.addValue(Boolean.TRUE.toString());
			}
			else if (Objects.equals(
						Instance.Status.PENDING.getValue(), status)) {

				termsQuery.addValue(Boolean.FALSE.toString());
			}
		}

		return termsQuery;
	}

	private BooleanQuery _createCountFilterBooleanQuery() {
		BooleanQuery booleanQuery = _queries.booleanQuery();

		return booleanQuery.addFilterQueryClauses(
			_queries.term(
				"_index",
				_taskWorkflowMetricsIndexNameBuilder.getIndexName(
					contextCompany.getCompanyId())));
	}

	private Instance _createInstance(Document document) {
		return new Instance() {
			{
				assetTitle = document.getString(
					_getLocalizedName("assetTitle"));
				assetType = document.getString(_getLocalizedName("assetType"));
				creator = _toCreator(document.getLong("userId"));
				dateCompletion = _toDate(document.getDate("completionDate"));
				dateCreated = _toDate(document.getDate("createDate"));
				dateModified = _toDate(document.getDate("modifiedDate"));
				id = document.getLong("instanceId");
				processId = document.getLong("processId");
				status = _getStatus(
					GetterUtil.getBoolean(document.getString("completed")));
			}
		};
	}

	private Instance _createInstance(Map<String, Object> sourcesMap) {
		return new Instance() {
			{
				assetTitle = GetterUtil.getString(
					sourcesMap.get(_getLocalizedName("assetTitle")));
				assetType = GetterUtil.getString(
					sourcesMap.get(_getLocalizedName("assetType")));
				creator = _toCreator(
					GetterUtil.getLong(sourcesMap.get("userId")));
				dateCompletion = _toDate(
					GetterUtil.getString(sourcesMap.get("completionDate")));
				dateCreated = _toDate(
					GetterUtil.getString(sourcesMap.get("createDate")));
				dateModified = _toDate(
					GetterUtil.getString(sourcesMap.get("modifiedDate")));
				id = GetterUtil.getLong(sourcesMap.get("instanceId"));
				processId = GetterUtil.getLong(sourcesMap.get("processId"));
				status = _getStatus(
					GetterUtil.getBoolean(sourcesMap.get("completed")));
			}
		};
	}

	private BooleanQuery _createInstancesBooleanQuery(
		long processId, String[] statuses) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.addMustNotQueryClauses(_queries.term("instanceId", 0));

		if (statuses.length > 0) {
			booleanQuery.addMustQueryClauses(
				_createCompletedTermsQuery(statuses));
		}

		return booleanQuery.addMustQueryClauses(
			_queries.term("companyId", contextCompany.getCompanyId()),
			_queries.term("deleted", Boolean.FALSE),
			_queries.term("processId", processId));
	}

	private BooleanQuery _createSLAInstanceResultsBooleanQuery(long processId) {
		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.addMustNotQueryClauses(
			_queries.term("slaDefinitionId", 0),
			_queries.term("status", WorkflowMetricsSLAStatus.NEW.name()));

		return booleanQuery.addMustQueryClauses(
			_queries.term("companyId", contextCompany.getCompanyId()),
			_queries.term("deleted", Boolean.FALSE),
			_queries.term("processId", processId));
	}

	private SLAResult _createSLAResult(Map<String, Object> sourcesMap) {
		return new SLAResult() {
			{
				dateOverdue = _toDate(
					GetterUtil.getString(sourcesMap.get("overdueDate")));

				id = GetterUtil.getLong(sourcesMap.get("slaDefinitionId"));

				name = _getSLAName(id);

				onTime = GetterUtil.getBoolean(sourcesMap.get("onTime"));
				remainingTime = GetterUtil.getLong(
					sourcesMap.get("remainingTime"));
				status = _getSLAResultStatus(
					GetterUtil.getString(sourcesMap.get("status")));
			}
		};
	}

	private BooleanQuery _createTasksBooleanQuery(
		long processId, long instanceId) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.addMustNotQueryClauses(_queries.term("taskId", 0));

		return booleanQuery.addMustQueryClauses(
			_queries.term("companyId", contextCompany.getCompanyId()),
			_queries.term("completed", Boolean.FALSE),
			_queries.term("deleted", Boolean.FALSE),
			_queries.term("instanceId", instanceId),
			_queries.term("processId", processId));
	}

	private BooleanQuery _createTasksBooleanQuery(
		Long[] assigneeIds, long processId) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.addMustNotQueryClauses(_queries.term("taskId", 0));

		if (assigneeIds.length > 0) {
			booleanQuery.addShouldQueryClauses(
				_createAssigneeIdsExistsBooleanQuery(assigneeIds),
				_createAssigneeIdsTermsBooleanQuery(assigneeIds));
		}

		return booleanQuery.addMustQueryClauses(
			_queries.term("companyId", contextCompany.getCompanyId()),
			_queries.term("completed", Boolean.FALSE),
			_queries.term("deleted", Boolean.FALSE),
			_queries.term("processId", processId));
	}

	private List<Assignee> _getAssignees(Bucket bucket) {
		FilterAggregationResult filterAggregationResult =
			(FilterAggregationResult)bucket.getChildAggregationResult(
				"tasksIndex");

		TermsAggregationResult termsAggregationResult =
			(TermsAggregationResult)
				filterAggregationResult.getChildAggregationResult("assigneeId");

		Collection<Bucket> buckets = termsAggregationResult.getBuckets();

		Stream<Bucket> stream = buckets.stream();

		return stream.map(
			Bucket::getKey
		).map(
			GetterUtil::getLong
		).map(
			userId -> AssigneeUtil.toAssignee(
				_language, _portal,
				ResourceBundleUtil.getModuleAndPortalResourceBundle(
					contextAcceptLanguage.getPreferredLocale(),
					InstanceResourceImpl.class),
				userId, _userLocalService::fetchUser)
		).sorted(
			Comparator.comparing(
				Assignee::getName,
				Comparator.comparing(
					(String name) -> Objects.equals(
						name,
						_language.get(
							ResourceBundleUtil.getModuleAndPortalResourceBundle(
								contextAcceptLanguage.getPreferredLocale(),
								InstanceResourceImpl.class),
							"unassigned"))
				).thenComparing(
					Comparator.nullsLast(String::compareToIgnoreCase)
				))
		).filter(
			Objects::nonNull
		).collect(
			Collectors.toList()
		);
	}

	private int _getInstanceCount(SearchSearchResponse searchSearchResponse) {
		Map<String, AggregationResult> aggregationResultsMap =
			searchSearchResponse.getAggregationResultsMap();

		ScriptedMetricAggregationResult scriptedMetricAggregationResult =
			(ScriptedMetricAggregationResult)aggregationResultsMap.get(
				"instanceCount");

		return GetterUtil.getInteger(
			scriptedMetricAggregationResult.getValue());
	}

	private List<Instance> _getInstances(
		Long[] assigneeIds, Date dateEnd, Date dateStart, long instanceCount,
		Pagination pagination, long processId, String[] slaStatuses,
		String[] statuses, String[] taskKeys) {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		TermsAggregation termsAggregation = _aggregations.terms(
			"instanceId", "instanceId");

		FilterAggregation instancesIndexFilterAggregation =
			_aggregations.filter(
				"instanceIndex",
				_queries.term(
					"_index",
					_instanceWorkflowMetricsIndexNameBuilder.getIndexName(
						contextCompany.getCompanyId())));

		instancesIndexFilterAggregation.addChildAggregation(
			_aggregations.topHits("topHits"));

		FilterAggregation onTimeFilterAggregation = _aggregations.filter(
			"onTime", _resourceHelper.createMustNotBooleanQuery());

		onTimeFilterAggregation.addChildAggregation(
			_resourceHelper.createOnTimeScriptedMetricAggregation());

		FilterAggregation overdueFilterAggregation = _aggregations.filter(
			"overdue", _resourceHelper.createMustNotBooleanQuery());

		overdueFilterAggregation.addChildAggregation(
			_resourceHelper.createOverdueScriptedMetricAggregation());

		TermsAggregation taskNameTermsAggregation = _aggregations.terms(
			"name", "name");

		taskNameTermsAggregation.setSize(10000);

		FilterAggregation tasksIndexFilterAggregation = _aggregations.filter(
			"tasksIndex",
			_queries.term(
				"_index",
				_taskWorkflowMetricsIndexNameBuilder.getIndexName(
					contextCompany.getCompanyId())));

		TermsAggregation assigneeIdTermsAggregation = _aggregations.terms(
			"assigneeId", "assigneeId");

		assigneeIdTermsAggregation.setMissing(-1L);
		assigneeIdTermsAggregation.setSize(10000);

		tasksIndexFilterAggregation.addChildAggregation(
			assigneeIdTermsAggregation);

		termsAggregation.addChildrenAggregations(
			instancesIndexFilterAggregation, onTimeFilterAggregation,
			overdueFilterAggregation, taskNameTermsAggregation,
			tasksIndexFilterAggregation, _aggregations.topHits("topHits"),
			_resourceHelper.creatInstanceCountScriptedMetricAggregation(
				ListUtil.fromArray(assigneeIds), dateEnd, dateStart,
				ListUtil.fromArray(slaStatuses), ListUtil.fromArray(statuses),
				ListUtil.fromArray(taskKeys)));

		termsAggregation.addOrders(Order.key(true));
		termsAggregation.addPipelineAggregations(
			_createBucketSelectorPipelineAggregation(),
			_createBucketSortPipelineAggregation(pagination));

		termsAggregation.setSize(GetterUtil.getInteger(instanceCount));

		searchSearchRequest.addAggregation(termsAggregation);

		searchSearchRequest.setIndexNames(
			_instanceWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()),
			_slaInstanceResultWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()),
			_taskWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()));

		searchSearchRequest.setQuery(
			_createBooleanQuery(assigneeIds, processId, statuses));

		return Stream.of(
			_searchRequestExecutor.executeSearchRequest(searchSearchRequest)
		).map(
			SearchSearchResponse::getAggregationResultsMap
		).map(
			aggregationResultsMap ->
				(TermsAggregationResult)aggregationResultsMap.get("instanceId")
		).map(
			TermsAggregationResult::getBuckets
		).flatMap(
			Collection::stream
		).map(
			bucket -> Stream.of(
				(FilterAggregationResult)bucket.getChildAggregationResult(
					"instanceIndex")
			).map(
				filterAggregationResult ->
					(TopHitsAggregationResult)
						filterAggregationResult.getChildAggregationResult(
							"topHits")
			).map(
				TopHitsAggregationResult::getSearchHits
			).map(
				SearchHits::getSearchHits
			).flatMap(
				List::stream
			).map(
				SearchHit::getSourcesMap
			).findFirst(
			).map(
				this::_createInstance
			).map(
				instance -> {
					_setAssignees(bucket, instance);
					_setSLAStatus(bucket, instance);
					_setTaskNames(bucket, instance);
					_setTransitions(instance);

					return instance;
				}
			).orElseGet(
				Instance::new
			)
		).collect(
			Collectors.toCollection(LinkedList::new)
		);
	}

	private String _getLocalizedName(String name) {
		return Field.getLocalizedName(
			contextAcceptLanguage.getPreferredLocale(), name);
	}

	private List<String> _getNextTransitionNames(
		long processId, long instanceId) {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		TermsAggregation termsAggregation = _aggregations.terms("nodeId", null);

		termsAggregation.setSize(100000);

		termsAggregation.setScript(
			_scripts.script(
				"doc.containsKey('taskId') ? doc.taskId.value : doc." +
					"sourceNodeId.value"));

		FilterAggregation countFilterAggregation = _aggregations.filter(
			"countFilter", _createCountFilterBooleanQuery());

		countFilterAggregation.addChildrenAggregations(
			_aggregations.valueCount("taskCount", "taskId"));

		TermsAggregation nameTermsAggregation = _aggregations.terms(
			"name", "name");

		nameTermsAggregation.setSize(100000);

		termsAggregation.addChildrenAggregations(
			countFilterAggregation, nameTermsAggregation);

		BucketSelectorPipelineAggregation bucketSelectorPipelineAggregation =
			_aggregations.bucketSelector(
				"bucketSelector", _scripts.script("params.taskCount > 0"));

		bucketSelectorPipelineAggregation.addBucketPath(
			"taskCount", "countFilter>taskCount.value");

		termsAggregation.addPipelineAggregations(
			bucketSelectorPipelineAggregation);

		searchSearchRequest.addAggregation(termsAggregation);

		searchSearchRequest.setIndexNames(
			_taskWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()),
			_transitionWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()));

		BooleanQuery booleanQuery = _queries.booleanQuery();

		searchSearchRequest.setQuery(
			booleanQuery.addFilterQueryClauses(
				_createBooleanQuery(processId, instanceId)));

		return Stream.of(
			_searchRequestExecutor.executeSearchRequest(searchSearchRequest)
		).map(
			SearchSearchResponse::getAggregationResultsMap
		).map(
			aggregationResultsMap ->
				(TermsAggregationResult)aggregationResultsMap.get("nodeId")
		).map(
			TermsAggregationResult::getBuckets
		).flatMap(
			Collection::stream
		).map(
			bucket -> Stream.of(
				(TermsAggregationResult)bucket.getChildAggregationResult("name")
			).map(
				TermsAggregationResult::getBuckets
			).flatMap(
				Collection::stream
			).map(
				Bucket::getKey
			).collect(
				Collectors.toCollection(ArrayList::new)
			)
		).flatMap(
			Collection::stream
		).sorted(
		).collect(
			Collectors.toCollection(ArrayList::new)
		);
	}

	private SearchSearchResponse _getSearchSearchResponse(
		Long[] assigneeIds, Date dateEnd, Date dateStart, long processId,
		String[] slaStatuses, String[] statuses, String[] taskKeys) {

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		searchSearchRequest.addAggregation(
			_resourceHelper.creatInstanceCountScriptedMetricAggregation(
				ListUtil.fromArray(assigneeIds), dateEnd, dateStart,
				ListUtil.fromArray(slaStatuses), ListUtil.fromArray(statuses),
				ListUtil.fromArray(taskKeys)));
		searchSearchRequest.setIndexNames(
			_instanceWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()),
			_slaInstanceResultWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()),
			_taskWorkflowMetricsIndexNameBuilder.getIndexName(
				contextCompany.getCompanyId()));
		searchSearchRequest.setQuery(
			_createBooleanQuery(assigneeIds, processId, statuses));

		return _searchRequestExecutor.executeSearchRequest(searchSearchRequest);
	}

	private String _getSLAName(long slaDefinitionId) {
		try {
			WorkflowMetricsSLADefinition workflowMetricsSLADefinition =
				_workflowMetricsSLADefinitionLocalService.
					getWorkflowMetricsSLADefinition(slaDefinitionId);

			return workflowMetricsSLADefinition.getName();
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}

			return null;
		}
	}

	private SLAResult.Status _getSLAResultStatus(String status) {
		if (Objects.equals(status, WorkflowMetricsSLAStatus.COMPLETED.name()) ||
			Objects.equals(status, WorkflowMetricsSLAStatus.STOPPED.name())) {

			return SLAResult.Status.STOPPED;
		}
		else if (Objects.equals(
					status, WorkflowMetricsSLAStatus.PAUSED.name())) {

			return SLAResult.Status.PAUSED;
		}

		return SLAResult.Status.RUNNING;
	}

	private Instance.Status _getStatus(Boolean completed) {
		if (completed) {
			return Instance.Status.COMPLETED;
		}

		return Instance.Status.PENDING;
	}

	private List<String> _getTaskNames(Bucket bucket) {
		TermsAggregationResult termsAggregationResult =
			(TermsAggregationResult)bucket.getChildAggregationResult("name");

		Collection<Bucket> buckets = termsAggregationResult.getBuckets();

		Stream<Bucket> stream = buckets.stream();

		return stream.map(
			Bucket::getKey
		).map(
			taskName -> _language.get(
				ResourceBundleUtil.getModuleAndPortalResourceBundle(
					contextAcceptLanguage.getPreferredLocale(),
					InstanceResourceImpl.class),
				taskName)
		).collect(
			Collectors.toList()
		);
	}

	private boolean _isOnTime(Bucket bucket) {
		if (_resourceHelper.getOnTimeInstanceCount(bucket) > 0) {
			return true;
		}

		return false;
	}

	private boolean _isOverdue(Bucket bucket) {
		if (_resourceHelper.getOverdueInstanceCount(bucket) > 0) {
			return true;
		}

		return false;
	}

	private void _setAssignees(Bucket bucket, Instance instance) {
		List<Assignee> assignees = _getAssignees(bucket);

		if (ListUtil.isNull(assignees)) {
			return;
		}

		instance.setAssignees(assignees.toArray(new Assignee[0]));
	}

	private void _setSLAResults(Bucket bucket, Instance instance) {
		instance.setSlaResults(
			Stream.of(
				(TermsAggregationResult)bucket.getChildAggregationResult(
					"slaDefinitionId")
			).map(
				TermsAggregationResult::getBuckets
			).flatMap(
				Collection::stream
			).map(
				childBucket -> Stream.of(
					(TopHitsAggregationResult)
						childBucket.getChildAggregationResult("topHits")
				).map(
					TopHitsAggregationResult::getSearchHits
				).map(
					SearchHits::getSearchHits
				).flatMap(
					List::stream
				).findFirst(
				).map(
					SearchHit::getSourcesMap
				).map(
					this::_createSLAResult
				).orElseGet(
					SLAResult::new
				)
			).toArray(
				SLAResult[]::new
			));
	}

	private void _setSLAStatus(Bucket bucket, Instance instance) {
		if (_isOverdue(bucket)) {
			instance.setSLAStatus(Instance.SLAStatus.OVERDUE);
		}
		else if (_isOnTime(bucket)) {
			instance.setSLAStatus(Instance.SLAStatus.ON_TIME);
		}
		else {
			instance.setSLAStatus(Instance.SLAStatus.UNTRACKED);
		}
	}

	private void _setTaskNames(Bucket bucket, Instance instance) {
		List<String> taskNames = _getTaskNames(bucket);

		if (ListUtil.isNull(taskNames)) {
			return;
		}

		instance.setTaskNames(taskNames.toArray(new String[0]));
	}

	private void _setTransitions(Instance instance) {
		if (ArrayUtil.isEmpty(instance.getAssignees()) ||
			(ArrayUtil.getLength(instance.getTaskNames()) != 1)) {

			return;
		}

		Assignee[] assignees = instance.getAssignees();

		if (!Objects.equals(assignees[0].getId(), contextUser.getUserId())) {
			return;
		}

		instance.setTransitions(_toTransitions(instance));
	}

	private Creator _toCreator(Long userId) {
		if (Objects.isNull(userId)) {
			return null;
		}

		User user = _userLocalService.fetchUser(userId);

		return new Creator() {
			{
				id = userId;

				setName(
					() -> {
						if (user == null) {
							return String.valueOf(userId);
						}

						return user.getFullName();
					});
			}
		};
	}

	private Date _toDate(String dateString) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			_INDEX_DATE_FORMAT_PATTERN);

		try {
			return dateFormat.parse(dateString);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}

			return null;
		}
	}

	private Transition _toTransition(String name) {
		Transition transition = new Transition();

		transition.setLabel(
			_language.get(
				ResourceBundleUtil.getModuleAndPortalResourceBundle(
					contextAcceptLanguage.getPreferredLocale(),
					InstanceResourceImpl.class),
				name));
		transition.setName(name);

		return transition;
	}

	private Transition[] _toTransitions(Instance instance) {
		return transformToArray(
			_getNextTransitionNames(instance.getProcessId(), instance.getId()),
			this::_toTransition, Transition.class);
	}

	private static final String _INDEX_DATE_FORMAT_PATTERN = PropsUtil.get(
		PropsKeys.INDEX_DATE_FORMAT_PATTERN);

	private static final Log _log = LogFactoryUtil.getLog(
		InstanceResourceImpl.class);

	@Reference
	private Aggregations _aggregations;

	@Reference
	private InstanceWorkflowMetricsIndexer _instanceWorkflowMetricsIndexer;

	@Reference(target = "(workflow.metrics.index.entity.name=instance)")
	private WorkflowMetricsIndexNameBuilder
		_instanceWorkflowMetricsIndexNameBuilder;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference
	private Queries _queries;

	@Reference
	private ResourceHelper _resourceHelper;

	@Reference
	private Scripts _scripts;

	@Reference
	private SearchRequestExecutor _searchRequestExecutor;

	@Reference(
		target = "(workflow.metrics.index.entity.name=sla-instance-result)"
	)
	private WorkflowMetricsIndexNameBuilder
		_slaInstanceResultWorkflowMetricsIndexNameBuilder;

	@Reference
	private Sorts _sorts;

	@Reference(target = "(workflow.metrics.index.entity.name=task)")
	private WorkflowMetricsIndexNameBuilder
		_taskWorkflowMetricsIndexNameBuilder;

	@Reference(target = "(workflow.metrics.index.entity.name=transition)")
	private WorkflowMetricsIndexNameBuilder
		_transitionWorkflowMetricsIndexNameBuilder;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private WorkflowMetricsSLADefinitionLocalService
		_workflowMetricsSLADefinitionLocalService;

}