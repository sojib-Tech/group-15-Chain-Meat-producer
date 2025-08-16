package com.example.group15chainmeatproducer.Saiful.User3.models;

import java.io.Serializable;
import java.time.LocalDate;

public class HACCPCompliance implements Serializable {
    private String controlPoint;
    private String status;
    private LocalDate monitorDate;
    private String observation;
    private String parameters;
    private String notes;

    public HACCPCompliance() {
    }

    public HACCPCompliance(String controlPoint, String status, LocalDate monitorDate, String observation, String parameters, String notes) {
        this.controlPoint = controlPoint;
        this.status = status;
        this.monitorDate = monitorDate;
        this.observation = observation;
        this.parameters = parameters;
        this.notes = notes;
    }

    public String getControlPoint() {
        return controlPoint;
    }

    public void setControlPoint(String controlPoint) {
        this.controlPoint = controlPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getMonitorDate() {
        return monitorDate;
    }

    public void setMonitorDate(LocalDate monitorDate) {
        this.monitorDate = monitorDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}