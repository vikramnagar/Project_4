
<%@page import="com.sunrays.proj4.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@page errorPage="ORSView.ERROR_VIEW"%>
<html>
<head>
<title>User Registration</title>
<link rel="stylesheet" href="/ORS_Project4/css/jquery-ui.css">
<link rel="stylesheet" href="../css/style.css">
<script src="/ORS_Project4/js/jquery-1.12.4.js"></script>
<script src="/ORS_Project4/js/jquery-ui.js"></script>

 <!--<style type="text/css">
 select	{width: 199px!important;}</style> --> 
 
</head>
<body>
	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="./js/calendar.js"></script>
		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<center>
			<br>
			<br>
			<h1>User Registration</h1>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>
			<br>
		</center>
		<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=bean.getModifiedBy()%>"> <input type="hidden"
			name="createdDatetime"
			value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
		<input type="hidden" name="modifiedDatetime"
			value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


		<table style="margin-left: 37%">

			<tr>
				<th align="left">First Name<font color="red">*</font></th>
				<td><input type="text" name="firstName"
					placeholder="Enter First Name" size=25
					value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
			</tr>
			<tr></tr>
			<tr>
				<th align="left">Last Name<font color="red">*</font></th>
				<td><input type="text" name="lastName"
					placeholder="Enter Last name" size=25
					value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
			</tr>
			<tr></tr>


			<tr>
				<th align="left">LoginId<font color="red">*</font></th>
				<td><input type="text" name="login" placeholder="Enter LoginId"
					size=25 value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
			</tr>
			<tr></tr>
			<tr>
				<th align="left">Password<font color="red">*</font></th>
				<td><input type="password" name="password"
					placeholder="Enter Password" size=25
					value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
			</tr>
			<tr></tr>
			<tr>
				<th align="left">Confirm Password<font color="red">*</font></th>
				<td><input type="password" name="confirmPassword"
					placeholder="Confirm Password" size=25
					value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
			</tr>
			<tr></tr>
			<tr>
				<th align="left">Gender<font color="red">*</font></th>
				<td>
					<%
							HashMap map = new HashMap();
							map.put("Female", "Female");
							map.put("Male", "Male");

							String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
						%> <%=htmlList%> <font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<th align="left">Mobile No.<font color="red">*</font></th>
				<td><input type="text" name="mobileNo"
					placeholder="Enter Mobile Number" size=25 maxlength="10"
					value="<%=DataUtility.getStringData(bean.getMobileNo())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
			</tr>
			<tr></tr>
			<tr>
				<th align="left">Date Of Birth<font color="red">*</font></th>
				<td><input type="text" name="dob" id="datepicker" size=25
					placeholder="Enter DOB" readonly="readonly"
					value="<%=DataUtility.getDateString(bean.getDob())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th></th>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=UserRegistrationCtl.OP_SIGN_UP%>">&emsp; <input
					type="submit" name="operation"
					value="<%=UserRegistrationCtl.OP_RESET%>"></td>
			</tr>
			<tr></tr>
		</table>

		</center>
	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>