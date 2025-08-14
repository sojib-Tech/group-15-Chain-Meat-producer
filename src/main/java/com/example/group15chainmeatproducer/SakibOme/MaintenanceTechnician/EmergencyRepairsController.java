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

public class EmergencyRepairsController implements Initializable {

    @FXML
    private TableView<EmergencyAlertDetail> alertTable;
    @FXML
    private TableColumn<EmergencyAlertDetail, String> colMachineId;
    @FXML
    private TableColumn<EmergencyAlertDetail, String> colIssueReported;
    @FXML
    private TableColumn<EmergencyAlertDetail, String> colPriority;
    @FXML
    private TableColumn<EmergencyAlertDetail, String> colCurrentStatus;
    @FXML
    private Button backButton;

    private final List<EmergencyAlertDetail> store = new ArrayList<>();
    private ObservableList<EmergencyAlertDetail> tableItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableItems = FXCollections.observableList(store);
        alertTable.setItems(tableItems);
        colMachineId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colIssueReported.setCellValueFactory(new PropertyValueFactory<>("issueReported"));
        colPriority.setCellValueFactory(new PropertyValueFactory<>("priorityLevel"));
        colCurrentStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }
}
