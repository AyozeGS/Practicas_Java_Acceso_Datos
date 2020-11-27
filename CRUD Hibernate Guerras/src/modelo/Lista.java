/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import modelo.Lista.ParIdNombre;

/**
 *
 * @author Grupo3
 */
public class Lista extends HashMap<Integer, ParIdNombre>{
    
    /* 
    * Clase auxiliar con el par de de volres nombre, para la vista de usuario, 
    *  e ID, para cruzar datos con la base de datos 
    */
    public class ParIdNombre {
        int id;
        String nombre;

        public ParIdNombre(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }
    }
    
    //Copiar a la lista el contenido de un ResultSet
    public void Actualizar (ResultSet resultSet) throws SQLException{
        int indice = 1;
        this.clear();
        while (resultSet.next()){
            this.put(indice++, new ParIdNombre(
                    resultSet.getInt(1), resultSet.getString(2)));
        }
    }
    
    //Pasa los indices y los nombres a un JComboBox
    public void aJComboBox(JComboBox lista){
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        Iterator puntero = this.entrySet().iterator();
        modelo.addElement("-");
        while (puntero.hasNext()) {
            Map.Entry id_nombre = (Map.Entry)puntero.next();            
            modelo.addElement(((ParIdNombre)id_nombre.getValue()).getNombre());
            lista.setModel(modelo);
        }
    }
    
    //Pasa los indices y los nombres a un JList
    public void aJList(JList lista){
        DefaultListModel<String> modelo = new DefaultListModel<>();
        Iterator puntero = this.entrySet().iterator();
        while (puntero.hasNext()) {
            Map.Entry id_nombre = (Map.Entry)puntero.next();            
            modelo.addElement(((ParIdNombre)id_nombre.getValue()).getNombre());
            lista.setModel(modelo);
        }
    }

}
