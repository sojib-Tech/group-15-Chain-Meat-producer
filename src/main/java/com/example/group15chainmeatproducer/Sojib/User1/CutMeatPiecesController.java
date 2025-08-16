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

public class CutMeatPiecesController implements Initializable {

    @FXML
    private TextField animalIdField;
    @FXML
    private TextField numberOfPiecesField;
    @FXML
    private DatePicker cuttingDatePicker;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSubmit;
    @FXML
    private TableView<CutMeatPieces> cutMeatPiecesTable;
    @FXML
    private TableColumn<CutMeatPieces, String> colAnimalId;
    @FXML
    private TableColumn<CutMeatPieces, String> colNumberOfPieces;
    @FXML
    private TableColumn<CutMeatPieces, String> colCuttingDate;

    private ArrayList<CutMeatPieces> cutMeatPiecesList;
    private final String DATA_FILE = "cutMeatPieces.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadData();
    }

    private void setupTable() {
        colAnimalId.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        colNumberOfPieces.setCellValueFactory(new PropertyValueFactory<>("numberOfPieces"));
        colCuttingDate.setCellValueFactory(new PropertyValueFactory<>("cuttingDate"));
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            cutMeatPiecesList = (ArrayList<CutMeatPieces>) ois.readObject();
        } catch (Exception e) {
            cutMeatPiecesList = new ArrayList<>();
        }
        refreshTable();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(cutMeatPiecesList);
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void refreshTable() {
        ObservableList<CutMeatPieces> observableList = FXCollections.observableArrayList(cutMeatPiecesList);
        cutMeatPiecesTable.setItems(observableList);
    }

    @FXML
    private void handleSubmit() {
        if (validateInput()) {
            String animalId = animalIdField.getText();
            String numberOfPieces = numberOfPiecesField.getText();
            String cuttingDate = cuttingDatePicker.getValue() != null ? cuttingDatePicker.getValue().toString() : "";

            CutMeatPieces cutMeatPieces = new CutMeatPieces(animalId, numberOfPieces, cuttingDate);
            cutMeatPiecesList.add(cutMeatPieces);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Cut meat pieces data saved successfully!");
        }
    }

    private boolean validateInput() {
        if (animalIdField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Animal ID");
            return false;
        }
        if (numberOfPiecesField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Number of Pieces");
            return false;
        }
        if (cuttingDatePicker.getValue() == null) {
            showAlert("Error", "Please select Cutting Date");
            return false;
        }
        try {
            Integer.parseInt(numberOfPiecesField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Error", "Number of Pieces must be a valid number");
            return false;
        }
        return true;
    }

    private void clearFields() {
        animalIdField.clear();
        numberOfPiecesField.clear();
        cuttingDatePicker.setValue(null);
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