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

/**
 *
 * @author Ayoze Gil
 */
@XmlRootElement //Anotación que indica que es el elemento raíz
public class LibroCuentas {
    
    @XmlElementWrapper(name = "años") //Indica una colección de objetos
    @XmlElement(name = "año") // Indica cambio en el nombre del objeto en el xml
    private ArrayList<Año> años;

    public LibroCuentas() {
        años = new ArrayList<>();
    }

    public LibroCuentas(ArrayList<Año> años) {
        this.años = años;
    }

    public ArrayList<Año> getAños() {
        return años;
    }

    public void setDias(ArrayList<Año> años) {
        this.años = años;
    }
}