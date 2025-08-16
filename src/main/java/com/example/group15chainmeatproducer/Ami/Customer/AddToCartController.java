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

public class AddToCartController {

    @FXML
    private ComboBox<String> productComboBox;
    @FXML
    private TextField quantityField;
    @FXML
    private DatePicker orderDatePicker;
    @FXML
    private Button addToCartButton;
    @FXML
    private Button backButton;
    @FXML
    private TableView<CartItem> cartTable;
    @FXML
    private TableColumn<CartItem, String> productNameColumn;
    @FXML
    private TableColumn<CartItem, String> quantityColumn;
    @FXML
    private TableColumn<CartItem, String> dateAddedColumn;

    private final ArrayList<CartItem> cart = new ArrayList<>();
    private final ArrayList<Product> products = new ArrayList<>();

    @FXML
    private void initialize() {
        productNameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getProductName()));
        quantityColumn.setCellValueFactory(cell -> new SimpleStringProperty(Integer.toString(cell.getValue().getQuantity())));
        dateAddedColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDateAddedToCart() == null ? "" : cell.getValue().getDateAddedToCart().toString()));
        products.addAll(DataStoreCustomer.loadProducts());
        ArrayList<String> names = new ArrayList<>();
        for (Product p : products) names.add(p.getName());
        productComboBox.getItems().setAll(names);
        cart.addAll(DataStoreCustomer.loadCart());
        cartTable.getItems().setAll(cart);
    }

    @FXML
    private void handleAddToCart(ActionEvent event) {
        String name = productComboBox.getValue();
        int qty;
        try {
            qty = Integer.parseInt(quantityField.getText());
        } catch (Exception e) {
            qty = 1;
        }
        LocalDate date = orderDatePicker.getValue() == null ? LocalDate.now() : orderDatePicker.getValue();
        if (name != null && !name.isEmpty() && qty > 0) {
            cart.add(new CartItem(name, qty, date));
            DataStoreCustomer.saveCart(cart);
            cartTable.getItems().setAll(cart);
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
