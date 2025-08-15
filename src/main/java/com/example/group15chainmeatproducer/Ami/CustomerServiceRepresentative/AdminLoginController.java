package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class AdminLoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private DatePicker loginDatePicker;
    @FXML
    private Button loginButton;
    @FXML
    private Button backButton;
    @FXML
    private TableView<LoginAttempt> loginTable;
    @FXML
    private TableColumn<LoginAttempt, String> usernameColumn;
    @FXML
    private TableColumn<LoginAttempt, String> loginDateColumn;
    @FXML
    private TableColumn<LoginAttempt, String> statusColumn;

    private final ArrayList<LoginAttempt> attempts = new ArrayList<>();

    @FXML
    private void initialize() {
        usernameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getUsername()));
        loginDateColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLoginDate() == null ? "" : cell.getValue().getLoginDate().toString()));
        statusColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getStatus()));
        attempts.addAll(LoginStore.load());
        loginTable.getItems().setAll(attempts);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String user = usernameField.getText();
        String pass = passwordField.getText();
        LocalDate date = loginDatePicker.getValue() == null ? LocalDate.now() : loginDatePicker.getValue();
        String status = (user != null && !user.isBlank() && pass != null && !pass.isBlank()) ? "Success" : "Failed";
        LoginAttempt attempt = new LoginAttempt(user == null ? "" : user.trim(), date, status);
        attempts.add(attempt);
        LoginStore.save(attempts);
        loginTable.getItems().setAll(attempts);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
