<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.SubjectCtl"%>
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
    <jsp:useBean id="bean" class="in.co.rays.proj4.bean.SubjectBean" scope="request"></jsp:useBean>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>
	<%@include file="Header.jsp"%>
	<div align="center">
		<h1><%=bean != null && bean.getId()>0 ? "Update Subject" : "Add Subject"%></h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		<form action="<%=ORSView.COURSE_CTL%>" method="post">

			<table> 
<tr>
					<th>Course Name<font color="red">*</font></th>
					<td><select name='courseId'>
							<option selected value=''>------------Select-------------</option>
							<option value='1'>BCA</option>
							<option value='2'>BBA</option>
							<option value='3'>MTech</option>
							<option value='4'>BA</option>
							<option value='5'>MCA</option>
							<option value='6'>MBA</option>
							<option value='7'>Btech</option>
					</select></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("studentId", request)%></td>
				</tr>

				<tr>
					<th>Description<font color="red">*</font></th>
					<td><input type="text" name="description"
						placeholder="enter description" value="<%=DataUtility.getStringData(bean.getDescription())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("description", request)%>
					</td>
				</tr>
				
				<tr>
				     <th></th>
					<td><input type="submit" name="operation"
						value="<%=SubjectCtl.OP_SAVE%>"></td>
			</table>
		</form>
		</div>
</body>
</html>