<%-- 
    Document   : contactView
    Created on : 23.11.2020, 12:21:34
    Author     : kainz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <script src="https://kit.fontawesome.com/8b1b2bee53.js" crossorigin="anonymous"></script>
        <title>ContactApp</title>
    </head>
    <body>
        <form action="./ContactController" method="POST">
            <div class="header">
                <h1>ContactApp</h1>
            </div>
            <div class="filterContainer">
                <span></span>
            </div>
            <div class="contactTableContainer">
                <table border="0" class="contactTable">
                    <thead>
                        <tr>
                            <th class="tableHeader"></th>
                            <th class="tableHeader"></th>
                            <th class="tableHeader">Firstname:</th>
                            <th class="tableHeader">Lastname:</th>
                            <th class="tableHeader">Email:</th>
                            <th class="tableHeader">Birthday:</th>
                            <th class="tableHeader">Company:</th>
                            <th class="tableHeader">Stockmarket:</th>
                            <th class="tableHeader">Favourites:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="contact" items="${sessionScope.contactList}" varStatus="counter">
                            <tr>
                                <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}><input type="checkbox" name="userPick" value="ON" /></td>
                                <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}><i class="fas fa-user"></i></td>
                                <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}>${contact.getFirstname()}</td>
                                <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}>${contact.getLastname()}</td>
                                <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}>
                                    <c:forEach var="email" items="${contact.getEmail()}">
                                        ${email}
                                        <br>
                                    </c:forEach>
                                </td>
                                <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}>${contact.formatDate()}</td>
                                <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}>${contact.getCompany().getName()}</td>
                                <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}>${contact.getCompany().getStockmarket()}</td>
                                <c:choose>
                                    <c:when test="${contact.isFavourite()}">
                                        <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}><i class="fas fa-star"></i></td>  
                                        </c:when>
                                        <c:otherwise>
                                        <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}></td>  
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>