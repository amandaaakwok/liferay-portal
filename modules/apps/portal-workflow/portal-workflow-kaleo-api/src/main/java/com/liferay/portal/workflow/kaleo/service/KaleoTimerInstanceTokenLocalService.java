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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for KaleoTimerInstanceToken. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerInstanceTokenLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface KaleoTimerInstanceTokenLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoTimerInstanceTokenLocalServiceUtil} to access the kaleo timer instance token local service. Add custom service methods to <code>com.liferay.portal.workflow.kaleo.service.impl.KaleoTimerInstanceTokenLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the kaleo timer instance token to the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimerInstanceToken the kaleo timer instance token
	 * @return the kaleo timer instance token that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public KaleoTimerInstanceToken addKaleoTimerInstanceToken(
		KaleoTimerInstanceToken kaleoTimerInstanceToken);

	public KaleoTimerInstanceToken addKaleoTimerInstanceToken(
			long kaleoInstanceTokenId, long kaleoTaskInstanceTokenId,
			long kaleoTimerId, String kaleoTimerName,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException;

	public List<KaleoTimerInstanceToken> addKaleoTimerInstanceTokens(
			KaleoInstanceToken kaleoInstanceToken,
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			Collection<KaleoTimer> kaleoTimers,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException;

	public KaleoTimerInstanceToken completeKaleoTimerInstanceToken(
			long kaleoTimerInstanceTokenId, ServiceContext serviceContext)
		throws PortalException;

	public void completeKaleoTimerInstanceTokens(
			List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens,
			ServiceContext serviceContext)
		throws PortalException;

	public void completeKaleoTimerInstanceTokens(
			long kaleoInstanceTokenId, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Creates a new kaleo timer instance token with the primary key. Does not add the kaleo timer instance token to the database.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key for the new kaleo timer instance token
	 * @return the new kaleo timer instance token
	 */
	@Transactional(enabled = false)
	public KaleoTimerInstanceToken createKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Deletes the kaleo timer instance token from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimerInstanceToken the kaleo timer instance token
	 * @return the kaleo timer instance token that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public KaleoTimerInstanceToken deleteKaleoTimerInstanceToken(
		KaleoTimerInstanceToken kaleoTimerInstanceToken);

	/**
	 * Deletes the kaleo timer instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token that was removed
	 * @throws PortalException if a kaleo timer instance token with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public KaleoTimerInstanceToken deleteKaleoTimerInstanceToken(
			long kaleoTimerInstanceTokenId)
		throws PortalException;

	public void deleteKaleoTimerInstanceToken(
			long kaleoInstanceTokenId, long kaleoTimerId)
		throws PortalException;

	public void deleteKaleoTimerInstanceTokens(long kaleoInstanceId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoTimerInstanceToken fetchKaleoTimerInstanceToken(
		long kaleoTimerInstanceTokenId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the kaleo timer instance token with the primary key.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token
	 * @throws PortalException if a kaleo timer instance token with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoTimerInstanceToken getKaleoTimerInstanceToken(
			long kaleoTimerInstanceTokenId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoTimerInstanceToken getKaleoTimerInstanceToken(
			long kaleoInstanceTokenId, long kaleoTimerId)
		throws PortalException;

	/**
	 * Returns a range of all the kaleo timer instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @return the range of kaleo timer instance tokens
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoTimerInstanceToken> getKaleoTimerInstanceTokens(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoTimerInstanceToken> getKaleoTimerInstanceTokens(
		long kaleoInstanceTokenId, boolean blocking, boolean completed,
		ServiceContext serviceContext);

	/**
	 * Returns the number of kaleo timer instance tokens.
	 *
	 * @return the number of kaleo timer instance tokens
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoTimerInstanceTokensCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoTimerInstanceTokensCount(
		long kaleoInstanceTokenId, boolean blocking, boolean completed,
		ServiceContext serviceContext);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Updates the kaleo timer instance token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimerInstanceToken the kaleo timer instance token
	 * @return the kaleo timer instance token that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public KaleoTimerInstanceToken updateKaleoTimerInstanceToken(
		KaleoTimerInstanceToken kaleoTimerInstanceToken);

}