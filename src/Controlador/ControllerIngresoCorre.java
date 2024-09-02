package Controlador;

import Modelo.EnviarCorreo;
import Modelo.mdlEmpleado;
import Vista.FrmIngresarCodigo;
import Vista.frmOlvideContraseña;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JOptionPane;

public class ControllerIngresoCorre implements MouseListener {

    private mdlEmpleado modelo;
    private frmOlvideContraseña vista;
    private int numeroAleatorio;
    private String email;

    public ControllerIngresoCorre(mdlEmpleado modelo, frmOlvideContraseña vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.btnEnviarCodigo.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == vista.btnEnviarCodigo) {

            email = vista.txtCorreoOlvide.getText();

            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(vista, "Por favor, ingresa un correo electrónico válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!modelo.verificarCorreo(email)) {
                JOptionPane.showMessageDialog(vista, "El correo electrónico ingresado no está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                Random random = new Random();
                numeroAleatorio = 1000 + random.nextInt(9000);
                String recipient = email;
                String subject = "Recuperación de contraseña";
                String content = "Este es el código de recuperación: " + numeroAleatorio;

                EnviarCorreo.enviarCorreo(recipient, subject, content);

                JOptionPane.showMessageDialog(vista, "El correo se envió correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                vista.dispose();
                
                FrmIngresarCodigo ingresar = new FrmIngresarCodigo(this);
                ingresar.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "No se pudo enviar el correo. Por favor, intenta nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public int getNumeroAleatorio() {
        return numeroAleatorio;
    }
    
    public String getEmail() {
        return email;
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
