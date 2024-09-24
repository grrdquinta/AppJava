/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Modelo.SessionVar;
import Modelo.TabbedForm;
import Modelo.WindowsTabbed;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author gerst
 */
public class InicioPanel extends TabbedForm {

    /**
     * Creates new form InicioPanel
     */
    public InicioPanel() {
        initComponents();
        
        //rsscalelabel.RSScaleLabel.setScaleLabel(lbImage, "src/Vista/logo.png");
        jLabel1.setText("Bienvenido " + SessionVar.getNombre());
        
        DarkLightSwitchIcon customIcon = new DarkLightSwitchIcon();
        jToggleButton1.setIcon(customIcon);
        jToggleButton1.setSelectedIcon(customIcon);

        jToggleButton1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        jToggleButton1.addActionListener(new ActionListener() {

            private final ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
            private ScheduledFuture<?> scheduledFuture;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
                scheduledFuture = scheduled.schedule(() -> {
                    changeThemes(jToggleButton1.isSelected());
                }, 500, TimeUnit.MILLISECONDS);
            }
        });
    }
    
    private void changeThemes(boolean dark){
        if(FlatLaf.isLafDark() != dark)
        {
           if(!dark)
           {
               EventQueue.invokeLater(() ->{
                   FlatAnimatedLafChange.showSnapshot();
                   FlatMacLightLaf.setup();
                   FlatLaf.updateUI();
                   FlatAnimatedLafChange.hideSnapshotWithAnimation();
               }); 
           }
           else
           {
               EventQueue.invokeLater(() ->{
                   FlatAnimatedLafChange.showSnapshot();
                   FlatMacDarkLaf.setup();
                   FlatLaf.updateUI();
                   FlatAnimatedLafChange.hideSnapshotWithAnimation();
               }); 
           }
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

        jToggleButton1 = new javax.swing.JToggleButton();
        lbImage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/logo.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(365, 365, 365)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(412, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(159, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jToggleButton1)
                .addGap(78, 78, 78))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lbImage;
    // End of variables declaration//GEN-END:variables
}
