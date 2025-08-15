package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

public class EmergencyAlertDetail {
    private String machineId;
    private String issueReported;
    private String priorityLevel;
    private String currentStatus;

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getIssueReported() {
        return issueReported;
    }

    public void setIssueReported(String issueReported) {
        this.issueReported = issueReported;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
