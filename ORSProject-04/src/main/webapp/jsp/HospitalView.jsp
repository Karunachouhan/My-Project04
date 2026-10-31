<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.HospitalCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.HospitalBean"
		scope="request"></jsp:useBean>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>

	<%@include file="Header.jsp"%>
	<div align="center">
		<h1>
			<%= bean != null && bean.getId()>0 ? "Update Hospital" : "Add Hospital" %>
		</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.HOSPITAL_CTL%>" method="post">
			<table>

				<tr>
					<th>Name<font color="red">*</font></th>
					<td><input type="text" name="name"
						placeholder="enter your name"
						value="<%=DataUtility.getStringData(request.getParameter("name"))%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr>

				<tr>
					<th>Age<font color="red">*</font></th>
					<td><input type="number" name="age"
						placeholder="enter your age"
						value="<%=DataUtility.getStringData(request.getParameter("age"))%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("age", request)%></td>
				</tr>

				<tr>
					<th>Blood Group<font color="red">*</font></th>
					<td><input type="text" name="bloodGroup"
						placeholder="enter your blood group"
						value="<%=DataUtility.getStringData(request.getParameter("bloodGroup"))%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("bloodGroup", request)%></td>
				</tr>

				<tr>
					<th>Disease<font color="red">*</font></th>
					<td><input type="text" name="disease"
						placeholder="enter your disease"
						value="<%=DataUtility.getStringData(request.getParameter("disease"))%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("disease", request)%></td>
				</tr>
				
				<tr>
					<td><input type="hidden" name="id" value="<%=bean.getId()%>"></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=HospitalCtl.OP_SAVE%>"></td>
				</tr>
			</table>
		</form>

	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>