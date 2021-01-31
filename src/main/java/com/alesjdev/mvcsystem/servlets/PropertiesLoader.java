package com.alesjdev.mvcsystem.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class PropertiesLoader implements ServletContextListener {
    
    private static Properties properties;
    
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            properties = new Properties();
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(
                    servletContextEvent.getServletContext().getInitParameter("propfile")
            );      
            properties.load(in);
            
            // Store it in application scope as well
            servletContextEvent.getServletContext().setAttribute("props", properties);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Properties getProperties(){
        return properties;
    }
}
