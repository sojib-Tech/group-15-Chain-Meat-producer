package com.example.group15chainmeatproducer.Saiful.User3;

import com.example.group15chainmeatproducer.SceneManager;
import com.example.group15chainmeatproducer.Saiful.User3.models.FinalProductBatch;
import com.example.group15chainmeatproducer.Saiful.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class QAFinalProductApprovalController {

    @FXML
    private ComboBox<String> productBatchComboBox;
    @FXML
    private DatePicker reviewDatePicker;
    @FXML
    private RadioButton approvedRadio;
    @FXML
    private RadioButton rejectedRadio;
    @FXML
    private TextField qualityRecordsField;
    @FXML
    private TextField decisionNotesField;
    @FXML
    private TableView<FinalProductBatch> finalProductTable;
    @FXML
    private TableColumn<FinalProductBatch, String> batchIdColumn;
    @FXML
    private TableColumn<FinalProductBatch, String> statusColumn;
    @FXML
    private TableColumn<FinalProductBatch, LocalDate> dateColumn;
    @FXML
    private TableColumn<FinalProductBatch, String> decisionColumn;
    @FXML
    private Button backButton;
    @FXML
    private Button submitApprovalButton;
    @FXML
    private Button notifyDispatchButton;

    private ToggleGroup approvalToggleGroup;
    private ArrayList<FinalProductBatch> finalProductData;

    @FXML
    public void initialize() {
        initializeRadioButtons();
        initializeComboBox();
        initializeTableColumns();
        loadFinalProductData();
        updateTable();
    }

    private void initializeRadioButtons() {
        approvalToggleGroup = new ToggleGroup();
        approvedRadio.setToggleGroup(approvalToggleGroup);
        rejectedRadio.setToggleGroup(approvalToggleGroup);
    }

    private void initializeComboBox() {
        productBatchComboBox.getItems().clear();
        productBatchComboBox.getItems().addAll(
                "FINAL-PROD-2024-001", "FINAL-PROD-2024-002", "FINAL-PROD-2024-003",
                "FINAL-PROD-2024-004", "FINAL-PROD-2024-005", "FINAL-PROD-2024-006",
                "FINAL-PROD-2024-007", "FINAL-PROD-2024-008", "FINAL-PROD-2024-009",
                "FINAL-PROD-2024-010"
        );
        productBatchComboBox.setPromptText("Choose Product Batch");
    }

    private void initializeTableColumns() {
        batchIdColumn.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("reviewDate"));
        decisionColumn.setCellValueFactory(new PropertyValueFactory<>("decision"));
    }

    private void loadFinalProductData() {
        finalProductData = (ArrayList<FinalProductBatch>) DataManager.loadFromFile("final_product_approval_data");
        if (finalProductData == null) {
            finalProductData = new ArrayList<>();
        }
    }

    private void saveFinalProductData() {
        DataManager.saveToFile(finalProductData, "final_product_approval_data");
    }

    private void updateTable() {
        finalProductTable.getItems().clear();
        finalProductTable.getItems().addAll(finalProductData);
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.switchToUser3Menu(event);
    }

    @FXML
    public void handleSubmitApproval(ActionEvent event) {
        String selectedBatch = productBatchComboBox.getValue();
        LocalDate reviewDate = reviewDatePicker.getValue();
        RadioButton selectedStatus = (RadioButton) approvalToggleGroup.getSelectedToggle();
        String approvalStatus = selectedStatus != null ? selectedStatus.getText() : "";
        String qualityRecords = qualityRecordsField.getText();
        String decisionNotes = decisionNotesField.getText();

        if (validateInputs(selectedBatch, reviewDate, approvalStatus, qualityRecords, decisionNotes)) {
            FinalProductBatch newApproval = new FinalProductBatch(
                    selectedBatch, approvalStatus, reviewDate, qualityRecords,
                    decisionNotes, "Final product review completed"
            );

            finalProductData.add(newApproval);
            saveFinalProductData();
            updateTable();
            clearAllFields();
            showSuccessMessage("Final product approval decision submitted successfully!");
        } else {
            showErrorMessage("Please complete all required fields before submitting!");
        }
    }

    @FXML
    public void handleNotifyDispatch(ActionEvent event) {
        FinalProductBatch selectedProduct = finalProductTable.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            if ("Approved".equals(selectedProduct.getStatus())) {
                selectedProduct.setDecision("DISPATCH TEAM NOTIFIED - Product cleared for shipment");
                saveFinalProductData();
                updateTable();
                showSuccessMessage("Dispatch team has been notified successfully!\n" +
                        "Batch ID: " + selectedProduct.getBatchId() +
                        "\nStatus: Ready for immediate shipment");
            } else {
                showWarningMessage("Only APPROVED product batches can be dispatched!\n" +
                        "Current status: " + selectedProduct.getStatus());
            }
        } else {
            showErrorMessage("Please select a product batch from the table to notify dispatch!");
        }
    }

    private boolean validateInputs(String batch, LocalDate date, String status, String records, String notes) {
        return batch != null && !batch.isEmpty() &&
                date != null &&
                !status.isEmpty() &&
                !records.trim().isEmpty() &&
                !notes.trim().isEmpty();
    }

    private void clearAllFields() {
        productBatchComboBox.setValue(null);
        reviewDatePicker.setValue(null);
        approvalToggleGroup.selectToggle(null);
        qualityRecordsField.clear();
        decisionNotesField.clear();
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
        alert.setTitle("Approval Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}