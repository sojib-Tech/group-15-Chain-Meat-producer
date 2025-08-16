package com.example.group15chainmeatproducer.Saiful.User3;

import com.example.group15chainmeatproducer.SceneManager;
import com.example.group15chainmeatproducer.Saiful.User3.models.PackagingStandard;
import com.example.group15chainmeatproducer.Saiful.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class QAPackagingStandardsController {

    @FXML
    private ComboBox<String> packagingBatchComboBox;
    @FXML
    private DatePicker approvalDatePicker;
    @FXML
    private RadioButton approvedRadio;
    @FXML
    private RadioButton rejectedRadio;
    @FXML
    private TextField labelSealField;
    @FXML
    private TextField photoPathField;
    @FXML
    private TableView<PackagingStandard> packagingTable;
    @FXML
    private TableColumn<PackagingStandard, String> batchIdColumn;
    @FXML
    private TableColumn<PackagingStandard, String> statusColumn;
    @FXML
    private TableColumn<PackagingStandard, LocalDate> dateColumn;
    @FXML
    private TableColumn<PackagingStandard, String> photoColumn;
    @FXML
    private Button backButton;
    @FXML
    private Button uploadPhotoButton;
    @FXML
    private Button approveButton;

    private ToggleGroup packageStatusGroup;
    private ArrayList<PackagingStandard> packagingData;

    @FXML
    public void initialize() {
        packageStatusGroup = new ToggleGroup();
        approvedRadio.setToggleGroup(packageStatusGroup);
        rejectedRadio.setToggleGroup(packageStatusGroup);
        packagingBatchComboBox.getItems().clear();
        packagingBatchComboBox.getItems().addAll(
                "PACK-STD-001", "PACK-STD-002", "PACK-STD-003",
                "PACK-STD-004", "PACK-STD-005", "PACK-STD-006",
                "PACK-STD-007", "PACK-STD-008", "PACK-STD-009",
                "PACK-STD-010"
        );
        packagingBatchComboBox.setPromptText("Choose Packaging Batch");
        batchIdColumn.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("approvalDate"));
        photoColumn.setCellValueFactory(new PropertyValueFactory<>("photoPath"));
        packagingData = (ArrayList<PackagingStandard>) DataManager.loadFromFile("packaging_standards_data");
        if (packagingData == null) packagingData = new ArrayList<>();
        updateTable();
    }

    private void updateTable() {
        packagingTable.getItems().clear();
        packagingTable.getItems().addAll(packagingData);
    }

    private void saveData() {
        DataManager.saveToFile(packagingData, "packaging_standards_data");
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.switchToUser3Menu(event);
    }

    @FXML
    public void handleUploadPhoto(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Photo");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File file = chooser.showOpenDialog(uploadPhotoButton.getScene().getWindow());
        if (file != null) {
            photoPathField.setText(file.getName());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Success");
            a.setHeaderText(null);
            a.setContentText("Photo selected: " + file.getName());
            a.showAndWait();
        }
    }

    @FXML
    public void handleApprove(ActionEvent event) {
        String batch = packagingBatchComboBox.getValue();
        LocalDate date = approvalDatePicker.getValue();
        RadioButton selected = (RadioButton) packageStatusGroup.getSelectedToggle();
        String status = selected != null ? selected.getText() : "";
        String label = labelSealField.getText();
        String photo = photoPathField.getText();
        if (validateInputs(batch, date, status, label)) {
            PackagingStandard record = new PackagingStandard(batch, status, date, label, photo);
            packagingData.add(record);
            saveData();
            updateTable();
            clearAllFields();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Success");
            a.setHeaderText(null);
            a.setContentText("Approval recorded");
            a.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(null);
            a.setContentText("Fill all fields");
            a.showAndWait();
        }
    }

    private boolean validateInputs(String batch, LocalDate date, String status, String label) {
        return batch != null && !batch.isEmpty() && date != null && !status.isEmpty() && label != null && !label.trim().isEmpty();
    }

    private void clearAllFields() {
        packagingBatchComboBox.setValue(null);
        approvalDatePicker.setValue(null);
        packageStatusGroup.selectToggle(null);
        labelSealField.clear();
        photoPathField.clear();
    }
}