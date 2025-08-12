package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import com.example.group15chainmeatproducer.SceneManager;

public class AdminLoginController {

    @FXML
    private Button backButton;

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCSRMenu(event);
    }
}
