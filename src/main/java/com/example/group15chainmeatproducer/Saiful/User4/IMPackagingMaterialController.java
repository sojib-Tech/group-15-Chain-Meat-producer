package com.example.group15chainmeatproducer.Saiful.User4;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class IMPackagingMaterialController {

    @FXML
    private Button backButton;

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToUser4Menu(event);
    }

    // Placeholder methods for other buttons
    @FXML
    private void handleAccessScreen(ActionEvent event) {
        System.out.println("Access screen");
    }

    @FXML
    private void handleSelectBatch(ActionEvent event) {
        System.out.println("Select batch");
    }

    @FXML
    private void handleInputMaterial(ActionEvent event) {
        System.out.println("Input material");
    }

    @FXML
    private void handleCompareUsage(ActionEvent event) {
        System.out.println("Compare usage");
    }

    @FXML
    private void handleRecordDiscrepancy(ActionEvent event) {
        System.out.println("Record discrepancy");
    }

    @FXML
    private void handleNotifyProcurement(ActionEvent event) {
        System.out.println("Notify procurement");
    }

    @FXML
    private void handleReset(ActionEvent event) {
        System.out.println("Reset form");
    }

    @FXML
    private void handleComplete(ActionEvent event) {
        System.out.println("Complete tracking");
    }
}