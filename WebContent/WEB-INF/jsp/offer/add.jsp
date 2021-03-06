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
			<h1>New offer</h1>
			<form:form method="post" modelAttribute="offer" action="${pageContext.request.contextPath}/${action}">
				<table class="table">
					<tr>
						<td class="register"><form:label path="startDate">Start Date *</form:label></td>
						<td><form:input type="date" path="startDate" required="true" />
							<form:errors path="startDate" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td class="register"><form:label path="endDate">End Date *</form:label></td>
						<td><form:input type="date" path="endDate" required="true" />
							<form:errors path="endDate" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td class="register"><form:label path="skillId">Skill ID *</form:label></td>
						<td><form:input type="number" path="skillId" required="true" min="1"/>
							<form:errors path="skillId" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td class="register"><form:label path="email">Email *</form:label></td>
						<td><form:input type="email" path="email" required="true" placeholder="alxXxXxX@uji.es" maxlength="15"/>
							<form:errors path="email" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td class="register"><form:label path="description">Description</form:label></td>
						<td><form:input type="text" path="description" placeholder="Describe this offer" maxlength="200"/>
							<form:errors path="description" cssClass="error"/>
						</td>
					</tr>
					<tr>
						<td class="register"><input type="submit" value="Add offer" class="button2" onclick="return confirm('Do you want to submit your data?')"/></td>
						<td><input type="reset" value="Clear data" class="button2" onclick="return confirm('Do you want to clear your data?')"/></td>
					</tr>
					<tr>
						<td colspan="2"><p><strong>IMPORTANT: Fields marked with asterisk (*) are required</strong></p></td>
					</tr>
				</table>
			</form:form>  
		</div>
		
        <div class="footer">
            <p>&copy; EI102716GGV &#8226; All rights reserved &#8226; <a href="${pageContext.request.contextPath}/privacy.jsp" class="footer_links">Privacy Policy</a> &#8226; <a href="${pageContext.request.contextPath}/about.jsp" class="footer_links">About</a> &#8226; <a href="${pageContext.request.contextPath}/contact.jsp" class="footer_links">Contact</a></p>
        </div>
	</body>

</html>