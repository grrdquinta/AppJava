/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.Dashboard;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author gerst
 */
public class mdlDashboard {
    
     public void CerrarSesion() {
        
        SessionVar.setDui("");
        SessionVar.setNombre("");
        SessionVar.setApellidoPa("");
        SessionVar.setApellidoMa("");
        SessionVar.setMail("");
        SessionVar.setSalario(0.0);
        SessionVar.setFechaNa("");
        SessionVar.setIdRol(0);
        SessionVar.setIdSucursal(0);
        SessionVar.setMasculino(2);
        SessionVar.setEstado(2);
        SessionVar.setRol("");
        SessionVar.setSucursal("");
    }
     
    private String destinationFolder = "D:\\Aplicaciones Java\\IdeaPTC\\src\\ImagenesUsuarios\\";
    
    /*public void cargarImagenExistente(Dashboard vista) {
    Connection conexion = ClaseConexion.getConexion();
    try {
        // Definir las extensiones que se van a soportar
        String[] extensiones = {".png", ".jpg" };
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
            String[] extensions = {".png", ".jpg" };
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(imgFile));
            rsscalelabel.RSScaleLabel.setScaleLabel(vista.lbImage, "src/Vista/ImagenesCliente" + SessionVar.getDui() + extensions);
            //vista.lbImage.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } else {
            imgFile = new File(destinationFolder + "default.png");
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(imgFile));
            vista.lbImage.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
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
     
}*/
    private void SetImageLabel(JLabel labelName, String root)
    {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
    }
    
}
