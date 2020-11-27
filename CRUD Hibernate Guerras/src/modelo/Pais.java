/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 *
 * @author Grupo3
 */
public class Pais {
    
    int id_pais;
    String nombre_pais;
    HashSet<PeriodoIndependencia> periodo_independecia = new HashSet<>();

    public Pais() {
    }

    
    public Pais(int id_pais, String nombre_pais) {
        this.id_pais = id_pais;
        this.nombre_pais = nombre_pais;
    }

    public void setPais (int id_pais, String nombre_pais) {
        this.id_pais = id_pais;
        this.nombre_pais = nombre_pais;
    }
    
    public void setPais (ResultSet resultSet) throws SQLException {
        
        if (resultSet.next()){
            this.id_pais = resultSet.getInt("id_pais");
            this.nombre_pais = resultSet.getString("nombre_pais");
        }
        else {
            this.id_pais = -1;
            this.nombre_pais = "No Encontrado";
        }
    }
    
    public void añadirPeriodoIndependencia (Date fecha_inicio, Date fecha_fin){
        periodo_independecia.add(new PeriodoIndependencia(fecha_inicio, fecha_fin));
    }
    
    public void LimpiarPeriodosIndependencia (){
        periodo_independecia.clear();
    }
    
    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }
}
