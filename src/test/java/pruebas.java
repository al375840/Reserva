import Cliente.Cliente;
import Cliente.Particular;
import Control.Clientes;
import Control.Facturas;
import Datos.*;
import Datos.Tarifa.Tarifa;
import Datos.Tarifa.TarifaBasica;
import Excepciones.ExisteCliente;
import Excepciones.NoExisteCliente;
import es.uji.www.GeneradorDatosINE;
import org.junit.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class pruebas {
    Cliente cliente;
    Clientes clientes;
    Facturas facturas;
    Llamada llamada;
    @Before
    public void init() throws ExisteCliente {
        facturas=new Facturas();
        clientes=new Clientes();
        Calendar fecha=Calendar.getInstance();

        cliente= new Particular("Jaume","Barrios",
                "18463174Q","J@u.com",fecha,new TarifaBasica(1),new Dir(12600,"Castellon","La Vall'uixó"),644223444);

        Llamada llamada=new Llamada(644223344,60);
        cliente.addLlamada(llamada);
        clientes.addCliente(cliente);

    }

    public  Cliente generaCliente(){
        GeneradorDatosINE generador= new GeneradorDatosINE();
        Calendar fecha=Calendar.getInstance();
        return new Particular(generador.getNombre(),generador.getApellido()
                ,generador.getNIF(),generador.getNombre()+"@gmail.com",fecha ,new TarifaBasica(1.5f),new Dir(12000,generador.getProvincia(),generador.getPoblacion(generador.getProvincia())),Integer.parseInt(generaNumeroTelefono()));

    }
    private  String generaNumeroTelefono() {
        String telefono = "";
        MyRandom rnd = new MyRandom();
        for(int x=0;x<9;x++){

            telefono+=rnd.nextNonNegative();
    }
    return telefono ;
    }

    @Test
    public void TestAñadeCliente() throws NoExisteCliente, ExisteCliente {
        clientes.removeCliente(cliente.getNif());
        clientes.addCliente(cliente);
        assertEquals(cliente,clientes.getDatos("18463174Q"));
        Llamada llamada=new Llamada(1777777,60);
        cliente.addLlamada(llamada);
    }
    @Test
    public void TestBorrarCliente() throws ExisteCliente, NoExisteCliente {
       try {
            clientes.removeCliente(cliente.getNif());
            clientes.addCliente(cliente);
            Llamada llamada=new Llamada(1777777,60);
            cliente.addLlamada(llamada);
        } catch (Exception e) {
            fail();
       }
    }


    @Test
    public void TestTarifa() throws NoExisteCliente, ExisteCliente {
        String telefono = generaNumeroTelefono();
        clientes.getDatos("18463174Q").setTarifa(new TarifaBasica(5.0f));
        assertEquals(5,clientes.getDatos("18463174Q").getTarifa().getPrecio(),0);

    }

    @Test
    public void CambiarTarifa() {
        cliente.setTarifa(new TarifaBasica(2.0f));
        assertEquals(2,cliente.getTarifa().getPrecio(),0);
    }
    @Test
    public void RecuperarDatos() throws NoExisteCliente, ExisteCliente {
        assertEquals(0,clientes.getDatos("18463174Q").getNombre().compareTo("Jaume Barrios"));
        assertEquals(0,clientes.getDatos("18463174Q").getCorreoE().compareTo("J@u.com"));
    }
    @Test
    public void ListadoClientes() throws ExisteCliente {
        for (int i = 0; i < 10; i++)
            clientes.addCliente(generaCliente());
        assertEquals(11, clientes.getListaClientes().size());
    }

    @Test
    public void DarDeAltaUnaLlamada() {
        Llamada llamada=new Llamada(1,1);
        cliente.addLlamada(llamada);
        assertTrue(cliente.getLlamadas().contains(llamada));
    }

    @Test
    public void ListarTodasLasLlamadas() {

        assertEquals(1,cliente.getLlamadas().size());
        for(int x=1;x<11;x++)cliente.addLlamada(new Llamada(737373737,x));
        assertEquals(11,cliente.getLlamadas().size());
    }
    @Test
    public void EmitirFactura() {
        Calendar diaAnterior =new GregorianCalendar();
        diaAnterior.set(1999,1,1);
        Calendar diaPosterior =new GregorianCalendar();
        diaAnterior.set(2030,11,29);
        facturas.emitirFactura(cliente,diaAnterior, diaPosterior);
        assertEquals(1,facturas.facturasCliente(cliente.getNif()).size());

    }
    @Test
    public void RecuperarLosDatosDeUnaFactura() {
        Calendar diaAnterior =new GregorianCalendar();
        diaAnterior.set(1999,1,1);
        Calendar diaPosterior =new GregorianCalendar();
        diaAnterior.set(2030,11,29);
        facturas.emitirFactura(cliente,diaAnterior, diaPosterior);
        Factura fact=facturas.getFactura(0);
        System.out.println(fact);
        assertEquals(0,fact.getCliente().compareTo("18463174Q"));
    }
    @Test
    public void RecuperarTodasLasFacturas() {
        Calendar diaAnterior =new GregorianCalendar();
        diaAnterior.set(1999,1,1);
        Calendar diaPosterior =new GregorianCalendar();
        diaAnterior.set(2030,11,29);
        facturas.emitirFactura(cliente,diaAnterior, diaPosterior);
        assertEquals(1,cliente.getLlamadas().size());
        assertEquals(1,facturas.facturasCliente(cliente.getNif()).size());

    }





}
