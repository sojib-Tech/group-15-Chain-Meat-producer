package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PostRepairSafetyCheckController implements Initializable {

    @FXML
    private TableView<PostRepairSafetyResult> resultsTable;
    @FXML
    private TableColumn<PostRepairSafetyResult, String> colMachineId;
    @FXML
    private TableColumn<PostRepairSafetyResult, String> colInspectionDate;
    @FXML
    private TableColumn<PostRepairSafetyResult, String> colInspector;
    @FXML
    private TableColumn<PostRepairSafetyResult, String> colSafetyStatus;
    @FXML
    private TableColumn<PostRepairSafetyResult, String> colNotes;
    @FXML
    private Button backButton;

    private final List<PostRepairSafetyResult> store = new ArrayList<>();
    private ObservableList<PostRepairSafetyResult> tableItems;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableItems = FXCollections.observableList(store);
        resultsTable.setItems(tableItems);
        colMachineId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMachineId()));
        colInspector.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getInspector()));
        colSafetyStatus.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSafetyStatus()));
        colNotes.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNotes()));
        colInspectionDate.setCellValueFactory(c -> new SimpleStringProperty(formatDate(c.getValue().getInspectionDate())));
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToMaintenanceMenu(event);
    }

    private String formatDate(LocalDate d) {
        if (d == null) return "";
        return DATE_FORMAT.format(d);
    }
}
