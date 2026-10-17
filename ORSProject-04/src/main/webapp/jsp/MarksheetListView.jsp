<%@page import="in.co.rays.proj4.controller.MarksheetCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.MarksheetBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
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
	List<MarksheetBean> list = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;
	Iterator<MarksheetBean> it = list.iterator();
	%>

	<%@include file="Header.jsp"%>
	<div align="center">
		<h1>MarkSheet List</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">

			<table>
				<tr>
					<th>Roll No.</th>
					<td><input type="number" name="rollNo"
						placeholder="search by roll no" value=""><input
						type="submit" name="opertion" value="<%=MarksheetCtl.OP_SEARCH%>"></td>

					<th>Name</th>
					<td><input type="text" name="name" value=""
						placeholder="search by name"><input type="submit"
						name="operation" value="<%=MarksheetCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<table border="10px" width="100%">
				<tr style="background: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
					<th>S No.</th>
					<th>Roll No.</th>
					<th>Student Id</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Edit</th>
				</tr>
				<%
				while (it.hasNext()) {
					MarksheetBean bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<th><%=index++%></th>
					<th><%=bean.getRollNo()%></th>
					<th><%=bean.getStudentId()%></th>
					<th><%=bean.getName()%></th>
					<th><%=bean.getPhysics()%></th>
					<th><%=bean.getChemistry()%></th>
					<th><%=bean.getMaths()%></th>
					<th><a
						href="<%=ORSView.MARKSHEET_CTL + "?id=" + bean.getId()%>">Edit</a>
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