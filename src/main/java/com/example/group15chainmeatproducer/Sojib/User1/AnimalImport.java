package com.example.group15chainmeatproducer.Sojib.User1;

import java.io.Serializable;

public class AnimalImport implements Serializable {
    private String animalId;
    private String animalType;
    private String countryOfImport;
    private String importDate;

    public AnimalImport() {
    }

    public AnimalImport(String animalId, String animalType, String countryOfImport, String importDate) {
        this.animalId = animalId;
        this.animalType = animalType;
        this.countryOfImport = countryOfImport;
        this.importDate = importDate;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getCountryOfImport() {
        return countryOfImport;
    }

    public void setCountryOfImport(String countryOfImport) {
        this.countryOfImport = countryOfImport;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }
}