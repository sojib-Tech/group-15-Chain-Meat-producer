package com.example.group15chainmeatproducer.Ami.Customer;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class ViewProductDetailsController {
    @FXML
    private ComboBox<String> productComboBox;
    @FXML
    private DatePicker asOfDatePicker;
    @FXML
    private Button showDetailsButton;
    @FXML
    private Button backButton;
    @FXML
    private TableView<Product> detailsTable;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;

    private final ArrayList<Product> products = new ArrayList<>();

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        categoryColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCategory()));
        descriptionColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescription()));
        products.addAll(DataStoreCustomer.loadProducts());
        ArrayList<String> names = new ArrayList<>();
        for (Product p : products) names.add(p.getName());
        productComboBox.getItems().setAll(names);
    }

    @FXML
    private void handleShowDetails(ActionEvent event) {
        String selectedName = productComboBox.getValue();
        LocalDate asOf = asOfDatePicker.getValue();
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (selectedName != null && selectedName.equals(p.getName())) {
                if (asOf == null || (p.getDateAdded() != null && !p.getDateAdded().isAfter(asOf))) {
                    result.add(p);
                }
                break;
            }
        }
        detailsTable.getItems().setAll(result);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
