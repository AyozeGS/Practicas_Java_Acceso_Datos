/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Grupo3
 */
public class Guerra {
    
    int id_guerra;
    String nombre_guerra;
    Date fecha_inicio;
    Date fecha_fin;

    public Guerra() {
    }

    public Guerra(int id_guerra, String nombre_guerra, Date fecha_inicio, Date fecha_fin) {
        this.id_guerra = id_guerra;
        this.nombre_guerra = nombre_guerra;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }
    
    public void setGuerra(int id_guerra, String nombre_guerra, Date fecha_inicio, Date fecha_fin) {
        this.id_guerra = id_guerra;
        this.nombre_guerra = nombre_guerra;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }
    
    public void setGuerra(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            this.id_guerra = resultSet.getInt(1);
            this.nombre_guerra = resultSet.getString(2);
            this.fecha_inicio = resultSet.getDate(3);
            this.fecha_fin = resultSet.getDate(4);
        }
        else {
            this.id_guerra = -1;
            this.nombre_guerra = "No Encontrado";
            this.fecha_inicio = null;
            this.fecha_fin = null;
        }
            
    }
    
    public int getId_guerra() {
        return id_guerra;
    }

    public void setId_guerra(int id_guerra) {
        this.id_guerra = id_guerra;
    }

    public String getNombre_guerra() {
        return nombre_guerra;
    }

    public void setNombre_guerra(String nombre_guerra) {
        this.nombre_guerra = nombre_guerra;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    
    
    

    
}
