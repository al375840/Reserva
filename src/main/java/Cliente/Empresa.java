package Cliente;

import Datos.Dir;
import Datos.Tarifa.Tarifa;

import java.util.Calendar;

public class Empresa extends Cliente{
    public Empresa(){
        super();
    }
    public Empresa(String nombre, String nif, String correoE, Calendar fechaAlta, Tarifa tarifa, Dir dir, int nTelf) {
        super(nombre, nif, correoE, fechaAlta, tarifa,dir,nTelf);
    }



}
