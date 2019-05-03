package Control;

import Cliente.*;
import Datos.*;
import Datos.ParFecha;
import Datos.Tarifa.Tarifa;
import Datos.Tarifa.TarifaBasica;
import Excepciones.ExisteCliente;
import Excepciones.IntervaloNoCorrecto;
import Excepciones.NoExisteCliente;
import Excepciones.TarifaNula;
import Fabrica.*;
import MetodosUtilidad.SerializarDatos;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;


public class GestionMenu {
    public static void main(String[] args) throws Exception {
        new GestionMenu().ejecutaMenu();
    }
    Fabrica fabrica=new Fabricador();
    ClientesFacturas datos;
    Scanner sc =new Scanner(System.in);
    boolean continuar;
    public void ejecutaMenu() throws Exception {
       continuar=true;
        cargaDatos();
        do{
        System.out.println(MenuOpciones.getMenu());
        int opcion=pedirOpcion();
        while (opcion>=MenuOpciones.values().length){
            System.out.println("Opcion incorrecta");
            opcion=pedirOpcion();
        }
        MenuOpciones funcion =MenuOpciones.getOpcion(opcion);
        ejecutaFuncion(funcion);


        }while (continuar);
        guardarDatos();
    }

    private int pedirOpcion() {
        System.out.println("Elige una opción:");
        int opcion=sc.nextByte();
        return opcion;
    }

    private void ejecutaFuncion(MenuOpciones funcion) throws IntervaloNoCorrecto, NoExisteCliente, TarifaNula, ExisteCliente {
        switch (funcion){
            case DAR_DE_ALTA:
                darDeAlta();
                break;
            case BUSQUEDA_NIF:
                buscarNif();
                break;
            case LISTADO_CLIENTES:
                listaClientes();
                break;
            case SALIR:
                continuar=false;
                break;
            case ALTA_LLAMADA:
                altaLlamada();
                break;
            case CAMBIO_TARIFA:
                cambioTarifa();
                break;
            case BORRAR_CLIENTE:
                borraCliente();
                break;
            case EMITIR_FACTURA:
                emiteFactura();
                break;
            case LISTAR_LLAMADAS:
                listaLlamadas();
                break;
            case CONSULTAR_FACTURA:
                consultaFactura();
                break;
            case FACTURAS_CLIENTE:
                facturasCliente();
                break;
            case LISTA_CLIENTES_FECHA:
                listaClientesFecha();
                break;
            case LISTA_LLAMADAS_FECHA:
                listaLlamadasFecha();
                break;
            case LISTA_FACTURAS_FECHA:
                listaFacturasFecha();
                break;

        }
    }


    private ParFecha consultaParFechas() throws IntervaloNoCorrecto {
        System.out.println("--Fecha inicio--");
        Calendar ini=consultaFecha();
        System.out.println("--Fecha fin--");
        Calendar fin =consultaFecha();
        return new ParFecha(ini,fin);
    }
    private  String consultaNif(){
        System.out.println("Introduce NIF cliente:");
        String nif =sc.next();
        return nif;
    }
    private Cliente consultaCliente() throws NoExisteCliente {
        String nif=consultaNif();
        Cliente cliente=datos.getDatos(nif);
        return cliente;

    }
    private Calendar consultaFecha(){
        System.out.println("Introduce dia:");
        int dia=sc.nextInt();
        System.out.println("Introduce mes:");
        int mes=sc.nextInt();
        System.out.println("Introduce año:");
        int año=sc.nextInt();
        Calendar fecha =new GregorianCalendar();
        fecha.set(año,mes-1,dia);
        return fecha;
    }
    private void listaLlamadas() throws NoExisteCliente {
        Cliente cliente=consultaCliente();
        if(cliente == null){
            System.out.println("Nif incorrecto");
            return;
        }
        System.out.println(cliente.getLlamadas());
    }

    private void borraCliente() throws NoExisteCliente {
        String nif=consultaNif();
        Cliente cliente=datos.removeCliente(nif);
        System.out.println("El cliente "+cliente.getNombre()+" ha sido eliminado");
    }

    private void altaLlamada() throws NoExisteCliente {
        Cliente cliente=consultaCliente();
        if(cliente==null){
            System.out.println("Cliente no existe");
            return;
        }
        System.out.println("Nº Telf destinatario:");
        int destino=sc.nextInt();
        System.out.println("Introduce duración en segundos:");
        int duracion =sc.nextInt();
        cliente.addLlamada(new Llamada(destino,duracion));

    }

