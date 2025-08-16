package com.example.group15chainmeatproducer.Sojib.User2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DestinationClientController implements Initializable {

    @FXML
    private ComboBox<String> destinationCountryComboBox;
    @FXML
    private TextField clientCompanyField;
    @FXML
    private DatePicker entryDatePicker;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;
    @FXML
    private TableView<DestinationClient> destinationClientTable;
    @FXML
    private TableColumn<DestinationClient, String> colDestinationCountry;
    @FXML
    private TableColumn<DestinationClient, String> colClientCompany;
    @FXML
    private TableColumn<DestinationClient, String> colEntryDate;

    private ArrayList<DestinationClient> destinationClientList;
    private final String DATA_FILE = "destinationClient.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        setupComboBox();
        loadData();
    }

    private void setupTable() {
        colDestinationCountry.setCellValueFactory(new PropertyValueFactory<>("destinationCountry"));
        colClientCompany.setCellValueFactory(new PropertyValueFactory<>("clientCompanyName"));
        colEntryDate.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
    }

    private void setupComboBox() {
        destinationCountryComboBox.getItems().addAll(
                "USA", "Canada", "United Kingdom", "Germany", "France",
                "Japan", "Australia", "Singapore", "South Korea", "Netherlands"
        );
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            destinationClientList = (ArrayList<DestinationClient>) ois.readObject();
        } catch (Exception e) {
            destinationClientList = new ArrayList<>();
        }
        refreshTable();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(destinationClientList);
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void refreshTable() {
        ObservableList<DestinationClient> observableList = FXCollections.observableArrayList(destinationClientList);
        destinationClientTable.setItems(observableList);
    }

    @FXML
    private void handleSaveInformation() {
        if (validateInput()) {
            String destinationCountry = destinationCountryComboBox.getValue();
            String clientCompanyName = clientCompanyField.getText();
            String entryDate = entryDatePicker.getValue() != null ? entryDatePicker.getValue().toString() : "";

            DestinationClient destinationClient = new DestinationClient(destinationCountry, clientCompanyName, entryDate);
            destinationClientList.add(destinationClient);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Destination and client information saved successfully!");
        }
    }

    private boolean validateInput() {
        if (destinationCountryComboBox.getValue() == null) {
            showAlert("Error", "Please select Destination Country");
            return false;
        }
        if (clientCompanyField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Client Company Name");
            return false;
        }
        if (entryDatePicker.getValue() == null) {
            showAlert("Error", "Please select Entry Date");
            return false;
        }
        return true;
    }

    private void clearFields() {
        destinationCountryComboBox.setValue(null);
        clientCompanyField.clear();
        entryDatePicker.setValue(null);
    }

    @FXML
    private void handleBack() {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EC_MenuPage.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            showAlert("Error", "Failed to go back: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}