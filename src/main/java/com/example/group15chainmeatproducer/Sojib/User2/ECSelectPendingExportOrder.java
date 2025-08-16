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

public class ECSelectPendingExportOrder implements Initializable {

    @FXML private TextField customerIdField;
    @FXML private TextField customerNameField;
    @FXML private TextField fullAddressField;
    @FXML private DatePicker orderDatePicker;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;
    @FXML private TableView<ExportOrderData> exportOrderTable;
    @FXML private TableColumn<ExportOrderData, String> colCustomerId;
    @FXML private TableColumn<ExportOrderData, String> colCustomerName;
    @FXML private TableColumn<ExportOrderData, String> colFullAddress;
    @FXML private TableColumn<ExportOrderData, String> colOrderDate;
    @FXML private TableColumn<ExportOrderData, String> colPhoneNumber;

    private ArrayList<ExportOrderData> exportOrderList;
    private final String DATA_FILE = "exportOrders.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadData();
    }

    private void setupTable() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colFullAddress.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            exportOrderList = (ArrayList<ExportOrderData>) ois.readObject();
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
        ObservableList<ExportOrderData> observableList = FXCollections.observableArrayList(exportOrderList);
        exportOrderTable.setItems(observableList);
    }

    @FXML
    private void handleSave() {
        if (validateInput()) {
            String customerId = customerIdField.getText();
            String customerName = customerNameField.getText();
            String fullAddress = fullAddressField.getText();
            String orderDate = orderDatePicker.getValue() != null ? orderDatePicker.getValue().toString() : "";
            String phoneNumber = phoneNumberField.getText();

            ExportOrderData orderData = new ExportOrderData(customerId, customerName, fullAddress, orderDate, phoneNumber);
            exportOrderList.add(orderData);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Export order saved successfully!");
        }
    }

    private boolean validateInput() {
        if (customerIdField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Customer ID");
            return false;
        }
        if (customerNameField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Customer Name");
            return false;
        }
        if (fullAddressField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Full Address");
            return false;
        }
        if (orderDatePicker.getValue() == null) {
            showAlert("Error", "Please select Order Date");
            return false;
        }
        if (phoneNumberField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Phone Number");
            return false;
        }
        return true;
    }

    private void clearFields() {
        customerIdField.clear();
        customerNameField.clear();
        fullAddressField.clear();
        orderDatePicker.setValue(null);
        phoneNumberField.clear();
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

    public static class ExportOrderData implements Serializable {
        private String customerId;
        private String customerName;
        private String fullAddress;
        private String orderDate;
        private String phoneNumber;

        public ExportOrderData(String customerId, String customerName, String fullAddress, String orderDate, String phoneNumber) {
            this.customerId = customerId;
            this.customerName = customerName;
            this.fullAddress = fullAddress;
            this.orderDate = orderDate;
            this.phoneNumber = phoneNumber;
        }

        public String getCustomerId() {
            return customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getFullAddress() {
            return fullAddress;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
    }
}
