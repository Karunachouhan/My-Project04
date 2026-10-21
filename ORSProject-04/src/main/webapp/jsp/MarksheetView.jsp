<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.MarksheetCtl"%>
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
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.MarksheetBean"
		scope="request"></jsp:useBean>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>
	<%@include file="Header.jsp"%>
	<div align="center">
		<h1>Add MarkSheet</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
			<table>
				<tr>
					<th>Roll No.<font color="red">*</font></th>
					<td><input type="number" name="rollNo"
						placeholder="enter your roll no"
						value="<%=DataUtility.getStringData(bean.getRollNo())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("rollNo", request)%></td>
				</tr>

				<tr>
					<th>Student Name<font color="red">*</font></th>
					<td><select name="studentId">
							<option selected value=''>-----Select------</option>
							<option value="1">Ram Sharma</option>
							<option value="2">Siya Rao</option>
							<option value="3">Megha Lowanshi</option>
							<option value="4">Aaradhiya Chouhan</option>
					</select></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("studentId", request)%></td>
				</tr>
				<tr>
					<th>Physics<font color="red">*</font></th>
					<td><input type="number" name="physics"
						placeholder="enter your physics marks"
						value="<%=DataUtility.getStringData(bean.getPhysics())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("physics", request)%></td>
				</tr>

				<tr>
					<th>Chemistry<font color="red">*</font></th>
					<td><input type="number" name="chemistry"
						placeholder="enter your chemistry marks"
						value="<%=DataUtility.getStringData(bean.getChemistry())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("chemistry", request)%></td>
				</tr>

				<tr>
					<th>Maths<font color="red">*</font></th>
					<td><input type="number" name="maths"
						placeholder="enter your maths marks"
						value="<%=DataUtility.getStringData(bean.getMaths())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("maths", request)%></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_SAVE%>"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>