/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gerst
 */
public class mdlPaquetes {

    private String IdPaquete;
    private double peso;
    private double alto;
    private double largo;
    private double ancho;
    private String FechaInicio;
    private String IdDireccion;
    private int origen;
    private int IdSeguro;
    
    public String getIdPaquete() {
        return IdPaquete;
    }

    public void setIdPaquete(String IdPaquete) {
        this.IdPaquete = IdPaquete;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public String getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(String IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getIdSeguro() {
        return IdSeguro;
    }

    public void setIdSeguro(int IdSeguro) {
        this.IdSeguro = IdSeguro;
    }
    
   public void Mostrar(JTable tabla){
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"ID Paquete", "Peso", "Alto", "Largo", "Ancho", 
            "Fecha Inicio", "ID Direccion", "Origen", "ID Seguro"});
        
        try
        {
            String query = "select * from Paquete";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{
                    rs.getString(1), //DUI
                    rs.getDouble(2), //Nombre
                    rs.getDouble(3), //ApellidoPaterno
                    rs.getDouble(4), //ApellidoMaterno
                    rs.getDouble(5), //Emil
                    rs.getString(6), //Salario
                    rs.getString(7),   //FechaNa
                    rs.getInt(8), //Rol
                    rs.getInt(9)}  
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
    
   
    
}
