package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class IssueReport {
    private final StringProperty orderId = new SimpleStringProperty(this, "orderId");
    private final ObjectProperty<LocalDate> reportDate = new SimpleObjectProperty<>(this, "reportDate");
    private final StringProperty description = new SimpleStringProperty(this, "description");
    private final StringProperty type = new SimpleStringProperty(this, "type");
    private final StringProperty severity = new SimpleStringProperty(this, "severity");

    public IssueReport() {
    }

    public IssueReport(String orderId, LocalDate reportDate, String description, String type, String severity) {
        setOrderId(orderId);
        setReportDate(reportDate);
        setDescription(description);
        setType(type);
        setSeverity(severity);
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

    public LocalDate getReportDate() {
        return reportDate.get();
    }

    public void setReportDate(LocalDate value) {
        reportDate.set(value);
    }

    public ObjectProperty<LocalDate> reportDateProperty() {
        return reportDate;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String value) {
        type.set(value);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getSeverity() {
        return severity.get();
    }

    public void setSeverity(String value) {
        severity.set(value);
    }

    public StringProperty severityProperty() {
        return severity;
    }
}
