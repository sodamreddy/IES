<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>
	$(function(){
		$('form[id="loginForm"]').validate(funtion(){
			rules: {
				emailId: 'required',
				password: 'required',
			},//rules
			messages:{
				emailId:'Please Enter Email ',
				password: 'please Enter Password',
			},//messages
			submitHandler: function(form){
				form.submit();
			}
		});
	});
</script>
</head>
<body>
	<h2 style="text-align: center;">Login</h2>
	<font style="text-align: center;color: red">${failed}</font>
	<form:form action="login" method="POST" modelAttribute="accModel"
		id="loginForm">
		<table align="center">
			<tr>
				<td>Email:</td>
				<td><form:input path="emailId" /></td>
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