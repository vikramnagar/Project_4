<%@page import="java.util.HashMap"%>
<%@page import="com.sunrays.proj4.controller.CourseCtl"%>
<%@page import="java.util.List"%>
<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Course</title>
</head>
<body>

	<form action="<%=ORSView.COURSE_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.CourseBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("durationList");
		%>

		<br>
		<%
			if (bean.getId() > 0) {
		%><h1 align="center">Update Course</h1>
		<%
			} else {
		%>
		<h1 align="center">Add Course</h1>
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
				<th align="left">Course Name<font color="red">*</font></th>
				<td><input type="text" name="courseName" size="25"
					placeholder="Enter Course Name"
					value="<%=DataUtility.getStringData(bean.getName())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>> <font
					color="red"> <%=ServletUtility.getErrorMessage("courseName", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Duration<font color="red">*</font></th>
				<td>
					<%						 
						HashMap map = new HashMap();
					    map.put("1 Year", "1 Year");
					    map.put("2 Year", "2 Year");
					    map.put("3 Year", "3 Year");
					    map.put("4 Year", "4 Year");
					    map.put("5 Year", "5 Year");
							String htmlList = HTMLUtility.getList("duration", bean.getDuration(), map);
						%><%=htmlList%><font color="red"> <%=ServletUtility.getErrorMessage("duration", request)%>
				</font>
				</td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Description<font color="red">*</font></th>

				<td><input type="text" name="description"
					style="fixed: width:98%; height: 50px" size="25"
					placeholder="Description Of Course"
					value="<%=DataUtility.getStringData(bean.getDescription())%>">
					<font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>

			<tr>
				<th></th>
				<%
					if (bean.getId() > 0) {
				%><td colspan="2"><input type="submit" name="operation"
					value="<%=CourseCtl.OP_UPDATE%>">&emsp; <input
					type="submit" name="operation" value="<%=CourseCtl.OP_CANCEL%>"></td>
				<%
 	} else {
 %>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=CourseCtl.OP_SAVE%>">&emsp; <input type="submit"
					name="operation" value="<%=CourseCtl.OP_RESET%>"></td>
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

	<%@ include file="Footer.jsp"%>
</body>
</html>
