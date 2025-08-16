package com.example.group15chainmeatproducer.Saiful.User4;

import com.example.group15chainmeatproducer.SceneManager;
import com.example.group15chainmeatproducer.Saiful.User4.models.MonthlyReport;
import com.example.group15chainmeatproducer.Saiful.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.ArrayList;

public class IMMonthlyReportsController {

    @FXML
    private ComboBox<String> reportTypeComboBox;
    @FXML
    private DatePicker reportDatePicker;
    @FXML
    private RadioButton excelRadio;
    @FXML
    private RadioButton pdfRadio;
    @FXML
    private TextField categoryFilterField;
    @FXML
    private TextField dateFilterField;
    @FXML
    private TableView<MonthlyReport> monthlyReportTable;
    @FXML
    private TableColumn<MonthlyReport, String> reportTypeColumn;
    @FXML
    private TableColumn<MonthlyReport, String> categoryColumn;
    @FXML
    private TableColumn<MonthlyReport, LocalDate> dateColumn;
    @FXML
    private TableColumn<MonthlyReport, String> statusColumn;
    @FXML
    private Button backButton;
    @FXML
    private Button generateReportButton;
    @FXML
    private Button shareReportButton;

    private ToggleGroup exportFormatGroup;
    private ArrayList<MonthlyReport> monthlyReportData;

    @FXML
    public void initialize() {
        exportFormatGroup = new ToggleGroup();
        excelRadio.setToggleGroup(exportFormatGroup);
        pdfRadio.setToggleGroup(exportFormatGroup);
        reportTypeComboBox.getItems().clear();
        reportTypeComboBox.getItems().addAll(
                "Stock Movement Summary",
                "Damaged Stock Summary",
                "Dispatch Summary",
                "Packaging Usage Summary",
                "Inventory Balance",
                "Procurement Needs",
                "QA Exceptions",
                "Production Output",
                "Cold Storage Utilization",
                "Returns Summary"
        );
        reportTypeComboBox.setPromptText("Choose Report Type");
        reportTypeColumn.setCellValueFactory(new PropertyValueFactory<>("reportType"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("reportDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        monthlyReportData = (ArrayList<MonthlyReport>) DataManager.loadFromFile("monthly_reports_data");
        if (monthlyReportData == null) monthlyReportData = new ArrayList<>();
        updateTable();
    }

    private void updateTable() {
        monthlyReportTable.getItems().clear();
        monthlyReportTable.getItems().addAll(monthlyReportData);
    }

    private void saveData() {
        DataManager.saveToFile(monthlyReportData, "monthly_reports_data");
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.switchToUser4Menu(event);
    }

    @FXML
    public void handleGenerateReport(ActionEvent event) {
        String reportType = reportTypeComboBox.getValue();
        LocalDate reportDate = reportDatePicker.getValue();
        RadioButton selectedFormat = (RadioButton) exportFormatGroup.getSelectedToggle();
        String format = selectedFormat != null ? selectedFormat.getText() : "";
        String category = categoryFilterField.getText();
        String dateFilter = dateFilterField.getText();
        if (validateInputs(reportType, reportDate, format, category, dateFilter)) {
            MonthlyReport report = new MonthlyReport(reportType, category, reportDate, "Generated " + format, dateFilter);
            monthlyReportData.add(report);
            saveData();
            updateTable();
            clearAllFields();
            showInfo("Report generated successfully");
        } else {
            showError("Fill all fields");
        }
    }

    @FXML
    public void handleShareReport(ActionEvent event) {
        MonthlyReport selected = monthlyReportTable.getSelectionModel().getSelectedItem();
        RadioButton selectedFormat = (RadioButton) exportFormatGroup.getSelectedToggle();
        String format = selectedFormat != null ? selectedFormat.getText() : "";
        if (selected != null && !format.isEmpty()) {
            selected.setStatus("Shared via " + format);
            saveData();
            updateTable();
            showInfo("Report shared successfully");
        } else {
            showError("Select a report and export format");
        }
    }

    private boolean validateInputs(String type, LocalDate date, String format, String category, String df) {
        return type != null && !type.isEmpty() && date != null && !format.isEmpty() && category != null && !category.trim().isEmpty() && df != null && !df.trim().isEmpty();
    }

    private void clearAllFields() {
        reportTypeComboBox.setValue(null);
        reportDatePicker.setValue(null);
        exportFormatGroup.selectToggle(null);
        categoryFilterField.clear();
        dateFilterField.clear();
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