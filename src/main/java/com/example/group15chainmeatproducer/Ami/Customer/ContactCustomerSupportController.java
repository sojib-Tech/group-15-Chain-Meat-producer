package com.example.group15chainmeatproducer.Ami.Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.example.group15chainmeatproducer.SceneManager;

public class ContactCustomerSupportController {

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCustomerMenu(event);
    }
}
