/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Encriptacion;
import Modelo.mdlLogin;
import Vista.Dashboard;
import Vista.Login;
import Vista.Main;
import Vista.frmOlvideContraseña;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gerst
 */
public class ControllerLogin implements MouseListener{

    private mdlLogin modelo;
    private Login vista;
    
    public ControllerLogin(mdlLogin modelo, Login vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnIniciarSesion.addMouseListener(this);
        vista.lblOlvidaste.addMouseListener(this);
    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnIniciarSesion)
        {
            if (vista.txtUsuario.getText().isEmpty() ||vista.txtPassword.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            }            
            else
            {
                modelo.setUsuario(vista.txtUsuario.getText());
                Encriptacion en = new Encriptacion();
                String contraEnrip = en.convertirSHA256(vista.txtPassword.getText());
                modelo.setContraseña(contraEnrip);
                boolean answer = modelo.ValidarLogin();
                if(answer == true){
                    JOptionPane.showMessageDialog(vista, "Sesion Iniciada Correctamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                    vista.dispose();
                    //Dashboard dash = new Dashboard();
                    Main dash = new Main();
                    dash.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(vista, "No se encontro ningun usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        if(e.getSource() == vista.lblOlvidaste){
            vista.dispose();
            frmOlvideContraseña olvi = new frmOlvideContraseña();
            olvi.setVisible(true);
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
