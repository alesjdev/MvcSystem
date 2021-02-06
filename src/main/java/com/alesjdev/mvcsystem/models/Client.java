package com.alesjdev.mvcsystem.models;

public class Client {
    private long clientId;
    private String clientCompanyName;
    private String clientContactName;
    private String clientAddress;
    private String clientEmail;
    private String clientPhoneNumber;

    public Client() {
    }

    public Client(long clientId) {
        this.clientId = clientId;
    }

    public Client(long clientId, String clientCompanyName, String clientContactName, String clientAddress, String clientEmail, String clientPhoneNumber) {
        this.clientId = clientId;
        this.clientCompanyName = clientCompanyName;
        this.clientContactName = clientContactName;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
    }  
    
    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getClientCompanyName() {
        return clientCompanyName;
    }

    public void setClientCompanyName(String clientCompanyName) {
        this.clientCompanyName = clientCompanyName;
    }

    public String getClientContactName() {
        return clientContactName;
    }

    public void setClientContactName(String clientContactName) {
        this.clientContactName = clientContactName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    @Override
    public String toString() {
        return (clientCompanyName != null) ?
                clientCompanyName : clientContactName;
    }       
}
