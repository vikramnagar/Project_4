<%@page import="com.sunrays.proj4.controller.SubjectCtl"%>
<%@page import="com.sunrays.proj4.controller.BaseCtl"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Subject</title>
</head>
<body>
	<form action="<%=ORSView.SUBJECT_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.SubjectBean"
			scope="request"></jsp:useBean>
		<%
		List li =(List)request.getAttribute("courseList");
		%>



		<br>
		<%if(bean.getId()>0) {%><h1 align="center">Update Subject</h1>
		<%}else{ %>
		<h1 align="center">Add Subject</h1>
		<%} %>
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
				<th align="left">Subject Name<font color="red">*</font></th>
				<td><input type="text" name="subjectName"
					placeholder="Enter Subject Name" size="25"
					value="<%=DataUtility.getStringData(bean.getName())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("subjectName", request)%></font></td>
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
				<th align="left">Description<font color="red">*</font></th>

				<td><input type="textarea" name="description"
					placeholder="Description Of Subject"
					style="fixed: width:98%; height: 50px" size="25"
					value="<%=DataUtility.getStringData(bean.getDescription()	)%>">
				</textarea><font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th></th>
				<%
						if (bean.getId() > 0) {
					%><td colspan="2"><input type="submit" name="operation"
					value="<%=SubjectCtl.OP_UPDATE%>">&emsp; <input
					type="submit" name="operation" value="<%=SubjectCtl.OP_CANCEL%>">
					<%
						} else {
					%>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=SubjectCtl.OP_SAVE%>"> &emsp;<input type="submit"
					name="operation" value="<%=SubjectCtl.OP_RESET%>"></td>
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
	<br>
	<br>
	<br>
	<br>
	<br>

	<%@ include file="Footer.jsp"%>
</body>
</html>