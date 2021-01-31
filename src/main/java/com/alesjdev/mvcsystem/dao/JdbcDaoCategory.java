/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.models.Category;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Ales
 */
public class JdbcDaoCategory implements IDaoCategory {
    
    @Override
    public List<Category> listAll() {
        //Code to access database through JDBC.
        List <Category> categories = new ArrayList<>();
        return categories;
    }
    
}
