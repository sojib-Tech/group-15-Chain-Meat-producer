package com.example.group15chainmeatproducer.Sojib.User2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import com.example.group15chainmeatproducer.SceneManager;

public class ECMenuController {

    @FXML
    private Button backButton;
    @FXML
    private Button goal1Button;
    @FXML
    private Button goal2Button;
    @FXML
    private Button goal3Button;
    @FXML
    private Button goal4Button;
    @FXML
    private Button goal5Button;
    @FXML
    private Button goal6Button;
    @FXML
    private Button goal7Button;
    @FXML
    private Button goal8Button;

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }

    @FXML
    private void handleGoal1(ActionEvent event) {
        System.out.println("Navigate to Goal 1: Select Pending Export Order");
        // SceneManager.switchToUser2Goal1(event);
    }

    @FXML
    private void handleGoal2(ActionEvent event) {
        System.out.println("Navigate to Goal 2: Confirm Consignment Weight");
        // SceneManager.switchToUser2Goal2(event);
    }

    @FXML
    private void handleGoal3(ActionEvent event) {
        System.out.println("Navigate to Goal 3: Input Destination and Client Name");
        // SceneManager.switchToUser2Goal3(event);
    }

    @FXML
    private void handleGoal4(ActionEvent event) {
        System.out.println("Navigate to Goal 4: Submit Customs Invoice Details");
        // SceneManager.switchToUser2Goal4(event);
    }

    @FXML
    private void handleGoal5(ActionEvent event) {
        System.out.println("Navigate to Goal 5: Choose Logistics Partner");
        // SceneManager.switchToUser2Goal5(event);
    }

    @FXML
    private void handleGoal6(ActionEvent event) {
        System.out.println("Navigate to Goal 6: Input Dispatch");
        // SceneManager.switchToUser2Goal6(event);
    }

    @FXML
    private void handleGoal7(ActionEvent event) {
        System.out.println("Navigate to Goal 7: Confirm Export Clearance");
        // SceneManager.switchToUser2Goal7(event);
    }

    @FXML
    private void handleGoal8(ActionEvent event) {
        System.out.println("Navigate to Goal 8: Upload Delivery Confirmation");
        // SceneManager.switchToUser2Goal8(event);
    }
}