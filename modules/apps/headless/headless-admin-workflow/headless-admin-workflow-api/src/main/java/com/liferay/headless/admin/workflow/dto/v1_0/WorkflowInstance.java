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

package com.liferay.headless.admin.workflow.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import io.swagger.v3.oas.annotations.media.Schema;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@GraphQLName("WorkflowInstance")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "WorkflowInstance")
public class WorkflowInstance {

	@Schema(
		description = "A flag that indicates whether the instance is complete."
	)
	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@JsonIgnore
	public void setCompleted(
		UnsafeSupplier<Boolean, Exception> completedUnsafeSupplier) {

		try {
			completed = completedUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "A flag that indicates whether the instance is complete."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean completed;

	@Schema(description = "The instance's completion date.")
	public Date getDateCompletion() {
		return dateCompletion;
	}

	public void setDateCompletion(Date dateCompletion) {
		this.dateCompletion = dateCompletion;
	}

	@JsonIgnore
	public void setDateCompletion(
		UnsafeSupplier<Date, Exception> dateCompletionUnsafeSupplier) {

		try {
			dateCompletion = dateCompletionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The instance's completion date.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date dateCompletion;

	@Schema(description = "The instance's creation date.")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@JsonIgnore
	public void setDateCreated(
		UnsafeSupplier<Date, Exception> dateCreatedUnsafeSupplier) {

		try {
			dateCreated = dateCreatedUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The instance's creation date.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date dateCreated;

	@Schema(description = "The instance's ID.")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The instance's ID.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long id;

	@Schema(
		description = "The object/asset that the instance's workflow is managing."
	)
	@Valid
	public ObjectReviewed getObjectReviewed() {
		return objectReviewed;
	}

	public void setObjectReviewed(ObjectReviewed objectReviewed) {
		this.objectReviewed = objectReviewed;
	}

	@JsonIgnore
	public void setObjectReviewed(
		UnsafeSupplier<ObjectReviewed, Exception>
			objectReviewedUnsafeSupplier) {

		try {
			objectReviewed = objectReviewedUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The object/asset that the instance's workflow is managing."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected ObjectReviewed objectReviewed;

	@Schema(description = "The name of the instance's workflow definition.")
	public String getWorkflowDefinitionName() {
		return workflowDefinitionName;
	}

	public void setWorkflowDefinitionName(String workflowDefinitionName) {
		this.workflowDefinitionName = workflowDefinitionName;
	}

	@JsonIgnore
	public void setWorkflowDefinitionName(
		UnsafeSupplier<String, Exception>
			workflowDefinitionNameUnsafeSupplier) {

		try {
			workflowDefinitionName = workflowDefinitionNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The name of the instance's workflow definition."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String workflowDefinitionName;

	@Schema
	public String getWorkflowDefinitionVersion() {
		return workflowDefinitionVersion;
	}

	public void setWorkflowDefinitionVersion(String workflowDefinitionVersion) {
		this.workflowDefinitionVersion = workflowDefinitionVersion;
	}

	@JsonIgnore
	public void setWorkflowDefinitionVersion(
		UnsafeSupplier<String, Exception>
			workflowDefinitionVersionUnsafeSupplier) {

		try {
			workflowDefinitionVersion =
				workflowDefinitionVersionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String workflowDefinitionVersion;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof WorkflowInstance)) {
			return false;
		}

		WorkflowInstance workflowInstance = (WorkflowInstance)object;

		return Objects.equals(toString(), workflowInstance.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (completed != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"completed\": ");

			sb.append(completed);
		}

		if (dateCompletion != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCompletion\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateCompletion));

			sb.append("\"");
		}

		if (dateCreated != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateCreated));

			sb.append("\"");
		}

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		if (objectReviewed != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"objectReviewed\": ");

			sb.append(String.valueOf(objectReviewed));
		}

		if (workflowDefinitionName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workflowDefinitionName\": ");

			sb.append("\"");

			sb.append(_escape(workflowDefinitionName));

			sb.append("\"");
		}

		if (workflowDefinitionVersion != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workflowDefinitionVersion\": ");

			sb.append("\"");

			sb.append(_escape(workflowDefinitionVersion));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		defaultValue = "com.liferay.headless.admin.workflow.dto.v1_0.WorkflowInstance",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append("\"");
			sb.append(entry.getValue());
			sb.append("\"");

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}