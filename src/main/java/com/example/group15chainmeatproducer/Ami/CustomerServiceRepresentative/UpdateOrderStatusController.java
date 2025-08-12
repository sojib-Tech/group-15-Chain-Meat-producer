package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import com.example.group15chainmeatproducer.SceneManager;

public class UpdateOrderStatusController {

    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> newStatusCombo;
    @FXML
    private RadioButton notifyYesRadio;
    @FXML
    private RadioButton notifyNoRadio;
    @FXML
    private ToggleGroup notifyGroup;
    @FXML
    private DatePicker expectedDatePicker;

    @FXML
    private Button updateStatusButton;
    @FXML
    private Button backButton;

    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> orderIdCol;
    @FXML
    private TableColumn<Order, String> customerNameCol;
    @FXML
    private TableColumn<Order, String> currentStatusCol;
    @FXML
    private TableColumn<Order, String> newStatusCol;

    private final ObservableList<Order> master = FXCollections.observableArrayList();
    private FilteredList<Order> filtered;
    private final Map<Order, String> pendingNewStatuses = new HashMap<>();

    @FXML
    private void initialize() {

        newStatusCombo.getItems().setAll("Pending", "Shipped", "Delivered", "Cancelled", "Completed");
        if (notifyGroup == null) notifyGroup = new ToggleGroup();
        notifyYesRadio.setToggleGroup(notifyGroup);
        notifyNoRadio.setToggleGroup(notifyGroup);
        notifyNoRadio.setSelected(true);


        var loaded = OrderStore.load();
        if (loaded.isEmpty()) {
            seedSampleData();
        } else {
            master.setAll(loaded);
        }
        orderIdCol.setCellValueFactory(c -> c.getValue().orderIdProperty());
        customerNameCol.setCellValueFactory(c -> c.getValue().customerNameProperty());
        currentStatusCol.setCellValueFactory(c -> c.getValue().statusProperty());
        newStatusCol.setCellValueFactory(c -> new ReadOnlyStringWrapper(pendingNewStatuses.getOrDefault(c.getValue(), "")));

        filtered = new FilteredList<>(master, o -> true);
        ordersTable.setItems(filtered);

        searchField.textProperty().addListener((obs, o, n) -> applyFilter());
        newStatusCombo.setOnAction(e -> previewNewStatusForSelection());

        updateStatusButton.setOnAction(e -> handleUpdateStatus());
        backButton.setOnAction(this::handleBack);
    }

    private void applyFilter() {
        final String q = searchField.getText() == null ? "" : searchField.getText().trim().toLowerCase();
        Predicate<Order> p = o -> q.isEmpty()
                || (o.getOrderId() != null && o.getOrderId().toLowerCase().contains(q))
                || (o.getCustomerName() != null && o.getCustomerName().toLowerCase().contains(q));
        filtered.setPredicate(p);
    }

    private void previewNewStatusForSelection() {
        Order selected = ordersTable.getSelectionModel().getSelectedItem();
        String newStatus = newStatusCombo.getValue();
        if (selected != null && newStatus != null && !newStatus.isBlank()) {
            pendingNewStatuses.put(selected, newStatus);
            ordersTable.refresh();
        }
    }

    @FXML
    private void handleUpdateStatus() {
        Order selected = ordersTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an order to update.");
            return;
        }
        String newStatus = newStatusCombo.getValue();
        if (newStatus == null || newStatus.isBlank()) {
            showAlert(Alert.AlertType.INFORMATION, "Choose Status", "Please select a new status from the dropdown.");
            return;
        }

        boolean notify = notifyGroup.getSelectedToggle() == notifyYesRadio;
        LocalDate expectedDate = expectedDatePicker.getValue();

        String oldStatus = selected.getStatus();
        selected.setStatus(newStatus);
        pendingNewStatuses.put(selected, newStatus);
        ordersTable.refresh();
        OrderStore.save(master);

        StringBuilder msg = new StringBuilder();
        msg.append("Order ").append(selected.getOrderId()).append(" status updated from ")
                .append(oldStatus).append(" to ").append(newStatus).append('.')
                .append("\nNotify customer: ").append(notify ? "Yes" : "No");
        if (expectedDate != null) {
            msg.append("\nExpected delivery: ").append(expectedDate);
        }
        showAlert(Alert.AlertType.INFORMATION, "Status Updated", msg.toString());
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCSRMenu(event);
    }

    private void seedSampleData() {
        master.setAll(
                new Order("ORD-3001", "Isla Brown", "Mixed cuts", "Pending", LocalDate.now().minusDays(1)),
                new Order("ORD-3002", "Jack Davis", "Chicken bundle", "Shipped", LocalDate.now().minusDays(2)),
                new Order("ORD-3003", "Kara Mills", "Lamb rack", "Delivered", LocalDate.now().minusDays(7)),
                new Order("ORD-3004", "Liam Chen", "Beef steak", "Pending", LocalDate.now())
        );
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }
}
