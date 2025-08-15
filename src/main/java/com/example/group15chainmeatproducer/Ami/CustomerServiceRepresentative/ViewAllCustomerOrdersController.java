package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class ViewAllCustomerOrdersController {

    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private Button showAllButton;
    @FXML
    private Button backButton;
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> orderIdColumn;
    @FXML
    private TableColumn<Order, String> customerNameColumn;
    @FXML
    private TableColumn<Order, String> itemsColumn;
    @FXML
    private TableColumn<Order, String> statusColumn;

    private final ArrayList<Order> orders = new ArrayList<>();

    @FXML
    private void initialize() {
        orderIdColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getOrderId()));
        customerNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCustomerName()));
        itemsColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getItems()));
        statusColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));
        orders.addAll(OrderStore.load());
        if (orders.isEmpty()) seedSampleData();
        ordersTable.getItems().setAll(orders);
    }

    @FXML
    private void handleShowAll(ActionEvent event) {
        LocalDate from = fromDatePicker.getValue();
        if (from == null) {
            ordersTable.getItems().setAll(orders);
            return;
        }
        ArrayList<Order> filtered = new ArrayList<>();
        for (Order o : orders) {
            if (o.getOrderDate() != null && (o.getOrderDate().isEqual(from) || o.getOrderDate().isAfter(from))) {
                filtered.add(o);
            }
        }
        ordersTable.getItems().setAll(filtered);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    private void seedSampleData() {
        orders.add(new Order("ORD-1001", "Alice Johnson", "Beef x2, Lamb x1", "Pending", LocalDate.now()));
        orders.add(new Order("ORD-1002", "Bob Smith", "Chicken x5", "Shipped", LocalDate.now().minusDays(3)));
        orders.add(new Order("ORD-1003", "Carla Gomez", "Pork x3", "Delivered", LocalDate.now().minusDays(8)));
        orders.add(new Order("ORD-1004", "Daniel Wu", "Turkey x1", "Cancelled", LocalDate.now().minusDays(1)));
        OrderStore.save(orders);
    }
}
