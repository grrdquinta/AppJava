/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

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
import javax.swing.filechooser.FileNameExtensionFilter;
import jnafilechooser.api.JnaFileChooser;

/**
 *
 * @author gerst
 */
public class mdlPerfil {

    private String destinationFolder = "D:\\IMAGENES\\FOTOS_USUARIOS\\";

    public void seleccionarImagenYGuardar() {
    JnaFileChooser fileChooser = new JnaFileChooser();
    boolean action = fileChooser.showOpenDialog(null);

    if (action) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
            guardarImagen(selectedFile);
            System.out.println("Imagen guardada en: " + selectedFile.getPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

    public void guardarImagen(File selectedFile) throws IOException, SQLException {
        String destinationPath = destinationFolder + SessionVar.getDui() + getFileExtension(selectedFile);
        File destinationFile = new File(destinationPath);

        Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        guardarFotoEnBaseDeDatos(destinationFile.getName());
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        return name.substring(name.lastIndexOf("."));
    }

    private void guardarFotoEnBaseDeDatos(String nombreArchivo) throws SQLException {
        Connection conexion = ClaseConexion.getConexion();

        try {
            String sql = "UPDATE EMPLEADO SET FOTO_EMPLEADO = BFILENAME('DIR_FOTOS_USUARIOS', ?) WHERE DUI = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, nombreArchivo);
            pstmt.setString(2, SessionVar.getDui());

            pstmt.executeUpdate();
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public File cargarImagenExistente() throws SQLException {
        Connection conexion = ClaseConexion.getConexion();
        File imgFile = null;

        try {
            String sql = "SELECT FOTO_EMPLEADO FROM EMPLEADO WHERE DUI = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, SessionVar.getDui());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nombreArchivo = rs.getString("FOTO_EMPLEADO");
                imgFile = new File(destinationFolder + nombreArchivo);
                if (!imgFile.exists()) {
                    imgFile = null;
                }
            }
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
        return imgFile;
    }
    
    public void CargarInformacion(ProfilePanel vista) {
    
        vista.txtNombre.setText(SessionVar.getNombre());
        vista.txtApellidoPa.setText(SessionVar.getApellidoPa());
        vista.txtApellidoMa.setText(SessionVar.getApellidoMa());
        
    }
}
