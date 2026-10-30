<%@page import="in.co.rays.proj4.controller.RailwayCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.RailwayBean"%>
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
	List<RailwayBean> list = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;
	Iterator<RailwayBean> it = list.iterator();
	%>

	<%@include file="Header.jsp"%>
	<div align="center">
		<h1>Railway List</h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		<form action="<%=ORSView.RAILWAY_LIST_CTL%>" method="post">
			<table>
				<tr>
					<th>Passenger Name</th>
					<td><input type="text" name="passengerName" value=""
						placeholder="search by passenger name"><input
						type="submit" name="operation" value="<%=RailwayCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<table border="10px" width="100%">
				<tr style="background: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
					<th>S No.</th>
					<th>Passenger Name</th>
					<th>Train No</th>
					<th>Seat No</th>
					<th>Journey Date</th>
					<th>Edit</th>
				</tr>
				<%
				while (it.hasNext()) {
					RailwayBean bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getPassengerName()%></td>
					<td><%=bean.getTrainNo()%></td>
					<td><%=bean.getSeatNo()%></td>
					<td><%=bean.getJourneyDate()%></td>
					<td><a href="<%=ORSView.RAILWAY_CTL + "?id=" + bean.getId()%>">Edit</a>
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