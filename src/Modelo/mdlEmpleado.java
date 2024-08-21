/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.InformacionEmpleados;
import Vista.TableActionCellRender;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.table.DefaultTableModel;
import oracle.sql.DATE;

/**
 *
 * @author gerst
 */
public class mdlEmpleado {

    private String DUI;
    private String Nombre;
    private String ApellidoPa;
    private String ApellidoMa;
    private String Email;
    private Double Salario;
    private String FechaNa;
    private Number idRol;
    private Number idSucursal;
    private Number Genero;
    private Number Estado;
    private String Usuario;
    private String Contraseña;
    private String Licencia;

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getLicencia() {
        return Licencia;
    }

    public void setLicencia(String Licencia) {
        this.Licencia = Licencia;
    }

    public Number getEstado() {
        return Estado;
    }

    public void setEstado(Number Estado) {
        this.Estado = Estado;
    }
    
    public String getDUI() {
        return DUI;
    }

    public void setDUI(String DUI) {
        this.DUI = DUI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoPa() {
        return ApellidoPa;
    }

    public void setApellidoPa(String ApellidoPa) {
        this.ApellidoPa = ApellidoPa;
    }

    public String getApellidoMa() {
        return ApellidoMa;
    }

    public void setApellidoMa(String ApellidoMa) {
        this.ApellidoMa = ApellidoMa;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Double getSalario() {
        return Salario;
    }

    public void setSalario(Double Salario) {
        this.Salario = Salario;
    }

    public String getFechaNa() {
        return FechaNa;
    }

    public void setFechaNa(String FechaNa) {
        this.FechaNa = FechaNa;
    }

    public Number getIdRol() {
        return idRol;
    }

    public void setIdRol(Number idRol) {
        this.idRol = idRol;
    }

    public Number getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Number idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Number getGenero() {
        return Genero;
    }

    public void setGenero(Number Genero) {
        this.Genero = Genero;
    }   

    public void Mostrar(JTable tabla){
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"DUI", "Nombre", "Apellido Paterno", "Apellido Materno", "Email", 
            "Salario", "Fecha Na", "Rol", "Sucursal", "Masculino", "Estado"});
        
        try
        {
            String query = "Select DUI, Empleado.Nombre, apellidopaterno, apellidomaterno, email, salario, fechana, Rol.NomRol,Sucursal.Nombre as Sucursal, masculino, estado \n" +
            "from empleado\n" +
            "inner join Rol on Empleado.IdRol = Rol.idRol\n" +
            "inner join Sucursal on Empleado.idSucursal = sucursal.idsucursal";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{
                    rs.getString(1), //DUI
                    rs.getString(2), //Nombre
                    rs.getString(3), //ApellidoPaterno
                    rs.getString(4), //ApellidoMaterno
                    rs.getString(5), //Emil
                    rs.getDouble(6), //Salario
                    rs.getDate(7),   //FechaNa
                    rs.getString(8), //Rol
                    rs.getString(9), //Sucursal
                    rs.getInt(10),   //Genero
                    rs.getInt(11)}   //Estado
                );
                
            }
            //tabla.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
            tabla.setModel(modelo);
            
            
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
        }
        
    }
    
    public void CargarComboRol(String tabla, String valor, JComboBox c){    
        Connection conexion = ClaseConexion.getConexion();
        //DefaultComboBoxModel combo = new DefaultComboBoxModel();
        try{
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from Rol");
            
            while (rs.next()) {
                c.addItem(rs.getString(valor));                
            }                       
                    
                            //c.setModel(combo);

        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
        }
        
    }
    
    
    public void CargarComboSucursal(String tabla, String valor, JComboBox c){    
        Connection conexion = ClaseConexion.getConexion();
        //DefaultComboBoxModel combo = new DefaultComboBoxModel();
        try{
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from Sucursal");
            
            while (rs.next()) {
                c.addItem(rs.getString(valor));                
            }                       
                    
                            //c.setModel(combo);

        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
        }
    }
     
    public int AgregarEmpleado()
    {
        Connection conexion = ClaseConexion.getConexion();
        try
        {
            String sql = "insert into Empleado (DUI,Nombre, apellidopaterno, apellidomaterno, email, salario, fechana, idrol, idsucursal, masculino, Estado)\n" +
            "Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
            
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, getDUI());
            pstmt.setString(2, getNombre());
            pstmt.setString(3, getApellidoPa());
            pstmt.setString(4, getApellidoMa());
            pstmt.setString(5, getEmail());
            pstmt.setDouble(6, getSalario());
            pstmt.setString(7, getFechaNa().toString());
            pstmt.setInt(8, (int)getIdRol());
            pstmt.setInt(9, (int)getIdSucursal());
            pstmt.setInt(10, (int)getGenero());
            
            int respuesta = pstmt.executeUpdate();
            if(respuesta == 1)
            {
                String sql2 = "insert into Usuario(DUI, Usuario, Contrasena)\n" +
                "Values(?,?,?)";
                PreparedStatement pstmt2 = conexion.prepareStatement(sql2);
                pstmt2.setString(1, getDUI());
                pstmt2.setString(2, getUsuario());
                pstmt2.setString(3, getContraseña());
                respuesta = pstmt2.executeUpdate();
                return respuesta;
            }
            else
            {
                return 0;
            }
        }
        catch (SQLException ex) 
        {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
            return -1;
        }       
    }
    
    public int AgregarRepartidor()
    {
        Connection conexion = ClaseConexion.getConexion();
        try
        {
            
               
                String sql = "insert into Empleado (DUI,Nombre, apellidopaterno, apellidomaterno, email, salario, fechana, idrol, idsucursal, masculino, Estado)\n" +
                "Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";

                PreparedStatement pstmt = conexion.prepareStatement(sql);
                pstmt.setString(1, getDUI());
                pstmt.setString(2, getNombre());
                pstmt.setString(3, getApellidoPa());
                pstmt.setString(4, getApellidoMa());
                pstmt.setString(5, getEmail());
                pstmt.setDouble(6, getSalario());
                pstmt.setString(7, getFechaNa().toString());
                pstmt.setInt(8, (int)getIdRol());
                pstmt.setInt(9, (int)getIdSucursal());
                pstmt.setInt(10, (int)getGenero());

                int respuesta = pstmt.executeUpdate();
                if(respuesta == 1)
                {
                    String sql3 = "insert into Repartidor(Licencia, DUI)\n" +
                    "Values(?, ?)";

                    PreparedStatement pstmt3 = conexion.prepareStatement(sql3);
                    pstmt3.setString(1, getLicencia());
                    pstmt3.setString(2, getDUI());


                    respuesta = pstmt3.executeUpdate();
                    if(respuesta == 1){
                        String sql2 = "insert into Usuario(DUI, Usuario, Contrasena)\n" +
                        "Values(?,?,?)";
                        PreparedStatement pstmt2 = conexion.prepareStatement(sql2);
                        pstmt2.setString(1, getDUI());
                        pstmt2.setString(2, getUsuario());
                        pstmt2.setString(3, getContraseña());
                        respuesta = pstmt2.executeUpdate();
                        return respuesta;
                    }
                    else
                    {
                        return 0;
                    }
                }
                else
                {
                    return 0;
                }
            
            
        }
        catch (SQLException ex) 
        {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
            return -1;
        }       
    }
    
    public void limpiar(InformacionEmpleados vista) {
        vista.txtDUI.setText("");
        vista.txtNombre.setText("");
        vista.txtApellidoPa.setText("");
        vista.txtApellidoMa.setText("");
        vista.txtFechaNa.setText("");
        vista.txtTelefono.setText("");
        vista.txtEmail.setText("");
        vista.txtSalario.setText("");
        vista.txtLicencia.setText("");
        vista.txtUsuario.setText("");
        vista.txtContrasena.setText("");
    }
}


