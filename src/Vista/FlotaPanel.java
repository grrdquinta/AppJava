/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ControllerVehiculo;
import Modelo.ClaseConexion;
import Modelo.TabbedForm;
import Modelo.mdlVehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author gerst
 */
public class FlotaPanel extends TabbedForm {

    /**
     * Creates new form FlotaPanel
     */
    public FlotaPanel() {
        initComponents();
        
       FlotaPanel thisPanel = this;

        mdlVehiculo modelo = new mdlVehiculo();
        ControllerVehiculo controlador = new ControllerVehiculo(modelo, thisPanel);
       
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle());
        Mostrar(jtbVehiculo,1);
        pagination1.addEventPagination(new EventPagination(){
            @Override
            public void pageChanged(int page) {
                    
                  loadData(page);
            }
        });
       this.setVisible(true);
    }

    public void loadData(int page) {
    Connection conexion = ClaseConexion.getConexion();
    // Definimos el modelo de la tabla
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"Placa", "Modelo", "Marca", "Año", "Sucursal", 
        "Carga", "Estado", "ID Marca", "ID Modelo", "ID Sucursal"});

    try {
        // Establecemos el número de filas por página y calculamos el offset
        int limit = 10;  // Número de registros por página
        int offset = (page - 1) * limit;
        int limitUpper = page * limit;

        // Consulta para contar el total de registros
        String sqlCount = "SELECT COUNT(*) FROM vehiculo "
                + "INNER JOIN Modelo ON vehiculo.idmodelo = modelo.idmodelo "
                + "INNER JOIN Marca ON modelo.idmarca = marca.idmarca "
                + "INNER JOIN sucursal ON vehiculo.idsucursal = sucursal.idsucursal";
        PreparedStatement pstmtCount = conexion.prepareStatement(sqlCount);
        ResultSet rsCount = pstmtCount.executeQuery();
        int count = 0;
        if (rsCount.next()) {
            count = rsCount.getInt(1);  // Total de registros
        }
        int totalPage = (int) Math.ceil((double) count / limit);  // Calcula el total de páginas

        // Consulta con paginación
        String query = "SELECT * FROM ("
                + "SELECT vehiculo.placavehiculo, modelo.modelo, marca.nommarca AS Marca, modelo.año, "
                + "sucursal.nombre AS Sucursal, "
                + "CASE WHEN modelo.tipovehiculo = 1 THEN 'Carga' WHEN modelo.tipovehiculo = 0 THEN 'Entrega' END AS TipoVehiculo, "
                + "vehiculo.estado, modelo.idmarca, modelo.idmodelo, sucursal.idSucursal, "
                + "ROWNUM AS row_num "
                + "FROM vehiculo "
                + "INNER JOIN modelo ON vehiculo.idmodelo = modelo.idmodelo "
                + "INNER JOIN marca ON modelo.idmarca = marca.idmarca "
                + "INNER JOIN sucursal ON vehiculo.idsucursal = sucursal.idsucursal) "
                + "WHERE row_num BETWEEN ? AND ? order by año desc";

        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setInt(1, offset + 1);  // Desde el registro
        statement.setInt(2, offset + limit);  // Hasta el registro

        ResultSet rs = statement.executeQuery();

        // Llenamos el modelo con los datos obtenidos
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getString("placavehiculo"),
                rs.getString("modelo"),
                rs.getString("Marca"),
                rs.getString("año"),
                rs.getString("Sucursal"),
                rs.getString("TipoVehiculo"),
                rs.getString("estado"),
                rs.getInt("idmarca"),
                rs.getInt("idmodelo"),
                rs.getInt("idSucursal")
            });
        }

        // Configuramos el modelo de la tabla
        jtbVehiculo.setModel(modelo);

        // Ocultamos algunas columnas si es necesario
        jtbVehiculo.getColumnModel().getColumn(6).setMinWidth(0);
        jtbVehiculo.getColumnModel().getColumn(6).setMaxWidth(0);
        jtbVehiculo.getColumnModel().getColumn(6).setWidth(0);
        jtbVehiculo.getColumnModel().getColumn(7).setMinWidth(0);
        jtbVehiculo.getColumnModel().getColumn(7).setMaxWidth(0);
        jtbVehiculo.getColumnModel().getColumn(7).setWidth(0);
        jtbVehiculo.getColumnModel().getColumn(8).setMinWidth(0);
        jtbVehiculo.getColumnModel().getColumn(8).setMaxWidth(0);
        jtbVehiculo.getColumnModel().getColumn(8).setWidth(0);
        jtbVehiculo.getColumnModel().getColumn(9).setMinWidth(0);
        jtbVehiculo.getColumnModel().getColumn(9).setMaxWidth(0);
        jtbVehiculo.getColumnModel().getColumn(9).setWidth(0);
        jtbVehiculo.getColumnModel().getColumn(10).setMinWidth(0);
        jtbVehiculo.getColumnModel().getColumn(10).setMaxWidth(0);
        jtbVehiculo.getColumnModel().getColumn(10).setWidth(0);

        // Actualizar paginación
        pagination1.setPagegination(page, totalPage);  // Actualizamos el componente de paginación

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    
    public void Mostrar(JTable tabla, int page) {
    Connection conexion = ClaseConexion.getConexion();
    // Definimos el modelo de la tabla
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"Placa", "Modelo", "Marca", "Año", "Sucursal", 
        "Carga", "Estado", "ID Marca", "ID Modelo", "ID Sucursal"});

    try {
        // Establecemos el número de filas por página y calculamos el offset
        int limit = 10;  // Número de registros por página
        int offset = (page - 1) * limit;
        int limitUpper = page * limit;

        // Consulta para contar el total de registros
        String sqlCount = "SELECT COUNT(*) FROM vehiculo "
                + "INNER JOIN Modelo ON vehiculo.idmodelo = modelo.idmodelo "
                + "INNER JOIN Marca ON modelo.idmarca = marca.idmarca "
                + "INNER JOIN sucursal ON vehiculo.idsucursal = sucursal.idsucursal";
        PreparedStatement pstmtCount = conexion.prepareStatement(sqlCount);
        ResultSet rsCount = pstmtCount.executeQuery();
        int count = 0;
        if (rsCount.next()) {
            count = rsCount.getInt(1);  // Total de registros
        }
        int totalPage = (int) Math.ceil((double) count / limit);  // Calcula el total de páginas

        // Consulta con paginación
        String query = "SELECT * FROM ("
                + "SELECT vehiculo.placavehiculo, modelo.modelo, marca.nommarca AS Marca, modelo.año, "
                + "sucursal.nombre AS Sucursal, "
                + "CASE WHEN modelo.tipovehiculo = 1 THEN 'Carga' WHEN modelo.tipovehiculo = 0 THEN 'Entrega' END AS TipoVehiculo, "
                + "vehiculo.estado, modelo.idmarca, modelo.idmodelo, sucursal.idSucursal, "
                + "ROWNUM AS row_num "
                + "FROM vehiculo "
                + "INNER JOIN modelo ON vehiculo.idmodelo = modelo.idmodelo "
                + "INNER JOIN marca ON modelo.idmarca = marca.idmarca "
                + "INNER JOIN sucursal ON vehiculo.idsucursal = sucursal.idsucursal) "
                + "WHERE row_num BETWEEN ? AND ? order by año desc";

        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setInt(1, offset + 1);  // Desde el registro
        statement.setInt(2, offset + limit);  // Hasta el registro

        ResultSet rs = statement.executeQuery();

        // Llenamos el modelo con los datos obtenidos
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getString("placavehiculo"),
                rs.getString("modelo"),
                rs.getString("Marca"),
                rs.getString("año"),
                rs.getString("Sucursal"),
                rs.getString("TipoVehiculo"),
                rs.getString("estado"),
                rs.getInt("idmarca"),
                rs.getInt("idmodelo"),
                rs.getInt("idSucursal")
            });
        }

        // Configuramos el modelo de la tabla
        tabla.setModel(modelo);
        
        // Ocultamos algunas columnas si es necesario
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


        // Actualizar paginación (si tienes un componente visual para paginación)
        pagination1.setPagegination(page, totalPage);  // Esta línea la ajustas según tu sistema de paginación

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbVehiculo = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAño = new javax.swing.JTextField();
        txtCarga = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnAgregarMarca = new javax.swing.JButton();
        btnAgregarModelo = new javax.swing.JButton();
        cbMarca = new javax.swing.JComboBox<>();
        cbModelo = new javax.swing.JComboBox<>();
        cbSucursal = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        btnMapa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pagination1 = new Vista.Pagination();

        jtbVehiculo.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbVehiculo.setRowHeight(30);
        jScrollPane1.setViewportView(jtbVehiculo);

        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Placa:");

        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Marca:");

        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Modelo:");

        txtPlaca.setForeground(new java.awt.Color(153, 153, 153));

        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Año:");

        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Tipo:");

        txtAño.setForeground(new java.awt.Color(153, 153, 153));

        txtCarga.setForeground(new java.awt.Color(153, 153, 153));

        btnGuardar.setBackground(new java.awt.Color(255, 204, 51));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Agregar");

        btnActualizar.setBackground(new java.awt.Color(255, 204, 51));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");

        btnAgregarMarca.setBackground(new java.awt.Color(0, 51, 102));
        btnAgregarMarca.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarMarca.setText("Agregar Marca");

        btnAgregarModelo.setBackground(new java.awt.Color(0, 51, 102));
        btnAgregarModelo.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarModelo.setText("Agregar Modelo");

        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Sucursal:");

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Desactivo" }));

        lblEstado.setForeground(new java.awt.Color(0, 51, 153));
        lblEstado.setText("Estado:");

        btnMapa.setBackground(new java.awt.Color(255, 204, 51));
        btnMapa.setForeground(new java.awt.Color(255, 255, 255));
        btnMapa.setText("Ver Mapa");

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        pagination1.setBackground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarMarca)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarModelo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(txtPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                .addComponent(cbSucursal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEstado)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(cbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(739, 739, 739)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMapa)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarModelo)
                    .addComponent(btnAgregarMarca))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel6))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lblEstado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnMapa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnAgregarMarca;
    public javax.swing.JButton btnAgregarModelo;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnMapa;
    public javax.swing.JComboBox<String> cbEstado;
    public javax.swing.JComboBox<mdlVehiculo> cbMarca;
    public javax.swing.JComboBox<mdlVehiculo> cbModelo;
    public javax.swing.JComboBox<mdlVehiculo> cbSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtbVehiculo;
    public javax.swing.JLabel lblEstado;
    private Vista.Pagination pagination1;
    public javax.swing.JTextField txtAño;
    public javax.swing.JTextField txtCarga;
    public javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
