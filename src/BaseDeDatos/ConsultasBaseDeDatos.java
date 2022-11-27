/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import Clases.ServicioTecnico;
import javax.swing.JOptionPane;

/**
 *
 * @author josep
 */
public class ConsultasBaseDeDatos {

    ServicioTecnico sv = new ServicioTecnico();

    //~~~~~Variables que sirven para hacer las Consultas y transacciones en la Base de Datos ~~~~~~~~~~
    PreparedStatement ps;
    ResultSet res;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Metodos CRUD Empleados~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //Se analiza que los datos usuario y contraseña sean los correctos que estan en la base de datos 
    public boolean verificacionLogeo(String rut, String contraseña, Connection link) {
        Connection conectar = link;

        try {
            ps = (PreparedStatement) conectar.prepareStatement("SELECT * FROM Empleados WHERE rut = ?");
            ps.setString(1, rut);
            String rutCorrecto = null;
            String contraseñaCorrecta = null;
            res = ps.executeQuery();
            if (res.next()) {

                rutCorrecto = res.getString("rut");
                contraseñaCorrecta = res.getString("contraseña");

            }
            if (rut.equals(rutCorrecto) && contraseñaCorrecta.equals(contraseña)) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException loging) {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
        }
        return false;
    }
    //Verifica si existen mas empleados .
    public boolean esUltimoEmpleado(Connection link){
        Connection conectar = link ;
        String rutEm  ;
        try{
            ps = (PreparedStatement)conectar.prepareStatement("SELECT * FROM Empleados") ;
            res = ps.executeQuery() ;
            int i = 0 ;
            while(res.next()){
                i++ ;
            }
             if(i==1) return true ;   
        
        } catch(SQLException ulEm){
            System.out.println("Comando erroneo") ;
            
        }
        return false ;
    }
    //Se analiza si el empleado posee el permiso para hacer cambios o leer datos confidenciales(contraseña de otros empleados)
    public boolean permisosAdministrador(String rut, Connection link) {
        Connection conectar = link;
        String admin = "no";
        try {
            ps = (PreparedStatement) conectar.prepareStatement("SELECT * FROM Empleados WHERE rut = ?");
            ps.setString(1, rut);
            res = ps.executeQuery();
            if (res.next()) {
                admin = res.getString("admin");
            }
            if (admin.equals("si")) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException loging) {
        }
        return false;
    }
    public boolean hayEmpleados(String IDLocal , Connection link )
            
    {
        Connection conectar = link ;
        try {
            ps = (PreparedStatement)conectar.prepareStatement("SELECT * FROM Empleados WHERE id_local = ?") ;
            ps.setString(1,IDLocal);
            res = ps.executeQuery() ;
            if(res.next()){
                return true ;
            }
        } catch(SQLException h){
            
        }
        return false ;
    }
    //Se agrega un empleado a la base de datos.
    public void insertarEmpleado(String nombre, String apellido, String rut, String IdLocal, String contraseña, String admin, Connection link) {
        Connection conectar = link;
        try {

            ps = (PreparedStatement) conectar.prepareStatement("INSERT INTO Empleados (nombre, apellido, rut, id_local, contraseña, admin) VALUES(?,?,?,?,?,?) ");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, rut);
            ps.setString(4, IdLocal);
            ps.setString(5, contraseña);
            ps.setString(6, admin);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Empleado correctamente ingresado");
            } else {
                JOptionPane.showMessageDialog(null, "Uno o más datos son incorrectos");
            }
            conectar.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar empleado , ya existe el rut ingresado");

        }
    }
    //Se modifican los datos del empleado , incluido su permiso de edicion (admin) en la base de datos
    public void modificarEmpleado(String nombre, String apellido, String rut, String IdLocal, String contraseña, String admin, Connection link) {
        Connection conectar = link;
        try {
            ps = (PreparedStatement) conectar.prepareStatement("UPDATE Empleados SET nombre=?,apellido=?,id_local=?,contraseña=?,admin=? WHERE rut=?");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, IdLocal);
            ps.setString(4, contraseña);
            ps.setString(5, admin);
            ps.setString(6, rut);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Empleado correctamente actualizado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al intentar modificarle el empleado, revise los campos");
            }

            conectar.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion al servidor  ");

        }

    }
    //Se elimina a un empleado de la base de datos
    public void eliminarEmpleado(String rut, Connection link) {
        Connection conectar = link;
        try {
            ps = (PreparedStatement) conectar.prepareStatement("DELETE FROM Empleados WHERE rut=?");
            ps.setString(1, rut);
            sv.eliminarEmp(rut);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Empleado correctamente eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al intentar eliminar el empleado");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion al servidor ");

        }

    }
    //~~~~~~~~~~~~~~~~~~~~~~~~Metodos CRUD Clientes~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Se busca a un cliente por su rut en la base de datos
    public boolean buscarCl(String rut, Connection link) {
        Connection conectar = link;

        try {
            ps = (PreparedStatement) conectar.prepareStatement("SELECT * FROM Clientes WHERE rut_cliente = ?");
            ps.setString(1, rut);
            res = ps.executeQuery();

            return true;

        } catch (SQLException loging) {
        }
        return false;
    }
    //Se agrega un cliente a la base de datos
    public void insertarCliente(String nombre, String apellido, String rut, String correo, String direccion, String telefono, Connection link) {
        Connection conectar = link;
        try {

            ps = (PreparedStatement) conectar.prepareStatement("INSERT INTO Clientes (nombre, apellido, rut_cliente, correo, direccion, telefono) VALUES(?,?,?,?,?,?) ");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, rut);
            ps.setString(4, correo);
            ps.setString(5, direccion);
            ps.setString(6, telefono);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente correctamente ingresado");
            } else {
                JOptionPane.showMessageDialog(null, "Uno o más datos  son incorrectos");
            }
            conectar.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion al servidor");

        }
    }
    //Se modifican los datos del cliente en la base de datos
    public void modificarCliente(String nombre, String apellido, String rut, String correo, String direccion, String telefono, Connection link) {
        Connection conectar = link;
        try {
            ps = (PreparedStatement) conectar.prepareStatement("UPDATE Clientes SET nombre=?,apellido=?,correo=?,direccion=?,telefono=? WHERE rut_cliente=?");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setString(4, direccion);
            ps.setString(5, telefono);
            ps.setString(6, rut);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente  correctamente actualizado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al intentar modificar el cliente, revise los campos");
            }

            conectar.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion al servidor  " + e);

        }

    }
    // Se elimina un cliente de la base de datos
    public void eliminarCliente(String rut, Connection link) {
        Connection conectar = link;
        try {
            ps = (PreparedStatement) conectar.prepareStatement("DELETE FROM Clientes WHERE rut_cliente=?");
            ps.setString(1, rut);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente correctamente eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al intentar eliminar el cliente");
            }
            conectar.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion al servidor ");

        }
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 
    }

    //~~~~~~~~~~~~~~~~~Metodos CRUD Locales~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //se agrega local a la base de datos
    public void insertarLocal(String idLocal, String nombreLocal, String region, Connection link) {
        Connection conectar = link;

        try {

            ps = (PreparedStatement) conectar.prepareStatement("INSERT INTO Locales (id_local, nombre_local, region) VALUES(?,?,?)");
            ps.setString(1, idLocal);
            ps.setString(2, nombreLocal);
            ps.setString(3, region);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Local correctamente ingresado");
            } else {
                JOptionPane.showMessageDialog(null, "Uno o más datos son incorrectos");
            }

            conectar.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion al servidor");

        }
    }
    //Se modifican los datos del local en la base de datos
    public void modificarLocal(String idLocal, String nombreLocal, String region, Connection link) {
        Connection conectar = link;
        try {
            ps = (PreparedStatement) conectar.prepareStatement("UPDATE Locales SET nombre_local=?,region=? WHERE id_local=?");
            ps.setString(1, nombreLocal);
            ps.setString(2, region);
            ps.setString(3, idLocal);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Local  correctamente actualizado");
            } else {
                JOptionPane.showMessageDialog(null, " Error al intentar modifcar el local , revise los campos");
            }
            conectar.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion al servidor  ");

        }

    }
