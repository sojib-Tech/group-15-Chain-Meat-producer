package com.example.group15chainmeatproducer.Saiful.User4.models;

import java.io.Serializable;
import java.time.LocalDate;

public class MonthlyReport implements Serializable {
    private String reportType;
    private String category;
    private LocalDate reportDate;
    private String status;
    private String dateFilter;

    public MonthlyReport() {
    }

    public MonthlyReport(String reportType, String category, LocalDate reportDate, String status, String dateFilter) {
        this.reportType = reportType;
        this.category = category;
        this.reportDate = reportDate;
        this.status = status;
        this.dateFilter = dateFilter;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(String dateFilter) {
        this.dateFilter = dateFilter;
    }
}
