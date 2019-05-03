package Control;

public enum Planes {
    SEMANAL("La primera semana de todos los meses a 0,05€ min",0.05),
    DIARIA("Los lunes llamadas a 0,10€ min",0.10),
    HORARIA("De 20:00 a 24:00 llamadas a 0,15€ min",0.15),
    SALIR("Salir",0);


    private String descripcion;
    private  double precio;
    private Planes(String descripcion,double precio){
        this.descripcion=descripcion;
        this.precio=precio;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public static Planes getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(Planes opcion: Planes.values()) {
            sb.append(opcion.ordinal());
            sb.append("//");
            sb.append(opcion.getDescripcion());
            sb.append("\n");
        }
        return sb.toString();
    }
}
