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
            <div class="sortFilterContainer">
                <div class="borderContainer">
                    <table border="0">
                        <tbody>
                            <tr>
                                <td class="labelText">Name:</td>
                                <td><input type="text" name="name" value="" class="textfield"/></td>
                            </tr>
                            <tr>
                                <td class="labelText">Company:</td>
                                <td>
                                    <select name="companySelector" class="selectors">
                                        <option><c:out value="<None>"/></option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="labelText">Gender:</td>
                                <td>
                                    <select name="genderSelector" class="selectors">
                                        <option><c:out value="<None>"/></option>
                                        <option>Male</option>
                                        <option>Female</option>
                                    </select>
                                </td>
                                <td><input type="submit" value="Filter" name="filter" class="buttonClass"/></td>
                            </tr>
                            <tr>
                                <td class="labelText">Sort By: </td>
                                <td>
                                    <select name="sortSelector" class="selectors">
                                        <option><c:out value="<None>"/></option>
                                        <option>Name</option>
                                        <option>Name+Company</option>
                                        <option>Age</option>
                                    </select>
                                </td>
                                <td><input type="submit" value="Sort" name="sort" class="buttonClass"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <br>
                    <div class="buttonBorder">
                        <input type="submit" value="set Favourite" name="favourite" class="buttonClass"/>
                        <input type="submit" value="Delete" name="delete" class="buttonClass"/>
                        <input type="submit" value="Save Favourite" name="save" class="buttonClass"/>
                    </div>
                </div>
            </div>
            <div class="contactTableContainer">
                <table border="0" class="contactTable">
                    <thead>
                        <tr>
                            <th class="tableHeader"></th>
                            <th class="tableHeader">Firstname</th>
                            <th class="tableHeader">Lastname</th>
                            <th class="tableHeader">Email</th>
                            <th class="tableHeader">Birthday</th>
                            <th class="tableHeader">Company</th>
                            <th class="tableHeader">Stockmarket</th>
                            <th class="tableHeader">Favourites</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="contact" items="${sessionScope.contactList}" varStatus="counter">
                            <tr>
                                <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}><input type="checkbox" name="userPick" value="${contact.getId()}" /></td>
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