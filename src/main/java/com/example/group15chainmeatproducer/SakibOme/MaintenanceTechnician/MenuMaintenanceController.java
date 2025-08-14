package com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuMaintenanceController {

    @FXML
    private void openGoal1(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/MaintenanceTechnician/ScheduledMaintenance.fxml", "Scheduled Maintenance Management");
    }

    @FXML
    private void openGoal2(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/MaintenanceTechnician/MachinerySafetyInspection.fxml", "Machinery Safety Inspection");
    }

    @FXML
    private void openGoal3(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/MaintenanceTechnician/MaintenanceActivityDocumentation.fxml", "Maintenance Activity Documentation");
    }

    @FXML
    private void openGoal4(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/MaintenanceTechnician/UrgentRepairCoordination.fxml", "Urgent Repair Coordination");
    }

    @FXML
    private void openGoal5(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/MaintenanceTechnician/MachineryBreakdownResponse.fxml", "Machinery Breakdown Response");
    }

    @FXML
    private void openGoal6(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/MaintenanceTechnician/ReplacementPartsOrdering.fxml", "Replacement Parts Ordering");
    }

    @FXML
    private void openGoal7(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/MaintenanceTechnician/PostRepairSafetyCheck.fxml", "Post-Repair Safety Check");
    }

    @FXML
    private void openGoal8(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/MaintenanceTechnician/EmergencyRepairs.fxml", "Emergency Repairs");
    }

    @FXML
    private void backToLogin(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }

    private void load(ActionEvent event, String resource, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
