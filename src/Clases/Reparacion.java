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
public class Reparacion {

    private String IDregistro;
    private String rutCliente;
    private String tipoEquipo;//notebook o gabinete
    private String local;
    private String fechaIngresa;
    private String Comentario; //problemas del equipo o comentario relacionado al tipo de trabajo a realizar
    private String estado;

    //contructor
    /**
     *
     * @param IDregistro
     * @param rutCliente
     * @param tipoEquipo
     * @param local
     * @param fechaIngresa
     * @param Comentario
     * @param estado
     */
    public Reparacion(String IDregistro, String rutCliente, String tipoEquipo, String local, String fechaIngresa, String Comentario, String estado) {
        this.IDregistro = IDregistro;
        this.rutCliente = rutCliente;
        this.tipoEquipo = tipoEquipo;
        this.local = local;
        this.fechaIngresa = fechaIngresa;
        this.Comentario = Comentario;
        this.estado = estado;
    }

    //get y set
    /**
     *
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     *
     * @return
     */
    public String getLocal() {
        return local;
    }

    /**
     *
     * @param local
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     *
     * @return
     */
    public String getIDregistro() {
        return IDregistro;
    }

    /**
     *
     * @param IDregistro
     */
    public void setIDregistro(String IDregistro) {
        this.IDregistro = IDregistro;
    }

    /**
     *
     * @return
     */
    public String getRutCliente() {
        return rutCliente;
    }

    /**
     *
     * @param rutCliente
     */
    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    /**
     *
     * @return
     */
    public String getTipoEquipo() {
        return tipoEquipo;
    }

    /**
     *
     * @param tipoEquipo
     */
    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    /**
     *
     * @return
     */
    public String getFechaIngresa() {
        return fechaIngresa;
    }

    /**
     *
     * @param fechaIngresa
     */
    public void setFechaIngresa(String fechaIngresa) {
        this.fechaIngresa = fechaIngresa;
    }

    /**
     *
     * @return
     */
    public String getComentario() {
        return Comentario;
    }

    /**
     *
     * @param Comentario
     */
    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }
    public boolean formatoFecha (String fecha){
        fecha = fecha.toUpperCase() ;
        if(fecha.contains("A")|| fecha.contains("B")|| fecha.contains("C")) return false ;
        if(fecha.contains("D")|| fecha.contains("E")|| fecha.contains("F")) return false ;
        if(fecha.contains("G")|| fecha.contains("I")|| fecha.contains("J")) return false ;
        if(fecha.contains("L")|| fecha.contains("M")|| fecha.contains("N")) return false ;
        if(fecha.contains("O")|| fecha.contains("P")|| fecha.contains("R")) return false ;
        if(fecha.contains("S")|| fecha.contains("T")|| fecha.contains("U")) return false ;
        if(fecha.contains("Y")|| fecha.contains("Z")) return false ;
        if(fecha.contains("/")) return false ;
        if(fecha.contains(".")) return false ;
       
        
        
      
        return true ;
    }
}
