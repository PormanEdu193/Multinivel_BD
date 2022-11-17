
package Modelo;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ImplementarDAOClientes implements DAOClientes{

    @Override
 public void insertar(Clientes cliente)
 {
 try{
    ConectarBD conexion=new ConectarBD();
    String instruccion= "insert into clientes values(?,?,?,?,?)";
    conexion.sentencia=conexion.getConexion().prepareStatement(instruccion);
    conexion.sentencia.setString(1,cliente.getNit());
    conexion.sentencia.setString(2, cliente.getEmpresa());
    conexion.sentencia.setString(3, cliente.getDireccion());
    conexion.sentencia.setString(4, cliente.getTelefono());
    conexion.sentencia.setString(5, cliente.getCiudad());
    conexion.sentencia.execute();
    JOptionPane.showMessageDialog(null,"Registro Insertado","Información",
    JOptionPane.INFORMATION_MESSAGE);
    conexion.getConexion().close();
 }
 catch( SQLException e )
 {
    JOptionPane.showMessageDialog(null,"Error SQL"+e,"Información",
    JOptionPane.ERROR_MESSAGE);
 }
 catch( Exception e )
 {
    JOptionPane.showMessageDialog(null,"Error del sistema"+e,"Información",
    JOptionPane.ERROR_MESSAGE);
 }
 }
 
 public DefaultTableModel consultar()
 {
    ConectarBD conexion=new ConectarBD();
    String titulos[]={"Nit", "Empresa","Dirección","Teléfono","Ciudad"};
    DefaultTableModel modelo=new DefaultTableModel(null,titulos);
    int cantidadColumnas=5; // cantidad de campos a mostrar
    try {
        Statement sentencia=conexion.getConexion().createStatement();
        ResultSet resultado=sentencia.executeQuery("select * from clientes");
    while (resultado.next())
    {
        Object[] fila = new Object[cantidadColumnas];
        for (int i = 0; i < cantidadColumnas; i++)
            {
                fila[0] = resultado.getString("nit");
                fila[1] = resultado.getString("empresa");
                fila[2] = resultado.getString("direccion");
                fila[3] = resultado.getString("telefono");
                fila[4] = resultado.getString("telefono");
            }
        modelo.addRow(fila);
    }
    resultado.close();
    conexion.getConexion().close();
    }
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,"Error....:"+e,"Informacion",
        JOptionPane.INFORMATION_MESSAGE);
    }
    return modelo;
 }

    @Override
    public String[] buscar(String nit, String[] datos) {
       try {
            String criterio= nit;
            ConectarBD conexion=new ConectarBD();
            Statement sentencia=conexion.getConexion().createStatement();
            ResultSet resultado=sentencia.executeQuery("select * from clientes  where nit='"+criterio+"'");
    if (!resultado.first())
    {
        JOptionPane.showMessageDialog(null,"NIT no encontrado....","Información",
        JOptionPane.INFORMATION_MESSAGE);
    }
    else
    {
    do{
        datos[0]=resultado.getString("empresa");
        datos[1]=resultado.getString("direccion");
        datos[2]=resultado.getString("telefono");
    datos[3]=resultado.getString("ciudad");
    }while (resultado.next());
    }
    resultado.close();
    conexion.getConexion().close();
    }
    catch(Exception e)
    {
    JOptionPane.showMessageDialog(null,"Error:"+e,"Información",
    JOptionPane.INFORMATION_MESSAGE);
    }
    return datos; 
 }

    @Override
    public void modificar(Clientes cliente) {
        try{
            ConectarBD conexion=new ConectarBD();
            String instruccion= "Update clientes set empresa=?,direccion=?,telefono=?,ciudad=? where nit='" +cliente.getNit()+"'";
            conexion.sentencia=conexion.getConexion().prepareStatement(instruccion);
            conexion.sentencia.setString(1, cliente.getEmpresa());
            conexion.sentencia.setString(2, cliente.getDireccion());
            conexion.sentencia.setString(3, cliente.getTelefono());
            conexion.sentencia.setString(4, cliente.getCiudad());
            conexion.sentencia.execute();
            JOptionPane.showMessageDialog(null,"Registro Modificado","Información",
            JOptionPane.INFORMATION_MESSAGE);
            conexion.getConexion().close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error SQL:"+e,"Información",
            JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void eliminar(Clientes cliente) {
        int seleccion = JOptionPane.showOptionDialog(null,"¿Desea ELIMINAR ELREGISTRO (Si/No)","Seleccione una opción",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Si", "No"},"Si");
        if((seleccion + 1)==1)
        {
            try
                {
                    ConectarBD conexion=new ConectarBD();
                    String instruccion= "Delete from clientes where nit=" +cliente.getNit();
                    conexion.sentencia=conexion.getConexion().prepareStatement(instruccion);
                    conexion.sentencia.execute();
                    JOptionPane.showMessageDialog(null,"RegistroEliminado","Información",JOptionPane.INFORMATION_MESSAGE);
                    conexion.getConexion().close();
                }
            catch(SQLException e)
                {
                    JOptionPane.showMessageDialog(null,"ErrorSQL:"+e,"Información",JOptionPane.INFORMATION_MESSAGE);
                }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Registro NOELIMINADO","Información",JOptionPane.INFORMATION_MESSAGE);
        }
        }
    }

