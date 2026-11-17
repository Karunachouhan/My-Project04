<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.OnlineShoppingCtl"%>
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

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.OnlineShoppingBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>
	<%@include file="Header.jsp"%>
	<div align="center">
		<h1><%=bean != null && bean.getId() > 0 ? "Update OnlineShopping" : "Add OnlineShopping"%></h1>
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.ONLINE_SHOPPING_CTL%>" method="post">
			<table>
				<tr>
					<th>Name<font color="red">*</font></th>
					<td><input typt="text" name="name"
						value="<%=DataUtility.getStringData(bean.getName())%>"
						placeholder="enter product name"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr>

				<tr>
					<th>Price<font color="red">*</font></th>
					<td><input typt="number" name="price"
						value="<%=DataUtility.getStringData(bean.getPrice())%>"
						placeholder="enter product price"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("price", request)%></td>
				</tr>

				<tr>
					<th>Category<font color="red">*</font></th>
					<td><input typt="text" name="category"
						value="<%=DataUtility.getStringData(bean.getCategory())%>"
						placeholder="enter product category"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("category", request)%></td>
				</tr>

				<tr>
					<th>Stock<font color="red">*</font></th>
					<td><input typt="number" name="stock"
						value="<%=DataUtility.getStringData(bean.getStock())%>"
						placeholder="enter stock"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("stock", request)%></td>
				</tr>

				<tr>
					<td><input type="hidden" name="id" value="<%=bean.getId()%>"></td>
				</tr>

				<tr>
				<th></th>
					<td><input type="submit" name="operation"
						value="<%=OnlineShoppingCtl.OP_SAVE%>"></td>
				</tr>

			</table>

		</form>

	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>