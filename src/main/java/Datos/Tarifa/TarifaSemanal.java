package Datos.Tarifa;

import Datos.Llamada;

import java.util.Calendar;

public class TarifaSemanal extends Extra {
    int semana;

    public TarifaSemanal(Tarifa tarifa, double precio,int semana) {
        super(tarifa, precio);
        this.semana=semana;
    }
    @Override
    public double getPrecio(Llamada llamada){
        boolean se_aplica = llamada.getFecha().get(Calendar.WEEK_OF_MONTH)==semana;
        if(se_aplica)
            return super.getPrecio(llamada);
        else
            return super.tarifa.getPrecio(llamada);
    }
}
