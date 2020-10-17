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
            <div class="gridContainer">
                <div class="main">
                    <table border='0'>
                        <tbody>
                            <%
                                List<Pizza> pizzaList = (ArrayList) request.getAttribute("pizzaList");

                                for (Pizza pizza : pizzaList) {
                                    out.println(String.format("<tr>"
                                            + "<td><img src='%s' width='75px' height='75px'>&nbsp</td>"
                                            + "<td><p class='pizza'>%s - %.2f€</p><p class='ingredients'>%s</p></td>"
                                            + "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='number' value='0' min='0' max='10' class='selector' id='%sOrder'/></td></tr>", pizza.getImage(), pizza.getName(), pizza.getPrice(), pizza.getIngredients(),pizza.getName().toLowerCase()));
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                <div class="right">
                    <p>Lieferaddresse:</p>
                    <input type="text" name="inputFiled" class="inputField"  />
                    <input type="submit" value="Bestellen" class="orderButton"/>
                </div>
            </div>
        </form>
    </body>
</html>
