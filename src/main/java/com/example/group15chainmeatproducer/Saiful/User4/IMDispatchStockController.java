package com.example.group15chainmeatproducer.Saiful.User4;

import com.example.group15chainmeatproducer.SceneManager;
import com.example.group15chainmeatproducer.Saiful.User4.models.DispatchStock;
import com.example.group15chainmeatproducer.Saiful.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;

public class IMDispatchStockController {

    @FXML
    private ComboBox<String> exportItemComboBox;
    @FXML
    private DatePicker dispatchDatePicker;
    @FXML
    private RadioButton scheduledRadio;
    @FXML
    private RadioButton dispatchedRadio;
    @FXML
    private TextField loadingTeamField;
    @FXML
    private TextField quantityField;
    @FXML
    private TableView<DispatchStock> dispatchStockTable;
    @FXML
    private TableColumn<DispatchStock, String> itemIdColumn;
    @FXML
    private TableColumn<DispatchStock, String> quantityColumn;
    @FXML
    private TableColumn<DispatchStock, String> teamColumn;
    @FXML
    private TableColumn<DispatchStock, String> statusColumn;
    @FXML
    private Button backButton;
    @FXML
    private Button assignTeamButton;
    @FXML
    private Button confirmDispatchButton;

    private ToggleGroup dispatchStatusGroup;
    private ArrayList<DispatchStock> dispatchStockData;

    @FXML
    public void initialize() {
        dispatchStatusGroup = new ToggleGroup();
        scheduledRadio.setToggleGroup(dispatchStatusGroup);
        dispatchedRadio.setToggleGroup(dispatchStatusGroup);
        exportItemComboBox.getItems().clear();
        exportItemComboBox.getItems().addAll(
                "EXPORT-ITEM-001", "EXPORT-ITEM-002", "EXPORT-ITEM-003",
                "EXPORT-ITEM-004", "EXPORT-ITEM-005", "EXPORT-ITEM-006",
                "EXPORT-ITEM-007", "EXPORT-ITEM-008", "EXPORT-ITEM-009",
                "EXPORT-ITEM-010"
        );
        exportItemComboBox.setPromptText("Choose Export Item");
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dispatchStockData = (ArrayList<DispatchStock>) DataManager.loadFromFile("dispatch_stock_data");
        if (dispatchStockData == null) dispatchStockData = new ArrayList<>();
        updateTable();
    }

    private void updateTable() {
        dispatchStockTable.getItems().clear();
        dispatchStockTable.getItems().addAll(dispatchStockData);
    }

    private void saveData() {
        DataManager.saveToFile(dispatchStockData, "dispatch_stock_data");
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.switchToUser4Menu(event);
    }

    @FXML
    public void handleAssignTeam(ActionEvent event) {
        String item = exportItemComboBox.getValue();
        LocalDate date = dispatchDatePicker.getValue();
        RadioButton selectedStatus = (RadioButton) dispatchStatusGroup.getSelectedToggle();
        String status = selectedStatus != null ? selectedStatus.getText() : "";
        String team = loadingTeamField.getText();
        String qty = quantityField.getText();
        if (validateInputs(item, date, status, team, qty)) {
            DispatchStock record = new DispatchStock(item, qty, team, status, date);
            dispatchStockData.add(record);
            saveData();
            updateTable();
            clearAllFields();
            showInfo("Team assigned");
        } else {
            showError("Fill all fields");
        }
    }

    @FXML
    public void handleConfirmDispatch(ActionEvent event) {
        DispatchStock selected = dispatchStockTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("DISPATCHED");
            saveData();
            updateTable();
            showInfo("Dispatch confirmed");
        } else {
            showError("Select a record");
        }
    }

    private boolean validateInputs(String item, LocalDate date, String status, String team, String qty) {
        return item != null && !item.isEmpty() && date != null && !status.isEmpty() && team != null && !team.trim().isEmpty() && qty != null && !qty.trim().isEmpty();
    }

    private void clearAllFields() {
        exportItemComboBox.setValue(null);
        dispatchDatePicker.setValue(null);
        dispatchStatusGroup.selectToggle(null);
        loadingTeamField.clear();
        quantityField.clear();
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