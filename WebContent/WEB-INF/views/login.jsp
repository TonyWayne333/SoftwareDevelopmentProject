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
			<c:if test="${!model.first}">	
				<c:if test="${!model.success}">
					<div class="alert alert-dismissible alert-danger">
					  <button type="button" class="close" data-dismiss="alert" onClick="removeDiv()">&times;</button>
					  <strong>Oops!</strong> ${model.message}
					</div>
				</c:if>
			</c:if>
		</div>
		<form action="login" method="post">  
			<h1 align="center">Login</h1>
			<fieldset>
				<div class="form-group">
		      		<label for="userName">Email ID</label>
		      		<input type="text" class="form-control" name="userName" placeholder="Enter Email ID" required>
		      		<small class="form-text text-muted">We'll never share your email id with anyone else.</small>
		    	</div>
		    	<div class="form-group">
		      		<label for="password">Password</label>
		      		<input type="password" class="form-control" name="password" placeholder="Enter Password" required>
		    	</div>
		    </fieldset>
		    	<button type="submit" class="btn btn-primary" style="width: 320px; margin: 0 auto;">Login</button>  
		    
		    <p>Don't have an account? <a href="${contextPath}/newProfessor">Sign up</a> here.</p>
		</form>
	</div>
</body>
</html>
