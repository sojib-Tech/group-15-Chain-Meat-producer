package com.example.group15chainmeatproducer.Ami.Customer;

import com.example.group15chainmeatproducer.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UpdatePersonalInfoController {

    @FXML
    private TextField fullNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private ComboBox<String> countryComboBox;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private DatePicker dobDatePicker;
    @FXML
    private TableView activityTable;
    @FXML
    private TableColumn activityDateColumn;
    @FXML
    private TableColumn activityDescColumn;
    @FXML
    private Button saveChangesButton;
    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchToCustomerMenu(event);
    }
}
