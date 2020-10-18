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
        <link rel="stylesheet" href="stylesSummary.css">
    </head>
    <body>
        <h1>Pizzeria di Kainzi</h1>
        <form action="./PizzaOrderController" method="POST">
            <div class="header">
                <span class="item1">Pizza</span>
                <span class="item2">Pr.</span>
                <span class="item3">Stk.</span>
                <span class="item4"> Ges.</span>
            </div>
            <div class="PizzaOrder">
                <%
                    List<Pizza> pizzaOrder = (ArrayList) request.getAttribute("pizzaOrder");
                    String deliveryAddress = (String) request.getAttribute("deliveryAddress");
                    double result = 0.0;
                    for (Pizza pizza : pizzaOrder) {
                            out.println(String.format("<br/>"));
                            out.println(String.format("<span>%s</span>", pizza.getName()));
                            out.println(String.format("<span>%.2f€</span>", pizza.getPrice()));
                            out.println(String.format("<span>%d</span>", pizza.getOrder()));
                            out.println(String.format("<span>%.2f€</span>", (pizza.getPrice() * pizza.getOrder())));
                            result += pizza.getPrice() * pizza.getOrder();
                        }
                    out.println(String.format("<p>Summe: %.2f€</p>", result));
                    out.println(String.format("<p>Lieferadresse: %s</p>", deliveryAddress));
                    out.println("<input  type = 'submit' value = 'Zurück' / >");
                %>
            </div>
        </form>
    </body>
</html>
