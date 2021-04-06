package com.alesjdev.mvcsystem.controllers;

import com.alesjdev.mvcsystem.dao.*;
import com.alesjdev.mvcsystem.models.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
        //If user comes from cancel order button, delete it.
        if(request.getParameter("action")!=null){
            if(request.getParameter("action").equals("cancelOrder")){
                request.getSession().removeAttribute("order");
            }
        }
        
        //Create DAOs
        JdbcDaoClient clientDAO = new JdbcDaoClient();
        JdbcDaoProduct productDAO = new JdbcDaoProduct();
        JdbcDaoEmployee employeeDAO = new JdbcDaoEmployee();
        
        //Create lists with all the needed information
        List<Client> clientList = clientDAO.listAll();
        List<Product> productList = productDAO.listAll();
        List<Employee> employeeList = employeeDAO.listAll();
        
        Order order;
        if(request.getSession().getAttribute("order") == null){
            order = new Order();
            order.setAmount(0.0);
            order.setOrderDate(new java.sql.Date(new java.util.Date().getTime()));       
        } else {
            order = (Order)request.getSession().getAttribute("order");
            double totalOrderAmount = 0.0;
            for(OrderDetail details : order.getDetails()){
                totalOrderAmount += details.getAmount();
            }
            order.setAmount(totalOrderAmount);
        }
        request.getSession().setAttribute("order", order);
        
        //Set lists and order as attributes to the index
        request.setAttribute("clientList", clientList);
        request.setAttribute("productList", productList);
        request.setAttribute("employeeList", employeeList);        
        request.getRequestDispatcher("/WEB-INF/pos/index.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if(action != null) {
            switch(action){
                case "finishAndPay":
                    finishAndPay(request, response);
                    break;
                case "addProduct":
                    addProduct(request, response);
                    break;
            }
        }
    }

    private void finishAndPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long clientId = Long.parseLong(request.getParameter("clientId"));
        long employeeId = Long.parseLong(request.getParameter("employeeId"));       
        int discount = Integer.parseInt(request.getParameter("discount"));
        
        JdbcDaoClient clientDAO = new JdbcDaoClient();
        JdbcDaoEmployee employeeDAO = new JdbcDaoEmployee();
        
        Client client = clientDAO.findById(clientId);
        Employee employee = employeeDAO.findById(employeeId);
        
        // Obtain order from the session
        Order order = (Order) request.getSession().getAttribute("order");
        
        // Remove products sold from the database       
        JdbcDaoProduct productDAO = new JdbcDaoProduct();
        
        List <OrderDetail> detailList = order.getDetails();
        for (OrderDetail detail : detailList){
            Product product = detail.getProduct();
            int quantitySold = detail.getQuantity();
            int updatedStock = product.getProductStock() - quantitySold;
            
            productDAO.update(
                new Product(product.getProductId(), product.getSupplier(),
                        product.getCategory(), product.getProductName(),
                        product.getProductUnitPrice(), updatedStock));
        }
        
        // Add the client and employee to the order
        order.setClient(client);
        order.setEmployee(employee);
        
        //Add discount applied
        order.setDiscount(discount);
        
        JdbcDaoOrder orderDAO = new JdbcDaoOrder();
        Order completedOrder = orderDAO.insert(order);
        
        if (completedOrder != null) {
            request.getSession().setAttribute("completedOrder", completedOrder);
            response.sendRedirect(request.getContextPath()+"/OrderController");
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the product ID and the quantity sent by the form
        long prodId = Long.parseLong(request.getParameter("prodId"));
        int prodQuantity = Integer.parseInt(request.getParameter("prodQuantity"));
        
        // Get the product to add by it's ID
        JdbcDaoProduct productDAO = new JdbcDaoProduct();
        Product product = productDAO.findById(prodId);
        
        // Get the total amount based on the item quantity multiplied by it's unit price
        double amount = product.getProductUnitPrice() * prodQuantity;
        
        // Get order object and create Order Detail
        Order order = (Order)request.getSession().getAttribute("order");
        if(order == null){
            order = new Order();
            request.getSession().setAttribute("order", order);
        }
        OrderDetail details = new OrderDetail();
        
        // Set the values of the current transaction
        details.setQuantity(prodQuantity);
        details.setProduct(product);
        details.setOrder(order);
        details.setAmount(amount);
        
        // Add details to the order details.        
        order.getDetails().add(details);
        
        //request.getSession().setAttribute("order", order);
        
        response.sendRedirect(request.getContextPath()+"/PosController");
    }
   
}
