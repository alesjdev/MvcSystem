package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.models.*;
import java.util.List;


public interface IDaoProduct {
    
    public List<Product> listAll();
    
    public String insert(Product prod);
    
    public String update(Product prod);
    
    public String delete(Product prod);
    
    public Product findById(long prodId);
    
    public List<Product> getProductByCategory(Category cat);
    
}
