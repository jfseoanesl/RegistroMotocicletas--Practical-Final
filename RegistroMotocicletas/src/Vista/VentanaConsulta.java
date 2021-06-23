
package Vista;

import Modelo.Motocicleta;
import Negocio.GestionMotocicletas;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class VentanaConsulta extends JDialog{
    private JPanel paneFiltro;
    private JScrollPane panelTabla;
    private Container contenedor;
   
    private JPanel panelBase;
    
    private JLabel lFiltro;
    private JTextField tFiltro;
    
    private JTable tabla;
    private DefaultTableModel modelo;
    private String titulos[]={"Placa", "Marca", "Modelo", "No Kms", "Valor"};
    
    private GestionMotocicletas negocio;

    public VentanaConsulta(JFrame frame, boolean bln) {
        super(frame, bln);
        this.setTitle("Consulta de Motocicletas - V1");
        this.negocio = new GestionMotocicletas();
        this.iniciarComponentes();
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void iniciarComponentes(){
        this.contenedor = this.getContentPane();
        this.panelBase = new JPanel();
        this.panelBase.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.panelBase.setLayout(new BorderLayout());
        
        this.iniciarPanelFiltro();
        this.iniciarPanelTabla();
        
        try{
            List<Motocicleta> lista = this.negocio.leerMotocicletas();
            this.actualizarTabla(lista);
            
        }catch(IOException ioe){
            ioe.printStackTrace();
        }    
        
        this.contenedor.add(this.panelBase);
    }
    
    public void iniciarPanelFiltro(){
        
        this.paneFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        this.lFiltro = new JLabel(" Texto de Filtro: ");
        this.tFiltro = new JTextField(10);
        this.tFiltro.addKeyListener(new eventoTeclado());
        
        this.paneFiltro.add(this.lFiltro);
        this.paneFiltro.add(this.tFiltro);
        
        this.panelBase.add(this.paneFiltro, BorderLayout.NORTH);
        
    }
    
    public void iniciarPanelTabla(){
    
        this.panelTabla = new JScrollPane();
        this.tabla = new JTable();
        this.modelo = new DefaultTableModel(null, this.titulos);
        this.tabla.setModel(this.modelo);
        this.panelTabla.setViewportView(this.tabla);
        
        this.panelBase.add(this.panelTabla, BorderLayout.CENTER);
        
    }
    
    public void actualizarTabla(List<Motocicleta> lista){
       
            this.modelo.setNumRows(0);
            for(Motocicleta moto: lista){
                String fila[] = {moto.getPlaca(), moto.getMarca(), ""+moto.getModelo(),
                                 ""+moto.getKms(), ""+moto.getValor()};
                this.modelo.addRow(fila);
            }
       
    }
    
    public void filtrarDatosTabla(){
        try {
            String texto = this.tFiltro.getText();
            List<Motocicleta> listaFiltrada = this.negocio.consultarMotocicleta(texto);
            this.actualizarTabla(listaFiltrada);
        } catch (IOException ex) {
            this.mostrarMsg("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    
    }
    
    public void mostrarMsg(String titulo, String msg, int tipo){
        JOptionPane.showMessageDialog(this, msg, titulo, tipo);
    }
    
    class eventoTeclado extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent ke) {
            filtrarDatosTabla();
        }
    
    }
    
}
