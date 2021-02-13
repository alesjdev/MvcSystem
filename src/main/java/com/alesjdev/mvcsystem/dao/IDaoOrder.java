package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.models.*;
import java.util.List;
import java.util.Map;


public interface IDaoOrder {
    
    public List<Order> listAll();
    
    public Order insert(Order ord);
    
    public Order findById(long ordId);
    
    public List<OrderDetail> getDetails (Order order);
    
}
