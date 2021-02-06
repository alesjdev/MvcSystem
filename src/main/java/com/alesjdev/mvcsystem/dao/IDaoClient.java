package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.models.Client;
import java.util.List;


public interface IDaoClient {
    
    public List<Client> listAll();
    
    public String insert(Client cli);
    
    public String update(Client cli);
    
    public String delete(Client cli);
    
    public Client findById(long id);
}
