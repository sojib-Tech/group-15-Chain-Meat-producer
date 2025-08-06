package com.example.group15chainmeatproducer.Sojib.User2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ECChooseLogisticsPartner {

    @FXML
    private TextField C_NAME;
    @FXML
    private TextField contact_TERMS;
    @FXML
    private ComboBox<String> logistic_partner;

    @FXML
    public void initialize() {
        // Initialize combo box with sample data
        if (logistic_partner != null) {
            logistic_partner.getItems().addAll("DHL Express", "FedEx", "UPS", "Local Transport");
        }
    }

    @FXML
    public void submit_logistic_data(ActionEvent actionEvent) {
        // Handle logistics data submission
        System.out.println("Logistics data submitted");
    }

    @FXML
    public void confirm_termss(ActionEvent actionEvent) {
        // Handle terms confirmation  
        System.out.println("Terms confirmed");
    }

    @FXML
    public void backfront_page13(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EC_MenuPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}