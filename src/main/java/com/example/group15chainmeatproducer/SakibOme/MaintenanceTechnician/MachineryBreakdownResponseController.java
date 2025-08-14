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

public class MachineryBreakdownResponseController implements Initializable {

    @FXML
    private TableView<BreakdownDetail> breakdownTable;
    @FXML
    private TableColumn<BreakdownDetail, String> colMachineId;
    @FXML
    private TableColumn<BreakdownDetail, String> colAlertType;
    @FXML
    private TableColumn<BreakdownDetail, String> colTimeReported;
    @FXML
    private TableColumn<BreakdownDetail, String> colCurrentStatus;
    @FXML
    private Button backButton;

    private final List<BreakdownDetail> store = new ArrayList<>();
    private ObservableList<BreakdownDetail> tableItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableItems = FXCollections.observableList(store);
        breakdownTable.setItems(tableItems);
        colMachineId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colAlertType.setCellValueFactory(new PropertyValueFactory<>("alertType"));
        colTimeReported.setCellValueFactory(new PropertyValueFactory<>("timeReported"));
        colCurrentStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        SceneManager.switchToMaintenanceMenu(event);
    }
}
