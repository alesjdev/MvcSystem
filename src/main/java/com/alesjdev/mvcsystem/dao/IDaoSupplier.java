package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.models.Supplier;
import java.util.List;


public interface IDaoSupplier {
    public List<Supplier> listAll();
    
    public String insert(Supplier supp);
    
    public String update(Supplier supp);
    
    public String delete(Supplier supp);
    
    public Supplier findById(long id);
}
