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

/**
 *
 * @author Juan Ales
 */
public class JdbcDaoCategory implements IDaoCategory {
    
    
    //Method to retrieve full list of categories.
    @Override
    public List<Category> listAll() {
        
        List <Category> categoryList = new ArrayList<>();
                
        try {           
            DataBasePG database = new DataBasePG();
            Connection conn = database.getConnection();
            String sql = "SELECT * FROM categories";
            PreparedStatement ps = conn.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            
            Category cat;
            while (rs.next()){
                cat = new Category();
                cat.setCategoryId(rs.getInt("category_id"));
                cat.setCategoryName(rs.getString("category_name"));
                categoryList.add(cat);
            }
            
            database.disconnectDB();
        } catch (SQLException ex) { 
            System.out.println("Error in listAll (Categories): " + ex);
        } 
        
        return categoryList;
    }
    
    //Method to insert category.
    @Override
    public boolean insert(Category cat) {
        
        boolean success;
        
        try {           
            DataBasePG database = new DataBasePG();
            Connection conn = database.getConnection();
            
            String sql = "INSERT INTO categories (category_id, category_name) "
                    + "VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, cat.getCategoryId());
            ps.setString(2, cat.getCategoryName());
            ps.executeUpdate();
            success = true;
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            success = false;
        }
        
        return success;
    }

    @Override
    public Category findById(long id) {
        Category category = new Category();
                
        try {           
            DataBasePG database = new DataBasePG();
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
            System.out.println("Error in listAll (Categories): " + ex);
        } 
        
        return category;
    }
    
}
