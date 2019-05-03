import Cliente.Cliente;
import Datos.Factura;
import Datos.Llamada;
import Datos.Tarifa.Tarifa;
import Fabrica.Fabrica;
import Fabrica.Fabricador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class pruebasRecubrimiento {
    Fabrica fabrica=new Fabricador();

    Llamada llamada;
    int dia;
    int semana;
    Calendar calendar;
    @Before
    public void inicia(){
        calendar=new GregorianCalendar();
        calendar.set(2019,1,1);
         llamada=new Llamada(43241,calendar ,120);
         dia= calendar.get(Calendar.DAY_OF_WEEK);
         semana=calendar.get(Calendar.WEEK_OF_MONTH);
    }
    @Test
    public void TestRecubreDosCapas() {
        Tarifa tarifa;
        tarifa=fabrica.getTarifaBasica(2);
        tarifa=fabrica.getTarifaDiaria(tarifa,1,dia);
        System.out.println("Precio tarifa con reubrimiento diario:"+tarifa.getPrecio(llamada));
        Assert.assertEquals(1,tarifa.getPrecio(llamada),0);
        tarifa=fabrica.getTarifaBasica(2);
        tarifa=fabrica.getTarifaDiaria(tarifa,1,dia);
        tarifa=fabrica.getTarifaDiaria(tarifa,3,dia);
        Assert.assertEquals(1,tarifa.getPrecio(llamada),0);
        tarifa=fabrica.getTarifaBasica(2);
        tarifa=fabrica.getTarifaDiaria(tarifa,1,dia);
        tarifa=fabrica.getTarifaDiaria(tarifa,0.5,dia-1);
        Assert.assertEquals(1,tarifa.getPrecio(llamada),0);
    }

    @Test
    public void TestTarifasSemanales() {
        Tarifa tarifa=fabrica.getTarifaBasica(0.5);
        tarifa=fabrica.getTarifaSemanal(tarifa,0.2,semana);
        tarifa=fabrica.getTarifaDiaria(tarifa,0.19,dia);
        System.out.println("Precio tarifa semana ["+semana+"]:"+tarifa.getPrecio(llamada));
       Assert.assertEquals(0.19,tarifa.getPrecio(llamada),0);
    }

    @Test
    public void TestTarifaHoraria() {
        calendar.set(Calendar.HOUR_OF_DAY,23);
        Tarifa tarifa=fabrica.getTarifaBasica(0.5);
        tarifa=fabrica.getTarifaSemanal(tarifa,0.2,semana);
        tarifa=fabrica.getTarifaDiaria(tarifa,0.19,dia);
        tarifa=fabrica.getTarifaHoraria(tarifa,0.01,22,24);
        System.out.println("Precio tarifa a las 23:00 :"+tarifa.getPrecio(llamada));
        Assert.assertEquals(0.01,tarifa.getPrecio(llamada),0);
        calendar.set(Calendar.HOUR_OF_DAY,1);
        System.out.println("Precio tarifa a la 1:00 :"+tarifa.getPrecio(llamada));
        Assert.assertEquals(0.19,tarifa.getPrecio(llamada),0);
    }

    @Test
    public void TestImporteFacturas() {
        calendar.set(Calendar.HOUR_OF_DAY,23);
        Tarifa tarifa=fabrica.getTarifaBasica(0.5);
        tarifa=fabrica.getTarifaSemanal(tarifa,0.2,semana);
        tarifa=fabrica.getTarifaDiaria(tarifa,0.19,dia);
        tarifa=fabrica.getTarifaHoraria(tarifa,0.01,22,24);
        pruebas pruebas=new pruebas();
        Calendar calendarINI=new GregorianCalendar();
        calendarINI.set(2019,1,1);
        Calendar calendarFIN=new GregorianCalendar();
        calendarFIN.set(2019,2,1);
        Cliente cliente=pruebas.generaCliente();
        cliente.addLlamada(llamada);
        Factura factura= new Factura(1,tarifa,Calendar.getInstance(),
                calendarINI,calendarFIN,cliente);
        double importe=factura.calculaImporte();
        Assert.assertEquals(0.02,factura.calculaImporte(),0);
    }
}
