<%--
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

@generated
--%>

<%@ page import="com.liferay.portal.json.JSONFactoryImpl"%>
<%@ page import="com.liferay.portal.kernel.servlet.taglib.aui.ScriptData"%>
<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HashMapBuilder" %>
<%@ page import="com.liferay.portal.kernel.util.StringBundler" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="java.io.Serializable" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%!
private static ArrayList<Object> _toArrayList(Object obj) {
	return (ArrayList<Object>)_deserialize(obj);
}

private static HashMap<String, Object> _toHashMap(Object obj) {
	return (HashMap<String, Object>)_deserialize(obj);
}

public static void _initJSONFactoryUtil() {
	if (JSONFactoryUtil.getJSONFactory() == null) {
		(new JSONFactoryUtil()).setJSONFactory(new JSONFactoryImpl());
	}
}

private static Object _deserialize(Object obj) {
	if (obj != null) {
		String json = JSONFactoryUtil.looseSerialize(obj);

		json = StringUtil.unquote(json);

		return JSONFactoryUtil.looseDeserialize(json);
	}

	return null;
}
%>

<%
_initJSONFactoryUtil();
%>