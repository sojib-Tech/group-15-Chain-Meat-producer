package com.example.group15chainmeatproducer.SakibOme.LogisticsManager;

import java.time.LocalDate;

public class ShipmentScheduleRecord {
    private String shipmentId;
    private String status;
    private LocalDate plannedDate;
    private LocalDate actualDate;
    private String delay;
    private String notes;

    public ShipmentScheduleRecord() {
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(LocalDate plannedDate) {
        this.plannedDate = plannedDate;
    }

    public LocalDate getActualDate() {
        return actualDate;
    }

    public void setActualDate(LocalDate actualDate) {
        this.actualDate = actualDate;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
