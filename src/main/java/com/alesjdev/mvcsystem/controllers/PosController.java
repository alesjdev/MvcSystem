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


@WebServlet(name = "PosController", urlPatterns = {"/PosController"})
public class PosController extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Create DAOs
        JdbcDaoClient clientDAO = new JdbcDaoClient();
        JdbcDaoProduct productDAO = new JdbcDaoProduct();
        JdbcDaoEmployee employeeDAO = new JdbcDaoEmployee();
        
        //Create lists with all the needed information
        List<Client> clientList = clientDAO.listAll();
        List<Product> productList = productDAO.listAll();
        List<Employee> employeeList = employeeDAO.listAll();
        
        //Set lists as attributes to the index
        request.setAttribute("clientList", clientList);
        request.setAttribute("productList", productList);
        request.setAttribute("employeeList", employeeList);        
        request.getRequestDispatcher("WEB-INF/pos/index.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        switch(action){
            case "finishAndPay":
                finishAndPay(request, response);
                break;
            case "addProduct":
                addProduct(request, response);
                break;
        }
    }

    private void finishAndPay(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
