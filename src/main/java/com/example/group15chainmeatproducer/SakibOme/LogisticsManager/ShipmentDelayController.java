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

public class ShipmentDelayController implements Initializable {

    @FXML
    private TextField searchShipmentIdField;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<ShipmentDelayRecord> detailsTable;
    @FXML
    private TableColumn<ShipmentDelayRecord, String> colShipmentId;
    @FXML
    private TableColumn<ShipmentDelayRecord, String> colOrigin;
    @FXML
    private TableColumn<ShipmentDelayRecord, String> colDestination;
    @FXML
    private TableColumn<ShipmentDelayRecord, String> colScheduledDate;
    @FXML
    private TableColumn<ShipmentDelayRecord, String> colCurrentStatus;
    @FXML
    private TableColumn<ShipmentDelayRecord, String> colReason;
    @FXML
    private Button validateButton;
    @FXML
    private ComboBox<String> newStatusCombo;
    @FXML
    private DatePicker newEstimatedDatePicker;
    @FXML
    private TextField notesField;
    @FXML
    private Button processUpdateButton;
    @FXML
    private Button backButton;

    private final List<ShipmentDelayRecord> records = new ArrayList<>();
    private ObservableList<ShipmentDelayRecord> tableItems;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> statuses = new ArrayList<>();
        statuses.add("On Hold");
        statuses.add("Rescheduled");
        statuses.add("Cancelled");
        statuses.add("Delivered");
        newStatusCombo.setItems(FXCollections.observableList(statuses));

        tableItems = FXCollections.observableList(records);
        detailsTable.setItems(tableItems);
        colShipmentId.setCellValueFactory(new PropertyValueFactory<>("shipmentId"));
        colOrigin.setCellValueFactory(new PropertyValueFactory<>("origin"));
        colDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colCurrentStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
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
