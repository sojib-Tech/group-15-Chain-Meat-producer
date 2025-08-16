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

public class MachineCalibrationController implements Initializable {

    @FXML
    private TextField machineIdField;
    @FXML
    private ComboBox<String> calibrationStatusComboBox;
    @FXML
    private DatePicker calibrationDatePicker;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSaveLog;
    @FXML
    private TableView<MachineCalibration> machineCalibrationTable;
    @FXML
    private TableColumn<MachineCalibration, String> colMachineId;
    @FXML
    private TableColumn<MachineCalibration, String> colCalibrationStatus;
    @FXML
    private TableColumn<MachineCalibration, String> colCalibrationDate;

    private ArrayList<MachineCalibration> machineCalibrationList;
    private final String DATA_FILE = "machineCalibration.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        setupStatusComboBox();
        loadData();
    }

    private void setupTable() {
        colMachineId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colCalibrationStatus.setCellValueFactory(new PropertyValueFactory<>("calibrationStatus"));
        colCalibrationDate.setCellValueFactory(new PropertyValueFactory<>("calibrationDate"));
    }

    private void setupStatusComboBox() {
        calibrationStatusComboBox.getItems().addAll("Valid", "Expired", "Pending");
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
        machineCalibrationTable.setItems(observableList);
    }

    @FXML
    private void handleSaveLog() {
        if (validateInput()) {
            String machineId = machineIdField.getText();
            String calibrationStatus = calibrationStatusComboBox.getValue();
            String calibrationDate = calibrationDatePicker.getValue() != null ? calibrationDatePicker.getValue().toString() : "";

            MachineCalibration machineCalibration = new MachineCalibration(machineId, calibrationStatus, calibrationDate);
            machineCalibrationList.add(machineCalibration);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Machine calibration data saved successfully!");
        }
    }

    private boolean validateInput() {
        if (machineIdField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Machine ID");
            return false;
        }
        if (calibrationStatusComboBox.getValue() == null) {
            showAlert("Error", "Please select Calibration Status");
            return false;
        }
        if (calibrationDatePicker.getValue() == null) {
            showAlert("Error", "Please select Calibration Date");
            return false;
        }
        return true;
    }

    private void clearFields() {
        machineIdField.clear();
        calibrationStatusComboBox.setValue(null);
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