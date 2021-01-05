<%@page import="com.sunrays.proj4.controller.CollegeCtl"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>College</title>
</head>
<body>
	<form action="CollegeCtl" method="POST">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.CollegeBean"
			scope="request"></jsp:useBean>
		<br>

		<%
				if (bean.getId() > 0) {
			%><h1 align="center">Update College</h1>
		<%
				} else {
			%>
		<h1 align="center">Add College</h1>
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

		<table style="margin-left: 36%">
			<tr>
				<th align="left">Name<font color="red">*</font></th>
				<td><input type="text" name="name"
					placeholder="Enter College Name" size="25"
					value="<%=DataUtility.getStringData(bean.getName())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Address<font color="red">*</font></th>

				<td><input type="text" name="address"
					placeholder="Enter Address" style="fixed: width:98%; height: 50px"
					size="25" value="<%=DataUtility.getStringData(bean.getAddress())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font>
				</td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">City<font color="red">*</font></th>
				<td><input type="text" name="city" placeholder="Enter City"
					size="25" value="<%=DataUtility.getStringData(bean.getCity())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">State<font color="red">*</font></th>
				<td><input type="text" name="state" placeholder="Enter State"
					size="25" value="<%=DataUtility.getStringData(bean.getState())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>

			<tr>
				<th align="left">Mobile No<font color="red">*</font></th>
				<td><input type="text" name="mobileNo"
					placeholder="Enter Mobile Number" maxlength="10" size="25"
					value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>

			<tr>
				<th></th>
				<%
						if (bean.getId() > 0) {
					%><td colspan="2"><input type="submit" name="operation"
					value="<%=CollegeCtl.OP_UPDATE%>">&emsp; <input
					type="submit" name="operation" value="<%=CollegeCtl.OP_CANCEL%>"></td>
				<%
						} else {
					%>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=CollegeCtl.OP_SAVE%>"> &emsp;<input type="submit"
					name="operation" value="<%=CollegeCtl.OP_RESET%>"></td>
				<%
						}
					%>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
		</table>
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<%@ include file="Footer.jsp"%>

</body>
</html>