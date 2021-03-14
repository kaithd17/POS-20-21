<%-- 
    Document   : rssList
    Created on : 14.03.2021, 12:21:23
    Author     : kainz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/8b1b2bee53.js" crossorigin="anonymous"></script>
        <link href="styles.css" rel="stylesheet"/>
        <title>RssList</title>
    </head>
    <body>
        <form action="RSSController" method="POST">
            <div class="header">
                <h1>RSS-List</h1>
            </div>

            <div class="inputContainer">
                <div class="inputClass">
                    <input type="text" name="inputField" value="" placeholder="Your Link..." class="inputField"/>
                    <button type="submit" name="buttonClick" value="subscribe" class="subcribeButton">Subscribe</button>
                </div>
            </div>

            <div class="listContainer">
                <table>
                    <tbody>
                        <c:forEach var="rss" items="${sessionScope.rssList}">
                            <tr>
                                <td>${rss.channel.title}</td>
                                <td><button class="buttonClass" value="view${rss.channel.title}" name="buttonClick">View Items</button></td>
                                <td><button class="buttonClass" value="unsubscribe${rss.channel.title}" name="buttonClick">Unsubscribe</button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>
