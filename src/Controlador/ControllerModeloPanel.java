/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlModelo;
import Modelo.mdlModeloPanel;
import Vista.FrmModelo;
import Vista.ModeloPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gerst
 */
public class ControllerModeloPanel implements MouseListener{
    
    private mdlModeloPanel modelo;
    private ModeloPanel vista;
    
    public ControllerModeloPanel(mdlModeloPanel modelo, ModeloPanel vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnAgregar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        //vista.btnCerrar.addMouseListener(this);
        vista.jtbModelo.addMouseListener(this);
        vista.cbMarca.addMouseListener(this);
        
        modelo.MostrarModelo(vista.jtbModelo);
        modelo.CargarComboMarca("Marca", "NomMarca", vista.cbMarca);
        
        vista.cbMarca.addActionListener(e -> {
        // Verifica que la fuente del evento sea el JComboBox
        if (e.getSource() == vista.cbMarca) {
            // Obtén el elemento seleccionado y asegúrate de que no sea nulo
            mdlModeloPanel selectedItem = (mdlModeloPanel) vista.cbMarca.getSelectedItem();
           
            System.out.println(selectedItem);
            
            if(selectedItem.equals("Seleccionar Marca")){
                JOptionPane.showMessageDialog(vista, "Debes seleccionar una opcion valida", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                if (selectedItem != null) {
                    int id = selectedItem.getIdMarca();
                    modelo.setIdMarca(id);
                    System.out.println(id);
                }
            }
            
        }
        });
    }

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        /*if (e.getSource() == vista.cbMarca) 
        {
            
            mdlModeloPanel SelectedItem = (mdlModeloPanel)vista.cbMarca.getSelectedItem();
            if(SelectedItem != null)
            {
                int id = SelectedItem.getIdMarca();
                modelo.setIdMarca(id);
                System.out.println(id);
            }
        }*/
        if (e.getSource() == vista.btnAgregar) {
            if (vista.txtModelo.getText().isEmpty() || vista.cbMarca.getSelectedIndex() == 0 || vista.txtAño.getText().isEmpty()) 
            {

                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modelo.setModelo(vista.txtModelo.getText());
                    modelo.getIdMarca();
                    
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
            int filaSeleccionada = vista.jtbModelo.getSelectedRow();
            if (vista.txtModelo.getText().isEmpty() || vista.cbMarca.getSelectedIndex() == 0 || vista.txtAño.getText().isEmpty() || filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setModelo(vista.txtModelo.getText());
                    modelo.getIdMarca();
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
        if (e.getSource() == vista.jtbModelo) {
            modelo.cargarDatosTabla(vista);
        }
        /*if (e.getSource() == vista.btnCerrar) {
            vista.removeAll();
        }*/
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
