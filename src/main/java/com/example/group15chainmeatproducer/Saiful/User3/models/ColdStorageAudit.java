package com.example.group15chainmeatproducer.Saiful.User3.models;

import java.io.Serializable;
import java.time.LocalDate;

public class ColdStorageAudit implements Serializable {
    private String roomId;
    private String temperature;
    private LocalDate auditDate;
    private String status;
    private String notes;

    public ColdStorageAudit() {
    }

    public ColdStorageAudit(String roomId, String temperature, LocalDate auditDate, String status, String notes) {
        this.roomId = roomId;
        this.temperature = temperature;
        this.auditDate = auditDate;
        this.status = status;
        this.notes = notes;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public LocalDate getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(LocalDate auditDate) {
        this.auditDate = auditDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
