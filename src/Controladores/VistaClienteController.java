package Controladores;

import Clases.Cliente;
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
import Clases.Persona;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mdiaz
 */
public class VistaClienteController implements Initializable {

    private ServicioTecnico sv;
    private ConsultasBaseDeDatos consultas;
    private ConexionBaseDeDatos enlace;
    private Persona p;

    @FXML
    private TableView<Cliente> listaCLiente;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtRut;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtDireecion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TableColumn<Cliente, String> ColNombre;
    @FXML
    private TableColumn<Cliente, String> ColApellido;
    @FXML
    private TableColumn<Cliente, String> ColRut;
    @FXML
    private TableColumn<Cliente, String> ColCorreo;
    @FXML
    private TableColumn<Cliente, String> ColDireecion;
    @FXML
    private TableColumn<Cliente, String> ColTelefono;
    @FXML
    private Button btnvolver;
    @FXML
    private Button btnagregar;
    @FXML
    private Button btneliminar;
    @FXML
    private Button btnmodificar;
    @FXML
    private Button btnguardar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sv = new ServicioTecnico();
        p = new Persona() {
        };
        enlace = new ConexionBaseDeDatos();
        consultas = new ConsultasBaseDeDatos();
        Connection enladeDeConexion = enlace.obtenerEnlace();

        try {
            sv.leerClExcel();
        } catch (IOException ex) {
            Logger.getLogger(VistaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        ColNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ColRut.setCellValueFactory(new PropertyValueFactory<>("rut"));
        ColCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        ColDireecion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        ColTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        listaCLiente.setItems(FXCollections.observableArrayList(sv.obtenerCLientes()));

        btnguardar.setVisible(false);

    }
    // Agrega un nuevo cliente a la tabla
    @FXML
    private void btnnagregacliente_click(ActionEvent event) {

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String rut = txtRut.getText();
        String correo = txtCorreo.getText();
        String direccion = txtDireecion.getText();
        String telefono = txtTelefono.getText();
        if(telefono.length()!=9){
            telefono = null ;
            txtTelefono.clear();
            JOptionPane.showMessageDialog(null,"Telefono solo puede ser de 9 digitos") ;
        }
        if (validarCamposLlenos(nombre, apellido, rut, correo, direccion, telefono)) {
            consultas.insertarCliente(nombre, apellido, rut, correo, direccion, telefono, enlace.obtenerEnlace());
            Cliente nuevoCLiente = new Cliente(correo, direccion, telefono, nombre, apellido, rut);
            sv.addCliente(nuevoCLiente);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos y/o ingrese correctamente el rut sin puntos");
        }
        listaCLiente.setItems(FXCollections.observableArrayList(sv.obtenerCLientes()));

        limpiar();

    }
    // Elimina el cliente seleccionado de la tabla.
    @FXML
    private void btnEliminarCL_click(ActionEvent event) {
        Cliente clElegido = listaCLiente.getSelectionModel().getSelectedItem();
        
        if(consultas.tieneRegistro(clElegido.getRut(), enlace.obtenerEnlace())!=true){
            consultas.eliminarCliente(clElegido.getRut(), enlace.obtenerEnlace());
            sv.eliminarCl(clElegido.getRut());
        }
        else JOptionPane.showMessageDialog(null,"Imposible borrar cliente , tiene 1 o más registros asociados") ;
        listaCLiente.setItems(FXCollections.observableArrayList(sv.obtenerCLientes()));
    }
    // Selecciona un cliente para modificar
    @FXML
    private void btnmodificarCL_click(ActionEvent event) {
        Cliente modCL = listaCLiente.getSelectionModel().getSelectedItem();
        txtNombre.setText(modCL.getNombre());
        txtApellido.setText(modCL.getApellido());
        txtRut.setText(modCL.getRut());
        txtCorreo.setText(modCL.getCorreo());
        txtDireecion.setText(modCL.getDireccion());
        txtTelefono.setText(modCL.getTelefono());

        sv.eliminarCl(modCL.getRut());
        listaCLiente.setItems(FXCollections.observableArrayList(sv.obtenerCLientes()));
        btneliminar.setVisible(false) ;
        btnmodificar.setVisible(false) ;
        btnagregar.setVisible(false);
        btnguardar.setVisible(true);

    }
    // Guarda el cliente modificado
    @FXML
    private void btnGuardarCL_click(ActionEvent event) {

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String rut = txtRut.getText();
        String correo = txtCorreo.getText();
        String direccion = txtDireecion.getText();
        String telefono = txtTelefono.getText();
        if(telefono.length()!=9){
            telefono = null ;
            txtTelefono.clear();
            JOptionPane.showMessageDialog(null,"Telefono solo puede ser de 9 digitos") ;
        }
        if(validarCamposLlenos(nombre, apellido, rut, correo, direccion, telefono)){
            consultas.modificarCliente(nombre, apellido, rut, correo, direccion, telefono, enlace.obtenerEnlace());

            Cliente nuevoCLiente = new Cliente(correo, direccion, telefono, nombre, apellido, rut);
            sv.addCliente(nuevoCLiente);
        }
        else {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos y/o ingrese correctamente el rut sin puntos");
            limpiar() ;
            
        }
        listaCLiente.setItems(FXCollections.observableArrayList(sv.obtenerCLientes()));
        btneliminar.setVisible(true);
        btnagregar.setVisible(true);
        btnmodificar.setVisible(true) ;
        btnguardar.setVisible(false);
        limpiar();
    }
    
    // Vuele a la vista opciones
    @FXML
    private void btnVolver_click(ActionEvent event) throws IOException {
        sv.guardarExCL(sv.obtenerCLientes());
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

    /** Limpia los campos de textos .
     *
     */
    public void limpiar() {
        txtNombre.clear();
        txtApellido.clear();
        txtRut.clear();
        txtCorreo.clear();
        txtDireecion.clear();
        txtTelefono.clear();
    }

    /** Valida que los campos de textos tengan contenido. Además se valida que el rut sea correcto.
     *
     * @param nombre
     * @param apellido
     * @param rut
     * @param correo
     * @param direccion
     * @param telefono
     * @return
     */
    public boolean validarCamposLlenos(String nombre, String apellido, String rut, String correo, String direccion, String telefono) {
        if (nombre.isEmpty() != true) {
            if (apellido.isEmpty() != true) {
                if (rut.isEmpty() != true && p.validarRut(rut)&& p.validarFormatoRut(rut)) {
                    if (correo.isEmpty() != true) {
                        if (direccion.isEmpty() != true) {
                            if (telefono.isEmpty() != true) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
