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
public class Cliente extends Persona{
    
    private String correo;
    private String direccion;
    private String telefono;

    /**Constructor Clase Cliente con los atributos de su clase Padre
     *
     * @param correo
     * @param direccion
     * @param telefono
     * @param Nombre
     * @param Apellido
     * @param rut
     */
    public Cliente(String correo, String direccion, String telefono, String Nombre, String Apellido, String rut) {
        super(Nombre, Apellido, rut);
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Get y set Cliente
        
    /**
     *
     * @return
     */
    public String getCorreo() {
        return correo;
    }

    /**
     *
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     *
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     *
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *
     * @return
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
