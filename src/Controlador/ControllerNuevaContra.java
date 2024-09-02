package Controlador;

import Modelo.Encriptacion;
import Modelo.mdlEmpleado;
import Vista.FrmNuevaContraseña;
import Vista.Login;
import Vista.frmOlvideContraseña;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ControllerNuevaContra implements MouseListener{
    
    private mdlEmpleado modelo;
    private FrmNuevaContraseña vista;
    private ControllerIngresoCorre controllerIngresoCorre;
    private String email;
    
    public ControllerNuevaContra(mdlEmpleado modelo, FrmNuevaContraseña vista, ControllerIngresoCorre controllerIngresoCorre){
        this.modelo = modelo;
        this.vista = vista;
        this.email = controllerIngresoCorre.email;

        
        vista.btnActualizar.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnActualizar) {
            System.out.println("Correo electrónico: " + email);

            String nuevaContrasena1 = vista.txtContraNueva1.getText();
            String nuevaContrasena2 = vista.txtContraNueva2.getText();
            
            
            if (nuevaContrasena1.equals(nuevaContrasena2)) {
                String dui = modelo.obtenerDuiPorEmail(email);
                
                if (dui != null) {
                    Encriptacion en = new Encriptacion();
                    String contraEnrip = en.convertirSHA256(nuevaContrasena1);
                    modelo.setContraseña(contraEnrip);

                    boolean resultado = modelo.actualizarContrasena(dui, contraEnrip);
                    
                    if (resultado) {
                        JOptionPane.showMessageDialog(vista, "Contraseña actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        
                        vista.dispose();
                        Login login = new Login();
                        login.setVisible(true); 
                        
                    } else {
                        JOptionPane.showMessageDialog(vista, "No se pudo actualizar la contraseña. Por favor, inténtelo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "No se encontró un usuario con el email proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
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
