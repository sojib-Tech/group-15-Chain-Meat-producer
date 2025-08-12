package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import com.example.group15chainmeatproducer.SceneManager;

public class ViewAllCustomerOrdersController {

    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> statusFilter;
    @FXML
    private RadioButton todayRadio;
    @FXML
    private RadioButton last7DaysRadio;
    @FXML
    private ToggleGroup dateQuickFilterGroup;
    @FXML
    private DatePicker dateFilter;

    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> orderIdColumn;
    @FXML
    private TableColumn<Order, String> customerNameColumn;
    @FXML
    private TableColumn<Order, String> itemsSummaryColumn;
    @FXML
    private TableColumn<Order, String> statusColumn;

    @FXML
    private Button viewDetailsButton;
    @FXML
    private Button backButton;

    private final ObservableList<Order> masterData = FXCollections.observableArrayList();
    private FilteredList<Order> filteredData;

    @FXML
    private void initialize() {
        statusFilter.getItems().setAll("All", "Pending", "Shipped", "Delivered", "Cancelled");
        statusFilter.getSelectionModel().selectFirst();

        if (dateQuickFilterGroup == null) {
            dateQuickFilterGroup = new ToggleGroup();
        }
        todayRadio.setToggleGroup(dateQuickFilterGroup);
        last7DaysRadio.setToggleGroup(dateQuickFilterGroup);

        orderIdColumn.setCellValueFactory(cell -> cell.getValue().orderIdProperty());
        customerNameColumn.setCellValueFactory(cell -> cell.getValue().customerNameProperty());
        itemsSummaryColumn.setCellValueFactory(cell -> cell.getValue().itemsSummaryProperty());
        statusColumn.setCellValueFactory(cell -> cell.getValue().statusProperty());

        var loaded = OrderStore.load();
        if (loaded.isEmpty()) {
            seedSampleData();
        } else {
            masterData.setAll(loaded);
        }

        filteredData = new FilteredList<>(masterData, o -> true);
        ordersTable.setItems(filteredData);

        // Listeners
        searchField.textProperty().addListener((obs, old, val) -> applyFilters());
        statusFilter.valueProperty().addListener((obs, old, val) -> applyFilters());
        dateQuickFilterGroup.selectedToggleProperty().addListener((obs, old, val) -> applyFilters());
        dateFilter.valueProperty().addListener((obs, old, val) -> applyFilters());

        viewDetailsButton.setOnAction(e -> handleViewDetails());
        backButton.setOnAction(this::handleBack);
    }

    private void applyFilters() {
        final String search = searchField.getText() == null ? "" : searchField.getText().trim().toLowerCase();
        final String status = statusFilter.getValue();
        final Toggle selectedQuick = dateQuickFilterGroup.getSelectedToggle();
        final LocalDate date = dateFilter.getValue();

        filteredData.setPredicate(buildPredicate(search, status, selectedQuick, date));
    }

    private Predicate<Order> buildPredicate(String search, String status, Toggle quickToggle, LocalDate date) {
        return order -> {
            if (order == null) return false;

            boolean matchesSearch = search.isEmpty()
                    || (order.getOrderId() != null && order.getOrderId().toLowerCase().contains(search))
                    || (order.getCustomerName() != null && order.getCustomerName().toLowerCase().contains(search));

            boolean matchesStatus = (status == null || status.equals("All"))
                    || (order.getStatus() != null && order.getStatus().equalsIgnoreCase(status));

            boolean matchesQuickDate = true;
            LocalDate today = LocalDate.now();
            if (quickToggle == todayRadio) {
                matchesQuickDate = order.getOrderDate() != null && order.getOrderDate().isEqual(today);
            } else if (quickToggle == last7DaysRadio) {
                matchesQuickDate = order.getOrderDate() != null && !order.getOrderDate().isBefore(today.minusDays(6));
            }

            boolean matchesPickedDate = date == null
                    || (order.getOrderDate() != null && order.getOrderDate().isEqual(date));

            return matchesSearch && matchesStatus && matchesQuickDate && matchesPickedDate;
        };
    }

    private void seedSampleData() {
        masterData.setAll(
                new Order("ORD-1001", "Alice Johnson", "Beef x2, Lamb x1", "Pending", LocalDate.now()),
                new Order("ORD-1002", "Bob Smith", "Chicken x5", "Shipped", LocalDate.now().minusDays(3)),
                new Order("ORD-1003", "Carla Gomez", "Pork x3", "Delivered", LocalDate.now().minusDays(8)),
                new Order("ORD-1004", "Daniel Wu", "Turkey x1", "Cancelled", LocalDate.now().minusDays(1))
        );
    }

    @FXML
    private void handleViewDetails() {
        Order selected = ordersTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.INFORMATION, "No Selection", "Please select an order to view details.");
            return;
        }
        showAlert(Alert.AlertType.INFORMATION, "Order Details",
                "Order ID: " + selected.getOrderId() + "\n" +
                        "Customer: " + selected.getCustomerName() + "\n" +
                        "Items: " + selected.getItemsSummary() + "\n" +
                        "Status: " + selected.getStatus() + "\n" +
                        "Date: " + selected.getOrderDate());
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCSRMenu(event);
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

