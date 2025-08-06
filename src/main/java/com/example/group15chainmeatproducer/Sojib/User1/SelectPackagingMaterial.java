package com.example.group15chainmeatproducer.Sojib.User1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectPackagingMaterial {

    @FXML
    private TextField operator;
    @FXML
    private ComboBox<String> batch6;
    @FXML
    private ComboBox<String> materail6;

    @FXML
    public void initialize() {
        // Initialize combo boxes with sample data
        if (batch6 != null) {
            batch6.getItems().addAll("Batch001", "Batch002", "Batch003");
        }
        if (materail6 != null) {
            materail6.getItems().addAll("Plastic", "Cardboard", "Vacuum Sealed");
        }
    }

    @FXML
    public void confirmSelection(ActionEvent event) {
        // Handle selection confirmation
        System.out.println("Selection confirmed");
    }

    @FXML
    public void printLabel(ActionEvent event) {
        // Handle label printing
        System.out.println("Label printed");
    }

    @FXML
    public void backtoGoal5(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FF_MenuPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}