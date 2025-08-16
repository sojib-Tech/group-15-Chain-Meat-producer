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

public class MarkShiftHandover {

    @FXML
    private TextField supervisorName;
    @FXML
    private TextField notes;
    @FXML
    private ComboBox<String> incom_opearator;
    @FXML
    private ComboBox<String> outg_oparator;
    @FXML
    private DatePicker dates;
    @FXML
    private TableView<Object> table_mark_shift_handover;
    @FXML
    private TableColumn<Object, String> t_supervisorName;
    @FXML
    private TableColumn<Object, String> t_notes;
    @FXML
    private TableColumn<Object, String> t_outgoing_operator;
    @FXML
    private TableColumn<Object, String> incoming_operator;
    @FXML
    private TableColumn<Object, String> t_dateGaol8;

    @FXML
    public void initialize() {
        if (incom_opearator != null) {
            incom_opearator.getItems().addAll("Operator A", "Operator B", "Operator C");
        }
        if (outg_oparator != null) {
            outg_oparator.getItems().addAll("Operator 1", "Operator 2", "Operator 3");
        }
    }

    @FXML
    public void submit_handover(ActionEvent event) {
        // Handle handover submission
        System.out.println("Handover submitted");
    }

    @FXML
    public void backtogoal7(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FF_MenuPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}