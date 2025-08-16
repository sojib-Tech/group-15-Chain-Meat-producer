package com.example.group15chainmeatproducer.Ami.Customer;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchSpecificProductController {

    @FXML
    private TextField searchField;
    @FXML
    private DatePicker addedAfterDatePicker;
    @FXML
    private Button searchButton;
    @FXML
    private Button backButton;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, String> dateAddedColumn;

    private final ArrayList<Product> masterData = new ArrayList<>();

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        categoryColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCategory()));
        dateAddedColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDateAdded() == null ? "" : cell.getValue().getDateAdded().toString()));
        masterData.addAll(DataStoreCustomer.loadProducts());
        productTable.getItems().setAll(masterData);
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        String q = searchField.getText() == null ? "" : searchField.getText().trim().toLowerCase();
        LocalDate after = addedAfterDatePicker.getValue();
        ArrayList<Product> filtered = new ArrayList<>();
        for (Product p : masterData) {
            boolean ok = true;
            if (!q.isEmpty()) ok = ok && p.getName() != null && p.getName().toLowerCase().contains(q);
            if (after != null) ok = ok && p.getDateAdded() != null && p.getDateAdded().isAfter(after);
            if (ok) filtered.add(p);
        }
        productTable.getItems().setAll(filtered);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
