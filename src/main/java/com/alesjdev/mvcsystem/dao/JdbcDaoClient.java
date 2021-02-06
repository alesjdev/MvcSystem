package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.dbconnection.DataBasePG;
import com.alesjdev.mvcsystem.models.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcDaoClient implements IDaoClient {

    @Override
    public List<Client> listAll() {
        List <Client> clientList = new ArrayList<>();
        DataBasePG database = new DataBasePG();
        
        try {                       
            Connection conn = database.getConnection();
            String sql = "SELECT * FROM clients";
            PreparedStatement ps = conn.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            
            
            while (rs.next()){
                // Get all the client data
                long cliId = rs.getLong("client_id");
                String cliCompName = rs.getString("client_company_name");
                String cliContactName = rs.getString("client_contact_name");
                String cliAddress = rs.getString("client_address");
                String cliEmail = rs.getString("client_email");
                String cliPhoneNumber = rs.getString("client_phone_number");               
                
                // Create the client object
                Client client = new Client(cliId, cliCompName, cliContactName, cliAddress, cliEmail, cliPhoneNumber);
                               
                clientList.add(client);
            }
            
            database.disconnectDB();
        } catch (SQLException ex) { 
            System.out.println("Error in listAll (Clients): " + ex.getMessage());
            database.disconnectDB();
        } 
        
        return clientList;
    }

    @Override
    public String insert(Client cli) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {                       
            Connection conn = database.getConnection();
            
            String sql = "INSERT INTO clients (client_id, client_company_name, "
                    + "client_contact_name, client_address, client_email, "
                    + "client_phone_number) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, cli.getClientId());
            ps.setString(2, cli.getClientCompanyName());
            ps.setString(3, cli.getClientContactName());
            ps.setString(4, cli.getClientAddress());
            ps.setString(5, cli.getClientEmail());
            ps.setString(6, cli.getClientPhoneNumber());
            
            ps.executeUpdate();
            message = "Client successfully added to the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            message = "There was a problem adding the new client: " + ex.getMessage();
            database.disconnectDB();
        }
        
        return message;
    }

    @Override
    public String update(Client cli) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {                       
            Connection conn = database.getConnection();
            
            String sql = "UPDATE clients SET client_company_name=?, "
                    + "client_contact_name=?, client_address =?, client_email=? "
                    + "client_phone_number=? WHERE client_id= ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, cli.getClientCompanyName());
            ps.setString(2, cli.getClientContactName());
            ps.setString(3, cli.getClientAddress());
            ps.setString(4, cli.getClientEmail());
            ps.setString(5, cli.getClientPhoneNumber());
            ps.setLong(6, cli.getClientId());
            
            ps.executeUpdate();
            message = "Client successfully updated into the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            message = "There was a problem updating the client info: " + ex.getMessage();
            database.disconnectDB();
        }
        
        return message;
    }

    @Override
    public String delete(Client cli) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {           
            
            Connection conn = database.getConnection();
            
            String sql = "DELETE FROM clients WHERE client_id= ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, cli.getClientId());            
            ps.executeUpdate();
            
            message = "Client successfully removed from the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            message = "There was a problem deleting the client from the database: " + ex.getMessage();
            database.disconnectDB();
        }
        
        return message;
    }

    @Override
    public Client findById(long id) {
        Client client = new Client();
        DataBasePG database = new DataBasePG();  
        
        try {           
            
            Connection conn = database.getConnection();
            String sql = "SELECT * FROM clients WHERE client_id = ? LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()){                
                client.setClientId(rs.getLong("client_id"));
                client.setClientCompanyName(rs.getString("client_company_name"));
                client.setClientContactName(rs.getString("client_contact_name"));
                client.setClientAddress(rs.getString("client_address"));
                client.setClientEmail(rs.getString("client_email"));
                client.setClientPhoneNumber(rs.getString("client_phone_number"));                              
            }
            
            database.disconnectDB();
        } catch (SQLException ex) { 
            System.out.println("Error in findById (Client): " + ex);
            database.disconnectDB();
        } 
        
        return client;
    }
    
}
