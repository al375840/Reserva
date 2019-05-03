package Modelo;

import Cliente.Cliente;
import Excepciones.ExisteCliente;

public interface Modelo {
     void addParticular(Cliente cliente) ;
     Object[][]DevolverTablaClientes();


    void addEmpresa(Cliente empresa);

    void guardar();
    void cargar();
}
