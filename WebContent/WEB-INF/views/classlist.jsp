<%@page import="com.mongodb.MongoClient"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<%@ page import="com.development.StudentService" %>
<%@ page import="com.development.Student" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Class List</title>

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
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  		<a class="navbar-brand" href="#">${professor.getFirstName()} ${professor.getLastName()}</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
  		</button>

  		<div class="collapse navbar-collapse" id="navbarColor01">
    		<ul class="navbar-nav mr-auto">
    			<li class="nav-item active">
        			<a class="nav-link" href="#">Class List<span class="sr-only">(current)</span></a>
      			</li>
      			<li class="nav-item">
        			<a class="nav-link" href="${contextPath}/uploadphoto">Take Attendance</a>
      			</li>
      			<li class="nav-item">
        			<a class="nav-link" href="${contextPath}/accountinfo">Account Details</a>
      			</li>
      			<li class="nav-item">
        			<a class="nav-link" href="${contextPath}/logout">Log Out</a>
      			</li>
    		</ul>
  		</div>
	</nav>	
	
	<div class="jumbotron">
		<h1>Class List</h1>
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
					List<Student> students = studentService.getAll();
					
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
		<br>
		<form action="classlist" method="post">
			<input type="submit" class="btn btn-primary btn-lg" value="Refresh" style="width: 120px;">
		</form>
	</div>
</body>
</html>