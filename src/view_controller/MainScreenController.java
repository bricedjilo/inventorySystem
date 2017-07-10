/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private void handleMainAddPartsAction(ActionEvent event) throws IOException {
        stage = (new SceneUtil()).changeScene(event, "/fxml/addPart.fxml");
        stage.show();
    }
    
    @FXML
    private void handleMainModifyPartsAction(ActionEvent event) throws IOException {
        if(Inventory.getAllParts().size() > 0) {
            stage = (new SceneUtil()).changeScene(event, "/fxml/modifyPart.fxml");
            stage.show();
        } else {
            // Display error meesage: You have not added any part. There is nothing to modify
        }
        
    }
    
    @FXML
    private void handleDeletePart() {
        int selectedIdx = partsTable.getSelectionModel().getSelectedIndex();
        if(selectedIdx >= 0) {
            partsTable.getItems().remove(selectedIdx);
        }
    }
    
    @FXML
    private void handleMainExit(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
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
        partsTable.setItems(Inventory.getAllParts());
    }    
    
}
