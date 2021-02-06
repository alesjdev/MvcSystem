package com.alesjdev.mvcsystem.controllers;

import com.alesjdev.mvcsystem.dao.JdbcDaoClient;
import com.alesjdev.mvcsystem.models.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ClientController", urlPatterns = {"/ClientController"})
public class ClientController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("action") != null){
            
            String action = request.getParameter("action");
            
            switch(action){
                case "new":
                    newClient(request, response);
                    break;
                case "update":
                    updateClient(request, response);
                    break;
                
            }
            
        } else {           
            JdbcDaoClient clientDAO = new JdbcDaoClient();
            List <Client> clientList = clientDAO.listAll();

            request.setAttribute("clientList", clientList);
            request.getRequestDispatcher("/WEB-INF/clients/index.jsp")
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
                    processNewClient(request, response);                 
                    break;
                    
                case "update":
                    processUpdateClient(request, response);
                    break;
                    
                case "delete":
                    processDeleteClient(request, response);
                    break;
            }
            
        }
    }
    
    
    /*
    * Methods to handle CRUD operations.
    */

    // From GET
    private void newClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Specify the action to be performed
        request.setAttribute("formType", "new");
        
        // Send request to the form
        request.getRequestDispatcher("/WEB-INF/clients/form.jsp")
                .forward(request, response);
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtain the parameter with the client ID
        long cliId = Long.parseLong(request.getParameter("cliId"));
              
        // Create DAO and find the client in the Database by it's ID
        JdbcDaoClient clientDAO = new JdbcDaoClient();        
        Client cli = clientDAO.findById(cliId);
              
        
        // If client isn't null, send the data to the form, if not go to errorPage.
        if(cli != null){
            // Specify the action to be performed (update an existing client)
            request.setAttribute("formType", "update");
            request.setAttribute("client", cli);
            request.getRequestDispatcher("/WEB-INF/clients/form.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Couldn't find the client to edit.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    
    // From POST
    private void processNewClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long cliId = Long.parseLong(request.getParameter("cliId"));
        String cliCompanyName = request.getParameter("cliCompanyName");
        String cliContactName = request.getParameter("cliContactName");
        String cliAddress = request.getParameter("cliAddress");
        String cliEmail = request.getParameter("cliEmail");
        String cliPhoneNumber = request.getParameter("cliPhoneNumber");       
        
        
        JdbcDaoClient clientDAO = new JdbcDaoClient();
        String message = clientDAO.insert(
                new Client(cliId, cliCompanyName, cliContactName, cliAddress, cliEmail, cliPhoneNumber));

        request.getSession().setAttribute("message", message);
        response.sendRedirect("ClientController");
    }

    private void processUpdateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long cliId = Long.parseLong(request.getParameter("cliId"));
        String cliCompanyName = request.getParameter("cliCompanyName");
        String cliContactName = request.getParameter("cliContactName");
        String cliAddress = request.getParameter("cliAddress");
        String cliEmail = request.getParameter("cliEmail");
        String cliPhoneNumber = request.getParameter("cliPhoneNumber");       
        
        
        JdbcDaoClient clientDAO = new JdbcDaoClient();
        String message = clientDAO.update(
                new Client(cliId, cliCompanyName, cliContactName, cliAddress, cliEmail, cliPhoneNumber));

        request.getSession().setAttribute("message", message);
        response.sendRedirect("ClientController");
    }

    private void processDeleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process request received from index, not form.
        if(request.getParameter("action").equals("delete")){
            long cliId = Long.parseLong(request.getParameter("cliId"));                      
            JdbcDaoClient clientDAO = new JdbcDaoClient();          
            String message = clientDAO.delete(new Client(cliId));
            
            request.getSession().setAttribute("message", message);
            response.sendRedirect("ClientController");
        }
    }

    
}
