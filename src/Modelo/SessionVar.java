/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author gerst
 */
public class SessionVar {

    private static String Dui = "";
    private static String Nombre = "";
    private static String ApellidoPa = "";
    private static String ApellidoMa = "";
    private static String Mail = "";
    private static Double Salario = 0.0;
    private static String FechaNa = "";
    private static int IdRol = 0;
    private static int IdSucursal = 0;
    private static int Masculino = 2;
    private static int Estado = 2;
    private static String Rol = "";
    private static String Sucursal = ""; 

    
    public static String getRol() {
        return Rol;
    }

    public static void setRol(String Rol) {
        SessionVar.Rol = Rol;
    }

    public static String getSucursal() {
        return Sucursal;
    }

    public static void setSucursal(String Sucursal) {
        SessionVar.Sucursal = Sucursal;
    }
   
    public static int getEstado() {
        return Estado;
    }

    public static void setEstado(int Estado) {
        SessionVar.Estado = Estado;
    }
    
    public static String getDui() {
        return Dui;
    }

    public static void setDui(String Dui) {
        SessionVar.Dui = Dui;
    }

    public static String getNombre() {
        return Nombre;
    }

    public static void setNombre(String Nombre) {
        SessionVar.Nombre = Nombre;
    }

    public static String getApellidoPa() {
        return ApellidoPa;
    }

    public static void setApellidoPa(String ApellidoPa) {
        SessionVar.ApellidoPa = ApellidoPa;
    }

    public static String getApellidoMa() {
        return ApellidoMa;
    }

    public static void setApellidoMa(String ApellidoMa) {
        SessionVar.ApellidoMa = ApellidoMa;
    }

    public static String getMail() {
        return Mail;
    }

    public static void setMail(String Mail) {
        SessionVar.Mail = Mail;
    }

    public static Double getSalario() {
        return Salario;
    }

    public static void setSalario(Double Salario) {
        SessionVar.Salario = Salario;
    }

    public static String getFechaNa() {
        return FechaNa;
    }

    public static void setFechaNa(String FechaNa) {
        SessionVar.FechaNa = FechaNa;
    }

    public static int getIdRol() {
        return IdRol;
    }

    public static void setIdRol(int IdRol) {
        SessionVar.IdRol = IdRol;
    }

    public static int getIdSucursal() {
        return IdSucursal;
    }

    public static void setIdSucursal(int IdSucursal) {
        SessionVar.IdSucursal = IdSucursal;
    }

    public static int getMasculino() {
        return Masculino;
    }

    public static void setMasculino(int Masculino) {
        SessionVar.Masculino = Masculino;
    }
    
    
    
}
