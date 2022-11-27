package Controladores;

import BaseDeDatos.ConexionBaseDeDatos;
import BaseDeDatos.ConsultasBaseDeDatos;
import Clases.ServicioTecnico;
import Clases.Empleado;
import Clases.Local;
import Clases.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mdiaz
 */
public class VistaEmpleadoController implements Initializable {

    private ServicioTecnico sv;
    private ConsultasBaseDeDatos consultas;
    private ConexionBaseDeDatos enlace;
    private String nombreLocalAux; //Sirve para copiar el nnombre del local antes de cambiarlo
    private Persona p;

    @FXML
    private Button btnnagregarempleado;
    @FXML
    private Button btnModificarEmp;
    @FXML
    private Button btnEliminarEmp;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtContraseña;
    @FXML
    private TextField txtRut;
    @FXML
    private TableView<Empleado> listaEmpleados;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtnombre;
    @FXML
    private ComboBox<String> comboboxAdmin;
    @FXML
    private TableColumn<Empleado, String> colNombre;
    @FXML
    private TableColumn<Empleado, String> colApellido;
    @FXML
    private TableColumn<Empleado, String> colRut;
    @FXML
    private TableColumn<Empleado, String> colLocal;
    @FXML
    private TableColumn<Empleado, String> colContraseña;
    @FXML
    private TableColumn<Empleado, String> colAdministrador;
    @FXML
    private ChoiceBox<String> comboboxLocales;

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
        consultas = new ConsultasBaseDeDatos();
        enlace = new ConexionBaseDeDatos();
        JOptionPane.showMessageDialog(null, "Edicion Habilidata");

