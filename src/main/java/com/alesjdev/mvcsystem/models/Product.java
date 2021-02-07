package com.alesjdev.mvcsystem.models;

public class Product {
    private long productId;
    private Supplier supplier;
    private Category category;
    private String productName;
    private double productUnitPrice;
    private int productStock;

    
    public Product() {
    }

    public Product(long productId) {
        this.productId = productId;
    }

    public Product(long productId, Supplier supplier, Category category, String productName, double productUnitPrice, int productStock) {
        this.productId = productId;
        this.supplier = supplier;
        this.category = category;
        this.productName = productName;
        this.productUnitPrice = productUnitPrice;
        this.productStock = productStock;
    }
    
    
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(double productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    @Override
    public String toString() {
        return this.productName;
    }
    
}
