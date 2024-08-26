/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author gerst
 */
public class Encriptacion {
    
    public String convertirSHA256(String password){
    
        MessageDigest md = null;
        
        try{
            md = MessageDigest.getInstance("SHA-256");
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println(e.toString());
            return null;
        }
        
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        
        for(byte b : hash) 
        {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
}
