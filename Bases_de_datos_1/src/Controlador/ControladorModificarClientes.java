package Controlador;
import Modelo.ModificarClientes;
import Vista.Formulario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
public class ControladorModificarClientes implements ActionListener{
 Formulario objetoVista;
 ModificarClientes objetoModelo;
 DefaultTableModel modelo=new DefaultTableModel();
 String datos[]=new String[4];
 public ControladorModificarClientes() {
 objetoVista=new Formulario();
 objetoModelo=new ModificarClientes();
 objetoVista.setVisible(true);
 objetoVista.getButton().addActionListener(this);
 objetoVista.getBtnNit().addActionListener(this);
 }
 @Override
 public void actionPerformed(ActionEvent e) {
 if(e.getSource()==objetoVista.getButton()) {
 String nit=objetoVista.getTxtNit().getText();
 objetoModelo.setEmpresa(objetoVista.getTxtEmpresa().getText());
 objetoModelo.setDireccion(objetoVista.getTxtDireccion().getText());
 objetoModelo.setTelefono(objetoVista.getTxtTelefono().getText());
 objetoModelo.setCiudad(objetoVista.getTxtCiudad().getText());
 objetoModelo.modificar(nit);
 objetoVista.getTxtNit().setText("");
 objetoVista.getTxtEmpresa().setText("");
 objetoVista.getTxtDireccion().setText("");
 objetoVista.getTxtTelefono().setText("");
 objetoVista.getTxtCiudad().setText("");
 }
 if(e.getSource()==objetoVista.getBtnNit()) {
 String nit=objetoVista.getTxtNit().getText();
 datos=objetoModelo.buscar(nit, datos);
 objetoVista.getTxtEmpresa().setText(datos[0]);
 objetoVista.getTxtDireccion().setText(datos[1]);
 objetoVista.getTxtTelefono().setText(datos[2]);
 objetoVista.getTxtCiudad().setText(datos[3]);

 }
 }
}
