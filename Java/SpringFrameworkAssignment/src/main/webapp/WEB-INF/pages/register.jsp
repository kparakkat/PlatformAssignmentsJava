<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%
	String contextPath = request.getContextPath();
%>
 <html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Registration Form</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <style type="text/css">
            .errormsg {
                color: red;
            }
            * { font-family: Times New Roman !important }
        </style>
        <script>
		    function updatecaptcha() {
		    	var captaPath = "/SpringFrameworkAssignment/captcha";
		        document.getElementById('captchaimg').setAttribute('src', "captcha");
		        var span = document.getElementById('captaerror');
		        span.innerText = '';
		    }
		</script>
	</head>
	<body>
		<form:form id="regForm" modelAttribute="user" action="/SpringFrameworkAssignment/registerForm" method="post">
			<table align="center">
				<tr>
					<td>
						<form:label path="name">Name:</form:label>
					</td>
					<td>
						<form:input path="name" name="name" id="name" value = "${user.name}" />
					</td>
					<td>
						<small><form:errors path="name" cssClass="errormsg" /></small>
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="email">Email:</form:label>
					</td>
					<td>
						<form:input path="email" name="email" id="email" value="${user.email}"  />
					</td>
					<td>
						<small><form:errors path="email" cssClass="errormsg" /></small>
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="username">Username:</form:label>
					</td>
					<td>
						<form:input path="username" name="username" id="username" value="${user.username}"/>
					</td>
					<td>
						<small><form:errors path="username" cssClass="errormsg" /></small>
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="password">Password:</form:label>
					</td>
					<td>
						<form:password path="password" name="password" id="password" value="${user.password}" />
					</td>
					<td>
						<small><form:errors path="password" cssClass="errormsg" /></small>
					</td>
				</tr>
				
				<tr>
					<td>Captcha</td>
					<td colspan="2">
						<img src="/SpringFrameworkAssignment/captcha" id ="captchaimg">
						<br>
						<button onclick="updatecaptcha">Refresh</button>
						<br>
						<input type="text" name="captcha" style="margin-top: 5px;">
						<br>
						<span style="font-style: italic; color: red;" id="captaerror"> ${error } </span>
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
				<tr>
					<td><input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
					<form:input type="hidden" path="id" name="id" id="id"  value="${user.id}"  />
					</td>
					<td><a href='<c:url value="/home"/>'>Home</a>
					</td>
				</tr>
				<tr>
				   <td> 
				</tr>
			</table>
		</form:form>
	</body>
</html>