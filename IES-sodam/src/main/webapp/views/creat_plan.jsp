<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Plan</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>
	$(function() {
		$('form[id="crePlanForm"]').validate({
			rules : {
				planName : 'required',
				planStart : 'required',
				planEnd : 'required',
				planDesc : {
					required : true,
					maxlength : 120,
				},
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

		
		/* $("#datepickerStart").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd/mm/yy'
		});   */
	 
	 /* $("#datepickerStart").datepicker({
	        defaultDate: new Date(),
	        minDate: new Date(),
	        dateFormat : 'dd/mm/yy',
	        onSelect: function(dateStr) 
	        {         
	            $("#datepickerEnd").val(dateStr);
	            $("#datepickerEnd").datepicker("option",{ minDate: new Date(dateStr)})
	        }
	    }); */
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
		$("#planName").blur(function(){
			var enteredPlanName = $("#planName").val();
			$.ajax({
				url : window.location + "/validPlan",
				data : "planName=" + enteredPlanName,
				success : function(result) {
					if (result == false) {
						$("#planmsg").html("Plan Already Exists.!!");
						$("#planName").focus();
					} else {
						$("#planmsg").html("");
					}
				}
			});
		});		
	});
</script>
</head>
<%@ include file="header.jsp" %>
<h2>Enter Plan Details</h2>
<font color="green">${success}</font>
<font color="red">${failed}</font>
<body>
	<form:form action="crtPln" method="POST" id="crePlanForm"
		modelAttribute="planModel">
		<table>
			<tr>
				<td>Plan Name</td>
				<td><form:input path="planName" /></td>
				<td><font color='red'><span id="planmsg"></span></font></td>
			</tr>
			<tr>
				<td>Plan Description</td>
				<td><form:input path="planDesc" /></td>
			</tr>
			<tr>
				<td>Plan Start Date</td>
				<td><form:input path="planStart" id="datepickerStart" /></td>
			</tr>
			<tr>
				<td>Plan End Date</td>
				<td><form:input path="planEnd" id="datepickerEnd" /></td>
			</tr>
			<tr>
			<td><input type="reset" value="Reset" /></td>
				<td><input type="Submit" value="Create Plan" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>