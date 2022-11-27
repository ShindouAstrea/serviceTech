/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import BaseDeDatos.*;
import Clases.ServicioTecnico;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mdiaz
 */
public class OpcionesController implements Initializable {

    private ServicioTecnico sv;
    private ConexionBaseDeDatos enlace;
    private ConsultasBaseDeDatos consultas;
    String respaldoUsuario;

    @FXML
    private Button btnREgiCL;
    @FXML
    private Button btnRegiEmple;
    @FXML
    private Button btnReparaciones;
    @FXML
    private Button btnRegistroLocales;
    @FXML
    private Button btnCerrarSesion;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sv = new ServicioTecnico();
        consultas = new ConsultasBaseDeDatos();
        enlace = new ConexionBaseDeDatos();
    }
    
    // Botones para seleccionar las vistas 
    // Vista cliente
    @FXML
    private void btnRegiCl_click(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../vistas/vistaCliente.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setOnCloseRequest((WindowEvent event1) -> {
            Platform.exit();
            System.exit(0);
        });

        stage.setScene(scene);
        stage.show();

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    // Vista empleado
    @FXML
    private void btnRegEM_clic(ActionEvent event) throws IOException {
        try {
            respaldoUsuario = sv.leerRegUsuario();
        } catch (IOException em) {
        }
        if (consultas.permisosAdministrador(respaldoUsuario, enlace.obtenerEnlace())) {
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            Parent root = FXMLLoader.load(getClass().getResource("../vistas/vistaEmpleado.fxml"));
            Scene scene = new Scene(root);

            stage.setOnCloseRequest((WindowEvent event1) -> {
                Platform.exit();
                System.exit(0);
            });

            stage.setScene(scene);
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no habilitado para ver esta opcion");
        }

    }
    // Vista reparaciones
    @FXML
    private void btnReparaciones_click(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("../vistas/vistaReparacion.fxml"));
        Scene scene = new Scene(root);

        stage.setOnCloseRequest((WindowEvent event1) -> {
            Platform.exit();
            System.exit(0);
        });

        stage.setScene(scene);
        stage.show();

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    // Vista locales
    @FXML
    private void btnRegistroLocales_click(ActionEvent event) throws IOException {
        try {
            respaldoUsuario = sv.leerRegUsuario();
        } catch (IOException em) {
        }
        if (consultas.permisosAdministrador(respaldoUsuario, enlace.obtenerEnlace())) {

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            Parent root = FXMLLoader.load(getClass().getResource("../vistas/VistaLocales.fxml"));
            Scene scene = new Scene(root);

            stage.setOnCloseRequest((WindowEvent event1) -> {
                Platform.exit();
                System.exit(0);
            });

            stage.setScene(scene);
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            JOptionPane.showMessageDialog(null, " Usuario no habilitado para ver esta opcion");
        }
    }
    
    // Salir del programa
    // Vista confirmacion
    @FXML
    private void btnCerrarSesion_click(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("../vistas/Confirmacion.fxml"));
        Scene scene = new Scene(root);

        stage.setOnCloseRequest((WindowEvent event1) -> {
            Platform.exit();
            System.exit(0);
        });

        stage.setScene(scene);
        stage.show();

    }
}
