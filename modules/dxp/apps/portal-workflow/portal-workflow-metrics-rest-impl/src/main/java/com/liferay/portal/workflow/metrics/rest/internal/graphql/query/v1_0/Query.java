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

package com.liferay.portal.workflow.metrics.rest.internal.graphql.query.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Calendar;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.HistogramMetric;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Instance;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Node;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.NodeMetric;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Process;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.ProcessMetric;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Role;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.SLA;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Task;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.TimeRange;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.CalendarResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.HistogramMetricResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.InstanceResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.NodeMetricResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.NodeResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.ProcessMetricResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.ProcessResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.RoleResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.SLAResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.TaskResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.TimeRangeResource;

import java.util.Date;
import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
public class Query {

	public static void setCalendarResourceComponentServiceObjects(
		ComponentServiceObjects<CalendarResource>
			calendarResourceComponentServiceObjects) {

		_calendarResourceComponentServiceObjects =
			calendarResourceComponentServiceObjects;
	}

	public static void setHistogramMetricResourceComponentServiceObjects(
		ComponentServiceObjects<HistogramMetricResource>
			histogramMetricResourceComponentServiceObjects) {

		_histogramMetricResourceComponentServiceObjects =
			histogramMetricResourceComponentServiceObjects;
	}

	public static void setInstanceResourceComponentServiceObjects(
		ComponentServiceObjects<InstanceResource>
			instanceResourceComponentServiceObjects) {

		_instanceResourceComponentServiceObjects =
			instanceResourceComponentServiceObjects;
	}

	public static void setNodeResourceComponentServiceObjects(
		ComponentServiceObjects<NodeResource>
			nodeResourceComponentServiceObjects) {

		_nodeResourceComponentServiceObjects =
			nodeResourceComponentServiceObjects;
	}

	public static void setNodeMetricResourceComponentServiceObjects(
		ComponentServiceObjects<NodeMetricResource>
			nodeMetricResourceComponentServiceObjects) {

		_nodeMetricResourceComponentServiceObjects =
			nodeMetricResourceComponentServiceObjects;
	}

	public static void setProcessResourceComponentServiceObjects(
		ComponentServiceObjects<ProcessResource>
			processResourceComponentServiceObjects) {

		_processResourceComponentServiceObjects =
			processResourceComponentServiceObjects;
	}

	public static void setProcessMetricResourceComponentServiceObjects(
		ComponentServiceObjects<ProcessMetricResource>
			processMetricResourceComponentServiceObjects) {

		_processMetricResourceComponentServiceObjects =
			processMetricResourceComponentServiceObjects;
	}

	public static void setRoleResourceComponentServiceObjects(
		ComponentServiceObjects<RoleResource>
			roleResourceComponentServiceObjects) {

		_roleResourceComponentServiceObjects =
			roleResourceComponentServiceObjects;
	}

	public static void setSLAResourceComponentServiceObjects(
		ComponentServiceObjects<SLAResource>
			slaResourceComponentServiceObjects) {

		_slaResourceComponentServiceObjects =
			slaResourceComponentServiceObjects;
	}

	public static void setTaskResourceComponentServiceObjects(
		ComponentServiceObjects<TaskResource>
			taskResourceComponentServiceObjects) {

		_taskResourceComponentServiceObjects =
			taskResourceComponentServiceObjects;
	}

