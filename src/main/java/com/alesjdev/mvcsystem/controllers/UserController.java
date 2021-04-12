package com.alesjdev.mvcsystem.controllers;

import com.alesjdev.mvcsystem.dao.IDaoUser;
import com.alesjdev.mvcsystem.dao.JdbcDaoUser;
import com.alesjdev.mvcsystem.exceptions.LoginErrorException;
import com.alesjdev.mvcsystem.exceptions.RegisterErrorException;
import com.alesjdev.mvcsystem.models.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("action") != null){
            String action = request.getParameter("action");
            switch(action){
                case "closeSession":
                    closeSession(request, response);
                    break;
            }
        }
    }
    

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
            
            boolean validEmail = user.validateEmail();
            
            if(!validEmail){
                throw new ServletException(new RegisterErrorException("Please use a valid e-mail adress."));
            }  
            User sessionUser=null;
            
            //Check validation code to register and if user is manager or employee rank
            int accountType = userDAO.verifyCode(valCode);
            if(accountType == -1){        
                throw new ServletException(new RegisterErrorException("The provided code is not valid. "
                        + "Please contact your manager for a valid code."));       
            } else {
                user.setAccountType(accountType);
                try {
                    sessionUser = userDAO.createUser(user);
                    request.getSession().setAttribute("user", sessionUser);
                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                } catch (RegisterErrorException ex) {
                    throw new ServletException(ex);
                }
            }
        } else {       
            throw new ServletException(new RegisterErrorException("Passwords don't match"));
        }
    }

    private void validateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User temp = new User();
        temp.setUsername(username);
        temp.setPassword(password);
        
        boolean validEmail = temp.validateEmail();
        
        if(!validEmail){
            throw new ServletException(new LoginErrorException("Please use a valid e-mail adress."));
        }
        
        IDaoUser userDAO = new JdbcDaoUser();
        User sessionUser = userDAO.validateUser(username, password);
        if (sessionUser != null){
            request.getSession().setAttribute("user", sessionUser);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        } else {
            throw new ServletException(new LoginErrorException("User isn't registered in the database or password doesn't match.")); 
        }
        
    }

    private void closeSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect(request.getContextPath()+"/signin.jsp");
    }
}
