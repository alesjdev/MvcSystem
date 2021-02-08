package com.alesjdev.mvcsystem.controllers;

import com.alesjdev.mvcsystem.dao.*;
import com.alesjdev.mvcsystem.models.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("action") != null){
            
            String action = request.getParameter("action");
            
            switch(action){
                case "new":
                    newProduct(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                
            }
            
        } else {           
            JdbcDaoProduct productDAO = new JdbcDaoProduct();
            List <Product> productList = productDAO.listAll();

            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/WEB-INF/products/index.jsp")
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
                    processNewProduct(request, response);                 
                    break;
                    
                case "update":
                    processUpdateProduct(request, response);
                    break;
                    
                case "delete":
                    processDeleteProduct(request, response);
                    break;
            }
            
        }
    }
    
    
    /*
    * Methods to handle CRUD operations.
    */

    // From GET
    private void newProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Create supplier and category lists.
        List <Supplier> supplierList = new JdbcDaoSupplier().listAll();
        List <Category> categoryList = new JdbcDaoCategory().listAll();
        
        /*
        * Send the category and supplier list as attributes to the form
        * so the user can select one there.
        */
        request.setAttribute("catList", categoryList);
        request.setAttribute("suppList", supplierList);
        request.setAttribute("formType", "new");
        
        request.getRequestDispatcher("/WEB-INF/products/form.jsp")
                .forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Create supplier and category lists.
        List <Supplier> supplierList = new JdbcDaoSupplier().listAll();
        List <Category> categoryList = new JdbcDaoCategory().listAll();
        
        long prodId = Long.parseLong(request.getParameter("prodId"));
        Product product = new JdbcDaoProduct().findById(prodId);
        
        if (product != null) {
            // Send the objects as attributes to the form
            request.setAttribute("product", product);
            request.setAttribute("catList", categoryList);
            request.setAttribute("suppList", supplierList);

            // Specify the form type to update, and send it to the form JSP
            request.setAttribute("formType", "update");
            request.getRequestDispatcher("/WEB-INF/products/form.jsp")
                    .forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Couldn't find the product to edit.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    
    // From POST
    private void processNewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long prodId = Long.parseLong(request.getParameter("prodId"));
        long suppId = Long.parseLong(request.getParameter("prodSupplier"));
        long catId = Long.parseLong(request.getParameter("prodCategory"));
        String prodName = request.getParameter("prodName");
        double prodUnitPrice = Double.parseDouble(request.getParameter("prodUnitPrice"));
        int prodStock = Integer.parseInt(request.getParameter("prodStock"));
               
        // Create product DAO and obtain category and supplier using their DAO
        JdbcDaoProduct productDAO = new JdbcDaoProduct();
        Supplier supplier = new JdbcDaoSupplier().findById(suppId);
        Category category = new JdbcDaoCategory().findById(catId);
        
        // Create product with all the gathered data
        String message = productDAO.insert(
                new Product(prodId, supplier, category, prodName, prodUnitPrice, prodStock));

        //Redirect to product controller with the confirmation message
        request.getSession().setAttribute("message", message);
        response.sendRedirect("ProductController");
    }

    private void processUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long prodId = Long.parseLong(request.getParameter("prodId"));
        long suppId = Long.parseLong(request.getParameter("prodSupplier"));
        long catId = Long.parseLong(request.getParameter("prodCategory"));
        String prodName = request.getParameter("prodName");
        double prodUnitPrice = Double.parseDouble(request.getParameter("prodUnitPrice"));
        int prodStock = Integer.parseInt(request.getParameter("prodStock"));
               
        // Create product DAO and obtain category and supplier using their DAO
        JdbcDaoProduct productDAO = new JdbcDaoProduct();
        Supplier supplier = new JdbcDaoSupplier().findById(suppId);
        Category category = new JdbcDaoCategory().findById(catId);
        
        // Create product with all the gathered data
        String message = productDAO.update(
                new Product(prodId, supplier, category, prodName, prodUnitPrice, prodStock));

        //Redirect to product controller with the confirmation message
        request.getSession().setAttribute("message", message);
        response.sendRedirect("ProductController");
    }

    private void processDeleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
            long prodId = Long.parseLong(request.getParameter("prodId"));                      
            JdbcDaoProduct productDAO = new JdbcDaoProduct();          
            String message = productDAO.delete(new Product(prodId));
            
            request.getSession().setAttribute("message", message);
            response.sendRedirect("ProductController");        
    }
    
}
