package Controlador;

import Modelo.EventLocationSelected;
import Modelo.mdlSucursales;
import Vista.FrmSucursales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

public class ControllerSucursales implements MouseListener {

    private FrmSucursales vista;
    private mdlSucursales modelo;

    public ControllerSucursales(FrmSucursales vista, mdlSucursales modelo) {
        this.modelo = modelo;
        this.vista = vista;

        modelo.init(vista);
        
       modelo.setEvent(new EventLocationSelected() {
            @Override
            public void onSelected(String location) {
                // Aquí puedes manejar la lógica cuando se selecciona una ubicación
                vista.lblUbicacion.setText("Ubicación: " + location);
            }
        });
        
        MouseInputListener mm = new PanMouseInputListener(vista.mapaSucursales);
        vista.mapaSucursales.addMouseListener(mm);
        vista.mapaSucursales.addMouseMotionListener(mm);
        vista.mapaSucursales.addMouseWheelListener(new ZoomMouseWheelListenerCenter(vista.mapaSucursales));

        vista.mapaSucursales.addMouseListener(this);
        
        this.vista.cmbMapa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = vista.cmbMapa.getSelectedIndex();
                cambiarMapa(index);
            }
        });
    }

    public void cambiarMapa(int index) {
        TileFactoryInfo info;

        if (index == 0) {
            info = new OSMTileFactoryInfo();
        } else if (index == 1) {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        } else if (index == 2) {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
        } else {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
        }

        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        vista.mapaSucursales.setTileFactory(tileFactory);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        GeoPosition pos = vista.mapaSucursales.convertPointToGeoPosition(e.getPoint());

        System.out.println("Latitud: " + pos.getLatitude() + ", Longitud: " + pos.getLongitude());
        // Llamar al método del modelo que muestra la ubicación
        modelo.showLocation(pos);
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
