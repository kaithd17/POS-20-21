<%-- 
    Document   : rssView
    Created on : 02.03.2021, 10:36:42
    Author     : kainz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/8b1b2bee53.js" crossorigin="anonymous"></script>
        <link href="styles.css" rel="stylesheet">
        <title>RSS-Feeds</title>
    </head>
    <body>
        <form action="RSSController" method="POST">
            <div class="header">
                <h1>${sessionScope.rssFeed.channel.title}</h1>
            </div>
            <div class="header">
                <button class="buttonClass" value="showAll" name="buttonClick">Show All</button>
                <button class="buttonClass" value="back" name="buttonClick">Back</button>
            </div>
            <div class="feedContainer">
                <div class="feedList">
                    <c:forEach var="item" items="${sessionScope.rssFeed.channel.itemList}">
                        <div class="${item.read == true ? "feedObject2" : "feedObject"}">
                            <table border="0">
                                <tbody>
                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.enclosure.url != null}">
                                                    <img src='${item.enclosure.url}'>
                                                </c:when>
                                                <c:otherwise>
                                                    <img class="noPicture" src='https://upload.wikimedia.org/wikipedia/commons/f/fc/No_picture_available.png'>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <table border="0">
                                                <thead>
                                                <th>${item.title}</th>
                                                    <c:choose>
                                                        <c:when test="${item.read}">
                                                        <th><button class="buttonClass" value="read${item.id}" name="buttonClick"><i class="fas fa-bookmark"></i></button></th>

                                                    </c:when>
                                                    <c:otherwise>
                                                        <th><button class="buttonClass" value="read${item.id}" name="buttonClick"><i class="far fa-bookmark"></i></button></th>
                                                            </c:otherwise>
                                                        </c:choose>
                                                <th><button class="buttonClass" value="hide${item.id}" name="buttonClick"><i class="fas fa-ban"></i></button></th>
                                                </thead>
                                                <tbody>
                                                    <tr><td>${item.description}</td></tr>
                                                    <tr><td>${item.formatDate()}</td></tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="buttonDirection">
                                <a class="buttonClass" href="${item.link}">read more</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </form>
    </body>
</html>
