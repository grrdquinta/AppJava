/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


    import Vista.Dashboard;
    import Vista.DashboardPanel;
    import Vista.EmpleadosPanel;
    import Vista.FlotaPanel;
import Vista.FrmSucursales;
    import Vista.Gmap;
import Vista.InicioPanel;
    import Vista.Login;
    import Vista.Main;
import Vista.MarcaPanel;
import Vista.ModeloPanel;
    import Vista.PaquetesPanel;
    import Vista.ProfilePanel;
import Vista.mapPanel;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
    import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
    import javax.swing.JFrame;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
    import raven.drawer.component.SimpleDrawerBuilder;
    import raven.drawer.component.footer.SimpleFooterData;
    import raven.drawer.component.header.SimpleHeaderData;
    import raven.drawer.component.menu.MenuAction;
    import raven.drawer.component.menu.MenuEvent;
    import raven.drawer.component.menu.MenuValidation;
    import raven.drawer.component.menu.SimpleMenuOption;
    import raven.swing.AvatarIcon;

    /**
     *
     * @author gerst
     */
    public class MyDrawerBuilder extends SimpleDrawerBuilder{
        String[] extensiones = {".jpg", ".png" };

        private AvatarIcon avatarIcon;

        /*private static MyDrawerBuilder instance;

    private MyDrawerBuilder() {
        // Constructor privado para evitar instancias externas
    }

    public static MyDrawerBuilder getInstance() {
        if (instance == null) {
            instance = new MyDrawerBuilder();
        }
        return instance;
    }
        
    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        actualizarIcono();
        return new SimpleHeaderData()
                .setIcon(avatarIcon) // Usar el avatarIcon actualizado
                .setTitle(SessionVar.getNombre())
                .setDescription("Sucursal: " + SessionVar.getSucursal());
    }

    // Método para actualizar el icono del perfil
    public void actualizarIcono() {
        File imgFile = null;

        // Iterar sobre las extensiones para encontrar la imagen actualizada
        for (String extension : extensiones) {
            imgFile = new File("D:\\Aplicaciones Java\\IdeaPTC\\src\\ImagenesUsuarios\\" + SessionVar.getDui() + extension);
            if (imgFile.exists()) {
                break;
            }
        }

        String iconPath = null;
        if (imgFile != null && imgFile.exists()) {
            iconPath = imgFile.getAbsolutePath();
        } else {
            // Fallback a una imagen por defecto si no se encuentra la imagen
            iconPath = getClass().getResource("/default-avatar.png").getPath();
        }

        // Actualizar el AvatarIcon con la nueva imagen
        avatarIcon = new AvatarIcon(iconPath, 60, 60, 999);
    }*/
        
        @Override
        public SimpleHeaderData getSimpleHeaderData() {
            String fotoUrl = SessionVar.getFotoEmpleado();
        // Cargar la imagen desde la URL
          try {
                 // Esto contiene la URL completa de la imagen

                // Cargar la imagen desde la URL
                var url = new URL(fotoUrl);  // Usar 'var' para la inferencia de tipos
                //var image = ImageIO.read(url); // Cargar la imagen desde la URL

                // Crear el icono de avatar con la imagen descargada
                var avatarIcon = new AvatarIcon(url, 60, 60, 999);  // Usar URL directamente

                return new SimpleHeaderData()
                    .setIcon(avatarIcon)
                    .setTitle(SessionVar.getNombre())
                    .setDescription(SessionVar.getRol())
                    .setDescription("Sucursal: " + SessionVar.getSucursal());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.err.println("La URL es inválida: " + fotoUrl);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error al cargar la imagen desde la URL: " + fotoUrl);
            }
            return new SimpleHeaderData()                
                    .setIcon(new AvatarIcon(getClass().getResource(SessionVar.getFotoEmpleado()) ,60,60,999))
                    .setTitle(SessionVar.getNombre())
                    .setDescription(SessionVar.getRol())
                    .setDescription("Sucursal: " + SessionVar.getSucursal());
            
        }
        
        public void init() {
        
        JButton cmd = new JButton(new FlatSVGIcon("modelo/refresh.svg", 0.8f));
        cmd.addActionListener((ae) -> {
            getSimpleHeaderData();
        });
        cmd.putClientProperty(FlatClientProperties.STYLE, ""
                + "margin:5,5,5,5;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:null;"
                + "arc:999;");
    }

        @Override
        public SimpleMenuOption getSimpleMenuOption() {
            if(SessionVar.getIdRol() == 1 || SessionVar.getIdRol() == 4){
            
            String menus[][] = {
            {"~INICIO~"},
            {"Dashboard"},
            {"~MOVIMIENTOS~"},
            {"Movimiento","Paquetes", "Sucursales"},
            {"~SEGUIMIENTO~"},
            {"Flota","Vehiculos", "Marcas", "Modelo", "Mapa"},
            {"~Empleados~"},
            {"Empleados"},
            {"~OTHER~"},
            {"Inicio"},
            {"Perfil"},
            {"Logout"}
        };  
            
        String icons[] = {
            "dashboard.svg",
            "movimientos.svg",
            "flota.svg",
            "empleados.svg",
            "paquetes.svg",
            "empleados.svg",
            "logout.svg"};    
            return new SimpleMenuOption()
                    .setMenus(menus)
                    .setIcons(icons)
                    .setBaseIconPath("Vista/")
                    .addMenuEvent(new MenuEvent() {
                        @Override
                        public void selected(MenuAction action, int index, int subIndex) {
                            if (index == 0) {
                                WindowsTabbed.getInstance().addTab("Dashboard", new DashboardPanel());
                            }
                            else if (index == 1 && subIndex == 1) {
                                WindowsTabbed.getInstance().addTab("Paquetes", new PaquetesPanel());
                            }
                            else if (index == 1 && subIndex == 2) {
                                WindowsTabbed.getInstance().addTab("Sucursales", new FrmSucursales());
                            }
                            else if (index == 2 && subIndex == 1) {
                                WindowsTabbed.getInstance().addTab("Vehiculos", new FlotaPanel());
                            }
                            else if (index == 2 && subIndex == 2) {
                                WindowsTabbed.getInstance().addTab("Marcas", new MarcaPanel());
                            }
                            else if (index == 2 && subIndex == 3) {
                                WindowsTabbed.getInstance().addTab("Modelos", new ModeloPanel());
                            }
                            else if (index == 2 && subIndex == 4) {
                                WindowsTabbed.getInstance().addTab("Mapa", new mapPanel());
                            }
                            else if (index == 3 && subIndex == 0) {
                                WindowsTabbed.getInstance().addTab("Empleado", new EmpleadosPanel());
                            }
                            else if (index == 4 && subIndex == 0) {
                                WindowsTabbed.getInstance().addTab("Inicio", new InicioPanel());
                            }
                            else if (index == 5 && subIndex == 0) {
                                WindowsTabbed.getInstance().addTab("Perfil", new ProfilePanel());
                            }

                            else if (index == 6 && subIndex == 0) {

                                Main.main.dispose();  // Cierra la ventana principal
                                Main.main = null;     // Libera la referencia a la instancia de Main

                                // Crear e iniciar el login nuevamente
                                Login login = new Login();
                                login.setVisible(true);
                            }
                            System.out.println("Menu selected " + index + " " + subIndex);
                        }
                    })
                    .setMenuValidation(new MenuValidation() {
                        @Override
                        public boolean menuValidation(int index, int subIndex) {
    //                        if(index==0){
    //                            return false;
    //                        }else if(index==3){
    //                            return false;
    //                        }
                            return true;
                        }

                    });}
            else if(SessionVar.getIdRol() == 2){
                String menus[][] = {
            {"~INICIO~"},
            {"~MOVIMIENTOS~"},
            {"Movimiento","Paquetes"},
            {"~OTHER~"},
            {"Inicio"},
            {"Perfil"},
            {"Logout"}
        };  
            
        String icons[] = {
            "movimientos.svg",
            "paquetes.svg",
            "empleados.svg",
            "logout.svg"};    
            return new SimpleMenuOption()
                    .setMenus(menus)
                    .setIcons(icons)
                    .setBaseIconPath("Vista/")
                    .addMenuEvent(new MenuEvent() {
                        @Override
                        public void selected(MenuAction action, int index, int subIndex) {
                            if (index == 0 && subIndex == 1 ) {
                                WindowsTabbed.getInstance().addTab("Paquetes", new PaquetesPanel());
                            }
                            else if (index == 1 && subIndex == 0) {
                                WindowsTabbed.getInstance().addTab("Inicio", new InicioPanel());
                            }
                            else if (index == 2 && subIndex == 0) {
                                WindowsTabbed.getInstance().addTab("Perfil", new ProfilePanel());
                            }
                            else if (index == 4 && subIndex == 0) {
                                WindowsTabbed.getInstance().addTab("Vehiculos", new FlotaPanel());
                            }
                            else if (index == 2 && subIndex == 2) {
                                WindowsTabbed.getInstance().addTab("Marcas", new MarcaPanel());
                            }
                            else if (index == 2 && subIndex == 3) {
                                WindowsTabbed.getInstance().addTab("Modelos", new ModeloPanel());
                            }
                            else if (index == 2 && subIndex == 4) {
                                WindowsTabbed.getInstance().addTab("Mapa", new mapPanel());
                            }
                            else if (index == 3 && subIndex == 0) {
                                //WindowsTabbed.getInstance().addTab("Empleado", new EmpleadosPanel());
                                Main.main.dispose();  // Cierra la ventana principal
                                Main.main = null;     // Libera la referencia a la instancia de Main

                                // Crear e iniciar el login nuevamente
                                Login login = new Login();
                                login.setVisible(true);
                            }
                            else if (index == 4 && subIndex == 0) {
                                
                            }
                            else if (index == 5 && subIndex == 0) {
                                
                            }

                            else if (index == 3 ) {

                                Main.main.dispose();  // Cierra la ventana principal
                                Main.main = null;     // Libera la referencia a la instancia de Main

                                // Crear e iniciar el login nuevamente
                                Login login = new Login();
                                login.setVisible(true);
                            }
                            System.out.println("Menu selected " + index + " " + subIndex);
                        }
                    })
                    .setMenuValidation(new MenuValidation() {
                        @Override
                        public boolean menuValidation(int index, int subIndex) {
    //                        if(index==0){
    //                            return false;
    //                        }else if(index==3){
    //                            return false;
    //                        }
                            return true;
                        }

                    });
            }
            
          return getSimpleMenuOption();
        }

        @Override
        public SimpleFooterData getSimpleFooterData() {
            return new SimpleFooterData();
        }

        @Override
        public int getDrawerWidth() {
            return 275;
        }
}
