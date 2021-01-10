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
                <input type="text" name="searchField" value="" class="textfield"/>
                <input type="submit" value="Search" name="buttonClick" class="buttonClass2"/>
            </div>

            <c:if test="${sessionScope.movieSelected}">
                <div class="controllerContainer">
                    <div class="controller">
                        <div class="filterSortContainer">
                            <div class="sortSection">
                                <select name="sortList" class="selectors">
                                    <option><c:out value="<None>"/></option>
                                    <c:choose>
                                        <c:when test="${sessionScope.sortCondition == 'Title'}">
                                            <option selected>Title</option>
                                            <option>Realease</option>
                                        </c:when>
                                        <c:when test="${sessionScope.sortCondition == 'Realease'}">
                                            <option>Title</option>
                                            <option selected>Realease</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>Title</option>
                                            <option>Realease</option>
                                        </c:otherwise>
                                    </c:choose>

                                </select>
                                <div class="buttonSort">
                                    <input type="submit" value="Sort" name="buttonClick" class="buttonClass"/>
                                </div>
                            </div>
                            <div class="filterSection">
                                <select name="filterList" class="selectors">
                                    <option><c:out value="<None>"/></option>
                                    <c:forEach var="genre" items="${sessionScope.genreSet}">
                                        <c:choose>
                                            <c:when test="${sessionScope.filterCondition == genre}">
                                                <option selected>${genre}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>${genre}</option>
                                            </c:otherwise>
                                        </c:choose>

                                    </c:forEach>
                                </select>
                                <div class="buttonFilter">
                                    <input type="submit" value="Filter" name="buttonClick" class="buttonClass"/>
                                </div>
                            </div>
                        </div>
                        <div class="pageContainer">
                            <table>
                                <tbody>
                                    <tr>
                                        <td>Page: </td>
                                        <td><select name="PageList" class="selectors">
                                                <c:forEach var="i" begin="1" end="${sessionScope.getPages}">
                                                    <c:choose>
                                                        <c:when test="${sessionScope.page == i}">
                                                            <option selected>${i}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option>${i}</option>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </select></td>
                                        <td>
                                            <input type="submit" value="Submit" name="buttonClick" class="buttonClass"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="movieListContainer">
                <div class="movieList">
                    <c:forEach var="movie" items="${sessionScope.movieList}" varStatus="counter">
                        <div class="movieObject">
                            <table border="0" >
                                <tbody>
                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test="${movie.getPoster() != 'N/A'}">
                                                    <img src='${movie.getPoster()}'>
                                                </c:when>
                                                <c:otherwise>
                                                    <img src='https://davidkoepp.com/wp-content/themes/blankslate/images/Movie%20Placeholder.jpg'>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
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
                                <button type="submit" value="${counter.index}" name="buttonClick" class="buttonClass">Details</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </form>
    </body>
</html>
