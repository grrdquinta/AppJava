
package Controlador;

import Modelo.mdlPaquetes;
import Vista.panelActualizarPaquete;


public class ControllerInfoPaquete {
    
    private panelActualizarPaquete vista;
    private mdlPaquetes modelo;
    
    public ControllerInfoPaquete(panelActualizarPaquete vista, mdlPaquetes modelo){
        this.vista = vista;
        this.modelo = modelo;
    }
    
     

     public void llenarCampos() {
        vista.txtPeso.setText(String.valueOf(modelo.getPeso()));
        vista.txtAlto.setText(String.valueOf(modelo.getAlto()));
        vista.txtLargo.setText(String.valueOf(modelo.getLargo()));
        vista.txtFecha.setText(modelo.getFechaInicio());
        vista.txtOrigen.setText(String.valueOf(modelo.getDireccion()));
        vista.txtDistrito.setText(String.valueOf(modelo.getDistrito()));
        vista.txtCliente.setText(String.valueOf(modelo.getCliente()));
        vista.txtAncho.setText(String.valueOf(modelo.getAncho()));
    }

}
