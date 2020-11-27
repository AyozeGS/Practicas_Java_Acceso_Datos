/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ayoze Gil
 */
public class Tabla extends DefaultTableModel {
    
    public Tabla() {
        String[] columnas = new String[2];
        this.setColumnCount(2);
        columnas[0] = "Importe";
        columnas[1] = "Concepto";
        this.setColumnIdentifiers(columnas);
        this.setRowCount(0);
    }
    
    @Override
    public int getRowCount() {
        return this.dataVector.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        String[] indice = (String[])this.dataVector.get(i);
        return indice[i1];
    }
}
