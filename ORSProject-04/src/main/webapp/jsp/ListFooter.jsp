<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.RoleBean"%>
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
	int pageNo1 = ServletUtility.getPageNo(request);
	List nextList = (List) request.getAttribute("nextList");
	%>
	<table width="100%">
		<input type="hidden" name="pageNo" value="<%=pageNo1%>">
		<tr>
		<tr>

			<td><input type="submit" name="operation"
				<%=pageNo1 == 1 ? "disabled" : ""%> value="<%=BaseCtl.OP_PREVIOUS%>"></td>
			<td align="center"><input type="submit" name="operation"
				value="<%=BaseCtl.OP_DELETE%>"></td>
			<td align="right"><input type="submit" name="operation"
				<%=nextList.size() == 0 ? "disabled" : ""%>
				value="<%=BaseCtl.OP_NEXT%>"></td>
		</tr>
	</table>

</body>
</html>