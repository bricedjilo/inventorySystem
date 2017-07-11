/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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
    private Button addProductSearch;

    @FXML
    private Button addProductAdd;

    @FXML
    private Button addProductSave;

    @FXML
    private Button addProductCancel;

    @FXML
    private Button addProductDelete;
    
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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
