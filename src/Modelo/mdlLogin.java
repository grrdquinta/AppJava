/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gerst
 */
public class mdlLogin {

    private String Usuario;
    private String Contraseña;
    
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }
    
    
    public Boolean ValidarLogin() 
    {
        Connection conexion = ClaseConexion.getConexion();
        try{
            
            String query = "SELECT E.*, R.NomRol as Rol, S.nombre as Sucursal, U.idusuario, U.usuario, U.contrasena\n" +
            "FROM USUARIO U\n" +
            "JOIN EMPLEADO E ON U.DUI = E.DUI\n" +
            "INNER JOIN ROL R ON E.IdRol = R.idRol\n" +
            "INNER JOIN SUCURSAL S ON E.idSucursal = S.idsucursal\n" +
            "WHERE U.USUARIO = ? AND U.CONTRASENA = ? AND E.Estado = 1 " ;
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, getUsuario());
            pstmt.setString(2, getContraseña());

            //pstmt.executeUpdate();
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {

                    SessionVar.setDui(rs.getString(1));
                    SessionVar.setNombre(rs.getString(2));
                    SessionVar.setApellidoPa(rs.getString(3));
                    SessionVar.setApellidoMa(rs.getString(4));
                    SessionVar.setMail(rs.getString(5));
                    SessionVar.setSalario(rs.getDouble(6));
                    SessionVar.setFechaNa(rs.getString(7));
                    SessionVar.setIdRol(rs.getInt(8));
                    SessionVar.setIdSucursal(rs.getInt(9));
                    SessionVar.setMasculino(rs.getInt(10));
                    //SessionVar.setEstado(rs.getInt(11));
                    SessionVar.setTelefono(rs.getString(12));
                    SessionVar.setFotoEmpleado(rs.getString(13));
                    SessionVar.setRol(rs.getString(14));
                    SessionVar.setSucursal(rs.getString(15));
                    SessionVar.setIdUsuario(rs.getString(16));
                    SessionVar.setUsuario(rs.getString(17));
                    SessionVar.setPass(rs.getString(18));
                }
            int respuesta = pstmt.executeUpdate();
            if(respuesta == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
   
    
}
