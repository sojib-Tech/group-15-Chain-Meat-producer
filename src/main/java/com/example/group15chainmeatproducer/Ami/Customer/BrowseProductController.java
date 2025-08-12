package com.example.group15chainmeatproducer.Ami.Customer;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class BrowseProductController {

    @FXML
    private TextField searchField;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Image> imageColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;

    private final ArrayList<Product> masterData = new ArrayList<>();

    @FXML
    private void initialize() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("shortDescription"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        imageColumn.setCellFactory(col -> new TableCell<Product, Image>() {
            private final ImageView view = new ImageView();
            @Override
            protected void updateItem(Image img, boolean empty) {
                super.updateItem(img, empty);
                if (empty || img == null) {
                    setGraphic(null);
                } else {
                    view.setImage(img);
                    view.setFitWidth(80);
                    view.setPreserveRatio(true);
                    setGraphic(view);
                }
            }
        });
        masterData.addAll(DataStoreCustomer.loadProducts());
        productTable.setItems(FXCollections.observableArrayList(masterData));
        searchField.textProperty().addListener((obs, oldV, newV) -> {
            String q = newV == null ? "" : newV.trim().toLowerCase();
            if (q.isEmpty()) {
                productTable.setItems(FXCollections.observableArrayList(masterData));
                return;
            }
            ArrayList<Product> filtered = new ArrayList<>();
            for (Product p : masterData) {
                if ((p.getName() != null && p.getName().toLowerCase().contains(q)) ||
                        (p.getShortDescription() != null && p.getShortDescription().toLowerCase().contains(q))) {
                    filtered.add(p);
                }
            }
            productTable.setItems(FXCollections.observableArrayList(filtered));
        });
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCustomerMenu(event);
    }
}
