<%@page import="com.sunrays.proj4.controller.ChangePasswordCtl"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<%@ include file="Header.jsp"%>
<body>

	<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">

		

		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>
		<br>
		<center>
			<h1>Change Password</h1>


			<H3>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H3>

			<H3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H3>
		</center>
		<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=bean.getModifiedBy()%>"> <input type="hidden"
			name="createdDatetime"
			value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
		<input type="hidden" name="modifiedDatetime"
			value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

		<table style="margin-left: 36%">



			<tr>
				<th align="left">Old Password<font color="red">*</font></th>
				<td><input type="password" name="oldPassword" size="25"
					placeholder="Enter Old Password"
					value=<%=DataUtility.getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request.getParameter("oldPassword")))%>>
					<font color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>

			<tr>
				<th align="left">New Password<font color="red">*</font></th>
				<td><input type="password" name="newPassword" size="25"
					placeholder="Enter New Password"
					value=<%=DataUtility
                    .getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("newPassword")))%>><font
					color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Confirm Password<font color="red">*</font></th>
				<td><input type="password" name="confirmPassword" size="25"
					placeholder="Enter Confirm Password"
					value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%>><font
					color="red"> <%=ServletUtility
                    .getErrorMessage("confirmPassword", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th></th>
				<td colspan="2"><input type="submit" name="operation"
					value="<%= ChangePasswordCtl.OP_SAVE%>"> &emsp; <input
					type="submit" name="operation"
					value="<%= ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>"></td>
				</td>

			</tr>

		</table>

	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<br>
	<br>
	<br>
	<br>
	<br>

	<%@ include file="Footer.jsp"%>
</body>
</html>
</body>
</html>