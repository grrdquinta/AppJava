/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Modelo.mdlMarca;
import Vista.FrmMarca;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gerst
 */
public class ControllerAddMarca implements MouseListener {
    
    private mdlMarca modelo;
    private FrmMarca vista;
    
    public ControllerAddMarca(mdlMarca modelo, FrmMarca vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnAgregar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.jtbMarca.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        modelo.MostrarMarca(vista.jtbMarca);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnAgregar) {
            if (vista.txtMarca.getText().isEmpty() ) {

                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modelo.setNomMarca(vista.txtMarca.getText());
                    
                    //Ejecutar el metodo 
                    modelo.GuardarMarca();
                    modelo.MostrarMarca(vista.jtbMarca);
                    modelo.limpiar(vista);
                                        
                     JOptionPane.showMessageDialog(vista, "Los datos han sido registrados exitosamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                   
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "No se pudo completar el registro.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtMarca.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setNomMarca(vista.txtMarca.getText());

                    //Ejecutar el método    
                    modelo.Actualizar(vista.jtbMarca);
                    modelo.MostrarMarca(vista.jtbMarca);
                    modelo.limpiar(vista);
                    JOptionPane.showMessageDialog(vista, "Los datos han sido actualizados exitosamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "No se pudieron actualizar los datos", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtMarca.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.jtbMarca);
                modelo.MostrarMarca(vista.jtbMarca);
                modelo.limpiar(vista);
            }
        }
        
        if (e.getSource() == vista.jtbMarca) {
            modelo.cargarDatosTabla(vista);
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
