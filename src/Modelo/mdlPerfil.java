/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.Dashboard;
import Vista.ProfilePanel;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import jnafilechooser.api.JnaFileChooser;
import raven.drawer.component.header.SimpleHeaderData;
import raven.swing.AvatarIcon;

/**
 *
 * @author gerst
 */
public class mdlPerfil {

    private String Contraseña;
    
    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    private String destinationFolder = "D:\\Aplicaciones Java\\IdeaPTC\\src\\ImagenesUsuarios\\";
    //private String destinationFolder2 = "D:\\Aplicaciones Java\\IdeaPTC\\src\\Vista\\ImagenesCliente";
    //ProfilePanel perfil = new ProfilePanel();

    public void SeleccionarImagen(ProfilePanel vista) {
        
        // **Cargar la imagen de perfil existente si la hay**
        cargarImagenExistente(vista);
        
        
        

        // Agregar acción al botón
       

    }

    
    public void cargarImagenExistente(ProfilePanel vista) {
    Connection conexion = ClaseConexion.getConexion();
    try {
        // Definir las extensiones que se van a soportar
        String[] extensiones = {".jpg", ".png" };
        File imgFile = null;

        // Iterar sobre las extensiones y buscar si existe un archivo con la extensión correspondiente
        for (String extension : extensiones) {
            imgFile = new File(destinationFolder + SessionVar.getDui() + extension);
            if (imgFile.exists()) {
                break; // Si encontramos una imagen que existe, salimos del bucle
            }
        }

        if (imgFile != null && imgFile.exists()) {
            // Ajuste en la carga de la imagen
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(imgFile));
            vista.imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(270, 250, Image.SCALE_SMOOTH)));
        } else {
            System.out.println("No se encontró ninguna imagen con las extensiones .jpg, .png o .jpeg en la ruta especificada.");
        }

        
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    public void seleccionarImagenYGuardar(ProfilePanel vista) {
    JnaFileChooser fileChooser = new JnaFileChooser();

    // Configurar el filtro de archivos para aceptar solo imágenes .jpg y .png
    fileChooser.addFilter("Imágenes (.jpg)", "jpg");

    boolean action = fileChooser.showOpenDialog(null);

        if (action) {
            File selectedFile = fileChooser.getSelectedFile();
            String extension = getFileExtension(selectedFile).toLowerCase();

            // Validar que la extensión sea .jpg o .png
            if (extension.equals(".jpg") || extension.equals(".png")) {
                String destinationPath = destinationFolder + SessionVar.getDui() + extension;
                File destinationFile = new File(destinationPath);

                try {
                    Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Imagen guardada como: " + destinationFile.getPath());

                    // Cargar la imagen en el JLabel
                    ImageIcon imageIcon = new ImageIcon(ImageIO.read(destinationFile));
                    vista.imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

                    //rsscalelabel.RSScaleLabel.setScaleLabel(Dashboard.lbImage, "src/ImagenesUsuarios/" + SessionVar.getDui() +".jpg");

                    // Guardar el nombre de la imagen en la base de datos
                    guardarFotoEnBaseDeDatos(destinationFile.getName(), vista);
                    MyDrawerBuilder menu = new MyDrawerBuilder();
                    menu.getSimpleHeaderData();
                    menu.rebuildMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Mostrar alerta si el archivo no es .jpg o .png
                JOptionPane.showMessageDialog(null, "Solo se permiten archivos .jpg o .png", "Error de archivo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return ""; // en caso de que no tenga extensión
        }
    }

    public void guardarFotoEnBaseDeDatos(String nombreArchivo, ProfilePanel vista) {
        Connection conexion = ClaseConexion.getConexion();

        try {
            String sql = "UPDATE EMPLEADO SET FOTO_EMPLEADO = BFILENAME('DIR_FOTOS_USUARIOS', ?) WHERE DUI = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, nombreArchivo);
            pstmt.setString(2, SessionVar.getDui());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Foto actualizada en la base de datos.");
                cargarImagenExistente(vista);
            } else {
                System.out.println("No se encontró ningún registro con el DUI proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void CargarInformacion(ProfilePanel vista) {
    
        vista.txtDui.setText(SessionVar.getDui());
        vista.txtNombre.setText(SessionVar.getNombre());
        vista.txtApellidoPa.setText(SessionVar.getApellidoPa() + " " +SessionVar.getApellidoMa());
        vista.txtMail.setText(SessionVar.getMail());
        vista.txtFechaNa.setText(SessionVar.getFechaNa());
        vista.txtTelefono.setText(SessionVar.getTelefono());
        vista.txtRol.setText(SessionVar.getRol());
        vista.txtUsuario.setText(SessionVar.getUsuario());
    }
    
    public int ActualizarPerfil(ProfilePanel vista) {
    
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario

        
            try
               {
                String sql = "update usuario set usuario = ? , contrasena = ? where dui = ?";

                PreparedStatement pstmt = conexion.prepareStatement(sql);
                pstmt.setString(1, vista.txtUsuario.getText());
                pstmt.setString(2, getContraseña());
                pstmt.setString(3, SessionVar.getDui());
                int respuesta = pstmt.executeUpdate();
                if(respuesta == 1)
                    {
                        SessionVar.setUsuario(vista.txtUsuario.getText());
                        vista.btnActivar.setText("Modificar");
                        vista.txtUsuario.setEditable(false);
                        vista.txtCambiarCont.setEditable(false);
                        vista.txtConfirmCont.setEditable(false);
                        vista.txtContActual.setEditable(false);
                        vista.txtUsuario.setText(null);
                        vista.txtCambiarCont.setText(null);
                        vista.txtConfirmCont.setText(null);
                        vista.txtContActual.setText(null);
                        return respuesta;
                    }
                    else
                    {
                        return 0;
                    }

                }
                catch (SQLException ex) 
                {
                    System.out.println("este es el error en el modelo:metodo actualizar " + ex);
                    return -1;
                }
    }
}
