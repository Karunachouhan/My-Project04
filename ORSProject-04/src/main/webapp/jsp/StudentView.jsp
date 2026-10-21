<%@page import="in.co.rays.proj4.controller.StudentCtl"%>
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
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.StudentBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>
	<%@include file="Header.jsp"%>
	<div align="center">
		<h1><%=bean != null && bean.getId() > 0 ? "Update Student" : "Add Student"%></h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		<form action="<%=ORSView.STUDENT_CTL%>" method="post">

			<table>

				<tr>
					<th>College<font color="red">*</font></th>
					<td><select name='collegeId'>
							<option selected value=''>------------Select-------------</option>
							<option value='1'>Chameli Devi College</option>
							<option value='2'>IPS Academy</option>
							<option value='3'>Acropolis Institution</option>
							<option value='4'>IPS college</option>
							<option value='5'>Sage University</option>
							<option value='6'>Malwa Institute</option>
							<option value='7'>Women Polytechnic college</option>
					</select></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("roleId", request)%></td>
				</tr>

				<tr>
					<th>First Name<font color="red">*</font></th>
					<td><input type="text" name="firstName"
						placeholder="enter your first name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("firstName", request)%></td>
				</tr>

				<tr>
					<th>Last Name<font color="red">*</font></th>
					<td><input type="text" name="lastName"
						placeholder="enter your last name" value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%></td>
				</tr>

				<tr>
					<th>Email<font color="red">*</font></th>
					<td><input type="email" name="email"
						placeholder="enter your email" value="<%=DataUtility.getStringData(bean.getEmail())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("email", request)%></td>
				</tr>

				<tr>
					<th>Mobile No.<font color="red">*</font></th>
					<td><input type="number" name="mobileNo"
						placeholder="enter your mobile no" value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></td>
				</tr>

				<tr>
					<th>Dob<font color="red">*</font></th>
					<td><input type="date" name="dateOfBirth"
						placeholder="enter your date of birth" value="<%=DataUtility.getStringData(bean.getDateOfBirth())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("dateOfBirth", request)%></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=StudentCtl.OP_SAVE%>"></td>
				</tr>

			</table>
		</form>
	</div>


	<%@include file="Footer.jsp"%>


</body>
</html>