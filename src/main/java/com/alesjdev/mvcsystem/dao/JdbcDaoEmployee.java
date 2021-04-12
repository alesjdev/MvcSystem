package com.alesjdev.mvcsystem.dao;

import com.alesjdev.mvcsystem.dbconnection.DataBasePG;
import com.alesjdev.mvcsystem.models.Employee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcDaoEmployee implements IDaoEmployee {
    //Method to retrieve full list of categories.
    @Override
    public List<Employee> listAll() {
        
        List <Employee> employeeList = new ArrayList<>();
        DataBasePG database = new DataBasePG();
        
        try {                       
            Connection conn = database.getConnection();
            String sql = "SELECT * FROM employees";
            PreparedStatement ps = conn.prepareStatement(sql);            
            ResultSet rs = ps.executeQuery();
            
            
            while (rs.next()){
                // Get all the employee data
                long id = rs.getLong("employee_id");
                String firstName = rs.getString("employee_first_name");
                String lastName = rs.getString("employee_last_name");
                Date dob = rs.getDate("employee_dob");
                long reportsTo = rs.getLong("employee_reports_to");
                int extension = rs.getInt("employee_extension");
                
                // Create the employee
                Employee employee = new Employee(id, firstName, lastName,
                    dob, reportsTo, extension);
                
                // If the employee reports to someone, get his/her name and save it too.
                if (reportsTo > 0) {
                    Employee boss = findById(reportsTo);
                    employee.setEmployeeBoss(boss.toString());
                } else {
                    employee.setEmployeeBoss("");
                }
                
                employeeList.add(employee);
            }
            
            database.disconnectDB();
        } catch (SQLException ex) { 
            System.out.println("Error in listAll (Employees): " + ex.getMessage());
            
        } finally {
            if (database.getConnection() != null){
                database.disconnectDB();
            }
        }
        
        return employeeList;
    }

    @Override
    public String insert(Employee emp) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {                       
            Connection conn = database.getConnection();
            
            String sql = "INSERT INTO employees (employee_id, employee_first_name, "
                    + "employee_last_name, employee_dob, employee_reports_to, employee_extension) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, emp.getEmployeeId());
            ps.setString(2, emp.getEmployeeFirstName());
            ps.setString(3, emp.getEmployeeLastName());
            ps.setDate(4, emp.getEmployeeDob());
            ps.setLong(5, emp.getEmployeeReportsTo());
            ps.setInt(6, emp.getEmployeeExtension());
            
            ps.executeUpdate();
            message = "Employee successfully added to the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "There was a problem adding the new employee: " + ex.getMessage();
            
        } finally {
            if (database.getConnection() != null){
                database.disconnectDB();
            }
        }
        
        return message;
    }

    @Override
    public String update(Employee emp) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {                      
            Connection conn = database.getConnection();
            
            String sql = "UPDATE employees SET employee_first_name= ?, "
                    + "employee_last_name= ?, employee_dob= ?, "
                    + "employee_reports_to= ?, employee_extension= ? "
                    + "WHERE employee_id= ? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            
            ps.setString(1, emp.getEmployeeFirstName());
            ps.setString(2, emp.getEmployeeLastName());
            ps.setDate(3, emp.getEmployeeDob());
            ps.setLong(4, emp.getEmployeeReportsTo());
            ps.setInt(5, emp.getEmployeeExtension());
            ps.setLong(6, emp.getEmployeeId());
            
            ps.executeUpdate();
            message = "Employee data successfully updated in the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "There was a problem updating the employee data: " + ex.getMessage();
            
        } finally {
            if (database.getConnection() != null){
                database.disconnectDB();
            }
        }
        
        return message;
    }

    @Override
    public String delete(Employee emp) {
        String message;
        DataBasePG database = new DataBasePG();
        
        try {           
            
            Connection conn = database.getConnection();
            
            String sql = "DELETE FROM employees WHERE employee_id= ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, emp.getEmployeeId());            
            ps.executeUpdate();
            
            message = "Employee successfully removed from the database.";
            
            database.disconnectDB();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "There was a problem deleting the employee from the database: " + ex.getMessage();
            
        } finally {
            if (database.getConnection() != null){
                database.disconnectDB();
            }
        }
        
        return message;
    }

    @Override
    public Employee findById(long id) {
        Employee employee = new Employee();
        DataBasePG database = new DataBasePG();
        
        try {           
            
            Connection conn = database.getConnection();
            String sql = "SELECT * FROM employees WHERE employee_id = ? LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()){                
                employee.setEmployeeId(rs.getLong("employee_id"));
                employee.setEmployeeFirstName(rs.getString("employee_first_name"));
                employee.setEmployeeLastName(rs.getString("employee_last_name"));
                employee.setEmployeeDob(rs.getDate("employee_dob"));
                employee.setEmployeeReportsTo(rs.getLong("employee_reports_to"));
                employee.setEmployeeExtension(rs.getInt("employee_extension"));               
            }
            
            database.disconnectDB();
        } catch (SQLException ex) { 
            System.out.println("Error in findById (Employee): " + ex);
            
        } finally {
            if (database.getConnection() != null){
                database.disconnectDB();
            }
        }
        
        return employee;
    }
    
}
