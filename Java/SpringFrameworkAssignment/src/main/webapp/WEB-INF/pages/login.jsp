<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
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
				   <td colspan="2">
				   		<table>
			   				<tr>
			                    <td>Image#</td>
			                    <td>
			                        <div>
			                            <img id="captcha_id" name="imgCaptcha" src="captcha.jpg">
			                        </div>
			                    </td>
			                    <td align="left"><a href="javascript:;"
			                        title="change captcha text"
			                        onclick="document.getElementById('captcha_id').src = 'captcha.jpg?' + Math.random();  return false">
			                            <img src="images/refresh.png" />
			                    </a></td>
			                </tr>
			                <tr>
			                    <td colspan="2"><form:errors path="captcha" cssClass="error" /></td>
			                </tr>
			 
			                <tr>
			                    <td>Enter above Image text#</td>
			                    <td><form:input path="captcha" /></td>
			                </tr>	   
				   		</table>
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