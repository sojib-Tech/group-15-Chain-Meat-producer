package com.example.group15chainmeatproducer.Sojib.User1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ScanRecord {
    private final StringProperty animalId;
    private final StringProperty status;

    public ScanRecord(String animalId, String status) {
        this.animalId = new SimpleStringProperty(animalId);
        this.status = new SimpleStringProperty(status);
    }

    public StringProperty animalIdProperty() { return animalId; }
    public StringProperty statusProperty() { return status; }
}
