package com.example.group15chainmeatproducer.Saiful.User4;

import com.example.group15chainmeatproducer.SceneManager;
import com.example.group15chainmeatproducer.Saiful.User4.models.DamagedStock;
import com.example.group15chainmeatproducer.Saiful.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class IMDamagedStockController {

    @FXML
    private ComboBox<String> stockItemComboBox;
    @FXML
    private DatePicker managementDatePicker;
    @FXML
    private RadioButton damagedRadio;
    @FXML
    private RadioButton expiredRadio;
    @FXML
    private TextField reasonField;
    @FXML
    private TextField quantityField;
    @FXML
    private TableView<DamagedStock> damagedStockTable;
    @FXML
    private TableColumn<DamagedStock, String> itemIdColumn;
    @FXML
    private TableColumn<DamagedStock, String> statusColumn;
    @FXML
    private TableColumn<DamagedStock, String> quantityColumn;
    @FXML
    private TableColumn<DamagedStock, String> reasonColumn;
    @FXML
    private Button backButton;
    @FXML
    private Button removeStockButton;
    @FXML
    private Button notifyQAButton;

    private ToggleGroup stockStatusGroup;
    private ArrayList<DamagedStock> damagedStockData;

    @FXML
    public void initialize() {
        stockStatusGroup = new ToggleGroup();
        damagedRadio.setToggleGroup(stockStatusGroup);
        expiredRadio.setToggleGroup(stockStatusGroup);
        stockItemComboBox.getItems().clear();
        stockItemComboBox.getItems().addAll(
                "STOCK-ITEM-001", "STOCK-ITEM-002", "STOCK-ITEM-003",
                "STOCK-ITEM-004", "STOCK-ITEM-005", "STOCK-ITEM-006",
                "STOCK-ITEM-007", "STOCK-ITEM-008", "STOCK-ITEM-009",
                "STOCK-ITEM-010"
        );
        stockItemComboBox.setPromptText("Choose Stock Item");
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        damagedStockData = (ArrayList<DamagedStock>) DataManager.loadFromFile("damaged_stock_data");
        if (damagedStockData == null) damagedStockData = new ArrayList<>();
        updateTable();
    }

    private void updateTable() {
        damagedStockTable.getItems().clear();
        damagedStockTable.getItems().addAll(damagedStockData);
    }

    private void saveData() {
        DataManager.saveToFile(damagedStockData, "damaged_stock_data");
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.switchToUser4Menu(event);
    }

    @FXML
    public void handleRemoveStock(ActionEvent event) {
        String item = stockItemComboBox.getValue();
        LocalDate date = managementDatePicker.getValue();
        RadioButton selectedStatus = (RadioButton) stockStatusGroup.getSelectedToggle();
        String status = selectedStatus != null ? selectedStatus.getText() : "";
        String reason = reasonField.getText();
        String qty = quantityField.getText();
        if (validateInputs(item, date, status, reason, qty)) {
            DamagedStock record = new DamagedStock(item, status, date, reason, qty);
            damagedStockData.add(record);
            saveData();
            updateTable();
            clearAllFields();
            showInfo("Stock removed");
        } else {
            showError("Fill all fields");
        }
    }

    @FXML
    public void handleNotifyQA(ActionEvent event) {
        DamagedStock selected = damagedStockTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showInfo("QA notified");
        } else {
            showError("Select a record");
        }
    }

    private boolean validateInputs(String item, LocalDate date, String status, String reason, String qty) {
        return item != null && !item.isEmpty() && date != null && !status.isEmpty() && reason != null && !reason.trim().isEmpty() && qty != null && !qty.trim().isEmpty();
    }

    private void clearAllFields() {
        stockItemComboBox.setValue(null);
        managementDatePicker.setValue(null);
        stockStatusGroup.selectToggle(null);
        reasonField.clear();
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