<%@page import="com.sunrays.proj4.bean.RoleBean"%>
<%@page import="com.sunrays.proj4.bean.UserBean"%>
<%@page import="com.sunrays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>


	<form action="<%=ORSView.WELCOME_CTL%>">

		<%@ include file="Header.jsp"%><br>
		<br>
		<center>
			<font size="15px" color="red"><h2>Welcome To ORS</h2></font>
		</center>

		<%       // session.setAttribute(); [LoginCtl]
		 	UserBean beanUserBean = (UserBean) session.getAttribute("user");
			if (beanUserBean != null) 
			{
				if (beanUserBean.getRoleId() == RoleBean.STUDENT) 
				{
		%>

		<h2 align="Center">
			<a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your
				Marksheet </a>
		</h2>

		<%
			    }
			}
		%>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>