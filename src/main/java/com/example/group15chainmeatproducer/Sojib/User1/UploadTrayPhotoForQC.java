package com.example.group15chainmeatproducer.Sojib.User1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UploadTrayPhotoForQC implements Initializable {

    @FXML
    private TextField opratorID;
    @FXML
    private ComboBox<String> trayID;
    @FXML
    private TextField comments;
    @FXML
    private Label upload_tray_photo_for_qc;
    @FXML
    private TableView<TrayPhotoData> table_upload_tray_photo_for_QC;
    @FXML
    private TableColumn<TrayPhotoData, String> t_opratorID;
    @FXML
    private TableColumn<TrayPhotoData, String> t_trayID;
    @FXML
    private TableColumn<TrayPhotoData, String> t_comments;

    private ArrayList<TrayPhotoData> trayPhotoDataList;
    private final String DATA_FILE = "trayPhoto.bin";
    private String uploadedPhotoPath = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        setupComboBox();
        loadData();
    }

    private void setupTable() {
        t_opratorID.setCellValueFactory(new PropertyValueFactory<>("operatorId"));
        t_trayID.setCellValueFactory(new PropertyValueFactory<>("trayId"));
        t_comments.setCellValueFactory(new PropertyValueFactory<>("comments"));
    }

    private void setupComboBox() {
        trayID.getItems().addAll("TRAY001", "TRAY002", "TRAY003", "TRAY004", "TRAY005");
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            trayPhotoDataList = (ArrayList<TrayPhotoData>) ois.readObject();
        } catch (Exception e) {
            trayPhotoDataList = new ArrayList<>();
        }
        refreshTable();
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(trayPhotoDataList);
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void refreshTable() {
        ObservableList<TrayPhotoData> observableList = FXCollections.observableArrayList(trayPhotoDataList);
        table_upload_tray_photo_for_QC.setItems(observableList);
    }

    @FXML
    private void uploadPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Tray Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif")
        );

        Stage stage = (Stage) opratorID.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            uploadedPhotoPath = selectedFile.getAbsolutePath();
            showAlert("Success", "Photo selected: " + selectedFile.getName());
        }
    }

    @FXML
    private void submitgoal5data() {
        if (validateInput()) {
            String operatorId = opratorID.getText();
            String trayId = trayID.getValue();
            String comment = comments.getText();

            TrayPhotoData trayData = new TrayPhotoData(operatorId, trayId, comment, uploadedPhotoPath);
            trayPhotoDataList.add(trayData);

            saveData();
            refreshTable();
            clearFields();
            showAlert("Success", "Tray photo data saved successfully!");
        }
    }

    private boolean validateInput() {
        if (opratorID.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Operator ID");
            return false;
        }
        if (trayID.getValue() == null) {
            showAlert("Error", "Please select Tray ID");
            return false;
        }
        if (comments.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter Comments");
            return false;
        }
        if (uploadedPhotoPath.isEmpty()) {
            showAlert("Error", "Please upload a photo first");
            return false;
        }
        return true;
    }

    private void clearFields() {
        opratorID.clear();
        trayID.setValue(null);
        comments.clear();
        uploadedPhotoPath = "";
    }

    @FXML
    private void backtogoal4() {
        try {
            Stage stage = (Stage) opratorID.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Goal4CutMeatPieces.fxml"));
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

    public static class TrayPhotoData implements Serializable {
        private String operatorId;
        private String trayId;
        private String comments;
        private String photoPath;

        public TrayPhotoData(String operatorId, String trayId, String comments, String photoPath) {
            this.operatorId = operatorId;
            this.trayId = trayId;
            this.comments = comments;
            this.photoPath = photoPath;
        }

        public String getOperatorId() {
            return operatorId;
        }

        public String getTrayId() {
            return trayId;
        }

        public String getComments() {
            return comments;
        }

        public String getPhotoPath() {
            return photoPath;
        }
    }
}