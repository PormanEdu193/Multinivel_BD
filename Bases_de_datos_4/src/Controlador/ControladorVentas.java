
package Controlador;
import Modelo.Ventas;
import Vista.Formulario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.JFreeChart;
public class ControladorVentas implements ActionListener{
 Formulario objetoVista;
 Ventas objetoModelo;
 DefaultTableModel modelo=new DefaultTableModel();
 int filas;
 public ControladorVentas() {
 objetoVista=new Formulario();
 objetoModelo=new Ventas();
 objetoVista.setVisible(true);
 objetoVista.getBoton().addActionListener(this);
 verregistros();
 vergrafico();
 }
 
 public void verregistros()
 {
 modelo=(DefaultTableModel)objetoVista.getTabla_productos().getModel();
 filas=objetoVista.getTabla_productos().getRowCount();
 for (int i = 0;filas>i; i++)
 modelo.removeRow(0);
 modelo=objetoModelo.consultar();
 objetoVista.getTabla_productos().setModel(modelo);
 }
 public void vergrafico()
 {
 BufferedImage grafico=null;
 JFreeChart grafica=objetoModelo.Graficar();
 grafico = grafica.createBufferedImage(600,300);
 objetoVista.getLblGrafico().setSize(objetoVista.getPanelgrafico().getSize());
 objetoVista.getLblGrafico().setIcon(new ImageIcon(grafico));
 objetoVista.getPanelgrafico().repaint();
 }
 @Override
 public void actionPerformed(ActionEvent e) {
 if(e.getSource()==objetoVista.getBoton()) {
 objetoModelo.setNombre(objetoVista.getListaproductos().getSelectedItem().toString());
 objetoModelo.setCantidad(objetoVista.getTxtcantidad().getText());
 objetoModelo.setPrecio(objetoVista.getTxtprecio().getText());
 objetoModelo.insertar();
 objetoVista.getTxtcantidad().setText("");
 objetoVista.getTxtprecio().setText("");
 verregistros();
 vergrafico();
 }
 }

}

