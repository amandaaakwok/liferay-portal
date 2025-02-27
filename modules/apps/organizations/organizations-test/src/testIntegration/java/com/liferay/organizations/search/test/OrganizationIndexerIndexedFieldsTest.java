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

package com.liferay.organizations.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchEngineHelper;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.portal.search.test.util.ExpandoTableSearchFixture;
import com.liferay.portal.search.test.util.FieldValuesAssert;
import com.liferay.portal.search.test.util.IndexedFieldsFixture;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.users.admin.test.util.search.GroupBlueprint;
import com.liferay.users.admin.test.util.search.GroupSearchFixture;
import com.liferay.users.admin.test.util.search.UserSearchFixture;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Igor Fabiano Nazar
 * @author Luan Maoski
 */
@RunWith(Arquillian.class)
public class OrganizationIndexerIndexedFieldsTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		ExpandoTableSearchFixture expandoTableSearchFixture =
			new ExpandoTableSearchFixture(
				classNameLocalService, expandoColumnLocalService,
				expandoTableLocalService);

		GroupSearchFixture groupSearchFixture = new GroupSearchFixture();

		Group group = groupSearchFixture.addGroup(new GroupBlueprint());

		UserSearchFixture userSearchFixture = new UserSearchFixture(
			userLocalService, groupSearchFixture, null, null);

		userSearchFixture.setUp();

		OrganizationFixture organizationFixture = new OrganizationFixture(
			organizationService, countryService, regionService, language);

		organizationFixture.setUp();

		organizationFixture.setGroup(group);

		_expandoColumns = expandoTableSearchFixture.getExpandoColumns();
		_expandoTables = expandoTableSearchFixture.getExpandoTables();
		_expandoTableSearchFixture = expandoTableSearchFixture;

		_group = group;
		_groups = groupSearchFixture.getGroups();

		_indexedFieldsFixture = new IndexedFieldsFixture(
			resourcePermissionLocalService, searchEngineHelper);

		_organizationFixture = organizationFixture;
		_organizations = organizationFixture.getOrganizations();

		_users = userSearchFixture.getUsers();
	}

	@Test
	public void testIndexedFields() throws Exception {
		Organization organization = _organizationFixture.createOrganization(
			"abcd efgh");

		String searchTerm = "abcd";

		assertFieldValues(_expectedFieldValues(organization), searchTerm);
	}

	@Test
	public void testIndexedFieldsWithExpando() throws Exception {
		String expandoColumnName = "expandoColumnName";
		String expandoColumnObs = "expandoColumnObs";

		_expandoTableSearchFixture.addExpandoColumn(
			Organization.class, ExpandoColumnConstants.INDEX_TYPE_KEYWORD,
			expandoColumnObs, expandoColumnName);

		Map<String, Serializable> expandoValues =
			HashMapBuilder.<String, Serializable>put(
				expandoColumnName, "Software Developer"
			).put(
				expandoColumnObs, "Software Engineer"
			).build();

		Organization organization = _organizationFixture.createOrganization(
			"My Organization", expandoValues);

		String searchTerm = "Developer";

		assertFieldValues(
			_expectedFieldValuesWithExpando(organization), searchTerm);
	}

	protected void assertFieldValues(Map<String, ?> map, String searchTerm) {
		FieldValuesAssert.assertFieldValues(
			map, name -> !name.equals("score"),
			searcher.search(
				searchRequestBuilderFactory.builder(
				).companyId(
					_group.getCompanyId()
				).fields(
					StringPool.STAR
				).modelIndexerClasses(
					Organization.class
				).queryString(
					searchTerm
				).build()));
	}

	@Inject
	protected ClassNameLocalService classNameLocalService;

	@Inject
	protected CountryService countryService;

	@Inject
	protected ExpandoColumnLocalService expandoColumnLocalService;

	@Inject
	protected ExpandoTableLocalService expandoTableLocalService;

	@Inject(
		filter = "indexer.class.name=com.liferay.portal.kernel.model.Organization"
	)
	protected Indexer<Organization> indexer;

	@Inject
	protected Language language;

	@Inject
	protected OrganizationService organizationService;

	@Inject
	protected RegionService regionService;

	@Inject
	protected ResourcePermissionLocalService resourcePermissionLocalService;

	@Inject
	protected SearchEngineHelper searchEngineHelper;

	@Inject
	protected Searcher searcher;

	@Inject
	protected SearchRequestBuilderFactory searchRequestBuilderFactory;

	@Inject
	protected UserLocalService userLocalService;

	private Map<String, Object> _expectedFieldValues(Organization organization)
		throws Exception {

		Map<String, Object> map = HashMapBuilder.put(
			Field.COMPANY_ID,
			(Object)String.valueOf(organization.getCompanyId())
		).put(
			Field.ENTRY_CLASS_NAME, Organization.class.getName()
		).put(
			Field.ENTRY_CLASS_PK,
			String.valueOf(organization.getOrganizationId())
		).put(
			Field.NAME, organization.getName()
		).put(
			Field.NAME + "_sortable",
			StringUtil.toLowerCase(organization.getName())
		).put(
			Field.ORGANIZATION_ID,
			String.valueOf(organization.getOrganizationId())
		).put(
			Field.TREE_PATH, organization.getTreePath()
		).put(
			Field.USER_ID, String.valueOf(organization.getUserId())
		).put(
			Field.USER_NAME, StringUtil.toLowerCase(organization.getUserName())
		).put(
			"country", _organizationFixture.getCountryNames(organization)
		).put(
			"nameTreePath", organization.getName()
		).put(
			"nameTreePath_String_sortable",
			StringUtil.toLowerCase(organization.getName())
		).put(
			"parentOrganizationId",
			String.valueOf(organization.getParentOrganizationId())
		).put(
			"region",
			() -> {
				Region region = regionService.getRegion(
					organization.getRegionId());

				return StringUtil.toLowerCase(region.getName());
			}
		).build();

		_indexedFieldsFixture.populateUID(
			Organization.class.getName(), organization.getOrganizationId(),
			map);

		map.put(Field.TYPE, organization.getType());

		_populateDates(organization, map);
		_populateRoles(organization, map);

		return map;
	}

	private Map<String, Object> _expectedFieldValuesWithExpando(
			Organization organization)
		throws Exception {

		Map<String, Object> expectedFieldValues = _expectedFieldValues(
			organization);

		expectedFieldValues.put(
			"expando__keyword__custom_fields__expandoColumnName",
			"Software Developer");
		expectedFieldValues.put(
			"expando__keyword__custom_fields__expandoColumnObs",
			"Software Engineer");

		return expectedFieldValues;
	}

	private void _populateDates(
		Organization organization, Map<String, Object> map) {

		_indexedFieldsFixture.populateDate(
			Field.CREATE_DATE, organization.getCreateDate(), map);
		_indexedFieldsFixture.populateDate(
			Field.MODIFIED_DATE, organization.getModifiedDate(), map);
	}

	private void _populateRoles(
			Organization organization, Map<String, Object> map)
		throws Exception {

		_indexedFieldsFixture.populateRoleIdFields(
			organization.getCompanyId(), Organization.class.getName(),
			organization.getOrganizationId(), organization.getGroupId(), null,
			map);
	}

	@DeleteAfterTestRun
	private List<ExpandoColumn> _expandoColumns;

	@DeleteAfterTestRun
	private List<ExpandoTable> _expandoTables;

	private ExpandoTableSearchFixture _expandoTableSearchFixture;
	private Group _group;

	@DeleteAfterTestRun
	private List<Group> _groups;

	private IndexedFieldsFixture _indexedFieldsFixture;
	private OrganizationFixture _organizationFixture;

	@DeleteAfterTestRun
	private List<Organization> _organizations;

	@DeleteAfterTestRun
	private List<User> _users;

}