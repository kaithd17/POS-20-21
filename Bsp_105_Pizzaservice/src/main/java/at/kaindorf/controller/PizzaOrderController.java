/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.controller;

import at.kaindorf.beans.Pizza;
import at.kaindorf.io.IO_Handler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private List<Pizza> pizzaList = new ArrayList<>();
    
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
        String relativePath = this.getServletContext().getRealPath("/at.kaindorf.res/pizza.csv");
        try {
            pizzaList = IO_Handler.getAllPizzas(relativePath);
        } catch (FileNotFoundException ex) {
            System.out.println("File failure");
        }
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("pizzaList", pizzaList);
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
        List<Pizza> pizzaOrder = new ArrayList<>();
        for (Pizza pizza : pizzaList) {
            int order = Integer.parseInt(request.getParameter(String.format("%sOrder", pizza.getName())));
            if (order > 0) {
                pizza.setOrder(order);
                pizzaOrder.add(pizza);
               // pizzaMap.put(pizza.getName(), order);
            }
        }
        String deliveryAddress = request.getParameter("inputField");
        if (buttonText != null) {
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
