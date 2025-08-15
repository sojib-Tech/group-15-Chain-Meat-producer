package com.example.group15chainmeatproducer.Saiful.User3;

import com.example.group15chainmeatproducer.SceneManager;
import com.example.group15chainmeatproducer.Saiful.User3.models.ColdStorageAudit;
import com.example.group15chainmeatproducer.Saiful.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class QAColdStorageAuditController {

    @FXML
    private ComboBox<String> storageRoomComboBox;
    @FXML
    private DatePicker auditDatePicker;
    @FXML
    private RadioButton normalRadio;
    @FXML
    private RadioButton fluctuationRadio;
    @FXML
    private TextField temperatureField;
    @FXML
    private TextField notesField;
    @FXML
    private TableView<ColdStorageAudit> auditTable;
    @FXML
    private TableColumn<ColdStorageAudit, String> roomIdColumn;
    @FXML
    private TableColumn<ColdStorageAudit, String> temperatureColumn;
    @FXML
    private TableColumn<ColdStorageAudit, LocalDate> dateColumn;
    @FXML
    private TableColumn<ColdStorageAudit, String> statusColumn;
    @FXML
    private Button backButton;
    @FXML
    private Button exportReportButton;
    @FXML
    private Button submitVerificationButton;

    private ToggleGroup tempStatusGroup;
    private ArrayList<ColdStorageAudit> auditData;

    @FXML
    public void initialize() {
        tempStatusGroup = new ToggleGroup();
        normalRadio.setToggleGroup(tempStatusGroup);
        fluctuationRadio.setToggleGroup(tempStatusGroup);
        storageRoomComboBox.getItems().clear();
        storageRoomComboBox.getItems().addAll(
                "ROOM-A1", "ROOM-A2", "ROOM-B1", "ROOM-B2", "ROOM-C1",
                "FREEZER-01", "FREEZER-02", "CHILL-01", "CHILL-02", "STAGING-01"
        );
        storageRoomComboBox.setPromptText("Choose Storage Room");
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        temperatureColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("auditDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        auditData = (ArrayList<ColdStorageAudit>) DataManager.loadFromFile("cold_storage_audit_data");
        if (auditData == null) auditData = new ArrayList<>();
        updateTable();
    }

    private void updateTable() {
        auditTable.getItems().clear();
        auditTable.getItems().addAll(auditData);
    }

    private void saveData() {
        DataManager.saveToFile(auditData, "cold_storage_audit_data");
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.switchToUser3Menu(event);
    }

    @FXML
    public void handleExportReport(ActionEvent event) {
        ColdStorageAudit selected = auditTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Success");
            a.setHeaderText(null);
            a.setContentText("Report exported for " + selected.getRoomId());
            a.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(null);
            a.setContentText("Select a record");
            a.showAndWait();
        }
    }

    @FXML
    public void handleSubmitVerification(ActionEvent event) {
        String room = storageRoomComboBox.getValue();
        LocalDate date = auditDatePicker.getValue();
        RadioButton selected = (RadioButton) tempStatusGroup.getSelectedToggle();
        String status = selected != null ? selected.getText() : "";
        String temp = temperatureField.getText();
        String notes = notesField.getText();
        if (validateInputs(room, date, status, temp, notes)) {
            ColdStorageAudit record = new ColdStorageAudit(room, temp, date, status, notes);
            auditData.add(record);
            saveData();
            updateTable();
            clearAllFields();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Success");
            a.setHeaderText(null);
            a.setContentText("Verification submitted");
            a.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(null);
            a.setContentText("Fill all fields");
            a.showAndWait();
        }
    }

    private boolean validateInputs(String room, LocalDate date, String status, String temp, String notes) {
        return room != null && !room.isEmpty() && date != null && !status.isEmpty() && temp != null && !temp.trim().isEmpty() && notes != null && !notes.trim().isEmpty();
    }

    private void clearAllFields() {
        storageRoomComboBox.setValue(null);
        auditDatePicker.setValue(null);
        tempStatusGroup.selectToggle(null);
        temperatureField.clear();
        notesField.clear();
    }
}