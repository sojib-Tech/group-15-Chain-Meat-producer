package com.example.group15chainmeatproducer.Sojib.User1;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FFMenuController {

    @FXML
    private Button backButton;

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }

    @Deprecated
    private void handleGoal2(ActionEvent event) throws IOException {
        System.out.println("Navigate to Goal 2: Upload Hygiene Checklist");}

    @Deprecated
    private void handleGoal3(ActionEvent event) {
        System.out.println("Navigate to Goal 3: Confirm Meat Cutting Machine Calibration");
        // SceneManager.switchToUser1Goal3(event);
    }

    @Deprecated
    private void handleGoal4(ActionEvent event) {
        System.out.println("Navigate to Goal 4: Input Number of Cut Meat Pieces");
        // SceneManager.switchToUser1Goal4(event);
    }

    @Deprecated
    private void handleGoal5(ActionEvent event) {
        System.out.println("Navigate to Goal 5: Upload Tray Photo for QC");
        // SceneManager.switchToUser1Goal5(event);
    }

    @Deprecated
    private void handleGoal6(ActionEvent event) {
        System.out.println("Navigate to Goal 6: Select Packaging Material");
        // SceneManager.switchToUser1Goal6(event);
    }

    @Deprecated
    private void handleGoal7(ActionEvent event) {
        System.out.println("Navigate to Goal 7: Record Cold Storage Entry Time");
        // SceneManager.switchToUser1Goal7(event);
    }

    @Deprecated
    private void handleGoal8(ActionEvent event) {
        System.out.println("Navigate to Goal 8: Mark Shift Handover");
        // SceneManager.switchToUser1Goal8(event);
    }

    @FXML
    public void goal1OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("1..FF Scan Animal ID Before Slaughter.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();}

    @FXML
    public void goal4OnAction(ActionEvent actionEvent) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("4.FF Input Number of Cut Meat Pieces.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goal8OnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Navigate to Goal 2: Upload Hygiene Checklist");
        Parent root = FXMLLoader.load(getClass().getResource("8.FF Mark Shift Handover.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goal6OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("6.FF Select Packaging Material.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goal3OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("3'FFconfarm meat cutting machine calibration.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goal2OnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Navigate to Goal 2: Upload Hygiene Checklist");
        Parent root = FXMLLoader.load(getClass().getResource("2.FFcheckIn.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goal5OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("5.FF Upload Tray Photo for QC.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goal7OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("7.FF Record Cold Storage Entry Time.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}