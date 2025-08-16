package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import java.io.Serializable;
import java.time.LocalDate;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderId;
    private String customerName;
    private String items;
    private String status;
    private LocalDate orderDate;
    private LocalDate updateDate;

    public Order() {
    }

    public Order(String orderId, String customerName, String items, String status, LocalDate orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.status = status;
        this.orderDate = orderDate;
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}
