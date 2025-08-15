package com.example.group15chainmeatproducer.Saiful.User3.models;

import java.io.Serializable;
import java.time.LocalDate;

public class MeatBatch implements Serializable {
    private String batchId;
    private String qualityStatus;
    private LocalDate evaluationDate;
    private String colorTexture;
    private String labReport;
    private String results;

    public MeatBatch() {
    }

    public MeatBatch(String batchId, String qualityStatus, LocalDate evaluationDate, String colorTexture, String labReport, String results) {
        this.batchId = batchId;
        this.qualityStatus = qualityStatus;
        this.evaluationDate = evaluationDate;
        this.colorTexture = colorTexture;
        this.labReport = labReport;
        this.results = results;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(String qualityStatus) {
        this.qualityStatus = qualityStatus;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getColorTexture() {
        return colorTexture;
    }

    public void setColorTexture(String colorTexture) {
        this.colorTexture = colorTexture;
    }

    public String getLabReport() {
        return labReport;
    }

    public void setLabReport(String labReport) {
        this.labReport = labReport;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}