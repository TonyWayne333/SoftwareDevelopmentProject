<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Attendance System</title>
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
	margin: 45px;
}

</style>

</head>
<body>
<div class="jumbotron" align="center">
  <h1 class="display">Welcome to Online Attendance System</h1>
  <p class="lead">Please select your role:</p>
	
	<form action="student">
		<input type="submit" class="btn btn-primary btn-lg" value="Student">
	</form>
	<br/><br/>
	<form action="professor">
		<input type="submit" class="btn btn-primary btn-lg" value="Professor">
	</form>
</div>
</body>
</html>