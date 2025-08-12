package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final StringProperty orderId = new SimpleStringProperty(this, "orderId");
    private final StringProperty customerName = new SimpleStringProperty(this, "customerName");
    private final StringProperty itemsSummary = new SimpleStringProperty(this, "itemsSummary");
    private final StringProperty status = new SimpleStringProperty(this, "status");
    private final ObjectProperty<LocalDate> orderDate = new SimpleObjectProperty<>(this, "orderDate");

    public Order() {
    }

    public Order(String orderId, String customerName, String itemsSummary, String status, LocalDate orderDate) {
        setOrderId(orderId);
        setCustomerName(customerName);
        setItemsSummary(itemsSummary);
        setStatus(status);
        setOrderDate(orderDate);
    }

    public String getOrderId() {
        return orderId.get();
    }

    public void setOrderId(String value) {
        orderId.set(value);
    }

    public StringProperty orderIdProperty() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String value) {
        customerName.set(value);
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public String getItemsSummary() {
        return itemsSummary.get();
    }

    public void setItemsSummary(String value) {
        itemsSummary.set(value);
    }

    public StringProperty itemsSummaryProperty() {
        return itemsSummary;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String value) {
        status.set(value);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate.get();
    }

    public void setOrderDate(LocalDate value) {
        orderDate.set(value);
    }

    public ObjectProperty<LocalDate> orderDateProperty() {
        return orderDate;
    }
}
