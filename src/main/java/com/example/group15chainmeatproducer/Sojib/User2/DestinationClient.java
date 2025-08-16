package com.example.group15chainmeatproducer.Sojib.User2;

import java.io.Serializable;

public class DestinationClient implements Serializable {
    private String destinationCountry;
    private String clientCompanyName;
    private String entryDate;

    public DestinationClient() {
    }

    public DestinationClient(String destinationCountry, String clientCompanyName, String entryDate) {
        this.destinationCountry = destinationCountry;
        this.clientCompanyName = clientCompanyName;
        this.entryDate = entryDate;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getClientCompanyName() {
        return clientCompanyName;
    }

    public void setClientCompanyName(String clientCompanyName) {
        this.clientCompanyName = clientCompanyName;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
}