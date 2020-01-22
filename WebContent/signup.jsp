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
  max-width: 400px;
  margin: 10px auto;
  padding: 10px 20px;
  border-radius: 8px;
}
h1{
  color:  #48B4E6;
}
</style>
<title>Sign Up Page</title>

</head>
<body>
	<form action="signUp" method="post">  
	<h1 align="center">Sign Up</h1>
	<fieldset>
		<div class="form-group">
      		<label for="firstName">Email Id</label>
      		<input type="email" class="form-control" placeholder="Email Id" name="emailId" required>
    	</div>
	    <div class="form-group">
      		<label for="firstName">First Name</label>
      		<input type="text" class="form-control" placeholder="First Name" name="firstName" required>
    	</div>
    	<div class="form-group">
      		<label for="lastName">Last Name</label>
      		<input type="text" class="form-control" placeholder="last Name" name="lastName" required>
    	</div>
    	<div class="form-group">
      		<label for="password">Password</label>
      		<input type="password" class="form-control" name="password" placeholder="Enter Password" required>
    	</div>
    	<div class="form-group">
      		<label for="address">Phone</label>
			<input type="tel" name="phone" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" placeholder="Your Phone Number (e.g.123-456-7890)" required/>
    	</div>
    </fieldset>
    	<input type="submit" class="btn btn-primary" value="SignUp" />
    
    <p>Already have an account? <a href="index.jsp">Log In</a> here.</p>
    </form>
	
</body>
</html>