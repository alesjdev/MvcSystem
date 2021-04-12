package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.dbconnection.DataBasePG;
import com.alesjdev.mvcsystem.models.*;
import com.alesjdev.mvcsystem.models.OrderDetail;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JdbcDaoOrder implements IDaoOrder {

    
    @Override
    public List<Order> listAll() {
        DataBasePG db = new DataBasePG();
        Connection conn = db.getConnection();
        List<Order> orderList = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM orders";            
            PreparedStatement ps = conn.prepareStatement(sql);           
            ResultSet rs = ps.executeQuery();
            
            Order order = null;
            while(rs.next()){
                long orderId = rs.getLong("order_id");
                Client client = new JdbcDaoClient().findById(rs.getLong("client_id"));
                Employee employee = new JdbcDaoEmployee().findById(rs.getLong("employee_id"));
                Date orderDate = rs.getDate("order_date");
                int orderDiscount = rs.getInt("order_discount");
                double orderAmount = rs.getBigDecimal("order_amount").doubleValue();
                
                if(employee != null && client != null){
                    order = new Order(orderId, employee, client, orderDate, orderDiscount, orderAmount);
                    orderList.add(order);
                }
                
            }
            
            db.disconnectDB();
            
        } catch (SQLException e) {
            System.err.println("Error fetching order list from database: " + e.getMessage());
            
        } finally {
            if (db.getConnection() != null){
                db.disconnectDB();
            }
        }
        
        return orderList;
    }

    
    @Override
    public Order insert(Order ord) {
        
        // Create variable to store the generated Order ID
        long generatedOrderID = 0;  
        
        // Create database access tools
        DataBasePG db = new DataBasePG();
        Connection conn = db.getConnection();
 
        try {
            
            /************* Orders table handling *************/
            
            // SQL statement, leave order_id out because it's generated automatically
            String sql = "INSERT INTO orders (client_id, employee_id, order_date, "
                    + "order_discount, order_amount) VALUES (?,?,?,?,?)";
                       
            // Actions won't be performed in the database until manually commited.
            conn.setAutoCommit(false);
            
            // Get the prepared statement ready, returning the generated Order ID.
            PreparedStatement psOrder = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psOrder.setLong(1, ord.getClient().getClientId());
            psOrder.setLong(2, ord.getEmployee().getEmployeeId());
            psOrder.setDate(3, ord.getOrderDate());
            psOrder.setInt(4, ord.getDiscount());
            psOrder.setBigDecimal(5, new BigDecimal(
                    ord.getAmount() * (100-ord.getDiscount()) / 100
                )
            );      
            
            psOrder.executeUpdate();
            
            // Obtain the generated keys in the result set
            ResultSet generatedKeys = psOrder.getGeneratedKeys();
            
            // Save the value in the variable created at the start of the method
            if (generatedKeys.next()){
                generatedOrderID = generatedKeys.getLong(1);
            }
            
            // Save the order id in the object
            ord.setOrderId(generatedOrderID);
            
            /************* Order_details table handling *************/
            
            // Prepared Statement for details
            PreparedStatement psDetails;
            
            // Iterate through details
            for (OrderDetail detail : ord.getDetails()) {
                String sqlDetail = "INSERT INTO order_details (order_id, "
                        + "product_id, quantity, amount) VALUES (?,?,?,?)";
                
                psDetails = conn.prepareStatement(sqlDetail);
                
                // Use the order id generated before to create the detail
                psDetails.setLong(1, ord.getOrderId());
                psDetails.setLong(2, detail.getProduct().getProductId());
                psDetails.setInt(3, detail.getQuantity());
                psDetails.setBigDecimal(4, new BigDecimal(detail.getAmount()));
                
                psDetails.executeUpdate();
            }
            
            // Perform commit if everything went fine
            conn.commit();   
            
            db.disconnectDB();
            
            
        } catch (SQLException e) {
            if(conn != null){
                try {
                    System.err.println("Transaction is being rolled back: "
                            + e.getMessage());
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Couldn't roll back the transaction: "
                            + ex.getMessage());
                }
            }
            e.printStackTrace();
            System.err.println("Couldn't register product: " + e.getMessage());
            
        } finally {
            if(conn!=null){
                db.disconnectDB();
            }
        }
        
        return ord;
    }

    
    @Override
    public Order findById(long ordId) {
        Order order = null;
        Employee employee = null;
        Client client = null;
        
        DataBasePG db = new DataBasePG();
        Connection conn = db.getConnection();
        
        try {
            String sql = "SELECT * FROM orders WHERE order_id=? LIMIT 1";          
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, ordId);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                client = new JdbcDaoClient().findById(rs.getLong("client_id"));
                employee = new JdbcDaoEmployee().findById((rs.getLong("employee_id")));
                Date date = rs.getDate("order_date");
                int discount = rs.getInt("order_discount");
                double amount = rs.getBigDecimal("order_amount").doubleValue();
                
                order = new Order(ordId, employee, client, date, discount, amount);
            }
            
            db.disconnectDB();
        } catch (SQLException e) {
            System.err.println("Error finding Order by ID: " + e.getMessage());
            
        } finally {
            if (db.getConnection() != null){
                db.disconnectDB();
            }
        }
        
        return order;
    }

    
    @Override
    public List<OrderDetail> getDetails(Order order) {
        OrderDetail details;
        List<OrderDetail> detailList = new ArrayList<>();
        Product product;
        
        DataBasePG db = new DataBasePG();
        Connection conn = db.getConnection();
        
        try {
            String sql = "SELECT * FROM order_details WHERE order_id = ?";           
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, order.getOrderId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long detailId = rs.getLong("detail_id");
                product = new JdbcDaoProduct().findById(rs.getLong("product_id"));
                int quantity = rs.getInt("quantity");
                double amount = rs.getBigDecimal("amount").doubleValue();
                
                details = new OrderDetail(detailId, order, product, quantity, amount);
                detailList.add(details);
            }
            
            db.disconnectDB();
        } catch (SQLException e) {
            System.err.println("Error retrieving order details: " + e.getMessage());
                   
        } finally {
            if (db.getConnection() != null){
                db.disconnectDB();
            }
        }
        
        return detailList;
    }

    @Override
    public List<Order> getOrdersByClientId(long clientId) {
        List<Order> clientOrders = new ArrayList<>();
        
        DataBasePG db = new DataBasePG();
        Connection conn = db.getConnection();
        
        try {
            String sql = "SELECT * FROM orders WHERE client_id = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);           
            ps.setLong(1, clientId);
            ResultSet rs = ps.executeQuery();
            
            Order order;
            while(rs.next()){
                long orderId = rs.getLong("order_id");
                Employee employee = new JdbcDaoEmployee().findById(rs.getLong("employee_id"));
                Client client = new JdbcDaoClient().findById(clientId);
                Date date = rs.getDate("order_date");
                int discount = rs.getInt("order_discount");
                double amount = rs.getBigDecimal("order_amount").doubleValue();
                
                order = new Order(orderId, employee, client, date, discount, amount);
                clientOrders.add(order);
            }
            
            db.disconnectDB();
        } catch (SQLException e) {
            System.err.println("Error finding client orders: " + e.getMessage());
            
        } finally {
            if (db.getConnection() != null){
                db.disconnectDB();
            }
        }
        
        return clientOrders;
    }
    
}
