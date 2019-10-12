<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Account Details</title>
    </head>
    <body>
        <table>
            <tr>
                <td>Welcome to account details page</td>
            </tr>
            <tr>
            	<td><a href='<c:url value="/updateaccount/${userid}"/>'>Update Account</a></td>
            </tr>
            <tr>
            	<td><a href='<c:url value="/tutorials/${userid}"/>'>Tutorials</a></td>
            </tr>
            <tr>
            	<td><a href='<c:url value="/admin/${userid}"/>'>Admin Page</a></td>
            </tr>
            <tr>
            </tr>
            <tr>
                <td><a href='<c:url value="/home"/>'>Logout</a>
                </td>
            </tr>
        </table>
    </body>
</html>