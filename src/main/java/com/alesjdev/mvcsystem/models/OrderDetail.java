package com.alesjdev.mvcsystem.models;

import java.text.DecimalFormat;

public class OrderDetail {
    private long detailId;
    private Order order;
    private Product product;
    private int quantity;
    private double amount;
    private String simplifiedAmount;
    
    public OrderDetail() {
    }

    public OrderDetail(long detailId, Order order, Product product, int quantity, double amount) {
        this.detailId = detailId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getSimplifiedAmount(){
        return new DecimalFormat("#.##").format(amount);
    }

    @Override
    public String toString() {
        return String.valueOf(this.amount);
    }
    
    
}
