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

public class MachineBookingController implements Initializable {

    @FXML
    private TextField machineIdField;
    @FXML
    private TextField bookerNameField;
    @FXML
    private TextField animalIdField;
    @FXML
    private DatePicker bookingDatePicker;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSaveBooking;
    @FXML
    private TableView<MachineBooking> machineBookingTable;
    @FXML
    private TableColumn<MachineBooking, String> colMachineId;
    @FXML
    private TableColumn<MachineBooking, String> colBookerName;
    @FXML
    private TableColumn<MachineBooking, String> colAnimalId;
    @FXML
    private TableColumn<MachineBooking, String> colBookingDate;

    private ArrayList<MachineBooking> machineBookingList;
    private final String DATA_FILE = "machineBooking.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadData();
    }

    private void setupTable() {
        colMachineId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colBookerName.setCellValueFactory(new PropertyValueFactory<>("bookerName"));
        colAnimalId.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        colBookingDate.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            machineBookingList = (ArrayList<MachineBooking>) ois.readObject();
        } catch (Exception e) {
            machineBookingList = new ArrayList<>();
        }
        refreshTable();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(machineBookingList);
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void refreshTable() {
        ObservableList<MachineBooking> observableList = FXCollections.observableArrayList(machineBookingList);
        machineBookingTable.setItems(observableList);
    }

    @FXML
    private void handleSaveBooking() {
        if (validateInput()) {
            String machineId = machineIdField.getText();
            String bookerName = bookerNameField.getText();
            String animalId = animalIdField.getText();
            String bookingDate = bookingDatePicker.getValue() != null ? bookingDatePicker.getValue().toString() : "";

            MachineBooking machineBooking = new MachineBooking(machineId, bookerName, animalId, bookingDate);
            machineBookingList.add(machineBooking);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Machine booking data saved successfully!");
        }
    }

    private boolean validateInput() {
        if (machineIdField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Machine ID");
            return false;
        }
        if (bookerNameField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Booker Name");
            return false;
        }
        if (animalIdField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Animal ID");
            return false;
        }
        if (bookingDatePicker.getValue() == null) {
            showAlert("Error", "Please select Booking Date");
            return false;
        }
        return true;
    }

    private void clearFields() {
        machineIdField.clear();
        bookerNameField.clear();
        animalIdField.clear();
        bookingDatePicker.setValue(null);
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