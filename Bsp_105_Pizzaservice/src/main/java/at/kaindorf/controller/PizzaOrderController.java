/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.controller;

import at.kaindorf.beans.Pizza;
import at.kaindorf.bl.Language;
import at.kaindorf.bl.LanguageSelector;
import at.kaindorf.io.IO_Handler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kainz
 */
@WebServlet(name = "PizzaOrderController", urlPatterns = {"/PizzaOrderController"})
public class PizzaOrderController extends HttpServlet {

    private List<Pizza> pizzaListDE = new ArrayList<>();
    private List<Pizza> pizzaListEN = new ArrayList<>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config);
        String relativePath = this.getServletContext().getRealPath("/at.kaindorf.res/pizza_de.csv");
        String relativePath2 = this.getServletContext().getRealPath("/at.kaindorf.res/pizza_en.csv");
        try {
            pizzaListDE = IO_Handler.getAllPizzas(relativePath);
            pizzaListEN = IO_Handler.getAllPizzas(relativePath2);
        } catch (FileNotFoundException ex) {
            System.out.println("File failure");
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("pizzaListDE", pizzaListDE);
        request.setAttribute("pizzaListEN", pizzaListEN);
        request.getRequestDispatcher("PizzaOrder.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String buttonText = request.getParameter("Order");
        //HashMap <String, Integer> pizzaMap = new HashMap<String, Integer>();

        if (buttonText != null) {
            String deliveryAddress = request.getParameter("inputField");
            List<Pizza> pizzaOrder = new ArrayList<>();
            List<Pizza> pizzaList = new ArrayList<>(pizzaListDE);
            Language language = LanguageSelector.getLanguage(request, response);
            if (language == Language.EN) {
                pizzaList.clear();
                pizzaList.addAll(pizzaListEN);
            }
            for (Pizza pizza : pizzaList) {
                try {
                    int order = Integer.parseInt(request.getParameter(String.format("%sOrder", pizza.getName())));
                    if (order > 0) {
                        pizza.setOrder(order);
                        pizzaOrder.add(pizza);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Is not a number");
                }
            }
            request.setAttribute("pizzaOrder", pizzaOrder);
            request.setAttribute("deliveryAddress", deliveryAddress);
            request.getRequestDispatcher("PizzaOrderSummary.jsp").forward(request, response);
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
