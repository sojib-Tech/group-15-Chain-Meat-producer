package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UrgentRepairCoordinationController implements Initializable {

    @FXML
    private TableView<UrgentRepairRecord> statusTable;
    @FXML
    private TableColumn<UrgentRepairRecord, String> colMachineId;
    @FXML
    private TableColumn<UrgentRepairRecord, String> colMachineName;
    @FXML
    private TableColumn<UrgentRepairRecord, String> colCurrentIssue;
    @FXML
    private TableColumn<UrgentRepairRecord, String> colOperatorNotes;
    @FXML
    private TableColumn<UrgentRepairRecord, String> colUrgency;
    @FXML
    private Button backButton;

    private final List<UrgentRepairRecord> store = new ArrayList<>();
    private ObservableList<UrgentRepairRecord> tableItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableItems = FXCollections.observableList(store);
        statusTable.setItems(tableItems);
        colMachineId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colMachineName.setCellValueFactory(new PropertyValueFactory<>("machineName"));
        colCurrentIssue.setCellValueFactory(new PropertyValueFactory<>("currentIssue"));
        colOperatorNotes.setCellValueFactory(new PropertyValueFactory<>("operatorNotes"));
        colUrgency.setCellValueFactory(new PropertyValueFactory<>("urgencyLevel"));
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }
}
