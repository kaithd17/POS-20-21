<%-- 
    Document   : supplierplanView
    Created on : 04.11.2020, 11:32:19
    Author     : kainz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>Supplierplan</title>
    </head>
    <body>
        <form action="./SupplierplanController" method="POST">
            <h1>Supplierplan</h1>
            <p class="className">Klasse</p>
            <div class="header">
                <table border="0">
                    <tbody>
                        <tr>
                            <td><span>Tag: </span></td>
                            <td>
                                <select name="days">
                                    <option>Montag</option>
                                    <option>Dienstag</option>
                                    <option>Mittwoch</option>
                                    <option>Donnerstag</option>
                                    <option>Freitag</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Stunde:</td>
                            <td>                               
                                <select name="days">
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
                            <th class="timetableHeader">MO</th>
                            <th class="timetableHeader">DI</th>
                            <th class="timetableHeader">MI</th>
                            <th class="timetableHeader">DO</th>
                            <th class="timetableHeader">FR</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="i" begin="1" end="10">
                            <tr>
                                <td class="timetableHeader">${i}</td>
                                <c:forEach var="j" begin="1" end="5">
                                    <td class="subjectElement">
                                        <p>text</p>
                                        <p>text2</p>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>

            </div>
        </form>
    </body>
</html>
