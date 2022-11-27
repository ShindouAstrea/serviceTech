/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import BaseDeDatos.*;
import Clases.Persona ;
import Clases.ServicioTecnico;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mdiaz
 */
public class LoginController implements Initializable {

    private final ServicioTecnico sv = new ServicioTecnico();
    private Persona p = new Persona() {
        
    };

    /**
     * Conexion de base de datos
     *
     */
    public final ConexionBaseDeDatos enlace = new ConexionBaseDeDatos();
    private final ConsultasBaseDeDatos consultas = new ConsultasBaseDeDatos();

    @FXML
    private AnchorPane login;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private TextField txtUsuario;
    @FXML
    private Button bttlogin;
    @FXML
    private Button btnsalir;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    // Boton inicio secion
    @FXML
    private void bttlogin_click(ActionEvent event) throws IOException {

        String rut = txtUsuario.getText();
        String pass = txtContraseña.getText();
       
        if(p.validarFormatoRut(rut)){
            
            
        
        
            if (consultas.verificacionLogeo(rut, pass, enlace.obtenerEnlace())) {

                enlace.establecerConexion(); //Se conecta al servidor 
                sv.guardarExRegistroUsuario(rut, pass);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                Parent root = FXMLLoader.load(getClass().getResource("../vistas/opciones.fxml"));
                Scene scene = new Scene(root);

                stage.setOnCloseRequest((WindowEvent event1) -> {
                    Platform.exit();
                    System.exit(0);
                });

                stage.setScene(scene);
                stage.show();

                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos ");
                txtUsuario.clear();
                txtContraseña.clear();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Usuario es rut sin puntos.") ;
            txtUsuario.clear();
            txtContraseña.clear();
        }

    }
    
    //Boton salir del programa.
    @FXML
    private void btnsalir_click(ActionEvent event) {

        System.exit(0);
    }
}
