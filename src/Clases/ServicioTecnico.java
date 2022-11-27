/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author mdiaz
 */
public class ServicioTecnico {

    /* declaracion de mapas */
    private HashMap<String, Cliente> clientes;
    private HashMap<String, Empleado> empleados;
    private HashMap<String, Local> locales;
    private HashMap<String, Reparacion> reparaciones;
    private HashMap<String, Reparacion> reparacionesListas;
    private HashMap<String, Reparacion> reparacionesCliente;
    private HashMap<String, Reparacion> reparacionesFecha;
    private HashMap<String, Reparacion> reparacionesPendientes;

    /**
     * Contructor
     *
     */
    public ServicioTecnico() {
        clientes = new HashMap<>();
        empleados = new HashMap<>();
        locales = new HashMap<>();
        reparaciones = new HashMap<>();
        reparacionesListas = new HashMap<>();
        reparacionesCliente = new HashMap<>();
        reparacionesFecha = new HashMap<>();
        reparacionesPendientes = new HashMap<>();
    }


    /* metodos cliente*/
    //almacena el cliente en el mapa
    /**
     *
     * @param nuevoCliente
     */
    public void addCliente(Cliente nuevoCliente) {

        if (!clientes.containsKey(nuevoCliente.getRut())) {
            clientes.put(nuevoCliente.getRut(), nuevoCliente);
        }

    }

    //obtengo al cliente segun el rut buscado
    /**
     *
     * @param rut
     * @return
     */
    public Cliente buscarCL(String rut) {

        if (clientes.containsKey(rut)) {
            return clientes.get(rut);
        }
        return null;
    }

    //elimina el cliente del mapa segun el rut ingresado
    /**
     *
     * @param rut
     */
    public void eliminarCl(String rut) {

        if (clientes.containsKey(rut)) {
            clientes.remove(rut);
        }
    }

    /**
     *
     * @param rut
     * @return
     */
    public boolean ExisteCl(String rut) {
        return clientes.containsKey(rut);
    }

    //retorno el mapa en una arraylist
    /**
     *
     * @return
     */
    public ArrayList obtenerCLientes() {

        ArrayList<Cliente> lista = new ArrayList<>(clientes.values());
        return lista;
    }

    //metodos Empleado
    /**
     *
     * @param nuevoEmpleado
     */
    public void addEmpleado(Empleado nuevoEmpleado) {

        if (!empleados.containsKey(nuevoEmpleado.getRut())) {
            empleados.put(nuevoEmpleado.getRut(), nuevoEmpleado);
        }

    }

    /**
     *
     * @param rut
     * @return
     */
    public Empleado buscarEmp(String rut) {

        if (empleados.containsKey(rut)) {
            return empleados.get(rut);
        }
        return null;
    }

    /**
     *
     * @param rut
     */
    public void eliminarEmp(String rut) {

        if (empleados.containsKey(rut)) {
            empleados.remove(rut);
        }
    }

    /**
     *
     * @return
     */
    public ArrayList obtenerEmpleado() {

        ArrayList<Empleado> lista = new ArrayList<>(empleados.values());
        return lista;
    }

