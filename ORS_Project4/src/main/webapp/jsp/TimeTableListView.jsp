<%@page import="com.sunrays.proj4.bean.TimeTableBean"%>
<%@page import="com.sunrays.proj4.bean.TimeTableBean"%>
<%@page import="java.util.List"%>
<%@page import="com.sunrays.proj4.controller.TimeTableListCtl"%>
<%@page import="com.sunrays.proj4.util.ServletUtility"%>
<%@page import="com.sunrays.proj4.util.HTMLUtility"%>
<%@page import="com.sunrays.proj4.util.DataUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/ORS_Project4/css/jquery-ui.css">

<script src="/ORS_Project4/js/jquery-1.12.4.js"></script>
<script src="/ORS_Project4/js/jquery-ui.js"></script>
 <script type="text/javascript">
/* function noSunday(date){
	return [date.getDay() !=0, false];


};

$(function(){
	var endyear = new Date();
	var startyear = new Date().getFullYear()-2;

	$("#datepicker1").datepicker({
		dateFormat :'dd/mm/yy',
		minDate : new Date(),
		maxDate : new Date().getFullYear()-2,
	    changeMonth : true,
		changeYear : true,
		beforeShowDay : noSunday,
		yearRange : "2018:2050"

	});
}); */
</script> 


</head>

<body>

    <%@include file="Header.jsp"%>
	<jsp:useBean id="bean" class="com.sunrays.proj4.bean.TimeTableBean"
		scope="request"></jsp:useBean>
	<jsp:useBean id="model" class="com.sunrays.proj4.model.UserModel"
		scope="request"></jsp:useBean>
		<jsp:useBean id="timemodel" class="com.sunrays.proj4.model.TimeTableModel"
		scope="request"></jsp:useBean>
	<jsp:useBean id="model1" class="com.sunrays.proj4.model.RoleModel"
		scope="request"></jsp:useBean>

	<%
		List subjectList = (List) request.getAttribute("subjectList");
		List courseList = (List) request.getAttribute("courseList");
	%>

	<center>

		<h1>Time Table List</h1>
            
            <%if(ServletUtility.getSuccessMessage(request).length()>0){ %>
			<h2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h2>
			
			<%}else{ %>
			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h2>
			<%} %>
            
      
		<form action="<%=ORSView.TIME_TABLE_LIST_CTL%>" method="post">
			<%	List l1 = ServletUtility.getList(request); %>
			<%-- <% if(l1==null || l1.size()==0){ %>

			<br>
			<td align="right"><input type="submit" name="operation"
				 value="<%=TimeTableListCtl.OP_BACK%>">


				<% } else { %>

 --%>
				<table width="100%" align="center">

					<tr>
						<td align="center">Course Name <%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), courseList)%>
							&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Subject Name <%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), subjectList)%>
                     
                      <th align="left">Date Of Exam</th>
				<td><input type="text" name="examDate" id="datepicker"
					readonly="readonly" size="25" placeholder="Enter Date Of Exam"
					value="<%=DataUtility.getDateString(bean.getExamDate())%>">  
                      
                      
							<input type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_SEARCH%>"> <input
							type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_RESET%>">
						</td>
					</tr>
				</table> <br>


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
                       
				<table border="1" width="100%">
						<table border="1" width="100%" height="40%">
				<th align="left" width="90"><input type="checkbox"
					id="ck" onclick="selectall(this)">Select All</th>
					<th>S.No.</th>
					<th>Course Name</th>
					<th>Subject Name</th>
					<th>Semester</th>
					<th>Exam Time &emsp;&emsp;</th> 
					<th>Exam Date</th>
					<th>Edit</th>

					<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					 list = ServletUtility.getList(request);
					Iterator<TimeTableBean> it = list.iterator();
					while (it.hasNext()) {
						bean = it.next();
				%>
					<tr>
						<td><input type="checkbox" name="ids"
						onclick="unchek(this)" value="<%=bean.getId()%>"></td>
						<td><%=index++%></td>
						<td><%=bean.getCourseName()%></td>
						<td><%=bean.getSubject()%></td>
						<td><%=bean.getSemester()%></td>
					 <td><%=bean.getExamTime()%></td>
						<td><%=bean.getExamDate()%></td>

						<td><a href="TimeTableCtl?id=<%=bean.getId()%>">Edit</a></td>
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
							value="<%=TimeTableListCtl.OP_PREVIOUS%>"></td>

						<%
						} else {
					%>
						<td><input type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_PREVIOUS%>"></td>
						<%
						}
					%>
						<td><input type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_NEW%>"></td>

						<td><input type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_DELETE%>"></td>


						<%
					if((timemodel.nextPK()-1) == bean.getId() || (list.size() < pageSize)){
					%>
						<td align="right"><input type="submit" name="operation"
							disabled="disabled" value="<%=TimeTableListCtl.OP_NEXT%>"></td>
						<%
						} else {
					%><td align="right"><input type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_NEXT%>"></td>
						<%
						}
					%>
					
					</tr>
				</table> <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>"> <% } %>
			
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