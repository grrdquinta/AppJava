/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ControllerAddMarca;
import Controlador.ControllerMarcaPanel;
import Modelo.ClaseConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelo.TabbedForm;
import Modelo.mdlMarca;
import Modelo.mdlMarcaPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gerst
 */
public class MarcaPanel extends TabbedForm {

    /**
     * Creates new form MarcaPanel
     */
    public MarcaPanel() {
        initComponents();
        MarcaPanel marca = this;
        mdlMarcaPanel modelo = new mdlMarcaPanel();
        ControllerMarcaPanel controlador = new ControllerMarcaPanel(modelo, marca);
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle());
        MostrarMarca(jtbMarca,1);
        pagination1.addEventPagination(new EventPagination(){
            @Override
            public void pageChanged(int page) {
                    
                  loadMarcaData(page);
            }
        });
    }

    public void loadMarcaData(int page) {
    Connection conexion = ClaseConexion.getConexion();
    // Definimos el modelo de la tabla
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"idMarca", "Marca"});

    try {
        // Establecemos el número de filas por página y calculamos el offset
        int limit = 10;  // Número de registros por página
        int offset = (page - 1) * limit;
        int limitUpper = page * limit;

        // Consulta para contar el total de registros
        String sqlCount = "SELECT COUNT(*) FROM Marca";
        PreparedStatement pstmtCount = conexion.prepareStatement(sqlCount);
        ResultSet rsCount = pstmtCount.executeQuery();
        int count = 0;
        if (rsCount.next()) {
            count = rsCount.getInt(1);  // Total de registros
        }
        int totalPage = (int) Math.ceil((double) count / limit);  // Calcula el total de páginas

        // Consulta con paginación
        String query = "SELECT * FROM ("
                + "SELECT idmarca, nommarca, ROWNUM rnum FROM Marca "
                + "WHERE ROWNUM <= " + limitUpper + ") "
                + "WHERE rnum > " + offset ;

        PreparedStatement statement = conexion.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        // Llenamos el modelo con los datos obtenidos
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("idmarca"),
                rs.getString("nommarca")
            });
        }

        // Configuramos el modelo de la tabla
        jtbMarca.setModel(modelo);

        // Ocultamos la columna de ID
        jtbMarca.getColumnModel().getColumn(0).setMinWidth(0);
        jtbMarca.getColumnModel().getColumn(0).setMaxWidth(0);
        jtbMarca.getColumnModel().getColumn(0).setWidth(0);

        // Actualizar la paginación
        pagination1.setPagegination(page, totalPage);  // Actualizamos el componente de paginación

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    
    public void MostrarMarca(JTable tabla, int page) {
    Connection conexion = ClaseConexion.getConexion();
    // Definimos el modelo de la tabla
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"idMarca", "Marca"});

    try {
        // Establecemos el número de filas por página y calculamos el offset
        int limit = 10;  // Número de registros por página
        int offset = (page - 1) * limit;
        int limitUpper = page * limit;

        // Consulta para contar el total de registros
        String sqlCount = "SELECT COUNT(*) FROM Marca";
        PreparedStatement pstmtCount = conexion.prepareStatement(sqlCount);
        ResultSet rsCount = pstmtCount.executeQuery();
        int count = 0;
        if (rsCount.next()) {
            count = rsCount.getInt(1);  // Total de registros
        }
        int totalPage = (int) Math.ceil((double) count / limit);  // Calcula el total de páginas

        // Consulta con paginación
        String query = "SELECT * FROM ("
                + "SELECT idmarca, nommarca, ROWNUM rnum FROM Marca "
                + "WHERE ROWNUM <= " + limitUpper + ") "
                + "WHERE rnum > " + offset ;

        PreparedStatement statement = conexion.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        // Llenamos el modelo con los datos obtenidos
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("idmarca"),
                rs.getString("nommarca")
            });
        }

        // Configuramos el modelo de la tabla
        tabla.setModel(modelo);

        // Ocultamos la columna de ID
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setWidth(0);

        // Actualizar la paginación
        pagination1.setPagegination(page, totalPage);  // Actualizamos el componente de paginación

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMarca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbMarca = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pagination1 = new Vista.Pagination();

        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("Nombre Marca");

        btnAgregar.setBackground(new java.awt.Color(255, 204, 51));
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");

        btnActualizar.setBackground(new java.awt.Color(255, 204, 51));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");

        jtbMarca.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbMarca.setRowHeight(30);
        jScrollPane1.setViewportView(jtbMarca);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Marca");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(578, 578, 578))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        pagination1.setBackground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(225, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(88, 88, 88)
                        .addComponent(btnActualizar)
                        .addGap(470, 470, 470))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(515, 515, 515))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnAgregar))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnAgregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtbMarca;
    private Vista.Pagination pagination1;
    public javax.swing.JTextField txtMarca;
    // End of variables declaration//GEN-END:variables
}
