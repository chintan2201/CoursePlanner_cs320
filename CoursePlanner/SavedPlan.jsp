<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Saved Plan</title>
</head>
<body>
<h1>Your Course Plans</h1>
<ul>
   <c:forEach items='${entries}' var='e'>
    <li>
        <a href="DisplaySavedPlan?u=${sessionScope.user}&t=${e}">Saved at ${e}</a>
    </li>
    </c:forEach>
</ul>

<br/>
<br/>
<a href="StartupServlet">Home Page</a>
</body>
</html>