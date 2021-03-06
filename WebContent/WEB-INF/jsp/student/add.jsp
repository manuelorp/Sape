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
			<h1>New student</h1>
			<form:form method="post" modelAttribute="student" action="${pageContext.request.contextPath}/${action}">
                <table class="table">
					<tr>
						<td class="register"><form:label path="nif">NIF</form:label></td>
						<td><form:input type="text" path="nif" placeholder="12345678A" required="true" maxlength="9"/>
	                        <form:errors path="nif" cssClass="error"/>
                        </td>
					</tr>
					<tr>
						<td class="register"><form:label path="name">Name</form:label></td>
						<td><form:input type="text" path="name" placeholder="Skill Sharing" required="true" maxlength="100"/>
	                        <form:errors path="name" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td class="register"><form:label path="email">Email</form:label></td>
						<td><form:input path="email" type="email" placeholder="alxXxXxX@uji.es" required="true" maxlength="15"/>
	                        <form:errors path="email" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td class="register"><form:label path="username">Username</form:label></td>
						<td><form:input type="text" path="username" placeholder="username" required="true" maxlength="20"/>
	                        <form:errors path="username" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td class="register"><form:label path="degree">You are studying?</form:label></td>
						<td><form:input type="text" path="degree" placeholder="Grado en Ingeniería Informática" required="true" maxlength="100"/>
	                        <form:errors path="degree" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td class="register"><form:label path="course">Course number</form:label></td>
						<td><form:input path="course" type="number" required="true" min="1" max="6"/>
	                        <form:errors path="course" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td class="register"><form:label path="pw">Password</form:label></td>
						<td><form:input path="pw" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}" placeholder="Password" required="true"/>
	                        <form:errors path="pw" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td colspan="2"><p style="font-size:14px;">Password must have between 6 and 20 characters and include at least 1 UPPERCASE, 1 lowercase and 1 number</p></td>
					</tr>
					<tr>
						<td class="register"><form:label path="pw2">Confirm Password</form:label></td>
						<td><form:input path="pw2" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}" placeholder="Confirm password" required="true"/>
							<p style="font-size:14px;">Please re-type your password</p>
	                        <form:errors path="pw2" cssClass="error"/>
						</td>
					</tr>
                    <tr>
                        <td class="register"><input type="submit" value="Register" class="button2" onclick="return confirm('Do you want to submit your data?')"/></td>
                        <td><input type="reset" value="Clear data" class="button2" onclick="return confirm('Do you want to clear your data?')"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><p><strong>IMPORTANT: All fields are required!</strong></p></td>
                    </tr>
                </table>
			</form:form>       
		</div>
		
        <div class="footer">
            <p>&copy; EI102716GGV &#8226; All rights reserved &#8226; <a href="${pageContext.request.contextPath}/privacy.jsp" class="footer_links">Privacy Policy</a> &#8226; <a href="${pageContext.request.contextPath}/about.jsp" class="footer_links">About</a> &#8226; <a href="${pageContext.request.contextPath}/contact.jsp" class="footer_links">Contact</a></p>
        </div>
	</body>

</html>
