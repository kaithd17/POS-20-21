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
        if (request.getSession().getAttribute("rssList") == null) {
            List<Rss> rssList = new ArrayList<>();
            rssList.add(XMLAccess.getFeeds("https://www.diepresse.com/rss/home"));
            rssList.add(XMLAccess.getFeeds("https://www.diepresse.com/rss/Sport"));
            rssList.add(XMLAccess.getFeeds("https://www.diepresse.com/rss/Politik"));
            rssList.add(XMLAccess.getFeeds("https://www.diepresse.com/rss/Kultur"));
            request.getSession().setAttribute("rssList", rssList);
        }
        request.getRequestDispatcher("rssList.jsp").forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        List<Rss> rssList = (List<Rss>) request.getSession().getAttribute("rssList");
        Rss rssFeed = new Rss();

        if (buttonText.contains("view")) {
            buttonText = buttonText.replace("view", "");
            for (Rss rssObject : rssList) {
                if (rssObject.getChannel().getTitle().equals(buttonText)) {
                    rssFeed = rssObject;
                }
            }
            allItems = rssFeed.getChannel().getItemList().stream().map(r -> r.clone()).collect(Collectors.toList());

            //set Attributes
            request.getSession().setAttribute("rssFeed", rssFeed);
            request.getSession().setAttribute("allItems", allItems);
            request.getRequestDispatcher("rssView.jsp").forward(request, response);

        } else if (buttonText.contains("hide")) {
            final String finalStr = buttonText.replace("hide", "");
            //get Feed
            rssFeed = (Rss) request.getSession().getAttribute("rssFeed");
            rssFeed.getChannel().getItemList().removeIf(r -> r.getId() == Integer.parseInt(finalStr));

            //set Attributes
            request.getSession().setAttribute("rssFeed", rssFeed);
            request.getRequestDispatcher("rssView.jsp").forward(request, response);

        } else if (buttonText.contains("read")) {
            String finalStr = buttonText.replace("read", "");
            //get Feed
            rssFeed = (Rss) request.getSession().getAttribute("rssFeed");
            allItems = (List<Item>) request.getSession().getAttribute("allItems");

            for (Item item : rssFeed.getChannel().getItemList()) {
                if (item.getId() == Integer.parseInt(finalStr)) {
                    if (item.isRead()) {
                        item.setRead(false);
                    } else {
                        item.setRead(true);
                    }
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
            request.getRequestDispatcher("rssView.jsp").forward(request, response);

        } else if (buttonText.equals("showAll")) {
            //get Attributes
            allItems = (List<Item>) request.getSession().getAttribute("allItems");
            List<Item> itemList = new ArrayList<>();
            rssFeed = (Rss) request.getSession().getAttribute("rssFeed");

            //get items
            itemList = allItems.stream().map(r -> r.clone()).collect(Collectors.toList());
            rssFeed.getChannel().getItemList().clear();
            rssFeed.getChannel().getItemList().addAll(itemList);

            //set Attribute
            request.getSession().setAttribute("rssFeed", rssFeed);
            request.getRequestDispatcher("rssView.jsp").forward(request, response);

        } else if (buttonText.equals("back")) {
            processRequest(request, response);

        } else if (buttonText.equals("subscribe")) {
            String link = request.getParameter("inputField");
            try {
                rssList.add(XMLAccess.getFeeds(link));
            } catch (Exception ex) {
                
            }
            processRequest(request, response);

        } else if (buttonText.contains("unsubscribe")) {
            final String finalStr = buttonText.replace("unsubscribe", "");
            rssList.removeIf(r -> r.getChannel().getTitle().equals(finalStr));
            //set Attributes
            request.getSession().setAttribute("rssList", rssList);
            processRequest(request, response);
        }
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
