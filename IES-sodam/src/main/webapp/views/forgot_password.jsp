<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Password Recovery</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>
	$(function(){
		$('form[id="forgotForm"]').validate({
			rules:{
				emailId: 'required',
			},
			messages:{
				emailId:'Enter Registred Email',
			},
			submitHandler: function(form){
				form.submit();
			}
		});
	});
		
</script>
</head>
<body>
	<h2 style="text-align:center">Account Recovery</h2>
	<form:form method="GET" action="/forgot" id="forgotForm">
	<table align="center">
	<tr>
		<td>Email:<input type="text" name="emailId" ></td>
	</tr>
	<tr>
		<td><input type="submit" value="Submit"/></td>
	</tr>
	</table>
	</form:form>
</body>
</html>