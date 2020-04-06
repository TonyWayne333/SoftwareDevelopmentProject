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
  padding: 10px 20px;
  border-radius: 8px;
}
h1{
  color:  #48B4E6;
}

.jumbotron{
	max-width: 400px;
	margin: 20px auto;
	padding: 20px;
}
</style>
<script type="text/javascript">
	function removeDiv(){
		var x = document.getElementById("alertSection");
		x.style.display = "none";
		
	}
</script>
<title>Sign Up Page</title>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  		<a class="navbar-brand" href="${contextPath}/">Online Attendance System</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
  		</button>

  		<div class="collapse navbar-collapse" id="navbarColor01">
    		<ul class="navbar-nav mr-auto">
      			<li class="nav-item active">
        			<a class="nav-link" href="#">Professor <span class="sr-only">(current)</span></a>
      			</li>
      			<li class="nav-item">
        			<a class="nav-link" href="${contextPath}/student">Student</a>
      			</li>
    		</ul>
  		</div>
	</nav>	
	
	<div class="jumbotron">
		<div id="alertSection">
			<c:if test="${!first}">	
				<c:if test="${!success}">
					<div class="alert alert-dismissible alert-danger">
					  <button type="button" class="close" data-dismiss="alert" onClick="removeDiv()">&times;</button>
					  <strong>Oh snap!</strong>You are already registered.
					</div>
				</c:if>
			</c:if>
		</div>
		<form action="signUp" method="post">  
		<h1 align="center">Sign Up</h1>
		<fieldset>
			<div class="form-group">
	      		<label for="firstName">Email ID</label>
	      		<input type="email" class="form-control" placeholder="Email ID" name="emailId" required>
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
	      		<label for="password">Password</label>
	      		<input type="password" class="form-control" name="password" placeholder="Enter Password" required>
	    	</div>
	    	<div class="form-group">
	      		<label for="address">Phone</label>
				<input type="tel" name="phone" class="form-control" pattern="[0-9]{10}" placeholder="Phone Number (e.g.1234567890)" required/>
	    	</div>
	    </fieldset>
	    	<input type="submit" class="btn btn-primary" value="SignUp" style="width: 320px; margin: 0 auto;"/>
	    
	    <p>Already have an account? <a href="${contextPath}/professor">Log In</a> here.</p>
	    </form>
	</div>
</body>
</html>