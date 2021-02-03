package com.alesjdev.mvcsystem.models;

public class Supplier {
    private long supplierId;
    private String supplierName;
    private String supplierContactName;
    private String supplierPhoneNumber;

    
    public Supplier() {
    }

    public Supplier(long supplierId) {
        this.supplierId = supplierId;
    }

    public Supplier(long supplierId, String supplierName, String supplierContactName, String supplierPhoneNumber) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierContactName = supplierContactName;
        this.supplierPhoneNumber = supplierPhoneNumber;
    }
    
    
    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierContactName() {
        return supplierContactName;
    }

    public void setSupplierContactName(String supplierContactName) {
        this.supplierContactName = supplierContactName;
    }

    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    public void setSupplierPhoneNumber(String supplierPhoneNumber) {
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    
    @Override
    public String toString() {
        return this.supplierName;
    }   
}
