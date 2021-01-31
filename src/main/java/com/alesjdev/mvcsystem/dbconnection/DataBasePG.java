package com.alesjdev.mvcsystem.dbconnection;


public class DataBasePG {
        
    public DataBasePG (){
        String databaseUrl = "jdbc:";
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {

        }
    }
}
