package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.dbconnection.DataBasePG;
import com.alesjdev.mvcsystem.models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcDaoProduct implements IDaoProduct {

    @Override
    public List<Product> listAll() {
        List <Product> productList = new ArrayList<>();
        DataBasePG database = new DataBasePG();
        
        try {                       
            Connection conn = database.getConnection();
            String sql = "SELECT * FROM products";
            PreparedStatement ps = conn.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            
            
            while (rs.next()){
                // Get all the product data
                long prodId = rs.getLong("product_id");
                long suppId = rs.getLong("supplier_id");
                long catId = rs.getLong("category_id");
                String prodName = rs.getString("product_name");
                double prodUnitPrice = rs.getDouble("product_unit_price");
                int prodStock = rs.getInt("product_stock");               
                
                /*
                * Use the JDBCs to get the supplier and category associated with the ID
                */               
                Supplier prodSupplier = new JdbcDaoSupplier().findById(suppId);
                Category prodCategory = new JdbcDaoCategory().findById(catId);
                
                // Create the product object
                Product product = new Product(prodId, prodSupplier, prodCategory, prodName, prodUnitPrice, prodStock);
                               
                productList.add(product);
            }
            
            database.disconnectDB();
        } catch (SQLException ex) { 
            System.out.println("Error in listAll (Products): " + ex.getMessage());
            database.disconnectDB();
        } 
        
        return productList;
    }

    @Override
    public String insert(Product prod) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {                       
            Connection conn = database.getConnection();
            
            String sql = "INSERT INTO products (product_id, supplier_id, "
                    + "category_id, product_name, product_unit_price, "
                    + "product_stock) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, prod.getProductId());
            ps.setLong(2, prod.getSupplier().getSupplierId());
            ps.setLong(3, prod.getCategory().getCategoryId());
            ps.setString(4, prod.getProductName());
            ps.setDouble(5, prod.getProductUnitPrice());
            ps.setInt(6, prod.getProductStock());
            
            ps.executeUpdate();
            message = "Product successfully added to the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            message = "There was a problem adding the new product: " + ex.getMessage();
            database.disconnectDB();
        }
        
        return message;
    }

    @Override
    public String update(Product prod) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {                       
            Connection conn = database.getConnection();
            
            String sql = "UPDATE products SET supplier_id=?, category_id=?, "
                    + "product_name=?, product_unit_price=?, product_stock=? "
                    + "WHERE product_id=? ";
            PreparedStatement ps = conn.prepareStatement(sql);
           
            ps.setLong(1, prod.getSupplier().getSupplierId());
            ps.setLong(2, prod.getCategory().getCategoryId());
            ps.setString(3, prod.getProductName());
            ps.setDouble(4, prod.getProductUnitPrice());
            ps.setInt(5, prod.getProductStock());
            ps.setLong(6, prod.getProductId());
            
            ps.executeUpdate();
            message = "Product successfully updated into the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            message = "There was a problem updating the product: " + ex.getMessage();
            database.disconnectDB();
        }
        
        return message;
    }

    @Override
    public String delete(Product prod) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {           
            
            Connection conn = database.getConnection();
            
            String sql = "DELETE FROM products WHERE product_id= ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, prod.getProductId());            
            ps.executeUpdate();
            
            message = "Product successfully removed from the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            message = "There was a problem deleting the product from the database: " + ex.getMessage();
            database.disconnectDB();
        }
        
        return message;
    }

    @Override
    public Product findById(long prodId) {
        Product product = new Product();
        DataBasePG database = new DataBasePG();  
        
        try {           
            
            Connection conn = database.getConnection();
            String sql = "SELECT * FROM products WHERE product_id = ? LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, prodId);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()){                
                product.setProductId(rs.getLong("product_id"));
                product.setSupplier(new JdbcDaoSupplier().findById(rs.getLong("supplier_id")));
                product.setCategory(new JdbcDaoCategory().findById(rs.getLong("category_id")));
                product.setProductName(rs.getString("product_name"));
                product.setProductUnitPrice(rs.getDouble("product_unit_price"));
                product.setProductStock(rs.getInt("product_stock"));
            }
            
            database.disconnectDB();
        } catch (SQLException ex) { 
            System.out.println("Error in findById (Product): " + ex);
            database.disconnectDB();
        } 
        
        return product;
    }

    @Override
    public List<Product> getProductByCategory(Category cat) {
        List<Product> productList = new ArrayList<>();
        DataBasePG db = new DataBasePG();
               
        try {
            Connection conn = db.getConnection();
            // Prepare statement with the results given when searching by category 
            String sql = "SELECT * FROM products WHERE category_id = ?";           
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, cat.getCategoryId());           
            ResultSet rs = ps.executeQuery();
            
            // Create product and initialize with each new result
            Product product = null;
            while(rs.next()){
                product = new Product();
                product.setProductId(rs.getLong("product_id"));
                product.setSupplier(new JdbcDaoSupplier().findById(rs.getLong("supplier_id")));
                product.setCategory(new JdbcDaoCategory().findById(rs.getLong("category_id")));
                product.setProductName(rs.getString("product_name"));
                product.setProductUnitPrice(rs.getDouble("product_unit_price"));
                product.setProductStock(rs.getInt("product_stock"));
                
                productList.add(product);
            }
            
            db.disconnectDB();
        } catch (SQLException ex) {
            System.out.println("Error in obtain products by category: " + ex.getMessage());
            db.disconnectDB();
        }
        
        return productList;
    }
    
    
    
}
