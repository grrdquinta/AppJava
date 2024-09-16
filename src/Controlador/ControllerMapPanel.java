/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlMapa;
import Modelo.mdlMapaPanel;
import Vista.map;
import Vista.mapPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author gerst
 */
public class ControllerMapPanel  implements MouseListener{
    private mdlMapaPanel modelo;
    private mapPanel vista;
    
    public ControllerMapPanel(mdlMapaPanel modelo,mapPanel vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        
        modelo.initx(vista);
        vista.btnBuscar.addMouseListener(this);
        vista.btnReiniciar.addMouseListener(this);
        vista.btnClose.addMouseListener(this);
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnBuscar)
        {
            modelo.Buscar();
        }
        if (e.getSource() == vista.btnReiniciar)
        {
            modelo.Reiniciar();
        }
        if (e.getSource() == vista.btnClose)
        {
            vista.removeAll();
            vista.repaint();
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
