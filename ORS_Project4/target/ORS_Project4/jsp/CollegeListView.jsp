<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.controller.CollegeCtl"%>
<%@page import="com.sunrays.proj4.controller.CollegeListCtl"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@page import="com.sunrays.proj4.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="bean" class="com.sunrays.proj4.bean.CollegeBean"
	scope="request"></jsp:useBean>
<jsp:useBean id="model" class="com.sunrays.proj4.model.CollegeModel"
	scope="request"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>College List</title>
</head>
<%@include file="Header.jsp"%>
<body>
	
	<center>
		<h1>College List</h1>
                   
                   <%if(ServletUtility.getSuccessMessage(request).length()>0){ %>
			<h2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h2>
			
			<%}else{ %>
			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h2>
			<%} %>
                   
		<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="POST">

			<table width="100%">
				<%-- <%
						List list = ServletUtility.getList(request);
						if (list.size() == 0 || list == null) {
					%>
				<br>
				<input type="submit" name="operation"
					value="<%=CollegeListCtl.OP_BACK %>">

				<%
						} else {
					%>

 --%>				<tr>
					<td align="center"><label> Name :</label> <input type="text"
						name="name" placeholder="Enter College Name"
						value="<%=ServletUtility.getParameter("name", request)%>">&emsp;&emsp;
						&emsp;&emsp;<label>City :</label> <input type="text" name="city"
						placeholder="Enter City"
						value="<%=ServletUtility.getParameter("city", request)%>">
						<input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=CollegeListCtl.OP_RESET%>"></td>
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
			<table border="1" width="100%">
				<tr>
						<table border="1" width="100%" height="40%">
				<th align="left" width="90"><input type="checkbox"
					id="ck" onclick="selectall(this)">Select All</th>
					<th>S.No.</th>
					<th>Name</th>
					<th>Address</th>
					<th>State</th>
					<th>City</th>
					<th>Mobile No</th>
					<th>Edit</th>
				</tr>

				<%
					int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);
						int index = ((pageNo - 1) * pageSize) + 1;

						list = ServletUtility.getList(request);
						Iterator<CollegeBean> it = list.iterator();

						while (it.hasNext()) {

							bean = it.next();
				%>
				<tr>
					<td><input type="checkbox" name="ids"
						onclick="unchek(this)" value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>

					<td><%=bean.getName()%></td>
					<td><%=bean.getAddress()%></td>
					<td><%=bean.getState()%></td>
					<td><%=bean.getCity()%></td>
					<td><%=bean.getPhoneNo()%></td>
					<td><a href="CollegeCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						value="<%=CollegeListCtl.OP_PREVIOUS%>"></td>

					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_DELETE%>"></td>

					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_NEW%>"></td>
					<%
					if((model.nextPK()-1) == bean.getId() || (list.size() < pageSize)){
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=CollegeListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%><td align="right"><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_NEXT%>"></td>
					<%
						}
					%>
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
