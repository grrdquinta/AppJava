/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.SessionVar;
import Modelo.mdlDashboard;
import Vista.Dashboard;
import Vista.DashboardPanel;
import Vista.EmpleadosPanel;
import Vista.FlotaPanel;
import Vista.Login;
import Vista.PaquetesPanel;
import Vista.ProfilePanel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author gerst
 */
public class ControllerDashboard implements MouseListener{

    private Dashboard vista;
    private mdlDashboard modelo;
    
    public ControllerDashboard(mdlDashboard modelo, Dashboard vista)
    {
        this.vista = vista;
        this.modelo = modelo;
        
        vista.btnEmpleados.addMouseListener(this);
        vista.btnDashboard.addMouseListener(this);
        vista.btnFlota.addMouseListener(this);
        vista.btnPaquetes.addMouseListener(this);
        vista.btnProfile.addMouseListener(this);
        vista.btnCerrarSesion.addMouseListener(this);
        
        vista.lbNombre.setText(SessionVar.getNombre());
        vista.lbRol.setText(SessionVar.getRol());
        vista.lbSucursal.setText("Sucursal: " + SessionVar.getSucursal());
        //modelo.cargarImagenExistente(vista);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() ==  vista.btnEmpleados)
        {
            EmpleadosPanel pp = new EmpleadosPanel();
        
            vista.content.removeAll();
            vista.content.add(pp);
            vista.content.revalidate();
            vista.content.repaint();
        }
        if(e.getSource() ==  vista.btnFlota)
        {
            FlotaPanel fp = new FlotaPanel();

            vista.content.removeAll();
            vista.content.add(fp);
            vista.content.revalidate();
            vista.content.repaint();
        }
        if(e.getSource() ==  vista.btnPaquetes)
        {
           PaquetesPanel pp = new PaquetesPanel();
            

            vista.content.removeAll();
            vista.content.add(pp);
            vista.content.revalidate();
            vista.content.repaint();
        }
        if(e.getSource() ==  vista.btnDashboard)
        {
            DashboardPanel dsp = new DashboardPanel();

            vista.content.removeAll();
            vista.content.add(dsp);
            vista.content.revalidate();
            vista.content.repaint();
        }
        if(e.getSource() ==  vista.btnProfile)
        {
            ProfilePanel pp = new ProfilePanel();

            vista.content.removeAll();
            vista.content.add(pp);
            vista.content.revalidate();
            vista.content.repaint();
        }
        if(e.getSource() ==  vista.btnCerrarSesion)
        {            
            vista.dispose();
            modelo.CerrarSesion();
            Login log = new Login();
            log.setVisible(true);
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
