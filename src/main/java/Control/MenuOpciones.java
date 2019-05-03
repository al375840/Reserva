package Control;

public enum MenuOpciones {
    DAR_DE_ALTA("Dar de alta a un cliente"),
    BORRAR_CLIENTE("Borrar cliente"),
    CAMBIO_TARIFA("Cambiar tarifa cliente"),
    BUSQUEDA_NIF("Buscar a cliente partir de su nif"),
    LISTADO_CLIENTES("Listar clientes"),
    ALTA_LLAMADA("Dar de alta nueva llamada"),
    LISTAR_LLAMADAS("Listar todas las llamadas de un cliente"),
    EMITIR_FACTURA("Emitir una nueva factura"),
    CONSULTAR_FACTURA("Consultar datos factura "),
    FACTURAS_CLIENTE("Listar todas las facturas de un cliente"),
    LISTA_CLIENTES_FECHA("Listar clientes dados de alta entre dos fechas"),
    LISTA_LLAMADAS_FECHA("Listar llamadas de un cliente entre dos fechas."),
    LISTA_FACTURAS_FECHA("Listar facturas de un cliente entre dos fechas."),
    SALIR("Salir");


    private String descripcion;

    private MenuOpciones(String descripcion){
        this.descripcion=descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public static MenuOpciones getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(MenuOpciones opcion: MenuOpciones.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.getDescripcion());
            sb.append("\n");
        }
        return sb.toString();
    }
}
