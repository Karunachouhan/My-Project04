<%@page import="in.co.rays.proj4.controller.RailwayCtl"%>
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
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.RailwayBean"
		scope="request"></jsp:useBean>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>
	<%@include file="Header.jsp"%>
	<div align="center">
		<h1><%=bean != null && bean.getId() > 0 ? "Update Railway Reservation" : "Add Railway Reservation"%></h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>
		<form action="<%=ORSView.RAILWAY_CTL%>" method="post">

			<table>

				<tr>
					<th>Name<font color="red">*</font></th>
					<td><input type="text" name="passengerName"
						placeholder="enter your name"
						value="<%=DataUtility.getStringData(bean.getPassengerName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("passengerName", request)%></td>
				</tr>
				
				</table>
				<table>

				<tr>
					<th>Train No<font color="red">*</font></th>
					<td><input type="number" name="trainNo"
						placeholder="enter your train number"
						value="<%=DataUtility.getStringData(bean.getTrainNo())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("trainNo", request)%>
					</td>
				</tr>

				<tr>
					<th>Seat No<font color="red">*</font></th>
					<td><input type="number" name="seatNo"
						placeholder="enter your seat number"
						value="<%=DataUtility.getStringData(bean.getSeatNo())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("seatNo", request)%></td>
				</tr>

				<tr>
					<th>Journey Date<font color="red">*</font></th>
					<td><input type="text" name="journeyDate"
						placeholder="enter your journey date"
						value="<%=DataUtility.getStringData(bean.getJourneyDate())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("journeyDate", request)%></td>
				</tr>
				
				<tr>
					<td><input type="hidden" name="id" value="<%=bean.getId()%>"></td>
				</tr>
				

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=RailwayCtl.OP_SAVE%>"></td>
				</tr>
			</table>
		</form>
	</div>


	<%@include file="Footer.jsp"%>
</body>
</html>