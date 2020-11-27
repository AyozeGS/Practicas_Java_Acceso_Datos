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
@XmlRootElement (name = "año") //Indica que es la raíz de la clase y su nombre en xml
@XmlType(propOrder = { "año", "meses"}) // Indica el orden de los subelementos en xml
public class Año {
    
    private String año;
    private ArrayList<Mes> meses;

    public Año() {
        meses = new ArrayList<>();
    }

    public Año(String año, ArrayList<Mes> meses) {
        this.año = año;
        this.meses = meses;
    }

    @XmlElement(name="num_año") //Cambia el nombre en xml
    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    @XmlElementWrapper(name = "meses") //Indica una colección de objetos
    @XmlElement(name = "mes") // Indica el nombre de cada objeto de la colección
    public ArrayList<Mes> getMeses() {
        return meses;
    }

    public void setMeses(ArrayList<Mes> meses) {
        this.meses = meses;
    }
}
