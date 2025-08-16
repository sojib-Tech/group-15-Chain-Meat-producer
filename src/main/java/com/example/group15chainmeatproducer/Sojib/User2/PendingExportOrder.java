package com.example.group15chainmeatproducer.Sojib.User2;

import java.io.Serializable;

public class PendingExportOrder implements Serializable {
    private String username;
    private String password;
    private String orderId;
    private String customerName;
    private String items;
    private String status;

    public PendingExportOrder() {
    }

    public PendingExportOrder(String username, String password, String orderId, String customerName, String items, String status) {
        this.username = username;
        this.password = password;
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}