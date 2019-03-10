<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Registration</title>
</head>
<body>
	<table>
		<tr>
			<td>First Name:</td>
			<td><form:input path="firstName" /></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><form:input path="lastName" /></td>
		</tr>
		<tr>
			<td>Date Of Birth:</td>
			<td><form:input path="dateOfBirth" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><form:input path="emailId" /></td>
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
			<td><form:input path="ssn"/></td>
		</tr>
		<tr>
			<td>Role:</td>
			<td><form:select path="role" items=${roleList } /></td>
		</tr>
		<tr>
			<td><form:button>Reset</form:button>
			<td><form:button>Register</form:button>
		</table>
		