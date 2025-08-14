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
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TransportationCostController implements Initializable {

    @FXML
    private TableView<TransportationCostRecord> costTable;
    @FXML
    private TableColumn<TransportationCostRecord, String> colMonth;
    @FXML
    private TableColumn<TransportationCostRecord, String> colRoute;
    @FXML
    private TableColumn<TransportationCostRecord, String> colMode;
    @FXML
    private TableColumn<TransportationCostRecord, String> colTotalCost;
    @FXML
    private TableColumn<TransportationCostRecord, String> colCostPerShipment;
    @FXML
    private TableColumn<TransportationCostRecord, String> colVariance;

    @FXML
    private TextField routeField;
    @FXML
    private ComboBox<String> modeCombo;
    @FXML
    private TextField totalCostField;
    @FXML
    private DatePicker costMonthPicker;
    @FXML
    private Button saveButton;
    @FXML
    private TextArea suggestionsArea;
    @FXML
    private Button runAnalysisButton;
    @FXML
    private Button validateButton;
    @FXML
    private Button backButton;

    private final List<TransportationCostRecord> costStore = new ArrayList<>();
    private ObservableList<TransportationCostRecord> tableItems;

    private static final DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> modes = new ArrayList<>();
        modes.add("Truck");
        modes.add("Air");
        modes.add("Ship");
        modes.add("Rail");
        modeCombo.setItems(FXCollections.observableList(modes));

        tableItems = FXCollections.observableList(costStore);
        costTable.setItems(tableItems);

        colRoute.setCellValueFactory(new PropertyValueFactory<>("route"));
        colMode.setCellValueFactory(new PropertyValueFactory<>("transportMode"));
        colMonth.setCellValueFactory(cell -> new SimpleStringProperty(formatMonth(cell.getValue().getCostMonth())));
        colTotalCost.setCellValueFactory(cell -> new SimpleStringProperty(formatMoney(cell.getValue().getTotalCost())));
        colCostPerShipment.setCellValueFactory(cell -> new SimpleStringProperty(formatMoney(cell.getValue().getCostPerShipment())));
        colVariance.setCellValueFactory(cell -> new SimpleStringProperty(formatPercent(cell.getValue().getVariancePercent())));
    }

    @FXML
    private void onSaveCostRecord(ActionEvent event) {
        String route = trimOrEmpty(routeField.getText());
        String mode = modeCombo.getValue();
        String costText = trimOrEmpty(totalCostField.getText());
        LocalDate date = costMonthPicker.getValue();
        List<String> issues = new ArrayList<>();
        if (route.isEmpty()) issues.add("Route is required");
        if (mode == null || mode.isEmpty()) issues.add("Transport Mode is required");
        Double totalCost = parsePositiveDouble(costText);
        if (totalCost == null) issues.add("Total Cost must be a positive number");
        if (date == null) issues.add("Cost Month is required");
        if (!issues.isEmpty()) {
            showAlert("Save", String.join("\n", issues), Alert.AlertType.WARNING);
            return;
        }
        LocalDate normalized = normalizeToFirstOfMonth(date);
        TransportationCostRecord existing = findByKey(route, mode, normalized);
        if (existing == null) {
            TransportationCostRecord r = new TransportationCostRecord();
            r.setRoute(route);
            r.setTransportMode(mode);
            r.setCostMonth(normalized);
            r.setTotalCost(totalCost);
            r.setCostPerShipment(calculateCostPerShipment(mode, totalCost));
            r.setVariancePercent(calculateVariancePercent(route, mode, normalized, totalCost));
            tableItems.add(r);
        } else {
            existing.setTotalCost(totalCost);
            existing.setCostPerShipment(calculateCostPerShipment(mode, totalCost));
            existing.setVariancePercent(calculateVariancePercent(route, mode, normalized, totalCost));
            costTable.refresh();
        }
        clearForm();
        showAlert("Save", "Cost record saved.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onRunCostAnalysis(ActionEvent event) {
        if (costStore.isEmpty()) {
            suggestionsArea.setText("No data available for analysis.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Map<String, Double> routeTotals = new HashMap<>();
        for (TransportationCostRecord r : costStore) {
            String key = r.getRoute() + " | " + r.getTransportMode();
            routeTotals.put(key, routeTotals.getOrDefault(key, 0.0) + r.getTotalCost());
        }
        List<Map.Entry<String, Double>> sorted = routeTotals.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
        Map<String, Double> avgCostPerShipment = new HashMap<>();
        for (TransportationCostRecord r : costStore) {
            String key = r.getRoute() + " | " + r.getTransportMode();
            avgCostPerShipment.put(key, avgCostPerShipment.getOrDefault(key, 0.0) + r.getCostPerShipment());
        }
        Map<String, Long> counts = costStore.stream().collect(Collectors.groupingBy(r -> r.getRoute() + " | " + r.getTransportMode(), Collectors.counting()));
        for (String k : avgCostPerShipment.keySet()) {
            avgCostPerShipment.put(k, avgCostPerShipment.get(k) / counts.get(k));
        }
        sb.append("Top cost routes/modes:\n");
        for (int i = 0; i < Math.min(5, sorted.size()); i++) {
            Map.Entry<String, Double> e = sorted.get(i);
            sb.append(" - ").append(e.getKey()).append(": ").append(formatMoney(e.getValue())).append(" total\n");
        }
        List<String> highVariance = costStore.stream()
                .filter(r -> r.getVariancePercent() > 10.0)
                .map(r -> r.getRoute() + " | " + r.getTransportMode() + " in " + formatMonth(r.getCostMonth()) + " (" + formatPercent(r.getVariancePercent()) + ")")
                .distinct()
                .collect(Collectors.toList());
        if (!highVariance.isEmpty()) {
            sb.append("\nHigh month-over-month increases (>10%):\n");
            for (String s : highVariance) sb.append(" - ").append(s).append("\n");
        }
        List<String> modeTips = new ArrayList<>();
        Map<String, Double> modeAvg = new HashMap<>();
        Map<String, Long> modeCnt = new HashMap<>();
        for (TransportationCostRecord r : costStore) {
            modeAvg.put(r.getTransportMode(), modeAvg.getOrDefault(r.getTransportMode(), 0.0) + r.getCostPerShipment());
            modeCnt.put(r.getTransportMode(), modeCnt.getOrDefault(r.getTransportMode(), 0L) + 1);
        }
        for (String m : modeAvg.keySet()) {
            modeAvg.put(m, modeAvg.get(m) / modeCnt.get(m));
        }
        if (modeAvg.getOrDefault("Air", 0.0) > modeAvg.getOrDefault("Truck", 0.0) * 2) {
            modeTips.add("Air looks much costlier per shipment than Truck. Consider mode shift where possible.");
        }
        if (modeAvg.getOrDefault("Ship", 0.0) < modeAvg.getOrDefault("Rail", 0.0)) {
            modeTips.add("Ship has lower cost per shipment than Rail in your data. Consider consolidations to Ship.");
        }
        if (!modeTips.isEmpty()) {
            sb.append("\nMode suggestions:\n");
            for (String s : modeTips) sb.append(" - ").append(s).append("\n");
        }
        suggestionsArea.setText(sb.toString());
    }

    @FXML
    private void onValidateCostData(ActionEvent event) {
        List<String> issues = new ArrayList<>();
        for (TransportationCostRecord r : costStore) {
            if (r.getRoute() == null || r.getRoute().trim().isEmpty()) issues.add("A record has empty route");
            if (r.getTransportMode() == null || r.getTransportMode().trim().isEmpty())
                issues.add("A record has empty mode");
            if (r.getTotalCost() <= 0) issues.add("A record has non-positive total cost");
            if (r.getCostMonth() == null) issues.add("A record has no month set");
        }
        if (issues.isEmpty()) {
            showAlert("Validate", "All records look valid.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Validate", String.join("\n", issues), Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }

    private TransportationCostRecord findByKey(String route, String mode, LocalDate month) {
        for (TransportationCostRecord r : costStore) {
            if (r.getRoute().equalsIgnoreCase(route) && r.getTransportMode().equalsIgnoreCase(mode) && r.getCostMonth().isEqual(month))
                return r;
        }
        return null;
    }

    private Double parsePositiveDouble(String s) {
        try {
            double v = Double.parseDouble(s);
            if (v > 0) return v;
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDate normalizeToFirstOfMonth(LocalDate d) {
        YearMonth ym = YearMonth.of(d.getYear(), d.getMonth());
        return ym.atDay(1);
    }

    private double calculateCostPerShipment(String mode, double totalCost) {
        int shipments = 1;
        if ("Truck".equalsIgnoreCase(mode)) shipments = 20;
        else if ("Rail".equalsIgnoreCase(mode)) shipments = 15;
        else if ("Ship".equalsIgnoreCase(mode)) shipments = 8;
        else if ("Air".equalsIgnoreCase(mode)) shipments = 5;
        return totalCost / shipments;
    }

    private double calculateVariancePercent(String route, String mode, LocalDate month, double totalCost) {
        LocalDate prev = month.minusMonths(1);
        TransportationCostRecord last = findByKey(route, mode, prev);
        if (last == null || last.getTotalCost() <= 0) return 0.0;
        double change = totalCost - last.getTotalCost();
        return (change / last.getTotalCost()) * 100.0;
    }

    private String trimOrEmpty(String s) {
        return s == null ? "" : s.trim();
    }

    private String formatMonth(LocalDate month) {
        if (month == null) return "";
        return MONTH_FORMAT.format(month);
    }

    private String formatMoney(double v) {
        return String.format("%.2f", v);
    }

    private String formatPercent(double v) {
        return String.format("%.1f%%", v);
    }

    private void clearForm() {
        routeField.clear();
        modeCombo.getSelectionModel().clearSelection();
        totalCostField.clear();
        costMonthPicker.setValue(null);
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
