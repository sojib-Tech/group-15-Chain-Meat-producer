package com.example.group15chainmeatproducer.SakibOme.LogisticsManager;

import java.time.LocalDate;

public class LogisticsProvider {
    private String providerName;
    private String serviceType;
    private LocalDate contractExpiry;
    private String contactPerson;
    private String contactInfo;
    private String rating;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getContractExpiry() {
        return contractExpiry;
    }

    public void setContractExpiry(LocalDate contractExpiry) {
        this.contractExpiry = contractExpiry;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
