package com.example.group15chainmeatproducer.Ami.Customer;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class AddToCartController {

    @FXML
    private TextField productNameField;
    @FXML
    private TextField quantityField;
    @FXML
    private ComboBox<String> variantComboBox;
    @FXML
    private ToggleGroup deliveryGroup;
    @FXML
    private RadioButton homeDeliveryRadio;
    @FXML
    private RadioButton pickupRadio;
    @FXML
    private DatePicker deliveryDatePicker;

    @FXML
    private TableView<CartItem> cartTable;
    @FXML
    private TableColumn<CartItem, String> productNameColumn;
    @FXML
    private TableColumn<CartItem, Integer> quantityColumn;
    @FXML
    private TableColumn<CartItem, Double> priceColumn;

    private final ArrayList<CartItem> cart = new ArrayList<>();

    @FXML
    private void initialize() {
        variantComboBox.setItems(FXCollections.observableArrayList("500 g", "1 kg", "2 kg"));

        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        cart.addAll(DataStoreCustomer.loadCart());
        cartTable.setItems(FXCollections.observableArrayList(cart));
    }

    @FXML
    private void handleAddToCart(ActionEvent event) {
        String name = productNameField.getText() == null || productNameField.getText().trim().isEmpty()
                ? "Unnamed Product" : productNameField.getText().trim();
        String variant = variantComboBox.getValue();
        int qty;
        try {
            qty = Integer.parseInt(quantityField.getText());
        } catch (Exception e) {
            qty = 1;
        }
        double pricePerUnit = switch (variant == null ? "" : variant) {
            case "500 g" -> 6.0;
            case "1 kg" -> 10.0;
            case "2 kg" -> 18.0;
            default -> 8.0;
        };
        double total = pricePerUnit * qty;
        cart.add(new CartItem(name, qty, total));
        DataStoreCustomer.saveCart(cart);
        cartTable.setItems(FXCollections.observableArrayList(cart));
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCustomerMenu(event);
    }
}
