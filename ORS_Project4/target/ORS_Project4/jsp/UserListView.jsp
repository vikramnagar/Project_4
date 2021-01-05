<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="com.sunrays.proj4.model.RoleModel"%>
<%@page import="java.util.List"%>
<%@page import="com.sunrays.proj4.controller.UserListCtl"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User List</title>



</head>


<body>
	<%@include file="Header.jsp"%>

	<center>
		<h2>User List</h2>
		
		<%
					if(ServletUtility.getSuccessMessage(request).length()>0){
				%>
			<h2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h2>
			
			<%
							}else{
						%>
			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h2>
			<%
				}
			%>
		
		
		<%
							List l = (List) request.getAttribute("roleList");
						%>

		<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
			<jsp:useBean id="bean" class="com.sunrays.proj4.bean.UserBean"
				scope="request"></jsp:useBean>
			<jsp:useBean id="model" class="com.sunrays.proj4.model.UserModel"
				scope="request"></jsp:useBean>
			<%-- <jsp:useBean id="bean2" class="com.raystech.proj4.bean.RoleBean" scope="request"></jsp:useBean> --%>
			<jsp:useBean id="model1" class="com.sunrays.proj4.model.RoleModel"
				scope="request"></jsp:useBean>

			<table width="100%">

			<%-- <%
						List list = ServletUtility.getList(request);
						if (list.size() == 0 || list == null) {
					%>
				<br>
				<input type="submit" name="operation" 
					value="<%=UserListCtl.OP_BACK %>">


				<%
					} else {
				%>
 --%>				<tr>
					<td align="center"><label>First Name :</label> <input
						type="text" name="firstName" placeholder="Enter First Name"
						size="23"
						value="<%=ServletUtility.getParameter("firstName", request)%>">&emsp;
						<label>Last Name:</label> <input type="text" name="lastName"
						size="23" placeholder="Enter Last Name"
						value="<%=ServletUtility.getParameter("lastName", request)%>">
						&emsp;<label>LoginId:</label> <input type="text" name="login"
						size="23" placeholder="Enter LoginId"
						value="<%=ServletUtility.getParameter("login", request)%>">
						&emsp;
						 Role <%=HTMLUtility.getList("roleId", String.valueOf(bean.getRoleId()), l)%>

						<input type="submit" name="operation"
						value="<%=UserListCtl.OP_SEARCH%>"> <input type="submit"
						name="operation" value="<%=UserListCtl.OP_RESET%>"> 
						</td>

				</tr>

			</table>
			
			 <%
						List list = ServletUtility.getList(request);
						if (list.size() == 0 || list == null) {
					%>
				<br>
				<%-- <input type="submit" name="operation" 
					value="<%=CourseListCtl.OP_RESET %>"> --%>
     <%-- <%} %> --%>

				<%
						} else {
					%>
			
			<br>


			<table border="1" width="100%" height="40%">
				<th align="left" width="90"><input type="checkbox"
					id="ck" onclick="selectall(this)">Select All</th>
				<th>S.No.</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>LoginId</th>
				<th>Gender</th>
				<th>DOB</th>
				<th>Role</th>
				<th>Edit</th>
				</tr>



				<%
					int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);
						int index = ((pageNo - 1) * pageSize) + 1;
						
						list = ServletUtility.getList(request);
						Iterator<UserBean> it = list.iterator();
						while (it.hasNext()) {
							bean = it.next();
				%>

				<tr>
					<%
						RoleBean roleBean = model1.findByPK(bean.getRoleId());
					%>
					<%
						if (roleBean.getId() == 1) {
					%>
					<td><input type="checkbox" name="ids" disabled="disabled"
						value="<%=bean.getId()%>"></td>
					<%
						} else {
					%>

					<td><input type="checkbox" name="ids"
						onclick="unchek(this)" value="<%=bean.getId()%>"></td>
					<%
						}
					%>

					<td><%=index++%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getGender()%></td>
					<td><%=bean.getDob()%></td>

					<td><%=roleBean.getName()%></td>
					<%
						if (roleBean.getId() == 1) {
					%><td>---</td>
					<%
						} else {
					%>
					<td><a href="UserCtl?id=<%=bean.getId()%>">Edit</a></td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value=<%=UserListCtl.OP_PREVIOUS%>></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEW%>"></td>
						<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_DELETE%>"></td>
					<%
					if((model.nextPK()-1) == bean.getId() || (list.size() < pageSize)){
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=UserListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEXT%>"></td>
					<% } %>

				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
			<%
				}
			%>

		</form>
	</center>
	<br>
	<br>
	<br>
	<br>
	
	<br>
	<%@include file="Footer.jsp"%>
</body>
</html>