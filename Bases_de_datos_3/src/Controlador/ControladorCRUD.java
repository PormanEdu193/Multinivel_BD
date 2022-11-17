package Controlador;
import Modelo.Clientes;
import Modelo.ImplementarDAOClientes;
import Vista.Formulario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
public class ControladorCRUD implements ActionListener{
 Formulario objetoVista;
 ImplementarDAOClientes objetoModelo;
 Clientes objetoClientes;
 DefaultTableModel modelo=new DefaultTableModel();
 String nit, empresa, direccion, telefono, ciudad;
 int filas;
 String datos[]=new String[4];
 public ControladorCRUD() {
 objetoVista=new Formulario();
 objetoModelo=new ImplementarDAOClientes();
 objetoClientes=new Clientes();
 objetoVista.setVisible(true);
 objetoVista.getBtnCliente().addActionListener(this);
 objetoVista.getBtnNit().addActionListener(this);
 objetoVista.getBtnRegistro().addActionListener(this);
 objetoVista.getBtnEliminar().addActionListener(this);
 verregistros();
 }
 public void verregistros()
 {
 modelo=(DefaultTableModel)objetoVista.getTable().getModel();
 filas=objetoVista.getTable().getRowCount();
 for (int i = 0;filas>i; i++)
 modelo.removeRow(0);
 modelo=objetoModelo.consultar();
 objetoVista.getTable().setModel(modelo);
 }
 public void actionPerformed(ActionEvent e) {
 if(e.getSource()==objetoVista.getBtnCliente()) {
 objetoClientes.setNit(objetoVista.getTxtNit().getText());
 objetoClientes.setEmpresa(objetoVista.getTxtEmpresa().getText());
 objetoClientes.setDireccion(objetoVista.getTxtDireccion().getText());
 objetoClientes.setTelefono(objetoVista.getTxtTelefono().getText());
 objetoClientes.setCiudad(objetoVista.getTxtCiudad().getText());
 objetoModelo.insertar(objetoClientes);
 objetoVista.getTxtNit().setText("");
 objetoVista.getTxtEmpresa().setText("");
 objetoVista.getTxtDireccion().setText("");
 objetoVista.getTxtTelefono().setText("");
 objetoVista.getTxtCiudad().setText("");
 verregistros();
 }
 if(e.getSource()==objetoVista.getBtnNit()) {
 String nit=objetoVista.getTxtNit().getText();
 datos=objetoModelo.buscar(nit, datos);
 objetoVista.getTxtEmpresa().setText(datos[0]);
 objetoVista.getTxtDireccion().setText(datos[1]);
 objetoVista.getTxtTelefono().setText(datos[2]);
 objetoVista.getTxtCiudad().setText(datos[3]);
 }
 if(e.getSource()==objetoVista.getBtnRegistro()) {
 objetoClientes.setNit(objetoVista.getTxtNit().getText());
 objetoClientes.setEmpresa(objetoVista.getTxtEmpresa().getText());
 objetoClientes.setDireccion(objetoVista.getTxtDireccion().getText());
 objetoClientes.setTelefono(objetoVista.getTxtTelefono().getText());
 objetoClientes.setCiudad(objetoVista.getTxtCiudad().getText());
 objetoModelo.modificar(objetoClientes);
 verregistros();
 }
 if(e.getSource()==objetoVista.getBtnEliminar()) {
 objetoClientes.setNit(objetoVista.getTxtNit().getText());
 objetoModelo.eliminar(objetoClientes);
 objetoVista.getTxtNit().setText("");
 objetoVista.getTxtEmpresa().setText("");
 objetoVista.getTxtDireccion().setText("");
 objetoVista.getTxtTelefono().setText("");
 objetoVista.getTxtCiudad().setText("");
 verregistros();
 }
 }
}