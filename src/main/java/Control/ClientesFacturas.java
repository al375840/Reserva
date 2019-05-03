package Control;

import Cliente.Cliente;
import Datos.Factura;
import Datos.Llamada;
import Datos.ParFecha;
import Datos.Tarifa.Tarifa;
import Excepciones.ExisteCliente;
import Excepciones.NoExisteCliente;
import Excepciones.TarifaNula;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class ClientesFacturas implements Serializable {
    Clientes clientes;
    Facturas facturas;

    public ClientesFacturas(){
        clientes=new Clientes();
        facturas=new Facturas();
    }
    public ClientesFacturas(Clientes clientes, Facturas facturas) {
        this.clientes = clientes;
        this.facturas = facturas;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public Facturas getFacturas() {
        return facturas;
    }

    public List<Factura> facturasClientes(String nif, ParFecha par){
        return (List<Factura>) facturas.listaFacturas(nif,par);
    }

    public List<Factura> facturasCliente(String nif){
        return facturas.facturasCliente(nif);
    }
    public int emitirFactura(Cliente cliente, Calendar ini, Calendar fin){
        return facturas.emitirFactura(cliente,ini,fin);
    }
    public boolean addCliente(Cliente cliente) throws ExisteCliente {
        return clientes.addCliente(cliente);
    }
    public Cliente removeCliente(String nif) throws NoExisteCliente {
        return clientes.removeCliente(nif);
    }
    public boolean cambioTarifa(Tarifa tarifa,String nif) throws TarifaNula {
        return clientes.cambioTarifa(tarifa,nif);
    }
    public Cliente getDatos(String nif) throws NoExisteCliente {
        return clientes.getDatos(nif);
    }
    public Factura getFactura(int cod){
        return facturas.getFactura(cod);
    }

    //Get datos acotado por fecha
    public Collection<Cliente> listaClientes(ParFecha par){
        return clientes.listaClientes(par);
    }

    public Collection<Llamada> listaLlamadas(String nif, ParFecha par) throws NoExisteCliente {
        return clientes.listaLlamadas(nif,par);
    }

    public Collection<Factura> listaFacturas(String nif, ParFecha par) {
        return facturas.listaFacturas(nif,par);
    }
}
