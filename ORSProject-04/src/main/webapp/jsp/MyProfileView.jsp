
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@page import="java.util.HashMap"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile</title>
</head>

<body>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request">
	</jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	HashMap<String, String> map = new HashMap<String, String>();

	map.put("male", "Male");
	map.put("female", "Female");
	map.put("other", "Other");
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1>My Profile</h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<div align="center">

			<%
			if (bean.getId() > 0) {
			%>

			<form action="<%=ORSView.UPLOAD_PHOTO_CTL%>" method="POST"
				enctype="multipart/form-data">

				<input type="hidden" name="id" value="<%=bean.getId()%>">

				<table>
					<tr>
						<th>Photo</th>
					</tr>

					<tr>
						<td align="center"><img
							src="<%=ORSView.UPLOAD_PHOTO_CTL%>?id=<%=bean.getId()%>"
							onerror="this.style.display='none';" alt="User Photo" width="80"
							height="80"
							style="object-fit: cover; border-radius: 50%; border: 1px solid #ccc;">
						</td>
					</tr>

				</table>

			</form>

			<%
			}
			%>

		</div>

		<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

			<table>

				<tr>
					<th>First Name <font color="red">*</font></th>

					<td><input type="text" name="firstName"
						placeholder="Enter your first name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>">
					</td>

					<td style="color: red"><%=ServletUtility.getErrorMessage("firstName", request)%>
					</td>
				</tr>

				<tr>
					<th>Last Name <font color="red">*</font></th>

					<td><input type="text" name="lastName"
						placeholder="Enter your last name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>">
					</td>

					<td style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%>
					</td>
				</tr>

				<tr>
					<th>Login <font color="red">*</font></th>

					<td><input type="email" name="login" readonly
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>

					<td style="color: red"><%=ServletUtility.getErrorMessage("login", request)%>
					</td>
				</tr>

				<tr>
					<th>Password <font color="red">*</font></th>

					<td><input type="password" name="password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>">
					</td>

					<td style="color: red"><%=ServletUtility.getErrorMessage("password", request)%>
					</td>
				</tr>

				<tr>
					<th>Gender <font color="red">*</font></th>

					<td><%=HTMLUtility.getList("gender", DataUtility.getStringData(bean.getGender()).trim().toLowerCase(), map)%></td>

					<td style="color: red"><%=ServletUtility.getErrorMessage("gender", request)%>
					</td>
				</tr>

				<tr>
					<th>DOB <font color="red">*</font></th>

					<td><input type="date" name="dob"
						value="<%=DataUtility.getDateString(bean.getDob())%>"></td>

					<td style="color: red"><%=ServletUtility.getErrorMessage("dob", request)%>
					</td>
				</tr>

				<tr>
					<td><input type="hidden" name="id" value="<%=bean.getId()%>">

						<input type="hidden" name="roleId" value="<%=bean.getRoleId()%>">
					</td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=BaseCtl.OP_SAVE%>"></td>
				</tr>

			</table>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>







<%-- <%@page import="in.co.rays.proj4.controller.MyProfileCtl"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="java.util.HashMap"%>
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
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request"></jsp:useBean>
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("Male", "Male");
	map.put("Female", "Female");
	map.put("Other", "Other");
	%>
	<%@include file="Header.jsp"%>
	<div align="center">
		<h1>My Profile</h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<div align="center">
			<%
			if (bean.getId() > 0) {
			%>

			<form action="<%=ORSView.MY_PROFILE_CTL%>" method="POST"
				enctype="multipart/form-data">

				<input type="hidden" name="id" value="<%=bean.getId()%>">

				<table>

					<tr>
						<th>Photo</th>
					</tr>

					<tr>
						<td align="center"><img
							src="<%=ORSView.UPLOAD_PHOTO_CTL%>?id=<%=bean.getId()%>"
							onerror="this.style.display='none';" alt="User Photo" width="80"
							height="80"
							style="object-fit: cover; border-radius: 50%; border: 1px solid #ccc;">
						</td>
					</tr>

				</table>

			</form>

			<%
			}
			%>
		</div>

		<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">
			<table>

				<tr>
					<td><input type="hidden" name="id" value="<%=bean.getId()%>"></td>
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
						placeholder="enter your last name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%></td>
				</tr>

				<tr>
					<th>Gender<font color="red">*</font></th>
					<td><%=HTMLUtility.getList("gender", DataUtility.getStringData(bean.getGender()), map)%></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("gender", request)%></td>
				</tr>

				<tr>
					<th>DOB<font color="red">*</font></th>
					<td><input type="date" name="dob"
						value="<%=DataUtility.getStringData(bean.getDob())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("dob", request)%></td>

				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name=""
						value="<%=MyProfileCtl.OP_SAVE%>"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html> --%>