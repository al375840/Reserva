import Cliente.Cliente;
import Cliente.Particular;
import Control.Clientes;
import Control.Facturas;
import Datos.ParFecha;
import Datos.Llamada;
import Datos.*;
import Datos.Tarifa.Tarifa;
import Datos.Tarifa.TarifaBasica;
import Excepciones.ExisteCliente;
import Excepciones.IntervaloNoCorrecto;
import es.uji.www.GeneradorDatosINE;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class TestFuncion {
    private ParFecha intervalos;
    private Clientes clientes;
    private Facturas facturas;
    private Cliente cliente1;
    private Cliente cliente2;
    private String DNI1;
    private Factura factura1;
    private String DNI2;
    private Factura factura2;
    private Calendar ini;
    private Calendar fin;
    private Calendar date;
    GeneradorDatosINE generador;

    private int numero;

    @Before
    public void init() throws ExisteCliente {
        numero=0;
        generador = new GeneradorDatosINE();
        facturas = new Facturas();
        clientes = new Clientes();
        ini = new GregorianCalendar();
        fin = new GregorianCalendar();
        date = new GregorianCalendar();
        Calendar fecha = Calendar.getInstance();
        cliente2 = new Particular();
        cliente1 = new Particular("Heisemberg", "Magdalena",
                "99999999X", "lol@u.com", fecha, new TarifaBasica(5), new Dir(12600, "Demacia", "Grieta del invocador"), 644223449);
        DNI1 = "99999999X";
        for (int x = 1; x < 11; x++) {
            date.set(x, x, x);
            cliente1.addLlamada(new Llamada(98989898, date, x));
            cliente2 = generaCliente();

            for (int y = 20; y < 30; y++) {
                date.set(y, x, y);
                cliente2.addLlamada(new Llamada(33333333, date, y));
                cliente1.addLlamada(new Llamada(33333333, date, y));
            }
            clientes.addCliente(cliente2);
            cliente2.setFechaAlta(generarCalendario());
        }
        clientes.addCliente(cliente1);
        for (int x = 2; x < 12; x++) {
            Calendar ini2 = new GregorianCalendar();
            ini2.set(x, x, x);
            Calendar fin2 = new GregorianCalendar();
            fin2.set(1, 1, 20030);
            date.set(x*10,x,x);
            int cod=facturas.emitirFactura(cliente1, ini2, fin2);
            facturas.getFactura(cod).setFechaFacturacion(date);
            cod=facturas.emitirFactura(cliente2, ini2, fin2);
            facturas.getFactura(cod).setFechaFacturacion(date);
        }
    }

    @Test
    public void TestListadoClientes() throws IntervaloNoCorrecto {
        ini.set(0, 1, 1);
        fin.set(999999, 9, 1);
        intervalos = new ParFecha(ini, fin);
        assertEquals(11, clientes.listaClientes(intervalos).size(), 0);
        fin.set(10,2,3);
        intervalos = new ParFecha(ini, fin);
        assertEquals(9, clientes.listaClientes(intervalos).size(), 0);

    }


    public Cliente generaCliente() {
        GeneradorDatosINE generador = new GeneradorDatosINE();
        Calendar fecha = Calendar.getInstance();
        return new Particular(generador.getNombre(), generador.getApellido()
                , generador.getNIF(), generador.getNombre() + "@gmail.com", fecha, new TarifaBasica(1.5), new Dir(12000, generador.getProvincia(), generador.getPoblacion(generador.getProvincia())), Integer.parseInt(generaNumeroTelefono()));

    }
    public Calendar generarCalendario() {
        Calendar Random=new GregorianCalendar();
        numero++;
        Random.set(numero,numero,numero);
        return Random;
    }

    private String generaNumeroTelefono() {
        String telefono = "";
        MyRandom rnd = new MyRandom();
        for (int x = 0; x < 9; x++) {

            telefono += rnd.nextNonNegative();
        }
        return telefono;
    }
}
