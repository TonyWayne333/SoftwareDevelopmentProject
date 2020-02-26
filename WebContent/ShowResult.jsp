<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.development.StudentService" %>
<%@ page import="com.development.Student" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result for Attendance</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<style>
fieldset {
  margin-bottom: 30px;
  border: none;
}

legend {
  font-size: 1.4em;
  margin-bottom: 10px;
}
h1{
  color:  #48B4E6;
}

.jumbotron{
	text-align: center;
	max-width: 750px;
	margin: 20px auto;
	padding: 20px;
}
</style>

</head>
<body>
	<div class="jumbotron">
		<h1>Present Student List</h1>
		<table class="table table-hover">
			<thead>
				<tr class="table-active">
					<th scope="col">Student ID</th>
      				<th scope="col">First Name</th>
      				<th scope="col">Last Name</th>
      				<th scope="col">Presence</th>
      				<th scope="col">Email ID</th>
      				<th scope="col">Phone Number</th>
				</tr>
			</thead>
			<tbody>
				<%  
					StudentService studentService = new StudentService();
					List<Student> students = studentService.getAllPresent();
					
					for(Student student : students)
					{
				%>

					<tr class="table-secondary">
						<th scope="col"><%out.println(student.getStudentId()); %></th>
						<th scope="col"><%out.println(student.getFirstName()); %></th>
						<th scope="col"><%out.println(student.getLastName()); %></th>
						<th scope="col"><%out.println(student.getPresence()); %></th>
						<th scope="col"><%out.println(student.getEmailId()); %></th>
						<th scope="col"><%out.println(student.getPhone()); %></th>												
					</tr>
							
		 	<%
				}
			%>
		</tbody>
	</table>

	</div>
</body>
</html>