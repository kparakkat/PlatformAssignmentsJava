<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
		<script>
		    function updatecaptcha() {
		        document.getElementById('captchaimg').setAttribute('src', "captcha");
		        var span = document.getElementById('captaerror');
		        span.innerText = '';
		    }
		</script>
	</head>
	<body>
		<form:form id="loginForm" modelAttribute="login" action="loginProcess" method="post">
			<table align="center">
				<tr>
					<td>
						<form:label path="username">Username:</form:label>
					</td>
					<td>
						<form:input path="username" name="username" id="username" />
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="password">Password:</form:label>
					</td>
					<td>
						<form:password path="password" name="password" id="password" />
					</td>
				</tr>
				
				<tr>
					<td>Captcha</td>
					<td>
						<img src = "captcha" id ="captchaimg">
						<br>
						<button onclick="updatecaptcha">Refresh</button>
						<br>
						<input type="text" name="captcha" style="margin-top: 5px;">
						<br>
						<span style="font-style: italic; color: red;" id="captaerror"> ${error } </span>
					</td>
				</tr>
				<tr>
					<td align="left">
						<form:button id="login" name="login">Login</form:button>
					</td>
					<td align="left">
						<form:button id="reset" name="Reset">Reset</form:button>
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td><input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
					</td>
					<td><a href="home">Home</a>
					</td>
				</tr>
			</table>
		</form:form>
		<table align="center">
			<tr>
				<td style="font-style: italic; color: red;">${message}</td>
			</tr>
		</table>
	</body>
</html>