/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Encriptacion;
import Modelo.mdlEmpleado;
import Vista.EmpleadosPanel;
import Vista.InformacionEmpleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author gerst
 */
public class ControllerAddEmpleado implements MouseListener{

    private mdlEmpleado modelo;
    private InformacionEmpleados vista;
    private EmpleadosPanel vista2;
    
    public ControllerAddEmpleado (mdlEmpleado modelo, InformacionEmpleados vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnGuardar.setVisible(false);
        vista.btnActualizar.setVisible(false);
        vista.cbEstado.setVisible(false);
        vista.lbEstado.setVisible(false);
        
        vista.btnCerrar.addMouseListener(this);
        vista.cbRol.addMouseListener(this);
        vista.btnGuardar.addMouseListener(this);
        
        
        modelo.CargarComboRol("Rol", "Nomrol", vista.cbRol);
        modelo.CargarComboSucursal("Sucursal", "Nombre", vista.cbSucursal);
        
        
        
        
        vista.cbRol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si se ha seleccionado un elemento que no sea el predeterminado
                if (!vista.cbRol.getSelectedItem().equals("Repartidor")) {
                    vista.txtLicencia.setEnabled(false);
                } else {
                    vista.txtLicencia.setEnabled(true); 
                }
            }
        });
        
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnCerrar)
        {
            vista.dispose();
            //modelo.Mostrar(tabla);
        }
        
        if (e.getSource() == vista.btnGuardar) {
            if(vista.cbRol.getSelectedIndex() != 3){
                if (vista.txtDUI.getText().isEmpty() ||vista.txtNombre.getText().isEmpty() || vista.txtApellidoPa.getText().isEmpty() 
                        || vista.txtApellidoMa.getText().isEmpty() || vista.jdcFecha.equals("") || vista.txtTelefono.getText().isEmpty()
                        || vista.txtEmail.getText().isEmpty() || vista.txtSalario.getText().isEmpty() || vista.txtUsuario.getText().isEmpty()
                        || vista.txtContrasena.getText().isEmpty() || vista.cbEstado.getSelectedIndex() == 0 || 
                        vista.cbSucursal.getSelectedIndex() == 0) {

                    JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos o seleccionar opcion valida", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        //Asignar lo de la vista al modelo
                        modelo.setDUI(vista.txtDUI.getText());
                        modelo.setNombre(vista.txtNombre.getText());
                        modelo.setApellidoPa(vista.txtApellidoPa.getText());
                        modelo.setApellidoMa(vista.txtApellidoMa.getText());
                        modelo.setEmail(vista.txtEmail.getText());
                        modelo.setSalario(Double.parseDouble(vista.txtSalario.getText()));
                        modelo.setFechaNa(modelo.fecha.format(vista.jdcFecha.getDate()).toString());
                        modelo.setIdRol((int)vista.cbRol.getSelectedIndex());
                        modelo.setIdSucursal((int)vista.cbSucursal.getSelectedIndex());
                        modelo.setGenero((int)vista.cbSexo.getSelectedIndex());
                        modelo.setTelefono(vista.txtTelefono.getText());
                        modelo.setUsuario(vista.txtUsuario.getText());                        
                        Encriptacion en = new Encriptacion();
                        String contraEnrip = en.convertirSHA256(vista.txtContrasena.getText());
                        modelo.setContraseña(contraEnrip);
                        //Ejecutar el metodo 
                        int valorRetornado = modelo.AgregarEmpleado();

                        if(valorRetornado == 1){
                            JOptionPane.showMessageDialog(vista, "Los datos han sido registrados exitosamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                            modelo.limpiar(vista);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vista, "No se pudo completar el registro.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            else
            {
            
                if (vista.txtDUI.getText().isEmpty() ||vista.txtLicencia.getText().isEmpty() ||vista.txtNombre.getText().isEmpty() || vista.txtApellidoPa.getText().isEmpty() 
                        || vista.txtApellidoMa.getText().isEmpty() || vista.jdcFecha.equals("") || vista.txtTelefono.getText().isEmpty()
                        || vista.txtEmail.getText().isEmpty() || vista.txtSalario.getText().isEmpty() || vista.txtUsuario.getText().isEmpty()
                        || vista.txtContrasena.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        //Asignar lo de la vista al modelo
                        modelo.setLicencia(vista.txtLicencia.getText());
                        modelo.setDUI(vista.txtDUI.getText());
                        modelo.setNombre(vista.txtNombre.getText());
                        modelo.setApellidoPa(vista.txtApellidoPa.getText());
                        modelo.setApellidoMa(vista.txtApellidoMa.getText());
                        modelo.setEmail(vista.txtEmail.getText());
                        modelo.setSalario(Double.parseDouble(vista.txtSalario.getText()));
                        modelo.setFechaNa(modelo.fecha.format(vista.jdcFecha.getDate()).toString());
                        modelo.setIdRol((int)vista.cbRol.getSelectedIndex());
                        modelo.setIdSucursal((int)vista.cbSucursal.getSelectedIndex());
                        modelo.setGenero((int)vista.cbSexo.getSelectedIndex());
                        modelo.setTelefono(vista.txtTelefono.getText());
                        modelo.setUsuario(vista.txtUsuario.getText());
                        Encriptacion en = new Encriptacion();
                        String contraEnrip = en.convertirSHA256(vista.txtContrasena.getText());
                        modelo.setContraseña(contraEnrip);
                        //Ejecutar el metodo 
                        int valorRetornado = modelo.AgregarRepartidor();

                        if(valorRetornado == 1){
                            JOptionPane.showMessageDialog(vista, "Los datos han sido registrados exitosamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                            modelo.limpiar(vista);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vista, "No se pudo completar el registro.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
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
