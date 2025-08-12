package com.example.group15chainmeatproducer.Ami.Customer;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderSummary implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate orderDate;
    private String orderId;
    private String itemsSummary;
    private String status;

    public OrderSummary(LocalDate orderDate, String orderId, String itemsSummary, String status) {
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.itemsSummary = itemsSummary;
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemsSummary() {
        return itemsSummary;
    }

    public void setItemsSummary(String itemsSummary) {
        this.itemsSummary = itemsSummary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
