<%@page import="com.sunrays.proj4.controller.TimeTableCtl"%>
<%@page import="com.sunrays.proj4.controller.StudentCtl"%>
<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Time Table</title>
<link rel="stylesheet" href="/ORS_Project4/css/jquery-ui.css">

<script src="/ORS_Project4/js/jquery-1.12.4.js"></script>
<script src="/ORS_Project4/js/jquery-ui.js"></script>
 <script type="text/javascript">
function noSunday(date){
	return [date.getDay() !=0, false];
};

$(function(){
	 var endyear = new Date();
	var startyear = new Date().getFullYear()-2;
 
	$("#datepicker1").datepicker({
		//dateFormat :'dd/mm/yy',
		 minDate : new Date(),
		maxDate : new Date().getFullYear()-2, 
	    changeMonth : true,
		changeYear : true,
		beforeShowDay : noSunday,
		yearRange : "endyear:startyear"

	});
});
</script> 



</head>
<body>
	<form action="<%=ORSView.TIME_TABLE_CTL%>" method="post">
		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.TimeTableBean"
			scope="request"></jsp:useBean>

		<%
			List lis = (List) request.getAttribute("subjectList");
			List li = (List) request.getAttribute("courseList");
		%>

		<br>

		<%
				if (bean.getId() > 0) {
			%><h1 align="center">Update Time Table</h1>
		<%
				} else {
			%>
		<h1 align="center">Add Time Table</h1>
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
			</tr>
			<th align="left">Course Name<font color="red">*</font></th>
			<td><%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), li)%><font
				color="red"> <%=ServletUtility.getErrorMessage("courseId", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Subject Name<font color="red">*</font></th>
				<td><%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), lis)%>
					<font color="red"> <%=ServletUtility.getErrorMessage("subjectId", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>

			<%-- <tr>
					<th align="left">Semester</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("1", "1st Semester");
							map.put("2", "2nd Semester");
							map.put("3", "3rd Semester");
							map.put("4", "4th Semester");
							map.put("5", "5th Semester");
							map.put("6", "6th Semester");
							map.put("7", "7th Semester");
							map.put("8", "8th Semester");

							String htmlList = HTMLUtility.getList("semester", bean.getSemester(), map);
						%> <%=htmlList%></td>
				</tr>
 --%>
			<tr>
				<th align="left">Date Of Exam<font color="red">*</font></th>
				<td><input type="text" name="examDate" id="datepicker1"
					readonly="readonly" size="25" placeholder="Enter Date Of Exam"
					value="<%=DataUtility.getDateString(bean.getExamDate())%>">

					</a><font color="red"> <%=ServletUtility.getErrorMessage("examDate", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>

			 <tr>
					<th align="left">Exam Time<font color="red">*</font></th>
					<td>
						<%HashMap map = new HashMap();
							map = new HashMap();
							map.put("7:00 AM TO 10:00 AM", "7:00 AM TO 10:00 AM");
							map.put("10:00 AM TO 1:00 PM", "10:00 AM TO 1:00 PM");
							map.put("1:00 PM TO 3:00 PM", "1:00 PM TO 3:00 PM");
							map.put("3:00 PM TO 6:00 PM", "3:00 PM TO 6:00 PM");

							String htmlList = HTMLUtility.getList("time", bean.getExamTime(), map);
						%> <%=htmlList%><font
						color="red"> <%=ServletUtility.getErrorMessage("time", request)%></font></td>
				</tr> 
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th></th>
				<%
					if (bean.getId() > 0) {
				%><td colspan="2"><input type="submit" name="operation"
					value="<%=TimeTableCtl.OP_UPDATE%>"> <%
 	} else {
 %>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=TimeTableCtl.OP_SAVE%>"> <%
 	}
 %> &emsp; <%
 	if (bean.getId() > 0) {
 %> <input type="submit" name="operation"
					value="<%=TimeTableCtl.OP_CANCEL%>"></td>
				<%
					} else {
				%><input type="submit" name="operation"
					value="<%=TimeTableCtl.OP_RESET%>">
				</td>
				<%
					}
				%>
			</tr>
		</table>
	</form>
	<br>
	<%@ include file="Footer.jsp"%>
</body>
</html>