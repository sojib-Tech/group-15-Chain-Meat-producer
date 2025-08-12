package com.example.group15chainmeatproducer.Ami.Customer;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuCustomerController {

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
    private void goToBrowseProducts(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/Customer/BrowseProduct.fxml", "Browse Products");
    }

    @FXML
    private void goToSearchProduct(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/Customer/SearchSpecificProduct.fxml", "Search Products");
    }

    @FXML
    private void goToViewProductDetails(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/Customer/ViewProductDetails.fxml", "Product Details");
    }

    @FXML
    private void goToAddToCart(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/Customer/AddToCart.fxml", "Add to Cart");
    }

    @FXML
    private void goToCheckout(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/Customer/CheckoutPlaceOrder.fxml", "Checkout & Place Order");
    }

    @FXML
    private void goToViewPastOrders(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/Customer/ViewPastOrders.fxml", "My Orders");
    }

    @FXML
    private void goToUpdatePersonalInfo(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/Customer/UpdatePersonalInfo.fxml", "Edit Profile");
    }

    @FXML
    private void goToContactSupport(ActionEvent event) {
        switchTo(event, "/com/example/group15chainmeatproducer/Ami/Customer/ContactCustomerSupport.fxml", "Contact Support");
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneManager.switchToLogin(event);
    }
}
