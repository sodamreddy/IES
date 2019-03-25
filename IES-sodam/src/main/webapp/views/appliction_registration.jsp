<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Application Registration</title>
<script type="text/javascript">
$(function() {
		$('form[id="appRegForm"]').validate({
			rules : {
				firstName : 'required',
				lastName : 'required',
				gender : 'required',
				ssn : 'required',
				phoneNo : 'required',
				role : 'required',
				emailId : {
					required : true,
					emailId : true,
				},
				password : {
					required : true,
					maxlength : 5,
				},
				dateOfBirth : 'required',
			},//rules
			messages : {
				firstName : 'Please enter first name',
				lastName : 'please enter last name',
				emailId : 'Please enter a valid email',
				password : {
					required : 'Please enter password',
					minlength : 'Password must be at least 5 characters long'
				},
				dateOfBirth : 'Please select date',
				gender : '     Please select Gender',
				role : 'Please select a Role',
				phoneNo : 'Please enter Phone No',
				ssn : 'Please enter SSN'
			},//messages
			errorPlacement : function(error, element) {
				if (element.is(":radio")) {
					error.appendTo(element.parents('.gender'));
				} else { // This is the default behavior of the script for all fields
					error.insertAfter(element);
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});

		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			maxDate : new Date(),
			dateFormat : 'dd/mm/yy'
		});

		$("#emailId").blur(function() {
			var givenEmail = $("#emailId").val();
			var uri=window.location.href.toString();
			console.log(uri);
			$.ajax({
				url : window.location + "/uniqueMail",
				data : "emailId=" + givenEmail,
				success : function(result) {
					if (result == "Duplicate") {
						$("#emailMsg").html("Email  Already Exists.!!");
						$("#emailId").focus();
					} else {
						$("#emailMsg").html("");

					}
				}
			});
		});
	});
</script>
</head>
<body>
<h1>Account Registration Form</h1>
	<font color="green">${success}</font>
	<font color="red">${failed}</font>

	<form:form action="accReg" method="POST" id="accRegForm"
		modelAttribute="accModel">
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
				<td class="gender">Male<form:radiobutton path="gender"
						value="Male" />Female <form:radiobutton path="gender"
						value="Female" /></td>
			</tr>
			<tr>
				<td>Email Id</td>
				<td><form:input path="emailId" />
				<td><font color='red'><span id="emailMsg"></span></font></td>
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
					<td><form:input path="ssn"/>
					<form:input path="ssn" />
					<form:input path="ssn" /></td>
			</tr>
			<tr>
				<td>Role</td>
				<td><form:select path="role" items="${roleList}"></form:select>
			</tr>
			<tr>
				<td><input type="reset" value="Reset" /></td>

				<td><input type="Submit" value="Register"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>