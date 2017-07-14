/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import inventorysystem_bricedjilo.InventoryService;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import util.SceneUtil;
import validation.InputConstraintException;
import validation.Validate;

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
    
    @FXML
    private Text errorSaveProductField;
    
    private Stage stage;
    
    private ObservableList<Part> addedParts = FXCollections.observableArrayList();
    private Scene scene;

    
    
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    
    
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
        if(partToBeDeleted != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("You are about to delete a part: " + partToBeDeleted);
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                addedParts.remove(partToBeDeleted);
            } 
            if(addedParts.size() > 0) {
            deletePartButton.setDisable(false);
            saveNewProductButton.setDisable(false);
            } else {
                deletePartButton.setDisable(true);
                saveNewProductButton.setDisable(true);
            }
        }
    }
    
    @FXML
    private void handleSaveNewProduct(ActionEvent event) {
        this.setScene(((Node) event.getSource()).getScene());
        try {
            Validate.isAlphaNumeric(productNameField.getText().trim(), "Name", "productNameField");
            productNameField.setStyle("-fx-border-color: green;");
            Validate.isPositiveInt(productInStockField.getText().trim(), "Inv", "productInStockField");
            productInStockField.setStyle("-fx-border-color: green;");
            Validate.isPositiveDouble(productPriceField.getText().trim(), "Price", "productPriceField");
            productPriceField.setStyle("-fx-border-color: green;");
            Validate.isPositiveInt(productMaxField.getText().trim(), "Max", "productMaxField");
            productMaxField.setStyle("-fx-border-color: green;");
            Validate.isNotGreaterThan(productInStockField.getText(), "Inv", "productInStockField", 
                productMaxField.getText(), "Max", "productMaxField");
            Validate.isPositiveInt(productMinField.getText().trim(), "Min", "productMinField");
            productMinField.setStyle("-fx-border-color: green;");
            Validate.isNotLessThan(productInStockField.getText(), "Inv", "productInStockField", 
                productMinField.getText(), "Min", "productMinField");
            Validate.isNotGreaterThan(productMinField.getText(), "Min", "productMinField",  
                productMaxField.getText(), "Max", "productMaxField");
            
            double totalCostOfParts = costOfParts(addedParts);
            Validate.isNotLessThan(productPriceField.getText(), "Price", 
                "productPriceField", Double.toString(totalCostOfParts), 
                "The total cost of parts (" + totalCostOfParts + ")", "addedPriceCostColumn");
            
            errorSaveProductField.setText("");
            
            Inventory.setLastProductAdded(new Product(
                addedParts, InventoryService.getNextProductID(), productNameField.getText(), 
                Double.parseDouble(productPriceField.getText()), 
                Integer.parseInt(productInStockField.getText()), 
                Integer.parseInt(productMinField.getText()), 
                Integer.parseInt(productMaxField.getText()))
            );
            Inventory.addProduct(Inventory.getLastProductAdded());
            setStage((new SceneUtil()).changeScene(event, "/fxml/mainScreen.fxml"));
            stage.show();            
        } catch (IOException io) {
            errorSaveProductField.setText(io.getMessage());
        } catch (PatternSyntaxException patEx) {
            String[] message = patEx.getMessage().split("-");
            errorSaveProductField.setText(message[0]);
            String field = message[1].split("\n")[0].trim();
            scene.lookup("#"+field).setStyle("-fx-border-color: red;");
        } catch(InputConstraintException inputConstEx) { 
            String[] message = inputConstEx.getMessage().split("-");
            errorSaveProductField.setText(message[0]);
            getScene().lookup("#"+message[1].trim()).setStyle("-fx-border-color: orange;");
            getScene().lookup("#"+message[2].trim()).setStyle("-fx-border-color: orange;");
        } catch(Exception ex) {
            errorSaveProductField.setText(ex.getMessage());
        }
    }
    
    private double costOfParts(ObservableList<Part> parts) {
        double cost = 0.0;
        return parts.stream().map((part) -> part.getPrice()).reduce(cost, (accumulator, _item) -> accumulator + _item);
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
