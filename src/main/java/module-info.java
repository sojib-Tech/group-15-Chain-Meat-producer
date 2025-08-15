module com.example.group15chainmeatproducer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;


    opens com.example.group15chainmeatproducer to javafx.fxml;
    exports com.example.group15chainmeatproducer;
    // Sakib-Ome (User-7) packages
    opens com.example.group15chainmeatproducer.SakibOme.LogisticsManager to javafx.fxml, javafx.base;
    exports com.example.group15chainmeatproducer.SakibOme.LogisticsManager;
    opens com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician to javafx.fxml, javafx.base;
    exports com.example.group15chainmeatproducer.SakibOme.MaintenanceTechnician;
    opens com.example.group15chainmeatproducer.Saiful to javafx.fxml;
    exports com.example.group15chainmeatproducer.Saiful;
    opens com.example.group15chainmeatproducer.Saiful.User3 to javafx.fxml;
    exports com.example.group15chainmeatproducer.Saiful.User3;
    opens com.example.group15chainmeatproducer.Saiful.User3.models to javafx.base;
    exports com.example.group15chainmeatproducer.Saiful.User3.models;
    opens com.example.group15chainmeatproducer.Saiful.User4 to javafx.fxml;
    exports com.example.group15chainmeatproducer.Saiful.User4;
    opens com.example.group15chainmeatproducer.Saiful.User4.models to javafx.base;
    exports com.example.group15chainmeatproducer.Saiful.User4.models;
    opens com.example.group15chainmeatproducer.Sojib to javafx.fxml;
    exports com.example.group15chainmeatproducer.Sojib;
    exports com.example.group15chainmeatproducer.Sojib.User1;
    opens com.example.group15chainmeatproducer.Sojib.User1 to javafx.fxml;
    exports com.example.group15chainmeatproducer.Sojib.User2;
    opens com.example.group15chainmeatproducer.Sojib.User2 to javafx.fxml;
    exports com.example.group15chainmeatproducer.Ami.Customer;
    opens com.example.group15chainmeatproducer.Ami.Customer to javafx.fxml;
    exports com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;
    opens com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative to javafx.fxml;
}