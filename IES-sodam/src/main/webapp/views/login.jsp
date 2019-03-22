<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>

	$(function() {
		$('form[id="loginForm"]').validate({
			rules : {
				emailId : 'required',
				password: 'required',
			},
			messages : {
				emailId : 'Enter Registred Email',
				password: 'Enter Password',
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>
</head>
<body>
	<h2 style="text-align: center;">Login</h2>
	<font style="text-align:center">${failue}</font>
	<form:form method="POST" action="login" id="loginForm" modelAttribute="accModel">
		<table align="center">
			<tr>
				<td>Email</td>
				<td><input type="text" name="emailId"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:password path="password"/></td>
			</tr>			
			<tr>
				<td><input type="Submit" value="login" /></td>
				<td><a href="/IES/forgot">Forgot Password</a>
			</tr>
		</table>
	</form:form>
</body>
</html>