	public static void setTimeRangeResourceComponentServiceObjects(
		ComponentServiceObjects<TimeRangeResource>
			timeRangeResourceComponentServiceObjects) {

		_timeRangeResourceComponentServiceObjects =
			timeRangeResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {calendars{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public CalendarPage calendars() throws Exception {
		return _applyComponentServiceObjects(
			_calendarResourceComponentServiceObjects,
			this::_populateResourceContext,
			calendarResource -> new CalendarPage(
				calendarResource.getCalendarsPage()));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processHistogramMetric(dateEnd: ___, dateStart: ___, processId: ___, unit: ___){histograms, unit, value}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public HistogramMetric processHistogramMetric(
			@GraphQLName("processId") Long processId,
			@GraphQLName("dateEnd") Date dateEnd,
			@GraphQLName("dateStart") Date dateStart,
			@GraphQLName("unit") String unit)
		throws Exception {

		return _applyComponentServiceObjects(
			_histogramMetricResourceComponentServiceObjects,
			this::_populateResourceContext,
			histogramMetricResource ->
				histogramMetricResource.getProcessHistogramMetric(
					processId, dateEnd, dateStart, unit));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processInstances(assigneeIds: ___, dateEnd: ___, dateStart: ___, page: ___, pageSize: ___, processId: ___, slaStatuses: ___, statuses: ___, taskKeys: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public InstancePage processInstances(
			@GraphQLName("processId") Long processId,
			@GraphQLName("assigneeIds") Long[] assigneeIds,
			@GraphQLName("dateEnd") Date dateEnd,
			@GraphQLName("dateStart") Date dateStart,
			@GraphQLName("slaStatuses") String[] slaStatuses,
			@GraphQLName("statuses") String[] statuses,
			@GraphQLName("taskKeys") String[] taskKeys,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_instanceResourceComponentServiceObjects,
			this::_populateResourceContext,
			instanceResource -> new InstancePage(
				instanceResource.getProcessInstancesPage(
					processId, assigneeIds, dateEnd, dateStart, slaStatuses,
					statuses, taskKeys, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processInstance(instanceId: ___, processId: ___){assetTitle, assetTitle_i18n, assetType, assetType_i18n, assignees, className, classPK, completed, creator, dateCompletion, dateCreated, dateModified, duration, id, processId, processVersion, slaResults, slaStatus, status, taskNames, transitions}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Instance processInstance(
			@GraphQLName("processId") Long processId,
			@GraphQLName("instanceId") Long instanceId)
		throws Exception {

		return _applyComponentServiceObjects(
			_instanceResourceComponentServiceObjects,
			this::_populateResourceContext,
			instanceResource -> instanceResource.getProcessInstance(
				processId, instanceId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processNodes(processId: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public NodePage processNodes(@GraphQLName("processId") Long processId)
		throws Exception {

		return _applyComponentServiceObjects(
			_nodeResourceComponentServiceObjects,
			this::_populateResourceContext,
			nodeResource -> new NodePage(
				nodeResource.getProcessNodesPage(processId)));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processNodeMetrics(completed: ___, dateEnd: ___, dateStart: ___, key: ___, page: ___, pageSize: ___, processId: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public NodeMetricPage processNodeMetrics(
			@GraphQLName("processId") Long processId,
			@GraphQLName("completed") Boolean completed,
			@GraphQLName("dateEnd") Date dateEnd,
			@GraphQLName("dateStart") Date dateStart,
			@GraphQLName("key") String key,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_nodeMetricResourceComponentServiceObjects,
			this::_populateResourceContext,
			nodeMetricResource -> new NodeMetricPage(
				nodeMetricResource.getProcessNodeMetricsPage(
					processId, completed, dateEnd, dateStart, key,
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(nodeMetricResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {process(processId: ___){active, dateCreated, dateModified, description, id, name, title, title_i18n, version}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Process process(@GraphQLName("processId") Long processId)
		throws Exception {

		return _applyComponentServiceObjects(
			_processResourceComponentServiceObjects,
			this::_populateResourceContext,
			processResource -> processResource.getProcess(processId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processTitle(processId: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public String processTitle(@GraphQLName("processId") Long processId)
		throws Exception {

		return _applyComponentServiceObjects(
			_processResourceComponentServiceObjects,
			this::_populateResourceContext,
			processResource -> processResource.getProcessTitle(processId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processMetrics(page: ___, pageSize: ___, sorts: ___, title: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProcessMetricPage processMetrics(
			@GraphQLName("title") String title,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_processMetricResourceComponentServiceObjects,
			this::_populateResourceContext,
			processMetricResource -> new ProcessMetricPage(
				processMetricResource.getProcessMetricsPage(
					title, Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						processMetricResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processMetric(completed: ___, dateEnd: ___, dateStart: ___, processId: ___){instanceCount, onTimeInstanceCount, overdueInstanceCount, process, untrackedInstanceCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProcessMetric processMetric(
			@GraphQLName("processId") Long processId,
			@GraphQLName("completed") Boolean completed,
			@GraphQLName("dateEnd") Date dateEnd,
			@GraphQLName("dateStart") Date dateStart)
		throws Exception {

		return _applyComponentServiceObjects(
			_processMetricResourceComponentServiceObjects,
			this::_populateResourceContext,
			processMetricResource -> processMetricResource.getProcessMetric(
				processId, completed, dateEnd, dateStart));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processRoles(completed: ___, processId: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public RolePage processRoles(
			@GraphQLName("processId") Long processId,
			@GraphQLName("completed") Boolean completed)
		throws Exception {

		return _applyComponentServiceObjects(
			_roleResourceComponentServiceObjects,
			this::_populateResourceContext,
			roleResource -> new RolePage(
				roleResource.getProcessRolesPage(processId, completed)));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processSLAs(page: ___, pageSize: ___, processId: ___, status: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public SLAPage processSLAs(
			@GraphQLName("processId") Long processId,
			@GraphQLName("status") Integer status,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_slaResourceComponentServiceObjects, this::_populateResourceContext,
			slaResource -> new SLAPage(
				slaResource.getProcessSLAsPage(
					processId, status, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {sLA(slaId: ___){calendarKey, dateModified, description, duration, id, name, pauseNodeKeys, processId, startNodeKeys, status, stopNodeKeys}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public SLA sLA(@GraphQLName("slaId") Long slaId) throws Exception {
		return _applyComponentServiceObjects(
			_slaResourceComponentServiceObjects, this::_populateResourceContext,
			slaResource -> slaResource.getSLA(slaId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processTasks(processId: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TaskPage processTasks(@GraphQLName("processId") Long processId)
		throws Exception {

		return _applyComponentServiceObjects(
			_taskResourceComponentServiceObjects,
			this::_populateResourceContext,
			taskResource -> new TaskPage(
				taskResource.getProcessTasksPage(processId)));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {processTask(processId: ___, taskId: ___){assigneeId, className, classPK, completed, completionUserId, dateCompletion, dateCreated, dateModified, duration, id, instanceId, label, name, nodeId, processId, processVersion}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Task processTask(
			@GraphQLName("processId") Long processId,
			@GraphQLName("taskId") Long taskId)
		throws Exception {

		return _applyComponentServiceObjects(
			_taskResourceComponentServiceObjects,
			this::_populateResourceContext,
			taskResource -> taskResource.getProcessTask(processId, taskId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {timeRanges{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TimeRangePage timeRanges() throws Exception {
		return _applyComponentServiceObjects(
			_timeRangeResourceComponentServiceObjects,
			this::_populateResourceContext,
			timeRangeResource -> new TimeRangePage(
				timeRangeResource.getTimeRangesPage()));
	}

	@GraphQLTypeExtension(Process.class)
	public class GetProcessInstanceTypeExtension {

		public GetProcessInstanceTypeExtension(Process process) {
			_process = process;
		}

		@GraphQLField
		public Instance instance(@GraphQLName("instanceId") Long instanceId)
			throws Exception {

			return _applyComponentServiceObjects(
				_instanceResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				instanceResource -> instanceResource.getProcessInstance(
					_process.getId(), instanceId));
		}

		private Process _process;

	}

	@GraphQLTypeExtension(Task.class)
	public class GetProcessTypeExtension {

		public GetProcessTypeExtension(Task task) {
			_task = task;
		}

		@GraphQLField
		public Process process() throws Exception {
			return _applyComponentServiceObjects(
				_processResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				processResource -> processResource.getProcess(
					_task.getProcessId()));
		}

		private Task _task;

	}

	@GraphQLTypeExtension(Process.class)
	public class GetProcessNodesPageTypeExtension {

		public GetProcessNodesPageTypeExtension(Process process) {
			_process = process;
		}

		@GraphQLField
		public NodePage nodes() throws Exception {
			return _applyComponentServiceObjects(
				_nodeResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				nodeResource -> new NodePage(
					nodeResource.getProcessNodesPage(_process.getId())));
		}

		private Process _process;

	}

	@GraphQLTypeExtension(Process.class)
	public class GetProcessRolesPageTypeExtension {

		public GetProcessRolesPageTypeExtension(Process process) {
			_process = process;
		}

		@GraphQLField
		public RolePage roles(@GraphQLName("completed") Boolean completed)
			throws Exception {

			return _applyComponentServiceObjects(
				_roleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				roleResource -> new RolePage(
					roleResource.getProcessRolesPage(
						_process.getId(), completed)));
		}

		private Process _process;

	}

	@GraphQLTypeExtension(Process.class)
	public class GetProcessMetricTypeExtension {

		public GetProcessMetricTypeExtension(Process process) {
			_process = process;
		}

		@GraphQLField
		public ProcessMetric metric(
				@GraphQLName("completed") Boolean completed,
				@GraphQLName("dateEnd") Date dateEnd,
				@GraphQLName("dateStart") Date dateStart)
			throws Exception {

			return _applyComponentServiceObjects(
				_processMetricResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				processMetricResource -> processMetricResource.getProcessMetric(
					_process.getId(), completed, dateEnd, dateStart));
		}

		private Process _process;

	}

	@GraphQLTypeExtension(Process.class)
	public class GetProcessTaskTypeExtension {

		public GetProcessTaskTypeExtension(Process process) {
			_process = process;
		}

		@GraphQLField
		public Task task(@GraphQLName("taskId") Long taskId) throws Exception {
			return _applyComponentServiceObjects(
				_taskResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				taskResource -> taskResource.getProcessTask(
					_process.getId(), taskId));
		}

		private Process _process;

	}

	@GraphQLTypeExtension(Process.class)
	public class GetProcessHistogramMetricTypeExtension {

		public GetProcessHistogramMetricTypeExtension(Process process) {
			_process = process;
		}

		@GraphQLField
		public HistogramMetric histogramMetric(
				@GraphQLName("dateEnd") Date dateEnd,
				@GraphQLName("dateStart") Date dateStart,
				@GraphQLName("unit") String unit)
			throws Exception {

			return _applyComponentServiceObjects(
				_histogramMetricResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				histogramMetricResource ->
					histogramMetricResource.getProcessHistogramMetric(
						_process.getId(), dateEnd, dateStart, unit));
		}

		private Process _process;

	}

	@GraphQLTypeExtension(Process.class)
	public class GetProcessNodeMetricsPageTypeExtension {

		public GetProcessNodeMetricsPageTypeExtension(Process process) {
			_process = process;
		}

		@GraphQLField
		public NodeMetricPage nodeMetrics(
				@GraphQLName("completed") Boolean completed,
				@GraphQLName("dateEnd") Date dateEnd,
				@GraphQLName("dateStart") Date dateStart,
				@GraphQLName("key") String key,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page,
				@GraphQLName("sort") String sortsString)
			throws Exception {

			return _applyComponentServiceObjects(
				_nodeMetricResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				nodeMetricResource -> new NodeMetricPage(
					nodeMetricResource.getProcessNodeMetricsPage(
						_process.getId(), completed, dateEnd, dateStart, key,
						Pagination.of(page, pageSize),
						_sortsBiFunction.apply(
							nodeMetricResource, sortsString))));
		}

		private Process _process;

	}

	@GraphQLTypeExtension(Process.class)
	public class GetProcessTasksPageTypeExtension {

		public GetProcessTasksPageTypeExtension(Process process) {
			_process = process;
		}

		@GraphQLField
		public TaskPage tasks() throws Exception {
			return _applyComponentServiceObjects(
				_taskResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				taskResource -> new TaskPage(
					taskResource.getProcessTasksPage(_process.getId())));
		}

		private Process _process;

	}

	@GraphQLTypeExtension(Process.class)
	public class GetProcessSLAsPageTypeExtension {

		public GetProcessSLAsPageTypeExtension(Process process) {
			_process = process;
		}

		@GraphQLField
		public SLAPage sLAs(
				@GraphQLName("status") Integer status,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_slaResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				slaResource -> new SLAPage(
					slaResource.getProcessSLAsPage(
						_process.getId(), status,
						Pagination.of(page, pageSize))));
		}

		private Process _process;

	}

	@GraphQLTypeExtension(Process.class)
	public class GetProcessInstancesPageTypeExtension {

		public GetProcessInstancesPageTypeExtension(Process process) {
			_process = process;
		}

		@GraphQLField
		public InstancePage instances(
				@GraphQLName("assigneeIds") Long[] assigneeIds,
				@GraphQLName("dateEnd") Date dateEnd,
				@GraphQLName("dateStart") Date dateStart,
				@GraphQLName("slaStatuses") String[] slaStatuses,
				@GraphQLName("statuses") String[] statuses,
				@GraphQLName("taskKeys") String[] taskKeys,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_instanceResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				instanceResource -> new InstancePage(
					instanceResource.getProcessInstancesPage(
						_process.getId(), assigneeIds, dateEnd, dateStart,
						slaStatuses, statuses, taskKeys,
						Pagination.of(page, pageSize))));
		}

		private Process _process;

	}

	@GraphQLName("CalendarPage")
	public class CalendarPage {

		public CalendarPage(Page calendarPage) {
			actions = calendarPage.getActions();
			items = calendarPage.getItems();
			lastPage = calendarPage.getLastPage();
			page = calendarPage.getPage();
			pageSize = calendarPage.getPageSize();
			totalCount = calendarPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Calendar> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("HistogramMetricPage")
	public class HistogramMetricPage {

		public HistogramMetricPage(Page histogramMetricPage) {
			actions = histogramMetricPage.getActions();
			items = histogramMetricPage.getItems();
			lastPage = histogramMetricPage.getLastPage();
			page = histogramMetricPage.getPage();
			pageSize = histogramMetricPage.getPageSize();
			totalCount = histogramMetricPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<HistogramMetric> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("InstancePage")
	public class InstancePage {

		public InstancePage(Page instancePage) {
			actions = instancePage.getActions();
			items = instancePage.getItems();
			lastPage = instancePage.getLastPage();
			page = instancePage.getPage();
			pageSize = instancePage.getPageSize();
			totalCount = instancePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Instance> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("NodePage")
	public class NodePage {

		public NodePage(Page nodePage) {
			actions = nodePage.getActions();
			items = nodePage.getItems();
			lastPage = nodePage.getLastPage();
			page = nodePage.getPage();
			pageSize = nodePage.getPageSize();
			totalCount = nodePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Node> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("NodeMetricPage")
	public class NodeMetricPage {

		public NodeMetricPage(Page nodeMetricPage) {
			actions = nodeMetricPage.getActions();
			items = nodeMetricPage.getItems();
			lastPage = nodeMetricPage.getLastPage();
			page = nodeMetricPage.getPage();
			pageSize = nodeMetricPage.getPageSize();
			totalCount = nodeMetricPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<NodeMetric> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ProcessPage")
	public class ProcessPage {

		public ProcessPage(Page processPage) {
			actions = processPage.getActions();
			items = processPage.getItems();
			lastPage = processPage.getLastPage();
			page = processPage.getPage();
			pageSize = processPage.getPageSize();
			totalCount = processPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Process> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ProcessMetricPage")
	public class ProcessMetricPage {

		public ProcessMetricPage(Page processMetricPage) {
			actions = processMetricPage.getActions();
			items = processMetricPage.getItems();
			lastPage = processMetricPage.getLastPage();
			page = processMetricPage.getPage();
			pageSize = processMetricPage.getPageSize();
			totalCount = processMetricPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<ProcessMetric> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("RolePage")
	public class RolePage {

		public RolePage(Page rolePage) {
			actions = rolePage.getActions();
			items = rolePage.getItems();
			lastPage = rolePage.getLastPage();
			page = rolePage.getPage();
			pageSize = rolePage.getPageSize();
			totalCount = rolePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Role> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("SLAPage")
	public class SLAPage {

		public SLAPage(Page slaPage) {
			actions = slaPage.getActions();
			items = slaPage.getItems();
			lastPage = slaPage.getLastPage();
			page = slaPage.getPage();
			pageSize = slaPage.getPageSize();
			totalCount = slaPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<SLA> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("TaskPage")
	public class TaskPage {

		public TaskPage(Page taskPage) {
			actions = taskPage.getActions();
			items = taskPage.getItems();
			lastPage = taskPage.getLastPage();
			page = taskPage.getPage();
			pageSize = taskPage.getPageSize();
			totalCount = taskPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Task> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("TimeRangePage")
	public class TimeRangePage {

		public TimeRangePage(Page timeRangePage) {
			actions = timeRangePage.getActions();
			items = timeRangePage.getItems();
			lastPage = timeRangePage.getLastPage();
			page = timeRangePage.getPage();
			pageSize = timeRangePage.getPageSize();
			totalCount = timeRangePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<TimeRange> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(CalendarResource calendarResource)
		throws Exception {

		calendarResource.setContextAcceptLanguage(_acceptLanguage);
		calendarResource.setContextCompany(_company);
		calendarResource.setContextHttpServletRequest(_httpServletRequest);
		calendarResource.setContextHttpServletResponse(_httpServletResponse);
		calendarResource.setContextUriInfo(_uriInfo);
		calendarResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			HistogramMetricResource histogramMetricResource)
		throws Exception {

		histogramMetricResource.setContextAcceptLanguage(_acceptLanguage);
		histogramMetricResource.setContextCompany(_company);
		histogramMetricResource.setContextHttpServletRequest(
			_httpServletRequest);
		histogramMetricResource.setContextHttpServletResponse(
			_httpServletResponse);
		histogramMetricResource.setContextUriInfo(_uriInfo);
		histogramMetricResource.setContextUser(_user);
	}

	private void _populateResourceContext(InstanceResource instanceResource)
		throws Exception {

		instanceResource.setContextAcceptLanguage(_acceptLanguage);
		instanceResource.setContextCompany(_company);
		instanceResource.setContextHttpServletRequest(_httpServletRequest);
		instanceResource.setContextHttpServletResponse(_httpServletResponse);
		instanceResource.setContextUriInfo(_uriInfo);
		instanceResource.setContextUser(_user);
	}

	private void _populateResourceContext(NodeResource nodeResource)
		throws Exception {

		nodeResource.setContextAcceptLanguage(_acceptLanguage);
		nodeResource.setContextCompany(_company);
		nodeResource.setContextHttpServletRequest(_httpServletRequest);
		nodeResource.setContextHttpServletResponse(_httpServletResponse);
		nodeResource.setContextUriInfo(_uriInfo);
		nodeResource.setContextUser(_user);
	}

	private void _populateResourceContext(NodeMetricResource nodeMetricResource)
		throws Exception {

		nodeMetricResource.setContextAcceptLanguage(_acceptLanguage);
		nodeMetricResource.setContextCompany(_company);
		nodeMetricResource.setContextHttpServletRequest(_httpServletRequest);
		nodeMetricResource.setContextHttpServletResponse(_httpServletResponse);
		nodeMetricResource.setContextUriInfo(_uriInfo);
		nodeMetricResource.setContextUser(_user);
	}

	private void _populateResourceContext(ProcessResource processResource)
		throws Exception {

		processResource.setContextAcceptLanguage(_acceptLanguage);
		processResource.setContextCompany(_company);
		processResource.setContextHttpServletRequest(_httpServletRequest);
		processResource.setContextHttpServletResponse(_httpServletResponse);
		processResource.setContextUriInfo(_uriInfo);
		processResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			ProcessMetricResource processMetricResource)
		throws Exception {

		processMetricResource.setContextAcceptLanguage(_acceptLanguage);
		processMetricResource.setContextCompany(_company);
		processMetricResource.setContextHttpServletRequest(_httpServletRequest);
		processMetricResource.setContextHttpServletResponse(
			_httpServletResponse);
		processMetricResource.setContextUriInfo(_uriInfo);
		processMetricResource.setContextUser(_user);
	}

	private void _populateResourceContext(RoleResource roleResource)
		throws Exception {

		roleResource.setContextAcceptLanguage(_acceptLanguage);
		roleResource.setContextCompany(_company);
		roleResource.setContextHttpServletRequest(_httpServletRequest);
		roleResource.setContextHttpServletResponse(_httpServletResponse);
		roleResource.setContextUriInfo(_uriInfo);
		roleResource.setContextUser(_user);
	}

	private void _populateResourceContext(SLAResource slaResource)
		throws Exception {

		slaResource.setContextAcceptLanguage(_acceptLanguage);
		slaResource.setContextCompany(_company);
		slaResource.setContextHttpServletRequest(_httpServletRequest);
		slaResource.setContextHttpServletResponse(_httpServletResponse);
		slaResource.setContextUriInfo(_uriInfo);
		slaResource.setContextUser(_user);
	}

	private void _populateResourceContext(TaskResource taskResource)
		throws Exception {

		taskResource.setContextAcceptLanguage(_acceptLanguage);
		taskResource.setContextCompany(_company);
		taskResource.setContextHttpServletRequest(_httpServletRequest);
		taskResource.setContextHttpServletResponse(_httpServletResponse);
		taskResource.setContextUriInfo(_uriInfo);
		taskResource.setContextUser(_user);
	}

	private void _populateResourceContext(TimeRangeResource timeRangeResource)
		throws Exception {

		timeRangeResource.setContextAcceptLanguage(_acceptLanguage);
		timeRangeResource.setContextCompany(_company);
		timeRangeResource.setContextHttpServletRequest(_httpServletRequest);
		timeRangeResource.setContextHttpServletResponse(_httpServletResponse);
		timeRangeResource.setContextUriInfo(_uriInfo);
		timeRangeResource.setContextUser(_user);
	}

	private static ComponentServiceObjects<CalendarResource>
		_calendarResourceComponentServiceObjects;
	private static ComponentServiceObjects<HistogramMetricResource>
		_histogramMetricResourceComponentServiceObjects;
	private static ComponentServiceObjects<InstanceResource>
		_instanceResourceComponentServiceObjects;
	private static ComponentServiceObjects<NodeResource>
		_nodeResourceComponentServiceObjects;
	private static ComponentServiceObjects<NodeMetricResource>
		_nodeMetricResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProcessResource>
		_processResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProcessMetricResource>
		_processMetricResourceComponentServiceObjects;
	private static ComponentServiceObjects<RoleResource>
		_roleResourceComponentServiceObjects;
	private static ComponentServiceObjects<SLAResource>
		_slaResourceComponentServiceObjects;
	private static ComponentServiceObjects<TaskResource>
		_taskResourceComponentServiceObjects;
	private static ComponentServiceObjects<TimeRangeResource>
		_timeRangeResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private com.liferay.portal.kernel.model.Company _company;
	private com.liferay.portal.kernel.model.User _user;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private UriInfo _uriInfo;

}