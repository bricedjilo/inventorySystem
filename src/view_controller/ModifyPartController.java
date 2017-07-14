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
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
public class ModifyPartController implements Initializable {

    private Stage stage;
    private Scene scene;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane modifyPart;

    @FXML
    private Button modifyPartCancel;

    @FXML
    private RadioButton addPartInHouse;
    
    @FXML
    private RadioButton modifyPartInHouse;

    @FXML
    private RadioButton modifyPartOutsourced;

    @FXML
    private Button modifyPartSave;
    
    @FXML
    private TextField machineIdCompNameField, partIdField, priceCostField, maxField, 
            minField, invField, nameField;
    
    @FXML Label machineIdCompNameLabel;
    
    @FXML
    private Text errorField;
    
    /**
     * Sets the stage of this dialog.
     * @param stage
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
    private void handleCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("You are about to stop this part modification");
        alert.setContentText("Are you ok with this? \n" +
            "If you press OK, changes will not be saved.\n");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                setStage((new SceneUtil()).changeScene(event, "/fxml/mainScreen.fxml"));
                stage.show();
            } catch (IOException ex) {
                System.err.println("From ModifyPartController, /fxml/mainScreen.fxml cannot be located/loaded.");
            }
        } 
    }

    @FXML
    private void handleInHouse(ActionEvent event) {
        machineIdCompNameLabel.setText("Machine ID");
        machineIdCompNameField.setPromptText("Machine ID");
        
        InhousePart inHouse = Inventory.getPreviousInhousePart();
        partIdField.setText(String.valueOf(inHouse.getPartID()));
        priceCostField.setText(String.valueOf(inHouse.getPrice()));
        maxField.setText(String.valueOf(inHouse.getMax())); 
        minField.setText(String.valueOf(inHouse.getMin())); 
        invField.setText(String.valueOf(inHouse.getInStock())); 
        nameField.setText(inHouse.getPartName());     
        machineIdCompNameField.setText(String.valueOf(inHouse.getMachineID()));
    }
    
    @FXML
    private void handleOutSourced(ActionEvent event) {
        machineIdCompNameLabel.setText("Company Name");
        machineIdCompNameField.setPromptText("Company Name");
        
        OutsourcedPart outsource = Inventory.getPreviousOutsourcedPart();
        partIdField.setText(String.valueOf(outsource.getPartID()));
        priceCostField.setText(String.valueOf(outsource.getPrice()));
        maxField.setText(String.valueOf(outsource.getMax())); 
        minField.setText(String.valueOf(outsource.getMin())); 
        invField.setText(String.valueOf(outsource.getInStock())); 
        nameField.setText(outsource.getPartName());     
        machineIdCompNameField.setText(outsource.getCompanyName());
    }

    @FXML
    private void handleModifyPartSave(ActionEvent event) throws IOException {
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
            
            if(modifyPartInHouse.isSelected()) {
                Validate.isPositiveInt(machineIdCompNameField.getText().trim(), "Machine ID", "machineIdCompNameField");
                machineIdCompNameField.setStyle("-fx-border-color: green;");
                Inventory.setPreviousInhousePart(new InhousePart(
                    Integer.parseInt(machineIdCompNameField.getText()), 
                    Integer.parseInt(partIdField.getText()), 
                    nameField.getText(), Double.parseDouble(priceCostField.getText()), 
                    Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), 
                    Integer.parseInt(maxField.getText())
                ));
                Inventory.setLastPartUpdated(Inventory.getPreviousInhousePart());
                Inventory.updatePart(Inventory.getPreviousInhousePart().getPartID());         
               
            } else {
                Validate.isAlphaNumeric(machineIdCompNameField.getText().trim(), "Company Name", "machineIdCompNameField");
                machineIdCompNameField.setStyle("-fx-border-color: green;");
                Inventory.setPreviousOutsourcedPart(new OutsourcedPart(
                    machineIdCompNameField.getText(), Integer.parseInt(partIdField.getText()), 
                    nameField.getText(), Double.parseDouble(priceCostField.getText()), 
                    Integer.parseInt(invField.getText()), Integer.parseInt(minField.getText()), 
                    Integer.parseInt(maxField.getText())
                ));
                Inventory.setLastPartUpdated(Inventory.getPreviousOutsourcedPart());
                Inventory.updatePart(Inventory.getPreviousOutsourcedPart().getPartID());

            }
            this.setStage((new SceneUtil()).changeScene(event, "/fxml/mainScreen.fxml"));
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
            System.out.println(ex.getMessage());
            errorField.setText(ex.getMessage());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InhousePart inHouse = Inventory.getPreviousInhousePart();
        partIdField.setText(String.valueOf(inHouse.getPartID()));
        priceCostField.setText(String.valueOf(inHouse.getPrice()));
        maxField.setText(String.valueOf(inHouse.getMax())); 
        minField.setText(String.valueOf(inHouse.getMin())); 
        invField.setText(String.valueOf(inHouse.getInStock())); 
        nameField.setText(inHouse.getPartName());     
        machineIdCompNameField.setText(String.valueOf(inHouse.getMachineID()));          
    }    
    
}
