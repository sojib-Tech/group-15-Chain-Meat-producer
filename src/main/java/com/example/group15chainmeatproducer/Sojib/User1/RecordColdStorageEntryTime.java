package com.example.group15chainmeatproducer.Sojib.User1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RecordColdStorageEntryTime {

    @FXML
    private TextField operatorName;
    @FXML
    private TextField entrytime;
    @FXML
    private DatePicker entrydTE;
    @FXML
    private ComboBox<String> productBatch;
    @FXML
    private TableView<Object> table_record_cold_storage_entry_time;
    @FXML
    private TableColumn<Object, String> t_operatorNAME;
    @FXML
    private TableColumn<Object, String> t_entryTime;
    @FXML
    private TableColumn<Object, String> t_entryDay;
    @FXML
    private TableColumn<Object, String> t_productBatch;

    @FXML
    public void initialize() {
        // Initialize combo box with sample data
        if (productBatch != null) {
            productBatch.getItems().addAll("Batch001", "Batch002", "Batch003");
        }
    }

    @FXML
    public void submitGaol7(ActionEvent event) {
        // Handle submission
        System.out.println("Cold storage entry time submitted");
    }

    @FXML
    public void backtoGaol6(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FF_MenuPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}