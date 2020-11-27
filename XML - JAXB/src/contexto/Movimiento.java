/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contexto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ayoze Gil
 */
@XmlRootElement (name = "movimiento") //Indica que es la raíz de la clase y su nombre en xml
public class Movimiento {
    
    String concepto;
    int importe;

    public Movimiento() {
    }

    public Movimiento(String concepto, int importe) {
        this.concepto = concepto;
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }
}