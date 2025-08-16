package com.example.group15chainmeatproducer.Saiful.User4.models;

import java.io.Serializable;
import java.time.LocalDate;

public class DamagedStock implements Serializable {
    private String itemId;
    private String status;
    private LocalDate managementDate;
    private String reason;
    private String quantity;

    public DamagedStock() {
    }

    public DamagedStock(String itemId, String status, LocalDate managementDate, String reason, String quantity) {
        this.itemId = itemId;
        this.status = status;
        this.managementDate = managementDate;
        this.reason = reason;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getManagementDate() {
        return managementDate;
    }

    public void setManagementDate(LocalDate managementDate) {
        this.managementDate = managementDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
