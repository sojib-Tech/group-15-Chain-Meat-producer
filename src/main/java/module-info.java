module com.example.group15chainmeatproducer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.group15chainmeatproducer to javafx.fxml;
    exports com.example.group15chainmeatproducer;
    opens com.example.group15chainmeatproducer.Ami to javafx.fxml;
    exports com.example.group15chainmeatproducer.Ami;
    opens com.example.group15chainmeatproducer.Saiful to javafx.fxml;
    exports com.example.group15chainmeatproducer.Saiful;
    opens com.example.group15chainmeatproducer.Sojib to javafx.fxml;
    exports com.example.group15chainmeatproducer.Sojib;
}