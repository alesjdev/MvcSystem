package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.dbconnection.DataBasePG;
import com.alesjdev.mvcsystem.models.Supplier;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcDaoSupplier implements IDaoSupplier {

    //Method to retrieve full list of suppliers.
    @Override
    public List<Supplier> listAll() {
        
        List <Supplier> supplierList = new ArrayList<>();
        DataBasePG database = new DataBasePG();
        
        try {                       
            Connection conn = database.getConnection();
            String sql = "SELECT * FROM suppliers";
            PreparedStatement ps = conn.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            
            
            while (rs.next()){
                // Get all the supplier data
                long suppId = rs.getLong("supplier_id");
                String suppName = rs.getString("supplier_name");
                String suppContactName = rs.getString("supplier_contact_name");
                String suppPhoneNumber = rs.getString("supplier_phone_number");               
                
                // Create the supplier object
                Supplier supplier = new Supplier(suppId, suppName, suppContactName, suppPhoneNumber);
                               
                supplierList.add(supplier);
            }
            
            database.disconnectDB();
        } catch (SQLException ex) { 
            System.out.println("Error in listAll (Suppliers): " + ex.getMessage());
            database.disconnectDB();
        } 
        
        return supplierList;
    }

    @Override
    public String insert(Supplier supp) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {                       
            Connection conn = database.getConnection();
            
            String sql = "INSERT INTO suppliers (supplier_id, supplier_name, "
                    + "supplier_contact_name, supplier_phone_number) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, supp.getSupplierId());
            ps.setString(2, supp.getSupplierName());
            ps.setString(3, supp.getSupplierContactName());
            ps.setString(4, supp.getSupplierPhoneNumber());
            
            ps.executeUpdate();
            message = "Supplier successfully added to the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "There was a problem adding the new supplier: " + ex.getMessage();
            database.disconnectDB();
        }
        
        return message;
    }

    @Override
    public String update(Supplier supp) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {                      
            Connection conn = database.getConnection();
            
            String sql = "UPDATE suppliers SET supplier_name= ?, "
                    + "supplier_contact_name= ?, supplier_phone_number = ? "
                    + "WHERE supplier_id= ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
                     
            ps.setString(1, supp.getSupplierName());
            ps.setString(2, supp.getSupplierContactName());
            ps.setString(3, supp.getSupplierPhoneNumber());
            ps.setLong(4, supp.getSupplierId());
            
            ps.executeUpdate();
            message = "Supplier data successfully updated in the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "There was a problem updating the supplier data: " + ex.getMessage();
            database.disconnectDB();
        }
        
        return message;
    }

    @Override
    public String delete(Supplier supp) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {           
            
            Connection conn = database.getConnection();
            
            String sql = "DELETE FROM suppliers WHERE supplier_id= ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, supp.getSupplierId());            
            ps.executeUpdate();
            
            message = "Supplier successfully removed from the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "There was a problem deleting the supplier from the database: " + ex.getMessage();
            database.disconnectDB();
        }
        
        return message;
    }

    @Override
    public Supplier findById(long id) {
        Supplier supplier = new Supplier();
        DataBasePG database = new DataBasePG();  
        
        try {           
            
            Connection conn = database.getConnection();
            String sql = "SELECT * FROM suppliers WHERE supplier_id = ? LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()){                
                supplier.setSupplierId(rs.getLong("supplier_id"));
                supplier.setSupplierName(rs.getString("supplier_name"));
                supplier.setSupplierContactName(rs.getString("supplier_contact_name"));
                supplier.setSupplierPhoneNumber(rs.getString("supplier_phone_number"));                              
            }
            
            database.disconnectDB();
        } catch (SQLException ex) { 
            System.out.println("Error in findById (Supplier): " + ex);
            database.disconnectDB();
        } 
        
        return supplier;
    }
    
}
