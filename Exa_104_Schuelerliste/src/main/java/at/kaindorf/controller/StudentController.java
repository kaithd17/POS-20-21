/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.controller;

import at.kaindorf.beans.Student;
import at.kaindorf.bl.IO_Handler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
@WebServlet(name = "StudentController", urlPatterns = {"/StudentController"})
public class StudentController extends HttpServlet {

    private List<Student> studentList = new ArrayList<>();
    private List<Student> filteredList = new ArrayList<>();

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
        String relativePath = this.getServletContext().getRealPath("/at.kaindorf.res/students_2020.csv");
        try {
            studentList = IO_Handler.getAllStudents(relativePath);
            int catalognr = 1;
            for (int i = 0; i < studentList.size(); i++) {
                try {
                    if (studentList.get(i).getClassName().equals(studentList.get(i - 1).getClassName())) {
                        catalognr++;
                        studentList.get(i).setCatalognr(catalognr);
                    } else {
                        catalognr = 1;
                        studentList.get(i).setCatalognr(catalognr);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    studentList.get(i).setCatalognr(1);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File failure!");
        }
        filteredList.addAll(studentList);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String currentStudent = request.getParameter("studentselector");
        String buttonText1 = request.getParameter("setzen");
        String buttonText2 = request.getParameter("entfernen");
        String filterText = "";
        if (buttonText1 != null) {
            filterText = request.getParameter("filterText") == null ? "" : request.getParameter("filterText");
            final String filter = filterText;
            filteredList = studentList.stream().filter(student -> student.getLastname().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
            request.setAttribute("filterText", filterText);
        } else if (buttonText2 != null) {
            filteredList.clear();
            filteredList.addAll(studentList);
            request.setAttribute("filterText", "");
        } else {
            filteredList.clear();
            filteredList.addAll(studentList);
            filterText = request.getParameter("filterText") == null ? "" : request.getParameter("filterText");
            if (filterText != null) {
                final String filter = filterText;
                filteredList = studentList.stream().filter(student -> student.getLastname().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
            }
            request.setAttribute("filterText", filterText);
        }

        request.setAttribute("studentList", filteredList);
        request.setAttribute("currentStudent", currentStudent);
        request.getRequestDispatcher("studentView.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");

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
