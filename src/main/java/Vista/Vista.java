package Vista;

import Cliente.Cliente;

import java.util.List;

public interface Vista {
    void creaAddCliente();
    Cliente getParticular();
    Cliente getEmpresa();
    void esconderMenu();
    void mostrarMenu();

    void creaListCliente();
}
