/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author gerst
 */
public class mdlDashboard {
    
     public void CerrarSesion() {
        
        SessionVar.setDui("");
        SessionVar.setNombre("");
        SessionVar.setApellidoPa("");
        SessionVar.setApellidoMa("");
        SessionVar.setMail("");
        SessionVar.setSalario(0.0);
        SessionVar.setFechaNa("");
        SessionVar.setIdRol(0);
        SessionVar.setIdSucursal(0);
        SessionVar.setMasculino(2);
        SessionVar.setEstado(2);
        SessionVar.setRol("");
        SessionVar.setSucursal("");
    }
    
}
