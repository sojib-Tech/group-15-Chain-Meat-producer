package com.example.group15chainmeatproducer.Sojib.User2;

import java.io.Serializable;

public class ConsignmentWeight implements Serializable {
    private String packageCount;
    private String totalWeight;
    private String confirmationDate;

    public ConsignmentWeight() {
    }

    public ConsignmentWeight(String packageCount, String totalWeight, String confirmationDate) {
        this.packageCount = packageCount;
        this.totalWeight = totalWeight;
        this.confirmationDate = confirmationDate;
    }

    public String getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(String packageCount) {
        this.packageCount = packageCount;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(String confirmationDate) {
        this.confirmationDate = confirmationDate;
    }
}