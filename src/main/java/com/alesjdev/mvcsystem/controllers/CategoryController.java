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


@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryController"})
public class CategoryController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("action") != null){
            
            String action = request.getParameter("action");
            
            switch(action){
                case "new":
                    newCategory(request, response);
                    break;
                case "update":
                    updateCategory(request, response);
                    break;
                
            }
            
        } else {           
            JdbcDaoCategory categoryDAO = new JdbcDaoCategory();
            List <Category> categoryList = categoryDAO.listAll();

            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("/WEB-INF/categories/index.jsp")
                    .forward(request, response);
        }
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Handles the request from the form.jsp to manipulate categories.
        if (request.getParameter("action") != null){
                       
            String action = request.getParameter("action");
            
            switch(action){
                
                case "new":                    
                    processNewCategory(request, response);                 
                    break;
                    
                case "update":
                    processUpdateCategory(request, response);
                    break;
                case "delete":
                    processDeleteCategory(request, response);
                    break;
            }
            
        }
    }   

    
    /*
    * Methods to handle CRUD operations.
    */    
       
    // From GET
    private void newCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Specify the action to be performed
        request.setAttribute("formType", "new");
        // Send request to the form
        request.getRequestDispatcher("/WEB-INF/categories/form.jsp")
            .forward(request, response);
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtain the parameter with the category ID
        long catId = Long.parseLong(request.getParameter("catId"));
        
        // Create DAO and find the category in the Database by it's ID
        JdbcDaoCategory categoryDAO = new JdbcDaoCategory();        
        Category cat = categoryDAO.findById(catId);
        
        // If cat isn't null, send the data to the form, if not go to errorPage.
        if(cat != null){
            // Specify the action to be performed (update an existing category)
            request.setAttribute("formType", "update");
            request.setAttribute("category", cat);
            request.getRequestDispatcher("/WEB-INF/categories/form.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Couldn't find the category to edit.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
        
    }
    
    
    //From POST
    private void processNewCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long catId = Long.parseLong(request.getParameter("catId"));
        String catName = request.getParameter("catName");

        JdbcDaoCategory categoryDAO = new JdbcDaoCategory();
        String message = categoryDAO.insert(new Category(catId, catName));

        request.getSession().setAttribute("message", message);
        response.sendRedirect("CategoryController");
    }

    private void processUpdateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long catId = Long.parseLong(request.getParameter("catId"));
        String catName = request.getParameter("catName");

        JdbcDaoCategory categoryDAO = new JdbcDaoCategory();
        String message = categoryDAO.update(new Category(catId, catName));
        
        request.getSession().setAttribute("message", message);
        response.sendRedirect("CategoryController");
    }

    private void processDeleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process request received from index, not form.
        if(request.getParameter("action").equals("delete")){
            long catId = Long.parseLong(request.getParameter("catId"));
                       
            JdbcDaoCategory categoryDAO = new JdbcDaoCategory();          
            String message = categoryDAO.delete(new Category(catId));
            
            request.getSession().setAttribute("message", message);
            response.sendRedirect("CategoryController");
        }
        
    }
}
