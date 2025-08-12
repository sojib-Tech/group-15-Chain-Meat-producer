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

public class SearchForSpecificOrderController {

    @FXML
    private Label titleLabel;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> statusCombo;
    @FXML
    private RadioButton last24hRadio;
    @FXML
    private RadioButton lastWeekRadio;
    @FXML
    private ToggleGroup quickFilterGroup;
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
    private TableColumn<Order, LocalDate> orderDateCol;
    @FXML
    private TableColumn<Order, String> statusCol;

    private final ObservableList<Order> master = FXCollections.observableArrayList();
    private FilteredList<Order> filtered;

    @FXML
    private void initialize() {
        statusCombo.getItems().setAll("All", "Pending", "Completed", "Cancelled", "Shipped", "Delivered");
        statusCombo.getSelectionModel().selectFirst();

        // Ensure toggle group is set
        if (quickFilterGroup == null) quickFilterGroup = new ToggleGroup();
        last24hRadio.setToggleGroup(quickFilterGroup);
        lastWeekRadio.setToggleGroup(quickFilterGroup);

        // Table columns mapping
        orderIdCol.setCellValueFactory(c -> c.getValue().orderIdProperty());
        customerNameCol.setCellValueFactory(c -> c.getValue().customerNameProperty());
        orderDateCol.setCellValueFactory(c -> c.getValue().orderDateProperty());
        statusCol.setCellValueFactory(c -> c.getValue().statusProperty());

        var loaded = OrderStore.load();
        if (loaded.isEmpty()) {
            seedSampleData();
        } else {
            master.setAll(loaded);
        }
        filtered = new FilteredList<>(master, o -> true);
        resultsTable.setItems(filtered);

        searchButton.setOnAction(e -> applyFilters());
        backButton.setOnAction(this::handleBack);

        // Enter key triggers search
        searchField.setOnAction(e -> applyFilters());
        datePicker.setOnAction(e -> applyFilters());
        statusCombo.setOnAction(e -> applyFilters());
        last24hRadio.setOnAction(e -> applyFilters());
        lastWeekRadio.setOnAction(e -> applyFilters());
    }

    private void applyFilters() {
        final String query = searchField.getText() == null ? "" : searchField.getText().trim().toLowerCase();
        final String status = statusCombo.getValue();
        final Toggle quick = quickFilterGroup.getSelectedToggle();
        final LocalDate picked = datePicker.getValue();

        filtered.setPredicate(buildPredicate(query, status, quick, picked));
    }

    private Predicate<Order> buildPredicate(String q, String status, Toggle quick, LocalDate picked) {
        return o -> {
            if (o == null) return false;

            boolean qMatch = q.isEmpty()
                    || (o.getOrderId() != null && o.getOrderId().toLowerCase().contains(q))
                    || (o.getCustomerName() != null && o.getCustomerName().toLowerCase().contains(q));

            boolean sMatch = (status == null || status.equals("All"))
                    || (o.getStatus() != null && o.getStatus().equalsIgnoreCase(status));

            boolean quickMatch = true;
            LocalDate today = LocalDate.now();
            if (quick == last24hRadio) {
                // Approximate: orders from today
                quickMatch = o.getOrderDate() != null && o.getOrderDate().isEqual(today);
            } else if (quick == lastWeekRadio) {
                quickMatch = o.getOrderDate() != null && !o.getOrderDate().isBefore(today.minusDays(6));
            }

            boolean dateMatch = picked == null || (o.getOrderDate() != null && o.getOrderDate().isEqual(picked));

            return qMatch && sMatch && quickMatch && dateMatch;
        };
    }

    private void seedSampleData() {
        master.setAll(
                new Order("ORD-2001", "Evelyn Hart", "Steak x3", "Completed", LocalDate.now().minusDays(1)),
                new Order("ORD-2002", "Frank Zhou", "Chicken x4", "Pending", LocalDate.now()),
                new Order("ORD-2003", "Grace Lee", "Lamb x2", "Cancelled", LocalDate.now().minusDays(5)),
                new Order("ORD-2004", "Henry Kim", "Pork x6", "Shipped", LocalDate.now().minusDays(8))
        );
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCSRMenu(event);
    }
}
