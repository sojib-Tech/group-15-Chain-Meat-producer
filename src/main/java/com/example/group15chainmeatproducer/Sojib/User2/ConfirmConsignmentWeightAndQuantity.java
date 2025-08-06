package com.example.group15chainmeatproducer.Sojib.User2;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfirmConsignmentWeightAndQuantity {

    @FXML private ComboBox<String> customerIdComboBox;
    @FXML private Label orderDetailsLabel;
    @FXML private TextField packageCountField;
    @FXML private TextField totalWeightField;
    @FXML private Button btnBack;

    private final File exportOrderFile = new File("export_order.bin");
    private final File packageOrderFile = new File("package_order.bin");

    private final Map<String, ExportOrderData> exportOrderMap = new HashMap<>();

    @FXML
    public void initialize() {
        loadExportOrders();
    }

    private void loadExportOrders() {
        if (!exportOrderFile.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(exportOrderFile))) {
            while (true) {
                ExportOrderData order = (ExportOrderData) ois.readObject();
                exportOrderMap.put(order.getCustomerId(), order);
            }
        } catch (EOFException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }

        customerIdComboBox.setItems(FXCollections.observableArrayList(exportOrderMap.keySet()));
    }

    @FXML
    public void onCustomerSelected() {
        String selectedId = customerIdComboBox.getValue();
        ExportOrderData order = exportOrderMap.get(selectedId);
        if (order != null) {
            orderDetailsLabel.setText("Name: " + order.getCustomerName() +
                    "\nAddress: " + order.getFullAddress() +
                    "\nOrder Date: " + order.getOrderDate() +
                    "\nPhone: " + order.getPhoneNumber());
        }
    }

    @FXML
    public void submit() {
        String customerId = customerIdComboBox.getValue();
        String packageCount = packageCountField.getText().trim();
        String totalWeight = totalWeightField.getText().trim();

        if (customerId == null || packageCount.isEmpty() || totalWeight.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        ExportOrderData order = exportOrderMap.get(customerId);
        if (order == null) {
            showAlert("Selected customer ID is invalid.");
            return;
        }

        PackageOrder record = new PackageOrder(
                order.getCustomerId(), order.getCustomerName(), order.getFullAddress(),
                order.getOrderDate(), order.getPhoneNumber(),
                packageCount, totalWeight
        );

        saveToPackageFile(record);
        showAlert("Package order saved successfully.");
        clearInputs();
    }

    private void clearInputs() {
        customerIdComboBox.setValue(null);
        orderDetailsLabel.setText("Order details will appear here.");
        packageCountField.clear();
        totalWeightField.clear();
    }

    private void saveToPackageFile(PackageOrder record) {
        try {
            boolean append = packageOrderFile.exists() && packageOrderFile.length() > 0;
            FileOutputStream fos = new FileOutputStream(packageOrderFile, true);
            ObjectOutputStream oos = append ? new AppendingObjectOutputStream(fos) : new ObjectOutputStream(fos);
            oos.writeObject(record);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to save package order.");
        }
    }

    @FXML
    public void back() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("EC_MenuPage.fxml"));
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Failed to load menu page.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private static class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }
        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }

    private static class PackageOrder implements Serializable {
        String customerId, customerName, fullAddress, orderDate, phoneNumber, packageCount, totalWeight;

        public PackageOrder(String customerId, String customerName, String fullAddress,
                            String orderDate, String phoneNumber,
                            String packageCount, String totalWeight) {
            this.customerId = customerId;
            this.customerName = customerName;
            this.fullAddress = fullAddress;
            this.orderDate = orderDate;
            this.phoneNumber = phoneNumber;
            this.packageCount = packageCount;
            this.totalWeight = totalWeight;
        }
    }
}
