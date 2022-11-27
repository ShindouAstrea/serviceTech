/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import BaseDeDatos.ConexionBaseDeDatos;
import BaseDeDatos.ConsultasBaseDeDatos;
import Clases.Local;
import Clases.Reparacion;
import Clases.ServicioTecnico;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import Clases.Cliente;
import Clases.Persona;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author mdiaz
 */
public class VistaReparacionController implements Initializable {

    private ServicioTecnico sv;
    private Reparacion rep ;
    private String ID;
    private ConsultasBaseDeDatos consultas;
    private ConexionBaseDeDatos enlace;
    private Cliente cl;
    private Persona p ;

    @FXML
    private TableView<Reparacion> ListaReparacion;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtRut;
    @FXML
    private ChoiceBox<String> ChoiceTipoEquipo;
    @FXML
    private DatePicker FechaIngreso;
    @FXML
    private TextArea Comentario;
    @FXML
    private TableColumn<Reparacion, String> collIDRegistro;
    @FXML
    private TableColumn<Reparacion, String> colRut;
    @FXML
    private TableColumn<Reparacion, String> ColTipoEquipo;
    @FXML
    private TableColumn<Reparacion, Date> colFechaIngreso;
    @FXML
    private TableColumn<Reparacion, String> colObservacion;
    @FXML
    private Button btnGuardar;
    @FXML
    private ChoiceBox<String> choiceboxLocales;
    @FXML
    private TableColumn<Reparacion, String> ColLocales;
    @FXML
    private TableColumn<Reparacion, String> colEstado;
    @FXML
    private ChoiceBox<String> choiceboxEstado;
    @FXML
    private Button btnFiltro;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private Button limpFiltros;
    @FXML
    private ComboBox<String> comboBoxBusqueda;

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
        FechaIngreso.setValue(LocalDate.now());
        p = new Persona(){  
        };
        txtBusqueda.setVisible(false);
        //Se lee archivo que contiene la informacion de las reparaciones
        try {
            sv.leerRepExcel();
        } catch (IOException ex) {
            Logger.getLogger(VistaReparacionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            llenarChoiceboxLocal();
        } catch (IOException ex) {
            Logger.getLogger(VistaEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        llenarComboBoxFiltro();
        llenarChoiceBoxTipo();

        collIDRegistro.setCellValueFactory(new PropertyValueFactory<>("IDregistro"));
        colRut.setCellValueFactory(new PropertyValueFactory<>("rutCliente"));
        ColTipoEquipo.setCellValueFactory(new PropertyValueFactory<>("tipoEquipo"));
        ColLocales.setCellValueFactory(new PropertyValueFactory<>("Local"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngresa"));
        colObservacion.setCellValueFactory(new PropertyValueFactory<>("Comentario"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("Estado"));

        ListaReparacion.setItems(FXCollections.observableArrayList(sv.obtenerReparacion()));

        btnGuardar.setVisible(false);

    }
    // Vuelve al menu opciones
    @FXML
    private void btnVolverClick(ActionEvent event) throws IOException {
        sv.guardarExReparacion(sv.obtenerReparacion());
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
    // Agrega una nueva reparacion
    @FXML
    private void btnAbregarClick(ActionEvent event) {

        ID = sv.randomID();
        if (sv.ExisteReparacion(ID)) {
            ID = sv.randomID();
        }
        String rut = txtRut.getText();
        cl = new Cliente(null, null, null, null, null, rut);
        //Se valida que el rut sea correcto
        if (cl.validarRut(rut)) {
            //Se valida que el rut pertenezca a un cliente existente , sino dira que hay que agregarlo.
            if (consultas.buscarCl(rut, enlace.obtenerEnlace()) != true) {

                JOptionPane.showMessageDialog(null, "Cliente no agregado , asegurese de agregarlo primero");
                rut = null;
                txtRut.clear();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Rut invalido , asegurese que este bien escrito");
        }

        String tipo = null;
        if (ChoiceTipoEquipo.getValue() != null) {
            tipo = ChoiceTipoEquipo.getValue();
        }

        String local = null;
        if (choiceboxLocales.getValue() != null) {
            local = choiceboxLocales.getValue();
        }

        String fecha = null;
        if (FechaIngreso.getValue() != null) {
            fecha = sv.conversion(FechaIngreso.getValue());
        }

        String observacion = Comentario.getText();

        String estado = null;
        if (choiceboxEstado.getValue() != null) {
            estado = choiceboxEstado.getValue();
        }
        //Se valida que los campos esten completos
        if (rut != null && tipo != null && local != null && estado != null && fecha != null) {
            consultas.insertarRegReparacion(ID, rut, tipo, local, fecha, observacion, estado, enlace.obtenerEnlace());
            Reparacion lista = new Reparacion(ID, rut, tipo, local, fecha, observacion, estado);
            sv.addReparacion(lista);
        } else {
            limpiar();
            JOptionPane.showMessageDialog(null, "Por favor verifique que los campos obligatorios  esten completos");
        }
        ListaReparacion.setItems(FXCollections.observableArrayList(sv.obtenerReparacion()));
        limpiar();
    }
    // selecciona una reparacion a modificar
    @FXML
    private void btnModificarClick(ActionEvent event) {
        Reparacion repElegida = ListaReparacion.getSelectionModel().getSelectedItem();
        txtRut.setText(repElegida.getRutCliente());
        Comentario.setText(repElegida.getComentario());
        ChoiceTipoEquipo.setValue(repElegida.getTipoEquipo()) ;
        //Se obtiene el string de la reparacion elegida y se invoca a los metodos para sacar dia mes y año
        int d = sv.stringADia(repElegida.getFechaIngresa()) ;
        int a = sv.stringAYear(repElegida.getFechaIngresa()) ;
        int m = sv.stringAMes(repElegida.getFechaIngresa()) ;
        System.out.println("Dia : " + d) ;
        System.out.println("Mes : " +m) ;
        System.out.println("Años : " + a) ;
        FechaIngreso.setValue(LocalDate.of(a, m, d)) ;//Se ajusta la fecha a la que tenia la reparacion seleccionada
        
        
        choiceboxEstado.setValue(repElegida.getEstado());
        choiceboxLocales.setValue(repElegida.getLocal());
        ID = repElegida.getIDregistro();
        sv.eliminarReparacion(repElegida.getIDregistro());
        ListaReparacion.setItems(FXCollections.observableArrayList(sv.obtenerReparacion()));
        btnEliminar.setVisible(false) ;
        btnModificar.setVisible(false) ;
        btnGuardar.setVisible(true);
        btnAgregar.setVisible(false);
    }
    // elmina una reparacion seleccionada
    @FXML
    private void btnEliminarClick(ActionEvent event) {
        Reparacion repElegido = ListaReparacion.getSelectionModel().getSelectedItem();
        consultas.eliminarRegReparacion(repElegido.getIDregistro(), enlace.obtenerEnlace());
        sv.eliminarReparacion(repElegido.getIDregistro());
        ListaReparacion.setItems(FXCollections.observableArrayList(sv.obtenerReparacion()));
    }
    // guara la reparacion modificada
    @FXML
    private void btnGuardarClick(ActionEvent event) {
        String rut = txtRut.getText();
        cl = new Cliente(null, null, null, null, null, rut);
        if (cl.validarRut(rut)) {
            //Se valida que el rut pertenezca a un cliente existente , sino dira que hay que agregarlo.
            if (consultas.buscarCl(rut, enlace.obtenerEnlace()) != true) {

                JOptionPane.showMessageDialog(null, "Cliente no agregado , asegurese de agregarlo primero");
                rut = null;
                txtRut.clear();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Rut invalido , asegurese que este bien escrito");
        }
          String tipo = null;
        if (ChoiceTipoEquipo.getValue() != null) {
            tipo = ChoiceTipoEquipo.getValue();
        }

        String local = null;
        if (choiceboxLocales.getValue() != null) {
            local = choiceboxLocales.getValue();
        }

        String fecha = null;
        if (FechaIngreso.getValue() != null) {
            fecha = sv.conversion(FechaIngreso.getValue());
        }

        String observacion = Comentario.getText();

        String estado = null;
        if (choiceboxEstado.getValue() != null) {
            estado = choiceboxEstado.getValue();
        }
        //Se valida que los campos esten completos
        if (rut != null && tipo != null && local != null && estado != null && fecha != null) {
            consultas.modificarRegReparacion(ID, rut, tipo, local, fecha, observacion, estado, enlace.obtenerEnlace());
            Reparacion lista = new Reparacion(ID, rut, tipo, local, fecha, observacion, estado);
            sv.addReparacion(lista);
        } else {
            limpiar();
            JOptionPane.showMessageDialog(null, "Por favor verifique que los campos obligatorios  esten completos");
        }   

        ListaReparacion.setItems(FXCollections.observableArrayList(sv.obtenerReparacion()));
        limpiar();
        FechaIngreso.setValue(LocalDate.now()) ;
        btnEliminar.setVisible(true);
        btnModificar.setVisible(true) ;
        btnGuardar.setVisible(false);
        btnAgregar.setVisible(true);
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
            choiceboxLocales.getItems().add(lista.get(i).getNombreLocal());

        }
    }

    /**
     *
     */
    public void llenarChoiceBoxTipo() {

        ChoiceTipoEquipo.getItems().add("NoteBook");
        ChoiceTipoEquipo.getItems().add("Pc Escritorio");
        ChoiceTipoEquipo.getItems().add("Impresora");
        ChoiceTipoEquipo.getItems().add("Scanner");

        choiceboxEstado.getItems().add("Pendiente");
        choiceboxEstado.getItems().add("Revisado");
        choiceboxEstado.getItems().add("Despachado");

    }

    /**
     *Agrega las opciones de filtro de busqueda
     */
    public void llenarComboBoxFiltro() {
        comboBoxBusqueda.getItems().add("Reparaciones Listas");
        comboBoxBusqueda.getItems().add("Reparaciones Pendientes");
        comboBoxBusqueda.getItems().add("Rut del Cliente");
        comboBoxBusqueda.getItems().add("Fecha de ingreso");

    }
    /**Limpia los campos de texto
     *
     */
    private void limpiar() {
        txtRut.clear();
        Comentario.clear();
    }

    @FXML
    //Metodos de filtrado de busqueda por rut , fecha de ingreso y estado de reparacion pendiente y listo.
    private void FiltrarConjunto(ActionEvent event) {
        String tipoBusqueda = comboBoxBusqueda.getValue();
        if (tipoBusqueda != null) {
            if (tipoBusqueda.equals("Rut del Cliente")) {
                //Se valida que el rut este escrito de manera correcta y que pertenezca a un cliente
                if (txtBusqueda.getText() != null && p.validarRut(txtBusqueda.getText())&& p.validarFormatoRut(txtBusqueda.getText())) {
                    if(consultas.buscarCl(txtBusqueda.getText(),enlace.obtenerEnlace())){
                        String rut = txtBusqueda.getText();
                        txtBusqueda.clear();
                        ListaReparacion.setItems(FXCollections.observableArrayList(sv.obtenerRepCliente(rut)));
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Cliente no encontrado , asegurese que este ingresado");
                        txtBusqueda.clear();
                        
                    }
                } else {
                   
                    JOptionPane.showMessageDialog(null, "Ingrese Rut del Cliente correctamente , ademas sin puntos.");
                }

            } else {
                if (tipoBusqueda.equals("Fecha de ingreso")) {
                    
                    String fecha = txtBusqueda.getText();
                   
                    rep = new  Reparacion(null , null ,null ,null ,fecha,null,null);
                    //Se valida que la fecha sea en el formato dispuesto como dd-mm-aaaa
                    if(rep.formatoFecha(fecha)){
                        txtBusqueda.clear() ;
                        ListaReparacion.setItems(FXCollections.observableArrayList(sv.obtenerPorFecha(fecha)));
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Formato de fecha incorrecto escriba la fecha como dd-mm-aaaa");
                    }

                } else {
                    if (tipoBusqueda.equals("Reparaciones Pendientes")) {
                        txtBusqueda.setVisible(false);
                        ListaReparacion.setItems(FXCollections.observableArrayList(sv.obtenerRepPendientes()));
                    } else {
                        if (tipoBusqueda.equals("Reparaciones Listas")) {
                            txtBusqueda.setVisible(false);
                            ListaReparacion.setItems(FXCollections.observableArrayList(sv.obtenerRepListas()));
                        }

                    }
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un criterio de busqueda");
        }
    }

    @FXML
    private void filtroSelected(ActionEvent event) {
        String tipoBusqueda = comboBoxBusqueda.getValue();
        if (tipoBusqueda.equals("Rut del Cliente")) {
            txtBusqueda.setVisible(true);
            txtBusqueda.setPromptText("Rut sin puntos");
        } else {
            if (tipoBusqueda.equals("Fecha de ingreso")) {
                txtBusqueda.setPromptText("Formato DD-MM-AAAA");
                txtBusqueda.setVisible(true);
            } else {
                txtBusqueda.setVisible(false);
            }

        }
    }
    // limpia los filtro de busqueda
    @FXML
    private void limpFiltros(ActionEvent event) {
        ListaReparacion.setItems(FXCollections.observableArrayList(sv.obtenerReparacion()));
        txtBusqueda.setVisible(false);
        comboBoxBusqueda.setValue("");

    }

}
