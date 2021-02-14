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


@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        JdbcDaoOrder orderDAO = new JdbcDaoOrder();
        
        String action = request.getParameter("action");
        
        
        if(action != null) {
            if(action.equals("seeDetail")){
                // Obtain order based on the ID received by parameter
                long orderId = Long.parseLong(request.getParameter("orderId"));
                Order order = orderDAO.findById(orderId);
                List <OrderDetail> detailList = orderDAO.getDetails(order);
                order.setDetails(detailList);
                
                // Send it to the ticket display JSP
                request.setAttribute("order", order);
                request.getRequestDispatcher("/WEB-INF/orders/detailedTransaction.jsp")
                        .forward(request, response);             
            } else if (action.equals("checkOrders")){
                long clientId = Long.parseLong(request.getParameter("cliId"));
                String clientName = new JdbcDaoClient().findById(clientId).getClientCompanyName();
                List <Order> clientOrders = new JdbcDaoOrder().getOrdersByClientId(clientId);
                request.setAttribute("clientOrders", clientOrders);
                request.setAttribute("clientName", clientName);
                request.getRequestDispatcher("/WEB-INF/orders/clientOrders.jsp")
                        .forward(request, response); 
            }
        } else {
            // Send to the order list page
            List <Order> orderList = orderDAO.listAll();
            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("WEB-INF/orders/index.jsp").forward(request, response);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
