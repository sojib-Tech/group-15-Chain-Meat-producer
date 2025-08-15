package com.example.group15chainmeatproducer.Saiful.User4;

import com.example.group15chainmeatproducer.SceneManager;
import com.example.group15chainmeatproducer.Saiful.User4.models.PackagingMaterial;
import com.example.group15chainmeatproducer.Saiful.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class IMPackagingMaterialController {

    @FXML
    private ComboBox<String> packingBatchComboBox;
    @FXML
    private DatePicker usageDatePicker;
    @FXML
    private RadioButton standardUsageRadio;
    @FXML
    private RadioButton discrepancyFoundRadio;
    @FXML
    private TextField materialIssuedField;
    @FXML
    private TextField standardUsageField;
    @FXML
    private TableView<PackagingMaterial> packagingTable;
    @FXML
    private TableColumn<PackagingMaterial, String> batchIdColumn;
    @FXML
    private TableColumn<PackagingMaterial, String> issuedColumn;
    @FXML
    private TableColumn<PackagingMaterial, String> standardColumn;
    @FXML
    private TableColumn<PackagingMaterial, String> statusColumn;
    @FXML
    private Button backButton;
    @FXML
    private Button recordDiscrepancyButton;
    @FXML
    private Button notifyProcurementButton;

    private ToggleGroup materialStatusGroup;
    private ArrayList<PackagingMaterial> packagingData;

    @FXML
    public void initialize() {
        materialStatusGroup = new ToggleGroup();
        standardUsageRadio.setToggleGroup(materialStatusGroup);
        discrepancyFoundRadio.setToggleGroup(materialStatusGroup);
        packingBatchComboBox.getItems().clear();
        packingBatchComboBox.getItems().addAll(
                "PACK-BATCH-001", "PACK-BATCH-002", "PACK-BATCH-003",
                "PACK-BATCH-004", "PACK-BATCH-005", "PACK-BATCH-006",
                "PACK-BATCH-007", "PACK-BATCH-008", "PACK-BATCH-009",
                "PACK-BATCH-010"
        );
        packingBatchComboBox.setPromptText("Choose Packing Batch");
        batchIdColumn.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        issuedColumn.setCellValueFactory(new PropertyValueFactory<>("issued"));
        standardColumn.setCellValueFactory(new PropertyValueFactory<>("standard"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        packagingData = (ArrayList<PackagingMaterial>) DataManager.loadFromFile("packaging_material_data");
        if (packagingData == null) packagingData = new ArrayList<>();
        updateTable();
    }

    private void updateTable() {
        packagingTable.getItems().clear();
        packagingTable.getItems().addAll(packagingData);
    }

    private void saveData() {
        DataManager.saveToFile(packagingData, "packaging_material_data");
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.switchToUser4Menu(event);
    }

    @FXML
    public void handleRecordDiscrepancy(ActionEvent event) {
        String batch = packingBatchComboBox.getValue();
        LocalDate usageDate = usageDatePicker.getValue();
        RadioButton selectedStatus = (RadioButton) materialStatusGroup.getSelectedToggle();
        String status = selectedStatus != null ? selectedStatus.getText() : "";
        String issued = materialIssuedField.getText();
        String standard = standardUsageField.getText();
        if (validateInputs(batch, usageDate, status, issued, standard)) {
            PackagingMaterial pm = new PackagingMaterial(batch, issued, standard, status, usageDate);
            packagingData.add(pm);
            saveData();
            updateTable();
            clearAllFields();
            showInfo("Record saved");
        } else {
            showError("Fill all fields");
        }
    }

    @FXML
    public void handleNotifyProcurement(ActionEvent event) {
        PackagingMaterial selected = packagingTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("PROCUREMENT NOTIFIED");
            saveData();
            updateTable();
            showInfo("Procurement notified");
        } else {
            showError("Select a record");
        }
    }

    private boolean validateInputs(String batch, LocalDate date, String status, String issued, String standard) {
        return batch != null && !batch.isEmpty() && date != null && !status.isEmpty() && issued != null && !issued.trim().isEmpty() && standard != null && !standard.trim().isEmpty();
    }

    private void clearAllFields() {
        packingBatchComboBox.setValue(null);
        usageDatePicker.setValue(null);
        materialStatusGroup.selectToggle(null);
        materialIssuedField.clear();
        standardUsageField.clear();
    }

    private void showInfo(String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success");
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }

    private void showError(String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }
}