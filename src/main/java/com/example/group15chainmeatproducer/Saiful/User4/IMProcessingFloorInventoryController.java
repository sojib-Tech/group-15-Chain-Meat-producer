package com.example.group15chainmeatproducer.Saiful.User4;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import com.example.group15chainmeatproducer.SceneManager;

public class IMProcessingFloorInventoryController {

    @FXML
    private Button backButton;

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToUser4Menu(event);
    }

    // Placeholder methods for other buttons
    @FXML
    private void handleViewDashboard(ActionEvent event) {
        System.out.println("View dashboard");
    }

    @FXML
    private void handleSelectShiftLine(ActionEvent event) {
        System.out.println("Select shift line");
    }

    @FXML
    private void handleReviewData(ActionEvent event) {
        System.out.println("Review data");
    }

    @FXML
    private void handleCompareUsage(ActionEvent event) {
        System.out.println("Compare usage");
    }

    @FXML
    private void handleEnterUpdate(ActionEvent event) {
        System.out.println("Enter update");
    }

    @FXML
    private void handleAlertSupervisor(ActionEvent event) {
        System.out.println("Alert supervisor");
    }

    @FXML
    private void handleReset(ActionEvent event) {
        System.out.println("Reset form");
    }

    @FXML
    private void handleComplete(ActionEvent event) {
        System.out.println("Complete monitoring");
    }
}