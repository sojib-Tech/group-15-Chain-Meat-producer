package com.example.group15chainmeatproducer.Saiful.User3.models;

import java.io.Serializable;
import java.time.LocalDate;

public class HygieneChecklist implements Serializable {
    private String batchId;
    private String status;
    private LocalDate verificationDate;
    private String photoPath;
    private String notes;

    public HygieneChecklist() {
    }

    public HygieneChecklist(String batchId, String status, LocalDate verificationDate, String photoPath, String notes) {
        this.batchId = batchId;
        this.status = status;
        this.verificationDate = verificationDate;
        this.photoPath = photoPath;
        this.notes = notes;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(LocalDate verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}