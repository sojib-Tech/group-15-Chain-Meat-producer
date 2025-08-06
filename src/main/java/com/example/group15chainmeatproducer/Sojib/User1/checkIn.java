package com.example.group15chainmeatproducer.Sojib.User1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class checkIn {

    @FXML private TextField operatorNameField;
    @FXML private TextField animalIdField;
    @FXML private TextField placeField;
    @FXML private DatePicker dateField;

    @FXML private TableView<HygieneData> table_Upload_Hygiene_Checklist;
    @FXML private TableColumn<HygieneData, String> t_operator_name;
    @FXML private TableColumn<HygieneData, String> t_animal_id;
    @FXML private TableColumn<HygieneData, String> t_date;
    @FXML private TableColumn<HygieneData, String> t_place;

    @FXML private Button btnBack;

    private final File binFile = new File("checkIn.bin");

    @FXML
    public void initialize() {
        t_operator_name.setCellValueFactory(data -> data.getValue().operatorNameProperty());
        t_animal_id.setCellValueFactory(data -> data.getValue().animalIdProperty());
        t_date.setCellValueFactory(data -> data.getValue().dateProperty());
        t_place.setCellValueFactory(data -> data.getValue().placeProperty());

        loadFromBinFile(); // load records when screen opens
    }

    @FXML
    public void save() {
        String operatorName = operatorNameField.getText().trim();
        String animalId = animalIdField.getText().trim();
        String place = placeField.getText().trim();
        LocalDate date = dateField.getValue();

        if (operatorName.isEmpty() || animalId.isEmpty() || place.isEmpty() || date == null) {
            showAlert("Please fill in all fields.");
            return;
        }

        HygieneData data = new HygieneData(operatorName, animalId, date.toString(), place);
        table_Upload_Hygiene_Checklist.getItems().add(data);
        appendToBinFile(data);

        operatorNameField.clear();
        animalIdField.clear();
        placeField.clear();
        dateField.setValue(null);
    }

    private void appendToBinFile(HygieneData data) {
        try {
            boolean fileExists = binFile.exists() && binFile.length() > 0;
            FileOutputStream fos = new FileOutputStream(binFile, true);
            ObjectOutputStream oos = fileExists
                    ? new AppendingObjectOutputStream(fos)
                    : new ObjectOutputStream(fos);

            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to save data.");
        }
    }

    private void loadFromBinFile() {
        if (!binFile.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(binFile))) {
            while (true) {
                HygieneData data = (HygieneData) ois.readObject();
                table_Upload_Hygiene_Checklist.getItems().add(data);
            }
        } catch (EOFException eof) {
            // End of file — normal case
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backgoal1() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FF_MenuPage.fxml"));
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Could not load menu screen.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // Serializable data class
    public static class HygieneData implements Serializable {
        private final String operatorName;
        private final String animalId;
        private final String date;
        private final String place;

        public HygieneData(String operatorName, String animalId, String date, String place) {
            this.operatorName = operatorName;
            this.animalId = animalId;
            this.date = date;
            this.place = place;
        }

        // Used by scan controller
        public String getAnimalId() {
            return animalId;
        }

        public StringProperty operatorNameProperty() { return new SimpleStringProperty(operatorName); }
        public StringProperty animalIdProperty() { return new SimpleStringProperty(animalId); }
        public StringProperty dateProperty() { return new SimpleStringProperty(date); }
        public StringProperty placeProperty() { return new SimpleStringProperty(place); }
    }

    // Appends objects to .bin file without duplicating headers
    private static class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset(); // don't write a header again
        }
    }
}
