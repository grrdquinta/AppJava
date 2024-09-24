/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Encriptacion;
import Modelo.SessionVar;
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
        vista.txtUsuario.setEditable(false);
        vista.txtCambiarCont.setEditable(false);
        vista.txtConfirmCont.setEditable(false);
        vista.txtContActual.setEditable(false);
        vista.btnGuardar.addMouseListener(this);
        vista.btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.seleccionarImagenYGuardar(vista);
                JOptionPane.showMessageDialog(vista, "Los cambios se veran reflejados la proxima vez que inicies sesion", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        vista.btnActivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vista.btnActivar.getText().equals("Modificar"))
            {
                 vista.txtUsuario.setEditable(true);
                 vista.txtCambiarCont.setEditable(true);
                 vista.txtConfirmCont.setEditable(true);
                 vista.txtContActual.setEditable(true);
                 vista.btnActivar.setText("Desactivar");
            }
                else if(vista.btnActivar.getText().equals("Desactivar"))
            {
                 vista.txtUsuario.setEditable(false);
                 vista.txtCambiarCont.setEditable(false);
                 vista.txtConfirmCont.setEditable(false);
                 vista.txtContActual.setEditable(false);
                 vista.btnActivar.setText("Modificar");
            }
            }
        });
        modelo.SeleccionarImagen(vista);
        modelo.CargarInformacion(vista);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnGuardar)
        {
            if(vista.btnActivar.getText().equals("Desactivar"))
            {
                if(vista.txtCambiarCont.getText().isEmpty() || vista.txtConfirmCont.getText().isEmpty() || 
                        vista.txtContActual.getText().isEmpty() || vista.txtUsuario.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(vista, "Verificar si no hay campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if(vista.txtCambiarCont.getText().equals(vista.txtConfirmCont.getText()))
                        {
                            Encriptacion en = new Encriptacion();
                            String contraEnrip = en.convertirSHA256(vista.txtContActual.getText());
                            if(SessionVar.getPass().equals(contraEnrip))
                            {
                                Encriptacion enN = new Encriptacion();
                                String contraEnript = enN.convertirSHA256(vista.txtCambiarCont.getText());
                                modelo.setContraseña(contraEnript);
                                modelo.ActualizarPerfil(vista);
                                JOptionPane.showMessageDialog(vista, "Proceso Completado", "Completado", JOptionPane.INFORMATION_MESSAGE);
                                modelo.CargarInformacion(vista);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(vista, "Contraseña Actual Incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    else
                        {
                            JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden, verificar que esten escritas de la misma manera", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                }
            }
            else
            {
            JOptionPane.showMessageDialog(vista, "Por favor habilite la opcion de modificar", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
