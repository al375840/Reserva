package Vista;


import Cliente.Cliente;
import Controlador.Controlador;
import Fabrica.*;
import Modelo.Modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.List;

public class ImpVista implements Vista {
    private Modelo modelo;
    private Controlador controlador;
    private EntradaDatos nombre;
    private EntradaDatos apellidos;
    private EntradaDatos nif;
    private EntradaDatos correo;
    private EntradaDatos telf;
    private EntradaDatos codp;
    private EntradaDatos prov;
    private EntradaDatos pob;
    private Fabrica fabrica=new Fabricador();
    private JFrame mainWindow;
    private String [] nombrescolumnas={"Nombre","NIF","Correo","Teléfono","CP","Población","Provincia"};
    private Object [][] nombresfilas;

     private void GUI() {
        mainWindow = new JFrame("Menú principal");
        Container contenedor = mainWindow.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JButton addClient = new JButton("Añadir cliente");
        addClient.setAlignmentX(Component.CENTER_ALIGNMENT);
        addClient.addActionListener(new Escuchador());
        JButton listarClientes = new JButton("Listar Clientes");
        listarClientes.addActionListener(new Escuchador());
        listarClientes.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(addClient);
        contenedor.add(listarClientes);
        //ventana.pack();
        mainWindow.setSize(400, 400);
        mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.guardar();
                System.exit(0);
            }
        });
        mainWindow.setVisible(true);
    }


    public void setImpModelo(Modelo impModelo) {
        this.modelo = impModelo;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void creaGUI() {
        controlador.cargar();
        SwingUtilities.invokeLater(() -> GUI());
    }

    class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String texto = boton.getText();
            switch (texto) {
                case "Añadir cliente":
                    controlador.ventanaAddClient();
                    break;
                case "Listar Clientes":
                    controlador.ventanaListClient();
            }
        }
    }

    public void creaAddCliente() {
        SwingUtilities.invokeLater(() -> GUIAddCli());
    }

    private void GUIAddCli() {
        JFrame ventana = new JFrame("Registrar cliente");
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));

        nombre = new EntradaDatos("Nombre: ");
        apellidos = new EntradaDatos("Apellidos: ");
        nif = new EntradaDatos("NIF: ","");
        correo = new EntradaDatos("Correo: ");
        telf = new EntradaDatos("Teléfono: ");
        codp = new EntradaDatos("C.P: ");
        prov = new EntradaDatos("Provincia: ");
        pob = new EntradaDatos("Población: ");
        //botones
        JPanel botones=new JPanel();
        JButton gCli=new JButton("añadir cliente");
        gCli.setAlignmentX(Component.LEFT_ALIGNMENT);
        gCli.addActionListener(e -> controlador.guardaParticular());
        JButton gEmp=new JButton("añadir empresa");
        gEmp.setAlignmentX(Component.RIGHT_ALIGNMENT);
        gEmp.addActionListener(e -> controlador.guardaEmpresa());
        botones.add(gCli);
        botones.add(gEmp);

        contenedor.add(nombre.generaPanel());
        contenedor.add(apellidos.generaPanel());
        contenedor.add(nif.generaPanel());
        contenedor.add(correo.generaPanel());
        contenedor.add(telf.generaPanel());
        contenedor.add(codp.generaPanel());
        contenedor.add(pob.generaPanel());
        contenedor.add(prov.generaPanel());

        contenedor.add(botones);
        ventana.pack();
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.mostrarMenu();
            }
        });
        ventana.setVisible(true);
     }



    @Override
    public void creaListCliente (){
        SwingUtilities.invokeLater(() -> GUIListCli());
    }


    private void GUIListCli() {
        JFrame tabla = new MarcoTabla();
        Container contenedor = tabla.getContentPane();
        tabla.setSize(400, 400);
        tabla.setVisible(true);
        tabla.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.mostrarMenu();
            }
        });
    }


    class MarcoTabla extends JFrame{
         public MarcoTabla(){
             setTitle("Clientes");
             setBounds(150,150,150,150);
             nombresfilas=controlador.ActualizarClientes();
             JTable tablero = new JTable(nombresfilas,nombrescolumnas);
             add(new JScrollPane(tablero),BorderLayout.CENTER);
         }
    }


    public Cliente getParticular(){
        return fabrica.getParticular(nombre.getEntrada(),apellidos.getEntrada(),nif.getEntrada(),
                correo.getEntrada(), Calendar.getInstance(),fabrica.getTarifaBasica(0.25),fabrica.getDir(Integer.parseInt(codp.getEntrada()),prov.getEntrada(),pob.getEntrada()), Integer.parseInt(telf.getEntrada()));
    }
    public Cliente getEmpresa(){
        return fabrica.getEmpresa(nombre.getEntrada(),nif.getEntrada(),
                correo.getEntrada(), Calendar.getInstance(),fabrica.getTarifaBasica(0.25),fabrica.getDir(Integer.parseInt(codp.getEntrada()),prov.getEntrada(),pob.getEntrada()), Integer.parseInt(telf.getEntrada()));
    }
    public void mostrarMenu(){
        mainWindow.setVisible(true);
    }


    public void esconderMenu(){
        mainWindow.setVisible(false);
    }
    private class EntradaDatos {

        String textoDef;
        JLabel txt;
        JTextField entrada;
        private EntradaDatos(String texto) {
            this.textoDef="";
            entrada = new JTextField(20);
            entrada.setText(textoDef);
            txt =new JLabel();
            txt.setText(texto);
        }
        private EntradaDatos(String texto, String textoDef) {
            this.textoDef=textoDef;
            entrada = new JTextField(20);
            entrada.setText(textoDef);
            txt =new JLabel();
            txt.setText(texto);
        }
        private JPanel generaPanel(){
            JPanel panel=new JPanel();
            //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panel.setLayout(new GridLayout(1,2));
            panel.add(txt);
            panel.add(entrada);
            return panel;
        }

        public String getTextoDef() {
            return textoDef;
        }

        private String getEntrada() {
            return entrada.getText();
        }
    }
}
