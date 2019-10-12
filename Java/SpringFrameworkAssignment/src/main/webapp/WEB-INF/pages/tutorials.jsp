<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tutorials Page</title>
</head>
<body>
 <h3>Tutorials Page</h3>
 <h4>This page is visible and accessible only to logged-in users</h4>
 <br>
 <a href='<c:url value="/getaccount/${userid}"/>'>Back</a>
 <br>
 <a href='<c:url value="/home"/>'>Home</a>
</body>
</html>