package com.example.group15chainmeatproducer.Saiful.User3.models;

import java.io.Serializable;
import java.time.LocalDate;

public class PackagingStandard implements Serializable {
    private String batchId;
    private String status;
    private LocalDate approvalDate;
    private String labelCheck;
    private String photoPath;

    public PackagingStandard() {
    }

    public PackagingStandard(String batchId, String status, LocalDate approvalDate, String labelCheck, String photoPath) {
        this.batchId = batchId;
        this.status = status;
        this.approvalDate = approvalDate;
        this.labelCheck = labelCheck;
        this.photoPath = photoPath;
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

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getLabelCheck() {
        return labelCheck;
    }

    public void setLabelCheck(String labelCheck) {
        this.labelCheck = labelCheck;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
