package com.alesjdev.mvcsystem.dbconnection;

import com.alesjdev.mvcsystem.servlets.PropertiesLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataBasePG {
    
    Properties props = PropertiesLoader.getProperties();
    Connection conn = null;
    
    public DataBasePG (){
        
        String dbUrl = "jdbc:" + props.getProperty("dbaccess");
        String dbUser = props.getProperty("dbuser");
        String dbPass = props.getProperty("dbpass");
        
        try {            
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Exception: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Exception (Driver not found): " + ex.getMessage());
        }
    }
    
    public Connection getConnection () {
        return this.conn;
    }
       
    public void disconnectDB () {
        
        if (conn != null){
            try {
                System.out.println("Closing connection to the DataBase.");
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Couldn't close connection, exception: " + ex.getMessage());
            }
        }
    }
}
