package Controlador;

import Modelo.Modelo;
import Vista.Vista;

public class ImpControlador implements Controlador{
    Modelo impModelo;
    Vista vista;


    public void setModelo(Modelo modelo) {
        this.impModelo = modelo;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    @Override
    public void guardaParticular() {
            impModelo.addParticular(vista.getParticular());

    }
    @Override
    public  Object[][] ActualizarClientes(){
        return impModelo.DevolverTablaClientes();
    }

    @Override
    public void guardaEmpresa() {
            impModelo.addEmpresa(vista.getEmpresa());
    }

    @Override
    public void guardar() {
        impModelo.guardar();
    }

    @Override
    public void cargar() {
        impModelo.cargar();
    }

    @Override
    public void ventanaAddClient() {

        vista.creaAddCliente();
        esconderMenu();
    }
    @Override
    public void ventanaListClient() {

        vista.creaListCliente();
        esconderMenu();
    }


    public void esconderMenu() {
        vista.esconderMenu();
    }
    public void mostrarMenu(){
        vista.mostrarMenu();
    }

}