        try {
            sv.leerEmExcel();

        } catch (IOException ex) {
            Logger.getLogger(VistaEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboboxAdmin.getItems().add("si");
        comboboxAdmin.getItems().add("no");

        try {
            llenarChoiceboxLocal();
        } catch (IOException ex) {
            Logger.getLogger(VistaEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colRut.setCellValueFactory(new PropertyValueFactory<>("rut"));
        colContraseña.setCellValueFactory(new PropertyValueFactory<>("contraseña"));
        colLocal.setCellValueFactory(new PropertyValueFactory<>("idLocal"));
        colAdministrador.setCellValueFactory(new PropertyValueFactory<>("administrador"));

        listaEmpleados.setItems(FXCollections.observableArrayList(sv.obtenerEmpleado()));

        btnGuardar.setVisible(false);
    }
    // Agrega un nuevo empleado
    @FXML
    private void btnnagregaEMple_click(ActionEvent event) {
        String nombre = txtnombre.getText();
        String apellido = txtApellido.getText();
        String rut = txtRut.getText();
        String contraseña = txtContraseña.getText();
        String idLocal = comboboxLocales.getValue();
        String admin = comboboxAdmin.getValue();

        if (validarCamposLlenos(nombre, apellido, rut, contraseña, idLocal, admin)) {

            String id_local = obtenerIdLocal(idLocal);
            consultas.insertarEmpleado(nombre, apellido, rut, id_local, contraseña, admin, enlace.obtenerEnlace());

            Empleado nuevoEmpleado = new Empleado(nombre, apellido, rut, contraseña, idLocal, admin);
            sv.addEmpleado(nuevoEmpleado);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor rellene los campos y/o ingrese correctamente el rut sin puntos");
        }

        listaEmpleados.setItems(FXCollections.observableArrayList(sv.obtenerEmpleado()));
        limpiar();
    }
    // Seleeciona el empleado a modificar
    @FXML
    private void btnmodificarEmp_click(ActionEvent event) {
        Empleado emElegido = listaEmpleados.getSelectionModel().getSelectedItem();
        txtnombre.setText(emElegido.getNombre());
        txtApellido.setText(emElegido.getApellido());
        txtRut.setText(emElegido.getRut());
        txtContraseña.setText(emElegido.getContraseña());
        comboboxLocales.setValue(emElegido.getIdLocal()) ;
        comboboxAdmin.setValue(emElegido.getAdministrador());
        nombreLocalAux = emElegido.getIdLocal();
        sv.eliminarEmp(emElegido.getRut());
        listaEmpleados.setItems(FXCollections.observableArrayList(sv.obtenerEmpleado()));
        btnGuardar.setVisible(true);
         btnEliminarEmp.setVisible(false) ;
        btnModificarEmp.setVisible(false) ;
        btnnagregarempleado.setVisible(false);
    }
    // Elimina el empleado elegido
    @FXML
    private void btnEliminarEmp_click(ActionEvent event) {
        Empleado emElegido = listaEmpleados.getSelectionModel().getSelectedItem();
        //verifica si es el ultimo empleado , en caso de serlo no se borra ya que no se podria ingresar denuevo.
        if(consultas.esUltimoEmpleado( enlace.obtenerEnlace())!=true){
             consultas.eliminarEmpleado(emElegido.getRut(), enlace.obtenerEnlace());
            sv.eliminarEmp(emElegido.getRut());
        }
        else JOptionPane.showMessageDialog(null,"Imposible eliminar , unico empleado") ;
        listaEmpleados.setItems(FXCollections.observableArrayList(sv.obtenerEmpleado()));
    }
    // Guarda el empleado modificado
    @FXML
    private void btnGuardarClick(ActionEvent event) {

        String nombre = txtnombre.getText();
        String apellido = txtApellido.getText();
        String rut = txtRut.getText();
        String contraseña = txtContraseña.getText();
        String idLocal = comboboxLocales.getValue(); // En realidad es el nombre del local
        String admin = comboboxAdmin.getValue();
        if (validarCamposLlenos(nombre, apellido, rut, contraseña, idLocal, admin)) {
            consultas.modificarEmpleado(nombre, apellido, rut, obtenerIdLocal(nombreLocalAux), contraseña, admin, enlace.obtenerEnlace());
            Empleado nuevoEmpleado = new Empleado(nombre, apellido, rut, contraseña, idLocal, admin);
            sv.addEmpleado(nuevoEmpleado);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos y/o ingrese correctamente el rut sin puntos");
        }
        listaEmpleados.setItems(FXCollections.observableArrayList(sv.obtenerEmpleado()));
        btnGuardar.setVisible(false);
        btnEliminarEmp.setVisible(true) ;
        btnModificarEmp.setVisible(true) ;
        btnnagregarempleado.setVisible(true);
        limpiar();

    }
    // Vuelve al menu opciones
    @FXML
    private void btnVolver_click(ActionEvent event) throws IOException {
        sv.guardarExEM(sv.obtenerEmpleado());
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
        txtnombre.clear();
        txtApellido.clear();
        txtRut.clear();
        txtContraseña.clear();
    }

    /** 
     *
     * @throws IOException
     */
    public void llenarChoiceboxLocal() throws IOException {
        try {
            sv.leerLcExcel();
        } catch (IOException ex) {
            Logger.getLogger(VistaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Local> lista = sv.obtenerLocal();

        for (int i = 0; i < lista.size(); i++) {
            comboboxLocales.getItems().add(lista.get(i).getNombreLocal());

        }
    }

    /** Valida que los campos de textos tengan contenido.
     *
     * @param nombre
     * @param apellido
     * @param rut
     * @param contraseña
     * @param idLocal
     * @param admin
     * @return
     */
    public boolean validarCamposLlenos(String nombre, String apellido, String rut, String contraseña, String idLocal, String admin) {
        if (nombre.isEmpty() != true) {
            if (apellido.isEmpty() != true) {
                if (rut.isEmpty() != true && p.validarRut(rut)&& p.validarFormatoRut(rut)) {
                    if (contraseña.isEmpty() != true) {
                        if (idLocal.isEmpty() != true) {
                            if (admin.isEmpty() != true) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /** Obtiene el id local
     *
     * @param nombreLocal
     * @return
     */
    public String obtenerIdLocal(String nombreLocal) {
        ArrayList<Local> lista = sv.obtenerLocal();
        for (int i = 0; lista.get(i).getNombreLocal().equals(nombreLocal); i++) {
            return lista.get(i).getIDLocal();

        }
        return null;
    }
}
