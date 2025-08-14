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

public class ImportExportDocumentController implements Initializable {

    @FXML
    private Button validateDocsButton;
    @FXML
    private TextField searchShipmentIdField;
    @FXML
    private Button searchShipmentButton;
    @FXML
    private ComboBox<String> documentTypeCombo;
    @FXML
    private DatePicker issueDatePicker;
    @FXML
    private Button processDocumentButton;

    @FXML
    private TableView<ShipmentDocument> documentsTable;
    @FXML
    private TableColumn<ShipmentDocument, String> colDocumentId;
    @FXML
    private TableColumn<ShipmentDocument, String> colShipmentId;
    @FXML
    private TableColumn<ShipmentDocument, String> colType;
    @FXML
    private TableColumn<ShipmentDocument, String> colIssueDate;
    @FXML
    private TableColumn<ShipmentDocument, String> colStatus;

    @FXML
    private TextField documentNumberField;
    @FXML
    private TextField issuingAuthorityField;
    @FXML
    private Button saveChangesButton;
    @FXML
    private Button backButton;

    private final List<ShipmentDocument> docsStore = new ArrayList<>();
    private ObservableList<ShipmentDocument> tableItems;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> types = new ArrayList<>();
        types.add("Bill of Lading");
        types.add("Invoice");
        types.add("Packing List");
        types.add("Customs Declaration");
        documentTypeCombo.setItems(FXCollections.observableList(types));

        tableItems = FXCollections.observableList(docsStore);
        documentsTable.setItems(tableItems);
        colDocumentId.setCellValueFactory(new PropertyValueFactory<>("documentId"));
        colShipmentId.setCellValueFactory(new PropertyValueFactory<>("shipmentId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colIssueDate.setCellValueFactory(cell -> new SimpleStringProperty(formatDate(cell.getValue().getIssueDate())));
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
