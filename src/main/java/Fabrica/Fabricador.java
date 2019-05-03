package Fabrica;

import Cliente.*;
import Datos.Dir;
import Datos.Tarifa.*;

import java.util.Calendar;

public class Fabricador implements Fabrica {
    public Fabricador(){
        super();
    }
    @Override
    public Cliente getEmpresa(String nombre, String nif, String correoE, Calendar fechaAlta, Tarifa tarifa, Dir dir, int nTelf) {
        return new Empresa(nombre,nif,correoE, fechaAlta,tarifa,dir,nTelf);
    }

    @Override
    public Cliente getParticular(String nombre, String apellidos, String nif, String correoE, Calendar fechaAlta, Tarifa tarifa, Dir direc, int nTelf) {
        return new Particular(nombre,apellidos,nif,correoE,fechaAlta,tarifa,direc,nTelf);
    }

    @Override
    public Tarifa getTarifaBasica(double precio) {
        return new TarifaBasica(precio);
    }

    @Override
    public Tarifa getTarifaDiaria(Tarifa tarifa, double precio, int dia) {
        return new TarifaDiaria(tarifa,precio,dia);
    }

    @Override
    public Tarifa getTarifaSemanal(Tarifa tarifa, double precio, int semana) {
        return new TarifaSemanal(tarifa,precio,semana);
    }

    @Override
    public Tarifa getTarifaHoraria(Tarifa tarifa, double precio, int ini, int fin) {
        return new TarifaHoraria(tarifa,precio,ini,fin);
    }

    @Override
    public Dir getDir(int codP, String prov, String poblacion) {
        return new Dir(codP,prov,poblacion);
    }
}
