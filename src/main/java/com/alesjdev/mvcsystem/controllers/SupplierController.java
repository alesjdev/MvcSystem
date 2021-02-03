package com.alesjdev.mvcsystem.controllers;

import com.alesjdev.mvcsystem.dao.JdbcDaoSupplier;
import com.alesjdev.mvcsystem.models.Supplier;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SupplierController", urlPatterns = {"/SupplierController"})
public class SupplierController extends HttpServlet {
  
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("action") != null){
            
            String action = request.getParameter("action");
            
            switch(action){
                case "new":
                    newSupplier(request, response);
                    break;
                case "update":
                    updateSupplier(request, response);
                    break;
                
            }
            
        } else {           
            JdbcDaoSupplier supplierDAO = new JdbcDaoSupplier();
            List <Supplier> supplierList = supplierDAO.listAll();

            request.setAttribute("supplierList", supplierList);
            request.getRequestDispatcher("/WEB-INF/suppliers/index.jsp")
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
                    processNewSupplier(request, response);                 
                    break;
                    
                case "update":
                    processUpdateSupplier(request, response);
                    break;
                    
                case "delete":
                    processDeleteSupplier(request, response);
                    break;
            }
            
        }
    }
    
    
    /*
    * Methods to handle CRUD operations.
    */
    
    // From GET
    private void newSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Specify the action to be performed
        request.setAttribute("formType", "new");
        
        // Send request to the form
        request.getRequestDispatcher("/WEB-INF/suppliers/form.jsp")
                .forward(request, response);
    }

    private void updateSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtain the parameter with the Supplier ID
        long suppId = Long.parseLong(request.getParameter("suppId"));
              
        // Create DAO and find the supplier in the Database by it's ID
        JdbcDaoSupplier supplierDAO = new JdbcDaoSupplier();        
        Supplier supp = supplierDAO.findById(suppId);
              
        
        // If supplier isn't null, send the data to the form, if not go to errorPage.
        if(supp != null){
            // Specify the action to be performed (update an existing supplier)
            request.setAttribute("formType", "update");
            request.setAttribute("supplier", supp);
            request.getRequestDispatcher("/WEB-INF/suppliers/form.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Couldn't find the supplier to edit.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
        
    }

    
    // From POST
    private void processNewSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long suppId = Long.parseLong(request.getParameter("suppId"));
        String suppName = request.getParameter("suppName");
        String suppContactName = request.getParameter("suppContactName");
        String suppPhoneNumber = request.getParameter("suppPhoneNumber");       
        
        
        JdbcDaoSupplier supplierDAO = new JdbcDaoSupplier();
        String message = supplierDAO.insert(
                new Supplier(suppId, suppName, suppContactName, suppPhoneNumber));

        request.getSession().setAttribute("message", message);
        response.sendRedirect("SupplierController");
    }

    private void processUpdateSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        long suppId = Long.parseLong(request.getParameter("suppId"));
        String suppName = request.getParameter("suppName");
        String suppContactName = request.getParameter("suppContactName");
        String suppPhoneNumber = request.getParameter("suppPhoneNumber");       
        
        
        JdbcDaoSupplier supplierDAO = new JdbcDaoSupplier();
        String message = supplierDAO.update(
                new Supplier(suppId, suppName, suppContactName, suppPhoneNumber));

        request.getSession().setAttribute("message", message);
        response.sendRedirect("SupplierController");
    }

    private void processDeleteSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process request received from index, not form.
        if(request.getParameter("action").equals("delete")){
            long suppId = Long.parseLong(request.getParameter("suppId"));                      
            JdbcDaoSupplier supplierDAO = new JdbcDaoSupplier();          
            String message = supplierDAO.delete(new Supplier(suppId));
            
            request.getSession().setAttribute("message", message);
            response.sendRedirect("SupplierController");
        }
    }

}
