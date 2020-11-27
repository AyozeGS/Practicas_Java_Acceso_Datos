/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Grupo3
 */
public class Negocio {
   ResultSet rs; 
   Connection con;
    
   public Negocio(){
       con = conexion.getConn1();
   }
   
   public ResultSet consulta(String cadena) throws SQLException{
       Statement s = con.createStatement();
       return rs= s.executeQuery (cadena); 
    }
   
    public int update(String cadena) throws SQLException{
       Statement s = con.createStatement();
       return s.executeUpdate (cadena); 
    }

    public ResultSet getRs() {
        return rs;
    }

    public Connection getCon() {
        return con;
    }

}
