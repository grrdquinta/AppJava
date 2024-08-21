/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlEmpleado;
import Vista.EmpleadosPanel;
import Vista.InformacionEmpleados;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author gerst
 */
public class ControllerEmpleados implements MouseListener{
    
    private mdlEmpleado modelo;
    private EmpleadosPanel vista;
    
    public ControllerEmpleados(mdlEmpleado modelo, EmpleadosPanel vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnEmpleado.addMouseListener(this);
        
        modelo.Mostrar(vista.jtbEmpleados);
        
    } 
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //////////////////////////4- Detección de clicks en la vista
        if(e.getSource() ==  vista.btnEmpleado)
        {
            InformacionEmpleados IE = new InformacionEmpleados();
            IE.setVisible(true);
            IE.setLocationRelativeTo(IE);
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


