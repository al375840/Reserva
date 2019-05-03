package Modelo;

import Cliente.Cliente;
import Control.ClientesFacturas;
import Datos.Dir;
import Excepciones.ExisteCliente;
import MetodosUtilidad.SerializarDatos;
import Vista.ImpVista;
import Vista.Vista;

import java.io.IOException;

public class ImpModelo implements Modelo {
    Vista vista;
    ClientesFacturas datos;




    public ImpModelo() {
        datos=new ClientesFacturas();
    }

    public void addParticular(Cliente cliente)  {
        try {
            datos.addCliente(cliente);
        } catch (ExisteCliente existeCliente) {
            existeCliente.printStackTrace();
        }

    }
    @Override
    public Object[][] DevolverTablaClientes() {
        Object[][]datuelos= new Object[1000][7];
        int i =0;
        for(Cliente cli: datos.getClientes().getListaClientes() ){
            datuelos[i][0]=cli.getNombre();
            datuelos[i][1]=cli.getNif();
            datuelos[i][2]=cli.getCorreoE();
            datuelos[i][3]=cli.getnTelf();
            Dir direccion = cli.getDirec();
            datuelos[i][4]=direccion.getCodP();
            datuelos[i][5]=direccion.getPoblacion();
            datuelos[i][6]=direccion.getProv();
            i++;
        }
        return datuelos;
    }


    @Override
    public void addEmpresa(Cliente empresa) {
        try {
            datos.addCliente(empresa);
        } catch (ExisteCliente existeCliente) {
            existeCliente.printStackTrace();
        }
    }

    @Override
    public void guardar() {
        try {
            SerializarDatos.guardar("datos.ban",datos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargar() {
        try {
            datos=SerializarDatos.cargar("datos.ban");
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Fichero no existe");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }
}
