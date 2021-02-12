package com.alesjdev.mvcsystem.models;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private long orderId;
    private Employee employee;
    private Client client;
    private Date orderDate;
    private int discount;
    private double amount;
    private String simplifiedAmount;
    private List<OrderDetail> details = new ArrayList<>();

    public Order() {
    }

    public Order(long orderId, Employee employee, Client client, Date orderDate, int discount, double amount) {
        this.orderId = orderId;
        this.employee = employee;
        this.client = client;
        this.orderDate = orderDate;
        this.discount = discount;
        this.amount = amount;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
    
    public String getSimplifiedAmount(){
        return new DecimalFormat("#.##").format(amount);
    }
        
    @Override
    public String toString() {
        return String.valueOf(this.amount);
    }
}
