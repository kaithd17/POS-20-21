<%-- 
    Document   : detailView
    Created on : 23.12.2020, 14:15:35
    Author     : kainz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details</title>
        <link rel="stylesheet" href="detailStyles.css"/>
    </head>
    <body>
        <form action="./MovieController" method="POST">
            <div class="header">
                <h1>${sessionScope.selectedMovie.getTitle()}</h1>
            </div>

            <div class="movieDetailContainer">
                <div class="movieDetailObject">
                    <div class="imageContainer">
                        <c:choose>
                            <c:when test="${sessionScope.selectedMovie.getPoster() != 'N/A'}">
                                <img src='${sessionScope.selectedMovie.getPoster()}'>
                            </c:when>
                            <c:otherwise>
                                <img src='https://davidkoepp.com/wp-content/themes/blankslate/images/Movie%20Placeholder.jpg'>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="movieDetail">
                        <table>
                            <tbody>
                                <tr>
                                    <td><span>Year:</span> ${sessionScope.selectedMovie.getYear()}</td>
                                    <td><span>Rated:</span> ${sessionScope.selectedMovie.getRated()}</td>
                                </tr>
                                <tr>
                                    <c:choose>
                                        <c:when test="${sessionScope.selectedMovie.getReleased() == null}">
                                            <td><span>Release:</span> N/A</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span>Release:</span> ${sessionScope.selectedMovie.formatDate()}</td>
                                        </c:otherwise>
                                    </c:choose>
                                            
                                    <td><span>Runtime:</span> ${sessionScope.selectedMovie.getRuntime()}</td>
                                </tr>
                                <tr>
                                    <td><span>Genre:</span> ${sessionScope.selectedMovie.getGenre()}</td>
                                    <td><span>Actors:</span> ${sessionScope.selectedMovie.getActors()}</td>
                                </tr>
                                <tr>
                                    <td><span>Writer:</span> ${sessionScope.selectedMovie.getWriter()}</td>
                                    <td><span>Directors:</span>${sessionScope.selectedMovie.getDirector()}</td>
                                </tr>
                                <tr>
                                    <td><span>Type:</span> ${sessionScope.selectedMovie.getType()}</td>
                                    <td><span>Language:</span> ${sessionScope.selectedMovie.getLanguage()}</td>
                                </tr>
                                <tr>
                                    <td><span>Country:</span> ${sessionScope.selectedMovie.getCountry()}</td>
                                    <td><span>Awards:</span> ${sessionScope.selectedMovie.getAwards()}</td>
                                </tr>
                                <tr>
                                    <td><span>Metascore:</span> ${sessionScope.selectedMovie.getMetascore()}</td>
                                    <td><span>imdbRating:</span> ${sessionScope.selectedMovie.getImdbRating()}</td>
                                </tr>
                                <tr>
                                    <td><span>imdbVotes:</span> ${sessionScope.selectedMovie.getImdbVotes()}</td>
                                    <td><span>imdbID:</span> ${sessionScope.selectedMovie.getImdbID()}</td>
                                </tr>
                                <tr>
                                    <td><span>imdbVotes:</span> ${sessionScope.selectedMovie.getImdbVotes()}</td>
                                    <td><span>imdbID:</span> ${sessionScope.selectedMovie.getImdbID()}</td>
                                </tr>
                            </tbody>
                        </table>   
                    </div>
                    <div class="plotRatingsContainer">
                        <span>Plot: <p>${sessionScope.selectedMovie.getPlot()}</p></span>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
