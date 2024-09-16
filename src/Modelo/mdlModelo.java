/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.FrmModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gerst
 */
public class mdlModelo {

    private int IdModelo;
    private int idMarca;
    private String Modelo;
    private String Año;
    private int Carga;
    private String Marca;

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }
    
    public int getIdModelo() {
        return IdModelo;
    }

    public void setIdModelo(int IdModelo) {
        this.IdModelo = IdModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
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
    
    @Override
    public String toString()
    {
        return Marca;
    }
    
    public void GuardarModelo() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO Modelo(modelo, idmarca, año, carga) VALUES (?,?,?,?)";
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
    
    public mdlModelo()
    {
       
    }
    
    public mdlModelo(int id, String marca)
    {
        this.idMarca = id;
        this.Marca = marca;
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            int IDModelo = (int)tabla.getValueAt(filaSeleccionada, 0);

            try {
                //Ejecutamos la Query
                String sql = "update Modelo set modelo= ?, IdMarca = ?, año = ?, carga = ? where IdModelo = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getModelo());
                updateUser.setInt(2, (int)getIdMarca());
                updateUser.setString(3, getAño());
                updateUser.setInt(4, (int)getCarga());
                updateUser.setInt(5, IDModelo);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void MostrarModelo(JTable tabla){
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"ID Modelo", "Modelo", "Marca", "Año", "Carga", "IdMarca"});
        
        try
        {
            String query = "select idModelo, modelo.modelo, marca.nommarca as Marca,modelo.año, modelo.carga, marca.idmarca from Modelo\n" +
            "inner join Marca on modelo.idmarca = marca.idmarca ORDER BY modelo.id_secuencia ASC" ;
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{
                    rs.getInt(1), //ID
                    rs.getString(2), //Marca
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6)
                    }
                );
                
            }
            
            tabla.setModel(modelo);
            /*tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
            tabla.getColumnModel().getColumn(5).setWidth(0);*/            
            
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
        }        
    }
    
    public void cargarDatosTabla(FrmModelo vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbModelo.getSelectedRow();
        System.out.println(filaSeleccionada);

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            int IDModelo = (int)vista.jtbModelo.getValueAt(filaSeleccionada, 0);
            String Modelo = vista.jtbModelo.getValueAt(filaSeleccionada, 1).toString();
            String Año = vista.jtbModelo.getValueAt(filaSeleccionada, 3).toString();
            //String Marca = vista.jtbModelo.getValueAt(filaSeleccionada, 2).toString();
            int idCarga = Integer.parseInt(vista.jtbModelo.getValueAt(filaSeleccionada, 4).toString());
            int idMarca = Integer.parseInt(vista.jtbModelo.getValueAt(filaSeleccionada, 5).toString());
            

            // Establece los valores en los campos de texto
            vista.txtModelo.setText(Modelo);
            vista.txtAño.setText(Año);
            //vista.cbMarca.setActionCommand(Marca);
            vista.cbCarga.setSelectedIndex(idCarga);
            vista.cbMarca.setSelectedIndex(idMarca);
            
        }
    }
    
    /*tpublic void CargarComboMarca(String tabla, String valor, JComboBox c){    
        Connection conexion = ClaseConexion.getConexion();
        //DefaultComboBoxModel combo = new DefaultComboBoxModel();
        try{
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from " + tabla  + " ORDER BY id_secuencia ASC");
            
            while (rs.next()) {
                int id = rs.getInt(1);
                String marca = rs.getString(2);
                
                c.addItem(new mdlModelo(id,marca));                
            }                       
                    
                            //c.setModel(combo);

        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
        }
    }*/
    
    public void CargarComboMarca(String tabla, String valor, JComboBox c) {
    Connection conexion = ClaseConexion.getConexion();
    ArrayList<mdlModelo> listaMarcas = new ArrayList<>();

    try {
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM " + tabla + " ORDER BY id_secuencia ASC");

        listaMarcas.add(new mdlModelo(0, "Seleccionar Marca"));

        while (rs.next()) {
            int id = rs.getInt(1); // Suponiendo que el ID está en la primera columna
            String marca = rs.getString(2); // Suponiendo que el nombre de la marca está en la segunda columna
            
            listaMarcas.add(new mdlModelo(id, marca));
        }

        DefaultComboBoxModel<mdlModelo> combo = new DefaultComboBoxModel<>(listaMarcas.toArray(new mdlModelo[0]));
        c.setModel(combo);

    } catch (SQLException ex) {
        ex.printStackTrace();   
    }
}
    
    public String[] to_Array(ArrayList<String> list)
    {
        String array[] = new String[list.size()];
        
        for (int i=0; i<array.length; i++)
        {
            array[i] = list.get(i);
        } 
        
        return array;
    }
   
    
}
