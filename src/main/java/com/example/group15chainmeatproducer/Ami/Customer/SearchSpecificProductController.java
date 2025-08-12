package com.example.group15chainmeatproducer.Ami.Customer;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class SearchSpecificProductController {

    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private ToggleGroup availabilityGroup;
    @FXML
    private RadioButton inStockRadio;
    @FXML
    private RadioButton outOfStockRadio;
    @FXML
    private DatePicker availabilityDatePicker;
    @FXML
    private Button searchButton;

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;

    private final ArrayList<Product> masterData = new ArrayList<>();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("shortDescription"));
        categoryComboBox.setItems(FXCollections.observableArrayList("Beef", "Chicken", "Fish"));
        // Load from bin
        masterData.addAll(DataStoreCustomer.loadProducts());
        productTable.setItems(FXCollections.observableArrayList(masterData));
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        String q = searchField.getText() == null ? "" : searchField.getText().trim().toLowerCase();
        if (q.isEmpty()) {
            productTable.setItems(FXCollections.observableArrayList(masterData));
            return;
        }
        ArrayList<Product> filtered = new ArrayList<>();
        for (Product p : masterData) {
            if ((p.getId() != null && p.getId().toLowerCase().contains(q)) ||
                    (p.getName() != null && p.getName().toLowerCase().contains(q)) ||
                    (p.getShortDescription() != null && p.getShortDescription().toLowerCase().contains(q))) {
                filtered.add(p);
            }
        }
        productTable.setItems(FXCollections.observableArrayList(filtered));
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCustomerMenu(event);
    }
}
