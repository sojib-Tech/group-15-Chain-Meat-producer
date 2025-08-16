package com.example.group15chainmeatproducer.Sojib.User1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class InputNumberOfCutMeatPieces {

    @FXML private TextField oprator_name;
    @FXML private ComboBox<String> animalIdComboBox;
    @FXML private TextField numberOfPieces;
    @FXML private DatePicker dateOfCutting;

    @FXML
    private TableView<CutMeatPieces> table_input_number_of_cut_meat_pieces;
    @FXML
    private TableColumn<CutMeatPieces, String> t_opratorName;
    @FXML
    private TableColumn<CutMeatPieces, String> t_batch_Id;
    @FXML
    private TableColumn<CutMeatPieces, String> t_numberOfPeices;
    @FXML
    private TableColumn<CutMeatPieces, String> t_date;

    @FXML private Button btnBack;
    @FXML private Button btnSubmit;

    private ArrayList<CutMeatPieces> cutMeatPiecesList;
    private final String DATA_FILE = "cutting_pieces.bin";
    private final File checkInFile = new File("checkIn.bin");

    @FXML
    public void initialize() {
        setupTable();
        setupComboBox();
        loadData();
    }

    private void setupTable() {
        t_opratorName.setCellValueFactory(new PropertyValueFactory<>("operatorName"));
        t_batch_Id.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        t_numberOfPeices.setCellValueFactory(new PropertyValueFactory<>("numberOfPieces"));
        t_date.setCellValueFactory(new PropertyValueFactory<>("cuttingDate"));
    }

    private void setupComboBox() {
        if (!checkInFile.exists()) {
            animalIdComboBox.getItems().addAll("A001", "A002", "A003", "A004", "A005");
        } else {
            Set<String> uniqueIds = new HashSet<>();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(checkInFile))) {
                while (true) {
                    Object obj = ois.readObject();
                    if (obj instanceof checkIn.HygieneData) {
                        uniqueIds.add(((checkIn.HygieneData) obj).getAnimalId());
                    }
                }
            } catch (EOFException ignored) {
            } catch (Exception e) {
                e.printStackTrace();
            }
            animalIdComboBox.getItems().addAll(uniqueIds);
        }
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            cutMeatPiecesList = (ArrayList<CutMeatPieces>) ois.readObject();
        } catch (Exception e) {
            cutMeatPiecesList = new ArrayList<>();
        }
        refreshTable();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(cutMeatPiecesList);
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void refreshTable() {
        ObservableList<CutMeatPieces> observableList = FXCollections.observableArrayList(cutMeatPiecesList);
        table_input_number_of_cut_meat_pieces.setItems(observableList);
    }

    @FXML
    public void submitgoal4() {
        if (validateInput()) {
            String operatorName = oprator_name.getText();
            String animalId = animalIdComboBox.getValue();
            String pieces = numberOfPieces.getText();
            String cuttingDate = dateOfCutting.getValue() != null ? dateOfCutting.getValue().toString() : "";

            CutMeatPieces cutMeat = new CutMeatPieces(operatorName, animalId, pieces, cuttingDate);
            cutMeatPiecesList.add(cutMeat);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Cut meat pieces data saved successfully!");
        }
    }

    private boolean validateInput() {
        if (oprator_name.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Operator Name");
            return false;
        }
        if (animalIdComboBox.getValue() == null) {
            showAlert("Error", "Please select Animal ID");
            return false;
        }
        if (numberOfPieces.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Number of Pieces");
            return false;
        }
        if (dateOfCutting.getValue() == null) {
            showAlert("Error", "Please select Cutting Date");
            return false;
        }
        try {
            Integer.parseInt(numberOfPieces.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Error", "Number of Pieces must be a valid number");
            return false;
        }
        return true;
    }

    private void clearFields() {
        oprator_name.clear();
        animalIdComboBox.setValue(null);
        numberOfPieces.clear();
        dateOfCutting.setValue(null);
    }

    @FXML
    public void backto3() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Goal3MachineCalibration.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Unable to go back: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class CutMeatPieces {
        private String operatorName;
        private String animalId;
        private String numberOfPieces;
        private String cuttingDate;

        public CutMeatPieces(String operatorName, String animalId, String numberOfPieces, String cuttingDate) {
            this.operatorName = operatorName;
            this.animalId = animalId;
            this.numberOfPieces = numberOfPieces;
            this.cuttingDate = cuttingDate;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public String getAnimalId() {
            return animalId;
        }

        public String getNumberOfPieces() {
            return numberOfPieces;
        }

        public String getCuttingDate() {
            return cuttingDate;
        }
    }
}
