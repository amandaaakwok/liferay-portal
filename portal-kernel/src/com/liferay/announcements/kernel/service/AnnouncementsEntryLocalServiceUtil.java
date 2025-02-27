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

package com.liferay.announcements.kernel.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * Provides the local service utility for AnnouncementsEntry. This utility wraps
 * <code>com.liferay.portlet.announcements.service.impl.AnnouncementsEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsEntryLocalService
 * @generated
 */
public class AnnouncementsEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portlet.announcements.service.impl.AnnouncementsEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the announcements entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param announcementsEntry the announcements entry
	 * @return the announcements entry that was added
	 */
	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
		addAnnouncementsEntry(
			com.liferay.announcements.kernel.model.AnnouncementsEntry
				announcementsEntry) {

		return getService().addAnnouncementsEntry(announcementsEntry);
	}

	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
			addEntry(
				long userId, long classNameId, long classPK, String title,
				String content, String url, String type,
				java.util.Date displayDate, java.util.Date expirationDate,
				int priority, boolean alert)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addEntry(
			userId, classNameId, classPK, title, content, url, type,
			displayDate, expirationDate, priority, alert);
	}

	public static void checkEntries()
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().checkEntries();
	}

	public static void checkEntries(
			java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().checkEntries(startDate, endDate);
	}

	/**
	 * Creates a new announcements entry with the primary key. Does not add the announcements entry to the database.
	 *
	 * @param entryId the primary key for the new announcements entry
	 * @return the new announcements entry
	 */
	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
		createAnnouncementsEntry(long entryId) {

		return getService().createAnnouncementsEntry(entryId);
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
	 * Deletes the announcements entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param announcementsEntry the announcements entry
	 * @return the announcements entry that was removed
	 */
	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
		deleteAnnouncementsEntry(
			com.liferay.announcements.kernel.model.AnnouncementsEntry
				announcementsEntry) {

		return getService().deleteAnnouncementsEntry(announcementsEntry);
	}

	/**
	 * Deletes the announcements entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the announcements entry
	 * @return the announcements entry that was removed
	 * @throws PortalException if a announcements entry with the primary key could not be found
	 */
	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
			deleteAnnouncementsEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteAnnouncementsEntry(entryId);
	}

	public static void deleteEntries(long companyId) {
		getService().deleteEntries(companyId);
	}

	public static void deleteEntries(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteEntries(classNameId, classPK);
	}

	public static void deleteEntries(
			long companyId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteEntries(companyId, classNameId, classPK);
	}

	public static void deleteEntry(
			com.liferay.announcements.kernel.model.AnnouncementsEntry entry)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteEntry(entry);
	}

	public static void deleteEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteEntry(entryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.announcements.model.impl.AnnouncementsEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.announcements.model.impl.AnnouncementsEntryModelImpl</code>.
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

	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
		fetchAnnouncementsEntry(long entryId) {

		return getService().fetchAnnouncementsEntry(entryId);
	}

	/**
	 * Returns the announcements entry with the matching UUID and company.
	 *
	 * @param uuid the announcements entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching announcements entry, or <code>null</code> if a matching announcements entry could not be found
	 */
	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
		fetchAnnouncementsEntryByUuidAndCompanyId(String uuid, long companyId) {

		return getService().fetchAnnouncementsEntryByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the announcements entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.announcements.model.impl.AnnouncementsEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of announcements entries
	 * @param end the upper bound of the range of announcements entries (not inclusive)
	 * @return the range of announcements entries
	 */
	public static java.util.List
		<com.liferay.announcements.kernel.model.AnnouncementsEntry>
			getAnnouncementsEntries(int start, int end) {

		return getService().getAnnouncementsEntries(start, end);
	}

	/**
	 * Returns the number of announcements entries.
	 *
	 * @return the number of announcements entries
	 */
	public static int getAnnouncementsEntriesCount() {
		return getService().getAnnouncementsEntriesCount();
	}

	/**
	 * Returns the announcements entry with the primary key.
	 *
	 * @param entryId the primary key of the announcements entry
	 * @return the announcements entry
	 * @throws PortalException if a announcements entry with the primary key could not be found
	 */
	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
			getAnnouncementsEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAnnouncementsEntry(entryId);
	}

	/**
	 * Returns the announcements entry with the matching UUID and company.
	 *
	 * @param uuid the announcements entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching announcements entry
	 * @throws PortalException if a matching announcements entry could not be found
	 */
	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
			getAnnouncementsEntryByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAnnouncementsEntryByUuidAndCompanyId(
			uuid, companyId);
	}

	public static java.util.List
		<com.liferay.announcements.kernel.model.AnnouncementsEntry> getEntries(
			long userId, java.util.LinkedHashMap<Long, long[]> scopes,
			boolean alert, int flagValue, int start, int end) {

		return getService().getEntries(
			userId, scopes, alert, flagValue, start, end);
	}

	public static java.util.List
		<com.liferay.announcements.kernel.model.AnnouncementsEntry> getEntries(
			long userId, java.util.LinkedHashMap<Long, long[]> scopes,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, boolean alert,
			int flagValue, int start, int end) {

		return getService().getEntries(
			userId, scopes, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, alert, flagValue, start, end);
	}

	public static java.util.List
		<com.liferay.announcements.kernel.model.AnnouncementsEntry> getEntries(
			long companyId, long classNameId, long classPK, boolean alert,
			int start, int end) {

		return getService().getEntries(
			companyId, classNameId, classPK, alert, start, end);
	}

	public static java.util.List
		<com.liferay.announcements.kernel.model.AnnouncementsEntry> getEntries(
			long userId, long classNameId, long[] classPKs,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, boolean alert,
			int flagValue, int start, int end) {

		return getService().getEntries(
			userId, classNameId, classPKs, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, alert, flagValue, start,
			end);
	}

	public static int getEntriesCount(
		long userId, java.util.LinkedHashMap<Long, long[]> scopes,
		boolean alert, int flagValue) {

		return getService().getEntriesCount(userId, scopes, alert, flagValue);
	}

	public static int getEntriesCount(
		long userId, java.util.LinkedHashMap<Long, long[]> scopes,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean alert, int flagValue) {

		return getService().getEntriesCount(
			userId, scopes, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, alert, flagValue);
	}

	public static int getEntriesCount(
		long companyId, long classNameId, long classPK, boolean alert) {

		return getService().getEntriesCount(
			companyId, classNameId, classPK, alert);
	}

	public static int getEntriesCount(
		long userId, long classNameId, long[] classPKs, boolean alert,
		int flagValue) {

		return getService().getEntriesCount(
			userId, classNameId, classPKs, alert, flagValue);
	}

	public static int getEntriesCount(
		long userId, long classNameId, long[] classPKs, int displayDateMonth,
		int displayDateDay, int displayDateYear, int displayDateHour,
		int displayDateMinute, int expirationDateMonth, int expirationDateDay,
		int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean alert, int flagValue) {

		return getService().getEntriesCount(
			userId, classNameId, classPKs, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, alert, flagValue);
	}

	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
			getEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEntry(entryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
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

	public static java.util.List
		<com.liferay.announcements.kernel.model.AnnouncementsEntry>
			getUserEntries(long userId, int start, int end) {

		return getService().getUserEntries(userId, start, end);
	}

	public static int getUserEntriesCount(long userId) {
		return getService().getUserEntriesCount(userId);
	}

	/**
	 * Updates the announcements entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param announcementsEntry the announcements entry
	 * @return the announcements entry that was updated
	 */
	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
		updateAnnouncementsEntry(
			com.liferay.announcements.kernel.model.AnnouncementsEntry
				announcementsEntry) {

		return getService().updateAnnouncementsEntry(announcementsEntry);
	}

	public static com.liferay.announcements.kernel.model.AnnouncementsEntry
			updateEntry(
				long entryId, String title, String content, String url,
				String type, java.util.Date displayDate,
				java.util.Date expirationDate, int priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateEntry(
			entryId, title, content, url, type, displayDate, expirationDate,
			priority);
	}

	public static AnnouncementsEntryLocalService getService() {
		if (_service == null) {
			_service =
				(AnnouncementsEntryLocalService)PortalBeanLocatorUtil.locate(
					AnnouncementsEntryLocalService.class.getName());
		}

		return _service;
	}

	private static AnnouncementsEntryLocalService _service;

}