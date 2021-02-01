package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.models.Category;
import java.util.List;


public interface IDaoCategory {
    
    public List<Category> listAll();
    
    public String insert(Category cat);
    
    public String update(Category cat);
    
    public String delete(Category cat);
    
    public Category findById(long id);
    
    
    
}
