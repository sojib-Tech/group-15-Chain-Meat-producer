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

public class DeliveryStatusNotificationController implements Initializable {

    @FXML
    private TextField deliveryIdField;
    @FXML
    private Button searchDeliveryButton;
    @FXML
    private TableView<DeliveryRecord> deliveryTable;
    @FXML
    private TableColumn<DeliveryRecord, String> colDeliveryId;
    @FXML
    private TableColumn<DeliveryRecord, String> colClientName;
    @FXML
    private TableColumn<DeliveryRecord, String> colShipmentDate;
    @FXML
    private TableColumn<DeliveryRecord, String> colCurrentStatus;
    @FXML
    private Button validateButton;
    @FXML
    private ComboBox<String> notificationTypeCombo;
    @FXML
    private DatePicker notificationDatePicker;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField messageField;
    @FXML
    private Button sendButton;
    @FXML
    private Button backButton;

    private final List<DeliveryRecord> store = new ArrayList<>();
    private ObservableList<DeliveryRecord> tableItems;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> types = new ArrayList<>();
        types.add("Email");
        types.add("SMS");
        types.add("System Message");
        notificationTypeCombo.setItems(FXCollections.observableList(types));

        tableItems = FXCollections.observableList(store);
        deliveryTable.setItems(tableItems);
        colDeliveryId.setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
        colClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        colCurrentStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
        colShipmentDate.setCellValueFactory(cell -> new SimpleStringProperty(formatDate(cell.getValue().getShipmentDate())));
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
