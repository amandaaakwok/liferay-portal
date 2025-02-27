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

package com.liferay.portal.workflow.metrics.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.ProcessMetric;
import com.liferay.portal.workflow.metrics.rest.client.http.HttpInvoker;
import com.liferay.portal.workflow.metrics.rest.client.pagination.Page;
import com.liferay.portal.workflow.metrics.rest.client.pagination.Pagination;
import com.liferay.portal.workflow.metrics.rest.client.resource.v1_0.ProcessMetricResource;
import com.liferay.portal.workflow.metrics.rest.client.serdes.v1_0.ProcessMetricSerDes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
public abstract class BaseProcessMetricResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_processMetricResource.setContextCompany(testCompany);

		ProcessMetricResource.Builder builder = ProcessMetricResource.builder();

		processMetricResource = builder.locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		ProcessMetric processMetric1 = randomProcessMetric();

		String json = objectMapper.writeValueAsString(processMetric1);

		ProcessMetric processMetric2 = ProcessMetricSerDes.toDTO(json);

		Assert.assertTrue(equals(processMetric1, processMetric2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		ProcessMetric processMetric = randomProcessMetric();

		String json1 = objectMapper.writeValueAsString(processMetric);
		String json2 = ProcessMetricSerDes.toJSON(processMetric);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		ProcessMetric processMetric = randomProcessMetric();

		String json = ProcessMetricSerDes.toJSON(processMetric);

		Assert.assertFalse(json.contains(regex));

		processMetric = ProcessMetricSerDes.toDTO(json);
	}

	@Test
	public void testGetProcessMetricsPage() throws Exception {
		Page<ProcessMetric> page = processMetricResource.getProcessMetricsPage(
			RandomTestUtil.randomString(), Pagination.of(1, 2), null);

		Assert.assertEquals(0, page.getTotalCount());

		ProcessMetric processMetric1 =
			testGetProcessMetricsPage_addProcessMetric(randomProcessMetric());

		ProcessMetric processMetric2 =
			testGetProcessMetricsPage_addProcessMetric(randomProcessMetric());

		page = processMetricResource.getProcessMetricsPage(
			null, Pagination.of(1, 2), null);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(processMetric1, processMetric2),
			(List<ProcessMetric>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProcessMetricsPageWithPagination() throws Exception {
		ProcessMetric processMetric1 =
			testGetProcessMetricsPage_addProcessMetric(randomProcessMetric());

		ProcessMetric processMetric2 =
			testGetProcessMetricsPage_addProcessMetric(randomProcessMetric());

		ProcessMetric processMetric3 =
			testGetProcessMetricsPage_addProcessMetric(randomProcessMetric());

		Page<ProcessMetric> page1 = processMetricResource.getProcessMetricsPage(
			null, Pagination.of(1, 2), null);

		List<ProcessMetric> processMetrics1 =
			(List<ProcessMetric>)page1.getItems();

		Assert.assertEquals(
			processMetrics1.toString(), 2, processMetrics1.size());

		Page<ProcessMetric> page2 = processMetricResource.getProcessMetricsPage(
			null, Pagination.of(2, 2), null);

		Assert.assertEquals(3, page2.getTotalCount());

		List<ProcessMetric> processMetrics2 =
			(List<ProcessMetric>)page2.getItems();

		Assert.assertEquals(
			processMetrics2.toString(), 1, processMetrics2.size());

		Page<ProcessMetric> page3 = processMetricResource.getProcessMetricsPage(
			null, Pagination.of(1, 3), null);

		assertEqualsIgnoringOrder(
			Arrays.asList(processMetric1, processMetric2, processMetric3),
			(List<ProcessMetric>)page3.getItems());
	}

	@Test
	public void testGetProcessMetricsPageWithSortDateTime() throws Exception {
		testGetProcessMetricsPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, processMetric1, processMetric2) -> {
				BeanUtils.setProperty(
					processMetric1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetProcessMetricsPageWithSortInteger() throws Exception {
		testGetProcessMetricsPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, processMetric1, processMetric2) -> {
				BeanUtils.setProperty(processMetric1, entityField.getName(), 0);
				BeanUtils.setProperty(processMetric2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetProcessMetricsPageWithSortString() throws Exception {
		testGetProcessMetricsPageWithSort(
			EntityField.Type.STRING,
			(entityField, processMetric1, processMetric2) -> {
				Class<?> clazz = processMetric1.getClass();

				Method method = clazz.getMethod(
					"get" +
						StringUtil.upperCaseFirstLetter(entityField.getName()));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanUtils.setProperty(
						processMetric1, entityField.getName(),
						Collections.singletonMap("Aaa", "Aaa"));
					BeanUtils.setProperty(
						processMetric2, entityField.getName(),
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else {
					BeanUtils.setProperty(
						processMetric1, entityField.getName(),
						"Aaa" + RandomTestUtil.randomString());
					BeanUtils.setProperty(
						processMetric2, entityField.getName(),
						"Bbb" + RandomTestUtil.randomString());
				}
			});
	}

	protected void testGetProcessMetricsPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField, ProcessMetric, ProcessMetric, Exception>
					unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		ProcessMetric processMetric1 = randomProcessMetric();
		ProcessMetric processMetric2 = randomProcessMetric();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(
				entityField, processMetric1, processMetric2);
		}

		processMetric1 = testGetProcessMetricsPage_addProcessMetric(
			processMetric1);

		processMetric2 = testGetProcessMetricsPage_addProcessMetric(
			processMetric2);

		for (EntityField entityField : entityFields) {
			Page<ProcessMetric> ascPage =
				processMetricResource.getProcessMetricsPage(
					null, Pagination.of(1, 2), entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(processMetric1, processMetric2),
				(List<ProcessMetric>)ascPage.getItems());

			Page<ProcessMetric> descPage =
				processMetricResource.getProcessMetricsPage(
					null, Pagination.of(1, 2), entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(processMetric2, processMetric1),
				(List<ProcessMetric>)descPage.getItems());
		}
	}

	protected ProcessMetric testGetProcessMetricsPage_addProcessMetric(
			ProcessMetric processMetric)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetProcessMetricsPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetProcessMetric() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetProcessMetric() throws Exception {
		Assert.assertTrue(true);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		ProcessMetric processMetric1, ProcessMetric processMetric2) {

		Assert.assertTrue(
			processMetric1 + " does not equal " + processMetric2,
			equals(processMetric1, processMetric2));
	}

	protected void assertEquals(
		List<ProcessMetric> processMetrics1,
		List<ProcessMetric> processMetrics2) {

		Assert.assertEquals(processMetrics1.size(), processMetrics2.size());

		for (int i = 0; i < processMetrics1.size(); i++) {
			ProcessMetric processMetric1 = processMetrics1.get(i);
			ProcessMetric processMetric2 = processMetrics2.get(i);

			assertEquals(processMetric1, processMetric2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<ProcessMetric> processMetrics1,
		List<ProcessMetric> processMetrics2) {

		Assert.assertEquals(processMetrics1.size(), processMetrics2.size());

		for (ProcessMetric processMetric1 : processMetrics1) {
			boolean contains = false;

			for (ProcessMetric processMetric2 : processMetrics2) {
				if (equals(processMetric1, processMetric2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				processMetrics2 + " does not contain " + processMetric1,
				contains);
		}
	}

	protected void assertEqualsJSONArray(
		List<ProcessMetric> processMetrics, JSONArray jsonArray) {

		for (ProcessMetric processMetric : processMetrics) {
			boolean contains = false;

			for (Object object : jsonArray) {
				if (equalsJSONObject(processMetric, (JSONObject)object)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				jsonArray + " does not contain " + processMetric, contains);
		}
	}

	protected void assertValid(ProcessMetric processMetric) {
		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("instanceCount", additionalAssertFieldName)) {
				if (processMetric.getInstanceCount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"onTimeInstanceCount", additionalAssertFieldName)) {

				if (processMetric.getOnTimeInstanceCount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"overdueInstanceCount", additionalAssertFieldName)) {

				if (processMetric.getOverdueInstanceCount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("process", additionalAssertFieldName)) {
				if (processMetric.getProcess() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"untrackedInstanceCount", additionalAssertFieldName)) {

				if (processMetric.getUntrackedInstanceCount() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<ProcessMetric> page) {
		boolean valid = false;

		java.util.Collection<ProcessMetric> processMetrics = page.getItems();

		int size = processMetrics.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			graphQLFields.add(new GraphQLField(additionalAssertFieldName));
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(
		ProcessMetric processMetric1, ProcessMetric processMetric2) {

		if (processMetric1 == processMetric2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("instanceCount", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						processMetric1.getInstanceCount(),
						processMetric2.getInstanceCount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"onTimeInstanceCount", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						processMetric1.getOnTimeInstanceCount(),
						processMetric2.getOnTimeInstanceCount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"overdueInstanceCount", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						processMetric1.getOverdueInstanceCount(),
						processMetric2.getOverdueInstanceCount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("process", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						processMetric1.getProcess(),
						processMetric2.getProcess())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"untrackedInstanceCount", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						processMetric1.getUntrackedInstanceCount(),
						processMetric2.getUntrackedInstanceCount())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equalsJSONObject(
		ProcessMetric processMetric, JSONObject jsonObject) {

		for (String fieldName : getAdditionalAssertFieldNames()) {
			if (Objects.equals("instanceCount", fieldName)) {
				if (!Objects.deepEquals(
						processMetric.getInstanceCount(),
						jsonObject.getLong("instanceCount"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("onTimeInstanceCount", fieldName)) {
				if (!Objects.deepEquals(
						processMetric.getOnTimeInstanceCount(),
						jsonObject.getLong("onTimeInstanceCount"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("overdueInstanceCount", fieldName)) {
				if (!Objects.deepEquals(
						processMetric.getOverdueInstanceCount(),
						jsonObject.getLong("overdueInstanceCount"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("untrackedInstanceCount", fieldName)) {
				if (!Objects.deepEquals(
						processMetric.getUntrackedInstanceCount(),
						jsonObject.getLong("untrackedInstanceCount"))) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid field name " + fieldName);
		}

		return true;
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_processMetricResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_processMetricResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, ProcessMetric processMetric) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("instanceCount")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("onTimeInstanceCount")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("overdueInstanceCount")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("process")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("untrackedInstanceCount")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected ProcessMetric randomProcessMetric() throws Exception {
		return new ProcessMetric() {
			{
				instanceCount = RandomTestUtil.randomLong();
				onTimeInstanceCount = RandomTestUtil.randomLong();
				overdueInstanceCount = RandomTestUtil.randomLong();
				untrackedInstanceCount = RandomTestUtil.randomLong();
			}
		};
	}

	protected ProcessMetric randomIrrelevantProcessMetric() throws Exception {
		ProcessMetric randomIrrelevantProcessMetric = randomProcessMetric();

		return randomIrrelevantProcessMetric;
	}

	protected ProcessMetric randomPatchProcessMetric() throws Exception {
		return randomProcessMetric();
	}

	protected ProcessMetricResource processMetricResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(":");
					sb.append(entry.getValue());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append(")");
			}

			if (_graphQLFields.length > 0) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append("}");
			}

			return sb.toString();
		}

		private final GraphQLField[] _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseProcessMetricResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private
		com.liferay.portal.workflow.metrics.rest.resource.v1_0.
			ProcessMetricResource _processMetricResource;

}