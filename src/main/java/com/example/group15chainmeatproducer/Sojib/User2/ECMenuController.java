package com.example.group15chainmeatproducer.Sojib.User2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import com.example.group15chainmeatproducer.SceneManager;

public class ECMenuController {

    @FXML
    private Button backButton;
    @FXML
    private Button goal4Button;

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }

    @Deprecated
    private void handleGoal1(ActionEvent event) {
        System.out.println("Navigate to Goal 1: Select Pending Export Order");
        // SceneManager.switchToUser2Goal1(event);
    }

    @Deprecated
    private void handleGoal2(ActionEvent event) {
        System.out.println("Navigate to Goal 2: Confirm Consignment Weight");
        // SceneManager.switchToUser2Goal2(event);
    }

    @Deprecated
    private void handleGoal3(ActionEvent event) {
        System.out.println("Navigate to Goal 3: Input Destination and Client Name");
        // SceneManager.switchToUser2Goal3(event);
    }

    @Deprecated
    private void handleGoal4(ActionEvent event) {
        System.out.println("Navigate to Goal 4: Submit Customs Invoice Details");
        // SceneManager.switchToUser2Goal4(event);
    }

    @Deprecated
    private void handleGoal5(ActionEvent event) {
        System.out.println("Navigate to Goal 5: Choose Logistics Partner");
        // SceneManager.switchToUser2Goal5(event);
    }

    @Deprecated
    private void handleGoal6(ActionEvent event) {
        System.out.println("Navigate to Goal 6: Input Dispatch");
        // SceneManager.switchToUser2Goal6(event);
    }

    @Deprecated
    private void handleGoal7(ActionEvent event) {
        System.out.println("Navigate to Goal 7: Confirm Export Clearance");
        // SceneManager.switchToUser2Goal7(event);
    }

    @Deprecated
    private void handleGoal8(ActionEvent event) {
        System.out.println("Navigate to Goal 8: Upload Delivery Confirmation");
        // SceneManager.switchToUser2Goal8(event);
    }

    @FXML
    public void goal4OnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void goal8OnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void goal6OnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void goal3OnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void goal2OnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void goal1OnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void goal5OnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void goal7OnAction(ActionEvent actionEvent) {
    }
}