/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alesjdev.mvcsystem.controllers;

import com.alesjdev.mvcsystem.dao.JdbcDaoCategory;
import com.alesjdev.mvcsystem.models.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan Ales
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryController"})
public class CategoryController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
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
        
        if (request.getParameter("action") != null){
            
            String action = request.getParameter("action");
            
            switch(action){
                case "new":
                    request.getRequestDispatcher("/WEB-INF/categories/form.jsp")
                            .forward(request, response);
                    break;
                case "update":
                    break; //TODO
                case "delete":
                    break; //TODO
            }
            
        } else {           
            JdbcDaoCategory categoryDAO = new JdbcDaoCategory();
            List <Category> categoryList = categoryDAO.listAll();

            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("/WEB-INF/categories/index.jsp")
                    .forward(request, response);
        }
        
        
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
        
        //Handles the request from the form.jsp to manipulate categories.
        if (request.getParameter("action") != null){
                       
            String action = request.getParameter("action");
            
            switch(action){
                
                case "new":
                    
                    long catId = Long.parseLong(request.getParameter("catId"));
                    String catName = request.getParameter("catName");
                    
                    JdbcDaoCategory categoryDAO = new JdbcDaoCategory();
                    boolean success = categoryDAO.insert(new Category(catId, catName));
                    
                   
                    request.getSession().setAttribute("success", success);
                    response.sendRedirect("CategoryController");
                   
                    break;
                    
                case "update":
                    break; //TODO
                case "delete":
                    break; //TODO
            }
            
        }
    }

    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
