package com.example.group15chainmeatproducer.Sojib.User1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class checkIn implements Initializable {

    @FXML private TextField operatorNameField;
    @FXML private TextField animalIdField;
    @FXML private DatePicker dateField;
    @FXML
    private TextField placeField;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;
    @FXML private TableView<HygieneData> table_Upload_Hygiene_Checklist;
    @FXML private TableColumn<HygieneData, String> t_operator_name;
    @FXML private TableColumn<HygieneData, String> t_animal_id;
    @FXML private TableColumn<HygieneData, String> t_date;
    @FXML private TableColumn<HygieneData, String> t_place;

    private ArrayList<HygieneData> hygieneDataList;
    private final String DATA_FILE = "checkIn.bin";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadData();
    }

    private void setupTable() {
        t_operator_name.setCellValueFactory(new PropertyValueFactory<>("operatorName"));
        t_animal_id.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        t_date.setCellValueFactory(new PropertyValueFactory<>("dateOfImport"));
        t_place.setCellValueFactory(new PropertyValueFactory<>("place"));
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            hygieneDataList = (ArrayList<HygieneData>) ois.readObject();
        } catch (Exception e) {
            hygieneDataList = new ArrayList<>();
        }
        refreshTable();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(hygieneDataList);
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void refreshTable() {
        ObservableList<HygieneData> observableList = FXCollections.observableArrayList(hygieneDataList);
        table_Upload_Hygiene_Checklist.setItems(observableList);
    }

    @FXML
    private void save() {
        if (validateInput()) {
            String operatorName = operatorNameField.getText();
            String animalId = animalIdField.getText();
            String dateOfImport = dateField.getValue() != null ? dateField.getValue().toString() : "";
            String place = placeField.getText();

            HygieneData hygiene = new HygieneData(operatorName, animalId, dateOfImport, place);
            hygieneDataList.add(hygiene);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Check-in data saved successfully!");
        }
    }

    private boolean validateInput() {
        if (operatorNameField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Operator Name");
            return false;
        }
        if (animalIdField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Animal ID");
            return false;
        }
        if (dateField.getValue() == null) {
            showAlert("Error", "Please select Date");
            return false;
        }
        if (placeField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Place");
            return false;
        }
        return true;
    }

    private void clearFields() {
        operatorNameField.clear();
        animalIdField.clear();
        dateField.setValue(null);
        placeField.clear();
    }

    @FXML
    private void backgoal1() {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FF_MenuPage.fxml"));
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

    public static class HygieneData implements Serializable {
        private String operatorName;
        private String animalId;
        private String dateOfImport;
        private String place;

        public HygieneData(String operatorName, String animalId, String dateOfImport, String place) {
            this.operatorName = operatorName;
            this.animalId = animalId;
            this.dateOfImport = dateOfImport;
            this.place = place;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public String getAnimalId() {
            return animalId;
        }

        public String getDateOfImport() {
            return dateOfImport;
        }

        public String getPlace() {
            return place;
        }
    }
}
