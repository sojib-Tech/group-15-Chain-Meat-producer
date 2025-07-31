package com.example.group15chainmeatproducer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class SceneManager {

    public static void switchToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Loginfxml.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Chain Meat Producer - Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToSignUp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/SignUpfxml.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Chain Meat Producer - Sign Up");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}