/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contexto;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Ayoze Gil
 */
@XmlRootElement (name = "dia") //Indica que es la raíz de la clase y su nombre en xml
@XmlType(propOrder = { "dia", "movimientos"}) // Indica el orden de los subelementos en xml
public class Dia {
    
    private String dia;
    private ArrayList<Movimiento> movimientos;

    public Dia() {
        movimientos = new ArrayList<>();
    }

    public Dia(String dia, ArrayList<Movimiento> movimientos) {
        this.dia = dia;
        this.movimientos = movimientos;
    }

    @XmlElement(name="num_dia") //Cambia el nombre en xml
    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @XmlElementWrapper(name = "movimientos") //Indica una colección de objetos
    @XmlElement(name = "movimiento") // Indica el nombre de cada objeto de la colección
    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
