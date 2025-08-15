package com.example.group15chainmeatproducer.Ami.Customer;

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

public class BrowseProductController {
    @FXML
    private TextField productNameField;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private DatePicker dateAddedPicker;
    @FXML
    private Button showProductsButton;
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
        categoryComboBox.getItems().setAll("Beef", "Chicken", "Vegetables");
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        categoryColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCategory()));
        dateAddedColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDateAdded() == null ? "" : cell.getValue().getDateAdded().toString()));
        masterData.addAll(DataStoreCustomer.loadProducts());
        productTable.getItems().setAll(masterData);
    }

    @FXML
    private void handleShowProducts(ActionEvent event) {
        String nameFilter = productNameField.getText() == null ? "" : productNameField.getText().trim().toLowerCase();
        String categoryFilter = categoryComboBox.getValue();
        LocalDate dateFilter = dateAddedPicker.getValue();
        ArrayList<Product> filtered = new ArrayList<>();
        for (Product p : masterData) {
            boolean ok = true;
            if (!nameFilter.isEmpty()) ok = ok && p.getName() != null && p.getName().toLowerCase().contains(nameFilter);
            if (categoryFilter != null && !categoryFilter.isEmpty()) ok = ok && categoryFilter.equals(p.getCategory());
            if (dateFilter != null) ok = ok && p.getDateAdded() != null && !p.getDateAdded().isAfter(dateFilter);
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
