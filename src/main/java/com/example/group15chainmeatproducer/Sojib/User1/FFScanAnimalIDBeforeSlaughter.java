package com.example.group15chainmeatproducer.Sojib.User1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;

public class FFScanAnimalIDBeforeSlaughter {

    @FXML private TextField animalIdField;
    @FXML private TextField scanStatusField;
    @FXML private Label labelSuccess;
    @FXML private Label labelInvalid;
    @FXML private TableView<ScanResult> scanTable;
    @FXML private TableColumn<ScanResult, String> colAnimalId;
    @FXML private TableColumn<ScanResult, String> colStatus;
    @FXML private Button btnBack;

    private final File hygieneChecklistFile = new File("checkIn.bin");
    private final ObservableList<ScanResult> scanData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colAnimalId.setCellValueFactory(data -> data.getValue().animalIdProperty());
        colStatus.setCellValueFactory(data -> data.getValue().statusProperty());
        scanTable.setItems(scanData);
    }

    @FXML
    public void handleScan() {
        String inputId = animalIdField.getText().trim();
        if (inputId.isEmpty()) {
            showAlert("Please enter an Animal ID.");
            return;
        }

        boolean isValid = isAnimalIdRegistered(inputId);
        String status = isValid ? "Valid" : "Invalid";

        scanStatusField.setText(status);
        labelSuccess.setVisible(isValid);
        labelInvalid.setVisible(!isValid);

        scanData.add(new ScanResult(inputId, status));
    }

    private boolean isAnimalIdRegistered(String animalId) {
        if (!hygieneChecklistFile.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(hygieneChecklistFile))) {
            while (true) {
                checkIn.HygieneData record = (checkIn.HygieneData) ois.readObject();
                if (record.getAnimalId().equalsIgnoreCase(animalId)) {
                    return true;
                }
            }
        } catch (EOFException eof) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void goBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FF_MenuPage.fxml"));
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Failed to load menu page.");
            e.printStackTrace();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // Inner class to represent table rows
    public static class ScanResult {
        private final StringProperty animalId;
        private final StringProperty status;

        public ScanResult(String animalId, String status) {
            this.animalId = new SimpleStringProperty(animalId);
            this.status = new SimpleStringProperty(status);
        }

        public StringProperty animalIdProperty() { return animalId; }
        public StringProperty statusProperty() { return status; }
    }
}
