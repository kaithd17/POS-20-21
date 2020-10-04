<%-- 
    Document   : studentlist
    Created on : 28.09.2020, 08:36:52
    Author     : kainz
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="at.kaindorf.beans.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Studentlist</title>
        <link rel="stylesheet" href="stylesheet.css">
    </head>
    <body>
        <form action="./StudentController" method="POST">
            <div class="list">
                <div class="filter">
                    <table border="0" class="filterTable">
                        <tbody>
                            <tr>
                                <td>Filter: </td>
                                <td><input type="text" name="filterText" value=<% out.println(request.getParameter("filterText") == null ? "\"\"" : "\"" + (String)request.getParameter("filterText") + "\""); %> size="50"/></td>
                                <td><input type="submit" value="Filter setzen" class="button" name="setzen"/></td>
                                <td><input type="submit" value="Filter entfernen" class="button" name="entfernen"/></td>
                            </tr>
                            <tr>
                                <td>Schüler auswählen:</td>
                                <td><select name="studentselector" class="selector" onchange="submit()">
                                        <%
                                            List<Student> studentList = (ArrayList) request.getAttribute("studentList");
                                            String studentName = (String) request.getAttribute("currentStudent");
                                            System.out.println(studentName);
                                            String selected = "";
                                            if (studentList != null) {
                                                for (Student student : studentList) {
                                                    selected = "";
                                                    if (studentName != null && student.getFirstname().equals(studentName.split(" ")[0]) && student.getLastname().equals(studentName.split(" ")[1])) {
                                                            selected = "selected";
                                                        }
                                                    out.println(String.format("<option %s>%s %s</option>", selected, student.getFirstname(), student.getLastname()));
                                                }
                                            }
                                        %>
                                    </select></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="details">
                    <div class="image">
                        <img src="at.kaindorf.images/cap.png" width="110px" height="110px">
                    </div>
                    <table border="0" class="detailsTable">
                        <tbody>
                            <tr>
                                <td>Name:</td>
                            </tr>
                            <tr>
                                <td>Katalognummer:</td>
                            </tr>
                            <tr>
                                <td>Klasse:</td>
                            </tr>
                            <tr>
                                <td>Geschlecht:</td>
                            </tr>
                            <tr>
                                <td>Geburtsdatum:</td>
                            </tr>
                        </tbody>
                    </table>

                </div> 
            </div>
        </form>
    </body>

</html>
