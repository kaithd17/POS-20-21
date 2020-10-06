<%-- 
    Document   : studentlist
    Created on : 28.09.2020, 08:36:52
    Author     : kainz
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
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
                                <td><input type="text" name="filterText" value="<% String filter = (String) request.getAttribute("filterText");
                                    if (filter != null) {
                                        out.println(filter);
                                    }%>" size="50"/></td>
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
                                            Student studentDetails = new Student();
                                            if (studentList != null) {
                                                try {
                                                    studentDetails = studentList.get(0);
                                                } catch (IndexOutOfBoundsException ex) {

                                                }
                                                for (Student student : studentList) {
                                                    selected = "";
                                                    String name = student.getLastname()+ " " + student.getFirstname();
                                                    if (studentName != null && studentName.equals(name)) {
                                                        selected = "selected";
                                                        studentDetails = student;
                                                    }
                                                    out.println(String.format("<option %s>%s %s</option>", selected, student.getLastname(), student.getFirstname()));
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
                            <%
                                if (studentDetails != null && studentDetails.getCatalognr() != 0) {
                                    String template = "<tr> <td>%s: %s</td></tr>";
                                    final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                                    out.println(String.format(template, "Name", studentDetails.getLastname() + " " + studentDetails.getFirstname()));
                                    out.println(String.format(template, "Katalognummer", studentDetails.getCatalognr() + ""));
                                    out.println(String.format(template, "Klasse", studentDetails.getClassName()));
                                    out.println(String.format(template, "Geschlecht", studentDetails.getGender()));
                                    out.println(String.format(template, "Geburtsdatum", DTF.format(studentDetails.getBirthdate())));
                                } else {
                                    String template = "<tr> <td>%s:</td></tr>";
                                    out.println(String.format(template, "Name"));
                                    out.println(String.format(template, "Katalognummer"));
                                    out.println(String.format(template, "Klasse"));
                                    out.println(String.format(template, "Geschlecht"));
                                    out.println(String.format(template, "Geburtsdatum"));
                                }


                            %>
                        </tbody>
                    </table>

                </div> 
            </div>
        </form>
    </body>

</html>
