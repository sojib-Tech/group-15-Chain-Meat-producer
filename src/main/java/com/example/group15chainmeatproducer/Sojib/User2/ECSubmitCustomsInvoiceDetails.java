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

public class ECSubmitCustomsInvoiceDetails {

    @FXML private ComboBox<String> customerIdComboBox;
    @FXML private TextArea productDetails;
    @FXML private Label orderInfoLabel;
    @FXML private Button btnBack;

    private final File exportOrderFile = new File("export_order.bin");
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
            orderInfoLabel.setText(
                    "Customer ID: " + order.getCustomerId() + "\n" +
                            "Name: " + order.getCustomerName() + "\n" +
                            "Address: " + order.getFullAddress() + "\n" +
                            "Order Date: " + order.getOrderDate() + "\n" +
                            "Phone: " + order.getPhoneNumber()
            );
        } else {
            orderInfoLabel.setText("No details found for selected ID.");
        }
    }

    @FXML
    public void submitInvoice() {
        String selectedId = customerIdComboBox.getValue();
        String products = productDetails.getText().trim();

        if (selectedId == null || products.isEmpty()) {
            showAlert("Please select a Customer ID and fill in product details.");
            return;
        }

        ExportOrderData order = exportOrderMap.get(selectedId);
        if (order == null) {
            showAlert("Invalid Customer ID selected.");
            return;
        }

        // Generate invoice text
        String invoiceText = "--- Customs Invoice Slip ---\n"
                + "Customer ID: " + order.getCustomerId() + "\n"
                + "Name: " + order.getCustomerName() + "\n"
                + "Address: " + order.getFullAddress() + "\n"
                + "Order Date: " + order.getOrderDate() + "\n"
                + "Phone: " + order.getPhoneNumber() + "\n\n"
                + "Product Details:\n" + products;

        try {
            File txtFile = new File("Invoice_" + selectedId + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile))) {
                writer.write(invoiceText);
            }

            showAlert("Invoice saved as TXT: " + txtFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to generate text invoice.");
        }
    }

    @FXML
    public void backfrontpage12() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("EC_MenuPage.fxml")); // adjust if needed
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Failed to return to menu.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
