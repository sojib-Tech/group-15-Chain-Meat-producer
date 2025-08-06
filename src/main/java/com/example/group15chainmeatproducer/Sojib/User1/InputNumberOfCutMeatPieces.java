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
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class InputNumberOfCutMeatPieces {

    @FXML private TextField oprator_name;
    @FXML private ComboBox<String> animalIdComboBox;
    @FXML private TextField numberOfPieces;
    @FXML private DatePicker dateOfCutting;

    @FXML private TableView<MeatCutData> table_input_number_of_cut_meat_pieces;
    @FXML private TableColumn<MeatCutData, String> t_opratorName;
    @FXML private TableColumn<MeatCutData, String> t_batch_Id;
    @FXML private TableColumn<MeatCutData, String> t_numberOfPeices;
    @FXML private TableColumn<MeatCutData, String> t_date;

    @FXML private Button btnBack;
    @FXML private Button btnSubmit;

    private final File checkInFile = new File("checkIn.bin");
    private final File outputFile = new File("cutting_pieces.bin");
    private final ObservableList<MeatCutData> records = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        t_opratorName.setCellValueFactory(data -> data.getValue().operatorNameProperty());
        t_batch_Id.setCellValueFactory(data -> data.getValue().animalIdProperty());
        t_numberOfPeices.setCellValueFactory(data -> data.getValue().numberOfPiecesProperty());
        t_date.setCellValueFactory(data -> data.getValue().cuttingDateProperty());

        table_input_number_of_cut_meat_pieces.setItems(records);

        loadAnimalIdsFromCheckIn();

        loadSavedData();
    }

    private void loadAnimalIdsFromCheckIn() {
        if (!checkInFile.exists()) return;

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

        animalIdComboBox.setItems(FXCollections.observableArrayList(uniqueIds));
    }

    private void loadSavedData() {
        if (!outputFile.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(outputFile))) {
            while (true) {
                MeatCutData data = (MeatCutData) ois.readObject();
                records.add(data);
            }
        } catch (EOFException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void submitgoal4() {
        String operator = oprator_name.getText();
        String animalId = animalIdComboBox.getValue();
        String pieces = numberOfPieces.getText();
        LocalDate date = dateOfCutting.getValue();

        if (operator.isEmpty() || animalId == null || pieces.isEmpty() || date == null) {
            showAlert("All fields are required.");
            return;
        }

        MeatCutData data = new MeatCutData(operator, animalId, pieces, date.toString());
        records.add(data);
        appendToFile(data);
        clearFields();
    }

    private void appendToFile(MeatCutData data) {
        try (FileOutputStream fos = new FileOutputStream(outputFile, true);
             ObjectOutputStream oos = outputFile.exists() && outputFile.length() > 0
                     ? new AppendingObjectOutputStream(fos)
                     : new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to save data.");
        }
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
            Parent root = FXMLLoader.load(getClass().getResource("FF_MenuPage.fxml"));
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Unable to go back.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // Serializable inner class
    public static class MeatCutData implements Serializable {
        private final String operatorName;
        private final String animalId;
        private final String numberOfPieces;
        private final String cuttingDate;

        public MeatCutData(String operatorName, String animalId, String numberOfPieces, String cuttingDate) {
            this.operatorName = operatorName;
            this.animalId = animalId;
            this.numberOfPieces = numberOfPieces;
            this.cuttingDate = cuttingDate;
        }

        public StringProperty operatorNameProperty() { return new SimpleStringProperty(operatorName); }
        public StringProperty animalIdProperty() { return new SimpleStringProperty(animalId); }
        public StringProperty numberOfPiecesProperty() { return new SimpleStringProperty(numberOfPieces); }
        public StringProperty cuttingDateProperty() { return new SimpleStringProperty(cuttingDate); }
    }

    // Appending stream to avoid header corruption
    private static class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException { super(out); }
        @Override protected void writeStreamHeader() throws IOException { reset(); }
    }
}
