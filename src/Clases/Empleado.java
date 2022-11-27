/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author mdiaz
 */
public class Empleado extends Persona {

    private String contraseña;
    private String idLocal;
    private String administrador;

    /**
     * Contructor Clase Empleado con atributos de su clase padre
     *
     * @param Nombre
     * @param Apellido
     * @param rut
     * @param contraseña
     * @param idLocal
     * @param administrador
     */
    public Empleado(String Nombre, String Apellido, String rut, String contraseña, String idLocal, String administrador) {
        super(Nombre, Apellido, rut);
        this.contraseña = contraseña;
        this.idLocal = idLocal;
        this.administrador = administrador;
    }

    // Get y set 
    /**
     *
     * @return
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     *
     * @param contraseña
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     *
     * @return
     */
    public String getIdLocal() {
        return idLocal;
    }

    /**
     *
     * @param idLocal
     */
    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    /**
     *
     * @return
     */
    public String getAdministrador() {
        return administrador;
    }

    /**
     *
     * @param administrador
     */
    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

}
