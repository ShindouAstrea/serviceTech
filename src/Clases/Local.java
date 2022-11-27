/*+
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author mdiaz
 */
public class Local {

    private String IDLocal;
    private String nombreLocal;
    private String region;

    private ArrayList<Reparacion> reparaciones = new ArrayList<>();

    // Contructores Locales
    /**
     *
     * @param IDLocal
     * @param nombreLocal
     * @param region
     */
    public Local(String IDLocal, String nombreLocal, String region) {
        this.IDLocal = IDLocal;
        this.nombreLocal = nombreLocal;
        this.region = region;

    }

    /**
     *
     */
    public Local() {
        reparaciones = new ArrayList<>();
    }

    // get y set
    /**
     *
     * @return
     */
    public String getIDLocal() {
        return IDLocal;
    }

    /**
     *
     * @param IDLocal
     */
    public void setIDLocal(String IDLocal) {
        this.IDLocal = IDLocal;
    }

    /**
     *
     * @return
     */
    public String getNombreLocal() {
        return nombreLocal;
    }

    /**
     *
     * @param nombreLocal
     */
    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    /**
     *
     * @return
     */
    public String getRegion() {
        return region;
    }

    /**
     *
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
    }


}
