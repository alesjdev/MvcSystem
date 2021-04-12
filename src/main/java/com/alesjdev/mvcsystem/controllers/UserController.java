package com.alesjdev.mvcsystem.controllers;

import com.alesjdev.mvcsystem.dao.IDaoUser;
import com.alesjdev.mvcsystem.dao.JdbcDaoUser;
import com.alesjdev.mvcsystem.models.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    /*
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("action") != null){
            String action = request.getParameter("action");
            switch(action){
                case "createUser":
                    createUser(request, response);
                    break;
                case "validateUser":
                    validateUser(request, response);
                    break;                  
            }
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        int valCode = Integer.parseInt(request.getParameter("validationCode"));
        
        if(password.equals(password2)){
            IDaoUser userDAO = new JdbcDaoUser();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);         
            User sessionUser=null;
            
            //Check if user is manager or employee rank
            int accountType = userDAO.verifyCode(valCode);
            if(accountType == -1){
                request.setAttribute("errorMessage", "The provided validation code is not valid.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);         
            } else {
                user.setAccountType(accountType);
                sessionUser = userDAO.createUser(user);
            }
            
            
            
            
            if (sessionUser != null){
                request.getSession().setAttribute("user", sessionUser);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            } else {
                request.setAttribute("errorMessage", "Couldn't create user in the database.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Passwords doesn't match.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    private void validateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        IDaoUser userDAO = new JdbcDaoUser();
        User sessionUser = userDAO.validateUser(username, password);
        if (sessionUser != null){
            request.getSession().setAttribute("user", sessionUser);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        } else {
            request.setAttribute("errorMessage", "User isn't registered in the database or password doesn't match.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
        
    }

}
