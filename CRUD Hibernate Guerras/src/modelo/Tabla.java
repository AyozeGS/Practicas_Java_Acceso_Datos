/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Grupo3
 */
public class Tabla extends DefaultTableModel {
    
    //Constructor
    public Tabla(){
        String[] columnas = {"Autores"};
        this.addColumn(new TableColumn());
        this.setColumnIdentifiers(columnas);
        this.setRowCount(3);
        this.setValueAt("Gil Sosa, Ayoze", 0, 0);
        this.setValueAt("Guerra Rivero, Alexis de Jesús", 1, 0);
        this.setValueAt("Monte Soto, José Aurelio", 2, 0);
    };
    
    //Borrar tabla
    private void Borrar_Tabla(){
        int i;
        for (i=0;i<this.getRowCount();i++)
            this.removeRow(i);
        this.setColumnCount(0);
    }
    
    //Copiar a la tabla el contenido de un Result Set
    public void Actualizar_Tabla(ResultSet resultSet) throws SQLException{
        int numero_columnas = resultSet.getMetaData().getColumnCount();

        //Borramos tabla
        this.Borrar_Tabla();
        this.dataVector.clear();
        this.columnIdentifiers.clear();

        //Copiamos columnas
        for (int i=1; i<=numero_columnas ; i++){
            this.columnIdentifiers.add(resultSet.getMetaData().getColumnName(i));
        }    
        this.setColumnIdentifiers(this.columnIdentifiers);
        //Copiamos filas
        while (resultSet.next()) {
            Vector vectorFila = new Vector();
            for (int i=1; i<=numero_columnas; i++){
                vectorFila.add(resultSet.getObject(i));
            }
            this.dataVector.add(vectorFila);
        }
    }
    
    
}
