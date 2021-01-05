<%@page import="com.sunrays.proj4.bean.RoleBean"%>
<%@page import="com.sunrays.proj4.controller.LoginCtl"%>
<%@page import="com.sunrays.proj4.controller.ORSView"%>
<%@page import="com.sunrays.proj4.bean.UserBean"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ORS Project</title>
<style>
/* .c{
	background-color: lightblue;
} */
</style>
<link rel="stylesheet" href="http://localhost:8080/ORS_Project4/css/jquery-ui.css">
<link rel="stylesheet" href="http://localhost:8080/ORS_Project4/css/style.css">
<script src="http://localhost:8080/ORS_Project4/js/jquery-1.12.4.js"></script>
<script src="http://localhost:8080/ORS_Project4/js/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "-80:-18"
				//yearRange : "-18:-80"
		});
	}); 
</script>

<script type="text/javascript">
	    
    function selectall(bx) 
    {
		var form = bx.form;
		var isChecked = bx.checked;
		for (var i = 0; i < form.length; i++) 
		{
			if (form[i].type == 'checkbox') 
			{
				form[i].checked = isChecked;
			}
		}
	}
    function unchek(bx)
    	{
    	 document.getElementById("ck").checked=false;
    	}
	
	$(document).ready(function()
	{$  $('[name="ids"]').click(function()
			{
		if(!($(this)[0].checked))
		{
			$('[onclick="selectall(this)"]')[0].checked=false;
		};
	});	
	});
    
	
	
