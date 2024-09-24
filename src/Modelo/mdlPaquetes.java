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
    public String Cliente;
    public String Distrito;
    private String Direccion;

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String Distrito) {
        this.Distrito = Distrito;
    }
    
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
    
    private List<Object[]> paquetesData = new ArrayList<>();
        
        public void obtenerPaquetes() {
        Connection conexion = ClaseConexion.getConexion();
        paquetesData.clear();
        try {
            String query = "SELECT p.IDPAQUETE, c.NombreCompleto AS Cliente, p.FECHAINICIO, d.Direccion, dis.Distrito, "
                    + "p.Peso, p.Alto, p.Largo, p.Ancho "
                    + "FROM Paquete p "
                    + "INNER JOIN Direccion d ON p.IDDIRECCION = d.IdDireccion "
                    + "INNER JOIN Cliente c ON d.IdCliente = c.IdCliente "
                    + "INNER JOIN Distrito dis ON d.IdDistrito = dis.IdDistrito";

            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                paquetesData.add(new Object[]{
                    rs.getString("IDPAQUETE"),
                    rs.getString("Cliente"),
                    rs.getDate("FECHAINICIO"),
                    rs.getString("Direccion"),
                    rs.getString("Distrito"),
                    rs.getDouble("Peso"),   
                    rs.getDouble("Alto"),    
                    rs.getDouble("Largo"),   
                    rs.getDouble("Ancho")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        
    public List<Object[]> getPaquetesData() {
        return paquetesData;
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
    
   public void almacenarDatosTabla(JTable table) {
        int filaSeleccionada = table.getSelectedRow();
            if (filaSeleccionada != -1) {
                setIdPaquete(table.getValueAt(filaSeleccionada, 0).toString());
                setCliente(table.getValueAt(filaSeleccionada, 1).toString()); 
                setFechaInicio(table.getValueAt(filaSeleccionada, 2).toString());
                setDireccion(table.getValueAt(filaSeleccionada, 3).toString());
                setDistrito((table.getValueAt(filaSeleccionada, 4).toString()));
                setPeso(Double.parseDouble(table.getValueAt(filaSeleccionada, 5).toString())); 
                setAlto(Double.parseDouble(table.getValueAt(filaSeleccionada, 6).toString())); 
                setLargo(Double.parseDouble(table.getValueAt(filaSeleccionada, 7).toString())); 
                setAncho(Double.parseDouble(table.getValueAt(filaSeleccionada, 8).toString())); 
                /*System.out.println("Datos almacenados del paquete:");
                System.out.println("ID Paquete: " + getIdPaquete());
                System.out.println("Cliente: " + getCliente());
                System.out.println("Fecha de Inicio: " + getFechaInicio());
                System.out.println("ID Dirección: " + getIdDireccion());
                System.out.println("Origen: " + getOrigen());
                System.out.println("Peso: " + getPeso());
                System.out.println("Alto: " + getAlto());
                System.out.println("Largo: " + getLargo());
                System.out.println("Ancho: " + getAncho());*/
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }
}
