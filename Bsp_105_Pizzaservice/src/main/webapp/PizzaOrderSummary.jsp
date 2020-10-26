<%-- 
    Document   : PizzaOrderSummary.jsp
    Created on : 07.10.2020, 11:30:51
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
        <title>Pizzeria di Kainzi</title>
        <link rel="stylesheet" href="stylesSummaryScreen.css">
    </head>
    <body>
        <h1>Pizzeria di Kainzi</h1>
        <form action="./PizzaOrderController" method="POST">
            <div class="pizzaOrder">
                <h2>Ihre Bestellung: </h2>
                <%
                    List<Pizza> pizzaOrder = (ArrayList) request.getAttribute("pizzaOrder");
                    String deliveryAddress = (String) request.getAttribute("deliveryAddress");
                    double result = 0.0;
                    out.println("<br/>");
                    out.println("<table border='0'");
                    out.println("<tbody>");
                    for (Pizza pizza : pizzaOrder) {
                        out.println("<tr>");
                        out.println(String.format("<td><span>%s</span>&nbsp;</td>", pizza.getName()));
                        out.println(String.format("<td><span>%.2f€</span>&nbsp;</td>", pizza.getPrice()));
                        out.println(String.format("<td><span>%dx</span>&nbsp;</td>", pizza.getOrder()));
                        out.println(String.format("<td><span>%.2f€</span>&nbsp;</td>", (pizza.getPrice() * pizza.getOrder())));
                        out.println("</tr>");
                        result += pizza.getPrice() * pizza.getOrder();
                    }
                    out.println("</tbody>");
                    out.println("</table>");
                    out.println(String.format("<p class='goRight'>Summe: %.2f€</p>", result));
                    out.println(String.format("<p>Lieferadresse: %s</p>", deliveryAddress));
                    out.println("<a href='./PizzaOrderController' class='backButton'>Zurück</a>");
                %>
            </div>
        </form>
    </body>
</html>
