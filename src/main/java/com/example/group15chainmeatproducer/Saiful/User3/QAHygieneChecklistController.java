package com.example.group15chainmeatproducer.Saiful.User3;

import com.example.group15chainmeatproducer.SceneManager;
import com.example.group15chainmeatproducer.Saiful.User3.models.HygieneChecklist;
import com.example.group15chainmeatproducer.Saiful.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class QAHygieneChecklistController {

    @FXML
    private ComboBox<String> cleaningBatchComboBox;
    @FXML
    private DatePicker verificationDatePicker;
    @FXML
    private RadioButton completeRadio;
    @FXML
    private RadioButton incompleteRadio;
    @FXML
    private TextField photoUploadField;
    @FXML
    private TextField notesField;
    @FXML
    private TableView<HygieneChecklist> hygieneTable;
    @FXML
    private TableColumn<HygieneChecklist, String> batchIdColumn;
    @FXML
    private TableColumn<HygieneChecklist, String> statusColumn;
    @FXML
    private TableColumn<HygieneChecklist, LocalDate> dateColumn;
    @FXML
    private TableColumn<HygieneChecklist, String> photoColumn;
    @FXML
    private Button backButton;
    @FXML
    private Button uploadPhotoButton;
    @FXML
    private Button submitReportButton;

    private ToggleGroup statusToggleGroup;
    private ArrayList<HygieneChecklist> hygieneData;

    @FXML
    public void initialize() {
        initializeRadioButtons();
        initializeComboBox();
        initializeTableColumns();
        loadHygieneData();
        updateTable();
    }

    private void initializeRadioButtons() {
        statusToggleGroup = new ToggleGroup();
        completeRadio.setToggleGroup(statusToggleGroup);
        incompleteRadio.setToggleGroup(statusToggleGroup);
    }

    private void initializeComboBox() {
        cleaningBatchComboBox.getItems().clear();
        cleaningBatchComboBox.getItems().addAll(
                "HYGIENE-BATCH-001", "HYGIENE-BATCH-002", "HYGIENE-BATCH-003",
                "HYGIENE-BATCH-004", "HYGIENE-BATCH-005", "HYGIENE-BATCH-006",
                "HYGIENE-BATCH-007", "HYGIENE-BATCH-008", "HYGIENE-BATCH-009",
                "HYGIENE-BATCH-010"
        );
        cleaningBatchComboBox.setPromptText("Choose Cleaning Batch");
    }

    private void initializeTableColumns() {
        batchIdColumn.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("verificationDate"));
        photoColumn.setCellValueFactory(new PropertyValueFactory<>("photoPath"));
    }

    private void loadHygieneData() {
        hygieneData = (ArrayList<HygieneChecklist>) DataManager.loadFromFile("hygiene_checklist_data");
        if (hygieneData == null) {
            hygieneData = new ArrayList<>();
        }
    }

    private void saveHygieneData() {
        DataManager.saveToFile(hygieneData, "hygiene_checklist_data");
    }

    private void updateTable() {
        hygieneTable.getItems().clear();
        hygieneTable.getItems().addAll(hygieneData);
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.switchToUser3Menu(event);
    }

    @FXML
    public void handleUploadPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Hygiene Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.bmp", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(uploadPhotoButton.getScene().getWindow());
        if (selectedFile != null) {
            photoUploadField.setText(selectedFile.getName());
            showSuccessMessage("Photo uploaded: " + selectedFile.getName());
        }
    }

    @FXML
    public void handleSubmitReport(ActionEvent event) {
        String selectedBatch = cleaningBatchComboBox.getValue();
        LocalDate verificationDate = verificationDatePicker.getValue();
        RadioButton selectedStatus = (RadioButton) statusToggleGroup.getSelectedToggle();
        String status = selectedStatus != null ? selectedStatus.getText() : "";
        String photoPath = photoUploadField.getText();
        String notes = notesField.getText();

        if (validateInputs(selectedBatch, verificationDate, status, notes)) {
            HygieneChecklist newChecklist = new HygieneChecklist(
                    selectedBatch, status, verificationDate, photoPath, notes
            );

            hygieneData.add(newChecklist);
            saveHygieneData();
            updateTable();
            clearAllFields();
            showSuccessMessage("Hygiene checklist report submitted successfully!");
        } else {
            showErrorMessage("Please complete all required fields before submitting!");
        }
    }

    private boolean validateInputs(String batch, LocalDate date, String status, String notes) {
        return batch != null && !batch.isEmpty() &&
                date != null &&
                !status.isEmpty() &&
                !notes.trim().isEmpty();
    }

    private void clearAllFields() {
        cleaningBatchComboBox.setValue(null);
        verificationDatePicker.setValue(null);
        statusToggleGroup.selectToggle(null);
        photoUploadField.clear();
        notesField.clear();
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