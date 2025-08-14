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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ShipmentScheduleController implements Initializable {

    @FXML
    private TextField shipmentIdField;
    @FXML
    private DatePicker plannedDatePicker;
    @FXML
    private ComboBox<String> statusCombo;
    @FXML
    private Button viewScheduleButton;
    @FXML
    private TableView<ShipmentScheduleRecord> scheduleTable;
    @FXML
    private TableColumn<ShipmentScheduleRecord, String> colShipmentId;
    @FXML
    private TableColumn<ShipmentScheduleRecord, String> colStatus;
    @FXML
    private TableColumn<ShipmentScheduleRecord, String> colPlannedDate;
    @FXML
    private TableColumn<ShipmentScheduleRecord, String> colActualDate;
    @FXML
    private TableColumn<ShipmentScheduleRecord, String> colDelay;
    @FXML
    private Button validateButton;
    @FXML
    private TextField actualDateField;
    @FXML
    private TextField deliveryNotesField;
    @FXML
    private Button updateButton;
    @FXML
    private Button backButton;

    private final List<ShipmentScheduleRecord> recordsStore = new ArrayList<>();
    private ObservableList<ShipmentScheduleRecord> tableItems;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> statusOptions = new ArrayList<>();
        statusOptions.add("Scheduled");
        statusOptions.add("In Transit");
        statusOptions.add("Delivered");
        statusOptions.add("Delayed");
        statusCombo.setItems(FXCollections.observableList(statusOptions));

        tableItems = FXCollections.observableList(recordsStore);
        scheduleTable.setItems(tableItems);

        colShipmentId.setCellValueFactory(new PropertyValueFactory<>("shipmentId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPlannedDate.setCellValueFactory(cell -> new SimpleStringProperty(formatDate(cell.getValue().getPlannedDate())));
        colActualDate.setCellValueFactory(cell -> new SimpleStringProperty(formatDate(cell.getValue().getActualDate())));
        colDelay.setCellValueFactory(new PropertyValueFactory<>("delay"));
    }

    @FXML
    private void onViewCurrentSchedule(ActionEvent event) {
        String id = valueOrEmpty(shipmentIdField.getText());
        String status = statusCombo.getValue();
        LocalDate planned = plannedDatePicker.getValue();
        if (id.isEmpty() && status == null && planned == null) {
            scheduleTable.setItems(tableItems);
            return;
        }
        List<ShipmentScheduleRecord> filtered = recordsStore.stream().filter(r ->
                (id.isEmpty() || r.getShipmentId().equalsIgnoreCase(id)) &&
                        (status == null || r.getStatus().equalsIgnoreCase(status)) &&
                        (planned == null || equalsDate(r.getPlannedDate(), planned))
        ).collect(Collectors.toList());
        scheduleTable.setItems(FXCollections.observableList(filtered));
    }

    @FXML
    private void onValidateDeliveryTime(ActionEvent event) {
        String id = valueOrEmpty(shipmentIdField.getText());
        LocalDate planned = plannedDatePicker.getValue();
        LocalDate actual = parseDateOrNull(valueOrEmpty(actualDateField.getText()));
        List<String> issues = new ArrayList<>();
        if (id.isEmpty()) issues.add("Shipment ID is required");
        if (planned == null) issues.add("Planned Delivery Date is required");
        if (!valueOrEmpty(actualDateField.getText()).isEmpty() && actual == null)
            issues.add("Actual Delivery Date format should be yyyy-MM-dd");
        if (!issues.isEmpty()) {
            showAlert("Validation", String.join("\n", issues), Alert.AlertType.WARNING);
            return;
        }
        if (actual == null) {
            showAlert("Validation", "No actual date provided. Delivery time cannot be evaluated yet.", Alert.AlertType.INFORMATION);
            return;
        }
        long days = java.time.temporal.ChronoUnit.DAYS.between(planned, actual);
        if (days > 0) {
            showAlert("Validation", "Delivery delayed by " + days + " day(s).", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Validation", "Delivery on time.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void onUpdateDeliveryRecord(ActionEvent event) {
        String id = valueOrEmpty(shipmentIdField.getText());
        LocalDate planned = plannedDatePicker.getValue();
        String status = statusCombo.getValue();
        LocalDate actual = parseDateOrNull(valueOrEmpty(actualDateField.getText()));
        String notes = valueOrEmpty(deliveryNotesField.getText());
        List<String> issues = new ArrayList<>();
        if (id.isEmpty()) issues.add("Shipment ID is required");
        if (planned == null) issues.add("Planned Delivery Date is required");
        if (status == null || status.isEmpty()) issues.add("Please select a shipment status");
        if (!valueOrEmpty(actualDateField.getText()).isEmpty() && actual == null)
            issues.add("Actual Delivery Date format should be yyyy-MM-dd");
        if (!issues.isEmpty()) {
            showAlert("Update", String.join("\n", issues), Alert.AlertType.WARNING);
            return;
        }
        ShipmentScheduleRecord existing = findById(id);
        if (existing == null) {
            ShipmentScheduleRecord r = new ShipmentScheduleRecord();
            r.setShipmentId(id);
            r.setStatus(status);
            r.setPlannedDate(planned);
            r.setActualDate(actual);
            r.setDelay(makeDelay(planned, actual));
            r.setNotes(notes);
            tableItems.add(r);
        } else {
            existing.setStatus(status);
            existing.setPlannedDate(planned);
            existing.setActualDate(actual);
            existing.setDelay(makeDelay(planned, actual));
            existing.setNotes(notes);
            scheduleTable.refresh();
        }
        clearForm();
        showAlert("Update", "Delivery record saved.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }

    private ShipmentScheduleRecord findById(String id) {
        for (ShipmentScheduleRecord r : recordsStore) {
            if (r.getShipmentId().equalsIgnoreCase(id)) return r;
        }
        return null;
    }

    private String valueOrEmpty(String s) {
        return s == null ? "" : s.trim();
    }

    private boolean equalsDate(LocalDate a, LocalDate b) {
        if (a == null || b == null) return false;
        return a.isEqual(b);
    }

    private String formatDate(LocalDate d) {
        if (d == null) return "";
        return DATE_FORMATTER.format(d);
    }

    private LocalDate parseDateOrNull(String s) {
        if (s.isEmpty()) return null;
        try {
            return LocalDate.parse(s, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private String makeDelay(LocalDate planned, LocalDate actual) {
        if (planned == null || actual == null) return "";
        long days = java.time.temporal.ChronoUnit.DAYS.between(planned, actual);
        if (days <= 0) return "";
        return days + " day(s)";
    }

    private void clearForm() {
        shipmentIdField.clear();
        plannedDatePicker.setValue(null);
        statusCombo.getSelectionModel().clearSelection();
        actualDateField.clear();
        deliveryNotesField.clear();
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
