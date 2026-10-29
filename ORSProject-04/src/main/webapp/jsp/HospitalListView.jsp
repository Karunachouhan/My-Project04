<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.HospitalBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.HospitalCtl"%>
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
	List<HospitalBean> list = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;
	Iterator<HospitalBean> it = list.iterator();
	%>
	<%@include file="Header.jsp"%>
	<div align="center">
		<h1>Hospital List</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		<form action="<%=ORSView.HOSPITAL_LIST_CTL%>" method="post">
			<table>
				<tr>
					<th>Name</th>
					<td><input type="text" name="name" value=""
						placeholder="search by name"><input type="submit"
						name="operation" value="<%=HospitalCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<table border="10px" width="100%">
				<tr style="background: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
					<th>S No.</th>
					<th>Name</th>
					<th>Age</th>
					<th>Blood Group</th>
					<th>Disease</th>
					<th>Edit</th>
				</tr>
				<%
				while (it.hasNext()) {
					HospitalBean bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getAge()%></td>
					<td><%=bean.getBloodGroup()%></td>
					<td><%=bean.getDisease()%></td>
					<td><a
						href="<%=ORSView.HOSPITAL_CTL + "?id=" + bean.getId()%>">Edit</a>
				</tr>
				<%
				}
				%>

				<%@include file="ListFooter.jsp"%>

			</table>

		</form>

	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>