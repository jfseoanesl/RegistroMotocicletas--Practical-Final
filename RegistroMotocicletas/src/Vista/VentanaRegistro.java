package Vista;

import Modelo.Motocicleta;
import Negocio.GestionMotocicletas;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class VentanaRegistro extends JDialog {

    private JLabel lPlaca, lMarca, lModelo, lkms, lValor;
    private JTextField tPlaca, tKms, tValor;
    private JComboBox cMarca;
    private JRadioButton rModelo1, rModelo2, rModelo3;
    private ButtonGroup grupoRadio;
    private JButton bGuardar, bCancelar, bNuevo, bBuscar, bEliminar;

    private JLabel lDescuento;
    private JTextField tDescuento;

    private JSpinner sKms;

    private JFormattedTextField tfValor;

    private JPanel panelDatos, panelBotones;
    private Container contenedor;

    private GestionMotocicletas negocio;

    public VentanaRegistro(JFrame frame, boolean bln) {
        super(frame, bln);
        this.setTitle("Registro de Motocicletas - V1");
        this.negocio = new GestionMotocicletas();
        this.iniciarComponentes();
        //this.setSize(600, 400);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    public void iniciarComponentes() {
        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new BorderLayout());
        this.panelDatos();
        this.panelBotones();
    }

    public void panelDatos() {
        this.panelDatos = new JPanel();
        this.panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.panelDatos.setLayout(new GridLayout(7, 2, 5, 5));

        this.lPlaca = new JLabel("  Placa: ");
        this.lModelo = new JLabel("  Modelo: ");
        this.lMarca = new JLabel("  Marca: ");
        this.lValor = new JLabel("  Valor: ");
        this.lkms = new JLabel("  No Kms: ");
        this.lDescuento = new JLabel(" Descuento:");

        this.tKms = new JTextField(null);
        this.tPlaca = new JTextField(null);
        this.tPlaca.setEnabled(false);
        this.tValor = new JTextField(null);
        this.tDescuento = new JTextField(null);

        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(0, 0, 100000, 1);
        this.sKms = new JSpinner();
        this.sKms.setModel(modeloSpinner);

        NumberFormat formato = NumberFormat.getCurrencyInstance();
        this.tfValor = new JFormattedTextField(formato);

        this.cMarca = new JComboBox();
        this.cMarca.addItem("Yamaha");
        this.cMarca.addItem("Suzuki");
        this.cMarca.addItem("Honda");
        this.cMarca.addItem("Otra");
        this.cMarca.addActionListener(new EventoClickComboMarca());

        this.rModelo1 = new JRadioButton("2010");
        this.rModelo1.setSelected(true);
        this.rModelo2 = new JRadioButton("2015");
        this.rModelo3 = new JRadioButton("2020");
        this.grupoRadio = new ButtonGroup();
        this.grupoRadio.add(this.rModelo1);
        this.grupoRadio.add(this.rModelo2);
        this.grupoRadio.add(this.rModelo3);
        JPanel panel = new JPanel();
        panel.add(this.rModelo1);
        panel.add(this.rModelo2);
        panel.add(this.rModelo3);

        this.bGuardar = new JButton("Guardar");
        this.bGuardar.addActionListener(new EventoClickBotonGuardar());
        this.bGuardar.setEnabled(false);
        this.bCancelar = new JButton("Cancelar");
        this.bCancelar.setEnabled(false);

        this.panelDatos.add(this.lPlaca);
        this.panelDatos.add(this.tPlaca);

        this.panelDatos.add(this.lMarca);
        this.panelDatos.add(this.cMarca);

        this.panelDatos.add(this.lModelo);
        this.panelDatos.add(panel);

        this.panelDatos.add(this.lkms);
        // this.panelDatos.add(this.tKms);
        this.panelDatos.add(this.sKms);

        this.panelDatos.add(this.lValor);
        //this.panelDatos.add(this.tValor);
        this.panelDatos.add(this.tfValor);

        this.panelDatos.add(this.lDescuento);
        this.panelDatos.add(this.tDescuento);

        this.panelDatos.add(this.bGuardar);
        this.panelDatos.add(this.bCancelar);

        this.contenedor.add(this.panelDatos, BorderLayout.CENTER);

    }

    public void panelBotones() {
        this.panelBotones = new JPanel();

        this.bNuevo = new JButton("Nuevo");
        this.bNuevo.addActionListener(new EventoClickBotonNuevo());
        this.bBuscar = new JButton("Buscar");
        this.bBuscar.setEnabled(false);
        this.bBuscar.addActionListener(new EventoClickBotonBuscar());
        this.bEliminar = new JButton("Eliminar");
        this.bEliminar.setEnabled(false);
        this.bEliminar.addActionListener( new EventoClickBotonEliminar());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(this.bNuevo);
        panel.add(this.bBuscar);
        panel.add(this.bEliminar);

        this.panelBotones.add(panel);

        this.contenedor.add(this.panelBotones, BorderLayout.EAST);

    }

    public void activarComponentes() {
        this.tPlaca.setEnabled(true);
        this.bGuardar.setEnabled(true);
        this.bCancelar.setEnabled(true);
        this.bBuscar.setEnabled(true);
        this.bEliminar.setEnabled(true);
        this.tPlaca.grabFocus();
    }

    public Motocicleta leerDatos() {
        String placa = this.tPlaca.getText();
        String marca = this.cMarca.getSelectedItem().toString();
        int modelo;
        if (this.rModelo1.isSelected()) {
            modelo = 2010;
        } else if (this.rModelo2.isSelected()) {
            modelo = 2015;
        } else {
            modelo = 2020;
        }

        //int kms = Integer.parseInt(this.tKms.getText());
        int kms = (int) this.sKms.getValue();
        //double valor = Double.parseDouble(this.tValor.getText());
        double valor;
        Object objectValor = this.tfValor.getValue();
        if (objectValor instanceof Long) {
            valor = ((Long) (objectValor)).doubleValue();
        } else if (objectValor instanceof Double) {
            valor = (Double) (objectValor);
        } else {
            valor = 0;
        }

        Motocicleta moto = new Motocicleta(placa, marca, modelo, kms, valor);
        return moto;
    }

    public void eliminarMotocicleta() {

        String placa = this.tPlaca.getText();
        int confirmacion = JOptionPane.showConfirmDialog(this, "Desea eliminar el elemento", "Confirmacion", JOptionPane.OK_CANCEL_OPTION);
        if (confirmacion == 0) {
            try {
               this.negocio.eliminarMotocicletas(placa);
               this.mostrarMsg("Exito", "Elemento eliminado con exito", JOptionPane.INFORMATION_MESSAGE);
               this.limpiarComponentes();
            } catch (IOException | NullPointerException ex) {
                this.mostrarMsg("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void mostrarMotocicleta() {

        try {
            String placa = this.tPlaca.getText();
            Motocicleta moto = this.negocio.buscarMotocicleta(placa);

            if (moto == null) {
                throw new NullPointerException("La motocicleta no se encuentra registrada");
            }

            this.tPlaca.setText(placa);
            this.cMarca.setSelectedItem(moto.getMarca());
            switch (moto.getModelo()) {
                case 2010:
                    this.rModelo1.setSelected(true);
                    break;
                case 2015:
                    this.rModelo2.setSelected(true);
                    break;
                case 2020:
                    this.rModelo3.setSelected(true);
            }
            this.sKms.setValue(moto.getKms());
            this.tfValor.setText("$" + String.valueOf(moto.getValor()));

        } catch (IOException | NullPointerException ex) {
            this.mostrarMsg("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarComponentes() {
        this.tPlaca.setText(null);
        this.cMarca.setSelectedIndex(0);
        this.rModelo1.setSelected(true);
        this.sKms.setValue(0);
        this.tfValor.setText("$0");
        this.tPlaca.grabFocus();
    }

    public void mostrarMsg(String titulo, String msg, int tipo) {
        JOptionPane.showMessageDialog(this, msg, titulo, tipo);
    }

    public void guardar() {

        try {
            Motocicleta moto = this.leerDatos();
            this.negocio.registrarMotocicleta(moto);
            this.mostrarMsg("Exito", "Registro almacenado cone xito", JOptionPane.INFORMATION_MESSAGE);
            this.limpiarComponentes();
        } catch (IOException | NullPointerException ex) {
            //ex.printStackTrace();
            this.mostrarMsg("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE);

        }

    }

    public void ocultarComponentes() {
        String marca = this.cMarca.getSelectedItem().toString();
        if (marca.equalsIgnoreCase("Yamaha")) {
            this.lDescuento.setVisible(true);
            this.tDescuento.setVisible(true);
        } else {
            this.lDescuento.setVisible(false);
            this.tDescuento.setVisible(false);
        }
    }

    class EventoClickBotonNuevo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            activarComponentes();
        }
    }

    class EventoClickBotonGuardar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            guardar();
        }
    }

    class EventoClickBotonBuscar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarMotocicleta();
        }
    }

    class EventoClickBotonEliminar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            eliminarMotocicleta();
        }
    }

    class EventoClickComboMarca implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            ocultarComponentes();
        }
    }
}
