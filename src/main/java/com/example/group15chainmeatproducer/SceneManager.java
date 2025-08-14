package com.example.group15chainmeatproducer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class SceneManager {

    public static void switchToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Loginfxml.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Chain Meat Producer - Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToSignUp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/SignUpfxml.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Chain Meat Producer - Sign Up");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser1Menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Sojib/User1/FF_MenuPage.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Factory Floor Worker Dashboard");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser2Menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Sojib/User2/EC_MenuPage.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Export Coordinator Dashboard");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser3Menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User3/QA_MenuPage.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Quality Assurance Officer Dashboard");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser4Menu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User4/IM_MenuPage.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Inventory Manager Dashboard");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // User3 (Quality Assurance Officer) Goal Navigation Methods
    public static void switchToUser3Goal1(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User3/QA_PreSlaughterInspection.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Pre-Slaughter Animal Inspection");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser3Goal2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User3/QA_HygieneChecklist.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Verify Hygiene Checklist Post-Cleaning");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser3Goal3(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User3/QA_EquipmentCalibration.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Oversee Calibration of Critical Equipment");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser3Goal4(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User3/QA_MeatQuality.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Evaluate Meat Quality Parameters");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser3Goal5(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User3/QA_PackagingStandards.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Approve Packaging Standards");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser3Goal6(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User3/QA_ColdStorageAudit.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Audit Cold Storage Temperature Logs");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser3Goal7(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User3/QA_HACCPCompliance.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Monitor HACCP Compliance Points");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser3Goal8(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User3/QA_FinalProductApproval.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Approve Final Product Batch for Dispatch");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // User4 (Inventory Manager) Goal Navigation Methods
    public static void switchToUser4Goal1(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User4/IM_RawMaterialInventory.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Verify Incoming Raw Material Inventory");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser4Goal2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User4/IM_ProcessingFloorInventory.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Monitor Live Processing Floor Inventory");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser4Goal3(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User4/IM_PackagingMaterial.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Track Packaging Material Usage");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser4Goal4(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User4/IM_WeeklyAudit.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Conduct Weekly Inventory Audit");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser4Goal5(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User4/IM_DamagedStock.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Manage Damaged or Expired Stock");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser4Goal6(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User4/IM_DispatchStock.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Dispatch Stock to Export Area");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser4Goal7(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User4/IM_MonthlyReports.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Generate Monthly Stock Reports");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToUser4Goal8(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Saiful/User4/IM_StockReconciliation.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Reconcile Stock After Shipment Dispatch");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToCustomerMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Ami/Customer/MenuCustomer.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Customer Menu");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToCSRMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Ami/CustomerServiceRepresentative/MenuCustomerService.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Customer Service Representative Dashboard");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToLogisticsMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Sakib-Ome/LogisticsManager/MenuLogistics.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Logistics Manager - Menu");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToMaintenanceMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/group15chainmeatproducer/Sakib-Ome/MaintenanceTechnician/MenuMaintenance.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Maintenance Technician - Menu");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}