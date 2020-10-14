<%-- 
    Document   : PizzaOrder
    Created on : 07.10.2020, 11:29:50
    Author     : kainz
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="at.kaindorf.beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizzeria</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <h1>Pizzeria di Kainzi</h1>
        <form action="./PizzaOrderController" method="POST">
            <table border='0'>
                <tbody>
                    <%
                        List<Pizza> pizzaList = (ArrayList) request.getAttribute("pizzaList");
                        
                        for (Pizza pizza : pizzaList) {
                            out.println(String.format("<tr>"
                                                    + "<td><img src='%s' width='50px' height='50px'></td>"
                                                    + "<td><p class='pizza'>%s - %.2f€</p><p class='ingredients'>%s</p></td>"
                                                    + "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='number' value='0' min='0' max='10' class='selecter'/></td></tr>",pizza.getImage(), pizza.getName(),pizza.getPrice(),pizza.getIngredients()));
                        }
                    %>
                </tbody>
            </table>
        </form>
    </body>
</html>
