<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
form {
  max-width: 400px;
  margin: 10px auto;
  padding: 10px 20px;
  border-radius: 8px;
}
h1{
  color:  #48B4E6;
}

.jumbotron{
	text-align: center;
	max-width: 500px;
	height: 400px;
	margin: 20px auto;
	padding: 20px;
}
</style>
<title>Upload Photo to Take Attendance</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  		<a class="navbar-brand" href="#">${professor.getFirstName()} ${professor.getLastName()}</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
  		</button>
		<div class="collapse navbar-collapse" id="navbarColor01">
    		<ul class="navbar-nav mr-auto">
    			<li class="nav-item">
        			<a class="nav-link" href="${contextPath}/classlist">Class List</a>
      			</li>
    		    <li class="nav-item active">
        			<a class="nav-link" href="#">Take Attendance<span class="sr-only">(current)</span></a>
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
		<form action="photoupload" method="post" enctype="multipart/form-data">  
		<h1 align="center">Take Attendance</h1>
		<fieldset>
	    	<div class="form-group">
	      		<label for="photo">Upload Class Photo</label>
				<input type="file" name="file" required/>
	    	</div>
	    </fieldset>
	    	<input type="submit" class="btn btn-primary" value="Upload" />
	    </form>
    </div>
</body>
</html>