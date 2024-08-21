/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlModelo;
import Vista.FrmModelo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gerst
 */
public class ControllerAddModelo implements MouseListener{
    
    private mdlModelo modelo;
    private FrmModelo vista;
    
    public ControllerAddModelo(mdlModelo modelo, FrmModelo vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnAgregar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        
        modelo.MostrarModelo(vista.jtbModelo);
        modelo.CargarComboMarca("Marca", "NomMarca", vista.cbMarca);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            if (vista.txtModelo.getText().isEmpty() || vista.cbMarca.getSelectedIndex() == 0 || vista.txtAño.getText().isEmpty()) 
            {

                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modelo.setModelo(vista.txtModelo.getText());
                    modelo.setIdMarca(vista.cbMarca.getSelectedIndex());
                    modelo.setAño(vista.txtAño.getText());
                    modelo.setCarga(vista.cbCarga.getSelectedIndex());
                    
                    //Ejecutar el metodo 
                    modelo.GuardarModelo();
                    modelo.MostrarModelo(vista.jtbModelo);
                    //modelo.limpiar(vista);
                                        
                     JOptionPane.showMessageDialog(vista, "Los datos han sido registrados exitosamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                   
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "No se pudo completar el registro.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtModelo.getText().isEmpty() || vista.cbMarca.getSelectedIndex() == 0 || vista.txtAño.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setModelo(vista.txtModelo.getText());
                    modelo.setIdMarca(vista.cbMarca.getSelectedIndex());
                    modelo.setAño(vista.txtAño.getText());
                    modelo.setCarga(vista.cbCarga.getSelectedIndex());

                    //Ejecutar el método    
                    modelo.Actualizar(vista.jtbModelo);
                    modelo.MostrarModelo(vista.jtbModelo);
                    //modelo.limpiar(vista);
                    JOptionPane.showMessageDialog(vista, "Los datos han sido actualizados exitosamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "No se pudieron actualizar los datos", "Error", JOptionPane.WARNING_MESSAGE);
                }
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
