/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlMapa;
import Vista.map;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author gerst
 */
public class ControllerMap  implements MouseListener{
    private mdlMapa modelo;
    private map vista;
    
    public ControllerMap(mdlMapa modelo,map vista)
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
            vista.dispose();
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
