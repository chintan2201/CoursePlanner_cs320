<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Saved Course Plan</title>
</head>
<body>
<h3>Here is your course plan.</h3>
<c:forEach items="${quart}" var="q">
   
                <u>${q}</u>
                <br />
                <br/>
            
            <table border="1">
                <tr>
                    <th>Code</th>
                    <th>Title</th>
                    <th>Prerequisites</th>
                </tr>
                <c:forEach items="${map}" var="l">

	                <c:if test="${l.quarter eq q}">
	                    <tr>
	                        <td>${l.code}</td>
	                        <td>${l.title}</td>
	                        <td>${l.prerec}</td>
	                    </tr>
	                </c:if>
                </c:forEach>
            </table>
            
            <br/>
           
    
</c:forEach>
 <a href="SavedPlan?user=${sessionScope.user}">Back</a>
</body>
</html>