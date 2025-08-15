package com.example.group15chainmeatproducer.Saiful.User4.models;

import java.io.Serializable;
import java.time.LocalDate;

public class PackagingMaterial implements Serializable {
    private String batchId;
    private String issued;
    private String standard;
    private String status;
    private LocalDate usageDate;

    public PackagingMaterial() {
    }

    public PackagingMaterial(String batchId, String issued, String standard, String status, LocalDate usageDate) {
        this.batchId = batchId;
        this.issued = issued;
        this.standard = standard;
        this.status = status;
        this.usageDate = usageDate;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(LocalDate usageDate) {
        this.usageDate = usageDate;
    }
}
