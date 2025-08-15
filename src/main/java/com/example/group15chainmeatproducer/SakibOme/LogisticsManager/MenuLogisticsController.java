package com.example.group15chainmeatproducer.SakibOme.LogisticsManager;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuLogisticsController {

    @FXML
    private void openGoal1(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/LogisticsManager/1.ShipmentTransportCordination.fxml", "Shipment Transportation Coordination");
    }

    @FXML
    private void openGoal2(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/LogisticsManager/2.ShipmentSchdule.fxml", "Shipment Schedule & Delivery Monitoring");
    }

    @FXML
    private void openGoal3(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/LogisticsManager/3.TransportationCostTrack.fxml", "Transportation Cost Tracking");
    }

    @FXML
    private void openGoal4(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/LogisticsManager/4.ThirdpartyLogistics.fxml", "Third-Party Logistics Provider Management");
    }

    @FXML
    private void openGoal5(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/LogisticsManager/5.EnsureTimeDelivary.fxml", "Raw Material Delivery Tracking");
    }

    @FXML
    private void openGoal6(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/LogisticsManager/6.AddressingShipmentDelays.fxml", "Address Shipment Delays");
    }

    @FXML
    private void openGoal7(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/LogisticsManager/7.ImportExportDocument.fxml", "Import/Export Documentation");
    }

    @FXML
    private void openGoal8(ActionEvent event) {
        load(event, "/com/example/group15chainmeatproducer/Sakib-Ome/LogisticsManager/8.DelivaryStatusNotification.fxml", "Delivery Status Notification");
    }

    @FXML
    private void backToLogin(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }

    private void load(ActionEvent event, String resource, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(resource));
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
