package Vista;

import Modelo.ClaseConexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Sucursal {
    
    public Sucursal(){
    
    }
    
    public List<String> obtenerDatos() {
        List<String> datosCard = new ArrayList<>();
        try 
            (Connection connection = ClaseConexion.getConexion();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT nombre FROM Sucursal")) {
            
            while (resultSet.next()) {
                datosCard.add(resultSet.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datosCard;
    }
}