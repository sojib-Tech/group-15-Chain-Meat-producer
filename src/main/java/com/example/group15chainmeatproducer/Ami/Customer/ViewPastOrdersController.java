package com.example.group15chainmeatproducer.Ami.Customer;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class ViewPastOrdersController {

    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private ToggleGroup quickRangeGroup;
    @FXML
    private RadioButton last30DaysRadio;
    @FXML
    private RadioButton last6MonthsRadio;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private DatePicker toDatePicker;

    @FXML
    private TableView<OrderSummary> ordersTable;
    @FXML
    private TableColumn<OrderSummary, LocalDate> dateColumn;
    @FXML
    private TableColumn<OrderSummary, String> orderIdColumn;
    @FXML
    private TableColumn<OrderSummary, String> itemsSummaryColumn;
    @FXML
    private TableColumn<OrderSummary, String> statusColumn;

    private final ArrayList<OrderSummary> masterOrders = new ArrayList<>();

    @FXML
    private void initialize() {
        // Table binding
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        itemsSummaryColumn.setCellValueFactory(new PropertyValueFactory<>("itemsSummary"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Status options
        statusComboBox.setItems(FXCollections.observableArrayList("Delivered", "Pending", "Cancelled"));
        statusComboBox.setOnAction(e -> applyFilters());

        // Load from bin
        masterOrders.addAll(DataStoreCustomer.loadOrders());

        // Listeners
        searchField.textProperty().addListener((obs, o, n) -> applyFilters());
        fromDatePicker.valueProperty().addListener((obs, o, n) -> applyFilters());
        toDatePicker.valueProperty().addListener((obs, o, n) -> applyFilters());

        ordersTable.setItems(FXCollections.observableArrayList(masterOrders));
    }

    @FXML
    private void handleQuickRange(ActionEvent event) {
        if (last30DaysRadio.isSelected()) {
            fromDatePicker.setValue(LocalDate.now().minusDays(30));
            toDatePicker.setValue(LocalDate.now());
        } else if (last6MonthsRadio.isSelected()) {
            fromDatePicker.setValue(LocalDate.now().minusMonths(6));
            toDatePicker.setValue(LocalDate.now());
        }
        applyFilters();
    }

    private void applyFilters() {
        String q = searchField.getText() == null ? "" : searchField.getText().trim().toLowerCase();
        String status = statusComboBox.getValue();
        LocalDate from = fromDatePicker.getValue();
        LocalDate to = toDatePicker.getValue();

        ArrayList<OrderSummary> filtered = new ArrayList<>();
        for (OrderSummary o : masterOrders) {
            boolean matchesText = q.isEmpty() ||
                    (o.getOrderId() != null && o.getOrderId().toLowerCase().contains(q)) ||
                    (o.getItemsSummary() != null && o.getItemsSummary().toLowerCase().contains(q));
            boolean matchesStatus = (status == null || status.isEmpty()) || status.equals(o.getStatus());
            boolean matchesDate = true;
            if (from != null && o.getOrderDate() != null) {
                matchesDate &= !o.getOrderDate().isBefore(from);
            }
            if (to != null && o.getOrderDate() != null) {
                matchesDate &= !o.getOrderDate().isAfter(to);
            }
            if (matchesText && matchesStatus && matchesDate) {
                filtered.add(o);
            }
        }
        ordersTable.setItems(FXCollections.observableArrayList(filtered));
    }

    @FXML
    private void handleViewDetails(ActionEvent event) {
        OrderSummary selected = ordersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            System.out.println("View details for " + selected.getOrderId());
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCustomerMenu(event);
    }
}
