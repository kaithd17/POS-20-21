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
        <title>JSP Page</title>
    </head>
    <body>
        <form action="./PizzaOrderController" method="POST">
            <%
                List<Pizza> pizzaList = (ArrayList) request.getAttribute("pizzaList");
                for (Pizza pizza : pizzaList) {
                        out.println(pizza);
                    }
            %>
        </form>
    </body>
</html>
