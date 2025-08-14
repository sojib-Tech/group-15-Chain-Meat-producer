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

public class ThirdPartyLogisticsController implements Initializable {

    @FXML
    private TableView<LogisticsProvider> providerTable;
    @FXML
    private TableColumn<LogisticsProvider, String> colProviderName;
    @FXML
    private TableColumn<LogisticsProvider, String> colServiceType;
    @FXML
    private TableColumn<LogisticsProvider, String> colContractExpiry;
    @FXML
    private TableColumn<LogisticsProvider, String> colContactPerson;
    @FXML
    private TableColumn<LogisticsProvider, String> colContactInfo;
    @FXML
    private TableColumn<LogisticsProvider, String> colRating;

    @FXML
    private TextField providerNameField;
    @FXML
    private ComboBox<String> serviceTypeCombo;
    @FXML
    private DatePicker contractExpiryPicker;
    @FXML
    private TextField contactPersonField;
    @FXML
    private TextField contactInfoField;

    @FXML
    private RadioButton ratingExcellent;
    @FXML
    private RadioButton ratingGood;
    @FXML
    private RadioButton ratingAverage;
    @FXML
    private RadioButton ratingPoor;
    @FXML
    private ToggleGroup ratingToggleGroup;

    @FXML
    private Button backButton;

    private final List<LogisticsProvider> providerStore = new ArrayList<>();
    private ObservableList<LogisticsProvider> tableItems;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> services = new ArrayList<>();
        services.add("Freight");
        services.add("Warehousing");
        services.add("Customs");
        services.add("Others");
        serviceTypeCombo.setItems(FXCollections.observableList(services));

        tableItems = FXCollections.observableList(providerStore);
        providerTable.setItems(tableItems);

        colProviderName.setCellValueFactory(new PropertyValueFactory<>("providerName"));
        colServiceType.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        colContactPerson.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        colContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        colRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        colContractExpiry.setCellValueFactory(cell -> new SimpleStringProperty(formatDate(cell.getValue().getContractExpiry())));
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }

    private String formatDate(LocalDate d) {
        if (d == null) return "";
        return DATE_FORMATTER.format(d);
    }
}
