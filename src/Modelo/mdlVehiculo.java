/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.FrmMarca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gerst
 */
public class mdlVehiculo {

    private int IdMarca;
    private String NomMarca;
    private int IdModelo;
    private String Modelo;
    private String Año;
    private int Carga;
    private String Placa;
    private int IdSucursal;
    
    public int getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(int IdMarca) {
        this.IdMarca = IdMarca;
    }

    public String getNomMarca() {
        return NomMarca;
    }

    public void setNomMarca(String NomMarca) {
        this.NomMarca = NomMarca;
    }

    public int getIdModelo() {
        return IdModelo;
    }

    public void setIdModelo(int IdModelo) {
        this.IdModelo = IdModelo;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getAño() {
        return Año;
    }

    public void setAño(String Año) {
        this.Año = Año;
    }

    public int getCarga() {
        return Carga;
    }

    public void setCarga(int Carga) {
        this.Carga = Carga;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }
 
   
    public void Mostrar(JTable tabla){
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Placa", "Modelo", "Marca", "Año", "Sucursal", 
            "Carga"});
        
        try
        {
            String query = "select placavehiculo, modelo.modelo, marca.nommarca as Marca,modelo.año, sucursal.nombre as Sucursal , modelo.carga from vehiculo\n" +
            "inner join Modelo on vehiculo.idmodelo = modelo.idmodelo\n" +
            "inner join Marca on modelo.idmarca = marca.idmarca\n" +
            "inner join sucursal on vehiculo.idsucursal = sucursal.idsucursal";
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
                    rs.getInt(6) //Salario
                    }
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
    
    
    
    public void GuardarModelo() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "insert into Modelo(modelo, idmarca, año, carga) values(?, ?, ?, ?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, getModelo());
            pstmt.setInt(2, getIdMarca());
            pstmt.setString(3, getAño());
            pstmt.setInt(4, getCarga());

            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    

     
    
}
