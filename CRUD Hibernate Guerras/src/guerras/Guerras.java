/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guerras;

import conexion.conexion;
import controlador.Controlador;

/**
 *
 * @author Grupo3
 */
public class Guerras {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Controlador controlador;
        conexion.Gestor_conexion();
        controlador = new Controlador();   
    } 
}
