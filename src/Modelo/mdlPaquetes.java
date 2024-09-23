/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.TableActionCellEditor;
import Vista.TableActionCellRender;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<Object[]> obtenerPaquetes() {
        List<Object[]> paquetes = new ArrayList<>();
        Connection conexion = ClaseConexion.getConexion();

        try {
            String query = "SELECT * FROM Paquete";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                paquetes.add(new Object[]{
                    rs.getInt("IDPAQUETE"),
                    rs.getDouble("PESO"),
                    rs.getDouble("ALTO"),
                    rs.getDouble("LARGO"),
                    rs.getDouble("ANCHO"),
                    rs.getDate("FECHAINICIO"),
                    rs.getString("IDDIRECCION"),
                    rs.getInt("ORIGEN"),
                    rs.getInt("IDSEGURO"),
                    null 
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return paquetes;
    }

    public void actualizarPaquete(String idPaquete, double peso, double alto, double largo, double ancho,String idDireccion, int origen, int idSeguro) {
        Connection conexion = ClaseConexion.getConexion();

        String sql = "UPDATE Paquete SET Peso = ?, Alto = ?, Largo = ?, Ancho = ?, " +
                     "IdDireccion = ?, Origen = ?, IdSeguro = ? WHERE IdPaquete = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setDouble(1, peso);
            statement.setDouble(2, alto);
            statement.setDouble(3, largo);
            statement.setDouble(4, ancho);
            statement.setString(5, idDireccion);
            statement.setInt(6, origen);
            statement.setInt(7, idSeguro);
            statement.setString(8, idPaquete);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Paquete actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el paquete con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminarPaquete(int idPaquete) {
        Connection conexion = ClaseConexion.getConexion();

        String sql = "DELETE FROM Paquete WHERE IdPaquete = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idPaquete);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Paquete eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el paquete con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }


}
