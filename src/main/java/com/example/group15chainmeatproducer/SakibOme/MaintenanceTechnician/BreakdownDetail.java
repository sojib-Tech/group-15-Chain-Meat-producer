package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

public class BreakdownDetail {
    private String machineId;
    private String alertType;
    private String timeReported;
    private String currentStatus;

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getTimeReported() {
        return timeReported;
    }

    public void setTimeReported(String timeReported) {
        this.timeReported = timeReported;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
