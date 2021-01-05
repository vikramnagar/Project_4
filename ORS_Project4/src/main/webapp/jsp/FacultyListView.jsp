<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="javax.swing.text.html.HTML"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.sunrays.proj4.controller.FacultyListCtl"%>
<%@page import="com.sunrays.proj4.bean.FacultyBean"%>

<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<html>
<head>
<title>Faculty List</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<center>
		<h2>Faculty List</h2>
                 
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
                 
	 	<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">
			<jsp:useBean id="bean" class="com.sunrays.proj4.bean.FacultyBean"
				scope="request"></jsp:useBean>
			<jsp:useBean id="model" class="com.sunrays.proj4.model.FacultyModel"
				scope="request"></jsp:useBean>

			<% List clg = (List)request.getAttribute("clist"); %>

			<table width="100%">
				<%-- <%
					List l1 = ServletUtility.getList(request);
					if (l1.size() == 0 || l1 == null) {
				%>
				<br>
				<input type="submit" name="operation"
					value="<%=FacultyListCtl.OP_BACK%>">


				<%
					} else {
				%>
 --%>				<tr>
					<td align="center"><label> First Name :</label> <input
						type="text" name="firstName" placeholder="Enter First Name"
						value="<%=ServletUtility.getParameter("firstName", request)%>">&emsp;&emsp;&emsp;&emsp;&emsp;
						<label>Last Name :</label> <input type="text" name="lastName"
						placeholder="Enter Last Name"
						value="<%=ServletUtility.getParameter("lastName", request)%>">&emsp;&emsp;&emsp;&emsp;&emsp;<label>Login
							Id :</label> <input type="text" name="email" placeholder="Enter LoginId"
						value="<%=ServletUtility.getParameter("email", request)%>">
						<input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=FacultyListCtl.OP_RESET%>"></td>
				</tr>
				<tr>
					<%-- 	 <td colspan="8" align="center"> <font color="red" ><h2><%=ServletUtility.getErrorMessage(request)%></h2></font>
			<font color="green"><h2><%=ServletUtility.getSuccessMessage(request)%></h2></font>  --%>
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
			<table border="1" width="100%">
				<tr>
						<table border="1" width="100%" height="40%">
				<th align="left" width="90"><input type="checkbox"
					id="ck" onclick="selectall(this)">Select All</th>
					<th>S.No.</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Qualification</th>
					<th>Gender</th>
					<th>Date Of Birth</th>
					<th>Email ID</th>
					<th>Mobile No</th>
					<th>College</th>
					<th>Course</th>
					<th>Edit</th>
				</tr>
				<%
					int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);
						int index = ((pageNo - 1) * pageSize) + 1;

						 list = ServletUtility.getList(request);
						Iterator<FacultyBean> it = list.iterator();

						while (it.hasNext()) {

							bean = it.next();
				%>
				<tr>
					<td><input type="checkbox" name="ids"
						onclick="unchek(this)" value="<%=bean.getId()%>"></td>					
						<td><%=index++%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getQualification()%></td>
					<td><%=bean.getGender()%></td>
					<td><%=bean.getDOB()%></td>
					<td><%=bean.getEmailId()%></td>
					<td><%=bean.getMobileNo()%></td>
					<td><%=bean.getCollegeName()%></td>
					<td><%=bean.getCourseName()%></td>
					<td><a href="FacultyCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						value="<%=FacultyListCtl.OP_PREVIOUS%>"></td>

					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_DELETE%>"></td>

					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEW%>"></td>
					<%
					if((model.nextPK()-1) == bean.getId() || (list.size() < pageSize)){
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=FacultyListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%><td align="right"><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEXT%>"></td>
					<%
						}
					%>
				</tr>
			</table>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"><input
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