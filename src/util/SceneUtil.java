/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author Pat
 */
public class SceneUtil {

    public SceneUtil() {
    }
    
    public Stage changeScene(ActionEvent event, String SceneFXML) throws IOException {
        Parent parentNode = FXMLLoader.load(getClass().getResource(SceneFXML));
        javafx.scene.Scene parentScene = new javafx.scene.Scene(parentNode);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(parentScene);
        return stage;
    }
}
