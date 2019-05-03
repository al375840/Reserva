import Cliente.Particular;
import Control.ClientesFacturas;
import Datos.Dir;
import Datos.ParFecha;
import Datos.Tarifa.Tarifa;
import Datos.Tarifa.TarifaBasica;
import Excepciones.ExisteCliente;
import Excepciones.IntervaloNoCorrecto;
import Excepciones.NoExisteCliente;
import Excepciones.TarifaNula;
import org.junit.Before;
import org.junit.Test;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestExcepciones {
    ClientesFacturas datos;
    @Before
    public void generaDatos() throws Exception {
        datos=new ClientesFacturas();
        for (int i = 0; i < 10; i++) {
            datos.addCliente(new pruebas().generaCliente());
        }

        datos.addCliente(new Particular("J","B",
                "1","J@B.com",Calendar.getInstance(),new TarifaBasica(1),new Dir(12000,"C","V"),644223444));
    }

    @Test(expected=IntervaloNoCorrecto.class)
    public void intervaloIncorrecto() throws IntervaloNoCorrecto {
            Calendar ini =new GregorianCalendar();
            Calendar fin =new GregorianCalendar();
            fin.set(2020,4,14);
            ini.set(2019,1,14);
            ParFecha par=new ParFecha(fin,ini);
    }

    @Test(expected = ExisteCliente.class)
    public void ExisteCliente() throws ExisteCliente {
        datos.addCliente(new Particular("J","B",
                "1","J@B.com",Calendar.getInstance(),new TarifaBasica(1),new Dir(12000,"C","V"),644223444));
    }

    @Test(expected = NoExisteCliente.class)
    public void noExisteCliente() throws NoExisteCliente {
        datos.getDatos("-15");
    }

    @Test(expected = TarifaNula.class)
    public void tarifaNula() throws TarifaNula {
        datos.cambioTarifa(null,"1");
    }
}
