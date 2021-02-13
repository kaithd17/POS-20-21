/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.controller;

import at.kaindorf.io.IOHandler;
import at.kaindorf.xml.XML_Access;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import at.kaindorf.pojos.CurrentWeather;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;

/**
 *
 * @author kainz
 */
@WebServlet(name = "WeatherController", urlPatterns = {"/WeatherController"})
public class WeatherController extends HttpServlet {

    private String relativePath;
    private String relativePath2;

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
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        relativePath = this.getServletContext().getRealPath("/at.kaindorf.res/de.csv");
        relativePath2 = this.getServletContext().getRealPath("/at.kaindorf.res/en.csv");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("weatherView.jsp").forward(request, response);
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
        boolean failure = false;
        boolean responseState = false;
        boolean noGusts = false;
        String language = "";
        String searchString = "";
        List<String> languageList = new ArrayList<>();

        if (request.getParameter("searchButton") != null) {
            try {
                responseState = true;
                failure = false;

                if (request.getParameter("languageList") == null) {
                    language = "Deutsch";
                    request.getSession().setAttribute("currentLanguage", "Deutsch");
                } else {
                    language = request.getParameter("languageList");
                    request.getSession().setAttribute("currentLanguage", language);
                }
                searchString = request.getParameter("inputField");
                CurrentWeather weather = XML_Access.getCurrentWeather(searchString, language);
                if (!weather.getWind().getGusts().equals("")) {
                    noGusts = true;
                }

                if (language.equals("Deutsch")) {
                    languageList = IOHandler.getLanguage(relativePath);
                } else {
                    languageList = IOHandler.getLanguage(relativePath2);
                }

                //Set Attributes
                request.getSession().setAttribute("currentWeather", weather);
                request.getSession().setAttribute("failure", failure);
                request.getSession().setAttribute("responseState", responseState);
                request.getSession().setAttribute("gusts", noGusts);
                request.getSession().setAttribute("languageList", languageList);
            } catch (Exception ex) {
                failure = true;
                responseState = false;
                request.getSession().setAttribute("failure", failure);
                request.getSession().setAttribute("responseState", responseState);
                request.getSession().setAttribute("currentLanguage", "Deutsch");
            }
            
        } else if (request.getParameter("languageList") != null) {
            String currentLanguage = request.getParameter("languageList");
            CurrentWeather weather = (CurrentWeather) request.getSession().getAttribute("currentWeather");

            if (currentLanguage.equals("Deutsch")) {
                languageList = IOHandler.getLanguage(relativePath);
            } else {
                languageList = IOHandler.getLanguage(relativePath2);
            }

            weather = XML_Access.getCurrentWeather(weather.getCity().getName(), currentLanguage);

            //set Attributes
            request.getSession().setAttribute("currentLanguage", currentLanguage);
            request.getSession().setAttribute("languageList", languageList);
            request.getSession().setAttribute("currentWeather", weather);
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
