package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.models.User;


public interface IDaoUser {
    
    public User createUser(User user);
    
    public User validateUser(String username, String password);
    
    public String deleteUser(User user);
}
