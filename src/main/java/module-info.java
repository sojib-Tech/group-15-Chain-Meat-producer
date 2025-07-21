module com.example.group15chainmeatproducer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.group15chainmeatproducer to javafx.fxml;
    exports com.example.group15chainmeatproducer;
}