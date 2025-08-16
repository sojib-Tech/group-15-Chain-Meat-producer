package com.example.group15chainmeatproducer.Sojib.User1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AnimalImportController implements Initializable {

    @FXML
    private TextField animalIdField;
    @FXML
    private TextField animalTypeField;
    @FXML
    private ComboBox<String> countryComboBox;
    @FXML
    private DatePicker importDatePicker;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSubmit;
    @FXML
    private TableView<AnimalImport> animalImportTable;
    @FXML
    private TableColumn<AnimalImport, String> colAnimalId;
    @FXML
    private TableColumn<AnimalImport, String> colAnimalType;
    @FXML
    private TableColumn<AnimalImport, String> colCountryOfImport;
    @FXML
    private TableColumn<AnimalImport, String> colImportDate;

    private ArrayList<AnimalImport> animalImportList;
    private final String DATA_FILE = "animalImport.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        setupCountryComboBox();
        loadData();
    }

    private void setupTable() {
        colAnimalId.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        colAnimalType.setCellValueFactory(new PropertyValueFactory<>("animalType"));
        colCountryOfImport.setCellValueFactory(new PropertyValueFactory<>("countryOfImport"));
        colImportDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
    }

    private void setupCountryComboBox() {
        countryComboBox.getItems().addAll(
                "USA", "Canada", "Australia", "Brazil", "Argentina",
                "New Zealand", "United Kingdom", "Germany", "France", "Netherlands"
        );
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            animalImportList = (ArrayList<AnimalImport>) ois.readObject();
        } catch (Exception e) {
            animalImportList = new ArrayList<>();
        }
        refreshTable();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(animalImportList);
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void refreshTable() {
        ObservableList<AnimalImport> observableList = FXCollections.observableArrayList(animalImportList);
        animalImportTable.setItems(observableList);
    }

    @FXML
    private void handleSubmit() {
        if (validateInput()) {
            String animalId = animalIdField.getText();
            String animalType = animalTypeField.getText();
            String country = countryComboBox.getValue();
            String importDate = importDatePicker.getValue() != null ? importDatePicker.getValue().toString() : "";

            AnimalImport animalImport = new AnimalImport(animalId, animalType, country, importDate);
            animalImportList.add(animalImport);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Animal import data saved successfully!");
        }
    }

    private boolean validateInput() {
        if (animalIdField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Animal ID");
            return false;
        }
        if (animalTypeField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Animal Type");
            return false;
        }
        if (countryComboBox.getValue() == null) {
            showAlert("Error", "Please select Country of Import");
            return false;
        }
        if (importDatePicker.getValue() == null) {
            showAlert("Error", "Please select Import Date");
            return false;
        }
        return true;
    }

    private void clearFields() {
        animalIdField.clear();
        animalTypeField.clear();
        countryComboBox.setValue(null);
        importDatePicker.setValue(null);
    }

    @FXML
    private void handleBack() {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FF_MenuPage.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            showAlert("Error", "Failed to go back: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}