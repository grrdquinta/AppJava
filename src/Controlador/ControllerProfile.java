/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlPerfil;
import Vista.ProfilePanel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author gerst
 */
public class ControllerProfile implements MouseListener{
    
    private ProfilePanel vista;
    private mdlPerfil modelo;
    
    public ControllerProfile(mdlPerfil modelo ,ProfilePanel vista)
    {
    
        this.modelo = modelo;
        this.vista = vista;
        
        vista.txtDui.setEditable(false);
        vista.txtNombre.setEditable(false);
        vista.txtApellidoPa.setEditable(false);
        vista.txtMail.setEditable(false);
        vista.txtFechaNa.setEditable(false);
        vista.txtTelefono.setEditable(false);
        vista.txtRol.setEditable(false);
        vista.txtSucursal.setEditable(false);
        vista.txtCambiarCont.setEditable(false);
        vista.txtConfirmCont.setEditable(false);
        vista.txtContActual.setEditable(false);
        vista.btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.seleccionarImagenYGuardar(vista);
            }
        });
        vista.btnActivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vista.btnActivar.getText().equals("Activar"))
            {
                 vista.txtCambiarCont.setEditable(true);
                 vista.txtConfirmCont.setEditable(true);
                 vista.txtContActual.setEditable(true);
                 vista.btnActivar.setText("Desactivar");
            }
                else if(vista.btnActivar.getText().equals("Desactivar"))
            {
                 vista.txtCambiarCont.setEditable(false);
                 vista.txtConfirmCont.setEditable(false);
                 vista.txtContActual.setEditable(false);
                 vista.btnActivar.setText("Activar");
            }
            }
        });
        modelo.SeleccionarImagen(vista);
        modelo.CargarInformacion(vista);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
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
