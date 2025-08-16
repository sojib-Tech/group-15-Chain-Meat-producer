package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class UpdateOrderStatusController {

    @FXML
    private TextField orderIdField;
    @FXML
    private ComboBox<String> statusCombo;
    @FXML
    private DatePicker updateDatePicker;
    @FXML
    private Button updateButton;
    @FXML
    private Button backButton;
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> orderIdCol;
    @FXML
    private TableColumn<Order, String> customerNameCol;
    @FXML
    private TableColumn<Order, String> statusCol;
    @FXML
    private TableColumn<Order, String> updateDateCol;

    private final ArrayList<Order> orders = new ArrayList<>();

    @FXML
    private void initialize() {
        statusCombo.getItems().setAll("Pending", "Shipped", "Delivered");
        orderIdCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getOrderId()));
        customerNameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCustomerName()));
        statusCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));
        updateDateCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUpdateDate() == null ? "" : c.getValue().getUpdateDate().toString()));
        orders.addAll(OrderStore.load());
        if (orders.isEmpty()) seedSampleData();
        ordersTable.getItems().setAll(orders);
    }

    @FXML
    private void handleUpdateStatus(ActionEvent event) {
        String id = orderIdField.getText() == null ? "" : orderIdField.getText().trim();
        String newStatus = statusCombo.getValue();
        LocalDate when = updateDatePicker.getValue() == null ? LocalDate.now() : updateDatePicker.getValue();
        if (id.isEmpty() || newStatus == null || newStatus.isBlank()) return;
        for (Order o : orders) {
            if (id.equals(o.getOrderId())) {
                o.setStatus(newStatus);
                o.setUpdateDate(when);
                break;
            }
        }
        OrderStore.save(orders);
        ordersTable.getItems().setAll(orders);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    private void seedSampleData() {
        orders.add(new Order("ORD-3001", "Isla Brown", "Mixed cuts", "Pending", LocalDate.now().minusDays(1)));
        orders.add(new Order("ORD-3002", "Jack Davis", "Chicken bundle", "Shipped", LocalDate.now().minusDays(2)));
        orders.add(new Order("ORD-3003", "Kara Mills", "Lamb rack", "Delivered", LocalDate.now().minusDays(7)));
        orders.add(new Order("ORD-3004", "Liam Chen", "Beef steak", "Pending", LocalDate.now()));
        OrderStore.save(orders);
    }
}
