package Datos.Tarifa;

import Datos.Llamada;

import java.io.Serializable;
import java.util.Objects;

public abstract class Tarifa implements Serializable {
    private double precio;

    public Tarifa(double precio) {
        this.precio = precio;
    }

    public Tarifa() {
        precio=0;
    }
    public double getPrecio(Llamada llamada){
        return precio;
    }
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "precio=" + precio+"€/min";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarifa)) return false;
        Tarifa tarifa = (Tarifa) o;
        return Double.compare(tarifa.getPrecio(), getPrecio()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrecio());
    }

}
