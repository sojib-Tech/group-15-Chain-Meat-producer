package com.example.group15chainmeatproducer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseUtil.initializeDatabase();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/group15chainmeatproducer/Loginfxml.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Chain Meat Producer - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}