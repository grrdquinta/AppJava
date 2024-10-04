/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlVehiculo;
import Vista.FlotaPanel;
import Vista.FrmMarca;
import Vista.FrmModelo;
import Vista.Gmap;
import Vista.map;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gerst
 */
public class ControllerVehiculo implements MouseListener{
    
    private mdlVehiculo modelo;
    private FlotaPanel vista;
    
    public ControllerVehiculo(mdlVehiculo modelo, FlotaPanel vista)
    {
        this.modelo = modelo;
        this.vista = vista; 
        
        vista.btnAgregarMarca.addMouseListener(this);
        vista.btnAgregarModelo.addMouseListener(this);
        vista.btnMapa.addMouseListener(this);
        vista.btnGuardar.addMouseListener(this);
        vista.jtbVehiculo.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
    
        //modelo.Mostrar(vista.jtbVehiculo);
        modelo.CargarComboMarca("Marca", "NomMarca", vista.cbMarca);
        modelo.CargarComboSucursal("Sucursal", "Nombre", vista.cbSucursal);
        
        vista.txtAño.setEditable(false);
        vista.txtCarga.setEditable(false);
        vista.lblEstado.setVisible(false);
        vista.cbEstado.setVisible(false);
        
        vista.cbMarca.addActionListener(e -> {
        // Verifica que la fuente del evento sea el JComboBox
        if (e.getSource() == vista.cbMarca) {
            // Obtén el elemento seleccionado y asegúrate de que no sea nulo
            mdlVehiculo selectedItem = (mdlVehiculo) vista.cbMarca.getSelectedItem();
            if(selectedItem.equals("Seleccionar Marca"))
            {JOptionPane.showMessageDialog(vista, "Debes seleccionar una opcion valida", "Error", JOptionPane.ERROR_MESSAGE);}
            else{
                if (selectedItem != null) {
                    int id = selectedItem.getIdMarca();
                    modelo.setIdMarca(id);
                    System.out.println(id);
                   modelo.CargarComboModelo("Modelo", "Modelo",vista.cbModelo);
                }
            } 
        }
        });
        
        vista.cbModelo.addActionListener(e -> {
        // Verifica que la fuente del evento sea el JComboBox
        if (e.getSource() == vista.cbModelo) {
            // Obtén el elemento seleccionado y asegúrate de que no sea nulo
          
            mdlVehiculo selectedItem = (mdlVehiculo) vista.cbModelo.getSelectedItem();
            if (selectedItem != null) { 
                int id = selectedItem.getIdModelo();
                modelo.setIdModelo(id);
                System.out.println(id);
                modelo.CargarInfoCombo(vista);
            }
             
        }
        });
        
        vista.cbSucursal.addActionListener(e -> {
        // Verifica que la fuente del evento sea el JComboBox
        if (e.getSource() == vista.cbSucursal) {
            // Obtén el elemento seleccionado y asegúrate de que no sea nulo
          
            mdlVehiculo selectedItem = (mdlVehiculo) vista.cbSucursal.getSelectedItem();
            if (selectedItem != null) { 
                int id = selectedItem.getIdSucursal();
                modelo.setIdSucursal(id);
                System.out.println(id);
                //modelo.CargarComboSucursal(vista);
            }
             
        }
        });
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() ==  vista.btnAgregarMarca)
        {
            FrmMarca fm = new FrmMarca();
            fm.setVisible(true);
            fm.setLocationRelativeTo(fm);
        }
        if(e.getSource() ==  vista.btnAgregarModelo)
        {
            FrmModelo fm = new FrmModelo();
            fm.setVisible(true);
            fm.setLocationRelativeTo(fm);
        }
        if(e.getSource() ==  vista.btnMapa)
        {
            Gmap mapa = new Gmap();
        new map().setVisible(true);
        }
        if (e.getSource() == vista.btnGuardar) {
            if (vista.txtPlaca.getText().isEmpty() || vista.txtAño.getText().isEmpty() || vista.txtCarga.getText().isEmpty() || vista.cbSucursal.getSelectedItem().equals("Seleccionar Sucursal")) {

                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos o seleccionar una opcion valida", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modelo.setPlaca(vista.txtPlaca.getText());
                    modelo.getIdModelo();
                    //modelo.setIdSucursal(vista.cbSucursal.getSelectedItem());
                    modelo.getIdSucursal();
                    
                    //Ejecutar el metodo 
                    modelo.GuardarVehiculo();
                    modelo.Mostrar(vista.jtbVehiculo);
                    modelo.Limpiar(vista);
                                        
                     JOptionPane.showMessageDialog(vista, "Los datos han sido registrados exitosamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                   
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "No se pudo completar el registro.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
         if (e.getSource() == vista.btnActualizar) {
            if (vista.txtPlaca.getText().isEmpty() || vista.txtAño.getText().isEmpty()|| vista.txtCarga.getText().isEmpty() 
                    || vista.cbSucursal.getSelectedItem().equals("Seleccionar Sucursal") || vista.cbMarca.getSelectedItem().equals("Seleccionar Marca") || vista.cbModelo.getSelectedItem().equals("Seleccionar Modelo")) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setPlaca(vista.txtPlaca.getText());
                    modelo.getIdModelo();
                    modelo.setIdSucursal(vista.cbSucursal.getSelectedIndex());
                    modelo.setIdEstado(vista.cbEstado.getSelectedIndex());
                    
                    //Ejecutar el método    
                    modelo.Actualizar(vista.jtbVehiculo);
                    modelo.Mostrar(vista.jtbVehiculo);
                    modelo.Limpiar(vista);
                    JOptionPane.showMessageDialog(vista, "Los datos han sido actualizados exitosamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "No se pudieron actualizar los datos", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }        
        if (e.getSource() == vista.jtbVehiculo) {
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
