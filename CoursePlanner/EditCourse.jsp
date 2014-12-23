<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Course</title>
</head>
<body>
	<form action='EditCourse' method='post'>
		<table border='1'>
			<tr>
				<td>Code :</td>
				<td><input type='text' name='ccode' value='${ent.ccode}' /></td>
			</tr>
			<tr>
				<td>Title :</td>
				<td><input type='text' name='ctitle' value='${ent.ctitle}' /></td>
			</tr>
			<tr>
				<c:set var="s" value="${ent.cprerec}"></c:set>
				<td>Prerequisite(s) :</td>
				<td><c:forEach items="${entry}" var="i">

						<c:if test="${i.ccode ne ent.ccode}">


							<c:choose>
								<c:when test="${yes.contains(i.ccode)}">

									<input type='checkbox' name='Prerec' value='${i.ccode}'
										checked='checked' />${i.ccode}<br />

								</c:when>
								<c:otherwise>

									<input type='checkbox' name='Prerec' value='${i.ccode}' />${i.ccode}
										
										<br />
								</c:otherwise>
							</c:choose>


						</c:if>

					</c:forEach></td>
			</tr>
			<tr>
				<td colspan="2"><input type='submit' name='Save' value='Save' />
				</td>
			</tr>
		</table>
		<input type='hidden' name='id' value='${index}' /> <br />


	</form>
	<p>
		<a href='Logout'>Logout</a>
	</p>

</body>
</html>