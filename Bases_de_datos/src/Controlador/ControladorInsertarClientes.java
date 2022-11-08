package Controlador;

import Modelo.InsertarClientes;
import Vista.Formulario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ControladorInsertarClientes implements ActionListener{
 Formulario objetoVista;
 InsertarClientes objetoModelo;
 public ControladorInsertarClientes() {
 objetoVista=new Formulario();
 objetoVista.setVisible(true);
 objetoVista.getButton().addActionListener(this);
 }
 public void actionPerformed(ActionEvent e) {
 if(e.getSource()==objetoVista.getButton()) {
 objetoModelo=new InsertarClientes();
 objetoModelo.setNit(objetoVista.getTxtNit().getText());
 objetoModelo.setEmpresa(objetoVista.getTxtEmpresa().getText());
 objetoModelo.setDireccion(objetoVista.getTxtDireccion().getText());
 objetoModelo.setTelefono(objetoVista.getTxtTelefono().getText());
 objetoModelo.setCiudad(objetoVista.getTxtCiudad().getText());
 objetoModelo.insertar();
 objetoVista.getTxtNit().setText("");
 objetoVista.getTxtEmpresa().setText("");
 objetoVista.getTxtDireccion().setText("");
 objetoVista.getTxtTelefono().setText("");
 objetoVista.getTxtCiudad().setText("");
 }
 }
}
