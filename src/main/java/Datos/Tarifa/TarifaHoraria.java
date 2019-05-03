package Datos.Tarifa;

import Datos.Llamada;

import java.util.Calendar;

public class TarifaHoraria extends Extra {
    private int ini;
    private int fin;
    public TarifaHoraria(Tarifa tarifa, double precio,int ini,int fin) {
        super(tarifa, precio);
        this.fin=fin;
        this.ini=ini;
    }
    @Override
    public double getPrecio(Llamada llamada){
        int hora= llamada.getFecha().get(Calendar.HOUR_OF_DAY);
        boolean se_aplica= hora >=ini && hora <=fin;
        if(se_aplica)
            return super.getPrecio(llamada);
        else
            return super.tarifa.getPrecio(llamada);
    }
}

