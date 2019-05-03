package MetodosUtilidad;

import Datos.Fecha;
import Datos.ParFecha;
import java.util.Collection;
import java.util.LinkedList;

public class Funcion {
    public static <T extends Fecha> Collection<T>  acotaFecha(Collection<T> datos, ParFecha par){
        LinkedList<T> ret=new LinkedList<>();
        for(T dato:datos){
            if(dato.getFecha().compareTo(par.getIni())>=0 && dato.getFecha().compareTo(par.getFin())<=0)
                ret.add(dato);
        }

        return ret;
    }
}
