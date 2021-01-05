<%@page import="com.sunrays.proj4.controller.RoleCtl"%>
<%@page import="com.sunrays.proj4.controller.BaseCtl"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Role</title>
</head>
<body>
	<form action="<%=ORSView.ROLE_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.RoleBean"
			scope="request"></jsp:useBean>
		<br>
		<br>

		<%
				if (bean.getId() > 0) {
			%><h1 align="center">Update Role</h1>
		<%
				} else {
			%>
		<h1 align="center">Add Role</h1>
		<%
				}
			%>

		<center>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>
		</center>
		<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=bean.getModifiedBy()%>"> <input type="hidden"
			name="createdDatetime"
			value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
		<input type="hidden" name="modifiedDatetime"
			value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


		<table style="margin-left: 38%">
			<tr>
				<th align="left">Name<font color="red">*</font></th>
				<td><input type="text" name="name" size="26"
					placeholder="Role Name"
					value="<%=DataUtility.getStringData(bean.getName())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>

			<tr>
				<th align="left">Description<font color="red">*</font></th>

				<td><input type="text" name="description"
					placeholder="Description Of Role"
					style="fixed: width:98%; height: 50px" size="26"
					value="<%=DataUtility.getStringData(bean.getDescription())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th></th>
				<%
						if (bean.getId() > 0) {
					%><td colspan="2"><input type="submit" name="operation"
					value="<%=RoleCtl.OP_UPDATE%>">&emsp; &emsp; <input
					type="submit" name="operation" value="<%=RoleCtl.OP_CANCEL%>">
					<%
						} else {
					%>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=RoleCtl.OP_SAVE%>"> &emsp; <input type="submit"
					name="operation" value="<%=RoleCtl.OP_RESET%>">
					<%
						}
					%></td>
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