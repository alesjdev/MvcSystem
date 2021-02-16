package com.alesjdev.mvcsystem.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class User {
    
    private long id;
    private String username;
    private String password;

    
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    @Override
    public String toString() {
        return this.username;
    }
    
    // Method to validate e-mail adress
    public boolean validateEmail(){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");        
        Matcher matcher = pattern.matcher(this.username);        
        return matcher.find();
    }
}
