<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Course</title>
</head>
<body>
	<form action='AddCourse' method='post'>
		<table border='1'>
			<tr>
				<td>Code :</td>
				<td><input type='text' name='ccode' /></td>
			</tr>
			<tr>
				<td>Title :</td>
				<td><input type='text' name='ctitle' /></td>
			</tr>
			<tr>
				<td>Prerequisite(s) :</td>
				<td><c:forEach items="${entry}" var="i">
						<input type='checkbox' name='Prerec' value='${i.ccode}' /> ${i.ccode} <br />
					</c:forEach></td>
			</tr>
			<tr>
				<td colspan="2"><input type='submit' name='add' value='Add' />
					<br /></td>
			</tr>
		</table>
	</form>

	<p>
		<a href='Logout'>Logout</a>
	</p>
</body>
</html>