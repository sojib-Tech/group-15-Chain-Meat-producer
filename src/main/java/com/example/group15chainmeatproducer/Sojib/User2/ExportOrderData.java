package com.example.group15chainmeatproducer.Sojib.User2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class ExportOrderData implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String customerId;
    private final String customerName;
    private final String fullAddress;
    private final String orderDate;
    private final String phoneNumber;

    public ExportOrderData(String customerId, String customerName, String fullAddress, String orderDate, String phoneNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.fullAddress = fullAddress;
        this.orderDate = orderDate;
        this.phoneNumber = phoneNumber;
    }

    // Getters for use in FXML controllers
    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Properties for TableView binding
    public StringProperty customerIdProperty() {
        return new SimpleStringProperty(customerId);
    }

    public StringProperty customerNameProperty() {
        return new SimpleStringProperty(customerName);
    }

    public StringProperty fullAddressProperty() {
        return new SimpleStringProperty(fullAddress);
    }

    public StringProperty orderDateProperty() {
        return new SimpleStringProperty(orderDate);
    }

    public StringProperty phoneNumberProperty() {
        return new SimpleStringProperty(phoneNumber);
    }
}
