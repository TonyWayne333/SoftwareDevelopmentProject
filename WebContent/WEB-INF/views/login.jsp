<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log In Page</title>
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

</head>
<body>
<form action="login" method="post">  
	<h1 align="center">Login</h1>
	<fieldset>
		<div class="form-group">
      		<label for="userName">User Name</label>
      		<input type="text" class="form-control" name="userName" placeholder="Enter user name" required>
      		<small class="form-text text-muted">We'll never share your user name with anyone else.</small>
    	</div>
    	<div class="form-group">
      		<label for="password">Password</label>
      		<input type="password" class="form-control" name="password" placeholder="Enter Password" required>
    	</div>
    </fieldset>
    	<button type="submit" class="btn btn-primary">Login</button>  
    
    <p>Don't have an account? <a href="${contextPath}/newProfessor">Sign up</a> here.</p>
    </form>
</body>
</html>
