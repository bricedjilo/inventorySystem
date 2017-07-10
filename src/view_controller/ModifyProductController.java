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

/**
 * FXML Controller class
 *
 * @author Pat
 */
public class ModifyProductController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane modifyProduct;

    @FXML
    private Button modifyProductSearch;

    @FXML
    private Button modifyProductAdd;

    @FXML
    private Button modifyProductSave;

    @FXML
    private Button modifyProductCancel;

    @FXML
    private Button modifyProductDelete;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
