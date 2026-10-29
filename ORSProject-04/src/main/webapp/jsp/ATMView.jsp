<%@page import="in.co.rays.proj4.controller.ATMCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.ATMBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>
	<%@include file="Header.jsp"%>
	<div align="center">
		<h1><%=bean != null && bean.getId() > 0 ? "Update ATM System" : "Add ATM System"%></h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		<form action="<%=ORSView.ATM_CTL%>" method="post">
		<table>
				<tr>
					<th>Location<font color="red">*</font></th>
					<td><input type="text" name="location"
						placeholder="enter your location"
						value="<%=DataUtility.getStringData(bean.getLocation())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("location", request)%></td>
				</tr>

				<tr>
					<th>Bank Name<font color="red">*</font></th>
					<td><input type="text" name="bank_name"
						placeholder="enter your bank name"
						value="<%=DataUtility.getStringData(bean.getBankName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("bank_name", request)%>
					</td>
				</tr>

				<tr>
					<th>Cash Available<font color="red">*</font></th>
					<td><input type="number" name="cash_available"
						placeholder="enter available cash"
						value="<%=DataUtility.getStringData(bean.getCashAvailable())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("cash_available", request)%></td>
				</tr>

				<tr>
					<th>Status<font color="red">*</font></th>
					<td><input type="text" name="status"
						placeholder="enter your status"
						value="<%=DataUtility.getStringData(bean.getStatus())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("status", request)%></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=ATMCtl.OP_SAVE%>"></td>
				</tr>
			</table>
		</form>
	</div>


	<%@include file="Footer.jsp"%>
</body>
</html>