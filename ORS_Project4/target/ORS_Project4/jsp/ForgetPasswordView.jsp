<%@page import="com.sunrays.proj4.controller.ForgetPasswordCtl"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Foreget Password</title>
</head>
<body>
	<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<center>
			<br>
			<br>
			<h1>Forgot your password?</h1>
			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<h3>
					<lable>Submit your LoginId and we'll send you password.</lable>
				</h3>
				<br>
				<H2>
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</H2>
				<H2>
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</H2>

				<font color="red"><%=ServletUtility.getErrorMessage("login", request)%>&emsp;&emsp;&emsp;&emsp;</font>
				<br>
				<br>
				<label>Login Id :</label>&emsp;
				<input type="text" name="login" placeholder="Enter LoginId"
					value="<%=ServletUtility.getParameter("login", request)%>">&emsp;
				<input type="submit" name="operation"
					value="<%=ForgetPasswordCtl.OP_GO%>">&nbsp;
				<input type="submit" name="operation"
					value="<%=ForgetPasswordCtl.OP_RESET%>">
			</table>
	</form>
	</center>

	<%@ include file="Footer.jsp"%>
</body>
</html>
</body>
</html>