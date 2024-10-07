/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.Dashboard;
import Vista.ProfilePanel;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import jnafilechooser.api.JnaFileChooser;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
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
        //cargarImagenExistente(vista);
        
        
        

        // Agregar acción al botón
       

    }
    
    public void cargarImagenDesdeBD(ProfilePanel vista) {
    Connection conexion = ClaseConexion.getConexion();
    try {
        String sql = "SELECT FOTO_EMPLEADO FROM Empleado WHERE DUI = ?";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        pstmt.setString(1, SessionVar.getDui()); // Cambia esto para usar el UUID real que necesites
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String urlFoto = rs.getString("FOTO_EMPLEADO");
            if (urlFoto != null && !urlFoto.isEmpty()) {
                // Usar HttpURLConnection para cargar la imagen
                URL imageUrl = new URL(urlFoto);
                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                connection.setRequestMethod("POST");
                connection.connect();

                // Comprobar si la respuesta es OK
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // Leer la imagen
                    BufferedImage image = ImageIO.read(connection.getInputStream());
                    if (image != null) {
                        ImageIcon imageIcon = new ImageIcon(image);
                        vista.imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(270, 250, Image.SCALE_SMOOTH)));
                    } else {
                        System.out.println("No se pudo cargar la imagen desde la URL: " + urlFoto);
                    }
                } else {
                    System.out.println("Error al acceder a la URL: " + connection.getResponseMessage());
                }

                connection.disconnect(); // Desconectar
            } else {
                System.out.println("No se encontró ninguna URL de imagen en la base de datos.");
            }
        }
    } catch (SQLException | IOException e) {
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

    
    private String subirImagenImgbb(File imageFile) throws IOException, ParseException {
    // Cargar la imagen y convertirla en Base64
    byte[] fileContent = Files.readAllBytes(imageFile.toPath());
    String encodedImage = Base64.getEncoder().encodeToString(fileContent);

    // URL de la API de imgBB
    String uploadUrl = "https://api.imgbb.com/1/upload";

    // Reemplaza "YOUR_API_KEY" con tu clave de API de imgBB
    String apiKey = "b2441058fa326d319187dc5545aa9aff";

    // Crear un cliente HTTP
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost uploadFile = new HttpPost(uploadUrl + "?key=" + apiKey);

    // Crear un cuerpo multipart con la imagen codificada
    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    builder.addTextBody("image", encodedImage, ContentType.TEXT_PLAIN);

    // Establecer el cuerpo multipart en la solicitud
    HttpEntity multipart = builder.build();
    uploadFile.setEntity(multipart);

    // Ejecutar la solicitud de subida
    CloseableHttpResponse response = httpClient.execute(uploadFile);
    String jsonResponse = EntityUtils.toString(response.getEntity());

    // Imprimir la respuesta completa para verificar su contenido
    System.out.println("Respuesta de la API: " + jsonResponse);

    // Analizar la respuesta JSON para obtener la URL de la imagen
    JSONObject responseObject = new JSONObject(jsonResponse);
    String uploadedUrl = responseObject.getJSONObject("data").getString("url");

    response.close();
    return uploadedUrl;
}

    // Método para seleccionar y cargar la imagen desde la base de datos
    /*public void cargarImagenDesdeBD(NewJFrame vista) throws IOException {
        Connection conexion = ClaseConexion.getConexion();
        try {
            String sql = "SELECT FOTO_EMPLEADO FROM EMPLEADO WHERE UUID = 1";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String foto = rs.getString("FOTO_EMPLEADO");
                if (foto != null) {
                    InputStream is = foto.getBinaryStream();
                    BufferedImage image = ImageIO.read(is);
                    ImageIcon imageIcon = new ImageIcon(image);
                    vista.imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(270, 250, Image.SCALE_SMOOTH)));
                } else {
                    System.out.println("No se encontró ninguna imagen en la base de datos.");
                }
            }
        } catch (SQLException | IOException e) {
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
    }*/

    // Método para seleccionar una nueva imagen y guardarla
    public void seleccionarImagenYGuardar(ProfilePanel vista) {
        JnaFileChooser fileChooser = new JnaFileChooser();
                // Configurar el filtro de archivos para aceptar solo imágenes .jpg y .png
                fileChooser.addFilter("Imágenes (.jpg, .png)", "jpg", "png");
                boolean action = fileChooser.showOpenDialog(null);
                if (action) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                Connection conexion = ClaseConexion.getConexion();
                // Subir la imagen a imgBB
                String urlSubida = subirImagenImgbb(selectedFile);
                System.out.println(urlSubida);

                try{
                    String sql = "update empleado set foto_empleado = ? where Dui = ?";
                    PreparedStatement pstmt = conexion.prepareStatement(sql);
                    pstmt.setString(1, urlSubida);
                    pstmt.setString(2, SessionVar.getDui());
                    pstmt.executeUpdate();
                    SessionVar.setFotoEmpleado(urlSubida);
                    JOptionPane.showMessageDialog(null, "Imagen actualizada");
                    cargarImagenDesdeBD(vista);
                    JOptionPane.showMessageDialog(null, "Se veran reflejados los cambios la proxima ves que Inicies Sesion");
                }
                catch(Exception ex)
                {
                    System.out.println("no se pudo actualizar imagen en base de datos" + ex);
                }
                // Mostrar URL en un JOptionPane
                //JOptionPane.showMessageDialog(null, "Imagen subida a: " + urlSubida);
                
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error subiendo la imagen: " + ex.getMessage());
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
