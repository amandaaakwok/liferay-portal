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

package com.liferay.portal.workflow.kaleo.metrics.integration.internal.search.index.reindexer;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;
import com.liferay.portal.workflow.metrics.search.index.ProcessWorkflowMetricsIndexer;
import com.liferay.portal.workflow.metrics.search.index.reindexer.WorkflowMetricsReindexer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(
	immediate = true, property = "workflow.metrics.index.entity.name=process",
	service = WorkflowMetricsReindexer.class
)
public class ProcessWorkflowMetricsReindexer
	implements WorkflowMetricsReindexer {

	@Override
	public void reindex(long companyId) throws PortalException {
		ActionableDynamicQuery actionableDynamicQuery =
			_kaleoDefinitionLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property companyIdProperty = PropertyFactoryUtil.forName(
					"companyId");

				dynamicQuery.add(companyIdProperty.eq(companyId));
			});
		actionableDynamicQuery.setPerformActionMethod(
			(KaleoDefinition kaleoDefinition) -> {
				String defaultLanguageId =
					LocalizationUtil.getDefaultLanguageId(
						kaleoDefinition.getTitle());

				_processWorkflowMetricsIndexer.addProcess(
					kaleoDefinition.isActive(), kaleoDefinition.getCompanyId(),
					kaleoDefinition.getCreateDate(),
					kaleoDefinition.getDescription(),
					kaleoDefinition.getModifiedDate(),
					kaleoDefinition.getName(),
					kaleoDefinition.getKaleoDefinitionId(),
					kaleoDefinition.getTitle(defaultLanguageId),
					kaleoDefinition.getTitleMap(),
					StringBundler.concat(
						kaleoDefinition.getVersion(), CharPool.PERIOD, 0));
			});

		actionableDynamicQuery.performActions();
	}

	@Reference
	private KaleoDefinitionLocalService _kaleoDefinitionLocalService;

	@Reference
	private ProcessWorkflowMetricsIndexer _processWorkflowMetricsIndexer;

}