package Control;

import Cliente.Cliente;
import Datos.Factura;
import Datos.ParFecha;
import Datos.Tarifa.Tarifa;
import MetodosUtilidad.Funcion;

import java.io.Serializable;
import java.util.*;

public class Facturas implements Serializable {
    private HashMap<Integer,Factura> facturas;
    private int cod=0;
    public Facturas(){
        facturas=new HashMap<>();
    }

    public int emitirFactura(Cliente cliente, Calendar ini, Calendar fin){
        Tarifa tarifa=cliente.getTarifa();
        Calendar facturacion=Calendar.getInstance();
        Factura fact=new Factura(cod,tarifa,facturacion,ini,fin,cliente );
        facturas.put(cod++,fact);
        return cod-1;
    }

    public Factura getFactura(int cod){
        return facturas.get(cod);
    }

    public List<Factura> facturasCliente(String nif){
        List<Factura> list=new LinkedList<>();
        for(int cod:facturas.keySet()){
            Factura fact=facturas.get(cod);
            if(nif.equals(fact.getCliente()))
                list.add(fact);
        }
        return list;
    }

    public Collection<Factura> listaFacturas(String nif, ParFecha par){
        return Funcion.acotaFecha(facturasCliente(nif),par);
    }


}
