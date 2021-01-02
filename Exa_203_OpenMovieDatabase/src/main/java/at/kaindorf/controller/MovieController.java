/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.controller;

import at.kaindorf.bl.MovieListModel;
import at.kaindorf.io.IOHandler;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import at.kaindorf.pojos.Movie;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author kainz
 */
@WebServlet(name = "MovieController", urlPatterns = {"/MovieController"})
public class MovieController extends HttpServlet {
    MovieListModel mlm = new MovieListModel();

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

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("movieView.jsp").forward(request, response);
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
        try {
            String buttonName = request.getParameter("buttonClick");
            String condition = "";
            List<Movie> movieList = new ArrayList<>();
            List<Movie> allMovies = new ArrayList<>();

            switch (buttonName) {
                case "Search":
                    String movieName = request.getParameter("searchField");
                    movieList = IOHandler.getAllMovies(movieName, 1);
                    allMovies = movieList.stream().map(m -> m.clone()).collect(Collectors.toList());
                    
                    //set Attributes
                    request.getSession().setAttribute("notFiltered", allMovies);
                    request.getSession().setAttribute("movieList", movieList);
                    request.getSession().setAttribute("movieSelected", true);
                    request.getSession().setAttribute("userInput", movieName);
                    request.getSession().setAttribute("genreSet", mlm.getGenres(movieList));
                    break;
                    
                case "Sort":
                    movieList = (List<Movie>) request.getSession().getAttribute("movieList");
                    condition = request.getParameter("sortList");
                    mlm.sortList(movieList, condition);
                    request.setAttribute("movieList", movieList);
                    break;
                    
                case "Filter":
                    condition = request.getParameter("filterList");
                    allMovies = (List<Movie>) request.getSession().getAttribute("notFiltered");
                    movieList = allMovies.stream().map(m -> m.clone()).collect(Collectors.toList());
                    System.out.println(movieList.size());
                    mlm.filterList(movieList, condition);
                    
                    //set Attributes
                    request.getSession().setAttribute("movieList", movieList);
                    request.getSession().setAttribute("notFiltered", allMovies);
                    break;
            }
           
        } catch (NullPointerException ex) {

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
