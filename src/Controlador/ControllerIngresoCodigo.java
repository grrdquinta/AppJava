
package Controlador;

import Modelo.mdlEmpleado;
import Vista.FrmIngresarCodigo;
import Vista.FrmNuevaContraseña;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ControllerIngresoCodigo implements MouseListener{

    private FrmIngresarCodigo vista;
    private ControllerIngresoCorre controllerIngresoCorre;
    
    public ControllerIngresoCodigo(FrmIngresarCodigo vista, ControllerIngresoCorre controllerIngresoCorre){
        this.vista = vista;
        this.controllerIngresoCorre = controllerIngresoCorre;   
        vista.btnValidarCodigo.addMouseListener(this);
        
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnValidarCodigo){
            int codigoIngresado = Integer.parseInt(vista.txtCodigo.getText());
            int codigoGenerado = controllerIngresoCorre.getNumeroAleatorio();
            
            if(codigoIngresado == codigoGenerado){
                JOptionPane.showMessageDialog(vista, "Código válido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                vista.dispose();
                FrmNuevaContraseña nueva = new FrmNuevaContraseña(controllerIngresoCorre);
                nueva.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(vista, "Código incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
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
