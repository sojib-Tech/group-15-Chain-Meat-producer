package com.example.group15chainmeatproducer.Sojib.User1;

import java.io.Serializable;

public class CutMeatPieces implements Serializable {
    private String operatorName;
    private String animalId;
    private String numberOfPieces;
    private String cuttingDate;

    public CutMeatPieces() {
    }

    public CutMeatPieces(String animalId, String numberOfPieces, String cuttingDate) {
        this.animalId = animalId;
        this.numberOfPieces = numberOfPieces;
        this.cuttingDate = cuttingDate;
    }

    public CutMeatPieces(String operatorName, String animalId, String numberOfPieces, String cuttingDate) {
        this.operatorName = operatorName;
        this.animalId = animalId;
        this.numberOfPieces = numberOfPieces;
        this.cuttingDate = cuttingDate;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getNumberOfPieces() {
        return numberOfPieces;
    }

    public void setNumberOfPieces(String numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
    }

    public String getCuttingDate() {
        return cuttingDate;
    }

    public void setCuttingDate(String cuttingDate) {
        this.cuttingDate = cuttingDate;
    }
}