package Excepciones;

public class TarifaNula extends Exception {
    public TarifaNula() {
        super("Esta tarifa es igual a null");
    }
}
