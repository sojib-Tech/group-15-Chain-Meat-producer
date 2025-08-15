package com.example.group15chainmeatproducer.Saiful.User4;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class IMStockReconciliationController {

    @FXML
    private Button backButton;

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToUser4Menu(event);
    }

    // Placeholder methods for other buttons
    @FXML
    private void handleAccessReconciliation(ActionEvent event) {
        System.out.println("Access reconciliation");
    }

    @FXML
    private void handleLoadReport(ActionEvent event) {
        System.out.println("Load report");
    }

    @FXML
    private void handleCountStock(ActionEvent event) {
        System.out.println("Count stock");
    }

    @FXML
    private void handleInputValues(ActionEvent event) {
        System.out.println("Input values");
    }

    @FXML
    private void handleSubmitEntry(ActionEvent event) {
        System.out.println("Submit entry");
    }

    @FXML
    private void handleNotifyWarehouse(ActionEvent event) {
        System.out.println("Notify warehouse");
    }

    @FXML
    private void handleReset(ActionEvent event) {
        System.out.println("Reset form");
    }

    @FXML
    private void handleComplete(ActionEvent event) {
        System.out.println("Complete reconciliation");
    }
}