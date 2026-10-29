<%@page import="in.co.rays.proj4.controller.ATMCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.ATMBean"%>
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
	List<ATMBean> list = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;
	Iterator<ATMBean> it = list.iterator();
	%>

	<%@include file="Header.jsp"%>
	<div align="center">
		<h1>ATM List</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.ATM_LIST_CTL%>" method="post">
			<table>
				<tr>
					<th>Bank Name</th>
					<td><input type="text" name="name" value=""
						placeholder="search by college name"><input type="submit"
						name="operation" value="<%=ATMCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<table border="10px" width="100%">
				<tr style="background: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
					<th>S No.</th>
					<th>Location</th>
					<th>Bank Name</th>
					<th>Cash Available</th>
					<th>Status</th>
					<th>Edit</th>
				</tr>
				<%
				while (it.hasNext()) {
					ATMBean bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getLocation()%></td>
					<td><%=bean.getBankName()%></td>
					<td><%=bean.getCashAvailable()%></td>
					<td><%=bean.getStatus()%></td>
					<td><a href="<%=ORSView.ATM_CTL + "?id=" + bean.getId()%>">Edit</a></td>
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