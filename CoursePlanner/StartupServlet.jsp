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
	<c:choose>
	
			<c:when test="${empty sessionScope.user}">
				<p>
					<a href='Login'>Login</a>
				</p>
				<br />
				<p>
					<a href='UserRegistration'>New Registration</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>
					<a href='Logout'>Logout</a> 
					<br/><a href='SavedPlan?user=${sessionScope.user}'>Saved Course Plan</a> 
					 <br /> Welcome, ${sessionScope.user}
				</p>	
				
			</c:otherwise>
		</c:choose>
		<h1>Course Planner</h1>
		<table border='1'>
			<tr>
				<th>Course Code</th>
				<th>Course Title</th>
				<th>Course Prerequisites</th>
				<th><br /></th>
			</tr>
			<c:forEach items="${entry}" var="i" varStatus="Status">
	
				<tr>
					<td>${i.ccode}</td>
					<td>${i.ctitle}</td>
					<c:choose>
						<c:when test="${i.cprerec} eq null">
							<c:set property="${i.cprerec}" value=" "></c:set>
						</c:when>
					</c:choose>
					<td>${i.cprerec}</td>
					<td><a href="EditCourse?id=${Status.index}">Edit</a></td>
				</tr>
			</c:forEach>
		</table>
		<p>
			<a href='AddCourse'>Add Course</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href='CoursePlanner'>Course Planner</a>
		</p>
		<br />
	</body>
</html>