<%@page import="com.sunrays.proj4.controller.LoginCtl"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<html>
<head>
<title>Login</title>
</head>
<body>
	<form action="<%=ORSView.LOGIN_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<centre> 
		<input type="hidden" name="uri" value="<%=request.getAttribute("uri")%>"> 
		<input type="hidden" name="id" value="<%=bean.getId()%>"> 
	    <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
		<input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
		<input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
		<input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

		<%-- <br><br>
			
			<%
				String s = (String) request.getAttribute("msg");
				if (s != null) {
			%>
			<h4 align="center">
            <font color ="red"><%=s%></font>
            </h4>
			<%
				}
			%> --%> <br>

		<h1 align="center">Login</h1>

		<!---------  Successful Logout  -------->
		<h2 align="center">
			<font color="green"> <%=ServletUtility.getSuccessMessage(request)%> </font>
		</h2>
		<!---------  Invalid LoginId and Password  -------->
		<H2 align="center">
			<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
			<%-- <font color="red"> <%=request.getAttribute("Error")%></font> --%>
			

			<%
				//  Session has been Expired. It is Set on FrontController.
				String message = (String) request.getAttribute("message");
				if (message != null) {
			%>

			<font color="red"><%=message%></font>
			<%
				}
			%>

		</H2>

		</centre>
		<table style="margin-left: 38%">

			<tr width="100" height="50">
				<th align="left">LoginId<font color="red">*</font></th>
				<td><input type="text" name="login" size=30 placeholder="Enter LoginId"
					value="<%=DataUtility.getStringData(bean.getLogin())%>"> 
					<!------- Populate Data at View  ------->
					<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				<!------- Show Error Message at View  ------->
			</tr>
			<tr>
				<th align="left">Password<font color="red">*</font></th>
				<td><input type="password" name="password" size=30 placeholder="Enter Password"
					value="<%=DataUtility.getStringData(bean.getPassword())%>">
					<!------- Populate Data at View  -------> <font color="red">
						<%=ServletUtility.getErrorMessage("password", request)%></font></td>
				    <!------- Show Error Message at View  ------->
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th></th>
				<td colspan="2"><input type="submit" name="operation" size="15"
					value="<%=LoginCtl.OP_SIGN_IN%>"> &nbsp; &nbsp;&nbsp;<input
					type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>">
					&nbsp;</td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr> <!-- style="text-decoration: none" -->
			<tr></tr>
			<tr>
				<th></th>
				<td colspan="2"><a 
					href="<%=ORSView.FORGET_PASSWORD_CTL%>" ><b><h3>Forget my password ?</b>
						</h3></a>&nbsp;</td>
			</tr>
		</table>
	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>