<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, height=device-height, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <title>Skill Sharing</title>
    </head>
    
    <body>
        <div class="header">
            <img src="${pageContext.request.contextPath}/css/skill-sharing.png" alt="Skill Sharing" class="image">
        </div>
		
		<div class="content">
			<h1>Update student</h1>
			<form:form method="post" modelAttribute="student" action="${pageContext.request.contextPath}/${action}">
				<table class="table">
					<tr>
						<td class="register"><form:label path="nif">NIF</form:label></td>
						<td><form:input type="text" path="nif" /></td>
					</tr>
					<tr>
						<td class="register"><form:label path="name">Name</form:label></td>
						<td><form:input type="text" path="name" /></td>
					</tr>
					<tr>
						<td class="register"><form:label path="email">Email</form:label></td>
						<td><form:input path="email" type="email" /></td>
					</tr>
					<tr>
						<td class="register"><form:label path="username">Username</form:label></td>
						<td><form:input type="text" path="username" /></td>
					</tr>
					<tr>
						<td class="register"><form:label path="degree">You are studying?</form:label></td>
						<td><form:input type="text" path="degree" /></td>
					</tr>
					<tr>
						<td class="register"><form:label path="course">Course number</form:label></td>
						<td><form:input path="course" type="number" /></td>
					</tr>
					<tr>
						<td class="register"><form:label path="pw">New Password</form:label></td>
						<td><form:input path="pw" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}" placeholder="New password" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2"><p style="font-size:14px;">Password must have between 6 and 20 characters and include at least 1 UPPERCASE, 1 lowercase and 1 number</p></td>
					</tr>
					<tr>
						<td class="register"><form:label path="pw2">Confirm New Password</form:label></td>
						<td><form:input path="pw2" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}" placeholder="Confirm new password" required="true"/>
						<p style="font-size:14px;">Please retype your password</p></td>
					</tr>
					<tr>
						<td class="register"><input type="submit" value="Update student" class="button2" onclick="return confirm('Do you want to submit your data?')"/></td>
						<td><input type="reset" value="Clear data" class="button2" onclick="return confirm('Do you want to clear your data?')"/></td>
					</tr>

				</table>
			</form:form>       
		</div>
		
        <div class="footer">
            <p>&copy; EI102716GGV &#8226; All rights reserved &#8226; <a href="${pageContext.request.contextPath}/privacy.jsp" class="footer_links">Privacy Policy</a> &#8226; <a href="${pageContext.request.contextPath}/about.jsp" class="footer_links">About</a> &#8226; <a href="${pageContext.request.contextPath}/contact.jsp" class="footer_links">Contact</a></p>
        </div>
	</body>

</html>
