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
			<h1>Update skill</h1>
			<form:form method="post" modelAttribute="skill" action="${pageContext.request.contextPath}/${action}">
				<table class="table">
					<tr>
						<td class="register"><form:label path="id">Skill ID</form:label></td>
						<td><form:input type="number" path="id" /></td>
					</tr>
					<tr>
						<td class="register"><form:label path="name">Name</form:label></td>
						<td><form:input type="text" path="name" maxlength="50"/></td>
					</tr>
					<tr>
						<td class="register"><form:label path="lvl">Level</form:label></td>
						<td><form:select path="lvl">
								<option value="Basic">Basic</option>
								<option value="Medium">Medium</option>
								<option value="Advanced">Advanced</option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="register"><form:label path="status">Status</form:label></td>
						<td><form:select path="status">
								<option value="Active">Active</option>
								<option value="Cancelled">Cancelled</option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="register"><form:label path="kind">Type</form:label></td>
						<td><form:input type="text" path="kind" maxlength="50"/></td>
					</tr>
					<tr>
						<td class="register"><form:label path="description">Description</form:label></td>
						<td><form:input type="text" path="description" maxlength="200"/></td>
					</tr>
					<tr>
						<td class="register"><input type="submit" value="Update skill" class="button2" onclick="return confirm('Do you want to submit your data?')"/></td>
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