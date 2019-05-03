package Datos.Tarifa;

import Datos.Llamada;

public abstract class Extra extends Tarifa{
    protected Tarifa tarifa;
    public Extra(Tarifa tarifa, double precio){
        super(precio);
        this.tarifa=tarifa;
    }



    @Override
    public double getPrecio(Llamada llamada){
        if(super.getPrecio()>tarifa.getPrecio(llamada))
            return tarifa.getPrecio(llamada);
        return super.getPrecio();
    }

}
