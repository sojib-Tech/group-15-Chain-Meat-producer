package com.example.group15chainmeatproducer.Saiful.User4;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class IMMenuController {

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
        SceneManager.switchToUser4Goal1(event);
    }

    @FXML
    private void handleGoal2(ActionEvent event) {
        SceneManager.switchToUser4Goal2(event);
    }

    @FXML
    private void handleGoal3(ActionEvent event) {
        SceneManager.switchToUser4Goal3(event);
    }

    @FXML
    private void handleGoal4(ActionEvent event) {
        SceneManager.switchToUser4Goal4(event);
    }

    @FXML
    private void handleGoal5(ActionEvent event) {
        SceneManager.switchToUser4Goal5(event);
    }

    @FXML
    private void handleGoal6(ActionEvent event) {
        SceneManager.switchToUser4Goal6(event);
    }

    @FXML
    private void handleGoal7(ActionEvent event) {
        SceneManager.switchToUser4Goal7(event);
    }

    @FXML
    private void handleGoal8(ActionEvent event) {
        SceneManager.switchToUser4Goal8(event);
    }
}