</script>
</head>
<div class="c">
<body >
	<%
		UserBean userBean = (UserBean) session.getAttribute("user");

		boolean userLoggedIn = userBean != null;
       String welcomeMsg = "Hi, ";
      if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomeMsg += userBean.getFirstName() + "(" + role + ")";
		} else{
			
			welcomeMsg +="Guest";
			
		}
      
	%>


	<table width="100%" border="0" style="margin-top: 0%">
		<tr>
		 
			<td width="90%"><a href="<%=ORSView.WELCOME_CTL%>" 
			style="text-decoration:none"><b><font
						size="3">Welcome</font></b></a> || <%
		 if (userLoggedIn) {
			%> <a
				 " style="text-decoration:none" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><b>Logout</b></a>

				<%
					} else {
				%> <a " style="text-decoration:none" href="<%=ORSView.LOGIN_CTL%>"><b><font size="3">Login</font></b></a>
				<%
 	}
 %></td>
			<td rowspan="2">
				<h1 align="Right">
					<img src="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg" width="300"
						height="80">
				</h1>
			</td>

		</tr>
		<tr>
			<td><h3><%=welcomeMsg%></h3></td>
		</tr>
		
		<%
			if (userLoggedIn) {
		%>
				<tr>
			<% 
 	if (userBean.getRoleId() == RoleBean.ADMIN) {
 %>
			<td colspan="2"><a href="<%=ORSView.MY_PROFILE_CTL%>"><b><font
						size="3">My Profile</font></b></a>| <a
				href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b><font size="3">Change
							Password</font></b></a> | <a href="<%=ORSView.USER_CTL%>"><b><font
						size="3">Add User</font></b></a> | <a href="<%=ORSView.USER_LIST_CTL%>"><b><font
						size="3">User List</font></b></a> | <a href="<%=ORSView.COLLEGE_CTL%>"><b><font
						size="3">Add College</font></b></a> | <a
				href="<%=ORSView.COLLEGE_LIST_CTL%>"><b><font size="3">College
							List</font></b></a> | <a href="<%=ORSView.STUDENT_CTL%>"><b><font
						size="3">Add Student</font></b></a> | <a
				href="<%=ORSView.STUDENT_LIST_CTL%>"><b><font size="3">Student
							List</font></b></a> | <a href="<%=ORSView.COURSE_CTL%>"><b><font
						size="3">Add Course</font></b></a>| <a href="<%=ORSView.COURSE_LIST_CTL%>"><b><font
						size="3">Course List</font></b></a>| <a href="<%=ORSView.SUBJECT_CTL%>"><b><font
						size="3">Add Subject</font> </b></a>| <a
				href="<%=ORSView.SUBJECT_LIST_CTL%>"><b><font size="3">Subject
							List </font></b></a>| <a href="<%=ORSView.ROLE_CTL%>"><b><font size="3">Add
							Role</font></b></a> | <a href="<%=ORSView.ROLE_LIST_CTL%>"><b><font
						size="3">Role List</font></b></a> | <a href="<%=ORSView.TIME_TABLE_CTL%>"><b><font
						size="3">Add Time Table</font> </b></a> | <a
				href="<%=ORSView.TIME_TABLE_LIST_CTL%>"><b><font size="3">Time
							Table List</font> </b></a>| <a href="<%=ORSView.FACULTY_CTL%>"><b><font
						size="3">Add Faculty</font></b></a>| <a
				href="<%=ORSView.FACULTY_LIST_CTL%>"><b><font size="3">Faculty
							List </font></b></a>| <a href="<%=ORSView.MARKSHEET_CTL%>"><b><font
						size="3">Add Marksheet</font></b></a> | <a
				href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b><font size="3">Marksheet
							List</font></b></a> | <a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b><font
						size="3">Get Marksheet</font></b></a> | <a
				href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b><font
						size="3">Marksheet Merit List</font></b> </a> | <a
				href="<%=ORSView.JAVA_DOC_VIEW%>" target="blank"><b><font
						size="3">Java Doc </font></b></a> <%

				
					}
				%> <%
 	if (userBean.getRoleId() == RoleBean.STUDENT) {
 %>
			<td colspan="2"><a href="<%=ORSView.MY_PROFILE_CTL%>"><b><font
						size="3">My Profile</font></b></a>| <a
				href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b><font size="3">Change
							Password</font></b></a> | <a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b><font
						size="3">Get Marksheet</font></b></a> | <a
				href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b><font
						size="3">Marksheet Merit List</font></b> </a> | <%-- <a
			href="<%=ORSView.JAVA_DOC_VIEW%>"><b><font size="3">Java
							Doc</font></b></a> --%> | <%
 
				
					}
				%> <%
 	if (userBean.getRoleId() == RoleBean.FACULTY) {
 %>
			<td colspan="2"><a href="<%=ORSView.MY_PROFILE_CTL%>"><b><font
						size="3">My Profile</font></b></a> | <a
				href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b><font size="3">Change
							Password</font></b></a> | <a href="<%=ORSView.USER_LIST_CTL%>"><b><font
						size="3">User List</font></b></a> | <a
				href="<%=ORSView.COLLEGE_LIST_CTL%>"><b><font size="3">College
							List</font></b></a> | <a href="<%=ORSView.STUDENT_CTL%>"><b><font
						size="3">Add Student</font></b></a> | <a
				href="<%=ORSView.STUDENT_LIST_CTL%>"><b><font size="3">Student
							List</font></b></a> | <a href="<%=ORSView.COURSE_LIST_CTL%>"><b><font
						size="3">Course List</font></b></a> | <a href="<%=ORSView.SUBJECT_CTL%>"><b><font
						size="3">Add Subject</font></b></a> | <a
				href="<%=ORSView.SUBJECT_LIST_CTL%>"><b><font size="3">Subject
							List</font></b></a> | <a href="<%=ORSView.ROLE_LIST_CTL%>"><b><font
						size="3">Role List</font></b></a> | <a href="<%=ORSView.TIME_TABLE_CTL%>"><b><font
						size="3">Add Time Table</font></b></a> | <a
				href="<%=ORSView.TIME_TABLE_LIST_CTL%>"><b><font size="3">Time
							Table List</font></b></a> | <a href="<%=ORSView.FACULTY_LIST_CTL%>"><b><font
						size="3">Faculty List</font> </b></a> | <a
				href="<%=ORSView.MARKSHEET_CTL%>"><b><font size="3"></font>Add
						Marksheet</b></a> | <a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b><font
						size="3">Get Marksheet</font></b></a> | <a
				href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b><font
						size="3">Marksheet Merit List</font></b></a> | <a
				href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b><font size="3"></font>Marksheet
						List</b></a> | <%-- <a href="<%=ORSView.JAVA_DOC_VIEW%>"><b><font
						size="3">Java Doc</font></b></a> --%>
				<%
  
                %> <%
					}
				%> <%
 	if (userBean.getRoleId() == RoleBean.COLLAGE_SCHOOL) {
 %>
			<td colspan="2"><a href="<%=ORSView.MARKSHEET_CTL%>"><b>Add
						Marksheet</b></a> | <a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b>Marksheet
						List</b></a> | <a href="<%=ORSView.USER_CTL%>"><b>Add User</b></a> | <a
				href="<%=ORSView.USER_LIST_CTL%>"><b>User List</b></a> | <a
				href="<%=ORSView.COLLEGE_LIST_CTL%>"><b>College List</b></a> | <a
				href="<%=ORSView.STUDENT_CTL%>"><b>Add Student</b></a> | <a
				href="<%=ORSView.STUDENT_LIST_CTL%>"><b>Student List</b></a> | <a
				href="<%=ORSView.ROLE_CTL%>"><b>Add Role</b></a> | <a
				href="<%=ORSView.ROLE_LIST_CTL%>"><b>Role List</b></a> | <a
				href="<%=ORSView.FACULTY_CTL%>"><b>Add Faculty</b></a>| <a
				href="<%=ORSView.FACULTY_LIST_CTL%>"><b>Faculty List </b></a>| <a
				href="<%=ORSView.COURSE_CTL%>"><b>Add Course</b></a>| <a
				href="<%=ORSView.COURSE_LIST_CTL%>"><b>Course List</b></a>| <a
				href="<%=ORSView.SUBJECT_CTL%>"><b>Add Subject </b></a>| <a
				href="<%=ORSView.SUBJECT_LIST_CTL%>"><b>Subject List </b></a>|<a
				href="<%=ORSView.TIME_TABLE_CTL%>"><b>Add Time Table </b></a>| <a
				href="<%=ORSView.TIME_TABLE_LIST_CTL%>"><b>Time table List </b></a>
 

				<%
					}
				%> <%
 	if (userBean.getRoleId() == RoleBean.KIOSK) {
 %>
			<td colspan="2"><a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b>Marksheet
						List</b></a> | <a href="<%=ORSView.USER_CTL%>"><b>Add User</b></a> | <a
				href="<%=ORSView.USER_LIST_CTL%>"><b>User List</b></a> | <a
				href="<%=ORSView.COLLEGE_LIST_CTL%>"><b>College List</b></a> | <a
				href="<%=ORSView.STUDENT_CTL%>"><b>Add Student</b></a> | <a
				href="<%=ORSView.STUDENT_LIST_CTL%>"><b>Student List</b></a> | <a
				href="<%=ORSView.ROLE_LIST_CTL%>"><b>Role List</b></a>| <a
				href="<%=ORSView.COURSE_LIST_CTL%>"><b>Course List</b></a>| <a
				href="<%=ORSView.SUBJECT_LIST_CTL%>"><b>Subject List </b></a>| <a
				href="<%=ORSView.TIME_TABLE_LIST_CTL%>"><b>Time table List </b></a>


				<%
					}
				%></td>


		</tr>
		<%
			}
		%>
	</table>
	<hr>
</body>
</div>
</html>