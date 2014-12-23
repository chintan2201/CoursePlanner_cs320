<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
	<div style='float: left;'>
		<form action='UserRegistration' method='post'>
			<table border='1'>
				<tr>
					<th colspan='3'>
						<h2>User Registration</h2>
					</th>
				</tr>
				<tr>
					<td>Username:</td>
					<td><input type='text' name='username' /></td>
					<td><c:if test="${map.containsKey('unamereq')}">
	           - <font color='red'> ${map.get('unamereq')}</font>
						</c:if> <c:if test="${map.containsKey('unlength')}">
               - <font color='red'> ${map.get('unlength')}</font>
						</c:if> <c:if test="${map.containsKey('unexist')}">
               - <font color='red'> ${map.get('unexist')}</font>
						</c:if></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
					<td><c:if test="${map.containsKey('pswdreq')}">
               - <font color='red'> ${map.get('pswdreq')}</font>
						</c:if> <c:if test="${map.containsKey('pswdlength')}">
               - <font color='red'> ${map.get('pswdlength')}</font>
						</c:if></td>
				</tr>
				<tr>
					<td>Retype Password:</td>
					<td><input type='password' name='retypepassword' /></td>
					<td><c:if test="${map.containsKey('rtpswdreq')}">
               - <font color='red'> ${map.get('rtpswdreq')}</font>
						</c:if> <c:if test="${map.containsKey('matchpswd')}">
               - <font color='red'> ${map.get('matchpswd')}</font>
						</c:if></td>
				</tr>
				<tr>
					<td>First Name(Optional):</td>
					<td><input type='text' name='firstname' /></td>
				</tr>
				<tr>
					<td>Last Name(Optional):</td>
					<td><input type='text' name='lastname' /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' name='Register'
						value='Register' /></td>
				</tr>
			</table>
			${map.clear()}
		</form>
	</div>
</body>
</html>