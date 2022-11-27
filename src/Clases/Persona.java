package Clases;

/**
 *
 * @author mdiaz
 */
public abstract class Persona {

    String Nombre;
    String Apellido;
    String rut;
    
    //Contructor Clase Persona
    
    /**
     * 
     *
     */
    public Persona() {
    }

    /**
     *
     * @param Nombre
     * @param Apellido
     * @param rut
     */
    public Persona(String Nombre, String Apellido, String rut) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.rut = rut;
    }

    // Get y Set
    
    /**
     *
     * @return
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     *
     * @param Nombre
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     *
     * @return
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     *
     * @param Apellido
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     *
     * @return
     */
    public String getRut() {
        return rut;
    }

    /**
     *
     * @param rut
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    // Metodos
    
    /** Se encarga de revisar que el rut sea Valido
     *
     * @param rut
     * @return
     */
    public boolean validarRut(String rut) {
        boolean validacion = false;

        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;

            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }

        return validacion;
    }
    public boolean validarFormatoRut(String rut ) {
        rut = rut.toUpperCase() ;
        if(rut.contains("A")|| rut.contains("B")|| rut.contains("C")) return false ;
        if(rut.contains("D")|| rut.contains("E")|| rut.contains("F")) return false ;
        if(rut.contains("G")|| rut.contains("H")|| rut.contains("I")) return false ;
        if(rut.contains("J")|| rut.contains("K")|| rut.contains("L")) return false ;
        if(rut.contains("M")|| rut.contains("N")|| rut.contains("Ñ")) return false ;
        if(rut.contains("O")|| rut.contains("P")|| rut.contains("Q")) return false ;
        if(rut.contains("R")|| rut.contains("S")|| rut.contains("T")) return false ;
        if(rut.contains("U")||rut.contains("W")||rut.contains("X")) return false ;
        if(rut.contains("Y")||rut.contains("Z")||rut.contains(" ")) return false ;
        if(rut.contains(".")) return false ;

        return true ;
    }

}
