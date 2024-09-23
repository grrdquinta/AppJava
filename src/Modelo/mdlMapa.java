
package Modelo;

import Vista.map;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

public  class mdlMapa extends WaypointPainter<MyWaypoint>{
   
    private final Set<MyWaypoint> waypoints = new HashSet<>();
    private EventWaypoint event;
    JXMapViewer mapViewer;
    double lat = 13.723105892919316;
    double lon = -89.20433379787143;
    
    public void initx(map vista) {
        final List<TileFactory> factories = new ArrayList<TileFactory>();
        TileFactoryInfo osmInfo = new OSMTileFactoryInfo();
        TileFactoryInfo veInfo = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        factories.add(new DefaultTileFactory(osmInfo));
        factories.add(new DefaultTileFactory(veInfo));
        mapViewer = new JXMapViewer();
        mapViewer.setLayout(new BorderLayout());
        TileFactory firstFactory = factories.get(0);
        mapViewer.setTileFactory(firstFactory);

        GeoPosition kandy = new GeoPosition(13.723105892919316, -89.20433379787143);

        mapViewer.setZoom(5);
        mapViewer.setAddressLocation(kandy);
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
        vista.jPanel2.setLayout(new GridLayout());
        vista.jPanel2.setPreferredSize(new Dimension(200, 200));
        vista.jPanel2.add(mapViewer);
    }
    
    public void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        for (MyWaypoint wp : getWaypoints()) {
            Point2D p = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
            Rectangle rec = map.getViewportBounds();
            int x = (int) (p.getX() - rec.getX());
            int y = (int) (p.getY() - rec.getY());
            JButton cmd = wp.getButton();
            cmd.setLocation(x - cmd.getWidth() / 2, y - cmd.getHeight());
        }
    }
    
    public void addWaypoint(MyWaypoint waypoint) {
        for (MyWaypoint d : waypoints) {
            mapViewer.remove(d.getButton());
        }
        waypoints.add(waypoint);
        initWaypoint();
    }

    public void initWaypoint() {
        WaypointPainter<MyWaypoint> wp = new WaypointRender();
        wp.setWaypoints(waypoints);
        mapViewer.setOverlayPainter(wp);
        for (MyWaypoint d : waypoints) {
            mapViewer.add(d.getButton());
        }
    }

    public void clearWaypoint() {
        for (MyWaypoint d : waypoints) {
            mapViewer.remove(d.getButton());
        }
        waypoints.clear();
        initWaypoint();
    }
    
    public void Buscar() {                                          
        lat = lat + 0.0001;
        lon = lon + 0.0001;
        clearWaypoint();
        addWaypoint(new MyWaypoint("w1", event, new GeoPosition(lat, lon)));
    }                                         

    public void Reiniciar() {                                             
       lat = 13.723105892919316;
        lon = -89.20433379787143;
        clearWaypoint();
        addWaypoint(new MyWaypoint("w1", event, new GeoPosition(lat, lon)));
    }    
    
}
