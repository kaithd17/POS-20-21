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
                                <c:choose>
                                    <c:when test="${conditionList.get(0) != ''}">
                                        <td><input type="text" name="filter" value="${conditionList.get(0)}" class="textfield"/></td>
                                        </c:when>
                                        <c:otherwise>
                                        <td><input type="text" name="filter" value="" class="textfield"/></td>
                                        </c:otherwise>
                                    </c:choose>
                            </tr>
                            <tr>
                                <td class="labelText">Company:</td>
                                <td>
                                    <select name="filter" class="selectors">
                                        <option><c:out value="<None>"/></option>
                                        <c:forEach var="company" items="${sessionScope.companySet}">
                                            <c:choose>
                                                <c:when test="${company.getName() == conditionList.get(1)}">
                                                    <option selected>${company.getName()}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option>${company.getName()}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="labelText">Gender:</td>
                                <td>
                                    <select name="filter" class="selectors">
                                        <c:choose>
                                            <c:when test="${conditionList.get(2) == 'Male'}">
                                                <option><c:out value="<None>"/></option>
                                                <option selected>Male</option>
                                                <option>Female</option>
                                            </c:when>
                                            <c:when test="${conditionList.get(2) == 'Female'}">
                                                <option><c:out value="<None>"/></option>
                                                <option>Male</option>
                                                <option selected>Female</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option selected><c:out value="<None>"/></option>
                                                <option>Male</option>
                                                <option>Female</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </td>
                                <td><input type="submit" value="Filter" name="buttonClick" class="buttonClass"/></td>
                            </tr>
                            <tr>
                                <td class="labelText">Sort By: </td>
                                <td>
                                    <select name="sortSelector" class="selectors">
                                        <c:choose>
                                            <c:when test="${sortCondition == 'Name'}">
                                                <option><c:out value="<None>"/></option>
                                                <option selected>Name</option>
                                                <option>Name+Company</option>
                                                <option>Age</option>
                                            </c:when>
                                            <c:when test="${sortCondition == 'Name+Company'}">
                                                <option><c:out value="<None>"/></option>
                                                <option>Name</option>
                                                <option selected>Name+Company</option>
                                                <option>Age</option>
                                            </c:when>
                                            <c:when test="${sortCondition == 'Age'}">
                                                <option><c:out value="<None>"/></option>
                                                <option>Name</option>
                                                <option>Name+Company</option>
                                                <option selected>Age</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option selected><c:out value="<None>"/></option>
                                                <option>Name</option>
                                                <option>Name+Company</option>
                                                <option>Age</option>
                                            </c:otherwise>
                                        </c:choose>

                                    </select>
                                </td>
                                <td>
                                    <input type="submit" value="Sort" name="buttonClick" class="buttonClass"/>
                                    <input type="submit" value="Sort Reverse" name="buttonClick" class="buttonClass"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <br>
                    <div class="buttonBorder">
                        <input type="submit" value="Set Favourite" name="buttonClick" class="buttonClass"/>
                        <input type="submit" value="Delete" name="buttonClick" class="buttonClass"/>
                        <input type="submit" value="Save Favourite" name="buttonClick" class="buttonClass"/>
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
                                <td class=${counter.index%2 == 0 ? "contactElement" : "contactElementGray"}><input type="checkbox" name="${String.format("userPick")}" value="${contact.getId()}" /></td>
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
                                        <td class=${counter.index%2 == 0 ? "contactElementStar" : "contactElementGrayStar"}><i class="fas fa-star"></i></td>  
                                        </c:when>
                                        <c:otherwise>
                                        <td class=${counter.index%2 == 0 ? "contactElementStar" : "contactElementGrayStar"}></td>  
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