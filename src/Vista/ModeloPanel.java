/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ControllerAddModelo;
import Controlador.ControllerModeloPanel;
import Modelo.ClaseConexion;
import Modelo.TabbedForm;
import Modelo.mdlModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelo.mdlModeloPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gerst
 */
public class ModeloPanel extends TabbedForm {

    /**
     * Creates new form ModeloPanel
     */
    public ModeloPanel() {
        initComponents();
        ModeloPanel vista = this;
        mdlModeloPanel modeloM = new mdlModeloPanel();
        ControllerModeloPanel controlador = new ControllerModeloPanel(modeloM, vista);
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle());
        Mostrar(jtbModelo,1);
        pagination1.addEventPagination(new EventPagination(){
            @Override
            public void pageChanged(int page) {
                    
                  loadData(page);
            }
        });
        
    }

    public void loadData(int page) {
        Connection conexion = ClaseConexion.getConexion();
        // Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{
            "ID Modelo", "Modelo", "Marca", "Año", "Carga", "IdMarca", "ID Carga"
        });

        try {
            modelo.setRowCount(0);  // Limpiar las filas existentes
            int limit = 10;  // Número de registros por página
            int offset = (page - 1) * limit;  // Calculamos el offset
            int limitUpper = page * limit;

            // Consulta para contar el total de registros
            String sqlCount = "SELECT COUNT(*) FROM Modelo INNER JOIN Marca ON Modelo.idmarca = Marca.idmarca";
            PreparedStatement pstmt = conexion.prepareStatement(sqlCount);
            int count = 0;
            ResultSet r = pstmt.executeQuery();
            if (r.next()) {
                count = r.getInt(1);  // Obtenemos el total de registros
            }
            System.out.println(count);
            int totalPage = (int) Math.ceil((double) count / limit);   // Calcula el total de páginas

            // Consulta para obtener los registros con paginación
             String query = "SELECT * FROM ( "
                + "SELECT Modelo.idModelo AS ID_Modelo, Modelo.modelo AS Nombre_Modelo, Marca.nommarca AS Marca, "
                + "Modelo.año AS Año, "
                + "CASE WHEN Modelo.tipovehiculo = 1 THEN 'Carga' WHEN modelo.tipovehiculo = 0 THEN 'Entrega' END AS TipoVehiculo, marca.idmarca, "
                + "Modelo.tipovehiculo as idTipoVehiculo, ROWNUM AS rnum "
                + "FROM Modelo "
                + "INNER JOIN Marca ON Modelo.idmarca = Marca.idmarca "
                + "WHERE ROWNUM <= ?) "
                + "WHERE rnum > ?";

            PreparedStatement statement = conexion.prepareStatement(query); 
            statement.setInt(1, page * limit); // Set the upper limit
            statement.setInt(2, offset);

            ResultSet rs = statement.executeQuery();

            // Llenamos el modelo con los datos obtenidos
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt(1), //ID
                    rs.getString(2), //Marca
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),  // Tipo Vehículo
                    rs.getInt(7)
                });
            }

            // Configuramos el modelo de la tabla
            jtbModelo.setModel(modelo);
            jtbModelo.getColumnModel().getColumn(0).setMinWidth(0);
            jtbModelo.getColumnModel().getColumn(0).setMaxWidth(0);
            jtbModelo.getColumnModel().getColumn(0).setWidth(0);
            jtbModelo.getColumnModel().getColumn(5).setMinWidth(0);
            jtbModelo.getColumnModel().getColumn(5).setMaxWidth(0);
            jtbModelo.getColumnModel().getColumn(5).setWidth(0);
            jtbModelo.getColumnModel().getColumn(6).setMinWidth(0);
            jtbModelo.getColumnModel().getColumn(6).setMaxWidth(0);
            jtbModelo.getColumnModel().getColumn(6).setWidth(0);


            // Actualizamos el componente de paginación (si existe)
            pagination1.setPagegination(page, totalPage);  // Actualizamos la paginación

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public void Mostrar(JTable tabla, int page) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{
            "ID Modelo", "Modelo", "Marca", "Año", "Carga", "IdMarca", "ID Carga"
        });

        try {
            modelo.setRowCount(0);  // Limpiar las filas existentes
            int limit = 10;  // Número de registros por página
            int offset = (page - 1) * limit;  // Calculamos el offset
            int limitUpper = page * limit;
            
             String sqlCount = "SELECT COUNT(*) FROM Modelo INNER JOIN Marca ON Modelo.idmarca = Marca.idmarca";
            PreparedStatement pstmt = conexion.prepareStatement(sqlCount);
            int count = 0;
            ResultSet r = pstmt.executeQuery();
            if (r.next()) {
                count = r.getInt(1);  // Obtenemos el total de registros
            }
            System.out.println(count);
            int totalPage = (int) Math.ceil((double) count / limit);   // Calcula el total de páginas

            // Consulta para obtener todos los registros ordenados
            String query = "SELECT * FROM ( "
                + "SELECT Modelo.idModelo AS ID_Modelo, Modelo.modelo AS Nombre_Modelo, Marca.nommarca AS Marca, "
                + "Modelo.año AS Año, "
                + "CASE WHEN Modelo.tipovehiculo = 1 THEN 'Carga' ELSE 'Entrega' END AS TipoVehiculo, marca.idmarca, "
                + "Modelo.tipovehiculo as idTipoVehiculo , ROWNUM AS rnum "
                + "FROM Modelo "
                + "INNER JOIN Marca ON Modelo.idmarca = Marca.idmarca "
                + "WHERE ROWNUM <= ?) "
                + "WHERE rnum > ?";

            PreparedStatement statement = conexion.prepareStatement(query); 
            statement.setInt(1, page * limit); // Set the upper limit
            statement.setInt(2, offset);
            
            //PreparedStatement statement = conexion.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            // Llenamos el modelo con los datos obtenidos
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt(1), //ID
                    rs.getString(2), //Marca
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),  // Tipo Vehículo
                    rs.getInt(7)
                });
            }

            // Configuramos el modelo de la tabla
            jtbModelo.setModel(modelo);
            jtbModelo.getColumnModel().getColumn(0).setMinWidth(0);
            jtbModelo.getColumnModel().getColumn(0).setMaxWidth(0);
            jtbModelo.getColumnModel().getColumn(0).setWidth(0);
            jtbModelo.getColumnModel().getColumn(5).setMinWidth(0);
            jtbModelo.getColumnModel().getColumn(5).setMaxWidth(0);
            jtbModelo.getColumnModel().getColumn(5).setWidth(0);
            jtbModelo.getColumnModel().getColumn(6).setMinWidth(0);
            jtbModelo.getColumnModel().getColumn(6).setMaxWidth(0);
            jtbModelo.getColumnModel().getColumn(6).setWidth(0);

            pagination1.setPagegination(page, totalPage);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbModelo = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        cbMarca = new javax.swing.JComboBox<>();
        txtModelo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtAño = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbCarga = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        pagination1 = new Vista.Pagination();

        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Año:");

        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Carga:");

        jtbModelo.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbModelo.setRowHeight(30);
        jScrollPane1.setViewportView(jtbModelo);

        btnAgregar.setBackground(new java.awt.Color(255, 204, 51));
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");

        btnActualizar.setBackground(new java.awt.Color(255, 204, 51));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");

        txtModelo.setBackground(new java.awt.Color(86, 86, 86));
        txtModelo.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Modelo:");

        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Marca:");

        txtAño.setBackground(new java.awt.Color(86, 86, 86));
        txtAño.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modelo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(565, 565, 565))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbCarga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrega", "Carga" }));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        pagination1.setBackground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(568, Short.MAX_VALUE)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(568, Short.MAX_VALUE))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(128, 128, 128)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(148, 148, 148)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)))
                        .addGap(395, 395, 395))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(btnActualizar)
                        .addGap(459, 459, 459))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnAgregar))
                .addGap(43, 43, 43)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    //public JComboBox<mdlModeloPanel> cbMarca;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnAgregar;
    public javax.swing.JComboBox<String> cbCarga;
    public javax.swing.JComboBox<mdlModeloPanel> cbMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtbModelo;
    private Vista.Pagination pagination1;
    public javax.swing.JTextField txtAño;
    public javax.swing.JTextField txtModelo;
    // End of variables declaration//GEN-END:variables
}
