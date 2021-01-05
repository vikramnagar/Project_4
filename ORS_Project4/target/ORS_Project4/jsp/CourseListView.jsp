<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.controller.CourseListCtl"%>
<%@page import="com.sunrays.proj4.controller.BaseCtl"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@page import="com.sunrays.proj4.bean.CourseBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="bean" class="com.sunrays.proj4.bean.CourseBean"
	scope="request"></jsp:useBean>
<jsp:useBean id="model" class="com.sunrays.proj4.model.CourseModel"
	scope="request"></jsp:useBean>
<jsp:useBean id="model1" class="com.sunrays.proj4.model.CourseModel"
	scope="request"></jsp:useBean>
<html>
	<%@include file="Header.jsp"%>
<body>


	<center>
		<h2>Course List</h2>
              <% List l1 = ServletUtility.getList(request);%>
              <%if(ServletUtility.getSuccessMessage(request).length()>0){ %>
			<h2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h2>
			
			<%}else{ %>
			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h2>
			<%} %>
              
		<form action="<%=ORSView.COURSE_LIST_CTL%>" method="POST">

			<table width="100%">
				<%-- <%
						List list = ServletUtility.getList(request);
						if (list.size() == 0 || list == null) {
					%>
				<br>
				<input type="submit" name="operation" 
					value="<%=CourseListCtl.OP_RESET %>">
     <%} %>

				<%
						} else {
					%>
 --%>				<tr>
					<td align="center"><label>Course Name :</label> <input
						type="text" name="courseName" placeholder="Enter Course Name"
						value="<%=ServletUtility.getParameter("courseName", request)%>">&emsp;&emsp;&emsp;
						&emsp; <label>Duration :</label> <input type="text"
						name="duration" placeholder=" Enter Duration"
						value="<%=ServletUtility.getParameter("duration", request)%>">&emsp;
						<input type="submit" name="operation"
						value="<%=CourseListCtl.OP_SEARCH %>"> <input
						type="submit" name="operation" value="<%=CourseListCtl.OP_RESET%>"></td>
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

					<th>Course Name</th>
					<th>Duartion</th>
					<th>Description</th>

					<th>Edit</th>
				</tr>

				<%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                     list = ServletUtility.getList(request);
                    Iterator<CourseBean> it = list.iterator();

                    while (it.hasNext()) {

                         bean = it.next();
                %>
				<tr>
					<td><input type="checkbox" name="ids"
						onclick="unchek(this)" value="<%=bean.getId()%>"></td>					
						<td><%=index++%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getDuration()%></td>
					<td><%=bean.getDescription()%></td>

					<td><a href="CourseCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						value="<%=CourseListCtl.OP_PREVIOUS%>"></td>

					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_DELETE%>"></td>

					<td><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_NEW%>"></td>
					<%
					if((model.nextPK()-1) == bean.getId() || (list.size() < pageSize)){
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=CourseListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%><td align="right"><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_NEXT%>"></td>
					<%
						}
					%>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
			<%} %>
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