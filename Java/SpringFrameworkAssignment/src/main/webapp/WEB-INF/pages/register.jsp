<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
 <html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Registration Form</title>
	</head>
	<body>
		<form:form id="regForm" modelAttribute="user" action="registerForm" method="post">
			<table align="center">
				<tr>
					<td>
						<form:label path="name">Name:</form:label>
					</td>
					<td>
						<form:input path="name" name="name" id="name" value = "${user.name}" />
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="email">Email:</form:label>
					</td>
					<td>
						<form:input path="email" name="email" id="email" value="${user.email}"  />
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="username">Username:</form:label>
					</td>
					<td>
						<form:input path="username" name="username" id="username" value="${user.username}"/>
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="password">Password:</form:label>
					</td>
					<td>
						<form:password path="password" name="password" id="password" value="${user.password}" />
					</td>
				</tr>
				<tr>
					<td>
						<form:button id="register" name="register">Register</form:button>
					</td>
					<td>
						<form:button id="reset" name="reset">Reset</form:button>
					</td>
				</tr>
				
				<tr></tr>
				<tr>
					<td><input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
					<form:input type="hidden" path="id" name="id" id="id"  value="${user.id}"  />
					</td>
					<td><a href='<c:url value="/home"/>'>Home</a>
					</td>
				</tr>
			</table>
		</form:form>
	</body>
</html>