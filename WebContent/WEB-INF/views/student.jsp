<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  padding: 10px 20px;
  border-radius: 8px;
}
h1{
  color:  #48B4E6;
}
.jumbotron{
	max-width: 420px;
	height: 690px;
	margin: 20px auto;
	padding: 20px;
}
</style>
<title>Registration Page</title>

</head>
<body>
	<div class="jumbotron">
		<form action="register" method="post" enctype="multipart/form-data">  
		<h1 align="center">Registration</h1>
		<fieldset>
			<div class="form-group">
	      		<label for="studentId">Student ID</label>
	      		<input type="tel" name="studentId" class="form-control" pattern="[0-9]{9}" placeholder="Student Number (e.g.123456789)" required/>
	    	</div>
		    <div class="form-group">
	      		<label for="firstName">First Name</label>
	      		<input type="text" class="form-control" placeholder="First Name" name="firstName" required>
	    	</div>
	    	<div class="form-group">
	      		<label for="lastName">Last Name</label>
	      		<input type="text" class="form-control" placeholder="Last Name" name="lastName" required>
	    	</div>
	    	<div class="form-group">
	      		<label for="email">Email ID</label>
	      		<input type="email" class="form-control" placeholder="Email ID" name="emailId" required>
	    	</div>
	    	<div class="form-group">
	      		<label for="phone">Phone</label>
				<input type="tel" name="phone" class="form-control" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" placeholder="Phone Number (e.g.123-456-7890)" required/>
	    	</div>
	    	<div class="form-group">
	      		<label for="photo" >Upload Your Photo</label>
				<input type="file"  name="file" required/>
	    	</div>
	    </fieldset>
	    	<input type="submit" class="btn btn-primary" value="Register" style="width: 340px; margin: 0 auto;"/>
	    </form>
	</div>
</body>
</html>