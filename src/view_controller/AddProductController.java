/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import util.SceneUtil;

/**
 * FXML Controller class
 *
 * @author Pat
 */
public class AddProductController implements Initializable {
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane addProduct;
    
    @FXML
    private Button addPartToProductButton;
    
    @FXML
    private Button saveNewProductButton;
    
    @FXML 
    private Button deletePartButton;
    
    @FXML
    private Button searchPartButton;
    
    @FXML
    private TableView<Part> searchPartForProductTable;

    @FXML
    private TableColumn<Part, Number> partIdColumn;
    
    @FXML
    private TableColumn<Part, String> partNameColumn;
    
    @FXML
    private TableColumn<Part, Number> invLevelColumn;
    
    @FXML
    private TableColumn<Part, Number> priceCostColumn;
    
    @FXML
    private TableView<Part> productPartsTable;
    
    @FXML
    private TableColumn<Part, Number> addedPartIdColumn;
    
    @FXML
    private TableColumn<Part, String> addedPartNameColumn;
    
    @FXML
    private TableColumn<Part, Number> addedInvLevelColumn;
    
    @FXML
    private TableColumn<Part, Number> addedPriceCostColumn;
    
    

    @FXML
    private TextField searchPartField;
    
    @FXML
    private TextField productNameField;
    
    @FXML
    private TextField productInStockField;
    
    @FXML
    private TextField productPriceField;
    
    @FXML
    private TextField productMaxField;
    
    @FXML
    private TextField productMinField;
    
    private Stage stage;
    
    private ObservableList<Part> addedParts = FXCollections.observableArrayList();
    
    
    @FXML
    private void handleCancelProductAdd(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("You are about to stop adding a product");
        alert.setContentText("Are you ok with this? \n" +
            "If you press OK, entered information will not be saved.\n");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                stage = ((new SceneUtil()).changeScene(event, "/fxml/mainScreen.fxml"));
                stage.show();
            } catch (IOException ex) {
                System.err.println("From AddProductController, /fxml/mainScreen.fxml cannot be located/loaded.");
            }
        } 
    }
    
    @FXML
    private void handleSearchPartAdd(ActionEvent event) {
        FilteredList<Part> parts = new FilteredList<>(Inventory.getAllParts(), pre -> true);
        String query = searchPartField.getText();
        
        parts.setPredicate(part -> {
            if (query == null || query.isEmpty()) {
                return true;
            }
            return (part.getPartName().toLowerCase().contains(query) || 
                part.instockProperty().getValue().toString().contains(query) || 
                part.partIdProperty().getValue().toString().contains(query) ||
                part.priceProperty().getValue().toString().contains(query));
        });
        if(parts.size() > 0) {
            addPartToProductButton.setDisable(false);
        } else {
            addPartToProductButton.setDisable(true);
        }
        searchPartForProductTable.setItems(parts);
    }
    
    @FXML
    private void handleAddPartToProduct(ActionEvent event) {
        
        addedParts.addAll(searchPartForProductTable.getItems().filtered(part-> {
            if(addedParts.isEmpty()) return true;
            return addedParts.filtered(addedPart -> {
                return addedPart.getPartID() == part.getPartID();
            }).size() != 1;
        }));
        
        if(addedParts.size() > 0) {
            deletePartButton.setDisable(false);
            saveNewProductButton.setDisable(false);
        } else {
            deletePartButton.setDisable(true);
            saveNewProductButton.setDisable(true);
        }
        productPartsTable.setItems(addedParts);
        
    }
    
    @FXML
    private void handleDeletePart(ActionEvent event) {
        
        Part partToBeDeleted = productPartsTable.getSelectionModel().getSelectedItem();
        System.out.println(partToBeDeleted);
        
        addedParts.remove(partToBeDeleted);
        
        if(addedParts.size() > 0) {
            deletePartButton.setDisable(false);
            saveNewProductButton.setDisable(false);
        } else {
            deletePartButton.setDisable(true);
            saveNewProductButton.setDisable(true);
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        partIdColumn.setCellValueFactory(cellData -> cellData.getValue().partIdProperty());
        invLevelColumn.setCellValueFactory(cellData -> cellData.getValue().instockProperty());
        priceCostColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        
        addedPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        addedPartIdColumn.setCellValueFactory(cellData -> cellData.getValue().partIdProperty());
        addedInvLevelColumn.setCellValueFactory(cellData -> cellData.getValue().instockProperty());
        addedPriceCostColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
              
        
        // Disable Add, Delete, and Save if the product has not been created
        addPartToProductButton.setDisable(true);
        saveNewProductButton.setDisable(true);
        deletePartButton.setDisable(true);
    }    

    
    
}
