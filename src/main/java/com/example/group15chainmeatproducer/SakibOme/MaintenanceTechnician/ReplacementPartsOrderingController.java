package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReplacementPartsOrderingController implements Initializable {

    @FXML
    private TableView<RequiredPart> partsTable;
    @FXML
    private TableColumn<RequiredPart, String> colPartId;
    @FXML
    private TableColumn<RequiredPart, String> colPartName;
    @FXML
    private TableColumn<RequiredPart, String> colQuantity;
    @FXML
    private TableColumn<RequiredPart, String> colSupplier;
    @FXML
    private TableColumn<RequiredPart, String> colExpectedDelivery;
    @FXML
    private Button backButton;

    private final List<RequiredPart> store = new ArrayList<>();
    private ObservableList<RequiredPart> tableItems;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableItems = FXCollections.observableList(store);
        partsTable.setItems(tableItems);
        colPartId.setCellValueFactory(new PropertyValueFactory<>("partId"));
        colPartName.setCellValueFactory(new PropertyValueFactory<>("partName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantityNeeded"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colExpectedDelivery.setCellValueFactory(cell -> new SimpleStringProperty(formatDate(cell.getValue().getExpectedDelivery())));
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToMaintenanceMenu(event);
    }

    private String formatDate(LocalDate d) {
        if (d == null) return "";
        return DATE_FORMAT.format(d);
    }
}
