package com.example.group15chainmeatproducer.SakibOme.LogisticsManager;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShipmentTransportCordinationController implements Initializable {

    
    @FXML private TextField shipmentIdField;
    @FXML private ComboBox<String> shipmentTypeCombo;
    @FXML private DatePicker scheduledDatePicker;
    
    @FXML private ToggleGroup priorityToggleGroup;
    @FXML private RadioButton priorityHighRadio;
    @FXML private RadioButton priorityMediumRadio;
    @FXML private RadioButton priorityLowRadio;
    @FXML private Button validateButton;
    @FXML private TextArea processedPlanTextArea;
    @FXML private Button confirmButton;
    @FXML private Button backButton;
    @FXML private TableView<ShipmentPlan> shipmentTable;
    @FXML private TableColumn<ShipmentPlan, String> colShipmentId;
    @FXML private TableColumn<ShipmentPlan, String> colType;
    @FXML private TableColumn<ShipmentPlan, String> colDate;
    @FXML private TableColumn<ShipmentPlan, String> colPriority;
    @FXML private TableColumn<ShipmentPlan, String> colStatus;


    private final List<ShipmentPlan> plansStore = new ArrayList<>();


    private ObservableList<ShipmentPlan> tableItems;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> shipmentTypes = new ArrayList<>();
        shipmentTypes.add("Inbound");
        shipmentTypes.add("Outbound");
        shipmentTypeCombo.setItems(FXCollections.observableList(shipmentTypes));

        if (priorityMediumRadio != null) {
            priorityMediumRadio.setSelected(true);
        }
        tableItems = FXCollections.observableList(plansStore);
        shipmentTable.setItems(tableItems);

        colShipmentId.setCellValueFactory(new PropertyValueFactory<>("shipmentId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Format LocalDate to human-readable string for the Date column
        colDate.setCellValueFactory(cellData -> {
            LocalDate date = cellData.getValue().getDate();
            String asText = (date == null) ? "" : DATE_FORMATTER.format(date);
            return new SimpleStringProperty(asText);
        });
    }

    @FXML
    private void onValidate(ActionEvent event) {
        String validationMessage = buildValidationMessage();
        processedPlanTextArea.setText(validationMessage);
    }

    @FXML
    private void onConfirmAndSave(ActionEvent event) {
        String validationMessage = buildValidationMessage();
        if (!validationMessage.startsWith("All good")) {
            processedPlanTextArea.setText(validationMessage);
            showSimpleAlert("Please fix the issues", validationMessage, Alert.AlertType.WARNING);
            return;
        }


        String shipmentId = shipmentIdField.getText().trim();
        String type = shipmentTypeCombo.getValue();
        LocalDate date = scheduledDatePicker.getValue();
        String priority = getSelectedPriority();

        ShipmentPlan plan = new ShipmentPlan(shipmentId, type, date, priority, "Confirmed");


        tableItems.add(plan);

        processedPlanTextArea.setText(
                "Plan saved for Shipment " + shipmentId + " (" + type + ") on " + DATE_FORMATTER.format(date) +
                        " with priority " + priority + ". Status: Confirmed.");

        clearForm();
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }

    private String buildValidationMessage() {
        List<String> issues = new ArrayList<>();

        if (shipmentIdField.getText() == null || shipmentIdField.getText().trim().isEmpty()) {
            issues.add("Shipment ID is required");
        }
        if (shipmentTypeCombo.getValue() == null || shipmentTypeCombo.getValue().trim().isEmpty()) {
            issues.add("Please select a shipment type");
        }
        if (scheduledDatePicker.getValue() == null) {
            issues.add("Please select a scheduled transport date");
        }
        if (getSelectedPriority() == null) {
            issues.add("Please choose a priority");
        }

        if (issues.isEmpty()) {
            return "All good. Data looks valid and ready to process.";
        }
        return "Fix the following before proceeding:\n - " + String.join("\n - ", issues);
    }

    private String getSelectedPriority() {
        if (priorityToggleGroup.getSelectedToggle() == null) {
            return null;
        }
        RadioButton selected = (RadioButton) priorityToggleGroup.getSelectedToggle();
        return selected.getText();
    }

    private void clearForm() {
        shipmentIdField.clear();
        shipmentTypeCombo.getSelectionModel().clearSelection();
        scheduledDatePicker.setValue(null);
        priorityMediumRadio.setSelected(true);
    }

    private void showSimpleAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
