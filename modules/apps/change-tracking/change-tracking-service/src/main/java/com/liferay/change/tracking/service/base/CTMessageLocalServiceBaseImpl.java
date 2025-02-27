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

package com.liferay.change.tracking.service.base;

import com.liferay.change.tracking.model.CTMessage;
import com.liferay.change.tracking.service.CTMessageLocalService;
import com.liferay.change.tracking.service.persistence.CTMessagePersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the ct message local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.change.tracking.service.impl.CTMessageLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.change.tracking.service.impl.CTMessageLocalServiceImpl
 * @generated
 */
public abstract class CTMessageLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CTMessageLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CTMessageLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.change.tracking.service.CTMessageLocalServiceUtil</code>.
	 */

	/**
	 * Adds the ct message to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ctMessage the ct message
	 * @return the ct message that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CTMessage addCTMessage(CTMessage ctMessage) {
		ctMessage.setNew(true);

		return ctMessagePersistence.update(ctMessage);
	}

	/**
	 * Creates a new ct message with the primary key. Does not add the ct message to the database.
	 *
	 * @param ctMessageId the primary key for the new ct message
	 * @return the new ct message
	 */
	@Override
	@Transactional(enabled = false)
	public CTMessage createCTMessage(long ctMessageId) {
		return ctMessagePersistence.create(ctMessageId);
	}

	/**
	 * Deletes the ct message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ctMessageId the primary key of the ct message
	 * @return the ct message that was removed
	 * @throws PortalException if a ct message with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CTMessage deleteCTMessage(long ctMessageId) throws PortalException {
		return ctMessagePersistence.remove(ctMessageId);
	}

	/**
	 * Deletes the ct message from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ctMessage the ct message
	 * @return the ct message that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CTMessage deleteCTMessage(CTMessage ctMessage) {
		return ctMessagePersistence.remove(ctMessage);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CTMessage.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ctMessagePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.change.tracking.model.impl.CTMessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return ctMessagePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.change.tracking.model.impl.CTMessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return ctMessagePersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return ctMessagePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return ctMessagePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CTMessage fetchCTMessage(long ctMessageId) {
		return ctMessagePersistence.fetchByPrimaryKey(ctMessageId);
	}

	/**
	 * Returns the ct message with the primary key.
	 *
	 * @param ctMessageId the primary key of the ct message
	 * @return the ct message
	 * @throws PortalException if a ct message with the primary key could not be found
	 */
	@Override
	public CTMessage getCTMessage(long ctMessageId) throws PortalException {
		return ctMessagePersistence.findByPrimaryKey(ctMessageId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(ctMessageLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CTMessage.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ctMessageId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			ctMessageLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CTMessage.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"ctMessageId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(ctMessageLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CTMessage.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ctMessageId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ctMessagePersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return ctMessageLocalService.deleteCTMessage((CTMessage)persistedModel);
	}

	public BasePersistence<CTMessage> getBasePersistence() {
		return ctMessagePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ctMessagePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the ct messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.change.tracking.model.impl.CTMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ct messages
	 * @param end the upper bound of the range of ct messages (not inclusive)
	 * @return the range of ct messages
	 */
	@Override
	public List<CTMessage> getCTMessages(int start, int end) {
		return ctMessagePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of ct messages.
	 *
	 * @return the number of ct messages
	 */
	@Override
	public int getCTMessagesCount() {
		return ctMessagePersistence.countAll();
	}

	/**
	 * Updates the ct message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ctMessage the ct message
	 * @return the ct message that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CTMessage updateCTMessage(CTMessage ctMessage) {
		return ctMessagePersistence.update(ctMessage);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CTMessageLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		ctMessageLocalService = (CTMessageLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CTMessageLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CTMessage.class;
	}

	protected String getModelClassName() {
		return CTMessage.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ctMessagePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	protected CTMessageLocalService ctMessageLocalService;

	@Reference
	protected CTMessagePersistence ctMessagePersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}