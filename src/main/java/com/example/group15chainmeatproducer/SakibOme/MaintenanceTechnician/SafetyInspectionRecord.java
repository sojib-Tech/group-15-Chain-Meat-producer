package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

import java.time.LocalDate;

public class SafetyInspectionRecord {
    private String machineId;
    private String machineName;
    private LocalDate inspectionDate;
    private String safetyStatus;
    private String inspectorName;
    private String remarks;
    private LocalDate nextInspectionDate;

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

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getSafetyStatus() {
        return safetyStatus;
    }

    public void setSafetyStatus(String safetyStatus) {
        this.safetyStatus = safetyStatus;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getNextInspectionDate() {
        return nextInspectionDate;
    }

    public void setNextInspectionDate(LocalDate nextInspectionDate) {
        this.nextInspectionDate = nextInspectionDate;
    }
}
