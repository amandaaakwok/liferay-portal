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

package com.liferay.portal.workflow.metrics.rest.internal.graphql.mutation.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Assignee;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.AssigneeBulkSelection;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.AssigneeMetric;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.AssigneeMetricBulkSelection;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Instance;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Node;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Process;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.SLA;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Task;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.AssigneeMetricResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.AssigneeResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.InstanceResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.NodeResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.ProcessResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.SLAResource;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.TaskResource;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setAssigneeResourceComponentServiceObjects(
		ComponentServiceObjects<AssigneeResource>
			assigneeResourceComponentServiceObjects) {

		_assigneeResourceComponentServiceObjects =
			assigneeResourceComponentServiceObjects;
	}

	public static void setAssigneeMetricResourceComponentServiceObjects(
		ComponentServiceObjects<AssigneeMetricResource>
			assigneeMetricResourceComponentServiceObjects) {

		_assigneeMetricResourceComponentServiceObjects =
			assigneeMetricResourceComponentServiceObjects;
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

	public static void setProcessResourceComponentServiceObjects(
		ComponentServiceObjects<ProcessResource>
			processResourceComponentServiceObjects) {

		_processResourceComponentServiceObjects =
			processResourceComponentServiceObjects;
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

	@GraphQLField
	public java.util.Collection<Assignee> createProcessAssigneesPage(
			@GraphQLName("processId") Long processId,
			@GraphQLName("assigneeBulkSelection") AssigneeBulkSelection
				assigneeBulkSelection)
		throws Exception {

		return _applyComponentServiceObjects(
			_assigneeResourceComponentServiceObjects,
			this::_populateResourceContext,
			assigneeResource -> {
				Page paginationPage = assigneeResource.postProcessAssigneesPage(
					processId, assigneeBulkSelection);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	public java.util.Collection<AssigneeMetric>
			createProcessAssigneeMetricsPage(
				@GraphQLName("processId") Long processId,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page,
				@GraphQLName("sort") String sortsString,
				@GraphQLName("assigneeMetricBulkSelection")
					AssigneeMetricBulkSelection assigneeMetricBulkSelection)
		throws Exception {

		return _applyComponentServiceObjects(
			_assigneeMetricResourceComponentServiceObjects,
			this::_populateResourceContext,
			assigneeMetricResource -> {
				Page paginationPage =
					assigneeMetricResource.postProcessAssigneeMetricsPage(
						processId, Pagination.of(page, pageSize),
						_sortsBiFunction.apply(
							assigneeMetricResource, sortsString),
						assigneeMetricBulkSelection);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	public Instance createProcessInstance(
			@GraphQLName("processId") Long processId,
			@GraphQLName("instance") Instance instance)
		throws Exception {

		return _applyComponentServiceObjects(
			_instanceResourceComponentServiceObjects,
			this::_populateResourceContext,
			instanceResource -> instanceResource.postProcessInstance(
				processId, instance));
	}

	@GraphQLField
	public Response createProcessInstanceBatch(
			@GraphQLName("processId") Long processId,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_instanceResourceComponentServiceObjects,
			this::_populateResourceContext,
			instanceResource -> instanceResource.postProcessInstanceBatch(
				processId, callbackURL, object));
	}

	@GraphQLField
	public boolean deleteProcessInstance(
			@GraphQLName("processId") Long processId,
			@GraphQLName("instanceId") Long instanceId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_instanceResourceComponentServiceObjects,
			this::_populateResourceContext,
			instanceResource -> instanceResource.deleteProcessInstance(
				processId, instanceId));

		return true;
	}

	@GraphQLField
	public boolean patchProcessInstance(
			@GraphQLName("processId") Long processId,
			@GraphQLName("instanceId") Long instanceId,
			@GraphQLName("instance") Instance instance)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_instanceResourceComponentServiceObjects,
			this::_populateResourceContext,
			instanceResource -> instanceResource.patchProcessInstance(
				processId, instanceId, instance));

		return true;
	}

	@GraphQLField
	public boolean patchProcessInstanceComplete(
			@GraphQLName("processId") Long processId,
			@GraphQLName("instanceId") Long instanceId,
			@GraphQLName("instance") Instance instance)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_instanceResourceComponentServiceObjects,
			this::_populateResourceContext,
			instanceResource -> instanceResource.patchProcessInstanceComplete(
				processId, instanceId, instance));

		return true;
	}

	@GraphQLField
	public Node createProcessNode(
			@GraphQLName("processId") Long processId,
			@GraphQLName("node") Node node)
		throws Exception {

		return _applyComponentServiceObjects(
			_nodeResourceComponentServiceObjects,
			this::_populateResourceContext,
			nodeResource -> nodeResource.postProcessNode(processId, node));
	}

	@GraphQLField
	public Response createProcessNodeBatch(
			@GraphQLName("processId") Long processId,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_nodeResourceComponentServiceObjects,
			this::_populateResourceContext,
			nodeResource -> nodeResource.postProcessNodeBatch(
				processId, callbackURL, object));
	}

	@GraphQLField
	public boolean deleteProcessNode(
			@GraphQLName("processId") Long processId,
			@GraphQLName("nodeId") Long nodeId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_nodeResourceComponentServiceObjects,
			this::_populateResourceContext,
			nodeResource -> nodeResource.deleteProcessNode(processId, nodeId));

		return true;
	}

	@GraphQLField
	public Process createProcess(@GraphQLName("process") Process process)
		throws Exception {

		return _applyComponentServiceObjects(
			_processResourceComponentServiceObjects,
			this::_populateResourceContext,
			processResource -> processResource.postProcess(process));
	}

	@GraphQLField
	public Response createProcessBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_processResourceComponentServiceObjects,
			this::_populateResourceContext,
			processResource -> processResource.postProcessBatch(
				callbackURL, object));
	}

	@GraphQLField
	public boolean deleteProcess(@GraphQLName("processId") Long processId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_processResourceComponentServiceObjects,
			this::_populateResourceContext,
			processResource -> processResource.deleteProcess(processId));

		return true;
	}

	@GraphQLField
	public Response deleteProcessBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_processResourceComponentServiceObjects,
			this::_populateResourceContext,
			processResource -> processResource.deleteProcessBatch(
				callbackURL, object));
	}

	@GraphQLField
	public boolean updateProcess(
			@GraphQLName("processId") Long processId,
			@GraphQLName("process") Process process)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_processResourceComponentServiceObjects,
			this::_populateResourceContext,
			processResource -> processResource.putProcess(processId, process));

		return true;
	}

	@GraphQLField
	public Response updateProcessBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_processResourceComponentServiceObjects,
			this::_populateResourceContext,
			processResource -> processResource.putProcessBatch(
				callbackURL, object));
	}

	@GraphQLField
	public SLA createProcessSLA(
			@GraphQLName("processId") Long processId,
			@GraphQLName("sla") SLA sla)
		throws Exception {

		return _applyComponentServiceObjects(
			_slaResourceComponentServiceObjects, this::_populateResourceContext,
			slaResource -> slaResource.postProcessSLA(processId, sla));
	}

	@GraphQLField
	public Response createProcessSLABatch(
			@GraphQLName("processId") Long processId,
			@GraphQLName("sla") SLA sla,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_slaResourceComponentServiceObjects, this::_populateResourceContext,
			slaResource -> slaResource.postProcessSLABatch(
				processId, sla, callbackURL, object));
	}

	@GraphQLField
	public boolean deleteSLA(@GraphQLName("slaId") Long slaId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_slaResourceComponentServiceObjects, this::_populateResourceContext,
			slaResource -> slaResource.deleteSLA(slaId));

		return true;
	}

	@GraphQLField
	public Response deleteSLABatch(
			@GraphQLName("slaId") Long slaId,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_slaResourceComponentServiceObjects, this::_populateResourceContext,
			slaResource -> slaResource.deleteSLABatch(
				slaId, callbackURL, object));
	}

	@GraphQLField
	public SLA updateSLA(
			@GraphQLName("slaId") Long slaId, @GraphQLName("sla") SLA sla)
		throws Exception {

		return _applyComponentServiceObjects(
			_slaResourceComponentServiceObjects, this::_populateResourceContext,
			slaResource -> slaResource.putSLA(slaId, sla));
	}

	@GraphQLField
	public Response updateSLABatch(
			@GraphQLName("slaId") Long slaId, @GraphQLName("sla") SLA sla,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_slaResourceComponentServiceObjects, this::_populateResourceContext,
			slaResource -> slaResource.putSLABatch(
				slaId, sla, callbackURL, object));
	}

	@GraphQLField
	public Task createProcessTask(
			@GraphQLName("processId") Long processId,
			@GraphQLName("task") Task task)
		throws Exception {

		return _applyComponentServiceObjects(
			_taskResourceComponentServiceObjects,
			this::_populateResourceContext,
			taskResource -> taskResource.postProcessTask(processId, task));
	}

	@GraphQLField
	public Response createProcessTaskBatch(
			@GraphQLName("processId") Long processId,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_taskResourceComponentServiceObjects,
			this::_populateResourceContext,
			taskResource -> taskResource.postProcessTaskBatch(
				processId, callbackURL, object));
	}

	@GraphQLField
	public boolean deleteProcessTask(
			@GraphQLName("processId") Long processId,
			@GraphQLName("taskId") Long taskId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_taskResourceComponentServiceObjects,
			this::_populateResourceContext,
			taskResource -> taskResource.deleteProcessTask(processId, taskId));

		return true;
	}

	@GraphQLField
	public boolean patchProcessTask(
			@GraphQLName("processId") Long processId,
			@GraphQLName("taskId") Long taskId, @GraphQLName("task") Task task)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_taskResourceComponentServiceObjects,
			this::_populateResourceContext,
			taskResource -> taskResource.patchProcessTask(
				processId, taskId, task));

		return true;
	}

	@GraphQLField
	public boolean patchProcessTaskComplete(
			@GraphQLName("processId") Long processId,
			@GraphQLName("taskId") Long taskId, @GraphQLName("task") Task task)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_taskResourceComponentServiceObjects,
			this::_populateResourceContext,
			taskResource -> taskResource.patchProcessTaskComplete(
				processId, taskId, task));

		return true;
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(AssigneeResource assigneeResource)
		throws Exception {

		assigneeResource.setContextAcceptLanguage(_acceptLanguage);
		assigneeResource.setContextCompany(_company);
		assigneeResource.setContextHttpServletRequest(_httpServletRequest);
		assigneeResource.setContextHttpServletResponse(_httpServletResponse);
		assigneeResource.setContextUriInfo(_uriInfo);
		assigneeResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			AssigneeMetricResource assigneeMetricResource)
		throws Exception {

		assigneeMetricResource.setContextAcceptLanguage(_acceptLanguage);
		assigneeMetricResource.setContextCompany(_company);
		assigneeMetricResource.setContextHttpServletRequest(
			_httpServletRequest);
		assigneeMetricResource.setContextHttpServletResponse(
			_httpServletResponse);
		assigneeMetricResource.setContextUriInfo(_uriInfo);
		assigneeMetricResource.setContextUser(_user);
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

	private void _populateResourceContext(ProcessResource processResource)
		throws Exception {

		processResource.setContextAcceptLanguage(_acceptLanguage);
		processResource.setContextCompany(_company);
		processResource.setContextHttpServletRequest(_httpServletRequest);
		processResource.setContextHttpServletResponse(_httpServletResponse);
		processResource.setContextUriInfo(_uriInfo);
		processResource.setContextUser(_user);
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

	private static ComponentServiceObjects<AssigneeResource>
		_assigneeResourceComponentServiceObjects;
	private static ComponentServiceObjects<AssigneeMetricResource>
		_assigneeMetricResourceComponentServiceObjects;
	private static ComponentServiceObjects<InstanceResource>
		_instanceResourceComponentServiceObjects;
	private static ComponentServiceObjects<NodeResource>
		_nodeResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProcessResource>
		_processResourceComponentServiceObjects;
	private static ComponentServiceObjects<SLAResource>
		_slaResourceComponentServiceObjects;
	private static ComponentServiceObjects<TaskResource>
		_taskResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private com.liferay.portal.kernel.model.User _user;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private UriInfo _uriInfo;

}