/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.FrmMarca;
import Vista.MarcaPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gerst
 */
public class mdlMarcaPanel {

    private int IdMarca;
    private String NomMarca;
    
    
    
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
    
    
    
    
    public void GuardarMarca() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO Marca(NomMarca) VALUES (?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, getNomMarca());

            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            int IDMarca = (int)tabla.getValueAt(filaSeleccionada, 0);

            try {
                //Ejecutamos la Query
                String sql = "update Marca set NomMarca= ? where IdMarca = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNomMarca());
                updateUser.setInt(2, IDMarca);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //obtenemos que fila seleccionó el usuario
            int IDMarca = (int)tabla.getValueAt(filaSeleccionada, 0);
            //Obtenemos el id de la fila seleccionada


            //borramos 
            try {
                String sql = "delete from Marca where IdMarca = ?";
                PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
                deleteEstudiante.setInt(1, IDMarca);
                deleteEstudiante.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error metodo de eliminar" + e);
            }
        }
        else {
            System.out.println("no");
        }
    }
    
    public void MostrarMarca(JTable tabla){
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"idMarca", "Marca"});
        
        try
        {
            String query = "select * from Marca ORDER BY id_secuencia ASC" ;
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{
                    rs.getInt(1), //ID
                    rs.getString(2) //Marca
                    }
                );
                
            }
            
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
            
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
        }
        
    }

    public void cargarDatosTabla(MarcaPanel vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbMarca.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            int IDMarca = (int)vista.jtbMarca.getValueAt(filaSeleccionada, 0);
            String NomMarca = vista.jtbMarca.getValueAt(filaSeleccionada, 1).toString();
            

            // Establece los valores en los campos de texto
            vista.txtMarca.setText(NomMarca);
            
        }
    }
    
    public void limpiar(MarcaPanel vista) {
        vista.txtMarca.setText("");
    }
}
