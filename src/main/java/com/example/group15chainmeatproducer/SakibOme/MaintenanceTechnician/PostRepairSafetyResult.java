package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

import java.time.LocalDate;

public class PostRepairSafetyResult {
    private String machineId;
    private LocalDate inspectionDate;
    private String inspector;
    private String safetyStatus;
    private String notes;

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getSafetyStatus() {
        return safetyStatus;
    }

    public void setSafetyStatus(String safetyStatus) {
        this.safetyStatus = safetyStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
