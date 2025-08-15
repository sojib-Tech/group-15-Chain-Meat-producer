package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

import java.time.LocalDate;

public class MaintenanceRecord {
    private String machineId;
    private String machineName;
    private LocalDate lastMaintenanceDate;
    private LocalDate nextScheduledMaintenance;
    private String assignedTechnician;
    private String maintenanceStatus;
    private String maintenanceType;
    private String notes;

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public LocalDate getNextScheduledMaintenance() {
        return nextScheduledMaintenance;
    }

    public void setNextScheduledMaintenance(LocalDate nextScheduledMaintenance) {
        this.nextScheduledMaintenance = nextScheduledMaintenance;
    }

    public String getAssignedTechnician() {
        return assignedTechnician;
    }

    public void setAssignedTechnician(String assignedTechnician) {
        this.assignedTechnician = assignedTechnician;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
