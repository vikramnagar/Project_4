<%@page import="com.sunrays.proj4.controller.StudentCtl"%>
<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.controller.StudentListCtl"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@page import="com.sunrays.proj4.bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Student</title>
<link rel="stylesheet" href="/ORS_Project4/css/jquery-ui.css">
<link rel="stylesheet" href="../css/style.css">
<script src="/ORS_Project4/js/jquery-1.12.4.js"></script>
<script src="/ORS_Project4/js/jquery-ui.js"></script>
</head>
<body>
	<form action="<%=ORSView.STUDENT_CTL%>" method="post">
		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.StudentBean"
			scope="request"></jsp:useBean>

		<%
            List l = (List) request.getAttribute("collegeList");
        %>
		<br>
		<center>
			<%if(bean.getId()>0){ %>
			<h1>Update Student</h1>

			<%}else{ %>
			<h1>Add Student</h1>
			<%} %>
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
				<th align="left">First Name<font color="red">*</font></th>
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
			<%-- <tr>
					<th align="left">LoginId<font color="red">*</font></th>
					<td><input type="text" name="login" placeholder="Enter LoginId" size=25
						value="<%=DataUtility.getStringData(bean.getEmail())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr><tr></tr> --%>
			<tr>
				<th align="left">LoginId<font color="red">*</font></th>
				<td><input type="text" name="login" placeholder="Enter loginId"
					size="25" value="<%=DataUtility.getStringData(bean.getEmail())%>"<%-- <%=(bean.getId() > 0) ? "readonly" : ""%> --%>><font
					color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<%-- <tr>
                    <th align="left">Password<font color="red">*</font></th>
                    <td><input type="password" name="password"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr> --%>
			<%--  <tr>
                    <th align="left">Confirm Password<font color="red">*</font></th>
                    <td><input type="password" name="confirmPassword"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("confirmPassword",
                    request)%></font></td>
                </tr> --%>
			<tr>
				<th align="left">Mobile No<font color="red">*</font></th>
				<td><input type="text" name="mobileNo"
					placeholder="Enter Mobile Number" size="25" maxlength="10"
					value="<%=DataUtility.getStringData(bean.getMobileNo())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("mobileNo",
                    request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<%-- <tr>
                    <th align="left">Gender</th>
                    <td>
                        <% 
                            HashMap map = new HashMap();
                            map.put("M", "Male");
                            map.put("F", "Female");
                            map.put("O", "Others");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),
                                    map);
                        %> <%=htmlList%></tr>
                        <tr>
                 --%>
			<th align="left">College<font color="red">*</font></th>
			<td><%=HTMLUtility.getList("collegeId",String.valueOf(bean.getCollegeId()), l)%><font
				color="red"> <%=ServletUtility.getErrorMessage("collegeId",
                    request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Date Of Birth<font color="red">*</font></th>
				<td><input type="text" name="dob" id="datepicker"
					readonly="readonly" placeholder="Enter DOB" size="25"
					value="<%=DataUtility.getDateString(bean.getDob())%>"> </a><font
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
					value="<%=StudentCtl.OP_UPDATE%>">&emsp; <input
					type="submit" name="operation" value="<%=StudentCtl.OP_CANCEL%>"></td>
				<%
						} else {
					%>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=StudentCtl.OP_SAVE%>"> &emsp;<input type="submit"
					name="operation" value="<%=StudentCtl.OP_RESET%>"></td>
				<%
						}
					%>
			</tr>
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