//Se elimina un local de la base de datos
    public void eliminarLocal(String idLocal, Connection link) {
        Connection conectar = link;
        try {
            ps = (PreparedStatement) conectar.prepareStatement("DELETE FROM Locales WHERE id_local=?");
            ps.setString(1, idLocal);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Local correctamente eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al intentar eliminar el Local");
            }

            conectar.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion al servidor ");

        }

    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Se agrega un registro de reparacion a la base de datos
    public void insertarRegReparacion(String Id, String rutCliente, String tipo, String nombreLocal, String fecha, String comentario, String estado, Connection link) {
        Connection conectar = link;

        try {

            ps = (PreparedStatement) conectar.prepareStatement("INSERT INTO RegistroReparaciones (id_registro, rut_cliente, tipo_equipo, nombre_local, fecha_de_ingreso, comentario, estado) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, Id);
            ps.setString(2, rutCliente);
            ps.setString(3, tipo);
            ps.setString(4, nombreLocal);
            ps.setString(5, fecha);
            ps.setString(6, comentario);
            ps.setString(7, estado);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Registro correctamente ingresado");
            } else {
                JOptionPane.showMessageDialog(null, "Uno o más datos son incorrectos");
            }

            conectar.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Rut invalido , asegurese de que se encuentre agregado");

        }
    }
    //Se modifica los datos del registro de reparacion en la base de datos
    public void modificarRegReparacion(String Id, String rutCliente, String tipo, String nombreLocal, String fecha, String comentario, String estado, Connection link) {
        Connection conectar = link;
        try {
            ps = (PreparedStatement) conectar.prepareStatement("UPDATE RegistroReparaciones SET rut_cliente=?,tipo_equipo=?, nombre_local=?, fecha_de_ingreso=?,comentario=?,estado=? WHERE id_registro=?");
            ps.setString(1, rutCliente);
            ps.setString(2, tipo);
            ps.setString(3, nombreLocal);
            ps.setString(4, fecha);
            ps.setString(5, comentario);
            ps.setString(6, estado);
            ps.setString(7, Id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Registro  correctamente actualizado");
            } else {
                JOptionPane.showMessageDialog(null, " Error al intentar modifcar el Registro , revise los campos");
            }
            conectar.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion al servidor  ");

        }

    }
    //Se elimina el registro de reparacion de la base de datos
    public void eliminarRegReparacion(String Id, Connection link) {
        Connection conectar = link;
        try {
            ps = (PreparedStatement) conectar.prepareStatement("DELETE FROM RegistroReparaciones WHERE id_registro=?");
            ps.setString(1, Id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Registro correctamente eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al intentar eliminar el Registro");
            }

            conectar.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion al servidor ");

        }

    }
    //Metodo que analiza si un registro pertenece a cierto dueño
    public boolean tieneRegistro(String rut , Connection link )
    {
        Connection conectar = link ;
        try{
            ps = (PreparedStatement)conectar.prepareStatement("SELECT * FROM RegistroReparaciones WHERE rut_cliente = ?") ;
            ps.setString(1,rut) ;
            res = ps.executeQuery() ;
            if(res.next()) return true ;
        }catch(SQLException tr){
            
        }
        return false ;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
