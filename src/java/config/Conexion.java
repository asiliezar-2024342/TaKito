package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private Connection conexion;
    private static Conexion instance;

    private Conexion() {
    }

    public static synchronized Conexion getInstance() {
        instance = instance == null ? new Conexion() : instance;
        return instance;
    }

    
     public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBTaKito?useSSL=false", "root", "admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
}
