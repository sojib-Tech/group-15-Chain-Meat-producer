package com.example.group15chainmeatproducer.SakibOme.LogisticsManager;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RawMaterialDeliveryController implements Initializable {

    @FXML
    private TextField supplierNameField;
    @FXML
    private TextField materialTypeField;
    @FXML
    private TextField quantityField;
    @FXML
    private DatePicker expectedDatePicker;

    @FXML
    private Button scheduleButton;
    @FXML
    private Button validateButton;
    @FXML
    private Button analysisButton;
    @FXML
    private Button backButton;

    @FXML
    private TableView<RawMaterialDelivery> deliveryTable;
    @FXML
    private TableColumn<RawMaterialDelivery, String> colSupplierName;
    @FXML
    private TableColumn<RawMaterialDelivery, String> colMaterialType;
    @FXML
    private TableColumn<RawMaterialDelivery, String> colQuantity;
    @FXML
    private TableColumn<RawMaterialDelivery, String> colScheduledDate;
    @FXML
    private TableColumn<RawMaterialDelivery, String> colStatus;

    private final List<RawMaterialDelivery> deliveries = new ArrayList<>();
    private ObservableList<RawMaterialDelivery> tableItems;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableItems = FXCollections.observableList(deliveries);
        deliveryTable.setItems(tableItems);
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colMaterialType.setCellValueFactory(new PropertyValueFactory<>("materialType"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colScheduledDate.setCellValueFactory(cell -> new SimpleStringProperty(formatDate(cell.getValue().getScheduledDate())));
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToLogisticsMenu(event);
    }

    private String formatDate(LocalDate d) {
        if (d == null) return "";
        return DATE_FORMATTER.format(d);
    }
}
