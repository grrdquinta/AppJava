
package Modelo;

import static Modelo.SessionVar.getNombre;
import Vista.FrmSucursales;
import com.github.kevinsawicki.http.HttpRequest;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import java.sql.PreparedStatement;
import java.sql.Connection;




public class mdlSucursales extends JXMapViewer{
   
    private final Set<String> acceptedLocations = new HashSet<>();
    private FrmSucursales vista;
    
     public mdlSucursales(FrmSucursales vista) {
        this.vista = vista;
    }
    
    public EventLocationSelected getEvent() {
        return event;
    }

    public void setEvent(EventLocationSelected event) {
        this.event = event;
    }

    private EventLocationSelected event;

    public mdlSucursales() {
        
        
        acceptedLocations.add("San Salvador, El Salvador");
        acceptedLocations.add("13.692940, -89.218191"); 
    }

    
    public void init(FrmSucursales vista){
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        vista.mapaSucursales.setTileFactory(tileFactory);
        GeoPosition geo = new GeoPosition(13.692940, -89.218191);
        vista.mapaSucursales.setAddressLocation(geo);
        vista.mapaSucursales.setZoom(10);
    }
    
    public void showLocation(GeoPosition pos) {
        new Thread(() -> {
            try {
                String location = getLocation(pos);
                if (event != null) {
                    event.onSelected(location);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
     private boolean isLocationAccepted(String location, GeoPosition pos) {
        return acceptedLocations.contains(location) || 
               acceptedLocations.contains(pos.getLatitude() + ", " + pos.getLongitude());
    }

   public String getLocation(GeoPosition pos) throws JSONException {
        String body = HttpRequest.get("https://nominatim.openstreetmap.org/reverse?lat=" + pos.getLatitude() + "&lon=" + pos.getLongitude() + "&format=json").body();
        JSONObject json = new JSONObject(body);

        if (json.has("display_name")) {
            return json.getString("display_name");
        } else {
            JOptionPane.showMessageDialog(this, "Ubicación desconocida", "Alert", JOptionPane.WARNING_MESSAGE);
            return "Ubicacion desconocida";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = getWidth() / 2 - 12;
        int y = getHeight() / 2 - 24;
        Area area = new Area(new Rectangle.Double(0, 0, getWidth(), getHeight()));
        area.subtract(new Area(new RoundRectangle2D.Double(5, 5, getWidth() - 10, getHeight() - 10, 20, 20)));
        g2.setColor(new Color(255, 255, 255));
        g2.fill(area);
        g2.dispose();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setAlmacenamiento(int almacenamiento) {
        this.almacenamiento = almacenamiento;
    }
    
    public String getNombre() {
        return nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }
    
    private String nombre;
    private double latitud;
    private double longitud;
    private int almacenamiento;
    
    
    public void GuardarSucursal() {
        Connection conexion = ClaseConexion.getConexion();
        try {
            String sql = "INSERT INTO Sucursal (Nombre, Ubicacion, TipoSucursal) VALUES (?, " +
                         "SDO_GEOMETRY(2001, 4326, SDO_POINT_TYPE(?, ?, NULL), NULL, NULL), ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, getNombre());       
            pstmt.setDouble(2, getLongitud());     
            pstmt.setDouble(3, getLatitud());      
            pstmt.setInt(4, getAlmacenamiento());  

            pstmt.executeUpdate();  
            JOptionPane.showMessageDialog(this, "Sucursal agregada correctamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);

            
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing the connection - " + e);
            }
        }
    }
    
     public void limpiarCampos() {
        if (vista != null) {
            vista.txtNombreSucursal.setText("");
            vista.txtLatitud.setText("");        
            vista.txtLongitud.setText("");       
            vista.cmbAlmacenamiento.setSelectedIndex(0); 
            vista.lblUbicacion.setText("");
        } else {
            System.out.println("Vista is not initialized.");
        }
    }
    
}


