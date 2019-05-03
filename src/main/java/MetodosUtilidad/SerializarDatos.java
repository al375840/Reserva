package MetodosUtilidad;

import Control.ClientesFacturas;

import java.io.*;

public class SerializarDatos {

    public static void guardar(String fichero, ClientesFacturas datos) throws IOException {
        FileOutputStream fos = new FileOutputStream(fichero);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(datos);
        oos.close();
    }
    public static ClientesFacturas cargar(String fichero) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fichero);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ClientesFacturas datos=(ClientesFacturas) ois.readObject();
        ois.close();
        return datos;
    }


}
