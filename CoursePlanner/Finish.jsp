<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finish</title>
</head>
<body>
	<form action="Finish" method="post">
		<h1>Your Course Plan</h1>
		<c:forEach items="${quarter}" var="q">
			<c:if test="${not empty f.get(q)}"> 
				<u>${q}</u>
				<br />
				<br/>
			
			<table border="1">
				<tr>
					<th>Code</th>
					<th>Title</th>
					<th>Prerequisites</th>
				</tr>
				<c:forEach items="${f.get(q)}" var="l">


					<tr>
						<td>${l.ccode}</td>
						<td>${l.ctitle}</td>
						<td>${l.cprerec}</td>
					</tr>

				</c:forEach>
			</table>
			 </c:if>
            <br/>
		</c:forEach>
		<br /> <input type="submit" name="done" value="Done" /> 
		<c:choose>
            <c:when test="${not empty sessionScope.user}">
                <p>
                  | <input type="submit" name="done" value="Save this course plan" />
                </p>             
            </c:when>
       </c:choose>
	</form>
</body>
</html>