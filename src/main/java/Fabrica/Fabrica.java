package Fabrica;

import Cliente.Cliente;
import Datos.Dir;
import Datos.Tarifa.Tarifa;

import java.util.Calendar;

public interface Fabrica {
     Cliente getEmpresa(String nombre, String nif, String correoE, Calendar fechaAlta, Tarifa tarifa, Dir dir, int nTelf);
     Cliente getParticular(String nombre, String apellidos , String nif, String correoE, Calendar fechaAlta, Tarifa tarifa, Dir direc, int nTelf);
     Tarifa getTarifaBasica(double precio);
     Tarifa getTarifaDiaria(Tarifa tarifa, double precio,int dia);
     Tarifa getTarifaSemanal(Tarifa tarifa, double precio,int semana);

     Tarifa getTarifaHoraria(Tarifa tarifa, double v, int i, int i1);

     Dir getDir(int codP, String prov, String poblacion);
}
