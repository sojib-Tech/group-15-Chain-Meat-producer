package com.example.group15chainmeatproducer.Ami.Customer;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class CheckoutPlaceOrderController {

    @FXML
    private TextField deliveryAddressField;
    @FXML
    private TextField contactNumberField;
    @FXML
    private ComboBox<String> paymentMethodComboBox;
    @FXML
    private ToggleGroup paymentTypeGroup;
    @FXML
    private RadioButton fullPaymentRadio;
    @FXML
    private RadioButton partialPaymentRadio;
    @FXML
    private DatePicker preferredDatePicker;

    @FXML
    private TableView<CartItem> orderTable;
    @FXML
    private TableColumn<CartItem, String> productNameColumn;
    @FXML
    private TableColumn<CartItem, Integer> quantityColumn;
    @FXML
    private TableColumn<CartItem, Double> priceColumn;
    @FXML
    private TableColumn<CartItem, Double> subtotalColumn;

    @FXML
    private Button placeOrderButton;

    private final ArrayList<CartItem> orderItems = new ArrayList<>();

    @FXML
    private void initialize() {
        paymentMethodComboBox.setItems(FXCollections.observableArrayList("Credit Card", "Cash on Delivery"));

        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(cell -> {
            CartItem item = cell.getValue();
            double unit = item.getQuantity() > 0 ? item.getPrice() / item.getQuantity() : item.getPrice();
            return new ReadOnlyObjectWrapper<>(unit);
        });
        subtotalColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getPrice()));
        orderItems.addAll(DataStoreCustomer.loadCart());
        orderTable.setItems(FXCollections.observableArrayList(orderItems));
    }

    @FXML
    private void handlePlaceOrder(ActionEvent event) {
        String address = deliveryAddressField.getText();
        String phone = contactNumberField.getText();
        String paymentMethod = paymentMethodComboBox.getValue();
        String paymentType = fullPaymentRadio.isSelected() ? "Full" : (partialPaymentRadio.isSelected() ? "Partial" : "");
        String date = preferredDatePicker.getValue() != null ? preferredDatePicker.getValue().toString() : "";
        System.out.println("Placing order -> address=" + address + ", phone=" + phone + ", method=" + paymentMethod + ", type=" + paymentType + ", date=" + date + ", items=" + orderItems.size());
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCustomerMenu(event);
    }
}
