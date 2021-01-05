<%@page import="com.sunrays.proj4.controller.FacultyCtl"%>
<%@page import="com.sunrays.proj4.controller.StudentCtl"%>
<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>Faculty</title>
<link rel="stylesheet" href="/ORS_Project4/css/jquery-ui.css">
<link rel="stylesheet" href="../css/style.css">
<script src="/ORS_Project4/js/jquery-1.12.4.js"></script>
<script src="/ORS_Project4/js/jquery-ui.js"></script>
</head>
<body>
	<form action="<%=ORSView.FACULTY_CTL%>" method="post">
		<%@ include file="Header.jsp"%>
		
		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.FacultyBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("collegeList");
			List lis = (List) request.getAttribute("subjectList");
			List li = (List) request.getAttribute("courseList");
		%>



		<%
			if (bean.getId() > 0) {
		%><h1 align="center">Update Faculty</h1>
		<%
			} else {
		%>
		<h1 align="center">Add Faculty</h1>
		<%
			}
		%>

		<center>
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
			<tr>
				<th align="left">Gender<font color="red">*</font></th>
				<td>
					<%
						HashMap map = new HashMap();
						map.put("Male", "Male");
						map.put("Female", "Female");

						String htmlList = HTMLUtility.getList("gender", bean.getGender(), map).replaceAll("200", "200");
					%> <%=htmlList%><font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font>
				</td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Date Of Birth<font color="red">*</font></th>
				<td><input type="text" name="dob" id="datepicker"
					placeholder="Enter DOB" size="25" readonly="readonly"
					value="<%=DataUtility.getDateString(bean.getDOB())%>"> <font
					color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
					
					
				
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Qualification<font color="red">*</font></th>
				<td><input type="text" name="qualification" size="25"
					placeholder="Enter Qualification"
					value="<%=DataUtility.getStringData(bean.getQualification())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("qualification", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>

			<tr>
				<th align="left">LoginId<font color="red">*</font></th>
				<td><input type="text" name="email" placeholder="Enter LoginId"
					size="25" value="<%=DataUtility.getStringData(bean.getEmailId())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("email", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Mobile No<font color="red">*</font></th>
				<td><input type="text" name="mobileNo" size="25"
					placeholder="Enter Mobile No." maxlength="10"
					value="<%=DataUtility.getStringData(bean.getMobileNo())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>


			<tr>
				<th align="left">College Name<font color="red">*</font></th>
				<td><%=HTMLUtility.getList("collegeId", String.valueOf(bean.getCollegeId()), l)%><font
					color="red"> <%=ServletUtility.getErrorMessage("collegeName", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Course Name<font color="red">*</font></th>
				<td><%=HTMLUtility.getList("courseList", String.valueOf(bean.getCourseId()), li)%><font
					color="red"> <%=ServletUtility.getErrorMessage("courseName", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Subject Name<font color="red">*</font></th>
				<td><%=HTMLUtility.getList("subjectList", String.valueOf(bean.getSubjectId()), lis)%><font
					color="red"> <%=ServletUtility.getErrorMessage("subjectName", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>


			<tr>
				<th></th>
				<%
					if (bean.getId() > 0) {
				%><td colspan="2"><input type="submit" name="operation"
					value="<%=FacultyCtl.OP_UPDATE%>"> <%
 	} else {
 %>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=FacultyCtl.OP_SAVE%>"> <%
 	}
 %> &emsp; <%
 	if (bean.getId() > 0) {
 %> <input type="submit" name="operation"
					value="<%=FacultyCtl.OP_CANCEL%>"></td>
				<%
					} else {
				%><input type="submit" name="operation"
					value="<%=FacultyCtl.OP_RESET%>">
				</td>
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