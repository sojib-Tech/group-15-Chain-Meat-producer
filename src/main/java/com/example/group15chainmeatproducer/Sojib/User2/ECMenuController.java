package com.example.group15chainmeatproducer.Sojib.User2;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ECMenuController {

    @FXML
    private Button backButton;
    @FXML
    private Button goal4Button;

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }

    @FXML
    public void goal1OnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Goal1PendingExportOrder.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Failed to load Pending Export Order screen: " + e.getMessage());
        }
    }

    @FXML
    public void goal2OnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Goal2ConsignmentWeight.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Failed to load Consignment Weight screen: " + e.getMessage());
        }
    }

    @FXML
    public void goal3OnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Goal3DestinationClient.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Failed to load Destination Client screen: " + e.getMessage());
        }
    }

    @FXML
    public void goal4OnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Navigate to Goal 4: Submit Customs Invoice Details");
        Parent root = FXMLLoader.load(getClass().getResource("12.EC Submit Customs Invoice Details.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goal5OnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Navigate to Goal 5: Choose Logistics Partner");
        Parent root = FXMLLoader.load(getClass().getResource("13.EC Choose Logistics Partner.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goal6OnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Navigate to Goal 6: Input Dispatch");
        Parent root = FXMLLoader.load(getClass().getResource("14.EC Input Dispatch.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void goal7OnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Navigate to Goal 7: Confirm Export Clearance");
        Parent root = FXMLLoader.load(getClass().getResource("15.EC Confirm Export Clearance via Customs Portal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goal8OnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Navigate to Goal 8: Upload Delivery Confirmation");
        Parent root = FXMLLoader.load(getClass().getResource("16.EC Upload Signed Delivery Confirmation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}