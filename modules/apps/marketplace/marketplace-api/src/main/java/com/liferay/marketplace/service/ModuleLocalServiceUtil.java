/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.marketplace.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Module. This utility wraps
 * <code>com.liferay.marketplace.service.impl.ModuleLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ryan Park
 * @see ModuleLocalService
 * @generated
 */
public class ModuleLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.marketplace.service.impl.ModuleLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.marketplace.model.Module addModule(
			long appId, String bundleSymbolicName, String bundleVersion,
			String contextName)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addModule(
			appId, bundleSymbolicName, bundleVersion, contextName);
	}

	/**
	 * Adds the module to the database. Also notifies the appropriate model listeners.
	 *
	 * @param module the module
	 * @return the module that was added
	 */
	public static com.liferay.marketplace.model.Module addModule(
		com.liferay.marketplace.model.Module module) {

		return getService().addModule(module);
	}

	/**
	 * Creates a new module with the primary key. Does not add the module to the database.
	 *
	 * @param moduleId the primary key for the new module
	 * @return the new module
	 */
	public static com.liferay.marketplace.model.Module createModule(
		long moduleId) {

		return getService().createModule(moduleId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the module with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module that was removed
	 * @throws PortalException if a module with the primary key could not be found
	 */
	public static com.liferay.marketplace.model.Module deleteModule(
			long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteModule(moduleId);
	}

	/**
	 * Deletes the module from the database. Also notifies the appropriate model listeners.
	 *
	 * @param module the module
	 * @return the module that was removed
	 */
	public static com.liferay.marketplace.model.Module deleteModule(
		com.liferay.marketplace.model.Module module) {

		return getService().deleteModule(module);
	}

	public static void deleteModules(long appId) {
		getService().deleteModules(appId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.marketplace.model.impl.ModuleModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.marketplace.model.impl.ModuleModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.marketplace.model.Module fetchModule(
		long moduleId) {

		return getService().fetchModule(moduleId);
	}

	public static com.liferay.marketplace.model.Module fetchModule(
		long appId, String bundleSymbolicName, String bundleVersion,
		String contextName) {

		return getService().fetchModule(
			appId, bundleSymbolicName, bundleVersion, contextName);
	}

	/**
	 * Returns the module with the matching UUID and company.
	 *
	 * @param uuid the module's UUID
	 * @param companyId the primary key of the company
	 * @return the matching module, or <code>null</code> if a matching module could not be found
	 */
	public static com.liferay.marketplace.model.Module
		fetchModuleByUuidAndCompanyId(String uuid, long companyId) {

		return getService().fetchModuleByUuidAndCompanyId(uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the module with the primary key.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module
	 * @throws PortalException if a module with the primary key could not be found
	 */
	public static com.liferay.marketplace.model.Module getModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getModule(moduleId);
	}

	/**
	 * Returns the module with the matching UUID and company.
	 *
	 * @param uuid the module's UUID
	 * @param companyId the primary key of the company
	 * @return the matching module
	 * @throws PortalException if a matching module could not be found
	 */
	public static com.liferay.marketplace.model.Module
			getModuleByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getModuleByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.marketplace.model.impl.ModuleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of modules
	 */
	public static java.util.List<com.liferay.marketplace.model.Module>
		getModules(int start, int end) {

		return getService().getModules(start, end);
	}

	public static java.util.List<com.liferay.marketplace.model.Module>
		getModules(long appId) {

		return getService().getModules(appId);
	}

	/**
	 * Returns the number of modules.
	 *
	 * @return the number of modules
	 */
	public static int getModulesCount() {
		return getService().getModulesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the module in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param module the module
	 * @return the module that was updated
	 */
	public static com.liferay.marketplace.model.Module updateModule(
		com.liferay.marketplace.model.Module module) {

		return getService().updateModule(module);
	}

	public static ModuleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ModuleLocalService, ModuleLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ModuleLocalService.class);

		ServiceTracker<ModuleLocalService, ModuleLocalService> serviceTracker =
			new ServiceTracker<ModuleLocalService, ModuleLocalService>(
				bundle.getBundleContext(), ModuleLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}