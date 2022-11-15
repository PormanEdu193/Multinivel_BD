package Modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class EliminarClientes {
 DefaultTableModel modelo=new DefaultTableModel();
 String nit, empresa, direccion, telefono,ciudad;
 public EliminarClientes(){}
 public String getNit() {
 return nit;
 }
 public void setNit(String nit) {
 this.nit = nit;
 }
 public String getEmpresa() {
 return empresa;
 }
 public void setEmpresa(String empresa) {
 this.empresa = empresa;
 }
 public String getDireccion() {
 return direccion;
 }
 public void setDireccion(String direccion) {
 this.direccion = direccion;
 }
 public String getTelefono() {
 return telefono;
 }
 public void setTelefono(String telefono) {
 this.telefono = telefono;
 }
 public String getCiudad() {
 return ciudad;
 }
 public void setCiudad(String ciudad) {
 this.ciudad = ciudad;
 }
 public String[] buscar(String nit, String[] datos)
 {
 try {
 String criterio= nit;
 ConectarBD conexion=new ConectarBD();
 Statement sentencia=conexion.getConexion().createStatement();
 ResultSet resultado=sentencia.executeQuery(
"select * from clientes where nit='"+criterio+"'");
 while (resultado.next())
 {
 datos[0]=resultado.getString("empresa");
 datos[1]=resultado.getString("dirección");
 datos[2]=resultado.getString("teléfono");
 datos[3]=resultado.getString("ciudad");
 }
 resultado.close();
 conexion.getConexion().close();
 }
 catch(Exception e)
 {
 JOptionPane.showMessageDialog(null,"Error:"+e,
"Información",JOptionPane.INFORMATION_MESSAGE);
 }
 return datos;
 }
 public void eliminar(String nit)
 {
 int seleccion = JOptionPane.showOptionDialog(null,
"¿Desea ELIMINAR EL REGISTRO (Si/No)",
"Seleccione una opción",
JOptionPane.YES_NO_CANCEL_OPTION,
JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Si", "No"},"Si");
 if((seleccion + 1)==1)
 {
 try
 {
 ConectarBD conexion=new ConectarBD();
 String instruccion= "Delete from clientes where nit=" +nit;
 conexion.sentencia=conexion.getConexion().prepareStatement(instruccion);
 conexion.sentencia.execute();
 JOptionPane.showMessageDialog(null,"Registro Eliminado","Información",
JOptionPane.INFORMATION_MESSAGE);
 conexion.getConexion().close();
 }
 catch(SQLException e)
 {
 JOptionPane.showMessageDialog(null,"Error SQL:"+e,
 "Información",JOptionPane.INFORMATION_MESSAGE);
 }
 }
 else
 {
 JOptionPane.showMessageDialog(null,"Registro NO ELIMINADO","Información",
JOptionPane.INFORMATION_MESSAGE);
 }
 }
}
