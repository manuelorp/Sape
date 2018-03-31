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
            <table class="center">
                <tr>
                    <th colspan="3"><h2>Personal things</h2></th>
                </tr>
                <tr>
                    <td><a href="${pageContext.request.contextPath}/collaboration/myCollaborations.html" class="links"><h3>View my collaborations</h3></a></td>
                    <td><a href="${pageContext.request.contextPath}/request/myRequests.html" class="links"><h3>View my requests</h3></a></td>
                    <td><a href="${pageContext.request.contextPath}/offer/myOffers.html" class="links"><h3>View my offers</h3></a></td>
                </tr>
                <tr>
                    <th colspan="3"><h2>General things</h2></th>
                </tr>
                <tr>
                    <td><a href="${pageContext.request.contextPath}/skill/skill_list.html" class="links"><h3>View skills' list</h3></a></td>
                    <td><a href="${pageContext.request.contextPath}/request/request_list.html" class="links"><h3>View requests' list</h3></a></td>
                    <td><a href="${pageContext.request.contextPath}/offer/offer_list.html" class="links"><h3>View offers' list</h3></a></td>
                </tr>
            </table>

        </div>
        
        <div class="footer">
            <p>&copy; EI102716GGV &#8226; All rights reserved &#8226; <a href="${pageContext.request.contextPath}/privacy.jsp" class="footer_links">Privacy Policy</a> &#8226; <a href="${pageContext.request.contextPath}/about.jsp" class="footer_links">About</a> &#8226; <a href="${pageContext.request.contextPath}/contact.jsp" class="footer_links">Contact</a></p>
        </div>
    </body>
</html>
