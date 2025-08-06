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

public class UploadTrayPhotoForQC {

    @FXML
    private TextField comments;
    @FXML
    private TextField opratorID;
    @FXML
    private ComboBox<String> trayID;
    @FXML
    private TableView<Object> table_upload_tray_photo_for_QC;
    @FXML
    private TableColumn<Object, String> t_opratorID;
    @FXML
    private TableColumn<Object, String> t_trayID;
    @FXML
    private TableColumn<Object, String> t_comments;

    @FXML
    public void initialize() {
        // Initialize combo box with sample data
        if (trayID != null) {
            trayID.getItems().addAll("Tray001", "Tray002", "Tray003");
        }
    }

    @FXML
    public void uploadPhoto(ActionEvent event) {
        // Handle photo upload
        System.out.println("Photo uploaded");
    }

    @FXML
    public void submitgoal5data(ActionEvent event) {
        // Handle data submission
        System.out.println("Goal 5 data submitted");
    }

    @FXML
    public void backtogoal4(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FF_MenuPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}