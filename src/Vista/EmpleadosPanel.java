package Vista;

import Controlador.ControllerEmpleados;
import Modelo.ClaseConexion;
import Modelo.SessionVar;
import Modelo.TabbedForm;
import Modelo.mdlEmpleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author gerst
 */
public class EmpleadosPanel extends TabbedForm {

    /**
     * Creates new form PaquetesPanel
     */
    public EmpleadosPanel() {
       initComponents();
       
       EmpleadosPanel thisPanel = this;

       mdlEmpleado modelo = new mdlEmpleado();
       ControllerEmpleados controlador = new ControllerEmpleados(modelo, thisPanel);
       pagination1.setPaginationItemRender(new PaginationItemRenderStyle());
       if(SessionVar.getIdRol() == 1){ 
        Mostrar(jtbEmpleados,1);}
       else if(SessionVar.getIdRol() == 2) {
        MostrarAdmin(jtbEmpleados,1);
       }
        pagination1.addEventPagination(new EventPagination(){
            @Override
            public void pageChanged(int page) {
                if(SessionVar.getIdRol() == 1){    
                  LoadData(page);
                }
                else{
                    LoadDataAdmin(page);
                }
            }
        });
       this.setVisible(true);
    }
    
    public void LoadData(int page) {
    Connection conexion = ClaseConexion.getConexion();
    
    //Definimos el modelo de la tabla
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{
        "DUI", "Nombre", "Apellido Paterno", "Apellido Materno", "Email", "Telefono",
        "Salario", "Fecha Na", "Rol", "Sucursal", "Masculino", "Estado", "ID Rol", "ID Sucursal"
    });
    try {
        int limit = 10;  // Número de registros por página
        int offset = (page - 1) * limit;

        // Consulta para contar el número total de registros
        String sqlCount = "SELECT COUNT(*) FROM Empleado "
                        + "INNER JOIN Rol ON Empleado.IdRol = Rol.idRol "
                        + "INNER JOIN Sucursal ON Empleado.idSucursal = Sucursal.idsucursal";
        PreparedStatement pstmtCount = conexion.prepareStatement(sqlCount);
        ResultSet rsCount = pstmtCount.executeQuery();
        int count = 0;
        if (rsCount.next()) {
            count = rsCount.getInt(1);  // Total de registros
        }

        int totalPage = (int) Math.ceil((double) count / limit);  // Total de páginas

        // Consulta con paginación
        String query = "SELECT * FROM ( " 
        +"SELECT DUI, Empleado.Nombre, apellidopaterno, apellidomaterno, email, telefono, salario, " 
        +"fechana, Rol.NomRol, Sucursal.Nombre AS NombreSucursal, sexo, estado, " 
        +"Rol.idrol, Sucursal.idSucursal, ROWNUM rnum " 
        +"FROM Empleado " 
        +"INNER JOIN Rol ON Empleado.IdRol = Rol.idRol " 
        +"INNER JOIN Sucursal ON Empleado.idSucursal = Sucursal.idSucursal " 
        +"WHERE ROWNUM <= ? " 
        +"ORDER BY Empleado.DUI) " 
        + "WHERE rnum > ?";

        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setInt(1, page * limit);  // Establecer el límite superior
        statement.setInt(2, offset);        // Establecer el offset

        ResultSet rs = statement.executeQuery();

        // Llenamos el modelo con los datos obtenidos
        while (rs.next()) {
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
            });
        }
        
        // Ocultar algunas columnas si es necesario
        jtbEmpleados.getColumnModel().getColumn(10).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(10).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(10).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setWidth(0);

        // Actualizar la paginación
        pagination1.setPagegination(page, totalPage);  // Ajusta este componente según tu lógica de paginación

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    
    public void Mostrar(JTable tabla, int page) {
    Connection conexion = ClaseConexion.getConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{
        "DUI", "Nombre", "Apellido Paterno", "Apellido Materno", "Email", "Telefono",
        "Salario", "Fecha Na", "Rol", "Sucursal", "Masculino", "Estado", "ID Rol", "ID Sucursal"
    });

    try {
        int limit = 10;  // Número de registros por página
        int offset = (page - 1) * limit;

        // Consulta para contar el número total de registros
        String sqlCount = "SELECT COUNT(*) FROM Empleado "
                        + "INNER JOIN Rol ON Empleado.IdRol = Rol.idRol "
                        + "INNER JOIN Sucursal ON Empleado.idSucursal = Sucursal.idsucursal";
        PreparedStatement pstmtCount = conexion.prepareStatement(sqlCount);
        ResultSet rsCount = pstmtCount.executeQuery();
        int count = 0;
        if (rsCount.next()) {
            count = rsCount.getInt(1);  // Total de registros
        }
        
        // Imprimir el total de registros y páginas para verificar
        System.out.println("Total de registros: " + count);
        int totalPage = (int) Math.ceil((double) count / limit);  // Total de páginas
        System.out.println("Total de páginas: " + totalPage);

        // Consulta con paginación
        String query = "SELECT * FROM ( " 
        +"SELECT DUI, Empleado.Nombre, apellidopaterno, apellidomaterno, email, telefono, salario, " 
        +"fechana, Rol.NomRol, Sucursal.Nombre AS NombreSucursal, sexo, estado, " 
        +"Rol.idrol, Sucursal.idSucursal, ROWNUM rnum " 
        +"FROM Empleado " 
        +"INNER JOIN Rol ON Empleado.IdRol = Rol.idRol " 
        +"INNER JOIN Sucursal ON Empleado.idSucursal = Sucursal.idSucursal " 
        +"WHERE ROWNUM <= ? " 
        +"ORDER BY Empleado.DUI) " 
        + "WHERE rnum > ?";

        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setInt(1, page * limit);  // Establecer el límite superior
        statement.setInt(2, offset);        // Establecer el offset

        ResultSet rs = statement.executeQuery();

        // Llenamos el modelo con los datos obtenidos
        while (rs.next()) {
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
            });
        }

        // Asignar el modelo a la tabla
        tabla.setModel(modelo);

        // Ocultar algunas columnas si es necesario
        jtbEmpleados.getColumnModel().getColumn(10).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(10).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(10).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setWidth(0);

        // Actualizar la paginación
        pagination1.setPagegination(page, totalPage);  // Ajusta este componente según tu lógica de paginación

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    
    public void LoadDataAdmin(int page) {
    Connection conexion = ClaseConexion.getConexion();
    
    //Definimos el modelo de la tabla
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{
        "DUI", "Nombre", "Apellido Paterno", "Apellido Materno", "Email", "Telefono",
        "Salario", "Fecha Na", "Rol", "Sucursal", "Masculino", "Estado", "ID Rol", "ID Sucursal"
    });
    try {
        int limit = 10;  // Número de registros por página
        int offset = (page - 1) * limit;

        // Consulta para contar el número total de registros
        String sqlCount = "SELECT COUNT(*) FROM Empleado "
                        + "INNER JOIN Rol ON Empleado.IdRol = Rol.idRol "
                        + "INNER JOIN Sucursal ON Empleado.idSucursal = Sucursal.idsucursal";
        PreparedStatement pstmtCount = conexion.prepareStatement(sqlCount);
        ResultSet rsCount = pstmtCount.executeQuery();
        int count = 0;
        if (rsCount.next()) {
            count = rsCount.getInt(1);  // Total de registros
        }

        int totalPage = (int) Math.ceil((double) count / limit);  // Total de páginas

        // Consulta con paginación
        String query = "SELECT * FROM ( " 
        +"SELECT DUI, Empleado.Nombre, apellidopaterno, apellidomaterno, email, telefono, salario, " 
        +"fechana, Rol.NomRol, Sucursal.Nombre AS NombreSucursal, sexo, estado, " 
        +"Rol.idrol, Sucursal.idSucursal, ROWNUM rnum " 
        +"FROM Empleado " 
        +"INNER JOIN Rol ON Empleado.IdRol = Rol.idRol " 
        +"INNER JOIN Sucursal ON Empleado.idSucursal = Sucursal.idSucursal " 
        +"WHERE empleado.idsucursal = ? " 
        +"AND ROWNUM <= ? " 
        +"ORDER BY Empleado.DUI) " 
        + "WHERE rnum > ?";

        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setInt(1, SessionVar.getIdSucursal()); 
        statement.setInt(2, page * limit);  // Establecer el límite superior
        statement.setInt(3, offset);        // Establecer el offset

        ResultSet rs = statement.executeQuery();

        // Llenamos el modelo con los datos obtenidos
        while (rs.next()) {
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
            });
        }
        
        // Ocultar algunas columnas si es necesario
        jtbEmpleados.getColumnModel().getColumn(10).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(10).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(10).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setWidth(0);

        // Actualizar la paginación
        pagination1.setPagegination(page, totalPage);  // Ajusta este componente según tu lógica de paginación

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    
    public void MostrarAdmin(JTable tabla, int page) {
    Connection conexion = ClaseConexion.getConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{
        "DUI", "Nombre", "Apellido Paterno", "Apellido Materno", "Email", "Telefono",
        "Salario", "Fecha Na", "Rol", "Sucursal", "Masculino", "Estado", "ID Rol", "ID Sucursal"
    });

    try {
        int limit = 10;  // Número de registros por página
        int offset = (page - 1) * limit;

        // Consulta para contar el número total de registros
        String sqlCount = "SELECT COUNT(*) FROM Empleado "
                        + "INNER JOIN Rol ON Empleado.IdRol = Rol.idRol "
                        + "INNER JOIN Sucursal ON Empleado.idSucursal = Sucursal.idsucursal";
        PreparedStatement pstmtCount = conexion.prepareStatement(sqlCount);
        ResultSet rsCount = pstmtCount.executeQuery();
        int count = 0;
        if (rsCount.next()) {
            count = rsCount.getInt(1);  // Total de registros
        }
        
        // Imprimir el total de registros y páginas para verificar
        System.out.println("Total de registros: " + count);
        int totalPage = (int) Math.ceil((double) count / limit);  // Total de páginas
        System.out.println("Total de páginas: " + totalPage);

        // Consulta con paginación
        String query = "SELECT * FROM ( " 
        +"SELECT DUI, Empleado.Nombre, apellidopaterno, apellidomaterno, email, telefono, salario, " 
        +"fechana, Rol.NomRol, Sucursal.Nombre AS NombreSucursal, sexo, estado, " 
        +"Rol.idrol, Sucursal.idSucursal, ROWNUM rnum " 
        +"FROM Empleado " 
        +"INNER JOIN Rol ON Empleado.IdRol = Rol.idRol " 
        +"INNER JOIN Sucursal ON Empleado.idSucursal = Sucursal.idSucursal "
        +"where empleado.idsucursal = ? " 
        +"AND ROWNUM <= ? " 
        +"ORDER BY Empleado.DUI) " 
        + "WHERE rnum > ?";

        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setInt(1, SessionVar.getIdSucursal()); 
        statement.setInt(2, page * limit);  // Establecer el límite superior
        statement.setInt(3, offset);        // Establecer el offset

        ResultSet rs = statement.executeQuery();

        // Llenamos el modelo con los datos obtenidos
        while (rs.next()) {
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
            });
        }

        // Asignar el modelo a la tabla
        tabla.setModel(modelo);

        // Ocultar algunas columnas si es necesario
        jtbEmpleados.getColumnModel().getColumn(10).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(10).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(10).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(11).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(12).setWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setMinWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setMaxWidth(0);
        jtbEmpleados.getColumnModel().getColumn(13).setWidth(0);

        // Actualizar la paginación
        pagination1.setPagegination(page, totalPage);  // Ajusta este componente según tu lógica de paginación

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbEmpleados = new javax.swing.JTable();
        btnEmpleado = new javax.swing.JButton();
        btnActuualizar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pagination1 = new Vista.Pagination();

        jtbEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbEmpleados);

        btnEmpleado.setBackground(new java.awt.Color(255, 204, 51));
        btnEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        btnEmpleado.setText("Nuevo Empleado");

        btnActuualizar.setBackground(new java.awt.Color(255, 204, 51));
        btnActuualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActuualizar.setText("Actualizar");

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        pagination1.setBackground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEmpleado)
                .addGap(32, 32, 32)
                .addComponent(btnActuualizar)
                .addGap(33, 33, 33))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmpleado)
                    .addComponent(btnActuualizar))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActuualizar;
    public javax.swing.JButton btnEmpleado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtbEmpleados;
    private Vista.Pagination pagination1;
    // End of variables declaration//GEN-END:variables
}
