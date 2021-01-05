<%@page import="com.sunrays.proj4.controller.MyProfileCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@ page errorPage="ORSView.ERROR_VIEW"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
</head>
<body>

	<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

		<%@ include file="Header.jsp"%>
		<!-- <script type="text/javascript" src="../js/calendar.js"></script>
 -->
		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>



		<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=bean.getModifiedBy()%>"> <input type="hidden"
			name="createdDatetime"
			value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
		<input type="hidden" name="modifiedDatetime"
			value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
		<br>
		<center>
			<h1>My Profile</h1>

			<H3 align="center">
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>

					<H3>
						<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font>
					</H3>

				</font>
			</H3>
		</center>
		<table style="margin-left: 36%">

			<tr>
				<th align="left">LoginId<font color="red">*</font></th>
				<td><input type="text" name="login" size="25"
					value="<%=DataUtility.getStringData(bean.getLogin())%>"
					readonly="readonly"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
			</tr>
			<tr></tr>

			<tr>
				<th align="left">First Name<font color="red">*</font></th>
				<td><input type="text" name="firstName" size="25"
					value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
			</tr>
			<tr></tr>
			<tr>
				<th align="left">Last Name<font color="red">*</font></th>
				<td><input type="text" name="lastName" size="25"
					value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
			</tr>
			<tr></tr>
			<tr>
				<th align="left">Gender<font color="red">*</font></th>
				<td>
					<%
                            HashMap map = new HashMap();
                            map.put("Male", "Male");
                            map.put("Female", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),
                                    map);
                        %> <%=htmlList%> <font color="red"><%=ServletUtility.getErrorMessage("gender", request) %></font>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<th align="left">Mobile No<font color="red">*</font></th>
				<td><input type="text" name="mobileNo" maxlength="10" size="25"
					value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
					<font
					color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
			</tr>
			<tr></tr>

			<tr>
				<th align="left">Date Of Birth <font color="red">*</font></th>
				<td><input type="text" name="dob" id="datepicker"
					readonly="readonly" width="80" height="150" size="25"
					value="<%=DataUtility.getDateString(bean.getDob())%>"> <font
					color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
			</tr>
			<tr></tr>

			<tr>
				<th></th>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=MyProfileCtl.OP_SAVE %>">&emsp;&nbsp;<input
					type="submit" name="operation" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD %>">
					&nbsp;</td>
			</tr>
			<tr></tr>
		</table>
	</form>
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