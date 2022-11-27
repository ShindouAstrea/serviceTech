/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author josep
 */
public class ConfirmacionController implements Initializable {

    @FXML
    private Button btnSí;
    @FXML
    private Button btnNo;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    // Confirmacion de cierre de sesion .
    @FXML
    private void btnSí_click(ActionEvent event) throws IOException {
      
              System.exit(0);

    }
    // Volver a las opciones del programa
    @FXML
    private void btnNo_click(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
}
