<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Registration</title>

<!-- <style>
.error {
	color: #FF0000
}
</style> -->
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
				firstName : 'required',
				lastName : 'required',
				emailId : {
					required : true,
					emailId : true,
				},
				password : {
					required : true,
					minlength : 5,
				},
				dateOfBirth : 'required',
				gender : 'required',
				role : 'required',
				phoneNo: 'required',
				ssn : 'required'
			},
			messages : {
				firstName : 'Please enter first name',
				lastName : 'please enter last name',
				emailId : 'Please enter a valid email',
				password : {
					required : 'Please enter password',
					minlength : 'Password must be at least 5 characters long'
				},
				dateOfBirth : 'Please select dob',
				gender : 'Please select Gender',
				role : 'Please select a Role',
				phoneNo : 'Please enter Phno',
				ssn : 'Please enter SSN'
			},
			submitHandler : function(form) {
				form.submit();
			}
		});

		/* $("#emailId").blur(function() {
			var enteredEmail = $("#emailId").val();
			$.ajax({
				url : window.location + "/validateEmail",
				data : "email=" + enteredEmail,
				success : function(result) {
					if (result == 'Duplicate') {
						$("#emailMsg").html("Email already registered.!!");
						$("#emailId").focus();
					} else {
						$("#emailMsg").html("");
					}

				}
			});

		}); */

		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			maxDate : new Date(),
			dateFormat : 'dd/mm/yy'
		});
	});
</script>
</head>

<body>
	<font color='green'>${success}</font>
	<font color='red'>${failure}</font>

	<h2>Case Worker Registration</h2>
	<form:form action="accReg" method="POST" modelAttribute="accModel"
		id="accRegForm">
		<table>
			<tr>
				<td>First Name</td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td>Date Of Birth:</td>
				<td><form:input path="dateOfBirth" id="datepicker"  /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="emailId" id="emailId" /></td>
				<td><font color='red'><span id="emailMsg"></span></font></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><form:radiobutton path="gender" value="Male" /> <form:radiobutton
						path="gender" value="Female" /></td>
			</tr>
			<tr>
				<td>Phone No:</td>
				<td><form:input path="phoneNo" /></td>
			</tr>
			<tr>
				<td>SSN:</td>
				<td><form:input path="ssn" /></td>
			</tr>
			<tr>
				<td>Role:</td>
				<td><form:select path="role" items="${roleList}" /></td>
			</tr>
			<tr>
				<td><input type="reset" value="Reset" /></td>
				<td><input type="Submit" value="Register" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>