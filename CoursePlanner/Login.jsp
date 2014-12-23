<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

        <form action='Login' method='post' style='width:30%;'>
        <fieldset>
        <legend>Login</legend>
        Username : <input type='text' name='username' /> <br />
        Password : <input type='password' name='password' /> <br />
        <input type='submit' name='login' value='Login' /> <br />
        </fieldset>
        </form>

        <a href='UserRegistration'>New Registration</a><br/>
        <a href='StartupServlet'>Home Page</a>
        
</body>
</html>