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

public class MachinerySafetyInspectionController implements Initializable {

    @FXML
    private Button runValidationButton;
    @FXML
    private TextField machineIdField;
    @FXML
    private Button inspectButton;

    @FXML
    private TableView<SafetyInspectionRecord> inspectionTable;
    @FXML
    private TableColumn<SafetyInspectionRecord, String> colMachineId;
    @FXML
    private TableColumn<SafetyInspectionRecord, String> colMachineName;
    @FXML
    private TableColumn<SafetyInspectionRecord, String> colInspectionDate;
    @FXML
    private TableColumn<SafetyInspectionRecord, String> colSafetyStatus;
    @FXML
    private TableColumn<SafetyInspectionRecord, String> colInspectorName;
    @FXML
    private TableColumn<SafetyInspectionRecord, String> colRemarks;

    @FXML
    private ComboBox<String> safetyStatusCombo;
    @FXML
    private DatePicker nextInspectionPicker;
    @FXML
    private TextArea notesArea;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;

    private final List<SafetyInspectionRecord> records = new ArrayList<>();
    private ObservableList<SafetyInspectionRecord> tableItems;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> statuses = new ArrayList<>();
        statuses.add("Safe");
        statuses.add("Needs Repair");
        statuses.add("Unsafe");
        safetyStatusCombo.setItems(FXCollections.observableList(statuses));

        tableItems = FXCollections.observableList(records);
        inspectionTable.setItems(tableItems);
        colMachineId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colMachineName.setCellValueFactory(new PropertyValueFactory<>("machineName"));
        colSafetyStatus.setCellValueFactory(new PropertyValueFactory<>("safetyStatus"));
        colInspectorName.setCellValueFactory(new PropertyValueFactory<>("inspectorName"));
        colRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        colInspectionDate.setCellValueFactory(cell -> new SimpleStringProperty(formatDate(cell.getValue().getInspectionDate())));
    }

    @FXML
    private void onRunSafetyValidation(ActionEvent event) {
        if (records.isEmpty()) {
            showAlert("Validation", "No inspection records found.", Alert.AlertType.INFORMATION);
            return;
        }
        long unsafe = records.stream().filter(r -> "Unsafe".equalsIgnoreCase(r.getSafetyStatus())).count();
        long needs = records.stream().filter(r -> "Needs Repair".equalsIgnoreCase(r.getSafetyStatus())).count();
        String msg = "Unsafe: " + unsafe + ", Needs Repair: " + needs + ".";
        showAlert("Validation", msg, Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onInspectMachine(ActionEvent event) {
        String id = trim(machineIdField.getText());
        if (id.isEmpty()) {
            inspectionTable.setItems(tableItems);
            return;
        }
        List<SafetyInspectionRecord> filtered = records.stream()
                .filter(r -> r.getMachineId() != null && r.getMachineId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
        inspectionTable.setItems(FXCollections.observableList(filtered));
    }

    @FXML
    private void onSaveSafetyRecord(ActionEvent event) {
        String id = trim(machineIdField.getText());
        String status = safetyStatusCombo.getValue();
        LocalDate next = nextInspectionPicker.getValue();
        String notes = trim(notesArea.getText());
        List<String> issues = new ArrayList<>();
        if (id.isEmpty()) issues.add("Machine ID is required");
        if (status == null || status.isEmpty()) issues.add("Select a safety status");
        if (!issues.isEmpty()) {
            showAlert("Save", String.join("\n", issues), Alert.AlertType.WARNING);
            return;
        }
        SafetyInspectionRecord existing = findById(id);
        if (existing == null) {
            SafetyInspectionRecord r = new SafetyInspectionRecord();
            r.setMachineId(id);
            r.setMachineName("Machine " + id);
            r.setInspectionDate(LocalDate.now());
            r.setSafetyStatus(status);
            r.setInspectorName("Maintenance Team");
            r.setRemarks(notes);
            r.setNextInspectionDate(next);
            tableItems.add(r);
        } else {
            existing.setInspectionDate(LocalDate.now());
            existing.setSafetyStatus(status);
            existing.setRemarks(notes);
            existing.setNextInspectionDate(next);
            inspectionTable.refresh();
        }
        clearForm();
        showAlert("Save", "Safety record saved.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToMaintenanceMenu(event);
    }

    private SafetyInspectionRecord findById(String id) {
        for (SafetyInspectionRecord r : records) {
            if (r.getMachineId() != null && r.getMachineId().equalsIgnoreCase(id)) return r;
        }
        return null;
    }

    private String trim(String s) {
        return s == null ? "" : s.trim();
    }

    private String formatDate(LocalDate d) {
        if (d == null) return "";
        return DATE_FORMAT.format(d);
    }

    private void clearForm() {
        safetyStatusCombo.getSelectionModel().clearSelection();
        nextInspectionPicker.setValue(null);
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
