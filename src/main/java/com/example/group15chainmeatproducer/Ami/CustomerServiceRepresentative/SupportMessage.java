package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SupportMessage {
    private final StringProperty customerName = new SimpleStringProperty(this, "customerName");
    private final StringProperty lastMessagePreview = new SimpleStringProperty(this, "lastMessagePreview");
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>(this, "date");
    private final StringProperty status = new SimpleStringProperty(this, "status");
    private final StringProperty priority = new SimpleStringProperty(this, "priority");

    public SupportMessage() {
    }

    public SupportMessage(String customerName, String lastMessagePreview, LocalDate date, String status, String priority) {
        setCustomerName(customerName);
        setLastMessagePreview(lastMessagePreview);
        setDate(date);
        setStatus(status);
        setPriority(priority);
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

    public String getLastMessagePreview() {
        return lastMessagePreview.get();
    }

    public void setLastMessagePreview(String value) {
        lastMessagePreview.set(value);
    }

    public StringProperty lastMessagePreviewProperty() {
        return lastMessagePreview;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate value) {
        date.set(value);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
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

    public String getPriority() {
        return priority.get();
    }

    public void setPriority(String value) {
        priority.set(value);
    }

    public StringProperty priorityProperty() {
        return priority;
    }
}
