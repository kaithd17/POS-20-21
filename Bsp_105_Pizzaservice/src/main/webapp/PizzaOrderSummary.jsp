<%-- 
    Document   : PizzaOrderSummary.jsp
    Created on : 07.10.2020, 11:30:51
    Author     : kainz
--%>

<%@page import="at.kaindorf.bl.LanguageSelector"%>
<%@page import="at.kaindorf.bl.Language"%>
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
        <script>
            function changeLanguage() {
                let lang = document.getElementById('language').value;
                document.cookie = "lang=" + lang + ";";
                location.reload();
            }
        </script>
    </head>
    <body>
        <%
            Language language = LanguageSelector.getLanguage(request, response);
        %>
        <div class="header">
            <h1>Pizzeria di Kainzi</h1>
            <select name="language" class="language-selector" id="language" onchange="changeLanguage()">
                <option><%
                    out.println(language.getLanguageName());
                    %></option>
                    <%
                        for (Language value : Language.values()) {

                            if (!value.equals(language)) {
                                out.println("<option value='" + value.getLanguageCode() + "'>"
                                        + value.getLanguageName()
                                        + "</option>");
                            }
                        }
                    %>
            </select>
        </div>
        <form action="./PizzaOrderController" method="POST">
            <div class="pizzaOrder">
                <%
                    String text = "Ihre Bestellung";
                    String back = "Zurück";
                    String sum = "Summe";
                    String address = "Lieferadresse";
                    if (language == Language.EN) {
                        address = "Delivery address";
                        back = "Back";
                        text = "Your Order";
                        sum = "Total";
                    }
                    out.println(String.format("<h2>%s: </h2>", text));
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
                    out.println(String.format("<p class='goRight'>%s: %.2f€</p>",sum, result));
                    out.println(String.format("<p>%s: %s</p>",address, deliveryAddress));
                    out.println(String.format("<a href='./PizzaOrderController' class='backButton'>%s</a>", back));
                %>
            </div>
        </form>
    </body>
</html>
