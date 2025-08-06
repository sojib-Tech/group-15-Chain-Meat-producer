package com.example.group15chainmeatproducer.Sojib.User2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class ECSelectPendingExportOrder {

    @FXML private TextField customerIdField;
    @FXML private TextField customerNameField;
    @FXML private TextField fullAddressField;
    @FXML private TextField phoneNumberField;
    @FXML private DatePicker orderDatePicker;

    @FXML private TableView<ExportOrderData> exportOrderTable;
    @FXML private TableColumn<ExportOrderData, String> colCustomerId;
    @FXML private TableColumn<ExportOrderData, String> colCustomerName;
    @FXML private TableColumn<ExportOrderData, String> colFullAddress;
    @FXML private TableColumn<ExportOrderData, String> colOrderDate;
    @FXML private TableColumn<ExportOrderData, String> colPhoneNumber;

    @FXML private Button btnBack;

    private final File exportOrderFile = new File("export_order.bin");
    private final ObservableList<ExportOrderData> orderList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCustomerId.setCellValueFactory(data -> data.getValue().customerIdProperty());
        colCustomerName.setCellValueFactory(data -> data.getValue().customerNameProperty());
        colFullAddress.setCellValueFactory(data -> data.getValue().fullAddressProperty());
        colOrderDate.setCellValueFactory(data -> data.getValue().orderDateProperty());
        colPhoneNumber.setCellValueFactory(data -> data.getValue().phoneNumberProperty());

        exportOrderTable.setItems(orderList);
        loadOrdersFromFile();
    }

    @FXML
    public void handleSave() {
        String id = customerIdField.getText().trim();
        String name = customerNameField.getText().trim();
        String address = fullAddressField.getText().trim();
        String phone = phoneNumberField.getText().trim();
        LocalDate date = orderDatePicker.getValue();

        if (id.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty() || date == null) {
            showAlert("Please fill in all fields.");
            return;
        }

        ExportOrderData order = new ExportOrderData(id, name, address, date.toString(), phone);
        orderList.add(order);
        appendToFile(order);

        clearFields();
    }

    private void appendToFile(ExportOrderData data) {
        try {
            boolean append = exportOrderFile.exists() && exportOrderFile.length() > 0;
            FileOutputStream fos = new FileOutputStream(exportOrderFile, true);
            ObjectOutputStream oos = append ? new AppendingObjectOutputStream(fos) : new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error saving data.");
        }
    }

    private void loadOrdersFromFile() {
        if (!exportOrderFile.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(exportOrderFile))) {
            while (true) {
                ExportOrderData data = (ExportOrderData) ois.readObject();
                orderList.add(data);
            }
        } catch (EOFException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("EC_MenuPage.fxml"));
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Failed to load menu page.");
        }
    }

    private void clearFields() {
        customerIdField.clear();
        customerNameField.clear();
        fullAddressField.clear();
        phoneNumberField.clear();
        orderDatePicker.setValue(null);
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notice");
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
}
