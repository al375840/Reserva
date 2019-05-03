package Control;

import Cliente.Cliente;
import Datos.Llamada;
import Datos.ParFecha;
import Datos.Tarifa.Tarifa;
import Excepciones.ExisteCliente;
import Excepciones.NoExisteCliente;
import Excepciones.TarifaNula;
import MetodosUtilidad.Funcion;

import java.io.Serializable;
import java.util.*;

public class Clientes implements Serializable {
    private HashMap<String, Cliente> clientes;
    public Clientes(){
        clientes=new HashMap<String, Cliente>();
    }

    public boolean addCliente(Cliente cliente) throws ExisteCliente {
        if(cliente==null)
            return false;
        if(clientes.containsKey(cliente.getNif())){
            throw new ExisteCliente();
        }
        clientes.put(cliente.getNif(),cliente);
        return true;

    }
    public Cliente removeCliente(String nif) throws NoExisteCliente {
        if(!clientes.containsKey(nif))
            throw new NoExisteCliente(nif);
        return clientes.remove(nif);
    }
    public boolean cambioTarifa(Tarifa tarifa,String nif) throws TarifaNula {
        if(tarifa==null)
            throw new TarifaNula();
        if(clientes.containsKey(nif)){
            clientes.get(nif).setTarifa(tarifa);
            return true;
        }
        return false;
    }
    public Cliente getDatos(String nif) throws NoExisteCliente {
        if(!clientes.containsKey(nif))
            throw new NoExisteCliente(nif);
        return clientes.get(nif);
    }
    public List<Cliente> getListaClientes(){
        List<Cliente> list=new LinkedList<Cliente>();
        for(String nif:clientes.keySet())
            list.add(clientes.get(nif));
        return list;
    }

    //Get datos acotado por fecha
    public Collection<Cliente> listaClientes(ParFecha par){
       return Funcion.acotaFecha(clientes.values(),par);
    }
    public Collection<Llamada> listaLlamadas(String nif, ParFecha par) throws NoExisteCliente {
        if(!clientes.containsKey(nif))
            throw new NoExisteCliente(nif);
        return Funcion.acotaFecha(clientes.get(nif).getLlamadas(),par);
    }

}
