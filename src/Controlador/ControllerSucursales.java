package Controlador;

import Modelo.EventLocationSelected;
import Modelo.mdlSucursales;
import Vista.FrmSucursales;
import Vista.Sucursal;
import Vista.jpCards;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
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
        cargarComboBox();

        
       modelo.setEvent(new EventLocationSelected() {
            @Override
            public void onSelected(String location) {
                vista.lblUbicacion.setText("Ubicación: " + location);
            }
        });
        
        MouseInputListener mm = new PanMouseInputListener(vista.mapaSucursales);
        vista.mapaSucursales.addMouseListener(mm);
        vista.mapaSucursales.addMouseMotionListener(mm);
        vista.mapaSucursales.addMouseWheelListener(new ZoomMouseWheelListenerCenter(vista.mapaSucursales));

        vista.mapaSucursales.addMouseListener(this);
        vista.btnIngresar.addMouseListener(this);
        vista.btnver.addMouseListener(this);
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
    
     private void cargarComboBox() {
        String[] options = {"Almacen", "Centro Recolección"};
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(options);
        vista.cmbAlmacenamiento.setModel(model);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
     
        if (e.getSource() == vista.mapaSucursales) {
            GeoPosition pos = vista.mapaSucursales.convertPointToGeoPosition(e.getPoint());

        System.out.println("Latitud: " + pos.getLatitude() + ", Longitud: " + pos.getLongitude());
        
        vista.txtLatitud.setText(String.valueOf(pos.getLatitude()));
        vista.txtLongitud.setText(String.valueOf(pos.getLongitude()));
        
        
        modelo.showLocation(pos);
        }
              
        
        
        if (e.getSource() == vista.btnIngresar) {
      
            try {
                int tipoSucursal = vista.cmbAlmacenamiento.getSelectedIndex() == 0 ? 1 : 0;
                double longitud = Double.parseDouble(vista.txtLongitud.getText());
                double latitud = Double.parseDouble(vista.txtLatitud.getText());
                String nombre = vista.txtNombreSucursal.getText();


                modelo.setNombre(nombre);
                modelo.setLongitud(longitud);
                modelo.setLatitud(latitud);
                modelo.setAlmacenamiento(tipoSucursal);
                modelo.GuardarSucursal();
                
                modelo.limpiarCampos(); 
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource() == vista.btnver) {
            // Create a dialog window
            JDialog dialog = new JDialog((JFrame) null, "Sucursales", true); // 'true' makes it modal

            // Retrieve the list of sucursales from the database
            Sucursal sucursal = new Sucursal();
            List<String> nombre = sucursal.obtenerDatos(); // Get the list of names

            if (nombre == null || nombre.isEmpty()) {
                System.out.println("No se pudieron cargar los nombres de las sucursales.");
                return; // Exit if no data is retrieved
            } else {
                System.out.println("Lista de nombres cargada correctamente: " + nombre);
            }

            // Create the jpCards panel
            jpCards card = new jpCards(nombre);

            // Set the panel in the dialog
            dialog.setLayout(new BorderLayout());
            dialog.add(new JScrollPane(card), BorderLayout.CENTER);

            // Set dialog size and make it visible
            dialog.setSize(600, 400);
            dialog.setLocationRelativeTo(null); // Center the dialog
            dialog.setVisible(true);
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
