package com.example.group15chainmeatproducer.Saiful.User3;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class QAColdStorageAuditController {

    @FXML
    private Button backButton; //l

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToUser3Menu(event);
    }

    // Placeholder methods for other buttons
    @FXML
    private void handleLoginTracker(ActionEvent event) {
        System.out.println("Login tracker");
    }

    @FXML
    private void handleSelectRoom(ActionEvent event) {
        System.out.println("Select room");
    }

    @FXML
    private void handleReviewTrends(ActionEvent event) {
        System.out.println("Review trends");
    }

    @FXML
    private void handleExportReport(ActionEvent event) {
        System.out.println("Export report");
    }

    @FXML
    private void handleSubmitVerification(ActionEvent event) {
        System.out.println("Submit verification");
    }

    @FXML
    private void handleNotifyManagement(ActionEvent event) {
        System.out.println("Notify management");
    }

    @FXML
    private void handleReset(ActionEvent event) {
        System.out.println("Reset form");
    }

    @FXML
    private void handleComplete(ActionEvent event) {
        System.out.println("Complete audit");
    }
}