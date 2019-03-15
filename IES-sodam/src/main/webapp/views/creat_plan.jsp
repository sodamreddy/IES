<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	$(function(){
				$('form[id="crePlanForm"])').validate({
						rules:{
									planName: 'required',
									planDesc: 'required',
									planStart: 'required',
									planEnd:  'required',				
							},//rules
						messages:{
							planName: 'Please enter Plan Name',
							planDesc: 'Please enter Plan Description',
							planStart: 'Select Plan Start Date',
							planEnd: 'Select Plan End Date',
							},//msgs

						submitHandler:function(form){
								form.submit();
							},//submitHandler
					});
				$("#startDatePicker").datepicker({
					changeMonth:true,
					changeYear:true,
					dateFormat:'dd/mm/yy'
					});
				$("#endDatePicker").datepicker({
					changeMonth: true,
					changeYear: true,
					minDate:$("#startDatePicker").val()
					});
		});
</script>
</head>

<body>
	<form:form action="crtPln" method="POST" modelAttribute="planModel" id="crePlanForm">
		<table>
			<tr>
				<td>Plan Name</td>
				<td><form:input path="planName" /></td>
			</tr>
			<tr>
				<td>Plan Description</td>
				<td><form:input path="planDesc" /></td>
			</tr>
			<tr>
				<td>Plan Start Date</td>
				<td><form:input path="planStart" id="startDatePicker" /></td>
			</tr>
			<tr>
				<td>Plan End Date</td>
				<td><form:input path="planEnd" id="endDatePicker" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Create Plan" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>


<!-- https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_subnav
 -->