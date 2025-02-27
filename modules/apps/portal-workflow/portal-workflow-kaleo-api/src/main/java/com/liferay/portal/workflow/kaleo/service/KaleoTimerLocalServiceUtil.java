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

package com.liferay.portal.workflow.kaleo.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for KaleoTimer. This utility wraps
 * <code>com.liferay.portal.workflow.kaleo.service.impl.KaleoTimerLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerLocalService
 * @generated
 */
public class KaleoTimerLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.workflow.kaleo.service.impl.KaleoTimerLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the kaleo timer to the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimer the kaleo timer
	 * @return the kaleo timer that was added
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer
		addKaleoTimer(
			com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer) {

		return getService().addKaleoTimer(kaleoTimer);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer
			addKaleoTimer(
				String kaleoClassName, long kaleoClassPK,
				long kaleoDefinitionId, long kaleoDefinitionVersionId,
				com.liferay.portal.workflow.kaleo.definition.Timer timer,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addKaleoTimer(
			kaleoClassName, kaleoClassPK, kaleoDefinitionId,
			kaleoDefinitionVersionId, timer, serviceContext);
	}

	/**
	 * Creates a new kaleo timer with the primary key. Does not add the kaleo timer to the database.
	 *
	 * @param kaleoTimerId the primary key for the new kaleo timer
	 * @return the new kaleo timer
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer
		createKaleoTimer(long kaleoTimerId) {

		return getService().createKaleoTimer(kaleoTimerId);
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
	 * Deletes the kaleo timer from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimer the kaleo timer
	 * @return the kaleo timer that was removed
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer
		deleteKaleoTimer(
			com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer) {

		return getService().deleteKaleoTimer(kaleoTimer);
	}

	/**
	 * Deletes the kaleo timer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimerId the primary key of the kaleo timer
	 * @return the kaleo timer that was removed
	 * @throws PortalException if a kaleo timer with the primary key could not be found
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer
			deleteKaleoTimer(long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteKaleoTimer(kaleoTimerId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl</code>.
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer
		fetchKaleoTimer(long kaleoTimerId) {

		return getService().fetchKaleoTimer(kaleoTimerId);
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
	 * Returns the kaleo timer with the primary key.
	 *
	 * @param kaleoTimerId the primary key of the kaleo timer
	 * @return the kaleo timer
	 * @throws PortalException if a kaleo timer with the primary key could not be found
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer
			getKaleoTimer(long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getKaleoTimer(kaleoTimerId);
	}

	/**
	 * Returns a range of all the kaleo timers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @return the range of kaleo timers
	 */
	public static java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoTimer> getKaleoTimers(
			int start, int end) {

		return getService().getKaleoTimers(start, end);
	}

	public static java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoTimer> getKaleoTimers(
			String kaleoClassName, long kaleoClassPK) {

		return getService().getKaleoTimers(kaleoClassName, kaleoClassPK);
	}

	public static java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoTimer> getKaleoTimers(
			String kaleoClassName, long kaleoClassPK, boolean blocking) {

		return getService().getKaleoTimers(
			kaleoClassName, kaleoClassPK, blocking);
	}

	/**
	 * Returns the number of kaleo timers.
	 *
	 * @return the number of kaleo timers
	 */
	public static int getKaleoTimersCount() {
		return getService().getKaleoTimersCount();
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
	 * Updates the kaleo timer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimer the kaleo timer
	 * @return the kaleo timer that was updated
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoTimer
		updateKaleoTimer(
			com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer) {

		return getService().updateKaleoTimer(kaleoTimer);
	}

	public static KaleoTimerLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<KaleoTimerLocalService, KaleoTimerLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(KaleoTimerLocalService.class);

		ServiceTracker<KaleoTimerLocalService, KaleoTimerLocalService>
			serviceTracker =
				new ServiceTracker
					<KaleoTimerLocalService, KaleoTimerLocalService>(
						bundle.getBundleContext(), KaleoTimerLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}