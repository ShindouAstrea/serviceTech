/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.Local;
import Clases.ServicioTecnico;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import BaseDeDatos.*;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mdiaz
 */
public class VistaLocalesController implements Initializable {

    private ServicioTecnico sv;
    private ConsultasBaseDeDatos consultas;
    private ConexionBaseDeDatos enlace;
    private String id;
    String alfa = "L";
    String beta = "C";

    @FXML
    private TableView<Local> ListaLocales;
    @FXML
    private TextField txtNombreLc;
    @FXML
    private TextField txtRegionLc;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnVolver;
    @FXML
    private Button bntEliminar;
    @FXML
    private Button btnGuardar;
    @FXML
    private TableColumn<Local, String> colIDlocal;
    @FXML
    private TableColumn<Local, String> colNombre;
    @FXML
    private TableColumn<Local, String> colRegion;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        enlace = new ConexionBaseDeDatos();
        consultas = new ConsultasBaseDeDatos();
        sv = new ServicioTecnico();
       

        //Mensaje para indicarle al empleado que tiene los permisos para editar y ver informacion confidencial.
        JOptionPane.showMessageDialog(null, "Edicion Habilidata");

  
        //Se intenta leer el archivo con los datos de los locales.
        try {
            sv.leerLcExcel();

        } catch (IOException ex) {
            Logger.getLogger(VistaLocalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Local lc = new Local ("LC137","Nibble Galeria Cristal","Valparaiso") ;
        //sv.addLocal(lc) ;
        colIDlocal.setCellValueFactory(new PropertyValueFactory<>("IDLocal"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreLocal"));
        colRegion.setCellValueFactory(new PropertyValueFactory<>("region"));

        ListaLocales.setItems(FXCollections.observableArrayList(sv.obtenerLocal()));

        btnGuardar.setVisible(false);
    }
    // agrega un nuevo local
    @FXML
    private void btnAgregarClick(ActionEvent event) {

        id = sv.randomID(alfa, beta);
        if (sv.ExisteLocal(id)) {
            id = sv.randomID(alfa, beta);

        }

        String nombre = txtNombreLc.getText();
        System.out.println("Nombre local :" + nombre);
        String region = txtRegionLc.getText();
        System.out.println("Region : "+ region) ;
        if(nombre!=null && region != null){
            consultas.insertarLocal(id, nombre, region, enlace.obtenerEnlace());
            Local nuevoLocal = new Local(id, nombre, region);
            sv.addLocal(nuevoLocal);
        }
        else{
            JOptionPane.showMessageDialog(null,"Por favor rellene los campos") ;
            limpiar() ;
        }
        ListaLocales.setItems(FXCollections.observableArrayList(sv.obtenerLocal()));
        limpiar();
    }
    //selecciona un local a modificar
    @FXML
    private void btnModificaClick(ActionEvent event) {
        Local localElegido = (Local) ListaLocales.getSelectionModel().getSelectedItem();
        txtNombreLc.setText(localElegido.getNombreLocal());
        txtRegionLc.setText(localElegido.getRegion());
        id = localElegido.getIDLocal();
        sv.eliminarLocal(localElegido.getIDLocal());
        ListaLocales.setItems(FXCollections.observableArrayList(sv.obtenerLocal()));
        btnAgregar.setVisible(false);
        btnGuardar.setVisible(true);
        btnModificar.setVisible(false) ;
        bntEliminar.setVisible(false) ;
    }
    // ELIMINA UN LOCAL SELECCIONADO
    @FXML
    private void btnEliminarClick(ActionEvent event) {
        Local localElegido = ListaLocales.getSelectionModel().getSelectedItem();
        if(consultas.hayEmpleados(localElegido.getIDLocal(), enlace.obtenerEnlace())!=true) {
            consultas.eliminarLocal(localElegido.getIDLocal(), enlace.obtenerEnlace());
            sv.eliminarLocal(localElegido.getIDLocal());
        }
        else {
            JOptionPane.showMessageDialog(null,"Imposible Eliminar local , posee empleados ") ;
        } 
        ListaLocales.setItems(FXCollections.observableArrayList(sv.obtenerLocal()));
    }
    // guarda un local modificado
    @FXML
    private void btnGuardarClick(ActionEvent event) {
     
        String nombre = txtNombreLc.getText();
        String region = txtRegionLc.getText();
       
        if(nombre!=null && region!=null){
            consultas.modificarLocal(id, nombre, region, enlace.obtenerEnlace());
            Local nuevoLocal = new Local(id, nombre, region);
            sv.addLocal(nuevoLocal);
        }
        else{
            JOptionPane.showMessageDialog(null,"Por favor llene los campos obligatorios") ;
            limpiar();
        }
        ListaLocales.setItems(FXCollections.observableArrayList(sv.obtenerLocal()));
        btnModificar.setVisible(true ) ;
        btnAgregar.setVisible(true);
        bntEliminar.setVisible(true) ;
        btnGuardar.setVisible(false) ;
        limpiar();

    }
    // vuelve al menu opciones
    @FXML
    private void btnVolverClick(ActionEvent event) throws IOException {
        sv.guardarExLc(sv.obtenerLocal());
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
    }

    /** Limpia los campos de texto.
     *
     */
    public void limpiar() {
        txtNombreLc.clear();
        txtRegionLc.clear();
    }
    
}
