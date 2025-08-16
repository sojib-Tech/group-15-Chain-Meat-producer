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

public class ConfirmConsignmentWeightAndQuantity implements Initializable {

    @FXML private ComboBox<String> customerIdComboBox;
    @FXML private Label orderDetailsLabel;
    @FXML private TextField packageCountField;
    @FXML private TextField totalWeightField;
    @FXML private Button btnBack;
    @FXML
    private TableView<PackageOrder> consignmentTable;
    @FXML
    private TableColumn<PackageOrder, String> colCustomerId;
    @FXML
    private TableColumn<PackageOrder, String> colPackageCount;
    @FXML
    private TableColumn<PackageOrder, String> colTotalWeight;

    private ArrayList<PackageOrder> packageOrderList;
    private final String DATA_FILE = "packageOrders.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        setupComboBox();
        loadData();
    }

    private void setupTable() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colPackageCount.setCellValueFactory(new PropertyValueFactory<>("packageCount"));
        colTotalWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
    }

    private void setupComboBox() {
        customerIdComboBox.getItems().addAll("C001", "C002", "C003", "C004", "C005");
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            packageOrderList = (ArrayList<PackageOrder>) ois.readObject();
        } catch (Exception e) {
            packageOrderList = new ArrayList<>();
        }
        refreshTable();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(packageOrderList);
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void refreshTable() {
        ObservableList<PackageOrder> observableList = FXCollections.observableArrayList(packageOrderList);
        consignmentTable.setItems(observableList);
    }

    @FXML
    private void onCustomerSelected() {
        String selectedCustomer = customerIdComboBox.getValue();
        if (selectedCustomer != null) {
            orderDetailsLabel.setText("Order details for Customer: " + selectedCustomer +
                    "\nItems: Meat Products\nQuantity: Various packages\nStatus: Pending Confirmation");
        }
    }

    @FXML
    private void submit() {
        if (validateInput()) {
            String customerId = customerIdComboBox.getValue();
            String packageCount = packageCountField.getText();
            String totalWeight = totalWeightField.getText();

            PackageOrder packageOrder = new PackageOrder(customerId, packageCount, totalWeight);
            packageOrderList.add(packageOrder);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Consignment weight and quantity confirmed successfully!");
        }
    }

    private boolean validateInput() {
        if (customerIdComboBox.getValue() == null) {
            showAlert("Error", "Please select Customer ID");
            return false;
        }
        if (packageCountField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Package Count");
            return false;
        }
        if (totalWeightField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Total Weight");
            return false;
        }
        try {
            Integer.parseInt(packageCountField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Error", "Package Count must be a valid number");
            return false;
        }
        try {
            Double.parseDouble(totalWeightField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Error", "Total Weight must be a valid number");
            return false;
        }
        return true;
    }

    private void clearFields() {
        customerIdComboBox.setValue(null);
        packageCountField.clear();
        totalWeightField.clear();
        orderDetailsLabel.setText("Order details will appear here.");
    }

    @FXML
    private void back() {
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

    public static class PackageOrder implements Serializable {
        private String customerId;
        private String packageCount;
        private String weight;

        public PackageOrder(String customerId, String packageCount, String weight) {
            this.customerId = customerId;
            this.packageCount = packageCount;
            this.weight = weight;
        }

        public String getCustomerId() {
            return customerId;
        }

        public String getPackageCount() {
            return packageCount;
        }

        public String getWeight() {
            return weight;
        }
    }
}
