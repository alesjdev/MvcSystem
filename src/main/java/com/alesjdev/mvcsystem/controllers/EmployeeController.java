package com.alesjdev.mvcsystem.controllers;

import com.alesjdev.mvcsystem.dao.JdbcDaoEmployee;
import com.alesjdev.mvcsystem.models.Employee;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeController"})
public class EmployeeController extends HttpServlet {

     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("action") != null){
            
            String action = request.getParameter("action");
            
            switch(action){
                case "new":
                    newEmployee(request, response);
                    break;
                case "update":
                    updateEmployee(request, response);
                    break;
                
            }
            
        } else {           
            JdbcDaoEmployee employeeDAO = new JdbcDaoEmployee();
            List <Employee> employeeList = employeeDAO.listAll();

            request.setAttribute("employeeList", employeeList);
            request.getRequestDispatcher("/WEB-INF/employees/index.jsp")
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
                    processNewEmployee(request, response);                 
                    break;
                    
                case "update":
                    processUpdateEmployee(request, response);
                    break;
                    
                case "delete":
                    processDeleteEmployee(request, response);
                    break;
            }
            
        }
    }
    
    
    /*
    * Methods to handle CRUD operations.
    */
    
    // From GET
    private void newEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Specify the action to be performed
        request.setAttribute("formType", "new");
        
        // Attach an employee list to display in the "reports to" part of the form.
        JdbcDaoEmployee employeeDAO = new JdbcDaoEmployee();
        List<Employee> empList = employeeDAO.listAll();
        request.setAttribute("empList", empList);
        
        // Send request to the form
        request.getRequestDispatcher("/WEB-INF/employees/form.jsp")
                .forward(request, response);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtain the parameter with the employee ID
        long empId = Long.parseLong(request.getParameter("empId"));
              
        // Create DAO and find the employee in the Database by it's ID
        JdbcDaoEmployee employeeDAO = new JdbcDaoEmployee();        
        Employee emp = employeeDAO.findById(empId);
        
        // Attach an employee list to display in the "reports to" part of the form.
        List<Employee> empList = employeeDAO.listAll();
        request.setAttribute("empList", empList);
        
        // If emp isn't null, send the data to the form, if not go to errorPage.
        if(emp != null){
            // Specify the action to be performed (update an existing employee)
            request.setAttribute("formType", "update");
            request.setAttribute("employee", emp);
            request.getRequestDispatcher("/WEB-INF/employees/form.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Couldn't find the employee to edit.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
        
    }

    
    // From POST
    private void processNewEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long empId = Long.parseLong(request.getParameter("empId"));
        String empFirstName = request.getParameter("empFirstName");
        String empLastName = request.getParameter("empLastName");
        Date empDob = null;
        try {
            empDob = parseToDate(request.getParameter("empDob"));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        long empReportsTo = Long.parseLong(request.getParameter("empReportsTo"));
        int empExtension = Integer.parseInt(request.getParameter("empExtension"));
        
        
        JdbcDaoEmployee employeeDAO = new JdbcDaoEmployee();
        String message = employeeDAO.insert(new Employee(empId, empFirstName, 
                empLastName, empDob, empReportsTo, empExtension));

        request.getSession().setAttribute("message", message);
        response.sendRedirect("EmployeeController");
    }

    private void processUpdateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        long empId = Long.parseLong(request.getParameter("empId"));        
        String empFirstName = request.getParameter("empFirstName");
        String empLastName = request.getParameter("empLastName");
        Date empDob = null;
        try {
            empDob = parseToDate(request.getParameter("empDob"));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        long empReportsTo = Long.parseLong(request.getParameter("empReportsTo"));
        int empExtension = Integer.parseInt(request.getParameter("empExtension"));
        
        
        JdbcDaoEmployee employeeDAO = new JdbcDaoEmployee();
        String message = employeeDAO.update(new Employee(empId, empFirstName, 
                empLastName, empDob, empReportsTo, empExtension));

        request.getSession().setAttribute("message", message);
        response.sendRedirect("EmployeeController");
    }

    private void processDeleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process request received from index, not form.
        if(request.getParameter("action").equals("delete")){
            long empId = Long.parseLong(request.getParameter("empId"));
                       
            JdbcDaoEmployee employeeDAO = new JdbcDaoEmployee();          
            String message = employeeDAO.delete(new Employee(empId));
            
            request.getSession().setAttribute("message", message);
            response.sendRedirect("EmployeeController");
        }
    }

    
    /* 
    * Method to parse String to (sql) Date 
    */
    private Date parseToDate(String dobString) throws ParseException {               
        
        // Parse String to java.util.Date 
        java.util.Date utilDob = new SimpleDateFormat("yyyy-MM-dd").parse(dobString);
        // Parse java.util.Date to java.sql.Date
        java.sql.Date dob = new java.sql.Date(utilDob.getTime());
               
        return dob;
    }

   

}
