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
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for KaleoDefinitionVersion. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinitionVersionLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface KaleoDefinitionVersionLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoDefinitionVersionLocalServiceUtil} to access the kaleo definition version local service. Add custom service methods to <code>com.liferay.portal.workflow.kaleo.service.impl.KaleoDefinitionVersionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the kaleo definition version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @return the kaleo definition version that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public KaleoDefinitionVersion addKaleoDefinitionVersion(
		KaleoDefinitionVersion kaleoDefinitionVersion);

	public KaleoDefinitionVersion addKaleoDefinitionVersion(
			long kaleoDefinitionId, String name, String title,
			String description, String content, String version,
			ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Creates a new kaleo definition version with the primary key. Does not add the kaleo definition version to the database.
	 *
	 * @param kaleoDefinitionVersionId the primary key for the new kaleo definition version
	 * @return the new kaleo definition version
	 */
	@Transactional(enabled = false)
	public KaleoDefinitionVersion createKaleoDefinitionVersion(
		long kaleoDefinitionVersionId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Deletes the kaleo definition version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @return the kaleo definition version that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	public KaleoDefinitionVersion deleteKaleoDefinitionVersion(
			KaleoDefinitionVersion kaleoDefinitionVersion)
		throws PortalException;

	/**
	 * Deletes the kaleo definition version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoDefinitionVersionId the primary key of the kaleo definition version
	 * @return the kaleo definition version that was removed
	 * @throws PortalException if a kaleo definition version with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public KaleoDefinitionVersion deleteKaleoDefinitionVersion(
			long kaleoDefinitionVersionId)
		throws PortalException;

	public void deleteKaleoDefinitionVersion(
			long companyId, String name, String version)
		throws PortalException;

	public void deleteKaleoDefinitionVersions(
			List<KaleoDefinitionVersion> kaleoDefinitionVersions)
		throws PortalException;

	public void deleteKaleoDefinitionVersions(long companyId, String name)
		throws PortalException;

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionVersionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionVersionModelImpl</code>.
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
	public KaleoDefinitionVersion fetchKaleoDefinitionVersion(
		long kaleoDefinitionVersionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinitionVersion fetchKaleoDefinitionVersion(
		long companyId, String name, String version);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinitionVersion fetchLatestKaleoDefinitionVersion(
			long companyId, String name)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinitionVersion fetchLatestKaleoDefinitionVersion(
			long companyId, String name,
			OrderByComparator<KaleoDefinitionVersion> orderByComparator)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinitionVersion getFirstKaleoDefinitionVersion(
			long companyId, String name)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the kaleo definition version with the primary key.
	 *
	 * @param kaleoDefinitionVersionId the primary key of the kaleo definition version
	 * @return the kaleo definition version
	 * @throws PortalException if a kaleo definition version with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinitionVersion getKaleoDefinitionVersion(
			long kaleoDefinitionVersionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinitionVersion getKaleoDefinitionVersion(
			long companyId, String name, String version)
		throws PortalException;

	/**
	 * Returns a range of all the kaleo definition versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definition versions
	 * @param end the upper bound of the range of kaleo definition versions (not inclusive)
	 * @return the range of kaleo definition versions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDefinitionVersion> getKaleoDefinitionVersions(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDefinitionVersion> getKaleoDefinitionVersions(
		long companyId, int start, int end,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDefinitionVersion> getKaleoDefinitionVersions(
			long companyId, String name)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDefinitionVersion> getKaleoDefinitionVersions(
		long companyId, String name, int start, int end,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator);

	/**
	 * Returns the number of kaleo definition versions.
	 *
	 * @return the number of kaleo definition versions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoDefinitionVersionsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoDefinitionVersionsCount(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoDefinitionVersionsCount(long companyId, String name);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinitionVersion[] getKaleoDefinitionVersionsPrevAndNext(
			long companyId, String name, String version)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoDefinitionVersion getLatestKaleoDefinitionVersion(
			long companyId, String name)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDefinitionVersion> getLatestKaleoDefinitionVersions(
		long companyId, int start, int end,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoDefinitionVersion> getLatestKaleoDefinitionVersions(
		long companyId, String keywords, int status, int start, int end,
		OrderByComparator<KaleoDefinitionVersion> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLatestKaleoDefinitionVersionsCount(
		long companyId, String keywords, int status);

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
	 * Updates the kaleo definition version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @return the kaleo definition version that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public KaleoDefinitionVersion updateKaleoDefinitionVersion(
		KaleoDefinitionVersion kaleoDefinitionVersion);

}