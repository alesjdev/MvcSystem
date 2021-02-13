package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.dbconnection.DataBasePG;
import com.alesjdev.mvcsystem.models.Order;
import com.alesjdev.mvcsystem.models.OrderDetail;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JdbcDaoOrder implements IDaoOrder {

    @Override
    public List<Order> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            psOrder.setBigDecimal(5, new BigDecimal(ord.getAmount()));      
            
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
            db.disconnectDB();
        } finally {
            if(conn!=null){
                db.disconnectDB();
            }
        }
        
        return ord;
    }

    @Override
    public String update(Order ord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(Order ord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order findById(long ordId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
