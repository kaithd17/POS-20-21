/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.controller;

import at.kaindorf.bl.ContactListModel;
import at.kaindorf.json.JSONAccess;
import java.io.IOException;
import at.kaindorf.pojos.Contact;
import java.util.ArrayList;
import at.kaindorf.pojos.Company;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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
@WebServlet(name = "ContactController", urlPatterns = {"/ContactController"})
public class ContactController extends HttpServlet {

    private List<Contact> contactList = new ArrayList<>();
    private List<Contact> filteredList = new ArrayList<>();
    private ContactListModel clm = new ContactListModel();

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
        String relativePath = this.getServletContext().getRealPath("/at.kaindorf.res/contacts.json");
        contactList = JSONAccess.getAllContacts(relativePath);
        filteredList.addAll(contactList);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getSession().setAttribute("contactList", filteredList);
        request.getSession().setAttribute("companySet", clm.getAllCompanies(contactList));
        request.getRequestDispatcher("contactView.jsp").forward(request, response);
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
        Map<String, String[]> parameterMap = request.getParameterMap();
        String buttonClicked = request.getParameter("buttonClick");
        
        switch (buttonClicked) {
            case "Set Favourite":
                for (String id : parameterMap.get("userPick")) {
                    for (Contact contact : filteredList) {
                        if (contact.getId() == Integer.parseInt(id)) {
                            contact.setFavourite(true);
                        }
                    }
                }
                break;
                
            case "Delete":
                for (String id : parameterMap.get("userPick")) {
                    filteredList.removeIf(c -> c.getId() == Integer.parseInt(id));
                }
                break;
                
            case "Filter":
                filteredList.clear();
                filteredList.addAll(contactList);
                List<String> conditionList = new ArrayList<>();
                for (String filterCondition : parameterMap.get("filter")) {
                    if (filterCondition.equals("<None>")) {
                        filterCondition = "";
                    }
                    conditionList.add(filterCondition);
                }
                clm.filterContacts(filteredList, conditionList);
                break;
                
            case "Sort":
                String sortCodition = request.getParameter("sortSelector");
                clm.sortList(filteredList, sortCodition);
                break;
                
            case "Sort Reverse":
                String sortCoditionReverse = request.getParameter("sortSelector") + "Reverse";
                clm.sortList(filteredList, sortCoditionReverse);
                break;
                
            default:
                break;
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
