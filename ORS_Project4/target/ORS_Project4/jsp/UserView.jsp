<%@page import="com.sunrays.proj4.controller.UserCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>

<link rel="stylesheet" href="/ORS_Project4/css/jquery-ui.css">
<link rel="stylesheet" href="../css/style.css">
<script src="/ORS_Project4/js/jquery-1.12.4.js"></script>
<script src="/ORS_Project4/js/jquery-ui.js"></script>
<!-- <script>

$(function(){
	$("#datepicker").datepicker({
	dateFormat  : 'mm/dd/yy',
	defaultDate : "01/01/1992",
	changeMonth : true,
	changeYear: true,
	yearRange:'-30:-0'
	});
	});
			
	</script>
 -->
</head>
<body>

	<form action="<%=ORSView.USER_CTL%>" method="post">
		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("roleList");
		%>

		<center>
			<%
				if (bean.getId() > 0) {
			%><h1>Update user</h1>
			<%
				} else {
			%>

			<h1>Add User</h1>
			<%
				}
			%>

			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
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
				<th align="left">First Name <font color="red">*</font></th>
				<td><input type="text" name="firstName"
					placeholder="Enter First Name" size="25"
					value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Last Name<font color="red">*</font></th>
				<td><input type="text" name="lastName"
					placeholder="Enter Last Name" size="25"
					value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">LoginId<font color="red">*</font></th>
				<td><input type="text" name="login" placeholder="Enter LoginId"
					size="25" value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Password<font color="red">*</font></th>
				<td><input type="password" name="password"
					placeholder="Enter Password" size="25"
					value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Confirm Password<font color="red">*</font></th>
				<td><input type="password" name="confirmPassword"
					placeholder="Confirm Password" size=25
					value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
					<td><b>Gender</b><font color="red">*</font></td>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");

							String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
						%> <%=htmlList%>&nbsp; <font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
					</td>
				</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Role<font color="red">*</font></th>
				<td><%=HTMLUtility.getList("roleId", String.valueOf(bean.getRoleId()), l)%>
					<font color="red"> <%=ServletUtility.getErrorMessage("roleId", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Mobile No.<font color="red">*</font></th>
				<td><input type="text" name="mobileNo" maxlength="10"
					placeholder="Enter Mobile no." size="25"
					value="<%=DataUtility.getStringData(bean.getMobileNo())%>"><font
					color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
			</tr>
			<tr></tr>
			<tr>
			<tr>
				<th align="left">Date Of Birth<font color="red">*</font></th>
				<td><input type="text" name="dob" id="datepicker"
					placeholder="Enter DOB" size="25" readonly="readonly"
					value="<%=DataUtility.getDateString(bean.getDob())%>"> <font
					color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th></th>
				<%
					if (bean.getId() > 0) {
				%><td colspan="2"><input type="submit" name="operation"
					value="<%=UserCtl.OP_UPDATE%>"> <%
						} else {
					%>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=UserCtl.OP_SAVE%>"> <%
						}
					%> &emsp; <%
 	if (bean.getId() > 0) {
 %> <input type="submit" name="operation" value="<%=UserCtl.OP_CANCEL%>"></td>
				<%
					} else {
				%><input type="submit" name="operation"
					value="<%=UserCtl.OP_RESET%>">
				</td>
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

	<%@ include file="Footer.jsp"%>
</body>
</html>