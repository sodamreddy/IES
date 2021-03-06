<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Plans</title>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#planTable').DataTable({
			"pagingType" : "full_numbers"
		});
	});
	function confirmDelete() {
		return confirm("Are you sure, you want to delete ?");
	}
	function confirmActivate() {
		return confirm("Are you sure, you want to Activate ?");
	}
</script>
</head>
<%@ include file="header.jsp"%>
<body>
	<h2>View plans</h2>
	<table border="1" id="planTable">
		<thead>
			<tr>
				<td>S.No</td>
				<td>Plan Name</td>
				<td>Plan Description</td>
				<td>Plan Start Date</td>
				<td>Plan End Date</td>
				<td>Action</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${records}" var="plan" varStatus="index">
				<tr>
					<td><c:out value="${index.count}" /></td>
					<td><c:out value="${plan.planName}" /></td>
					<td><c:out value="${plan.planDesc}" /></td>
					<td><c:out value="${plan.planStart}" /></td>
					<td><c:out value="${plan.planEnd}" /></td>
					<td><a href="editPlan?planId=${plan.planId}">Edit</a>
					<c:if test="${plan.activeSw=='Y'}">
							<a href="deletePlan?planId=${plan.planId}"
								onclick="return confirmDelete()">Delete</a>
						</c:if> <c:if test="${plan.activeSw=='N'}">
							<a href="activatePlan?planId=${plan.planId}"
								onclick="return confirmActive()">Activate</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>