package Cliente;

import Datos.*;
import Datos.Tarifa.Tarifa;
import Datos.Tarifa.TarifaBasica;

import java.io.Serializable;
import java.util.*;

public abstract class Cliente implements Fecha, Serializable {
    private String nombre;
    private String nif;
    private String correoE;
    private Calendar fechaAlta;
    private Tarifa tarifa;
    private int nTelf;
    private List<Llamada> llamadas;

    public Dir getDirec() {
        return direc;
    }

    private Dir direc;
    //constructor
    public Cliente(){
        fechaAlta=Calendar.getInstance();
        tarifa=new TarifaBasica(0.25f);
        llamadas=new LinkedList<>();
        direc=new Dir();
    }
    public Cliente(String nombre, String nif, String correoE, Calendar fechaAlta, Tarifa tarifa,Dir direc,int nTelf) {
        this.nombre = nombre;
        this.nif = nif;
        this.correoE = correoE;
        this.fechaAlta = fechaAlta;
        this.tarifa = tarifa;
        llamadas=new LinkedList<>();
        this.nTelf=nTelf;
        this.direc=direc;
    }

    //añadir llamadas y facturas a las colecciones
    public  void addLlamada(Llamada llamada){
        if(llamada==null)
            throw new IllegalArgumentException();
        llamadas.add(llamada);
    }


    //getters and setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getCorreoE() {
        return correoE;
    }

    public void setCorreoE(String correoE) {
        this.correoE = correoE;
    }

    public Calendar getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public int getnTelf() {
        return nTelf;
    }

    public void setnTelf(int nTelf) {
        this.nTelf = nTelf;
    }

    public List<Llamada> getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(List<Llamada> llamadas) {
        this.llamadas = llamadas;
    }

    @Override
    public Calendar getFecha() {
        return getFechaAlta();
    }

    @Override
    public String toString() {
        return
                "nombre='" + getNombre() + '\'' +
                ", nif='" + nif + '\'' +
                ", correoE='" + correoE + '\'' +
                ", fechaAlta=" + fechaAlta.getTime() +
                ", tarifa=" + tarifa +
                ", nTelf=" + nTelf
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return getNombre().equals(cliente.getNombre()) &&
                getNif().equals(cliente.getNif());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getNif());
    }
}
