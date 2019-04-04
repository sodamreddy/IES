<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>
	$(document).ready(function() {
		$(function() {
			$('form[id="appRegForm"]').validate({
				rules : {
					firstName : 'required',
					lastName : 'required',
					dateOfBirth : 'required',
					ssn : 'required',
					gender : 'required',
					phoneNo : 'required',
				},
				messages : {
					firstName : 'Enter First Name',
					lastName : 'Enter Last Name',
					dateOfBirth : 'Select data of birth',
					phoneNo : 'Enter Phone No',
					gender : 'Select Gender',
					ssn : 'Enter SSN number',
				},
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
			$(".input-ssn-number").on('change keyup',function(){
				console.log($("#ssn1").val().length);
				if($("#ssn1").val().length > 2){
					$("#ssn2").focus();
				}
				console.log($("#ssn2").val().length);
				if($("#ssn2").val().length>1){
					$("#ssn3").focus();

				} 
				$("#ssn3").blur(function(){
					$("#ssn").val($("#ssn1").val()+$("#ssn2").val()+$("#ssn3").val());
				})
			});
		});
	});
</script>
</head>
<body>
	<font color="green">${success}</font>
	<font color="red">${failed}</font>
	<h2>Application Registration</h2>
	<form:form action="appReg" method="POST" id="appRegForm"
		modelAttribute="appModel">
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
			</tr>
			<tr>
				<td>Phone No</td>
				<td><form:input path="phoneNo" /></td>
			</tr>
			<tr>
				<td>SSN</td>
				<td><input type="text" id="ssn1" class="input-ssn-number"
					maxlength="3"   style="width: 41px; "/>
				<input type="text" id="ssn2" class="input-ssn-number"
					maxlength="2" style="width: 41px; "/>
				<input type="text" id="ssn3" class="input-ssn-number"
					maxlength="4" style="width: 41px; "/></td>
			</tr>
			<tr>
				<td><form:hidden path="ssn" /></td>
			</tr>
			<tr>
				<td><input type="reset" value="Reset" /></td>
				<td><input type="Submit" value="Register"  /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>