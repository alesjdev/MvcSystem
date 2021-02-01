package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.models.Employee;
import java.util.List;


public interface IDaoEmployee {
    public List<Employee> listAll();
    
    public String insert(Employee emp);
    
    public String update(Employee emp);
    
    public String delete(Employee emp);
    
    public Employee findById(long id);
    
}
