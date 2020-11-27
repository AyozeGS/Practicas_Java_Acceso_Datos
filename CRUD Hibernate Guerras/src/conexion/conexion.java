/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
*
* @author Grupo3
*/
public class conexion { 
   
    static Connection conn1=null; 

    
    public static void Gestor_conexion() {
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url1="jdbc:mysql://localhost:3306/guerras"; //ver my.ini port=3306
            String user="root";
            String password="";//no tiene clave
            conn1=DriverManager.getConnection(url1,user,password);
            if (conn1 != null)
            {
                System.out.println("Conectado a base de datos 'guerras'...");
            }
        }
        catch (SQLException ex)
        {
            System.out.println("ERROR: Dirección a o usuario/clave");
        }
    }

    public static Connection getConn1() {
        return conn1;
    }
    
    public static void cerrar() {
        try {
            conn1.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}