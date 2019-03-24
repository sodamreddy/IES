<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Plan</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>
	$(function() {
		$('form[id="editPlanForm"]')
				.validate(
						{
							rules : {
								planName : 'required',
								planStart : 'required',
								planEnd : 'required',
								planDesc : 'required',
							},//rules
							messages : {
								planName : 'Please enter plan name',
								planStart : 'please select plan start date',
								planEnd : 'please select plan end date',
								planDesc : {
									required : 'Please enter plan description',
									minlength : 'plan description should not exeed 120 characters'
								},
							},//messages
							submitHandler : function(form) {
								form.submit();
							}
						});
		 $("#datepickerStart").datepicker({
		    	changeMonth : true,
				changeYear : true,
				dateFormat : 'dd/mm/yy',
		    	  onSelect: function(dateText, inst){
		    	     $("#datepickerEnd").datepicker("option","minDate",
		    	     $("#datepickerStart").datepicker("getDate"));
		    	  }
		    	});

			$("#datepickerEnd").datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : 'dd/mm/yy',
			}); 
	});
</script>
</head>
<%@ include file="header.jsp"%>
<h2>Enter Plan Details</h2>
<font color="green">${success}</font>
<font color="red">${failed}</font>
<body>
	<form:form action="editPlan" method="POST" modelAttribute="planModel" id="editPlanForm" >
		<table>
			<tr>
				<td>Plan Id</td>
				<td><form:input path="planId" readonly="true" /></td>
			</tr>
			<tr>
				<td>Plan Name</td>
				<td><form:input path="planName" readonly="true" /></td>
				<td><font color='red'><span id="planmsg"></span></font></td>
			</tr>
			<tr>
				<td>Plan Description</td>
				<td><form:textarea path="planDesc" /></td>
			</tr>
			<tr>
				<td>Plan Start Date</td>
				<td><form:input path="planStart" id="datepickerStart" /></td>
			</tr>
			<tr>
				<td>Plan End Date</td>
				<td><form:input path="planEnd" id="datepickerEnd" /></td>
			</tr>
			<tr><td><form:hidden path="activeSw"/></td></tr>
			<tr><td><form:hidden path="createdBy"/></td></tr>
			<tr><td><form:hidden path="updatedBy"/></td></tr>
			<tr>
				<td><input type="reset" value="Reset" /></td>
				<td><input type="Submit" value="Update Plan" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>