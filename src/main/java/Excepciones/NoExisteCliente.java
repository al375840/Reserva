package Excepciones;

public class NoExisteCliente extends Exception {
    public NoExisteCliente(String nif) {
        super("No existe cliente con nif:"+nif);
    }
}
