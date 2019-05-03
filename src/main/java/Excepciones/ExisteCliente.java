package Excepciones;

public class ExisteCliente extends Exception {
    public ExisteCliente() {
        super("Ya Existe este cliente en la BASE");
    }
}
