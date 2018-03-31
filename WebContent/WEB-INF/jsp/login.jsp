<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
        
            <h1>Login</h1>
        
            <form:form method="post" modelAttribute="login" action="${pageContext.request.contextPath}/login.html">

                <table class="table">
                    <tr>
                        <td class="register"><form:label path="username">Username:</form:label></td>
                        <td><form:input type="text" path="username" pattern="\w+" required="true" placeholder="username" maxlength="20"/>
                        <form:errors path="username" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td class="register"><form:label path="pw">Password:</form:label></td>
                        <td><form:input type="password" path="pw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}" required="true" placeholder="password"/>
                        <form:errors path="pw" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td class="register"><input type="submit" value="Log in " class="button2"></td>
                        <td><input type="reset" value="Clear data" class="button2"></td>
                    </tr>

                </table>
			</form:form>
			
			<p class="p"><br>Don't have an account?<br>Click <a href="${pageContext.request.contextPath}/student/add.html" class="links">here</a> to register.</p> 
			
        </div>
        
        <div class="footer">
            <p>&copy; EI102716GGV &#8226; All rights reserved &#8226; <a href="${pageContext.request.contextPath}/privacy.jsp" class="footer_links">Privacy Policy</a> &#8226; <a href="${pageContext.request.contextPath}/about.jsp" class="footer_links">About</a> &#8226; <a href="${pageContext.request.contextPath}/contact.jsp" class="footer_links">Contact</a></p>
        </div>
    </body>
</html>
