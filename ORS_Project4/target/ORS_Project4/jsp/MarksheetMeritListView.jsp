<%@page import="com.sunrays.proj4.controller.MarksheetMeritListCtl"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@page import="com.sunrays.proj4.bean.MarksheetBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page errorPage="ErrorView.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Marksheet Merit List</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<center>
	
		<h2>Marksheet Merit List</h2>
             <H2><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></H2>
		<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="POST">
			<br>
			<tr>
	</td>
			</tr>
			<%
						List lis = ServletUtility.getList(request);
						if (lis.size() == 0 || lis == null) {
					%>
				<input type="button" name="submit" onclick="history.back()"
					value="<%=MarksheetMeritListCtl.OP_BACK %>">


				<%
					} else {
				%>
			
			<table border="1" width="100%">
				<tr>

					<th align="center" width="60">S.No.</th>
					<th>Roll No</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Total</th>
					<th>Percentage</th>

				</tr>

				<%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<MarksheetBean> it = list.iterator();

                    while (it.hasNext()) {

                        MarksheetBean bean = it.next();
                        int phy=bean.getPhysics();
                        int chem=bean.getChemistry();
                        int maths=bean.getMaths();
                        int total=phy+chem+maths;
                        float percentage=(float)total/3;
                         percentage=percentage*100;
                        percentage=Math.round(percentage);
                        percentage=percentage/100; 
                 %>
				<%if ((percentage>60) && (phy>60) && (chem>60) && (maths>60)){ %>
				<tr>

					<td><%=index++%></td>
					<td><%=bean.getRollNO()%></td>
					<td><%=bean.getName()%></td>
					<td><%=phy%></td>
					<td><%=chem%></td>
					<td><%=maths%></td>
					<td><%=total%></td>
					<td><%=percentage%>%</td>

				</tr>
				<%} %>
				<%
                    }
                %>
			</table>
			
			<table>
				<tr>
					<td align="right"><input type="button" name="submit"
						onclick="history.back()"
						value="<%=MarksheetMeritListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>

   <%} %>

	</center>
	<br>
	<br>
	<br>
	<br>
	<%@include file="Footer.jsp"%>
</body>
</html>
