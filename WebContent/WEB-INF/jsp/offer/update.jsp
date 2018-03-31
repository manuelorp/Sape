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
			<h1>Update offer</h1>
			<form:form method="post" modelAttribute="offer" action="${pageContext.request.contextPath}/${action}">
				<table class="table">
					<tr>
						<td class="register">Offer ID</td>
						<td><form:input type="number" path="id" ></form:input></td>
					</tr>
					<tr>
						<td class="register">Start Date</td>
						<td><form:input type="date" path="startDate" ></form:input></td>
					</tr>
					<tr>
						<td class="register">End Date</td>
						<td><form:input type="date" path="endDate" ></form:input></td>
					</tr>
					<tr>
						<td class="register">Skill ID</td>
						<td><form:input type="number" path="skillId" ></form:input></td>
					</tr>
					<tr>
						<td class="register">Email</td>
						<td><form:input type="email" path="email" ></form:input></td>
					</tr>
					<tr>
						<td class="register">Description</td>
						<td><form:input type="text" path="description"></form:input></td>
					</tr>
					<tr>
						<td class="register"><input type="submit" value="Update offer" class="button2" onclick="return confirm('Do you want to submit your data?')"/></td>
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