/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_bricedjilo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Pat
 */
public class InventorySystem extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        InventoryService inventoryService = new InventoryService();
        inventoryService.createDefaultParts();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Inventory Management System");
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
        
    }
    
}
