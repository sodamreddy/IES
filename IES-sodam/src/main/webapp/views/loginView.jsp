<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Registration</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>
	$(function() {
		$('form[id="accRegForm"]').validate({
			rules : {
				emailId: 'required',
				password: 'required',
			},//rules
			messages : {
				
				emailId : 'Please enter a valid email',
				password: 'please enter password',
			},//messages
			submitHandler : function(form) {
				form.submit();
			}
		});

		
		

	});
</script>
</head>
<body>
	<h2 style="text-align: center;">Login</h2>
	<font style="text-align: center; color: red">${failed}</font>
	<form:form action="login" method="POST" modelAttribute="accModel"
		id="loginForm">
		<table align="center">
			<tr>
				<td>Email:</td>
				<td><form:input path="emailId"  /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="login" /></td>
				<td><a href="/IES/forgot">Forgot Password</a></td>
			</tr>
		</table>
	</form:form>
</body>
</html>