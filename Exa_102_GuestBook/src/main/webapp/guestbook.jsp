<%-- 
    Document   : guestbook
    Created on : 24.09.2020, 08:41:49
    Author     : kainz
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="beans.GuestbookEntry"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color: lightgray">
        <h1>Welcome to our guestbook</h1>
    </body>
    <form action="./GuestbookController" method="POST">
        <table border="0">
            <tbody>
                <tr>
                    <td>Nickname</td>
                    <td><input type="text" name="nickname" value="Spiderman" /></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email" value="spiderman@marvel.com" /></td>
                </tr>
                <tr>
                    <td>Comment:</td>
                    <td>
                        <textarea name="comment" rows="4" cols="20">
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
    <%
        List<GuestbookEntry> entries = (ArrayList) request.getAttribute("guestbookEntries");
        if (entries != null) {
            for (GuestbookEntry entry : entries) {
                out.println(entry);
            }
        }

    %>
</html>
