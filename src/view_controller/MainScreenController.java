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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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
public class MainScreenController implements Initializable {

    private Stage stage;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainScreen;

    @FXML
    private Button mainPartsDelete;

    @FXML
    private Button mainPartsModify;

    @FXML
    private Button mainPartsAdd;

    @FXML
    private Button mainPartsSearch;

    @FXML
    private Button mainExit;

    @FXML
    private Button mainProductsDelete;

    @FXML
    private Button mainProductsModify;

    @FXML
    private Button mainProductsAdd;

    @FXML
    private Button mainProductsSearch;
    
    @FXML 
    private TextField searchPartField;
    
    @FXML 
    private CheckBox disableAutoSearchBox;
    
    @FXML
    private TableView<Part> partsTable;
    
    @FXML
    private TableColumn<Part, Number> partIdColumn;
    
    @FXML
    private TableColumn<Part, String> partNameColumn;
    
    @FXML
    private TableColumn<Part, Number> invLevelColumn;
    
    @FXML
    private TableColumn<Part, Number> priceCostColumn;
    
    @FXML
    private TableView<Part> productsTable;
    
    @FXML
    private TableColumn<Part, Number> productIdColumn;
    
    @FXML
    private TableColumn<Part, String> productNameColumn;
    
    @FXML
    private TableColumn<Part, Number> productInvLevelColumn;
    
    @FXML
    private TableColumn<Part, Number> productPriceColumn;
    
    private ChangeListener<String> partListener;

    
    //------------- Part Actions ----------------//
    @FXML
    private void handleMainAddPartsAction(ActionEvent event) {
        try {
            stage = (new SceneUtil()).changeScene(event, "/fxml/addPart.fxml");
            stage.show();
        } catch (IOException ex) {
            System.err.println("Unable to load /fxml/addPart.fxml. Please make sure the path is correct.");
        }
    }
    
    @FXML
    private void handleMainModifyPartsAction(ActionEvent event) {
        if(Inventory.getAllParts().size() > 0) {
            try {
                stage = (new SceneUtil()).changeScene(event, "/fxml/modifyPart.fxml");
                stage.show();
            } catch (IOException ex) {
                System.err.println("Unable to load /fxml/modifyPart.fxml. Please make sure the path is correct.");
            }
        } else {
            // Display error meesage: You have not added any part. There is nothing to modify
        }
    }
    
    @FXML
    private void handleSearchCheckBox() {
        if(disableAutoSearchBox.isSelected()) {
            mainPartsSearch.setDisable(false);
            searchPartField.textProperty().removeListener(partListener);
        } else {
            FilteredList<Part> filteredParts = new FilteredList<>(Inventory.getAllParts(), query -> true);
            partListener = ((observable, oldValue, newValue) -> {
                filteredParts.setPredicate(part -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String input = newValue.toLowerCase();

                    return (part.getPartName().toLowerCase().contains(input) || 
                        part.instockProperty().getValue().toString().contains(input) || 
                        part.partIdProperty().getValue().toString().contains(input) ||
                        part.priceProperty().getValue().toString().contains(input));
                });
            });
            searchPartField.textProperty().addListener(partListener);
            mainPartsSearch.setDisable(true);
            partsTable.setItems(filteredParts);
        }
    }
    
    @FXML
    private void handlePartSearch() {
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
        partsTable.setItems(parts);
    }
    
    @FXML
    private void handleDeletePart() {
        int selectedIdx = partsTable.getSelectionModel().getSelectedIndex();
        if(selectedIdx >= 0) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("You are about to delete a part: " + 
                partsTable.getSelectionModel().getSelectedItem());
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                partsTable.getItems().remove(selectedIdx);
            } 
        }
    }
    
    @FXML
    private void handleMainExit(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }
    
    
    //------------- Product Actions ----------------//
    
    @FXML
    private void handleAddProduct (ActionEvent event) {
        try {
            stage = (new SceneUtil()).changeScene(event, "/fxml/addProduct.fxml");
            stage.show();
        } catch (IOException ex) {
            System.err.println("Unable to load /fxml/addPart.fxml. Please make sure the path is correct.");
        }
    }
    
    @FXML
    private void handleModifyProduct (ActionEvent event) {
        try {
            stage = (new SceneUtil()).changeScene(event, "/fxml/modifyProduct.fxml");
            stage.show();
        } catch (IOException ex) {
            System.err.println("Unable to load /fxml/modifyProduct.fxml. Please make sure the path is correct.");
        }
    }
    
    
    public MainScreenController() { }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        partIdColumn.setCellValueFactory(cellData -> cellData.getValue().partIdProperty());
        invLevelColumn.setCellValueFactory(cellData -> cellData.getValue().instockProperty());
        priceCostColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

        mainPartsSearch.setDisable(true);
        
        FilteredList<Part> filteredParts = new FilteredList<>(Inventory.getAllParts(), query -> true);

        partListener = ((observable, oldValue, newValue) -> {
            filteredParts.setPredicate(part -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String input = newValue.toLowerCase();
                
                return (part.getPartName().toLowerCase().contains(input) || 
                    part.instockProperty().getValue().toString().contains(input) || 
                    part.partIdProperty().getValue().toString().contains(input) ||
                    part.priceProperty().getValue().toString().contains(input));
            });
        });
        searchPartField.textProperty().addListener(partListener);
        partsTable.setItems(filteredParts);

    }    
    
}
