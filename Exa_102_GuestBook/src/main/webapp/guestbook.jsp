<%-- 
    Document   : guestbook
    Created on : 24.09.2020, 08:41:49
    Author     : kainz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="beans.GuestbookEntry"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="guestbook.js" type="text/javascript"></script>
    </head>
    <body style="background-color: lightgray">
        <%-- <select name="Test">
        <c:forEach var="i" begin="1" end="10">
            <option value="${i}">${i}</option>
        </c:forEach>
    </select> --%> 
        <h1>Welcome to our guestbook</h1>
    </body>
    <form action="./GuestbookController" method="POST" onsubmit="return vlaidate();">
        <table border="0">
            <tbody>
                <tr>
                    <td>Nickname</td>
                    <td><input type="text" name="nickname" value="Spiderman" id="nickname"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email" value="spiderman@marvel.com" id="email" /></td>
                </tr>
                <tr>
                    <td>Comment:</td>
                    <td>
                        <textarea name="comment" rows="4" cols="20" id="comment">
Great guestbook
                        </textarea>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="send" /></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </form>
    <br/>
    <hr/>
    <br/>
    <%--
        List<GuestbookEntry> entries = (ArrayList) request.getAttribute("guestbookEntries");
        if (entries != null) {
            for (GuestbookEntry entry : entries) {
                out.println(entry);
            }
        }

    --%>
    <c:forEach var="entry" items="${requestScope.guestbookEntries}">
        <c:if test="${entry.nickname == 'Spiderman'}">
            ${entry.nickname} says: ${entry.comment} <br/>
        </c:if>
    </c:forEach>
</html>
