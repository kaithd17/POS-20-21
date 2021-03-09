<%-- 
    Document   : rssView
    Created on : 02.03.2021, 10:36:42
    Author     : kainz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RSS-Feeds</title>
    </head>
    <body>
        <form action="RSSController" method="POST">
            <button type="submit" value="Sport" name="buttonClick">Presse</button>
            <div class="feedContainer">
                <div class="feedObject">
                    <table border="0">
                        <tbody>
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </div>
            <p>${sessionScope.rssObject}</p>
        </form>
    </body>
</html>
