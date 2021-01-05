<%@page import="com.sunrays.proj4.controller.MarksheetCtl"%>
<%@page import="java.util.List"%>
<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Marksheet</title>
</head>
<body>

	<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="com.sunrays.proj4.bean.MarksheetBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("studentList");
		%>


		<%
			if (bean.getId() > 0) {
		%><h1 align="center">
			<br> Update Marksheet
		</h1>
		<%
			} else {
		%>
		<h1 align="center">
			<br> Add Marksheet
		</h1>
		<%
			}
		%>
		<center>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>


			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
		</center>

		<table style="margin-left: 36%">
			<tr>
				<th align="left">Roll No<font color="red">*</font></th>
				<td><input type="text" name="rollNo"
					placeholder="Enter Roll No" 
					value="<%=DataUtility.getStringData(bean.getRollNO())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>> <font
					color="red"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th align="left">Student Name<font color="red">*</font></th>
				<td><%=HTMLUtility.getList("studentId", String.valueOf(bean.getStudentId()), l).replaceAll("207", "173")%>
					<font color="red"> <%=ServletUtility.getErrorMessage("studentId", request)%></font></td>



			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			
			<tr>
				<th align="left">Physics<font color="red">*</font></th>
				<td><input type="number" name="physics"
					placeholder="Enter Physics Marks" size="25"
					
					value="<%=DataUtility.getStringData(bean.getPhysics())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
			</tr>
			
			
			<%-- <tr>
				<th align="left">Physics<font color="red">*</font></th>
				<td><input type="text" name="physics"
					placeholder="Enter Physics Marks" size="25"
					
					value="<%=DataUtility.getStringData(bean.getPhysics()).equals("0") ? "" :
					DataUtility.getStringData(bean.getPhysics())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
			</tr> --%>
			<%-- <tr>
				<th align="left">Physics<font color="red">*</font></th>
				<td><input type="text" name="physics"
					placeholder="Enter Physics Marks" size="25"
					
					value="<%=request.getAttribute("pk")!=null ? "" :
					DataUtility.getStringData(request.getParameter("physics"))%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
			</tr> --%>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<%-- <tr>
				<th align="left">Chemistry<font color="red">*</font></th>
				<td><input type="text" name="chemistry" size="25"
					placeholder="Enter Chemistry Marks"
					value="<%=request.getAttribute("pk")!=null ? "" :
					DataUtility.getStringData(request.getParameter("chemistry"))%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
			</tr> --%>
			 <tr>
				<th align="left">Chemistry<font color="red">*</font></th>
				<td><input type="number" name="chemistry" size="25"
					placeholder="Enter Chemistry Marks"
					value="<%=DataUtility.getStringData(bean.getChemistry())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
			</tr>
			<tr></tr>
			<tr></tr>
 			<tr></tr>
			<%-- <tr>
				<th align="left">Maths<font color="red">*</font></th>
				<td><input type="text" name="maths" size="25"
					placeholder="Enter Maths Marks"
					value="<%=request.getAttribute("pk")!=null ? "" :
					DataUtility.getStringData(request.getParameter("maths"))%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("maths", request)%></font></td>

			</tr> --%>
			<tr>
				<th align="left">Maths<font color="red">*</font></th>
				<td><input type="number" name="maths" size="25"
					placeholder="Enter Maths Marks"
					value="<%=DataUtility.getStringData(bean.getMaths())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("maths", request)%></font></td>

			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th></th>
				<%
					if (bean.getId() > 0) {
				%><td colspan="2"><input type="submit" name="operation"
					value="<%=MarksheetCtl.OP_UPDATE%>"> <%
 	} else {
 %>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=MarksheetCtl.OP_SAVE%>"> <%
 	}
 %> &emsp; <%
 	if (bean.getId() > 0) {
 %> <input type="submit" name="operation"
					value="<%=MarksheetCtl.OP_CANCEL%>"></td>
				<%
					} else {
				%><input type="submit" name="operation"
					value="<%=MarksheetCtl.OP_RESET%>">
				</td>
				<%
					}
				%>

			</tr>
		</table>
	</form>
	<br>
	<%@ include file="Footer.jsp"%>
</body>
</html>
