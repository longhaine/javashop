<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%JSONArray men = (JSONArray) request.getAttribute("men");%>
<%
for(int i = 0 ; i < men.length(); i++)
{ JSONObject object = men.getJSONObject(i);
	String name = object.getString("name");
%>
<h1><%=name %></h1>
<%} %>
</body>
</html>