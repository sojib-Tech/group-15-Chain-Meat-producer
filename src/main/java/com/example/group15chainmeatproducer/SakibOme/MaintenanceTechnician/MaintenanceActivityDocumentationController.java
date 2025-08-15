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

public class MaintenanceActivityDocumentationController implements Initializable {

    @FXML
    private Button reportButton;
    @FXML
    private TextField recordIdField;
    @FXML
    private Button searchButton;

    @FXML
    private TextField machineIdField;
    @FXML
    private TextField performedByField;
    @FXML
    private DatePicker activityDatePicker;
    @FXML
    private ComboBox<String> activityTypeCombo;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Button saveButton;

    @FXML
    private TableView<MaintenanceActivityRecord> historyTable;
    @FXML
    private TableColumn<MaintenanceActivityRecord, String> colRecordId;
    @FXML
    private TableColumn<MaintenanceActivityRecord, String> colMachineId;
    @FXML
    private TableColumn<MaintenanceActivityRecord, String> colDate;
    @FXML
    private TableColumn<MaintenanceActivityRecord, String> colActivityType;
    @FXML
    private TableColumn<MaintenanceActivityRecord, String> colPerformedBy;
    @FXML
    private TableColumn<MaintenanceActivityRecord, String> colRemarks;

    @FXML
    private Button validateButton;
    @FXML
    private Button backButton;

    private final List<MaintenanceActivityRecord> activities = new ArrayList<>();
    private ObservableList<MaintenanceActivityRecord> tableItems;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> types = new ArrayList<>();
        types.add("Repair");
        types.add("Cleaning");
        types.add("Lubrication");
        types.add("Replacement");
        activityTypeCombo.setItems(FXCollections.observableList(types));

        tableItems = FXCollections.observableList(activities);
        historyTable.setItems(tableItems);
        colRecordId.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        colMachineId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colActivityType.setCellValueFactory(new PropertyValueFactory<>("activityType"));
        colPerformedBy.setCellValueFactory(new PropertyValueFactory<>("performedBy"));
        colRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        colDate.setCellValueFactory(cell -> new SimpleStringProperty(formatDate(cell.getValue().getDate())));
    }

    @FXML
    private void onGenerateMaintenanceReport(ActionEvent event) {
        int count = activities.size();
        showAlert("Report", "Total activities: " + count + ".", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onSearchRecord(ActionEvent event) {
        String id = trim(recordIdField.getText());
        if (id.isEmpty()) {
            historyTable.setItems(tableItems);
            return;
        }
        List<MaintenanceActivityRecord> filtered = activities.stream()
                .filter(r -> r.getRecordId() != null && r.getRecordId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
        historyTable.setItems(FXCollections.observableList(filtered));
    }

    @FXML
    private void onSaveActivity(ActionEvent event) {
        String recId = trim(recordIdField.getText());
        String machineId = trim(machineIdField.getText());
        String performedBy = trim(performedByField.getText());
        LocalDate date = activityDatePicker.getValue();
        String type = activityTypeCombo.getValue();
        String desc = trim(descriptionArea.getText());
        List<String> issues = new ArrayList<>();
        if (recId.isEmpty()) issues.add("Record ID is required");
        if (machineId.isEmpty()) issues.add("Machine ID is required");
        if (performedBy.isEmpty()) issues.add("Performed By is required");
        if (date == null) issues.add("Maintenance Date is required");
        if (type == null || type.isEmpty()) issues.add("Activity Type is required");
        if (!issues.isEmpty()) {
            showAlert("Save", String.join("\n", issues), Alert.AlertType.WARNING);
            return;
        }
        MaintenanceActivityRecord existing = findById(recId);
        if (existing == null) {
            MaintenanceActivityRecord r = new MaintenanceActivityRecord();
            r.setRecordId(recId);
            r.setMachineId(machineId);
            r.setPerformedBy(performedBy);
            r.setDate(date);
            r.setActivityType(type);
            r.setRemarks(desc);
            tableItems.add(r);
        } else {
            existing.setMachineId(machineId);
            existing.setPerformedBy(performedBy);
            existing.setDate(date);
            existing.setActivityType(type);
            existing.setRemarks(desc);
            historyTable.refresh();
        }
        clearForm();
        showAlert("Save", "Activity saved.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onValidateRecord(ActionEvent event) {
        String recId = trim(recordIdField.getText());
        String machineId = trim(machineIdField.getText());
        String performedBy = trim(performedByField.getText());
        LocalDate date = activityDatePicker.getValue();
        String type = activityTypeCombo.getValue();
        List<String> issues = new ArrayList<>();
        if (recId.isEmpty()) issues.add("Record ID is required");
        if (machineId.isEmpty()) issues.add("Machine ID is required");
        if (performedBy.isEmpty()) issues.add("Performed By is required");
        if (date == null) issues.add("Maintenance Date is required");
        if (type == null || type.isEmpty()) issues.add("Activity Type is required");
        if (issues.isEmpty()) showAlert("Validate", "Record looks valid.", Alert.AlertType.INFORMATION);
        else showAlert("Validate", String.join("\n", issues), Alert.AlertType.WARNING);
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToMaintenanceMenu(event);
    }

    private MaintenanceActivityRecord findById(String id) {
        for (MaintenanceActivityRecord r : activities) {
            if (r.getRecordId() != null && r.getRecordId().equalsIgnoreCase(id)) return r;
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
        machineIdField.clear();
        performedByField.clear();
        activityDatePicker.setValue(null);
        activityTypeCombo.getSelectionModel().clearSelection();
        descriptionArea.clear();
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
