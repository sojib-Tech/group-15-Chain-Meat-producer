package com.example.group15chainmeatproducer.Saiful;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import com.example.group15chainmeatproducer.SceneManager;

public class QAPreSlaughterInspectionController {

    @FXML
    private Button backButton;
    @FXML
    private Button accessModuleButton;
    @FXML
    private ComboBox<String> animalIdComboBox;
    @FXML
    private Button selectAnimalButton;
    @FXML
    private TextArea visualCheckArea;
    @FXML
    private TextArea healthCheckArea;
    @FXML
    private Button conductCheckButton;
    @FXML
    private TextArea findingsArea;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private Button enterFindingsButton;
    @FXML
    private Button submitStatusButton;
    @FXML
    private TextArea notificationArea;
    @FXML
    private Button notifySupervisorButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button completeButton;

    @FXML
    private void initialize() {
        // Populate animal ID combo box
        animalIdComboBox.getItems().addAll(
                "A001", "A002", "A003", "A004", "A005",
                "A006", "A007", "A008", "A009", "A010"
        );

        // Populate status combo box
        statusComboBox.getItems().addAll(
                "Approved", "Rejected", "Conditional Approval"
        );
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToUser3Menu(event);
    }

    @FXML
    private void handleAccessModule(ActionEvent event) {
        System.out.println("Animal inspection module accessed");
        accessModuleButton.setText("Module Accessed ✓");
        accessModuleButton.setDisable(true);
    }

    @FXML
    private void handleSelectAnimal(ActionEvent event) {
        String selectedAnimal = animalIdComboBox.getValue();
        if (selectedAnimal != null) {
            System.out.println("Selected animal: " + selectedAnimal);
            selectAnimalButton.setText("Animal Selected ✓");
            selectAnimalButton.setDisable(true);
        } else {
            System.out.println("Please select an animal ID");
        }
    }

    @FXML
    private void handleConductCheck(ActionEvent event) {
        String visualNotes = visualCheckArea.getText();
        String healthNotes = healthCheckArea.getText();

        if (!visualNotes.isEmpty() && !healthNotes.isEmpty()) {
            System.out.println("Visual check: " + visualNotes);
            System.out.println("Health check: " + healthNotes);
            conductCheckButton.setText("Check Completed ✓");
            conductCheckButton.setDisable(true);
        } else {
            System.out.println("Please complete both visual and health checks");
        }
    }

    @FXML
    private void handleEnterFindings(ActionEvent event) {
        String findings = findingsArea.getText();
        String status = statusComboBox.getValue();

        if (!findings.isEmpty() && status != null) {
            System.out.println("Findings: " + findings);
            System.out.println("Status: " + status);
            enterFindingsButton.setText("Findings Entered ✓");
            enterFindingsButton.setDisable(true);
        } else {
            System.out.println("Please enter findings and select status");
        }
    }

    @FXML
    private void handleSubmitStatus(ActionEvent event) {
        System.out.println("Inspection status submitted");
        submitStatusButton.setText("Status Submitted ✓");
        submitStatusButton.setDisable(true);
    }

    @FXML
    private void handleNotifySupervisor(ActionEvent event) {
        String notification = notificationArea.getText();
        System.out.println("Supervisor notified: " + notification);
        notifySupervisorButton.setText("Supervisor Notified ✓");
        notifySupervisorButton.setDisable(true);
    }

    @FXML
    private void handleReset(ActionEvent event) {
        // Reset all form fields
        animalIdComboBox.setValue(null);
        visualCheckArea.clear();
        healthCheckArea.clear();
        findingsArea.clear();
        statusComboBox.setValue(null);
        notificationArea.clear();

        // Reset button states
        accessModuleButton.setText("Access Inspection Module");
        accessModuleButton.setDisable(false);
        selectAnimalButton.setText("Select Animal");
        selectAnimalButton.setDisable(false);
        conductCheckButton.setText("Complete Check");
        conductCheckButton.setDisable(false);
        enterFindingsButton.setText("Enter Findings");
        enterFindingsButton.setDisable(false);
        submitStatusButton.setText("Submit Status");
        submitStatusButton.setDisable(false);
        notifySupervisorButton.setText("Notify Supervisor");
        notifySupervisorButton.setDisable(false);

        System.out.println("Form reset");
    }

    @FXML
    private void handleComplete(ActionEvent event) {
        System.out.println("Pre-slaughter inspection workflow completed");
        // Could navigate to dashboard or show completion message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inspection Complete");
        alert.setHeaderText(null);
        alert.setContentText("Pre-slaughter animal inspection has been completed successfully!");
        alert.showAndWait();
    }
}