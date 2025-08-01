package com.example.group15chainmeatproducer.Saiful;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.group15chainmeatproducer.SceneManager;

public class QAHygieneChecklistController {

    @FXML
    private Button backButton;
    @FXML
    private TextField systemUsernameField;
    @FXML
    private PasswordField systemPasswordField;
    @FXML
    private Button loginSystemButton;
    @FXML
    private ComboBox<String> cleaningBatchComboBox;
    @FXML
    private Button selectBatchButton;
    @FXML
    private ListView<String> checklistListView;
    @FXML
    private Button reviewChecklistButton;
    @FXML
    private TextArea verificationNotesArea;
    @FXML
    private CheckBox item1CheckBox;
    @FXML
    private CheckBox item2CheckBox;
    @FXML
    private CheckBox item3CheckBox;
    @FXML
    private CheckBox item4CheckBox;
    @FXML
    private CheckBox item5CheckBox;
    @FXML
    private Button confirmVerificationButton;
    @FXML
    private Button uploadPhotoButton;
    @FXML
    private Label photoStatusLabel;
    @FXML
    private TextArea photoDescriptionArea;
    @FXML
    private TextArea complianceNotesArea;
    @FXML
    private Button submitReportButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button completeButton;

    @FXML
    private void initialize() {
        // Populate cleaning batch combo box
        cleaningBatchComboBox.getItems().addAll(
                "BATCH-2024-001", "BATCH-2024-002", "BATCH-2024-003",
                "BATCH-2024-004", "BATCH-2024-005", "BATCH-2024-006"
        );

        // Populate checklist items
        ObservableList<String> checklistItems = FXCollections.observableArrayList(
                "Floor cleaning - Status: Pending",
                "Equipment sanitization - Status: Pending",
                "Wall cleaning - Status: Pending",
                "Drain cleaning - Status: Pending",
                "Temperature monitoring - Status: Pending",
                "Chemical usage log - Status: Pending",
                "Air filtration check - Status: Pending"
        );
        checklistListView.setItems(checklistItems);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToUser3Menu(event);
    }

    @FXML
    private void handleLoginSystem(ActionEvent event) {
        String username = systemUsernameField.getText();
        String password = systemPasswordField.getText();

        if (!username.isEmpty() && !password.isEmpty()) {
            System.out.println("Logged into hygiene system - User: " + username);
            loginSystemButton.setText("System Login ✓");
            loginSystemButton.setDisable(true);
            systemUsernameField.setDisable(true);
            systemPasswordField.setDisable(true);
        } else {
            System.out.println("Please enter username and password");
        }
    }

    @FXML
    private void handleSelectBatch(ActionEvent event) {
        String selectedBatch = cleaningBatchComboBox.getValue();
        if (selectedBatch != null) {
            System.out.println("Selected cleaning batch: " + selectedBatch);
            selectBatchButton.setText("Batch Selected ✓");
            selectBatchButton.setDisable(true);
        } else {
            System.out.println("Please select a cleaning batch");
        }
    }

    @FXML
    private void handleReviewChecklist(ActionEvent event) {
        System.out.println("Reviewing auto-generated sanitation checklist");
        reviewChecklistButton.setText("Checklist Reviewed ✓");
        reviewChecklistButton.setDisable(true);

        // Update checklist items to show reviewed status
        ObservableList<String> reviewedItems = FXCollections.observableArrayList(
                "Floor cleaning - Status: Reviewed",
                "Equipment sanitization - Status: Reviewed",
                "Wall cleaning - Status: Reviewed",
                "Drain cleaning - Status: Reviewed",
                "Temperature monitoring - Status: Reviewed",
                "Chemical usage log - Status: Reviewed",
                "Air filtration check - Status: Reviewed"
        );
        checklistListView.setItems(reviewedItems);
    }

    @FXML
    private void handleConfirmVerification(ActionEvent event) {
        String notes = verificationNotesArea.getText();
        boolean allItemsChecked = item1CheckBox.isSelected() &&
                item2CheckBox.isSelected() &&
                item3CheckBox.isSelected() &&
                item4CheckBox.isSelected() &&
                item5CheckBox.isSelected();

        if (!notes.isEmpty() && allItemsChecked) {
            System.out.println("Verification notes: " + notes);
            System.out.println("All items verified");
            confirmVerificationButton.setText("Verification Confirmed ✓");
            confirmVerificationButton.setDisable(true);
        } else {
            System.out.println("Please complete verification notes and check all items");
        }
    }

    @FXML
    private void handleUploadPhoto(ActionEvent event) {
        System.out.println("Photo upload initiated");
        photoStatusLabel.setText("Photos uploaded successfully ✓");
        photoStatusLabel.setStyle("-fx-text-fill: green;");
        uploadPhotoButton.setText("Photos Uploaded ✓");
        uploadPhotoButton.setDisable(true);
    }

    @FXML
    private void handleSubmitReport(ActionEvent event) {
        String complianceNotes = complianceNotesArea.getText();
        System.out.println("Submitting report to compliance log: " + complianceNotes);
        submitReportButton.setText("Report Submitted ✓");
        submitReportButton.setDisable(true);
    }

    @FXML
    private void handleReset(ActionEvent event) {
        // Reset all form fields
        systemUsernameField.clear();
        systemPasswordField.clear();
        systemUsernameField.setDisable(false);
        systemPasswordField.setDisable(false);
        cleaningBatchComboBox.setValue(null);
        verificationNotesArea.clear();
        item1CheckBox.setSelected(false);
        item2CheckBox.setSelected(false);
        item3CheckBox.setSelected(false);
        item4CheckBox.setSelected(false);
        item5CheckBox.setSelected(false);
        photoDescriptionArea.clear();
        complianceNotesArea.clear();
        photoStatusLabel.setText("No photos uploaded");
        photoStatusLabel.setStyle("-fx-text-fill: black;");

        // Reset button states
        loginSystemButton.setText("Login to System");
        loginSystemButton.setDisable(false);
        selectBatchButton.setText("Select Batch");
        selectBatchButton.setDisable(false);
        reviewChecklistButton.setText("Review Checklist");
        reviewChecklistButton.setDisable(false);
        confirmVerificationButton.setText("Confirm All Items");
        confirmVerificationButton.setDisable(false);
        uploadPhotoButton.setText("Upload Photos");
        uploadPhotoButton.setDisable(false);
        submitReportButton.setText("Submit to Compliance Log");
        submitReportButton.setDisable(false);

        // Reset checklist
        initialize();

        System.out.println("Form reset");
    }

    @FXML
    private void handleComplete(ActionEvent event) {
        System.out.println("Hygiene checklist verification workflow completed");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Verification Complete");
        alert.setHeaderText(null);
        alert.setContentText("Hygiene checklist verification has been completed successfully!");
        alert.showAndWait();
    }
}