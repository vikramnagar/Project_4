<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.controller.StudentListCtl"%>
<%@page import="com.sunrays.proj4.controller.SubjectListCtl"%>
<%@page import="com.sunrays.proj4.controller.BaseCtl"%>
<%@page import="com.sunrays.proj4.bean.SubjectBean"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Subject List</title>
</head>
<body>

	<%@include file="Header.jsp"%>
	<form action="<%=ORSView.SUBJECT_LIST_CTL%>" method="post">
		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.SubjectBean"
			scope="request"></jsp:useBean>
		<jsp:useBean id="model" class="com.sunrays.proj4.model.SubjectModel"
			scope="request"></jsp:useBean>
		<%
			List l = (List) request.getAttribute("subjectlist");%>
		<center>
			<h2>Subject List</h2>

            <%if(ServletUtility.getSuccessMessage(request).length()>0){ %>
			<h2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h2>
			
			<%}else{ %>
			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h2>
			<%} %>
            

			<table width="100%">
				<%-- <%
						List list = ServletUtility.getList(request);
						if (list.size() == 0 || list == null) {
					%>
				<br>
				<input type="submit" name="operation"
					value="<%=SubjectListCtl.OP_BACK %>">

				<%
						} else {
					%>
 --%>				<tr>
					<td align="center"><label> Subject Name :</label> <input
						type="text" name="subjectName" placeholder="Enter Subject Name"
						value="<%=ServletUtility.getParameter("subjectName", request)%>">
						&nbsp; <input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_SEARCH %>"> <input
						type="submit" name="operation"
						value="<%=SubjectListCtl.OP_RESET%>"></td>
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
					<th>Description</th>

					<th>CourseName</th>
					<th>Edit</th>
				</tr>


				<%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                     list = ServletUtility.getList(request);
                    Iterator<SubjectBean> it = list.iterator();
                    while (it.hasNext()) {
                    	 bean = it.next();
                %>
				<tr>
					<td><input type="checkbox" name="ids"
						onclick="unchek(this)" value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>

					<td><%=bean.getName()%></td>
					<td><%=bean.getDescription()%></td>

					<td><%=bean.getCourseName() %></td>
					<td><a href="SubjectCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
                    }
                %>
			</table>
			<table width="100%">
				<tr>
					<%if( pageNo==1) {%>
					<td><input type="submit" name="operation" disabled="disabled"
						value=<%=SubjectListCtl.OP_PREVIOUS%>></td>
					<%}
						else{%>
					<td><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_PREVIOUS%>"></td>
					<%} %>
					<td><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_NEW %>"></td>
					<td><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_DELETE%>"></td>
					<td>
						<%
					if((model.nextPK()-1) == bean.getId() || (list.size() < pageSize)){
					%>
					
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=SubjectListCtl.OP_NEXT%>"></td>

					<%}else{
							%>
					<td align="right"><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_NEXT%>"></td>
					<%} %>




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