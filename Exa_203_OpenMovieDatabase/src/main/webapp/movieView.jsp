<%-- 
    Document   : movieView
    Created on : 22.12.2020, 10:19:44
    Author     : kainz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MovieDB</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <form action="./MovieController" method="POST">
            <div class="header">
                <c:choose>
                    <c:when test="${sessionScope.movieSelected}">
                        <h1>${sessionScope.userInput}</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>MovieDB</h1>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="searchContainer">
                <input type="text" name="searchField" value=""/>
                <input type="submit" value="Search" name="buttonClick"/>
            </div>

            <div class="controllerContainer">
                <div class="controller">
                    <c:if test="${sessionScope.movieSelected}">
                        <div class="filterSortContainer">
                            <div class="sortSection">
                                <select name="sortList">
                                    <option><c:out value="<None>"/></option>
                                    <option>Title</option>
                                    <option>Realease</option>
                                </select>
                                <div class="buttonSort">
                                    <input type="submit" value="Sort" name="buttonClick" class="buttonClass"/>
                                </div>
                            </div>
                            <div class="filterSection">
                                <select name="filterList">
                                    <option><c:out value="<None>"/></option>
                                    <c:forEach var="genre" items="${sessionScope.genreSet}">
                                        <option>${genre}</option>
                                    </c:forEach>
                                </select>
                                <div class="buttonFilter">
                                    <input type="submit" value="Filter" name="buttonClick" class="buttonClass"/>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>

            <div class="movieListContainer">
                <div class="movieList">
                    <c:forEach var="movie" items="${sessionScope.movieList}">
                        <div class="movieObject">
                            <table border="0" >
                                <tbody>
                                    <tr>
                                        <td><img src='${movie.getPoster()}'></td>
                                        <td>
                                            <table border="0" class="textTable">
                                                <thead>
                                                    <tr>
                                                        <th>Title</th>
                                                        <th>Year</th>
                                                        <th>Genre</th>
                                                        <th>Runtime</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="textSpace"><p class="textClass">${movie.getTitle()}</p></td>
                                                        <td class="textSpace"><p class="textClass">${movie.getYear()}</p></td>
                                                        <td class="textSpace"><p class="textClass">${movie.getGenre()}</p></td>
                                                        <td class="textSpace"><p class="textClass">${movie.getRuntime()}</p></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </td>                                     
                                    </tr>
                                </tbody>
                            </table>
                            <div class="buttonDirection">
                                <input type="submit" value="Details"/>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </form>
    </body>
</html>
