package com.example.group15chainmeatproducer.Sojib.User1;

import java.io.Serializable;

public class MachineCalibration implements Serializable {
    private String operatorName;
    private String animalId;
    private String machineId;
    private String calibrationStatus;
    private String calibrationDate;
    private String status;

    public MachineCalibration() {
    }

    public MachineCalibration(String machineId, String calibrationStatus, String calibrationDate) {
        this.machineId = machineId;
        this.calibrationStatus = calibrationStatus;
        this.calibrationDate = calibrationDate;
    }

    public MachineCalibration(String operatorName, String animalId, String machineId, String status, String calibrationDate) {
        this.operatorName = operatorName;
        this.animalId = animalId;
        this.machineId = machineId;
        this.status = status;
        this.calibrationDate = calibrationDate;
        this.calibrationStatus = status;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getCalibrationStatus() {
        return calibrationStatus;
    }

    public void setCalibrationStatus(String calibrationStatus) {
        this.calibrationStatus = calibrationStatus;
    }

    public String getCalibrationDate() {
        return calibrationDate;
    }

    public void setCalibrationDate(String calibrationDate) {
        this.calibrationDate = calibrationDate;
    }

    public String getStatus() {
        return status != null ? status : calibrationStatus;
    }

    public void setStatus(String status) {
        this.status = status;
        this.calibrationStatus = status;
    }
}