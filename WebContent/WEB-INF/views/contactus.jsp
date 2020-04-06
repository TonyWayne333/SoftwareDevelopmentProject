<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  		<a class="navbar-brand" href="${contextPath}/">Online Attendance System</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
  		</button>

  		<div class="collapse navbar-collapse" id="navbarColor01">
    		<ul class="navbar-nav mr-auto">
    		    <li class="nav-item">
        			<a class="nav-link" href="${contextPath}/aboutus">About Us</a>
      			</li>
    		    <li class="nav-item active">
        			<a class="nav-link" href="#">Contact Us<span class="sr-only">(current)</span></a>
      			</li>

    		</ul>
  		</div>
	</nav>
	<div class="jumbotron" align="center">
	  <h1 class="display">Contact Us</h1>
	  <p class="lead">If you have any query regarding this system, send an email to:
	  neel1040@gmail.com </p>
	
	</div>
</body>
</html>