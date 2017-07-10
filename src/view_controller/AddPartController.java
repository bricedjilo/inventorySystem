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
import java.util.regex.PatternSyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import model.InhousePart;
import model.Inventory;
import model.OutsourcedPart;
import util.SceneUtil;
import validation.InputConstraintException;
import validation.Validate;

/**
 * FXML Controller class
 *
 * @author Pat
 */
public class AddPartController implements Initializable {

    private Stage stage;
    private Scene scene;
    
    private int id = 5;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane addPart;

    @FXML
    private Button addPartCancel;

    @FXML
    private RadioButton addPartInHouse;

    @FXML
    private RadioButton addPartOutSourced;

    @FXML
    private Button addPartSave;
    
    @FXML
    private TextField machineIdCompNameField, partID, priceCostField, maxField, minField, invField, nameField;
    
    @FXML 
    private Label machineIdCompNameLabel;
    
    @FXML
    private Text errorField;
            
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public Stage getStage() {
        return stage;
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("You are about to stop adding a part");
        alert.setContentText("Are you ok with this? \n" +
            "If you press OK, the current part will not be added.\n");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                setStage((new SceneUtil()).changeScene(event, "/fxml/mainScreen.fxml"));
                stage.show();
            } catch (IOException ex) {
                System.err.println("From AddPartController, /fxml/mainScreen.fxml cannot be located/loaded.");
            }
        } 
    }
    
    @FXML
    private void handleInHouse(ActionEvent event) {
//        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        machineIdCompNameLabel.setText("Machine ID");
        machineIdCompNameField.setPromptText("Machine ID");
    }
    
    @FXML
    private void handleOutSourced(ActionEvent event) {
        machineIdCompNameLabel.setText("Company Name");
        machineIdCompNameField.setPromptText("Company Name");
    }
    
    @FXML
    private void handleAddPartSave(ActionEvent event) {
        scene = ((Node) event.getSource()).getScene();
        try {
            Validate.isAlphaNumeric(nameField.getText().trim(), "Name", "nameField");
            nameField.setStyle("-fx-border-color: green;");
            Validate.isPositiveInt(invField.getText().trim(), "Inv", "invField");
            invField.setStyle("-fx-border-color: green;");
            Validate.isPositiveDouble(priceCostField.getText().trim(), "Price/Cost", "priceCostField");
            priceCostField.setStyle("-fx-border-color: green;");
            Validate.isPositiveInt(maxField.getText().trim(), "Max", "maxField");
            maxField.setStyle("-fx-border-color: green;");
            Validate.isNotGreaterThan(invField.getText(), "Inv", "invField", 
                maxField.getText(), "Max", "maxField");
            Validate.isPositiveInt(minField.getText().trim(), "Min", "minField");
            minField.setStyle("-fx-border-color: green;");
            Validate.isNotLessThan(invField.getText(), "Inv", "invField", 
                minField.getText(), "Min", "minField");
            Validate.isNotGreaterThan(minField.getText(), "Min", "minField",  
                maxField.getText(), "Max", "maxField");
            
            if(addPartInHouse.isSelected()) {
                Validate.isPositiveInt(machineIdCompNameField.getText().trim(), "Machine ID", "machineIdCompNameField");
                machineIdCompNameField.setStyle("-fx-border-color: green;");
                Inventory.setPreviousInhousePart(new InhousePart(
                    Integer.parseInt(machineIdCompNameField.getText()), ++id, 
                    nameField.getText(), Double.parseDouble(priceCostField.getText()), 
                    Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), 
                    Integer.parseInt(maxField.getText())
                ));
                Inventory.addPart(Inventory.getPreviousInhousePart());
                Inventory.setPreviousInhousePartIndex(Inventory.getAllParts().size()-1);
            } else {
                Validate.isAlphaNumeric(machineIdCompNameField.getText().trim(), "Machine ID", "machineIdCompNameField");
                machineIdCompNameField.setStyle("-fx-border-color: green;");
                Inventory.setPreviousOutsourcedPart(new OutsourcedPart(machineIdCompNameField.getText(), ++id, 
                    nameField.getText(), Double.parseDouble(priceCostField.getText()), 
                    Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), 
                    Integer.parseInt(maxField.getText())
                ));
                Inventory.addPart(Inventory.getPreviousOutsourcedPart());
                Inventory.setPreviousOutsourcedPartIndex(Inventory.getAllParts().size()-1);
            }
            setStage((new SceneUtil()).changeScene(event, "/fxml/mainScreen.fxml"));
            stage.show();
        } catch (IOException io) {
            errorField.setText(io.getMessage());
        } catch (PatternSyntaxException patEx) {
            String[] message = patEx.getMessage().split("-");
            errorField.setText(message[0]);
            String field = message[1].split("\n")[0].trim();
            scene.lookup("#"+field).setStyle("-fx-border-color: red;");
        } catch(InputConstraintException inputConstEx) { 
            String[] message = inputConstEx.getMessage().split("-");
            errorField.setText(message[0]);
            scene.lookup("#"+message[1].trim()).setStyle("-fx-border-color: orange;");
            scene.lookup("#"+message[2].trim()).setStyle("-fx-border-color: orange;");
        } catch(Exception ex) {
            errorField.setText(ex.getMessage());
        }
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
