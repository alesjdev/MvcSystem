package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.models.Category;
import java.util.List;


public interface IDaoCategory {
    
    public List<Category> listAll();
    
    public boolean insert(Category cat);
    
    
}