    private void darDeAlta() throws ExisteCliente {
        Tarifa tarifaBase=fabrica.getTarifaBasica(1.5f);
        boolean particular=false;
        String apellidos="";
        System.out.println("particular(1) o empresa(2)");
        particular=(sc.nextInt()==1);
        System.out.println("Introduce nombre");
        String nombre=sc.next();
        if(particular){
            System.out.println("Introduce apellidos:");
            StringBuilder sb = new StringBuilder();
            sb.append(sc.next());
            sb.append(" ");
            sb.append(sc.next());
            apellidos=sb.toString();
        }
        System.out.println("Introduce nif:");
        String nif =sc.next();
        System.out.println("Introduce ntelf:");
        int ntelf=sc.nextInt();
        System.out.println("Introduce correo electónico:");
        String correo=sc.next();
        System.out.println("Introduce codigo provincial:");
        int codP=sc.nextInt();
        System.out.println("Nombre provincia:");
        String prov=sc.next();
        System.out.println("Nombre poblacion:");
        String poblacion=sc.next();
        Dir dir=new Dir(codP,prov,poblacion);
        if(particular){
            datos.addCliente(fabrica.getParticular(nombre,apellidos,nif,correo,
                    Calendar.getInstance(),tarifaBase,dir,ntelf));
        }else{
            datos.addCliente(fabrica.getEmpresa(nombre,nif,correo,
                    Calendar.getInstance(),tarifaBase,dir,ntelf));
        }
    }

    private void buscarNif() throws NoExisteCliente {
        Cliente cliente=consultaCliente();
        if(cliente==null){
            System.out.println("cliente no existe");
        }
        else
            System.out.println(cliente);
    }
    private void listaClientes() {
        for(Cliente cliente:datos.getClientes().getListaClientes()){
            System.out.println(cliente);
        }
    }
    private void cambioTarifa() throws TarifaNula, NoExisteCliente {
        Cliente cliente= consultaCliente();
        System.out.println("Indique precio tarifa base:");
        Tarifa tarifa =fabrica.getTarifaBasica(sc.nextDouble());
        Planes plan;
        do{
        System.out.println(Planes.getMenu());
        int opcion=pedirOpcion();
        plan=Planes.getOpcion(opcion);
        switch (plan){
            case DIARIA:
                tarifa=fabrica.getTarifaDiaria(tarifa,plan.getPrecio(),Calendar.MONDAY);
                break;
            case HORARIA:
                tarifa=fabrica.getTarifaHoraria(tarifa,plan.getPrecio(),22,24);
                break;
            case SEMANAL:
                tarifa=fabrica.getTarifaSemanal(tarifa,plan.getPrecio(),0);
                break;
        }
        }while (plan != Planes.SALIR);
        cliente.setTarifa(tarifa);
    }
    private void emiteFactura() throws IntervaloNoCorrecto, NoExisteCliente {
        Cliente cliente=consultaCliente();
        ParFecha par=consultaParFechas();
        datos.emitirFactura(cliente,par.getIni(),par.getFin());

    }
    private void consultaFactura(){
        System.out.println("Introduce código factura:");
        int cod=sc.nextInt();
        Factura factura=datos.getFactura(cod);
        if(factura != null)
            System.out.println(factura);
        else System.out.println("Factura con código "+cod+" no existe.");

    }
    private void facturasCliente(){
        String nif =consultaNif();
        List<Factura> listaFacturas =datos.facturasCliente(nif);
        for(Factura fac:listaFacturas)
            System.out.println(fac);
    }

    private void listaClientesFecha() throws IntervaloNoCorrecto {
        ParFecha par=consultaParFechas();
        for(Cliente cli: datos.listaClientes(par))
            System.out.println(cli);
    }
    private void listaLlamadasFecha() throws IntervaloNoCorrecto, NoExisteCliente {
        String nif=consultaNif();
        ParFecha par=consultaParFechas();
        for (Llamada llam:datos.listaLlamadas(nif,par))
            System.out.println(llam);
    }
    private void listaFacturasFecha() throws IntervaloNoCorrecto {
        String nif=consultaNif();
        ParFecha par=consultaParFechas();
        for(Factura fac: datos.listaFacturas(nif,par))
            System.out.println(fac);
    }
    private String preguntaNombreFichero(){
        System.out.println("Introduce nombre fichero:");
        return sc.next();
    }
    private void cargaDatos() throws IOException, ClassNotFoundException {
        String nombre;
        System.out.println("cargar datos? (y/n)");
        if(sc.next().equals("y")){
            nombre=preguntaNombreFichero()+".ban";
            datos=SerializarDatos.cargar(nombre);
        }else{
            datos=new ClientesFacturas();
        }

    }

    private void guardarDatos() throws IOException {
        System.out.println("Guardar datos? (y/n)");
        if(sc.next().equals("y")){
            SerializarDatos.guardar(preguntaNombreFichero()+".ban",datos);
        }
    }
}
