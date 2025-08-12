package com.example.group15chainmeatproducer.Ami.Customer;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class ViewProductDetailsController {

    // Top section fields
    @FXML
    private ImageView productImageView;
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
    private Button addToCartButton;
    
    @FXML
    private TableView<Review> reviewsTable;
    @FXML
    private TableColumn<Review, String> reviewerColumn;
    @FXML
    private TableColumn<Review, Integer> ratingColumn;
    @FXML
    private TableColumn<Review, String> commentColumn;

    private final ArrayList<Review> masterReviews = new ArrayList<>();

    @FXML
    private void initialize() {
        // Variants
        variantComboBox.setItems(FXCollections.observableArrayList("1 kg", "500 g", "2 kg"));

        // Reviews table setup
        reviewerColumn.setCellValueFactory(new PropertyValueFactory<>("reviewer"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

        // Load from DataStoreCustomer
        masterReviews.addAll(DataStoreCustomer.loadReviews());
        reviewsTable.setItems(FXCollections.observableArrayList(masterReviews));
    }

    @FXML
    private void handleAddToCart(ActionEvent event) {
        String name = productNameField.getText();
        String qty = quantityField.getText();
        String variant = variantComboBox.getValue();
        String deliveryType = homeDeliveryRadio.isSelected() ? "Home Delivery" : (pickupRadio.isSelected() ? "Pickup" : "");
        String date = deliveryDatePicker.getValue() != null ? deliveryDatePicker.getValue().toString() : "";
        System.out.println("Add to Cart -> name=" + name + ", qty=" + qty + ", variant=" + variant + ", delivery=" + deliveryType + ", date=" + date);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCustomerMenu(event);
    }

}
