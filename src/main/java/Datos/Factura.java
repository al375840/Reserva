package Datos;

import Cliente.Cliente;
import Datos.Tarifa.Tarifa;

import java.io.Serializable;
import java.util.Calendar;

public class Factura implements Fecha, Serializable {
    private int cod;
    private Tarifa tarifa;
    private Calendar fechaFacturacion;
    private Calendar fechaIni;
    private Calendar fechaFin;
    private double importe;
    private Cliente cliente;

    public Factura(int cod, Tarifa tarifa, Calendar fechaFacturacion, Calendar fechaIni, Calendar fechaFin,Cliente cliente) {
        this.cod = cod;
        this.tarifa = tarifa;
        this.fechaFacturacion = fechaFacturacion;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.cliente=cliente;
        importe= calculaImporte(cliente);
    }
    private double calculaImporte(Cliente cliente){
        double precio=0;
        for(Llamada llamada : cliente.getLlamadas()){
            if(llamada.getFecha().compareTo(fechaIni)>=0 && llamada.getFecha().compareTo(fechaFin)<0 )
                precio+=tarifa.getPrecio(llamada)*(llamada.getDuracion()/60.0);
        }
        return precio;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Calendar getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Calendar fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public Calendar getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Calendar fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double calculaImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getCliente(){return cliente.getNif(); }

    @Override
    public Calendar getFecha() {
        return fechaFacturacion;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "cod=" + cod +
                ", fechaFacturacion=" + fechaFacturacion.getTime() +
                ", fechaInicio=" + fechaIni.getTime() +
                ", fechaFin=" + fechaFin.getTime() +
                ", importe=" + importe +
                ", cliente=" + cliente.getNif() +
                '}';
    }
}
