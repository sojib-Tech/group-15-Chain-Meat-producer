package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

public class UrgentRepairRecord {
    private String machineId;
    private String machineName;
    private String currentIssue;
    private String operatorNotes;
    private String urgencyLevel;

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

    public String getCurrentIssue() {
        return currentIssue;
    }

    public void setCurrentIssue(String currentIssue) {
        this.currentIssue = currentIssue;
    }

    public String getOperatorNotes() {
        return operatorNotes;
    }

    public void setOperatorNotes(String operatorNotes) {
        this.operatorNotes = operatorNotes;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }
}
