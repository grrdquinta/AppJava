
package Modelo;

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
import org.json.JSONException;
import org.json.JSONObject;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;


public class mdlSucursales extends JXMapViewer{
   
    public EventLocationSelected getEvent() {
        return event;
    }

    public void setEvent(EventLocationSelected event) {
        this.event = event;
    }

    private final Image image;
    private EventLocationSelected event;

    public mdlSucursales() {
        image = new ImageIcon(getClass().getResource("/Vista/pin.png")).getImage();
    }

    
    public void init(FrmSucursales vista){
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        vista.mapaSucursales.setTileFactory(tileFactory);
        GeoPosition geo = new GeoPosition(13.692940, -89.218191);
        vista.mapaSucursales.setAddressLocation(geo);
        vista.mapaSucursales.setZoom(12);
    }
    
     // Este método debe llamarse cuando se selecciona una ubicación en el mapa
    public void showLocation(GeoPosition pos) {
        new Thread(() -> {
            try {
                // Llama al evento cuando se selecciona la ubicación
                String location = getLocation(pos);
                if (event != null) {
                    event.onSelected(location);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public String getLocation(GeoPosition pos) throws JSONException {
        String body = HttpRequest.get("https://nominatim.openstreetmap.org/reverse?lat=" + pos.getLatitude() + "&lon=" + pos.getLongitude() + "&format=json").body();
        JSONObject json = new JSONObject(body);
        return json.getString("display_name");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = getWidth() / 2 - 12;
        int y = getHeight() / 2 - 24;
        g2.drawImage(image, x, y, null);
        Area area = new Area(new Rectangle.Double(0, 0, getWidth(), getHeight()));
        area.subtract(new Area(new RoundRectangle2D.Double(5, 5, getWidth() - 10, getHeight() - 10, 20, 20)));
        g2.setColor(new Color(255, 255, 255));
        g2.fill(area);
        g2.dispose();
    }
}

