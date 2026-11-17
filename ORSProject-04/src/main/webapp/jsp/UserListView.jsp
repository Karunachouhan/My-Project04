<%@page import="in.co.rays.proj4.model.UserModel"%>
<%@page import="javax.management.modelmbean.ModelMBean"%>
<%@page import="in.co.rays.proj4.controller.UserCtl"%>
<%@page import="java.util.Iterator"%>
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
	List<UserBean> list = ServletUtility.getList(request);
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;
	Iterator<UserBean> it = list.iterator();
	RoleModel rmodel = new RoleModel();
	%>
	<%@ include file="Header.jsp"%>
	<div align="center">
		<h1>User List</h1>
		<h3 style="color: red"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<div class="d-flex gap-2">
			<a href="<%=ORSView.USER_REPORT_CTL%>" target="_blank"
				class="btn btn-sm btn-warning fw-semibold"> <i
				class="bi bi-file-earmark-pdf me-1"></i> Print PDF
			</a> <a href="<%=ORSView.USER_REPORT_CTL%>?type=doc" target="_blank"
				class="btn btn-sm btn-info fw-semibold"> <i
				class="bi bi-file-earmark-word me-1"></i> Print DOC
			</a>

		</div>

		<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
			<table>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName" value=""
						placeholder="search by first name"></td>

					<th>Last Name</th>
					<td><input type="text" name="lastName" value=""
						placeholder="search by last name"></td>

					<th>Login</th>
					<td><input type="email" name="login" value=""
						placeholder="search by login"></td>

					<th>Dob</th>
					<td><input type="date" name="dob" value=""
						placeholder="search by dob"><input type="submit"
						name="operation" value="<%=UserCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<table border="10px" width="100%">
				<tr style="background: skyblue">
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
					<th>S No.</th>
					<th>Profile</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login</th>
					<th>Role Name</th>
					<th>Gender</th>
					<th>Dob</th>
					<th>Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					UserBean bean = it.next();

					RoleBean rbean = rmodel.findByPk(bean.getRoleId());
				%>
				<tr align="center">
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>

					<td><img
						src="<%=ORSView.UPLOAD_PHOTO_CTL + "?id=" + bean.getId()%>"
						width="60" height="60"></td>

					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=rbean.getName()%></td>
					<td><%=bean.getGender()%></td>
					<td><%=bean.getDob()%></td>
					<td><a href="<%=ORSView.USER_CTL + "?id=" + bean.getId()%>">Edit</a></td>
				</tr>
				<%
				}
				%>

				<%@ include file="ListFooter.jsp"%>
			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>