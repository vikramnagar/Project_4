<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.controller.MarksheetListCtl"%>
<%@page import="com.sunrays.proj4.controller.BaseCtl"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@page import="com.sunrays.proj4.bean.MarksheetBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Marksheet List</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<jsp:useBean id="bean" class="com.sunrays.proj4.bean.MarksheetBean"
		scope="request"></jsp:useBean>
	<jsp:useBean id="model" class="com.sunrays.proj4.model.UserModel"
		scope="request"></jsp:useBean>
	<jsp:useBean id="model1" class="com.sunrays.proj4.model.RoleModel"
		scope="request"></jsp:useBean>
	<center>

		<h2>Marksheet List</h2>
          
           <%if(ServletUtility.getSuccessMessage(request).length()>0){ %>
			<h2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h2>
			
			<%}else{ %>
			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h2>
			<%} %>
           
		<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="POST">

			<table width="100%">
			 	<%-- <%
						List list = ServletUtility.getList(request);
						if (list.size() == 0 || list == null) {
					%>
				<br>
				<input type="submit" name="operation" 
					value="<%=MarksheetListCtl.OP_BACK %>">

				<%
						} else {
					%>
 --%>				<tr>
					<td align="center"><label>&emsp;&emsp;&emsp;&emsp;&emsp;
							&emsp;&emsp;&emsp;&emsp;&emsp; Name :</label> <input type="text"
						name="name" placeholder="Enter Name"
						value="<%=ServletUtility.getParameter("name", request)%>">
						&emsp;&emsp;&emsp;&emsp;&emsp; <label>Roll No :</label> <input
						type="text" name="rollNo" placeholder="Enter Roll No"
						value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
						<input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_SEARCH %>"> <input
						type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_RESET %>"></td>

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
					<th>RollNo</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Total</th>
					<th>Percentage</th>
					<th>Status</th>
					<th>Edit</th>

				</tr>

				<%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    list = ServletUtility.getList(request);
                    Iterator<MarksheetBean> it = list.iterator();

                    while (it.hasNext()) {

                         bean = it.next();
                         int phy=bean.getPhysics();
                         int chem=bean.getChemistry();
                         int maths=bean.getMaths();
                         int total=phy+chem+maths;
                         float percentage=(float)total/3;
                         percentage=percentage*100;
                         percentage=Math.round(percentage);
                         percentage=percentage/100;
                         
                         
                         String status;
                         if((chem>=33) && (phy>=33) &&(maths>=33)){
                        	 status="PASS";
                         }else
                         {
                        	 status="FAIL";
                         }
                
                         
                %>
				<tr>
					<td><input type="checkbox" name="ids"
						onclick="unchek(this)" value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getRollNO()%></td>
					<td><%=bean.getName()%></td>
					<td><%=phy%></td>
					<td><%=chem%></td>
					<td><%=maths%></td>
					<td><%=total %>
					<td><%=percentage %>%</td>
					<td>
						<%if(status=="PASS"){%><font color="green"> <%=status%></font> <%}else{ %><font
						color="red"> <%=status%></font>
						<%} %>
					</td>

					<td><a href="MarksheetCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>

				<%
                    }
                %>
			</table>
			<table width="100%">
				<%
						if (pageNo == 1) {
					%>
				<td><input type="submit" name="operation" disabled="disabled"
					value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>

				<%
						} else {
					%>
				<td><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
				<%
						}
					%>
				<td><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_DELETE%>"></td>

				<td><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_NEW%>"></td>
				<%
					if((model.nextPK()-1) == bean.getId() || (list.size() < pageSize)){
					%>
				<td align="right"><input type="submit" name="operation"
					disabled="disabled" value="<%=MarksheetListCtl.OP_NEXT%>"></td>
				<%
						} else {
					%><td align="right"><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_NEXT%>"></td>
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