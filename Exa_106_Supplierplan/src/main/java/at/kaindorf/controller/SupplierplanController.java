/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.controller;

import at.kaindorf.beans.Stunde;
import at.kaindorf.bl.Day;
import at.kaindorf.io.IO_Handler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "SupplierplanController", urlPatterns = {"/SupplierplanController"})
public class SupplierplanController extends HttpServlet {

    private List<Day> daysOfTheWeek = new ArrayList<>();
    private Map<String, Stunde> timeTableMap = new LinkedHashMap<>(); //Damit sie in der Reihenfolge bleiben wie sie eingefügt wurden

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
        daysOfTheWeek.add(Day.MO);
        daysOfTheWeek.add(Day.DI);
        daysOfTheWeek.add(Day.MI);
        daysOfTheWeek.add(Day.DO);
        daysOfTheWeek.add(Day.FR);
        config.getServletContext().setAttribute("daysOfTheWeek", daysOfTheWeek);

        String relativePath = this.getServletContext().getRealPath("/at.kaindorf.res/stundenplan.csv");
        try {
            String className = IO_Handler.getClassName(relativePath);
            config.getServletContext().setAttribute("className", className);
        } catch (IOException ex) {
            System.out.println("IO Failure!");
        }

        List<Stunde> lessons = IO_Handler.getAllLessons(relativePath);
        for (Stunde lesson : lessons) {
            System.out.println(lesson);
        }
        int counter = 0;
        int lessonCounter = 1;
        for (int i = 0; i < lessons.size(); i++) {
            if (counter % 5 == 0 && counter != 0) {
                counter = 0;
                lessonCounter++;
            }
            timeTableMap.put(daysOfTheWeek.get(counter).getDayToken() + lessonCounter, lessons.get(i));
            counter++;
        }
        config.getServletContext().setAttribute("timeTableMap", timeTableMap);
        
       /* for (String str : timeTableMap.keySet()) {
            System.out.print(str+": ");
            System.out.println(timeTableMap.get(str));
        }*/
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("supplierplanView.jsp").forward(request, response);
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
