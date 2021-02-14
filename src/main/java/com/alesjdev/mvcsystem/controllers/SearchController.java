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


@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> catList = null;
        List<Product> prodList = null;
        
        String search = (String)request.getParameter("searchCriteria");
        
        if (search!=null){
            catList = new JdbcDaoCategory().searchByCriteria(search);
            prodList = new JdbcDaoProduct().searchByCriteria(search);
        }
        request.setAttribute("catList", catList);
        request.setAttribute("prodList", prodList);
        request.getRequestDispatcher("/WEB-INF/search/index.jsp")
                .forward(request, response);
    }

}
