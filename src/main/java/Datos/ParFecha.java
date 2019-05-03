package Datos;

import Excepciones.IntervaloNoCorrecto;

import java.util.Calendar;

public class ParFecha {
    Calendar ini;
    Calendar fin;

    public ParFecha(Calendar ini, Calendar fin) throws IntervaloNoCorrecto {
        if(ini.after(fin))
            throw new IntervaloNoCorrecto();
        this.ini = ini;
        this.fin = fin;
    }

    public Calendar getIni() {
        return ini;
    }

    public Calendar getFin() {
        return fin;
    }
}
