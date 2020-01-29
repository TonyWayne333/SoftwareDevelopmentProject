<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
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
	height: 400px;
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
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/softwaredevelopment?serverTimezone=EST5EDT","root","password");
					Statement st=cn.createStatement();
					ResultSet rs=st.executeQuery("select * from student");
					while(rs.next())
					{
				%>

					<tr class="table-secondary">
						<th scope="col"><%out.println(rs.getString("studentId")); %></th>
						<th scope="col"><%out.println(rs.getString("firstName")); %></th>
						<th scope="col"><%out.println(rs.getString("lastName")); %></th>
						<th scope="col"><%out.println(rs.getString("presence")); %></th>
						<th scope="col"><%out.println(rs.getString("emailId")); %></th>
						<th scope="col"><%out.println(rs.getString("phone")); %></th>												
					</tr>
						
	 	<%
			}
		%>
			</tbody>
		</table>
		<br>
		<form action="classlist" method="post">
			<input type="submit" class="btn btn-primary btn-lg" value="Refresh">
		</form>
	</div>
</body>
</html>