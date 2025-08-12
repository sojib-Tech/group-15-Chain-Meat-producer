package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuCustomerServiceController {

    private void switchTo(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goGoal1(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/CustomerServiceRepresentative/AdminLogin.fxml", "Admin Login");
    }

    @FXML
    private void goGoal2(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/CustomerServiceRepresentative/ViewAllCustomerOrders.fxml", "All Customer Orders");
    }

    @FXML
    private void goGoal3(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/CustomerServiceRepresentative/SearchForSpecificOrder.fxml", "Search Orders");
    }

    @FXML
    private void goGoal4(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/CustomerServiceRepresentative/UpdateOrderStatus.fxml", "Update Order Status");
    }

    @FXML
    private void goGoal5(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/CustomerServiceRepresentative/ViewCustomerProfile.fxml", "Customer Profile");
    }

    @FXML
    private void goGoal6(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/CustomerServiceRepresentative/RespondToCustomerMessages.fxml", "Support Messages");
    }

    @FXML
    private void goGoal7(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/CustomerServiceRepresentative/CheckOrderIssueLog.fxml", "Order Issue Reports");
    }

    @FXML
    private void goGoal8(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/CustomerServiceRepresentative/LogOuttoAdminPanel.fxml", "Admin Panel");
    }

    @FXML
    private void goBackToLogin(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }
}
