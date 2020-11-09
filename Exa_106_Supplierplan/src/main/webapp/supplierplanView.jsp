<%-- 
    Document   : supplierplanView
    Created on : 04.11.2020, 11:32:19
    Author     : kainz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="at.kaindorf.bl.Day"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>Supplierplan</title>
        <script src="supplierplan.js" type="text/javascript"></script>
    </head>
    <body>
        <form action="./SupplierplanController" method="POST">
            <h1>Supplierplan</h1>
            <p class="className">
                <c:out value="${applicationScope.className}"/>
            </p>
            <div class="header">
                <table border="0">
                    <tbody>
                        <tr>
                            <td><span>Tag: </span></td>
                            <td>
                                <select name="days">
                                    <c:forEach var="day" items="${applicationScope.daysOfTheWeek}">
                                        <option>${day.getNameOfDay()}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Stunde:</td>
                            <td>                               
                                <select name="lesson">
                                    <c:forEach var="i" begin="1" end="10">
                                        <option>${i}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Fach:</td>
                            <td><input type="text" name="subjectField"/></td>
                        </tr>
                        <tr>
                            <td>Lehrer:</td>
                            <td><input type="text" name="teacherField"/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Übernehmen" class="buttonSubmit"/></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="timetableContainer">
                <table border="0" class="timetable">
                    <thead>
                        <tr>
                            <th class="timetableHeader"></th>
                                <c:forEach var="dayToken" items="${applicationScope.daysOfTheWeek}">
                                <th class="timetableHeader">${dayToken.getDayToken()}</th>
                                </c:forEach>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="key" items="${applicationScope.timeTableMap.keySet()}" varStatus="counter">
                            <c:if test="${counter.index%5 == 0}">
                                <tr>
                                    <td class="timetableHeader">${String.format("%.0f",counter.index/5+1)}</td>
                            </c:if>
                                <td class="subjectElement">
                                    <c:choose>
                                        <c:when test="${applicationScope.timeTableMap.get(key).isSupplierung()}">
                                            <p class="subjectSubstitution"><c:out value="${applicationScope.timeTableMap.get(key).getSubject()}"/></p>
                                            <p class="teacherSubstitution"><c:out value="${applicationScope.timeTableMap.get(key).formatTeachers()}"/></p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="subject"><c:out value="${applicationScope.timeTableMap.get(key).getSubject()}"/></p>
                                            <p class="teacher"><c:out value="${applicationScope.timeTableMap.get(key).formatTeachers()}"/></p>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                             <c:if test="${counter.index%5 == 0 && counter.index%5 != 0}">
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>

