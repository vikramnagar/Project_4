<%@page import="com.sunrays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>
	<h1 align="Right">
		<img src="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg" width="400"
			height="90">
	</h1>
	<br>
	<br>
	<center>
		<img src="<%=ORSView.APP_CONTEXT %>/img/Error.jpg" width="200"
			height="100"><br>
		<h2>
			<font color="red">Oops!!! Connecion lost please try after Some
				Time</font>
		</h2>
		<br> <input type="button" onclick="history.back()" value="Back"
			name="Back">
	</center>
</body>
</html>