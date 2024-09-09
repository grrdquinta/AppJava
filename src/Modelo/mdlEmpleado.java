/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.EmpleadosPanel;
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
    private Number Masculino;
    private String Usuario;
    private String Contraseña;
    private String Licencia;

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    private String Telefono;

    public Number getMasculino() {
        return Masculino;
    }

    public void setMasculino(Number Masculino) {
        this.Masculino = Masculino;
    }

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
        modelo.setColumnIdentifiers(new Object[]{"DUI", "Nombre", "Apellido Paterno", "Apellido Materno", "Email", "Telefono", 
            "Salario", "Fecha Na", "Rol", "Sucursal", "Masculino", "Estado", "ID Rol", "ID Sucursal"});
        
        try
        {
            String query = "Select DUI, Empleado.Nombre, apellidopaterno, apellidomaterno, email,  telefono ,salario, fechana, Rol.NomRol,Sucursal.Nombre as Sucursal, masculino, estado, rol.idrol, sucursal.idsucursal \n" +
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
                    rs.getString(6), //Telefono
                    rs.getDouble(7), //Salario
                    rs.getDate(8),   //FechaNa
                    rs.getString(9), //Rol
                    rs.getString(10), //Sucursal
                    rs.getInt(11),   //Genero
                    rs.getInt(12),   //Estado
                    rs.getInt(13),   //Estado
                    rs.getInt(14)    //Estado
                
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
    
    public void almacenarDatosTabla(EmpleadosPanel vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbEmpleados.getSelectedRow();
        

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            
            vista.btnActuualizar.setEnabled(true);
            setDUI(vista.jtbEmpleados.getValueAt(filaSeleccionada, 0).toString());
            setNombre(vista.jtbEmpleados.getValueAt(filaSeleccionada, 1).toString());
            setApellidoPa(vista.jtbEmpleados.getValueAt(filaSeleccionada, 2).toString());
            setApellidoMa(vista.jtbEmpleados.getValueAt(filaSeleccionada, 3).toString());
            setEmail(vista.jtbEmpleados.getValueAt(filaSeleccionada, 4).toString());
            setTelefono(vista.jtbEmpleados.getValueAt(filaSeleccionada, 5).toString());
            setSalario(Double.parseDouble(vista.jtbEmpleados.getValueAt(filaSeleccionada, 6).toString()));
            setFechaNa(vista.jtbEmpleados.getValueAt(filaSeleccionada, 7).toString());
            setMasculino((int) vista.jtbEmpleados.getValueAt(filaSeleccionada, 10));
            setEstado((int) vista.jtbEmpleados.getValueAt(filaSeleccionada, 11));
            setIdRol((int) vista.jtbEmpleados.getValueAt(filaSeleccionada, 12));
            setIdSucursal((int) vista.jtbEmpleados.getValueAt(filaSeleccionada, 13));
        
            /*vista.txtPlaca.setText(Placa);
            vista.cbMarca.setSelectedIndex(idMarca);*/
           
            System.out.println(filaSeleccionada);
        }
    }
    
        public void CargarDatosTabla(InformacionEmpleados vista) {
        // Obtén la fila seleccionada 
        vista.txtDUI.setText(getDUI());
        vista.txtNombre.setText(getNombre());
        vista.txtApellidoPa.setText(getApellidoPa());
        vista.txtApellidoMa.setText(getApellidoMa());
        vista.txtEmail.setText(getEmail());
        vista.txtTelefono.setText(getTelefono());
        vista.txtSalario.setText(getSalario().toString());
        vista.txtFechaNa.setText(getFechaNa());
        vista.cbSexo.setSelectedIndex((int)getMasculino());
        vista.cbSucursal.setSelectedIndex((int)getIdSucursal());
        vista.cbRol.setSelectedIndex((int)getIdRol());
        vista.cbEstado.setSelectedIndex((int)getEstado());
        
    }
     
    public int AgregarEmpleado()
    {
        Connection conexion = ClaseConexion.getConexion();
        try
        {
            String sql = "insert into Empleado (DUI,Nombre, apellidopaterno, apellidomaterno, email, salario, fechana, idrol, idsucursal, masculino, Estado, Telefono)\n" +
            "Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?)";
            
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
            pstmt.setString(11, getTelefono().toString());
            
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
            
               
                String sql = "insert into Empleado (DUI,Nombre, apellidopaterno, apellidomaterno, email, salario, fechana, idrol, idsucursal, masculino, Estado, Telefono)\n" +
                "Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?)";

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
                pstmt.setString(11, getTelefono());

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
    
    public int ActualizarEmpleado(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        
        
            int filaSeleccionada = tabla.getSelectedRow();
            
            if (filaSeleccionada != -1) {
               
            String DUI = tabla.getValueAt(filaSeleccionada, 0).toString();
                
            try
               {
                String sql = "update empleado set dui= ?, nombre = ? , apellidoPaterno = ?, apellidoMaterno = ?, email = ?,\n" +
                "salario = ?, fechaNa = ?, idRol = ?, idSucursal = ?, masculino = ?, estado = ?, telefono = ? where dui = ?";

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
                pstmt.setInt(11, (int)getEstado());
                pstmt.setString(12, getTelefono());
                pstmt.setString(13, DUI);

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
            else {
            System.out.println("no");
            return 0;
            }
            
        
    }
    
    public boolean verificarCorreo(String email) {
        Connection conexion = ClaseConexion.getConexion();
        try {
            String sql = "SELECT COUNT(*) FROM Empleado WHERE email = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
               System.out.println(ex);
        }
        return false;
    }
    
    public boolean actualizarContrasena(String dui, String nuevaContrasena) {
        Connection conexion = ClaseConexion.getConexion();
        try {
            // Consulta SQL para actualizar la contraseña usando el DUI como criterio
            String sql = "UPDATE Usuario SET Contrasena = ? WHERE DUI = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, nuevaContrasena); // Establece la nueva contraseña
            pstmt.setString(2, dui); // Establece el DUI como criterio de búsqueda

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;  // Retorna true si al menos una fila fue actualizada

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;  // Retorna false si ocurre un error o ninguna fila fue actualizada
    }

    
    public String obtenerDuiPorEmail(String email) {
        Connection conexion = ClaseConexion.getConexion();
        try {
            String sql = "SELECT DUI FROM Empleado WHERE email = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("DUI");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
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


