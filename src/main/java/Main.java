import Controlador.ImpControlador;
import Modelo.ImpModelo;
import Vista.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ImpControlador controlador =new ImpControlador();
        ImpModelo modelo=new ImpModelo();
        ImpVista vista=new ImpVista();
        controlador.setModelo(modelo);
        controlador.setVista(vista);
        vista.setControlador(controlador);
        vista.setImpModelo(modelo);
        modelo.setVista(vista);
        vista.creaGUI();

    }
}
