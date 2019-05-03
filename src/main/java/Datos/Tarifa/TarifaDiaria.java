package Datos.Tarifa;

import Datos.Llamada;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TarifaDiaria extends Extra{

    private int dia;
    public TarifaDiaria(Tarifa tarifa, double precio,int dia) {
        super(tarifa, precio);
        this.dia=dia;
    }
@Override
    public double getPrecio(Llamada llamada){
       boolean se_aplica=llamada.getFecha().get(Calendar.DAY_OF_WEEK)==dia;
       if(se_aplica)
         return super.getPrecio(llamada);
       else
           return super.tarifa.getPrecio(llamada);
    }
}
