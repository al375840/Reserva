package Datos;

import java.io.Serializable;

public class Dir implements Serializable {
    private int codP;
    private String prov;
    private String poblacion;

    public Dir(int codP, String prov, String poblacion) {
        this.codP = codP;
        this.prov = prov;
        this.poblacion = poblacion;
    }

    public Dir() {
        codP=0;
        prov="No asignado";
        poblacion="No asignado";
    }

    public int getCodP() {
        return codP;
    }

    public void setCodP(int codP) {
        this.codP = codP;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
}