    // Metodos Locales
    /**
     *
     * @param nuevoLocal
     */
    public void addLocal(Local nuevoLocal) {

        if (!locales.containsKey(nuevoLocal.getIDLocal())) {
            locales.put(nuevoLocal.getIDLocal(), nuevoLocal);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Local buscarLocal(String id) {
        if (locales.containsKey(id)) {
            return locales.get(id);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean ExisteLocal(String id) {
        return locales.containsKey(id);
    }

    /**
     *
     * @param id
     */
    public void eliminarLocal(String id) {
        if (locales.containsKey(id)) {
            locales.remove(id);
        }
    }

    /**
     *
     * @return
     */
    public ArrayList obtenerLocal() {

        ArrayList<Local> lista = new ArrayList<>(locales.values());
        return lista;
    }

    /**
     * Genera un id aleatorio para Locales
     *
     * @param a
     * @param b
     * @return
     */
    public String randomID(String a, String b) {

        Random aleatoreo = new Random();
        String alfa = a;
        String beta = b;
        String cadena = "";
        int numero;
        int forma;
        int forma2;

        forma = (int) (aleatoreo.nextDouble() * alfa.length());
        forma2 = (int) (aleatoreo.nextDouble() * beta.length());

        numero = (int) (aleatoreo.nextDouble() * 99 + 100);
        cadena = cadena + alfa.charAt(forma) + beta.charAt(forma2) + numero;
        return cadena;

    }
    //~~~~~~~~~~~~~~~~~~~~~~~Metodos de conversion de cadena a entero~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   public int stringADia(String Fecha) {
       int valor  ;
       Fecha  = Fecha.replace("-","") ;
       valor = Integer.parseInt(Fecha) / 1000000  ;
       return valor;
   }
   public int stringAMes (String Fecha){
       int valor ;
       Fecha  = Fecha.replace("-","") ;
       valor = (Integer.parseInt(Fecha) / 10000 ) % 100 ;
       return valor ;
   }
   public int stringAYear (String Fecha){
       int valor ;
       Fecha  = Fecha.replace("-","") ;
       valor = Integer.parseInt(Fecha) % 10000 ;
       return valor ;
       //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   }
    /**
     *
     * @param id
     * @return
     */
    public boolean validaID(String id) {

        return locales.containsKey(id);
    }

    //Metodos Reparaciones
    /**
     *
     * @param idLocal
     * @param idrepa
     * @return
     */
    public boolean ExisteReparacion(String idLocal, String idrepa) {
        if (ExisteLocal(idLocal)) {
            return reparaciones.containsKey(idrepa);
        }
        return false;
    }

    /**
     *
     * @param nuevaRepa
     */
    public void addReparacion(Reparacion nuevaRepa) {

        if (!reparaciones.containsKey(nuevaRepa.getIDregistro())) {
            reparaciones.put(nuevaRepa.getIDregistro(), nuevaRepa);
        }
    }

    /**
     *
     * @param idrepa
     * @return
     */
    public boolean ExisteReparacion(String idrepa) {
        if (ExisteLocal(idrepa)) {
            return reparaciones.containsKey(idrepa);
        }
        return false;
    }

    /**
     *
     * @param id
     */
    public void eliminarReparacion(String id) {
        if (reparaciones.containsKey(id)) {
            reparaciones.remove(id);
        }
    }

    /**
     *Obtiene la lista de reparaciones.
     * @return
     */
    public ArrayList obtenerReparacion() {
        ArrayList<Reparacion> lista = new ArrayList<>(reparaciones.values());
        return lista;
    }

    /**
     * Obtiene la lista de reparaciones segun el criterio de busqueda (estado de
     * la reparacion revisado)
     *
     * @return
     */
    public ArrayList obtenerRepListas() {
        ArrayList<Reparacion> listaRep = obtenerReparacion();
        for (int i = 0; i < listaRep.size(); i++) {
            if (listaRep.get(i).getEstado().equals("Revisado")) {
                if (!reparacionesListas.containsKey(listaRep.get(i).getIDregistro())) {
                    reparacionesListas.put(listaRep.get(i).getIDregistro(), listaRep.get(i));
                }
            }
        }
        ArrayList<Reparacion> repListas = new ArrayList(reparacionesListas.values());
        return repListas;
    }

    /**
     * Obtiene la lista de reparaciones segun el criterio de busqueda (rut
     * cliente)
     *
     * @param rut
     * @return
     */
    public ArrayList obtenerRepCliente(String rut) {
        ArrayList<Reparacion> listaRep = obtenerReparacion();
        for (int i = 0; i < listaRep.size(); i++) {
            if (listaRep.get(i).getRutCliente().equals(rut)) {
                if (!reparacionesCliente.containsKey(listaRep.get(i).getIDregistro())) {
                    reparacionesCliente.put(listaRep.get(i).getIDregistro(), listaRep.get(i));
                }
            }
        }
        ArrayList<Reparacion> repCliente = new ArrayList(reparacionesCliente.values());
        return repCliente;
    }

    /**
     * Obtiene la lista de reparaciones segun la fecha solicitada
     *
     * @param fecha
     * @return
     */
    public ArrayList obtenerPorFecha(String fecha) {
        ArrayList<Reparacion> listaRep = obtenerReparacion();
        for (int i = 0; i < listaRep.size(); i++) {
            if (listaRep.get(i).getFechaIngresa().equals(fecha)) {
                if (!reparacionesFecha.containsKey(listaRep.get(i).getIDregistro())) {
                    reparacionesFecha.put(listaRep.get(i).getIDregistro(), listaRep.get(i));
                }
            }
        }
        ArrayList<Reparacion> repFecha = new ArrayList(reparacionesFecha.values());
        return repFecha;
    }

    /**
     * Obtiene la lista de reparaciones segun el criterio de busqueda (estado
     * reparacion pendiente)
     *
     * @return
     */
    public ArrayList obtenerRepPendientes() {
        ArrayList<Reparacion> listaRep = obtenerReparacion();
        for (int i = 0; i < listaRep.size(); i++) {
            if (listaRep.get(i).getEstado().equals("Pendiente")) {
                if (!reparacionesPendientes.containsKey(listaRep.get(i).getIDregistro())) {
                    reparacionesPendientes.put(listaRep.get(i).getIDregistro(), listaRep.get(i));
                }
            }
        }
        ArrayList<Reparacion> repPendientes = new ArrayList(reparacionesPendientes.values());
        return repPendientes;
    }

    /**
     * Genera un ID aleatorio para reparaciones
     *
     * @return
     */
    public String randomID() {

        Random aleatoreo = new Random();
        String alfa = "A";
        String cadena = "";
        int numero;
        int forma;

        forma = (int) (aleatoreo.nextDouble() * alfa.length() - 1 + 0);

        numero = (int) (aleatoreo.nextDouble() * 9999 + 10000);
        cadena = cadena + alfa.charAt(forma) + numero;
        return cadena;

    }

    /**
     * Transforma el formato del atributo LocalDate a String
     *
     * @param fecha
     * @return
     */
    public String conversion(LocalDate fecha) {

        String formato = fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        return formato;

    }

    // Metodos Control Archivos Excel
    /**
     *
     * @throws IOException
     */
    public void leerClExcel() throws IOException {

        try {

            //recibe el archivo a leer
            FileInputStream file = new FileInputStream(new File("Archivos\\Clientes.xlsx"));

            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet sheet = book.getSheetAt(0);
            // se crean los iteradores para recorrer columnas y celdas
            Iterator<Row> rowIte;
            Iterator<Cell> cellIte;

            Row fila;
            Cell casilla;

            rowIte = sheet.iterator();
            rowIte.next();

            //ciclo while para recorrer filas
            while (rowIte.hasNext()) {

                fila = rowIte.next();
                cellIte = fila.cellIterator();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String nombre = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String Apellido = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String Rut = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String correo = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String direccion = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                //int telefono = (int) casilla.getNumericCellValue();
                //pasa el valor entero de la celda a string
                String phone = casilla.getStringCellValue();
                // String phone = Integer.toString(telefono);
                //se crea nuevo cliente con los datos de las celdas
                //System.out.println(nombre+" "+Apellido+" "+Rut+" "+correo+" "+direccion+" "+phone);
                Cliente nuevoCL = new Cliente(correo, direccion, phone, nombre, Apellido, Rut);
                //se agrega nuevo cliente al mapa
                addCliente(nuevoCL);
            }
            // si no encuentra el archivo para leer utiliza el catch para crearlo
        } catch (IOException e) {
            creaExcelCl();
        }

    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void creaExcelCl() throws FileNotFoundException, IOException {
        //declaro nombres de hoja
        String name = "clientes";

        XSSFWorkbook libro = new XSSFWorkbook();

        XSSFSheet hoja = libro.createSheet(name);

        //declaro cabezara primera fila como una lista
        String[] header = new String[]{"Nombre", "Apellido", "Rut", "Correo", "Direccion", "Telefono"};

        XSSFRow row;
        row = hoja.createRow(0);

        //lleno excel con la cabezera para cliente
        for (int j = 0; j < header.length; j++) {
            XSSFCell cell = (XSSFCell) row.createCell(j);
            cell.setCellValue(header[j]);
        }
        //creo el archhivo excel
        try (FileOutputStream fos = new FileOutputStream("Archivos\\Clientes.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Clientes creado");
        }
    }

    /**
     *
     * @param clientes
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void guardarExCL(ArrayList clientes) throws FileNotFoundException, IOException {

        ArrayList<Cliente> aux = clientes;
        int largo = aux.size();

        //declaro nombres de hoja
        String name = "clientes";

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet(name);

        //declaro cabezara primera fila como una lista
        String[] header = new String[]{"Nombre", "Apellido", "Rut", "Correo", "Direccion", "Telefono"};

        Row row;
        row = hoja.createRow(0);
        Cell cell;

        //lleno excel con la cabezera para cliente
        for (int j = 0; j < header.length; j++) {
            cell = (XSSFCell) row.createCell(j);
            cell.setCellValue(header[j]);
        }

        for (int i = 0; i < largo; i++) {
            row = hoja.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(aux.get(i).getNombre());
            cell = row.createCell(1);
            cell.setCellValue(aux.get(i).getApellido());
            cell = row.createCell(2);
            cell.setCellValue(aux.get(i).getRut());
            cell = row.createCell(3);
            cell.setCellValue(aux.get(i).getCorreo());
            cell = row.createCell(4);
            cell.setCellValue(aux.get(i).getDireccion());
            cell = row.createCell(5);
            cell.setCellValue(aux.get(i).getTelefono());
        }
        //creo el archhivo excel
        try (FileOutputStream fos = new FileOutputStream("Archivos\\Clientes.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Clientes Guardado");
        }
    }

    /**
     *
     * @param id
     * @return
     */
    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void creaExcelLc() throws FileNotFoundException, IOException {
        //declaro nombres de hoja
        String name = "Locales";

        XSSFWorkbook libro = new XSSFWorkbook();

        XSSFSheet hoja = libro.createSheet(name);

        //declaro cabezara primera fila como una lista
        String[] header = new String[]{"ID Local", "Nombre", "Region"};

        XSSFRow row;
        row = hoja.createRow(0);

        //lleno excel con la cabezera para Locales
        for (int j = 0; j < header.length; j++) {
            XSSFCell cell = (XSSFCell) row.createCell(j);
            cell.setCellValue(header[j]);
        }
        //creo el archhivo excel
        try (FileOutputStream fos = new FileOutputStream("Archivos\\Locales.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Locales creado");
        }
    }

    /**
     *Se leen los locales que estan en el archivo locales.
     * @throws IOException
     */
    public void leerLcExcel() throws IOException {

        try {
            //recibe el archivo a leer
            FileInputStream file = new FileInputStream(new File("Archivos\\Locales.xlsx"));

            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet sheet = book.getSheetAt(0);
            // se crean los iteradores para recorrer columnas y celdas
            Iterator<Row> rowIte;
            Iterator<Cell> cellIte;

            Row fila;
            Cell casilla;

            rowIte = sheet.iterator();
            rowIte.next();
            //ciclo while para recorrer filas
            while (rowIte.hasNext()) {

                fila = rowIte.next();
                cellIte = fila.cellIterator();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String idLocal = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String nombreLc = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String Region = casilla.getStringCellValue();
                //se crea nuevo empleado con los datos de las celdas
                Local nuevoCL = new Local(idLocal, nombreLc, Region);
                //se agrega nuevo cliente al mapa
                addLocal(nuevoCL);

            }
            // si no encuentra el archivo para leer utiliza el catch para crearlo
        } catch (IOException ex) {
            creaExcelLc();
        }
    }

    /**
     *
     * @param locales
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void guardarExLc(ArrayList locales) throws FileNotFoundException, IOException {

        ArrayList<Local> aux = locales;
        int largo = aux.size();

        //declaro nombres de hoja
        String name = "Locales";

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet(name);

        //declaro cabezara primera fila como una lista
        String[] header = new String[]{"ID Local", "Nombre", "Region"};

        Row row;
        row = hoja.createRow(0);
        Cell cell;

        //lleno excel con la cabezera para cliente
        for (int j = 0; j < header.length; j++) {
            cell = (XSSFCell) row.createCell(j);
            cell.setCellValue(header[j]);
        }

        for (int i = 0; i < largo; i++) {
            row = hoja.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(aux.get(i).getIDLocal());
            cell = row.createCell(1);
            cell.setCellValue(aux.get(i).getNombreLocal());
            cell = row.createCell(2);
            cell.setCellValue(aux.get(i).getRegion());
        }
        //creo el archhivo excel
        try (FileOutputStream fos = new FileOutputStream("Archivos\\Locales.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Locales Guardado");
        }
    }

    /**
     *
     * @param empleado
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void guardarExEM(ArrayList empleado) throws FileNotFoundException, IOException {

        ArrayList<Empleado> aux = empleado;
        int largo = aux.size();

        //declaro nombres de hoja
        String name = "empleados";

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet(name);

        //declaro cabezara primera fila como una lista
        String[] header = new String[]{"Nombre", "Apellido", "Rut", "Contraseña", "ID Local", "Administrador"};

        Row row;
        row = hoja.createRow(0);
        Cell cell;

        //lleno excel con la cabezera para cliente
        for (int j = 0; j < header.length; j++) {
            cell = (XSSFCell) row.createCell(j);
            cell.setCellValue(header[j]);
        }

        for (int i = 0; i < largo; i++) {
            row = hoja.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(aux.get(i).getNombre());
            cell = row.createCell(1);
            cell.setCellValue(aux.get(i).getApellido());
            cell = row.createCell(2);
            cell.setCellValue(aux.get(i).getRut());
            cell = row.createCell(3);
            cell.setCellValue(aux.get(i).getContraseña());
            cell = row.createCell(4);
            cell.setCellValue(aux.get(i).getIdLocal());
            cell = row.createCell(5);
            cell.setCellValue(aux.get(i).getAdministrador());
        }
        //creo el archhivo excel
        try (FileOutputStream fos = new FileOutputStream("Archivos\\Empleados.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Empleados Guardado");
        }
    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void creaExcelEm() throws FileNotFoundException, IOException {
        //declaro nombres de hoja
        String name = "empleados";

        XSSFWorkbook libro = new XSSFWorkbook();

        XSSFSheet hoja = libro.createSheet(name);

        //declaro cabezara primera fila como una lista
        String[] header = new String[]{"Nombre", "Apellido", "Rut", "Contraseña", "Id Local", "Administrador"};

        XSSFRow row;
        row = hoja.createRow(0);

        //lleno excel con la cabezera para Empleados
        for (int j = 0; j < header.length; j++) {
            XSSFCell cell = (XSSFCell) row.createCell(j);
            cell.setCellValue(header[j]);
        }
        //creo el archhivo excel
        try (FileOutputStream fos = new FileOutputStream("Archivos\\Empleados.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Empleados creado");
        }
    }

    /**
     *
     * @throws IOException
     */
    public void leerEmExcel() throws IOException {

        try {
            //recibe el archivo a leer
            FileInputStream file = new FileInputStream(new File("Archivos\\Empleados.xlsx"));

            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet sheet = book.getSheetAt(0);
            // se crean los iteradores para recorrer columnas y celdas
            Iterator<Row> rowIte;
            Iterator<Cell> cellIte;

            Row fila;
            Cell casilla;

            rowIte = sheet.iterator();
            rowIte.next();
            //ciclo while para recorrer filas
            while (rowIte.hasNext()) {

                fila = rowIte.next();
                cellIte = fila.cellIterator();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String nombre = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String Apellido = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String Rut = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String password = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String idLocal = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String admin = casilla.getStringCellValue();

                //System.out.println(nombre+" "+Apellido+" "+Rut+" "+password+" "+phone+" "+administrador);
                Empleado nuevoCL = new Empleado(nombre, Apellido, Rut, password, idLocal, admin);
                //se agrega nuevo cliente al mapa
                addEmpleado(nuevoCL);
            }
            // si no encuentra el archivo para leer utiliza el catch para crearlo
        } catch (IOException ex) {
            creaExcelEm();
        }
    }

    /**
     *
     * @throws IOException
     */
    public void leerRepExcel() throws IOException {

        try {
            FileInputStream file = new FileInputStream(new File("Archivos\\Reparaciones.xlsx"));

            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet sheet = book.getSheetAt(0);
            // se crean los iteradores para recorrer columnas y celdas
            Iterator<Row> rowIte;
            Iterator<Cell> cellIte;

            Row fila;
            Cell casilla;

            rowIte = sheet.iterator();
            rowIte.next();
            //ciclo while para recorrer filas
            while (rowIte.hasNext()) {

                fila = rowIte.next();
                cellIte = fila.cellIterator();
                //recorre celdas de la fila
                casilla = cellIte.next();
                String idRegistro = casilla.getStringCellValue();
                casilla = cellIte.next();
                String rut = casilla.getStringCellValue();
                casilla = cellIte.next();
                String equipo = casilla.getStringCellValue();
                casilla = cellIte.next();
                String local = casilla.getStringCellValue();
                casilla = cellIte.next();
                String fecha = casilla.getStringCellValue();
                casilla = cellIte.next();
                String comentario = casilla.getStringCellValue();
                casilla = cellIte.next();
                String estado = casilla.getStringCellValue();

                Reparacion nuevoRe = new Reparacion(idRegistro, rut, equipo, local, fecha, comentario, estado);
                addReparacion(nuevoRe);
            }
        } catch (IOException e) {
            crearExcelReparacion();
        }
    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void crearExcelReparacion() throws FileNotFoundException, IOException {

        String name = "reparaciones";
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet(name);

        String[] header = new String[]{"IdRegistro", "Rut Cliente", "Equipo", "Local", "Fecha Ingreso", "Comentario", "estado"};

        XSSFRow row;
        row = hoja.createRow(0);

        for (int i = 0; i < header.length; i++) {
            XSSFCell cell = (XSSFCell) row.createCell(i);
            cell.setCellValue(header[i]);
        }
        try (FileOutputStream fos = new FileOutputStream("Archivos\\Reparaciones.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Reparaciones creado");
        }
    }

    /**
     *
     * @param reparacion
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void guardarExReparacion(ArrayList reparacion) throws FileNotFoundException, IOException {

        ArrayList<Reparacion> aux = reparacion;
        int largo = aux.size();

        //declaro nombres de hoja
        String name = "reparaciones";
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet(name);

        String[] header = new String[]{"IdRegistro", "Rut Cliente", "Equipo", "Local", "Fecha Ingreso", "Comentario", "estado"};

        Row row;
        row = hoja.createRow(0);
        Cell cell;

        for (int j = 0; j < header.length; j++) {
            cell = (XSSFCell) row.createCell(j);
            cell.setCellValue(header[j]);
        }

        for (int i = 0; i < largo; i++) {
            row = hoja.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(aux.get(i).getIDregistro());
            cell = row.createCell(1);
            cell.setCellValue(aux.get(i).getRutCliente());
            cell = row.createCell(2);
            cell.setCellValue(aux.get(i).getTipoEquipo());
            cell = row.createCell(3);
            cell.setCellValue(aux.get(i).getLocal());
            cell = row.createCell(4);
            cell.setCellValue(aux.get(i).getFechaIngresa());
            cell = row.createCell(5);
            cell.setCellValue(aux.get(i).getComentario());
            cell = row.createCell(6);
            cell.setCellValue(aux.get(i).getEstado());
        }
        //creo el archhivo excel
        try (FileOutputStream fos = new FileOutputStream("Archivos\\Reparaciones.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Reparaciones Guardado");
        }

    }

    /**
     *
     * @return @throws IOException
     */
    public String leerRegUsuario() throws IOException {
        String Usuario = null;
        try {

            //recibe el archivo a leer
            FileInputStream file = new FileInputStream(new File("Archivos\\procesos\\RegistroUsuarioLogueado.xlsx"));

            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet sheet = book.getSheetAt(0);
            // se crean los iteradores para recorrer columnas y celdas
            Iterator<Row> rowIte;
            Iterator<Cell> cellIte;

            Row fila;
            Cell casilla;

            rowIte = sheet.iterator();
            rowIte.next();

            //ciclo while para recorrer filas
            while (rowIte.hasNext()) {

                fila = rowIte.next();
                cellIte = fila.cellIterator();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                Usuario = casilla.getStringCellValue();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                String Contraseña = casilla.getStringCellValue();

            }
            return Usuario;
            // si no encuentra el archivo para leer utiliza el catch para crearlo
        } catch (IOException e) {
            creaExcelRegUsuario();
        }
        return Usuario;

    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void creaExcelRegUsuario() throws FileNotFoundException, IOException {
        //declaro nombres de hoja
        String name = "RegistroUsuario";

        XSSFWorkbook libro = new XSSFWorkbook();

        XSSFSheet hoja = libro.createSheet(name);

        //declaro cabezara primera fila como una lista
        String[] header = new String[]{"Usuario", "Contraseña"};

        XSSFRow row;
        row = hoja.createRow(0);

        //lleno excel con la cabezera para cliente
        for (int j = 0; j < header.length; j++) {
            XSSFCell cell = (XSSFCell) row.createCell(j);
            cell.setCellValue(header[j]);
        }
        //creo el archhivo excel
        try (FileOutputStream fos = new FileOutputStream("Archivos\\procesos\\RegistroUsuarioLogueado.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Registro de Usuarios creado");
        }
    }

    /**
     *
     * @param Usuario
     * @param Pass
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void guardarExRegistroUsuario(String Usuario, String Pass) throws FileNotFoundException, IOException {

        //declaro nombres de hoja
        String name = "RegistroUsuario";

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet(name);

        //declaro cabezara primera fila como una lista
        String[] header = new String[]{"Usuario", "Contraseña"};

        Row row;
        row = hoja.createRow(0);
        Cell cell;

        //lleno excel con la cabezera para cliente
        for (int j = 0; j < header.length; j++) {
            cell = (XSSFCell) row.createCell(j);
            cell.setCellValue(header[j]);
        }

        row = hoja.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(Usuario);
        cell = row.createCell(1);
        cell.setCellValue(Pass);

        //creo el archhivo excel
        try (FileOutputStream fos = new FileOutputStream("Archivos\\procesos\\RegistroUsuarioLogueado.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Registro Usuario Guardado");
        }
    }

    /**
     *
     * @return @throws IOException
     */
    public String leerClienteRegistro() throws IOException {
        String rutCliente = null;
        try {

            //recibe el archivo a leer
            FileInputStream file = new FileInputStream(new File("Archivos\\procesos\\RegistroCliente.xlsx"));

            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet sheet = book.getSheetAt(0);
            // se crean los iteradores para recorrer columnas y celdas
            Iterator<Row> rowIte;
            Iterator<Cell> cellIte;

            Row fila;
            Cell casilla;

            rowIte = sheet.iterator();
            rowIte.next();

            //ciclo while para recorrer filas
            while (rowIte.hasNext()) {

                fila = rowIte.next();
                cellIte = fila.cellIterator();
                //recorre celdas de la fila
                casilla = cellIte.next();
                //obtiene contenido de la celda
                rutCliente = casilla.getStringCellValue();
                System.out.println(rutCliente);

            }
            return rutCliente;
            // si no encuentra el archivo para leer utiliza el catch para crearlo
        } catch (IOException e) {
            creaExcelRegUsuario();
        }
        return rutCliente;

    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void creaExcelRegCliente() throws FileNotFoundException, IOException {
        //declaro nombres de hoja
        String name = "RegistroCliente";

        XSSFWorkbook libro = new XSSFWorkbook();

        XSSFSheet hoja = libro.createSheet(name);

        //declaro cabezara primera fila como una lista
        String[] header = new String[]{"RutCliente"};

        XSSFRow row;
        row = hoja.createRow(0);

        //lleno excel con la cabezera para cliente
        for (int j = 0; j < header.length; j++) {
            XSSFCell cell = (XSSFCell) row.createCell(j);
            cell.setCellValue(header[j]);
        }
        //creo el archhivo excel
        try (FileOutputStream fos = new FileOutputStream("Archivos\\procesos\\RegistroCliente.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Registro de Reparaciones Cliente creado");
        }
    }

    /**
     *
     * @param Rut
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void guardarExRegistroCliente(String Rut) throws FileNotFoundException, IOException {

        //declaro nombres de hoja
        String name = "Cliente";

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet(name);

        //declaro cabezara primera fila como una lista
        String[] header = new String[]{"RutCliente"};

        Row row;
        row = hoja.createRow(0);
        Cell cell;

        //lleno excel con la cabezera para cliente
        for (int j = 0; j < header.length; j++) {
            cell = (XSSFCell) row.createCell(j);
            cell.setCellValue(header[j]);
        }

        row = hoja.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(Rut);

        //creo el archhivo excel
        try (FileOutputStream fos = new FileOutputStream("Archivos\\procesos\\RegistroCliente.xlsx")) {
            libro.write(fos);
            System.out.println("Archivo Registro Reparaciones Cliente Guardado");
        }
    }
}
