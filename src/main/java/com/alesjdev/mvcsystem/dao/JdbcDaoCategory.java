/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.dbconnection.DataBasePG;
import com.alesjdev.mvcsystem.models.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JdbcDaoCategory implements IDaoCategory {
    
    
    //Method to retrieve full list of categories.
    @Override
    public List<Category> listAll() {
        
        List <Category> categoryList = new ArrayList<>();
        DataBasePG database = new DataBasePG();        
        try {           
            
            Connection conn = database.getConnection();
            String sql = "SELECT * FROM categories";
            PreparedStatement ps = conn.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            
            Category cat;
            while (rs.next()){
                cat = new Category();
                cat.setCategoryId(rs.getLong("category_id"));
                cat.setCategoryName(rs.getString("category_name"));
                categoryList.add(cat);
            }
            
            database.disconnectDB();
        } catch (SQLException ex) { 
            System.out.println("Error in listAll (Categories): " + ex);
            
        } finally {
            if (database.getConnection() != null){
                database.disconnectDB();
            }
        }
        
        return categoryList;
    }
    
    //Method to insert category.
    @Override
    public String insert(Category cat) {
        
        String message;
        DataBasePG database = new DataBasePG();
        
        try {           
            
            Connection conn = database.getConnection();
            
            String sql = "INSERT INTO categories (category_id, category_name) "
                    + "VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, cat.getCategoryId());
            ps.setString(2, cat.getCategoryName());
            ps.executeUpdate();
            message = "Category successfully added.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "There was a problem adding the category: " + ex.getMessage();
            
        } finally {
            if (database.getConnection() != null){
                database.disconnectDB();
            }
        }
        
        return message;
    }

    @Override
    public Category findById(long id) {
        Category category = new Category();
        DataBasePG database = new DataBasePG();
        
        try {           
            
            Connection conn = database.getConnection();
            String sql = "SELECT * FROM categories WHERE category_id = ? LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()){
                category.setCategoryId(rs.getLong("category_id"));
                category.setCategoryName(rs.getString("category_name"));               
            }
            
            database.disconnectDB();
        } catch (SQLException ex) { 
            System.out.println("Error in findById (Categories): " + ex);
            
        } finally {
            if (database.getConnection() != null){
                database.disconnectDB();
            }
        }
        
        return category;
    }

    @Override
    public String update(Category cat) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {           
            
            Connection conn = database.getConnection();
            
            String sql = "UPDATE categories SET category_name = ? "                    
                    + "WHERE category_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, cat.getCategoryName());
            ps.setLong(2, cat.getCategoryId());            
            ps.executeUpdate();
            
            message = "Category successfully updated.";         
            database.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "There was a problem modifying the category: " + ex.getMessage();
            
        } finally {
            if (database.getConnection() != null){
                database.disconnectDB();
            }
        }
        
        return message;
    }

    @Override
    public String delete(Category cat) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {           
            
            Connection conn = database.getConnection();
            
            String sql = "DELETE FROM categories WHERE category_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
                       
            ps.setLong(1, cat.getCategoryId());            
            ps.executeUpdate();
            
            message = "Category successfully deleted.";         
            database.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "There was a problem deleting the category: " + ex.getMessage();
            
        } finally {
            if (database.getConnection() != null){
                database.disconnectDB();
            }
        }
        
        return message;
    }

    @Override
    public List<Category> searchByCriteria(String param) {
        List<Category> categoryList = new ArrayList<>();
        
        DataBasePG database = new DataBasePG();
        Connection conn = database.getConnection();
        
        try {
            String sql = "SELECT * FROM categories WHERE category_name ILIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+param+"%");
            ResultSet rs = ps.executeQuery();
            
            Category cat;
            while(rs.next()){
                long catId = rs.getLong("category_id");
                String catName = rs.getString("category_name");
                cat = new Category(catId, catName);
                categoryList.add(cat);
            }
            
            database.disconnectDB();
            
        } catch (SQLException e) {
            System.err.println("Error trying to find category by criteria: " + e.getMessage());
            
        } finally {
            if (database.getConnection() != null){
                database.disconnectDB();
            }
        }
        
        return categoryList;
    }
    
}
