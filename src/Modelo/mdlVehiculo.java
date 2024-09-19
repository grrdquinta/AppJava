/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.FlotaPanel;
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
public class mdlVehiculo {

    private int IdMarca;
    private String NomMarca;
    private int IdModelo;
    private String Modelo;
    private String Año;
    private int Carga;
    private String Placa;
    private int IdSucursal;
    private int IdEstado;

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int IdEstado) {
        this.IdEstado = IdEstado;
    }
    
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
 
    public String toString() {
        // Mostrar tanto el nombre del modelo como el nombre de la marca
        if (Modelo != null && !Modelo.isEmpty() && NomMarca != null && !NomMarca.isEmpty()) {
            return Modelo + " - " + NomMarca; // Combinación del modelo y la marca
        } else if (Modelo != null && !Modelo.isEmpty()) {
            return Modelo; // Solo el modelo si no hay marca
        } else if (NomMarca != null && !NomMarca.isEmpty()) {
            return NomMarca; // Solo la marca si no hay modelo
        } else {
            return "Sin información"; // Caso de excepción si ninguno está disponible
        }
    }
    
    public void GuardarVehiculo() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO Vehiculo(placavehiculo, idmodelo, idsucursal, estado) VALUES (?,?,?,0)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, getPlaca());
            pstmt.setInt(2, getIdModelo());
            pstmt.setInt(3, getIdSucursal());

            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla){
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Placa", "Modelo", "Marca", "Año", "Sucursal", 
            "Carga", "Estado", "ID Marca", "ID Modelo", "ID Sucursal", "ID Secuencia Marca"});
        
        try
        {
            String query = "select placavehiculo, modelo.modelo, marca.nommarca as Marca,modelo.año, sucursal.nombre as Sucursal , modelo.carga, estado, modelo.idmarca, modelo.idmodelo, sucursal.idSucursal, marca.id_secuencia from vehiculo\n" +
            "inner join Modelo on vehiculo.idmodelo = modelo.idmodelo\n" +
            "inner join Marca on modelo.idmarca = marca.idmarca\n" +
            "inner join sucursal on vehiculo.idsucursal = sucursal.idsucursal";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3), 
                    rs.getString(4), 
                    rs.getString(5), 
                    rs.getInt(6), 
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10),
                    rs.getInt(11)
                    }
                );
                
            }
            //tabla.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(6).setMinWidth(0);
            tabla.getColumnModel().getColumn(6).setMaxWidth(0);
            tabla.getColumnModel().getColumn(6).setWidth(0);
            tabla.getColumnModel().getColumn(7).setMinWidth(0);
            tabla.getColumnModel().getColumn(7).setMaxWidth(0);
            tabla.getColumnModel().getColumn(7).setWidth(0);
            tabla.getColumnModel().getColumn(8).setMinWidth(0);
            tabla.getColumnModel().getColumn(8).setMaxWidth(0);
            tabla.getColumnModel().getColumn(8).setWidth(0);
            tabla.getColumnModel().getColumn(9).setMinWidth(0);
            tabla.getColumnModel().getColumn(9).setMaxWidth(0);
            tabla.getColumnModel().getColumn(9).setWidth(0);
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
        }
        
    }
    
    
    public mdlVehiculo()
    {
       
    }
    
    public mdlVehiculo(int id, String marca)
    {
        this.IdMarca = id;
        this.NomMarca = marca;
    }
    
    public mdlVehiculo(String modelo, int id)
    {
        this.Modelo = modelo;
        this.IdModelo = id;
    }
    
    public void CargarComboMarca(String tabla, String valor, JComboBox c) {
    Connection conexion = ClaseConexion.getConexion();
    ArrayList<mdlVehiculo> listaMarcas = new ArrayList<>();

    try {
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM " + tabla + " ORDER BY id_secuencia ASC");

        listaMarcas.add(new mdlVehiculo(0, "Seleccionar Marca"));

        while (rs.next()) {
            int id = rs.getInt(1); // Suponiendo que el ID está en la primera columna
            String marca = rs.getString(2); // Suponiendo que el nombre de la marca está en la segunda columna
            
            listaMarcas.add(new mdlVehiculo(id, marca));
        }

        DefaultComboBoxModel<mdlVehiculo> combo = new DefaultComboBoxModel<>(listaMarcas.toArray(new mdlVehiculo[0]));
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
    
    public void CargarComboModelo(String tabla, String valor, JComboBox c){    
      Connection conexion = ClaseConexion.getConexion();
        ArrayList<mdlVehiculo>listaModelos = new ArrayList<>();
        
        try{
            Statement statement = conexion.createStatement();
            String sql = ("Select * from " + tabla + " where idMarca = ? ORDER BY id_secuencia ASC");
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, getIdMarca());
            ResultSet rs = pstmt.executeQuery();
            
            listaModelos.add(new mdlVehiculo(0, "Seleccionar Modelo"));
            while (rs.next()) {
                //list_Modelos.add(rs.getString(valor));
                int id = rs.getInt("IdModelo");
                String modelo = rs.getString(valor);
                 listaModelos.add(new mdlVehiculo(modelo, id));
                //c.addItem(new mdlVehiculo(modelo,id));                
            }                       
                 
            DefaultComboBoxModel combo = new DefaultComboBoxModel(listaModelos.toArray(new mdlVehiculo[0]));
            c.setModel(combo);
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
            ResultSet rs = statement.executeQuery("Select * from " + tabla);
            
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
    
    public void CargarInfoCombo(FlotaPanel vista){    
      Connection conexion = ClaseConexion.getConexion();
        
        try{
            Statement statement = conexion.createStatement();
            String sql = ("select idModelo, modelo.modelo, marca.nommarca as Marca,modelo.año, modelo.carga, marca.id_secuencia from Modelo inner join Marca on modelo.idmarca = marca.idmarca\n" +
            "where idModelo = ?");
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, getIdModelo());
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()) {
              String año = rs.getString(4);
              String carga = rs.getString("Carga".toString());
              vista.txtAño.setText(año);
              
              if(carga.equals("0"))
              {
                  vista.txtCarga.setText("Envio");
              }
              else
              {
                  vista.txtCarga.setText("Carga");
              }
              
            }                       
                 
                            //c.setModel(combo);

        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String PlacaVehiculo = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update Vehiculo set placavehiculo= ?, idmodelo = ?, idsucursal = ?, estado = ? where placavehiculo = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getPlaca());
                updateUser.setInt(2, (int)getIdModelo());
                updateUser.setInt(3, getIdSucursal());
                updateUser.setInt(4, getIdEstado());
                updateUser.setString(5, PlacaVehiculo);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void cargarDatosTabla(FlotaPanel vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbVehiculo.getSelectedRow();
        

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            
            String Placa = vista.jtbVehiculo.getValueAt(filaSeleccionada, 0).toString();
            int idMarca = (int) vista.jtbVehiculo.getValueAt(filaSeleccionada, 10);
            int idSucursal = (int) vista.jtbVehiculo.getValueAt(filaSeleccionada, 9);
        
            vista.txtPlaca.setText(Placa);
            vista.cbMarca.setSelectedIndex(idMarca);
            vista.cbSucursal.setSelectedIndex(idSucursal);
            vista.lblEstado.setVisible(true);
            vista.cbEstado.setVisible(true);
            System.out.println(filaSeleccionada);
        }
    }
    
    public void Limpiar(FlotaPanel vista) {
        vista.txtPlaca.setText("");
        vista.cbMarca.setSelectedIndex(0);
        vista.cbSucursal.setSelectedIndex(0);
        vista.txtAño.setText("");
        vista.txtCarga.setText("");
        vista.cbEstado.setSelectedIndex(0);
    }
    
}
