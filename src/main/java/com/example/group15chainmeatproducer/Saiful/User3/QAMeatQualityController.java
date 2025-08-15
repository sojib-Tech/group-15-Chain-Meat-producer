package com.example.group15chainmeatproducer.Saiful.User3;

import com.example.group15chainmeatproducer.SceneManager;
import com.example.group15chainmeatproducer.Saiful.User3.models.MeatBatch;
import com.example.group15chainmeatproducer.Saiful.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class QAMeatQualityController {

    @FXML
    private ComboBox<String> meatBatchComboBox;
    @FXML
    private DatePicker evaluationDatePicker;
    @FXML
    private RadioButton approvedRadio;
    @FXML
    private RadioButton holdRadio;
    @FXML
    private TextField colorTextureField;
    @FXML
    private TextField labReportField;
    @FXML
    private TableView<MeatBatch> meatQualityTable;
    @FXML
    private TableColumn<MeatBatch, String> batchIdColumn;
    @FXML
    private TableColumn<MeatBatch, String> qualityStatusColumn;
    @FXML
    private TableColumn<MeatBatch, LocalDate> dateColumn;
    @FXML
    private TableColumn<MeatBatch, String> resultsColumn;
    @FXML
    private Button backButton;
    @FXML
    private Button submitEvaluationButton;
    @FXML
    private Button approveBatchButton;

    private ToggleGroup qualityToggleGroup;
    private ArrayList<MeatBatch> meatQualityData;

    @FXML
    public void initialize() {
        initializeRadioButtons();
        initializeComboBox();
        initializeTableColumns();
        loadMeatQualityData();
        updateTable();
    }

    private void initializeRadioButtons() {
        qualityToggleGroup = new ToggleGroup();
        approvedRadio.setToggleGroup(qualityToggleGroup);
        holdRadio.setToggleGroup(qualityToggleGroup);
    }

    private void initializeComboBox() {
        meatBatchComboBox.getItems().clear();
        meatBatchComboBox.getItems().addAll(
                "MEAT-QUALITY-001", "MEAT-QUALITY-002", "MEAT-QUALITY-003",
                "MEAT-QUALITY-004", "MEAT-QUALITY-005", "MEAT-QUALITY-006",
                "MEAT-QUALITY-007", "MEAT-QUALITY-008", "MEAT-QUALITY-009",
                "MEAT-QUALITY-010"
        );
        meatBatchComboBox.setPromptText("Choose Meat Batch");
    }

    private void initializeTableColumns() {
        batchIdColumn.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        qualityStatusColumn.setCellValueFactory(new PropertyValueFactory<>("qualityStatus"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("evaluationDate"));
        resultsColumn.setCellValueFactory(new PropertyValueFactory<>("results"));
    }

    private void loadMeatQualityData() {
        meatQualityData = (ArrayList<MeatBatch>) DataManager.loadFromFile("meat_quality_evaluation_data");
        if (meatQualityData == null) {
            meatQualityData = new ArrayList<>();
        }
    }

    private void saveMeatQualityData() {
        DataManager.saveToFile(meatQualityData, "meat_quality_evaluation_data");
    }

    private void updateTable() {
        meatQualityTable.getItems().clear();
        meatQualityTable.getItems().addAll(meatQualityData);
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.switchToUser3Menu(event);
    }

    @FXML
    public void handleSubmitEvaluation(ActionEvent event) {
        String selectedBatch = meatBatchComboBox.getValue();
        LocalDate evaluationDate = evaluationDatePicker.getValue();
        RadioButton selectedStatus = (RadioButton) qualityToggleGroup.getSelectedToggle();
        String qualityStatus = selectedStatus != null ? selectedStatus.getText() : "";
        String colorTexture = colorTextureField.getText();
        String labReport = labReportField.getText();

        if (validateInputs(selectedBatch, evaluationDate, qualityStatus, colorTexture, labReport)) {
            MeatBatch newEvaluation = new MeatBatch(
                    selectedBatch, qualityStatus, evaluationDate, colorTexture,
                    labReport, "Quality evaluation completed successfully"
            );

            meatQualityData.add(newEvaluation);
            saveMeatQualityData();
            updateTable();
            clearAllFields();
            showSuccessMessage("Meat quality evaluation submitted successfully!");
        } else {
            showErrorMessage("Please complete all required fields before submitting!");
        }
    }

    @FXML
    public void handleApproveBatch(ActionEvent event) {
        MeatBatch selectedBatch = meatQualityTable.getSelectionModel().getSelectedItem();

        if (selectedBatch != null) {
            selectedBatch.setQualityStatus("APPROVED FOR PROCESSING");
            selectedBatch.setResults("BATCH APPROVED - Cleared for next production stage");

            saveMeatQualityData();
            updateTable();
            showSuccessMessage("Meat batch approved successfully!\n" +
                    "Batch ID: " + selectedBatch.getBatchId() +
                    "\nStatus: Ready for processing");
        } else {
            showErrorMessage("Please select a meat batch from the table to approve!");
        }
    }

    private boolean validateInputs(String batch, LocalDate date, String status, String color, String lab) {
        return batch != null && !batch.isEmpty() &&
                date != null &&
                !status.isEmpty() &&
                !color.trim().isEmpty() &&
                !lab.trim().isEmpty();
    }

    private void clearAllFields() {
        meatBatchComboBox.setValue(null);
        evaluationDatePicker.setValue(null);
        qualityToggleGroup.selectToggle(null);
        colorTextureField.clear();
        labReportField.clear();
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
}