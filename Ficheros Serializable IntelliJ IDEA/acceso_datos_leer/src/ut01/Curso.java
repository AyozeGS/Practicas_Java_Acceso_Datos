package ut01;

import java.io.Serializable;
import java.util.Date;

public class Curso implements Serializable {

    //ATRIBUTOS
    private String codigo;
    private String denominacion;
    private int numHoras;
    private Date fechaInicio;
    private Date fechaFin;

    //CONSTRUCTOR
    public Curso(String codigo, String denominacion, int numHoras, Date fechaInicio, Date fechaFin) {
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.numHoras = numHoras;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    //METODOS GETTERS Y SETTERS
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
