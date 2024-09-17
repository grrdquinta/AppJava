package Vista;

import Controlador.ControllerSucursales;
import Modelo.TabbedForm;
import Modelo.mdlSucursales;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

public class FrmSucursales extends TabbedForm {

    public FrmSucursales() {
        initComponents();
        FrmSucursales vista = this;
        mdlSucursales modelo = new mdlSucursales();
        ControllerSucursales controlador = new ControllerSucursales(vista, modelo);
    }

   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mapaSucursales = new org.jxmapviewer.JXMapViewer();
        cmbMapa = new javax.swing.JComboBox<>();
        lblUbicacion = new javax.swing.JLabel();

        javax.swing.GroupLayout mapaSucursalesLayout = new javax.swing.GroupLayout(mapaSucursales);
        mapaSucursales.setLayout(mapaSucursalesLayout);
        mapaSucursalesLayout.setHorizontalGroup(
            mapaSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );
        mapaSucursalesLayout.setVerticalGroup(
            mapaSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );

        cmbMapa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open Stree", "Virtual Earth", "Hybrid", "Satellite" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mapaSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(lblUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(mapaSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(lblUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> cmbMapa;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lblUbicacion;
    public org.jxmapviewer.JXMapViewer mapaSucursales;
    // End of variables declaration//GEN-END:variables
}
