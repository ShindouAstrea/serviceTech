/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.sql.Connection;

import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author josep
 */
public class ConexionBaseDeDatos {

    private static final String URL = "jdbc:mysql://168.232.165.245:3306/inf40";
    private static final String Username = "inf40";
    private static final String Password = "db6692";
    private Connection Link = null;

    public void establecerConexion() { // Metodo que hace la conexion al servidor
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Link = (Connection) DriverManager.getConnection(URL, Username, Password);
            JOptionPane.showMessageDialog(null, "Conectado");
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println("No se pudo establecer conexion con el Servidor" + e);
        }
    }

    public Connection obtenerEnlace() { //metodo que hace lo mismo que el anterior pero retorna el link del servidor .
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Link = (Connection) DriverManager.getConnection(URL, Username, Password);
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println("No se pudo establecer conexion con el Servidor" + e);
        }
        return Link;
    }
}
