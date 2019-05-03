package Cliente;

import Datos.Dir;

import Datos.Tarifa.Tarifa;

import java.util.Calendar;

public class Particular extends Cliente{
    private  String apellidos;
    public Particular(){
        super();
        apellidos="No asignado";
    }
    public Particular(String nombre, String apellidos , String nif, String correoE, Calendar fechaAlta, Tarifa tarifa, Dir direc, int nTelf) {
        super(nombre, nif, correoE, fechaAlta, tarifa,direc,nTelf);
        this.apellidos=apellidos;
    }

    @Override
    public String getNombre(){
        return super.getNombre()+" "+apellidos;
    }

}
