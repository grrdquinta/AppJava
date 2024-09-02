/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.mdlPaquetes;
import Vista.PaquetesPanel;
import Vista.TableActionCellRender;

/**
 *
 * @author gerst
 */
public class ControllerPaquete {
    
    private PaquetesPanel vista;
    private mdlPaquetes modelo;
    
    
    public ControllerPaquete(mdlPaquetes modelo, PaquetesPanel vista)
    {
    
        this.vista = vista;
        this.modelo = modelo;
        
        modelo.Mostrar(vista.jtbPaquetes);
        vista.jtbPaquetes.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRender());
        
    }
}
