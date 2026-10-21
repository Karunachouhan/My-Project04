<%@page import="in.co.rays.proj4.controller.StudentCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.StudentBean"%>
<%@page import="java.util.List"%>
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
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	List<StudentBean> list = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;
	Iterator<StudentBean> it = list.iterator();
	%>
	<%@include file="Header.jsp"%>
	<div align="center">
		<h1>Student List</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">
			<table>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName" value=""
						placeholder="search by first name"><input type="submit"
						name="operation" value="<%=StudentCtl.OP_SEARCH%>"></td>
			</table>
			<table border="10px" width="100%">
				<tr style="background: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
					<th>S No.</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>College</th>
					<th>Email</th>
					<th>Mobile No.</th>
					<th>Dob</th>
					<th>Edit</th>
				</tr>
				<%
				while (it.hasNext()) {
					StudentBean bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getCollegeName()%></td>
					<td><%=bean.getEmail()%></td>
					<td><%=bean.getMobileNo()%></td>
					<td><%=bean.getDateOfBirth()%></td>
					<td><a href="<%=ORSView.STUDENT_CTL + "?id=" + bean.getId()%>">Edit</a></td>
				</tr>
				<%
				}
				%>
				<%@include file="ListFooter.jsp" %>
			</table>
		</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>