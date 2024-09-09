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
        
        vista.btnSeleccionar.addMouseListener(this);
        /*vista.btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarImagenYGuardar();
            }
        });*/
        modelo.CargarInformacion(vista);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() ==  vista.btnSeleccionar)
        {
            try {
            // Selecciona y guarda la imagen
            modelo.seleccionarImagenYGuardar();

            // Carga la imagen desde la base de datos
            File imgFile = modelo.cargarImagenExistente();
            if (imgFile != null) {
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(imgFile));
                vista.imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                System.out.println("Imagen cargada y establecida en JLabel.");
            } else {
                vista.imageLabel.setIcon(null);
                System.out.println("No se pudo cargar la imagen.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "No se pudo completar la operación.", "Error", JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
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
