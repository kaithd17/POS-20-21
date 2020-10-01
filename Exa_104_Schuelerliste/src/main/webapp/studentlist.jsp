<%-- 
    Document   : studentlist
    Created on : 28.09.2020, 08:36:52
    Author     : kainz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Studentlist</title>
        <link rel="stylesheet" href="stylesheet.css">
    </head>
    <body>
        <h1>Welcome to my studentlist</h1>
    </body>
    <form action="./StudentController">
        <div class="list">
            <div class="filter">
                <table border="0" class="filterTable">
                    <tbody>
                        <tr>
                            <td>Filter: </td>
                            <td><input type="text" name="filterText" value="" size="50"/></td>
                            <td><input type="submit" value="Filter setzen" class="button"/></td>
                            <td><input type="submit" value="Filter entfernen" class="button"/></td>
                        </tr>
                        <tr>
                            <td>Schüler auswählen:</td>
                            <td><select name="studentselector">
                                    <option>Thomas Kainz</option>
                                    <option>Martin Trummer</option>
                                </select></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="details">
                <div class="image">
                    <img src="at.kaindorf.images/profile.png" width="90px" height="90px">
                </div>
                <table border="0" class="detailsTable">
                    <tbody>
                        <tr>
                            <td>Name:</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Katalognummer:</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Klasse:</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Geschlecht:</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Geburtsdatum:</td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>

            </div> 
        </div>



    </form>
</html>
