package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

import java.time.LocalDate;

public class RequiredPart {
    private String partId;
    private String partName;
    private String quantityNeeded;
    private String supplier;
    private LocalDate expectedDelivery;

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getQuantityNeeded() {
        return quantityNeeded;
    }

    public void setQuantityNeeded(String quantityNeeded) {
        this.quantityNeeded = quantityNeeded;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }
}
