<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Planner</title>
</head>
<body>
	<h1>Course Planner</h1>
	<form action='CoursePlanner' method='post'>
		<table border='1'>
			<tr>
				<th></th>
				<th>Course Code</th>
				<th>Course Title</th>
				<th>Course Prerequisites</th>
				<th><br /></th>
			</tr>
			<c:forEach items="${entry}" var="i" varStatus="Status">

				<tr>
					<td><input type='checkbox' name='SelectSub'
						value='${i.ccode}' /></td>
					<td>${i.ccode}</td>
					<td>${i.ctitle}</td>
					<c:choose>
						<c:when test="${i.cprerec} eq null">
							<c:set property="${i.cprerec}" value=" "></c:set>
						</c:when>
					</c:choose>
					<td>${i.cprerec}</td>
				</tr>


			</c:forEach>
		</table>
		<br /> <input type="submit" name="next" value="Next" />
	</form>
</body>
</html>