package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ScheduledMaintenanceController implements Initializable {

    @FXML
    private TextField machineIdField;
    @FXML
    private Button searchButton;

    @FXML
    private TableView<MaintenanceRecord> machineTable;
    @FXML
    private TableColumn<MaintenanceRecord, String> colMachineId;
    @FXML
    private TableColumn<MaintenanceRecord, String> colMachineName;
    @FXML
    private TableColumn<MaintenanceRecord, String> colLastMaintenance;
    @FXML
    private TableColumn<MaintenanceRecord, String> colNextScheduled;
    @FXML
    private TableColumn<MaintenanceRecord, String> colTechnician;
    @FXML
    private TableColumn<MaintenanceRecord, String> colStatus;

    @FXML
    private Button validateButton;
    @FXML
    private DatePicker maintenanceDatePicker;
    @FXML
    private ComboBox<String> maintenanceTypeCombo;
    @FXML
    private TextArea notesArea;
    @FXML
    private Button processButton;
    @FXML
    private Button backButton;

    private final List<MaintenanceRecord> records = new ArrayList<>();
    private ObservableList<MaintenanceRecord> tableItems;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> types = new ArrayList<>();
        types.add("Routine");
        types.add("Emergency");
        types.add("Overhaul");
        maintenanceTypeCombo.setItems(FXCollections.observableList(types));

        tableItems = FXCollections.observableList(records);
        machineTable.setItems(tableItems);
        colMachineId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colMachineName.setCellValueFactory(new PropertyValueFactory<>("machineName"));
        colTechnician.setCellValueFactory(new PropertyValueFactory<>("assignedTechnician"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("maintenanceStatus"));
        colLastMaintenance.setCellValueFactory(cell -> new SimpleStringProperty(formatDate(cell.getValue().getLastMaintenanceDate())));
        colNextScheduled.setCellValueFactory(cell -> new SimpleStringProperty(formatDate(cell.getValue().getNextScheduledMaintenance())));
    }

    @FXML
    private void onSearchMachine(ActionEvent event) {
        String id = trim(machineIdField.getText());
        if (id.isEmpty()) {
            machineTable.setItems(tableItems);
            return;
        }
        List<MaintenanceRecord> filtered = records.stream()
                .filter(r -> r.getMachineId() != null && r.getMachineId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
        machineTable.setItems(FXCollections.observableList(filtered));
    }

    @FXML
    private void onValidateMaintenanceData(ActionEvent event) {
        List<String> issues = new ArrayList<>();
        String id = trim(machineIdField.getText());
        if (id.isEmpty()) issues.add("Machine ID is required");
        if (maintenanceDatePicker.getValue() == null) issues.add("Maintenance Date is required");
        if (maintenanceTypeCombo.getValue() == null || maintenanceTypeCombo.getValue().trim().isEmpty())
            issues.add("Maintenance Type is required");
        if (!issues.isEmpty()) {
            showAlert("Validation", String.join("\n", issues), Alert.AlertType.WARNING);
            return;
        }
        showAlert("Validation", "Data looks valid.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onProcessMaintenanceRecord(ActionEvent event) {
        String id = trim(machineIdField.getText());
        LocalDate date = maintenanceDatePicker.getValue();
        String type = maintenanceTypeCombo.getValue();
        String notes = trim(notesArea.getText());
        List<String> issues = new ArrayList<>();
        if (id.isEmpty()) issues.add("Machine ID is required");
        if (date == null) issues.add("Maintenance Date is required");
        if (type == null || type.isEmpty()) issues.add("Maintenance Type is required");
        if (!issues.isEmpty()) {
            showAlert("Process", String.join("\n", issues), Alert.AlertType.WARNING);
            return;
        }
        MaintenanceRecord existing = findById(id);
        if (existing == null) {
            MaintenanceRecord r = new MaintenanceRecord();
            r.setMachineId(id);
            r.setMachineName("Machine " + id);
            r.setAssignedTechnician("Unassigned");
            r.setLastMaintenanceDate(date);
            r.setNextScheduledMaintenance(calculateNext(date, type));
            r.setMaintenanceStatus("Scheduled");
            r.setNotes(notes);
            r.setMaintenanceType(type);
            tableItems.add(r);
        } else {
            existing.setLastMaintenanceDate(date);
            existing.setNextScheduledMaintenance(calculateNext(date, type));
            existing.setMaintenanceStatus("Scheduled");
            existing.setNotes(notes);
            existing.setMaintenanceType(type);
            machineTable.refresh();
        }
        clearForm();
        showAlert("Process", "Maintenance record saved.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }

    private MaintenanceRecord findById(String id) {
        for (MaintenanceRecord r : records) {
            if (r.getMachineId() != null && r.getMachineId().equalsIgnoreCase(id)) return r;
        }
        return null;
    }

    private LocalDate calculateNext(LocalDate base, String type) {
        if (base == null) return null;
        if ("Routine".equalsIgnoreCase(type)) return base.plusDays(30);
        if ("Emergency".equalsIgnoreCase(type)) return base.plusDays(7);
        if ("Overhaul".equalsIgnoreCase(type)) return base.plusDays(90);
        return base.plusDays(30);
    }

    private String trim(String s) {
        return s == null ? "" : s.trim();
    }

    private String formatDate(LocalDate d) {
        if (d == null) return "";
        return DATE_FORMAT.format(d);
    }

    private void clearForm() {
        maintenanceDatePicker.setValue(null);
        maintenanceTypeCombo.getSelectionModel().clearSelection();
        notesArea.clear();
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
