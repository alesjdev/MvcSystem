package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.models.Order;
import java.util.List;
import java.util.Map;


public interface IDaoOrder {
    
    public List<Order> listAll();
    
    public Order insert(Order ord);
    
    public String update(Order ord);
    
    public String delete(Order ord);
    
    public Order findById(long ordId);
    
}
