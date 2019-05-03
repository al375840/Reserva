package Controlador;

import Excepciones.ExisteCliente;

public interface Controlador {
    void guardaParticular();

    Object[][] ActualizarClientes();

    void guardaEmpresa();

    void guardar();
    void cargar();

    void ventanaAddClient();
    void mostrarMenu();
    void esconderMenu();

    void ventanaListClient();
}
