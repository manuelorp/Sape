<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h1>List of offers</h1>
			<table class="list">
				<tr>
					<th>ID</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Offeror</th>
					<th>Skill ID</th>
					<th>Description</th>
				</tr>
				<c:forEach items="${offers}" var="offer">
					<tr>
						<td>${offer.id}</td>
						<td>${offer.startDate}</td>
						<td>${offer.endDate}</td>
						<td>${offer.email}</td>
						<td>${offer.skillId}</td>
						<td>${offer.description}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
        <div class="footer">
            <p>&copy; EI102716GGV &#8226; All rights reserved &#8226; <a href="${pageContext.request.contextPath}/privacy.jsp" class="footer_links">Privacy Policy</a> &#8226; <a href="${pageContext.request.contextPath}/about.jsp" class="footer_links">About</a> &#8226; <a href="${pageContext.request.contextPath}/contact.jsp" class="footer_links">Contact</a></p>
        </div>
	</body>

</html>