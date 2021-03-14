/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.controller;

import at.kaindorf.xml.XMLAccess;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import at.kaindorf.pojos.Rss;
import java.util.ArrayList;
import java.util.List;
import at.kaindorf.pojos.Item;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 *
 * @author kainz
 */
@WebServlet(name = "RSSController", urlPatterns = {"/RSSController"})
public class RSSController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("rssView.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonText = request.getParameter("buttonClick");
        List<Item> allItems = new ArrayList<>();
        Rss rssFeed = new Rss();

        if (buttonText.contains("hide")) {
            final String finalStr = buttonText.replace("hide", "");
            //get Feed
            rssFeed = (Rss) request.getSession().getAttribute("rssFeed");
            rssFeed.getChannel().getItemList().removeIf(r -> r.getId() == Integer.parseInt(finalStr));

            //set Attributes
            request.getSession().setAttribute("rssFeed", rssFeed);
        } else if (buttonText.contains("read")) {
            String finalStr = buttonText.replace("read", "");
            //get Feed
            rssFeed = (Rss) request.getSession().getAttribute("rssFeed");
            allItems = (List<Item>) request.getSession().getAttribute("allItems");
            System.out.println(rssFeed.getChannel().getItemList());

            for (Item item : rssFeed.getChannel().getItemList()) {
                if (item.getId() == Integer.parseInt(finalStr)) {
                    System.out.println(item.isRead());
                    if (item.isRead()) {
                        item.setRead(false);
                    } else {
                        item.setRead(true);
                    }
                    System.out.println(item.isRead());
                }
            }

            for (Item item : allItems) {
                if (item.getId() == Integer.parseInt(finalStr)) {
                    if (item.isRead()) {
                        item.setRead(false);
                    } else {
                        item.setRead(true);
                    }
                }
            }

            //set Attribute
            request.getSession().setAttribute("rssFeed", rssFeed);
            request.getSession().setAttribute("allItems", allItems);
        } else if (buttonText.equals("showAll")) {
            //get Attributes
            allItems = (List<Item>) request.getSession().getAttribute("allItems");
            List<Item> itemList = new ArrayList<>();
            rssFeed = (Rss) request.getSession().getAttribute("rssFeed");
            
            //Netbeans bug
            itemList = allItems.stream().map(r -> r.clone()).collect(Collectors.toList());
            rssFeed.getChannel().getItemList().clear();
            rssFeed.getChannel().getItemList().addAll(itemList);

            //set Attribute
            request.getSession().setAttribute("rssFeed", rssFeed);
        } else {
            //get Attributes
            rssFeed = XMLAccess.getFeeds(buttonText);
            allItems = rssFeed.getChannel().getItemList().stream().map(r -> r.clone()).collect(Collectors.toList());

            //set Attributes
            request.getSession().setAttribute("rssFeed", rssFeed);
            request.getSession().setAttribute("allItems", allItems);
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
