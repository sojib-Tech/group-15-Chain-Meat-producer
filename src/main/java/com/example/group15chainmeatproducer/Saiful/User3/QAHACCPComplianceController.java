package com.example.group15chainmeatproducer.Saiful.User3;

import com.example.group15chainmeatproducer.SceneManager;
import com.example.group15chainmeatproducer.Saiful.User3.models.HACCPCompliance;
import com.example.group15chainmeatproducer.Saiful.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class QAHACCPComplianceController {

    @FXML
    private ComboBox<String> controlPointComboBox;
    @FXML
    private DatePicker monitorDatePicker;
    @FXML
    private RadioButton compliantRadio;
    @FXML
    private RadioButton nonConformityRadio;
    @FXML
    private TextField observationField;
    @FXML
    private TextField parametersField;
    @FXML
    private TableView<HACCPCompliance> haccpTable;
    @FXML
    private TableColumn<HACCPCompliance, String> controlPointColumn;
    @FXML
    private TableColumn<HACCPCompliance, String> statusColumn;
    @FXML
    private TableColumn<HACCPCompliance, LocalDate> dateColumn;
    @FXML
    private TableColumn<HACCPCompliance, String> notesColumn;
    @FXML
    private Button backButton;
    @FXML
    private Button submitComplianceButton;
    @FXML
    private Button flagNonConformityButton;

    private ToggleGroup complianceToggleGroup;
    private ArrayList<HACCPCompliance> haccpData;

    @FXML
    public void initialize() {
        initializeRadioButtons();
        initializeComboBox();
        initializeTableColumns();
        loadHACCPData();
        updateTable();
    }

    private void initializeRadioButtons() {
        complianceToggleGroup = new ToggleGroup();
        compliantRadio.setToggleGroup(complianceToggleGroup);
        nonConformityRadio.setToggleGroup(complianceToggleGroup);
    }

    private void initializeComboBox() {
        controlPointComboBox.getItems().clear();
        controlPointComboBox.getItems().addAll(
                "Critical Temperature Control",
                "pH Level Monitoring System",
                "Metal Detection Equipment",
                "Pathogen Testing Protocol",
                "Cleaning and Sanitization",
                "Water Quality Assessment",
                "Pest Control Management",
                "Storage Temperature Monitoring",
                "Humidity Control System",
                "Equipment Calibration Check"
        );
        controlPointComboBox.setPromptText("Choose Control Point");
    }

    private void initializeTableColumns() {
        controlPointColumn.setCellValueFactory(new PropertyValueFactory<>("controlPoint"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("monitorDate"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
    }

    private void loadHACCPData() {
        haccpData = (ArrayList<HACCPCompliance>) DataManager.loadFromFile("haccp_compliance_data");
        if (haccpData == null) {
            haccpData = new ArrayList<>();
        }
    }

    private void saveHACCPData() {
        DataManager.saveToFile(haccpData, "haccp_compliance_data");
    }

    private void updateTable() {
        haccpTable.getItems().clear();
        haccpTable.getItems().addAll(haccpData);
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.switchToUser3Menu(event);
    }

    @FXML
    public void handleSubmitCompliance(ActionEvent event) {
        String selectedControlPoint = controlPointComboBox.getValue();
        LocalDate monitoringDate = monitorDatePicker.getValue();
        RadioButton selectedStatus = (RadioButton) complianceToggleGroup.getSelectedToggle();
        String complianceStatus = selectedStatus != null ? selectedStatus.getText() : "";
        String observationNotes = observationField.getText();
        String parameterDetails = parametersField.getText();

        if (validateInputs(selectedControlPoint, monitoringDate, complianceStatus, observationNotes, parameterDetails)) {
            HACCPCompliance newCompliance = new HACCPCompliance(
                    selectedControlPoint, complianceStatus, monitoringDate,
                    observationNotes, parameterDetails, "Compliance monitoring completed"
            );

            haccpData.add(newCompliance);
            saveHACCPData();
            updateTable();
            clearAllFields();
            showSuccessMessage("HACCP compliance record submitted successfully!");
        } else {
            showErrorMessage("Please complete all required fields before submitting!");
        }
    }

    @FXML
    public void handleFlagNonConformity(ActionEvent event) {
        HACCPCompliance selectedRecord = haccpTable.getSelectionModel().getSelectedItem();

        if (selectedRecord != null) {
            selectedRecord.setStatus("Non-Conformity FLAGGED");
            selectedRecord.setNotes("CRITICAL ALERT: Non-conformity identified - Immediate corrective action required");

            saveHACCPData();
            updateTable();
            showWarningMessage("Non-conformity flagged for control point: " + selectedRecord.getControlPoint() +
                    "\nImmediate corrective action is required!");
        } else {
            showErrorMessage("Please select a compliance record from the table to flag!");
        }
    }

    private boolean validateInputs(String controlPoint, LocalDate date, String status, String observation, String parameters) {
        return controlPoint != null && !controlPoint.isEmpty() &&
                date != null &&
                !status.isEmpty() &&
                !observation.trim().isEmpty() &&
                !parameters.trim().isEmpty();
    }

    private void clearAllFields() {
        controlPointComboBox.setValue(null);
        monitorDatePicker.setValue(null);
        complianceToggleGroup.selectToggle(null);
        observationField.clear();
        parametersField.clear();
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showWarningMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Non-Conformity Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}