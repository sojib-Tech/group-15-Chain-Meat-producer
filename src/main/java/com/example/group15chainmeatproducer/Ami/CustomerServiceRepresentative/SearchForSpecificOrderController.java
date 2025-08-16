package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchForSpecificOrderController {
    @FXML
    private Label titleLabel;
    @FXML
    private TextField searchField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button searchButton;
    @FXML
    private Button backButton;
    @FXML
    private TableView<Order> resultsTable;
    @FXML
    private TableColumn<Order, String> orderIdCol;
    @FXML
    private TableColumn<Order, String> customerNameCol;
    @FXML
    private TableColumn<Order, String> itemsCol;
    @FXML
    private TableColumn<Order, String> statusCol;

    private final ArrayList<Order> orders = new ArrayList<>();

    @FXML
    private void initialize() {
        orderIdCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getOrderId()));
        customerNameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCustomerName()));
        itemsCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getItems()));
        statusCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));
        orders.addAll(OrderStore.load());
        if (orders.isEmpty()) seedSampleData();
        resultsTable.getItems().setAll(orders);
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        String q = searchField.getText() == null ? "" : searchField.getText().trim().toLowerCase();
        LocalDate picked = datePicker.getValue();
        ArrayList<Order> filtered = new ArrayList<>();
        for (Order o : orders) {
            boolean ok = true;
            if (!q.isEmpty()) {
                boolean idMatch = o.getOrderId() != null && o.getOrderId().toLowerCase().contains(q);
                boolean nameMatch = o.getCustomerName() != null && o.getCustomerName().toLowerCase().contains(q);
                ok = idMatch || nameMatch;
            }
            if (picked != null) ok = ok && o.getOrderDate() != null && o.getOrderDate().isEqual(picked);
            if (ok) filtered.add(o);
        }
        resultsTable.getItems().setAll(filtered);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    private void seedSampleData() {
        orders.add(new Order("ORD-2001", "Evelyn Hart", "Steak x3", "Pending", LocalDate.now().minusDays(1)));
        orders.add(new Order("ORD-2002", "Frank Zhou", "Chicken x4", "Pending", LocalDate.now()));
        orders.add(new Order("ORD-2003", "Grace Lee", "Lamb x2", "Shipped", LocalDate.now().minusDays(5)));
        orders.add(new Order("ORD-2004", "Henry Kim", "Pork x6", "Delivered", LocalDate.now().minusDays(8)));
        OrderStore.save(orders);
    }
}
