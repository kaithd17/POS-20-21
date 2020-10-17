<%-- 
    Document   : PizzaOrderSummary.jsp
    Created on : 07.10.2020, 11:30:51
    Author     : kainz
--%>

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
                <p></p>
            </div>
        </form>
    </body>
</html>
