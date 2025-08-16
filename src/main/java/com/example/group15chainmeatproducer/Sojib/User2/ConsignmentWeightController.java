package com.example.group15chainmeatproducer.Sojib.User2;

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

public class ConsignmentWeightController implements Initializable {

    @FXML
    private TextField packageCountField;
    @FXML
    private TextField totalWeightField;
    @FXML
    private DatePicker confirmationDatePicker;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSubmit;
    @FXML
    private TableView<ConsignmentWeight> consignmentTable;
    @FXML
    private TableColumn<ConsignmentWeight, String> colPackageCount;
    @FXML
    private TableColumn<ConsignmentWeight, String> colTotalWeight;
    @FXML
    private TableColumn<ConsignmentWeight, String> colConfirmationDate;

    private ArrayList<ConsignmentWeight> consignmentList;
    private final String DATA_FILE = "consignmentWeight.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadData();
    }

    private void setupTable() {
        colPackageCount.setCellValueFactory(new PropertyValueFactory<>("packageCount"));
        colTotalWeight.setCellValueFactory(new PropertyValueFactory<>("totalWeight"));
        colConfirmationDate.setCellValueFactory(new PropertyValueFactory<>("confirmationDate"));
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            consignmentList = (ArrayList<ConsignmentWeight>) ois.readObject();
        } catch (Exception e) {
            consignmentList = new ArrayList<>();
        }
        refreshTable();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(consignmentList);
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void refreshTable() {
        ObservableList<ConsignmentWeight> observableList = FXCollections.observableArrayList(consignmentList);
        consignmentTable.setItems(observableList);
    }

    @FXML
    private void handleSubmitConfirmation() {
        if (validateInput()) {
            String packageCount = packageCountField.getText();
            String totalWeight = totalWeightField.getText();
            String confirmationDate = confirmationDatePicker.getValue() != null ? confirmationDatePicker.getValue().toString() : "";

            ConsignmentWeight consignment = new ConsignmentWeight(packageCount, totalWeight, confirmationDate);
            consignmentList.add(consignment);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Consignment weight confirmed successfully!");
        }
    }

    private boolean validateInput() {
        if (packageCountField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Package Count");
            return false;
        }
        if (totalWeightField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Total Weight");
            return false;
        }
        if (confirmationDatePicker.getValue() == null) {
            showAlert("Error", "Please select Confirmation Date");
            return false;
        }
        try {
            Integer.parseInt(packageCountField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Error", "Package Count must be a valid number");
            return false;
        }
        try {
            Double.parseDouble(totalWeightField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Error", "Total Weight must be a valid number");
            return false;
        }
        return true;
    }

    private void clearFields() {
        packageCountField.clear();
        totalWeightField.clear();
        confirmationDatePicker.setValue(null);
    }

    @FXML
    private void handleBack() {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EC_MenuPage.fxml"));
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