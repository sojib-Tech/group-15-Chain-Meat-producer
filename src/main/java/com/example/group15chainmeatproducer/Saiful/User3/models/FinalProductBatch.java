package com.example.group15chainmeatproducer.Saiful.User3.models;

import java.io.Serializable;
import java.time.LocalDate;

public class FinalProductBatch implements Serializable {
    private String batchId;
    private String status;
    private LocalDate reviewDate;
    private String qualityRecords;
    private String decisionNotes;
    private String decision;

    public FinalProductBatch() {
    }

    public FinalProductBatch(String batchId, String status, LocalDate reviewDate, String qualityRecords, String decisionNotes, String decision) {
        this.batchId = batchId;
        this.status = status;
        this.reviewDate = reviewDate;
        this.qualityRecords = qualityRecords;
        this.decisionNotes = decisionNotes;
        this.decision = decision;
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

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getQualityRecords() {
        return qualityRecords;
    }

    public void setQualityRecords(String qualityRecords) {
        this.qualityRecords = qualityRecords;
    }

    public String getDecisionNotes() {
        return decisionNotes;
    }

    public void setDecisionNotes(String decisionNotes) {
        this.decisionNotes = decisionNotes;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}