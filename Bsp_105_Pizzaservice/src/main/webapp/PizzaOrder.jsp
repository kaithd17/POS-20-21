<%-- 
    Document   : PizzaOrder
    Created on : 07.10.2020, 11:29:50
    Author     : kainz
--%>

<%@page import="at.kaindorf.bl.Language"%>
<%@page import="at.kaindorf.bl.LanguageSelector"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="at.kaindorf.beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizzeria di Kainzi</title>
        <link rel="stylesheet" href="styles.css">
        <script src="PizzaOrder.js" type="text/javascript"></script>
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
        <form action="./PizzaOrderController" method="POST" onsubmit="return valid()">
            <div class="gridContainer">
                <div class="main">
                    <table border='0'>
                        <tbody>
                            <%
                                List<Pizza> pizzaList = (ArrayList) request.getAttribute("pizzaList" + language.getLanguageCode());
                                for (Pizza pizza : pizzaList) {
                                    out.println(String.format("<tr>"
                                            + "<td><img src='%s' width='75px' height='75px'>&nbsp</td>"
                                            + "<td><p class='pizza'>%s - %.2f€</p><p class='ingredients'>%s</p></td>"
                                            + "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='number' value='0' min='0' max='10' class='selector' name='%sOrder'/></td></tr>", pizza.getImage(), pizza.getName(), pizza.getPrice(), pizza.getIngredients(), pizza.getName()));
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                <div class="right" id="right">
                    <%
                    String address = "Lieferadresse";
                    String order = "Bestellen";
                    if (language == Language.EN) {
                            address = "Delivery address";
                            order = "Order";
                        }
                    out.println(String.format("<p>%s:</p>", address));
                    %>
                    
                    <input type="text" name="inputField" class="inputField" id="inputField"  />
                    <%
                        out.println(String.format("<input type='submit' value='%s' name='Order' class='orderButton'/>", order));
                    %>
                    
                </div>
            </div>
        </form>
    </body>
</html>
