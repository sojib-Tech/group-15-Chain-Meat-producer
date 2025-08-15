package com.example.group15chainmeatproducer.SakibOme.LogisticsManager;

import java.time.LocalDate;

public class ShipmentPlan {
    private String shipmentId;
    private String type;            // Inbound / Outbound
    private LocalDate date;         // Scheduled Transport Date
    private String priority;        // High / Medium / Low
    private String status;          // e.g., Pending / Confirmed

    public ShipmentPlan() {
    }

    public ShipmentPlan(String shipmentId, String type, LocalDate date, String priority, String status) {
        this.shipmentId = shipmentId;
        this.type = type;
        this.date = date;
        this.priority = priority;
        this.status = status;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
