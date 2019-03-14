<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>
	$(function(){
		$('form[id="accRegForm"]').validate({
			rules:{
				firstName: 'required',
				lastName: 'required',
				dataOfBirth: 'required',
				gender:'required',
				ssn:'required',
				phoneNo:'required',
				role:'required',
				emailId:{
					required:true,
					emailId:true,
					},
				password:{
					required:true,
					maxlength:5,
				},
			},//rules
				messages : {
					firstName : 'Please enter first name',
					lastName : 'please enter last name',
					emailId : 'Please enter a valid email',
					password : {
						required : 'Please enter password',
						minlength : 'Password must be at least 5 characters long'
					},
					dob : 'Please select dob',
					gender : 'Please select Gender',
					role : 'Please select a Role',
					phoneNo : 'Please enter Phno',
					ssn : 'Please enter SSN'
				},//messages
				submitHandler:function(form){
					form.submit();
				}	
		});
		$("#datepicker").datepicker({
			changeMonth:true,
			changeYear: true,
			maxDate:new Date(),
			dateFormat:'dd/mm/yy'
		});	
	});
</script>
<body>
	<h2>Registration Form</h2>
	<form:form action="accReg" method="POST" name="accRegForm" modelAttribute="accModel">
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
				<td>Date Of Birth</td>
				<td><form:input path="dateOfBirth" id="datepicker" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td>Male<form:radiobutton path="gender" value="M" /> Female<form:radiobutton
						path="gender" value="F" /></td>
			</tr>
			<tr>
				<td>Email Id</td>
				<td><form:input path="emailId" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td>Phone No</td>
				<td><form:input path="phoneNo" /></td>
			</tr>
			<tr>
				<td>SSN</td>
				<td><form:input path="ssn" /></td>
			</tr>
			<tr>
				<td>Role</td>
				<td><form:select path="role" >
					<form:option value="Admin"/>
					<form:option value="case worker"/>
					</form:select>
					</td>
			</tr>
			<tr>
				<td><input type="reset" value="Reset" /></td>
				<td><input type="Submit" value="Register" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>