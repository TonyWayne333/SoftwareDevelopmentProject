<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Personal Info</title>

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
  max-width: 500px;
  margin: 10px auto;
  padding: 10px 20px;
  border-radius: 8px;
}
h1{
  color:  #48B4E6;
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
        <a class="nav-link" href="#">Account Details <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://localhost:9090/SoftwareDevelopmentProject/uploadphoto">Upload Photo</a>
      </li>

      <li class="nav-item">
        <a class="nav-link" href="http://localhost:9090/SoftwareDevelopmentProject/logout">Log Out</a>
      </li>
    </ul>
  </div>
</nav>	
	
	
	
	<form action="Update" method="post">  
	<h1 align="center">Account Details</h1>
	<fieldset>
		<div class="form-group">
      		<label for="firstName">Email Id</label>
      		<input type="email" class="form-control" name="emailId" value="${professor.getEmailId()}" required>
    	</div>
	    <div class="form-group">
      		<label for="firstName">First Name</label>
      		<input type="text" class="form-control" name="firstName" value="${professor.getFirstName() }" required>
    	</div>
    	<div class="form-group">
      		<label for="lastName">Last Name</label>
      		<input type="text" class="form-control" value="${professor.getLastName() }" name="lastName" required>
    	</div>
    	<div class="form-group">
      		<label for="password">Password</label>
      		<input type="password" class="form-control" name="password" value="${professor.getPassword() }" required>
    	</div>
    	<div class="form-group">
      		<label for="address">Phone</label>
			<input type="tel" name="phone" value="${professor.getPhone() }" required/>
    	</div>
    </fieldset>

    	<input type="submit" class="btn btn-primary" value="Save Changes" />
    	<input type="reset" class="btn btn-primary" value="Clear"/>
	</form>
</body>
</html>