/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlVehiculo;
import Vista.FlotaPanel;
import Vista.FrmMarca;
import Vista.FrmModelo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author gerst
 */
public class ControllerVehiculo implements MouseListener{
    
    private mdlVehiculo modelo;
    private FlotaPanel vista;
    
    public ControllerVehiculo(mdlVehiculo modelo, FlotaPanel vista)
    {
        this.modelo = modelo;
        this.vista = vista; 
        
        vista.btnAgregarMarca.addMouseListener(this);
        vista.btnAgregarModelo.addMouseListener(this);
    
        modelo.Mostrar(vista.jtbVehiculo);
        modelo.CargarComboMarca("Marca", "NomMarca", vista.cbMarca);
        
        
        vista.cbMarca.addActionListener(e -> {
        // Verifica que la fuente del evento sea el JComboBox
        if (e.getSource() == vista.cbMarca) {
            // Obtén el elemento seleccionado y asegúrate de que no sea nulo
            mdlVehiculo selectedItem = (mdlVehiculo) vista.cbMarca.getSelectedItem();
            if (selectedItem != null) {
                int id = selectedItem.getIdMarca();
                modelo.setIdMarca(id);
                System.out.println(id);
               modelo.CargarComboModelo("Modelo", "Modelo",vista.cbModelo);
            }
             
        }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() ==  vista.btnAgregarMarca)
        {
            FrmMarca fm = new FrmMarca();
            fm.setVisible(true);
            fm.setLocationRelativeTo(fm);
        }
        if(e.getSource() ==  vista.btnAgregarModelo)
        {
            FrmModelo fm = new FrmModelo();
            fm.setVisible(true);
            fm.setLocationRelativeTo(fm);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
