package Excepciones;

public class IntervaloNoCorrecto extends Exception{
    public IntervaloNoCorrecto() {
        super("Este intervalo no es correcto (inicial antes que final)");
    }
}
