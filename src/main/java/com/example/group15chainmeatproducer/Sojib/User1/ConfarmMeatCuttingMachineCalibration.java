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
import java.time.LocalDate;
import java.util.*;

public class ConfarmMeatCuttingMachineCalibration {

    @FXML private TextField operatorNameField;
    @FXML private ComboBox<String> animalIdComboBox;
    @FXML private ComboBox<String> machineIdComboBox;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private DatePicker calibrationDatePicker;

    @FXML private TableView<CalibrationData> calibrationTableView;
    @FXML private TableColumn<CalibrationData, String> colOperatorName;
    @FXML private TableColumn<CalibrationData, String> colAnimalId;
    @FXML private TableColumn<CalibrationData, String> colMachineId;
    @FXML private TableColumn<CalibrationData, String> colStatus;

    private final File checkInFile = new File("checkIn.bin");
    private final File calibrationFile = new File("calibration_booking.bin");

    private final ObservableList<CalibrationData> calibrationLogs = FXCollections.observableArrayList();
    private final List<String> allMachineIds = List.of("M001", "M002", "M003", "M004", "M005");

    @FXML
    public void initialize() {
        colOperatorName.setCellValueFactory(data -> data.getValue().operatorNameProperty());
        colAnimalId.setCellValueFactory(data -> data.getValue().animalIdProperty());
        colMachineId.setCellValueFactory(data -> data.getValue().machineIdProperty());
        colStatus.setCellValueFactory(data -> data.getValue().statusProperty());

        calibrationTableView.setItems(calibrationLogs);
        statusComboBox.setItems(FXCollections.observableArrayList("Booked", "Available"));

        loadAnimalIdsFromCheckIn();
        loadCalibrationLogs();
        updateAvailableMachines();
    }

    private void loadAnimalIdsFromCheckIn() {
        Set<String> animalIds = new HashSet<>();
        if (!checkInFile.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(checkInFile))) {
            while (true) {
                Object obj = ois.readObject();
                if (obj instanceof checkIn.HygieneData data) {
                    animalIds.add(data.getAnimalId());
                }
            }
        } catch (EOFException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }

        animalIdComboBox.setItems(FXCollections.observableArrayList(animalIds));
    }

    private void loadCalibrationLogs() {
        calibrationLogs.clear();
        if (!calibrationFile.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(calibrationFile))) {
            while (true) {
                CalibrationData data = (CalibrationData) ois.readObject();
                calibrationLogs.add(data);
            }
        } catch (EOFException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAvailableMachines() {
        Set<String> booked = new HashSet<>();
        for (CalibrationData data : calibrationLogs) {
            if ("Booked".equalsIgnoreCase(data.status)) {
                booked.add(data.machineId);
            }
        }

        List<String> available = new ArrayList<>(allMachineIds);
        available.removeAll(booked);
        Collections.sort(available);
        machineIdComboBox.setItems(FXCollections.observableArrayList(available));
    }

    @FXML
    public void saveLog() {
        String name = operatorNameField.getText();
        String animalId = animalIdComboBox.getValue();
        String machineId = machineIdComboBox.getValue();
        String status = statusComboBox.getValue();
        LocalDate date = calibrationDatePicker.getValue();

        if (name == null || name.isBlank() || animalId == null || machineId == null || status == null || date == null) {
            showAlert("All fields are required.");
            return;
        }

        CalibrationData data = new CalibrationData(name, animalId, date.toString(), machineId, status);
        calibrationLogs.add(data);
        appendToFile(data);
        clearFields();
        updateAvailableMachines();
    }

    private void appendToFile(CalibrationData data) {
        try {
            boolean append = calibrationFile.exists() && calibrationFile.length() > 0;
            FileOutputStream fos = new FileOutputStream(calibrationFile, true);
            ObjectOutputStream oos = append ? new AppendingObjectOutputStream(fos) : new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error saving log.");
        }
    }

    @FXML
    public void updateStatus() {
        CalibrationData selected = calibrationTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Select a log from the table.");
            return;
        }

        if (!"Booked".equalsIgnoreCase(selected.status)) {
            showAlert("Only 'Booked' entries can be updated.");
            return;
        }

        // Change status to Available
        selected.status = "Available";
        saveAllLogsToFile();
        calibrationTableView.refresh();
        updateAvailableMachines();
        showAlert("Status updated to Available.");
    }

    private void saveAllLogsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(calibrationFile))) {
            for (CalibrationData data : calibrationLogs) {
                oos.writeObject(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FF_MenuPage.fxml"));
            Stage stage = (Stage) operatorNameField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            showAlert("Can't load menu.");
        }
    }

    private void clearFields() {
        operatorNameField.clear();
        animalIdComboBox.setValue(null);
        machineIdComboBox.setValue(null);
        statusComboBox.setValue(null);
        calibrationDatePicker.setValue(null);
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // Serializable Model
    public static class CalibrationData implements Serializable {
        private final String operatorName;
        private final String animalId;
        private final String date;
        private final String machineId;
        private String status;

        public CalibrationData(String operatorName, String animalId, String date, String machineId, String status) {
            this.operatorName = operatorName;
            this.animalId = animalId;
            this.date = date;
            this.machineId = machineId;
            this.status = status;
        }

        public StringProperty operatorNameProperty() { return new SimpleStringProperty(operatorName); }
        public StringProperty animalIdProperty() { return new SimpleStringProperty(animalId); }
        public StringProperty machineIdProperty() { return new SimpleStringProperty(machineId); }
        public StringProperty statusProperty() { return new SimpleStringProperty(status); }
    }

    // Prevent header corruption when appending objects
    private static class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException { super(out); }
        @Override protected void writeStreamHeader() throws IOException { reset(); }
    }
}
