/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contexto;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Ayoze Gil
 */
@XmlRootElement (name = "mes") //Indica que es la raíz de la clase y su nombre en xml
@XmlType(propOrder = {"mes", "dias"}) // Indica el orden de los subelementos en xml
public class Mes {
    
    private String mes;
    private String nombre;
    private ArrayList<Dia> dias;

    public Mes() {
        dias = new ArrayList<>();
    }

    public Mes(String mes, ArrayList<Dia> dias) {
        this.mes = mes;
        this.dias = dias;
        switch(mes){
            case "01": this.nombre = "Enero"; break;
            case "02": this.nombre = "Febrero"; break;
            case "03": this.nombre = "Marzo"; break;
            case "04": this.nombre = "Abril"; break;
            case "05": this.nombre = "Mayo"; break;
            case "06": this.nombre = "Junio"; break;
            case "07": this.nombre = "Julio"; break;
            case "08": this.nombre = "Agosto"; break;
            case "09": this.nombre = "Septiembre"; break;
            case "10": this.nombre = "Octubre"; break;
            case "11": this.nombre = "Noviembre"; break;
            case "12": this.nombre = "Diciembre"; break;
        }
    }

    @XmlElement(name="num_mes") //Cambia el nombre en xml
    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
        switch(mes){
            case "01": this.nombre = "Enero"; break;
            case "02": this.nombre = "Febrero"; break;
            case "03": this.nombre = "Marzo"; break;
            case "04": this.nombre = "Abril"; break;
            case "05": this.nombre = "Mayo"; break;
            case "06": this.nombre = "Junio"; break;
            case "07": this.nombre = "Julio"; break;
            case "08": this.nombre = "Agosto"; break;
            case "09": this.nombre = "Septiembre"; break;
            case "10": this.nombre = "Octubre"; break;
            case "11": this.nombre = "Noviembre"; break;
            case "12": this.nombre = "Diciembre"; break;
        }
    }

    @XmlAttribute(name="id") //Indica que es un atributo y su nombre en xml
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        switch(nombre){
            case "Enero": this.nombre = "01"; break;
            case "Febrero": this.nombre = "02"; break;
            case "Marzo": this.nombre = "03"; break;
            case "Abril": this.nombre = "04"; break;
            case "Mayo": this.nombre = "05"; break;
            case "Junio": this.nombre = "06"; break;
            case "Julio": this.nombre = "07"; break;
            case "Agosto": this.nombre = "08"; break;
            case "Septiembre": this.nombre = "09"; break;
            case "Octubre": this.nombre = "10"; break;
            case "Noviembre": this.nombre = "11"; break;
            case "Diciembre": this.nombre = "12"; break;
        }
    }
    
    @XmlElementWrapper(name = "dias") //Indica una colección de objetos
    @XmlElement(name = "dia") // Indica el nombre de cada objeto de la colección
    public ArrayList<Dia> getDias() {
        return dias;
    }

    public void setDias(ArrayList<Dia> dias) {
        this.dias = dias;
    }
}
