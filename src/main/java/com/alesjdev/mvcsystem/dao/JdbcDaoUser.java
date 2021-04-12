package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.dbconnection.DataBasePG;
import com.alesjdev.mvcsystem.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;


public class JdbcDaoUser implements IDaoUser {

    @Override
    public User createUser(User user) {
        DataBasePG db = new DataBasePG();
        Connection conn = db.getConnection();
        try {
            String sql = "INSERT INTO users (username, password, account_type) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAccountType());
            ps.executeUpdate();
            
            int generatedId;
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            
            if(generatedKeys.next()){
                generatedId = generatedKeys.getInt(1);
                user.setUserId(generatedId);
            }
            
            db.disconnectDB();
            
        } catch (PSQLException e) {
            e.printStackTrace();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        
        } finally {
            if (db.getConnection() != null){
                db.disconnectDB();
            }
        }
        
        return user;
    }

    @Override
    public User validateUser(String username, String password) {
        User user = null;
        DataBasePG db = new DataBasePG();
        
        try {
            Connection conn = db.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long id_db = rs.getLong("user_id");
                String user_db = rs.getString("username");
                String pass_db = rs.getString("password");
                int type_db = rs.getInt("account_type");
                user = new User(user_db, pass_db, type_db);
                user.setUserId(id_db);
            }
            
            db.disconnectDB();
                      
        } catch (SQLException e) {
            System.err.println("Error retrieving user from database: " + e.getMessage());
            e.printStackTrace();
            
        } finally {
            if (db.getConnection() != null){
                db.disconnectDB();
            }
        }
        
        return user;
    }
    
    @Override
    public String deleteUser (User user) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {           
            
            Connection conn = database.getConnection();
            
            String sql = "DELETE FROM users WHERE user_id= ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, user.getUserId());            
            ps.executeUpdate();
            
            message = "User successfully removed from the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            message = "There was a problem removing the user from the database: " + ex.getMessage();
            database.disconnectDB();
        }
        
        return message;
    }

    @Override
    public int verifyCode(int code) {
        int accountType=-1;
        DataBasePG db = new DataBasePG();
        
        try {
            Connection conn = db.getConnection();
            String sql = "SELECT user_type FROM user_codes WHERE validation_code = ? LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, code);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){       
                String userType = rs.getString("user_type");
                switch(userType){
                    case "manager_account":
                        accountType=0;
                        break;
                    case "employee_account":
                        accountType=1;
                        break;
                    default:
                        accountType=-1;
                }
            }
            
            db.disconnectDB();
                      
        } catch (SQLException e) {
            System.err.println("Error retrieving user from database: " + e.getMessage());
        } finally {
            if (db.getConnection() != null){
                db.disconnectDB();
            }
        }
        
        return accountType;
    }
}
