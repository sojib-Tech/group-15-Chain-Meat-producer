package com.example.group15chainmeatproducer.Sojib.User2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PendingExportOrderController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> pendingOrderComboBox;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnConfirm;
    @FXML
    private TableView<PendingExportOrder> exportOrderTable;
    @FXML
    private TableColumn<PendingExportOrder, String> colOrderId;
    @FXML
    private TableColumn<PendingExportOrder, String> colCustomerName;
    @FXML
    private TableColumn<PendingExportOrder, String> colItems;
    @FXML
    private TableColumn<PendingExportOrder, String> colStatus;

    private ArrayList<PendingExportOrder> exportOrderList;
    private final String DATA_FILE = "pendingExportOrders.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        setupComboBox();
        loadData();
    }

    private void setupTable() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colItems.setCellValueFactory(new PropertyValueFactory<>("items"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void setupComboBox() {
        pendingOrderComboBox.getItems().addAll("ORD001", "ORD002", "ORD003", "ORD004", "ORD005");
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            exportOrderList = (ArrayList<PendingExportOrder>) ois.readObject();
        } catch (Exception e) {
            exportOrderList = new ArrayList<>();
        }
        refreshTable();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(exportOrderList);
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void refreshTable() {
        ObservableList<PendingExportOrder> observableList = FXCollections.observableArrayList(exportOrderList);
        exportOrderTable.setItems(observableList);
    }

    @FXML
    private void handleConfirmSelection() {
        if (validateInput()) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String orderId = pendingOrderComboBox.getValue();
            String customerName = "Customer for " + orderId;
            String items = "Items for " + orderId;
            String status = "Confirmed";

            PendingExportOrder order = new PendingExportOrder(username, password, orderId, customerName, items, status);
            exportOrderList.add(order);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Export order confirmed successfully!");
        }
    }

    private boolean validateInput() {
        if (usernameField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Username");
            return false;
        }
        if (passwordField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Password");
            return false;
        }
        if (pendingOrderComboBox.getValue() == null) {
            showAlert("Error", "Please select a Pending Order ID");
            return false;
        }
        return true;
    }

    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
        pendingOrderComboBox.setValue(null);
    }

    @FXML
    private void handleBack() {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EC_MenuPage.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            showAlert("Error", "Failed to go back: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}