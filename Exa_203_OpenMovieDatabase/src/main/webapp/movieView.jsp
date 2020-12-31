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
                <h1>MovieDB</h1>
            </div>

            <div class="controllerContainer">
                <div class="controller">
                    <div class="searchContainer">
                        <input type="text" name="searchField" value="" class=/>
                        <input type="submit" value="Search" />
                    </div>
                    <div class="filterSortContainer">
                        <div class="sortSection">
                            <select name="sortList">
                                <option>f</option>
                                <option>f</option>
                            </select>
                            <input type="submit" value="Sort" />
                        </div>
                        <div class="filterSection">
                            <select name="filterList">
                                <option>f</option>
                                <option>f</option>
                            </select>
                            <input type="submit" value="Filter" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="movieListContainer">
                <div class="movieList">
                    <c:forEach var="movie" items="${applicationScope.movieList}">
                        <div class="movieObject">
                            <table border="0" >
                                <tbody>
                                    <tr>
                                        <td><img src='${movie.getPoster()}'></td>
                                        <td class="objectText">
                                            <p>${movie.getTitle()}</p>
                                            <p>${movie.getYear()}</p>
                                            <p>${movie.getGenre()}</p>
                                            <p>${movie.getRuntime()}</p>
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
