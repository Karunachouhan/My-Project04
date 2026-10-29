<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.GymBean"
		scope="request"></jsp:useBean>
		
		<% 
		
		
		%>
		
		
	<%@include file="Header.jsp"%>
	<div align="centre">
		<h1><%=bean != null && bean.getId() > 0 ? "Update Gym" : "Add Gym"%></h1>
		
		

	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>