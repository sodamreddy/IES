<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Case</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>
	$(document).ready(function() {
		$(function() {
			$("#applicantDetails").hide();
			var uri = window.location.href.toString();
			$("#appNo").keyup(function() {
				var appno = $("#appNo").val().toUpperCase();
				$("#appNo").val(appno);
				var data;
				$.ajax({
					url : uri + "/getAppNos",
					success : function(result) {
						console.log(result)
						$("#appNo").autocomplete({
							source : result
						});
					}
				});

				 console.log(data)

				/* 	$("#appNo").change(function() {
						$.ajax({
							url : uri + "/getAppNos",
							data : "appNo=" + appno,
							success : function(result) {
								data = result;
							}
						});
					
					}); */
			});
			$("#appNoSearch").click(function() {
				$.ajax({
					url : uri + "/appNoIsAvailable",
					data : "appNo=" + $("#appNo").val(),
					success : function(result) {
						if (result = true) {
							$("#applicantDetails").show();
						} else {
							$("#applicantDetails").hide();
						}
					}
				});
			});
		});
	});
</script>
</head>
<body>
	<h2>Create Case</h2>
	<form style="align: center" action="/searchAppNo" method="POST">
		<table>
			<tr>
				<td>Application No:</td>
				<td><input type=text name="appNo" id="appNo" /></td>
				<td><input type="submit" id="appNoSearch" value="search" /></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>
	<div align="left" id="applicantDetails">
		<table>
			<tr>
				<td>Application No:</td>
				<td><p /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><p /></td>
			</tr>
			<tr>
				<td>Gender</td>
			</tr>
			<tr>
				<td>Date Of Birth</td>
				<td><p /></td>
			</tr>
			<tr>
				<td>SSN</td>
				<td><p /></td>
			</tr>
		</table>
	</div>
</body>
</html>