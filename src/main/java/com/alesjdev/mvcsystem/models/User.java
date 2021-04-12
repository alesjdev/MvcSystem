package com.alesjdev.mvcsystem.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class User {
    
    private long userId;
    private String username;
    private String password;
    private int accountType;
    
    public User() {
    }

    public User(String username, String password, int accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
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
