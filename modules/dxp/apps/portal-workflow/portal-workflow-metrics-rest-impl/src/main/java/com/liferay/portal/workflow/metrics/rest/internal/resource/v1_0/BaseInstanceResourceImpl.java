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

import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.VulcanBatchEngineTaskItemDelegate;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.ActionUtil;
import com.liferay.portal.vulcan.util.TransformUtil;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Instance;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.InstanceResource;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.constraints.NotNull;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseInstanceResourceImpl
	implements InstanceResource, EntityModelResource,
			   VulcanBatchEngineTaskItemDelegate<Instance> {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/instances'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "processId"),
			@Parameter(in = ParameterIn.QUERY, name = "assigneeIds"),
			@Parameter(in = ParameterIn.QUERY, name = "dateEnd"),
			@Parameter(in = ParameterIn.QUERY, name = "dateStart"),
			@Parameter(in = ParameterIn.QUERY, name = "slaStatuses"),
			@Parameter(in = ParameterIn.QUERY, name = "statuses"),
			@Parameter(in = ParameterIn.QUERY, name = "taskKeys"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/processes/{processId}/instances")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Instance")})
	public Page<Instance> getProcessInstancesPage(
			@NotNull @Parameter(hidden = true) @PathParam("processId") Long
				processId,
			@Parameter(hidden = true) @QueryParam("assigneeIds") Long[]
				assigneeIds,
			@Parameter(hidden = true) @QueryParam("dateEnd") java.util.Date
				dateEnd,
			@Parameter(hidden = true) @QueryParam("dateStart") java.util.Date
				dateStart,
			@Parameter(hidden = true) @QueryParam("slaStatuses") String[]
				slaStatuses,
			@Parameter(hidden = true) @QueryParam("statuses") String[] statuses,
			@Parameter(hidden = true) @QueryParam("taskKeys") String[] taskKeys,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/instances' -d $'{"assetTitle_i18n": ___, "assetType_i18n": ___, "className": ___, "classPK": ___, "completed": ___, "creator": ___, "dateCompletion": ___, "dateCreated": ___, "dateModified": ___, "duration": ___, "id": ___, "processId": ___, "processVersion": ___, "transitions": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "processId")})
	@Path("/processes/{processId}/instances")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Instance")})
	public Instance postProcessInstance(
			@NotNull @Parameter(hidden = true) @PathParam("processId") Long
				processId,
			Instance instance)
		throws Exception {

		return new Instance();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/instances/batch'  -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes("application/json")
	@POST
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "processId"),
			@Parameter(in = ParameterIn.QUERY, name = "callbackURL")
		}
	)
	@Path("/processes/{processId}/instances/batch")
	@Produces("application/json")
	@Tags(value = {@Tag(name = "Instance")})
	public Response postProcessInstanceBatch(
			@NotNull @Parameter(hidden = true) @PathParam("processId") Long
				processId,
			@Parameter(hidden = true) @QueryParam("callbackURL") String
				callbackURL,
			Object object)
		throws Exception {

		vulcanBatchEngineImportTaskResource.setContextAcceptLanguage(
			contextAcceptLanguage);
		vulcanBatchEngineImportTaskResource.setContextCompany(contextCompany);
		vulcanBatchEngineImportTaskResource.setContextHttpServletRequest(
			contextHttpServletRequest);
		vulcanBatchEngineImportTaskResource.setContextUriInfo(contextUriInfo);
		vulcanBatchEngineImportTaskResource.setContextUser(contextUser);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.entity(
			vulcanBatchEngineImportTaskResource.postImportTask(
				Instance.class.getName(), callbackURL, null, object)
		).build();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/instances/{instanceId}'  -u 'test@liferay.com:test'
	 */
	@Override
	@DELETE
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "processId"),
			@Parameter(in = ParameterIn.PATH, name = "instanceId")
		}
	)
	@Path("/processes/{processId}/instances/{instanceId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Instance")})
	public void deleteProcessInstance(
			@NotNull @Parameter(hidden = true) @PathParam("processId") Long
				processId,
			@NotNull @Parameter(hidden = true) @PathParam("instanceId") Long
				instanceId)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/instances/{instanceId}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "processId"),
			@Parameter(in = ParameterIn.PATH, name = "instanceId")
		}
	)
	@Path("/processes/{processId}/instances/{instanceId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Instance")})
	public Instance getProcessInstance(
			@NotNull @Parameter(hidden = true) @PathParam("processId") Long
				processId,
			@NotNull @Parameter(hidden = true) @PathParam("instanceId") Long
				instanceId)
		throws Exception {

		return new Instance();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PATCH' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/instances/{instanceId}' -d $'{"assetTitle_i18n": ___, "assetType_i18n": ___, "className": ___, "classPK": ___, "completed": ___, "creator": ___, "dateCompletion": ___, "dateCreated": ___, "dateModified": ___, "duration": ___, "id": ___, "processId": ___, "processVersion": ___, "transitions": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@PATCH
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "processId"),
			@Parameter(in = ParameterIn.PATH, name = "instanceId")
		}
	)
	@Path("/processes/{processId}/instances/{instanceId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Instance")})
	public void patchProcessInstance(
			@NotNull @Parameter(hidden = true) @PathParam("processId") Long
				processId,
			@NotNull @Parameter(hidden = true) @PathParam("instanceId") Long
				instanceId,
			Instance instance)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PATCH' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/instances/{instanceId}/complete' -d $'{"assetTitle_i18n": ___, "assetType_i18n": ___, "className": ___, "classPK": ___, "completed": ___, "creator": ___, "dateCompletion": ___, "dateCreated": ___, "dateModified": ___, "duration": ___, "id": ___, "processId": ___, "processVersion": ___, "transitions": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@PATCH
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "processId"),
			@Parameter(in = ParameterIn.PATH, name = "instanceId")
		}
	)
	@Path("/processes/{processId}/instances/{instanceId}/complete")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Instance")})
	public void patchProcessInstanceComplete(
			@NotNull @Parameter(hidden = true) @PathParam("processId") Long
				processId,
			@NotNull @Parameter(hidden = true) @PathParam("instanceId") Long
				instanceId,
			Instance instance)
		throws Exception {
	}

	@Override
	@SuppressWarnings("PMD.UnusedLocalVariable")
	public void create(
			java.util.Collection<Instance> instances,
			Map<String, Serializable> parameters)
		throws Exception {

		for (Instance instance : instances) {
			postProcessInstance(
				Long.valueOf((String)parameters.get("processId")), instance);
		}
	}

	@Override
	public void delete(
			java.util.Collection<Instance> instances,
			Map<String, Serializable> parameters)
		throws Exception {
	}

	@Override
	public EntityModel getEntityModel(Map<String, List<String>> multivaluedMap)
		throws Exception {

		return getEntityModel(
			new MultivaluedHashMap<String, Object>(multivaluedMap));
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap)
		throws Exception {

		return null;
	}

	@Override
	public Page<Instance> read(
			Filter filter, Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception {

		return getProcessInstancesPage(
			(Long)parameters.get("processId"),
			(Long[])parameters.get("assigneeIds"),
			(java.util.Date)parameters.get("dateEnd"),
			(java.util.Date)parameters.get("dateStart"),
			(String[])parameters.get("slaStatuses"),
			(String[])parameters.get("statuses"),
			(String[])parameters.get("taskKeys"), pagination);
	}

	@Override
	public void setLanguageId(String languageId) {
		this.contextAcceptLanguage = new AcceptLanguage() {

			@Override
			public List<Locale> getLocales() {
				return null;
			}

			@Override
			public String getPreferredLanguageId() {
				return languageId;
			}

			@Override
			public Locale getPreferredLocale() {
				return LocaleUtil.fromLanguageId(languageId);
			}

		};
	}

	@Override
	public void update(
			java.util.Collection<Instance> instances,
			Map<String, Serializable> parameters)
		throws Exception {
	}

	public void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {
		this.contextAcceptLanguage = contextAcceptLanguage;
	}

	public void setContextCompany(
		com.liferay.portal.kernel.model.Company contextCompany) {

		this.contextCompany = contextCompany;
	}

	public void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {

		this.contextHttpServletRequest = contextHttpServletRequest;
	}

	public void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {

		this.contextHttpServletResponse = contextHttpServletResponse;
	}

	public void setContextUriInfo(UriInfo contextUriInfo) {
		this.contextUriInfo = contextUriInfo;
	}

	public void setContextUser(
		com.liferay.portal.kernel.model.User contextUser) {

		this.contextUser = contextUser;
	}

	protected Map<String, String> addAction(
		String actionName, GroupedModel groupedModel, String methodName) {

		return ActionUtil.addAction(
			actionName, getClass(), groupedModel, methodName,
			contextScopeChecker, contextUriInfo);
	}

	protected Map<String, String> addAction(
		String actionName, Long id, String methodName, Long ownerId,
		String permissionName, Long siteId) {

		return ActionUtil.addAction(
			actionName, getClass(), id, methodName, contextScopeChecker,
			ownerId, permissionName, siteId, contextUriInfo);
	}

	protected Map<String, String> addAction(
		String actionName, String methodName, String permissionName,
		Long siteId) {

		return addAction(
			actionName, siteId, methodName, null, permissionName, siteId);
	}

	protected void preparePatch(Instance instance, Instance existingInstance) {
	}

	protected <T, R> List<R> transform(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transform(collection, unsafeFunction);
	}

	protected <T, R> R[] transform(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction,
		Class<?> clazz) {

		return TransformUtil.transform(array, unsafeFunction, clazz);
	}

	protected <T, R> R[] transformToArray(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction, Class<?> clazz) {

		return TransformUtil.transformToArray(
			collection, unsafeFunction, clazz);
	}

	protected <T, R> List<R> transformToList(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transformToList(array, unsafeFunction);
	}

	protected AcceptLanguage contextAcceptLanguage;
	protected com.liferay.portal.kernel.model.Company contextCompany;
	protected HttpServletRequest contextHttpServletRequest;
	protected HttpServletResponse contextHttpServletResponse;
	protected Object contextScopeChecker;
	protected UriInfo contextUriInfo;
	protected com.liferay.portal.kernel.model.User contextUser;
	protected GroupLocalService groupLocalService;
	protected ResourceActionLocalService resourceActionLocalService;
	protected ResourcePermissionLocalService resourcePermissionLocalService;
	protected RoleLocalService roleLocalService;
	protected VulcanBatchEngineImportTaskResource
		vulcanBatchEngineImportTaskResource;

}