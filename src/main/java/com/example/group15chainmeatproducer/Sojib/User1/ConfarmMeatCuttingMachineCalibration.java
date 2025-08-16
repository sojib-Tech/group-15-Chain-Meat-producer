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

public class ConfarmMeatCuttingMachineCalibration implements Initializable {

    @FXML
    private TextField operatorNameField;
    @FXML
    private ComboBox<String> animalIdComboBox;
    @FXML
    private DatePicker calibrationDatePicker;
    @FXML
    private ComboBox<String> machineIdComboBox;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSaveLog;
    @FXML
    private Button btnUpdateStatus;
    @FXML
    private TableView<MachineCalibration> calibrationTableView;
    @FXML
    private TableColumn<MachineCalibration, String> colOperatorName;
    @FXML
    private TableColumn<MachineCalibration, String> colAnimalId;
    @FXML
    private TableColumn<MachineCalibration, String> colMachineId;
    @FXML
    private TableColumn<MachineCalibration, String> colStatus;

    private ArrayList<MachineCalibration> machineCalibrationList;
    private final String DATA_FILE = "machineCalibration.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        setupComboBoxes();
        loadData();
    }

    private void setupTable() {
        colOperatorName.setCellValueFactory(new PropertyValueFactory<>("operatorName"));
        colAnimalId.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        colMachineId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void setupComboBoxes() {
        statusComboBox.getItems().addAll("Valid", "Expired", "Pending");
        animalIdComboBox.getItems().addAll("A001", "A002", "A003", "A004", "A005");
        machineIdComboBox.getItems().addAll("M001", "M002", "M003", "M004", "M005");
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            machineCalibrationList = (ArrayList<MachineCalibration>) ois.readObject();
        } catch (Exception e) {
            machineCalibrationList = new ArrayList<>();
        }
        refreshTable();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(machineCalibrationList);
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void refreshTable() {
        ObservableList<MachineCalibration> observableList = FXCollections.observableArrayList(machineCalibrationList);
        calibrationTableView.setItems(observableList);
    }

    @FXML
    private void saveLog() {
        if (validateInput()) {
            String operatorName = operatorNameField.getText();
            String animalId = animalIdComboBox.getValue();
            String machineId = machineIdComboBox.getValue();
            String status = statusComboBox.getValue();
            String calibrationDate = calibrationDatePicker.getValue() != null ? calibrationDatePicker.getValue().toString() : "";

            MachineCalibration calibration = new MachineCalibration(operatorName, animalId, machineId, status, calibrationDate);
            machineCalibrationList.add(calibration);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Machine calibration data saved successfully!");
        }
    }

    @FXML
    private void updateStatus() {
        MachineCalibration selected = calibrationTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Please select a record to update");
            return;
        }
        selected.setStatus("Updated");
        saveData();
        refreshTable();
        showAlert("Success", "Status updated successfully!");
    }

    private boolean validateInput() {
        if (operatorNameField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Operator Name");
            return false;
        }
        if (animalIdComboBox.getValue() == null) {
            showAlert("Error", "Please select Animal ID");
            return false;
        }
        if (machineIdComboBox.getValue() == null) {
            showAlert("Error", "Please select Machine ID");
            return false;
        }
        if (statusComboBox.getValue() == null) {
            showAlert("Error", "Please select Status");
            return false;
        }
        if (calibrationDatePicker.getValue() == null) {
            showAlert("Error", "Please select Calibration Date");
            return false;
        }
        return true;
    }

    private void clearFields() {
        operatorNameField.clear();
        animalIdComboBox.setValue(null);
        machineIdComboBox.setValue(null);
        statusComboBox.setValue(null);
        calibrationDatePicker.setValue(null);
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