package com.alesjdev.mvcsystem.models;

import java.sql.Date;

public class Employee {
    private long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private Date employeeDob;
    private long employeeReportsTo;
    private int employeeExtension;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public Date getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeDob(Date employeeDob) {
        this.employeeDob = employeeDob;
    }

    public long getEmployeeReportsTo() {
        return employeeReportsTo;
    }

    public void setEmployeeReportsTo(long employeeReportsTo) {
        this.employeeReportsTo = employeeReportsTo;
    }

    public int getEmployeeExtension() {
        return employeeExtension;
    }

    public void setEmployeeExtension(int employeeExtension) {
        this.employeeExtension = employeeExtension;
    }

    @Override
    public String toString() {
        return this.employeeFirstName + " " + this.employeeLastName;
    }
    
    
}
