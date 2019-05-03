package Datos;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Llamada implements Fecha, Serializable {
    int destino;
    Calendar fecha;
    int duracion; //duración de la llamada en segundos

    public Llamada(int destino, Calendar fecha, int duracion) {
        this.destino = destino;
        this.fecha = fecha;
        this.duracion = duracion;
    }
    public Llamada(int destino, int duracion) {
        this.destino = destino;
        this.fecha = Calendar.getInstance();
        this.duracion = duracion;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }
    @Override
    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Llamada{" +
                "destino=" + destino +
                ", fecha=" + fecha.getTime() +
                ", duracion=" + duracion +
                '}';
    }
}
