/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import java.io.Serializable;


/**
 *
 * @author VIIII1
 */
public class CartillaBean implements Serializable {
    private long idCartilla;
    private long idDocenteEscalafon;
    private double totalHoras;
    private double totalParcial;
    private double notaPromedio;
    private double puntajeEscalafon;
    private String gestion;
    
    private String ciDocenteEscalafon;

    public long getIdCartilla() {
        return idCartilla;
    }

    public void setIdCartilla(long idCartilla) {
        this.idCartilla = idCartilla;
    }

    public long getIdDocenteEscalafon() {
        return idDocenteEscalafon;
    }

    public void setIdDocenteEscalafon(long idDocenteEscalafon) {
        this.idDocenteEscalafon = idDocenteEscalafon;
    }

    public double getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(double totalHoras) {
        this.totalHoras = totalHoras;
    }

    public double getTotalParcial() {
        return totalParcial;
    }

    public void setTotalParcial(double totalParcial) {
        this.totalParcial = totalParcial;
    }

    public double getNotaPromedio() {
        return notaPromedio;
    }

    public void setNotaPromedio(double notaPromedio) {
        this.notaPromedio = notaPromedio;
    }

    public double getPuntajeEscalafon() {
        return puntajeEscalafon;
    }

    public void setPuntajeEscalafon(double puntajeEscalafon) {
        this.puntajeEscalafon = puntajeEscalafon;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public String getCiDocenteEscalafon() {
        return ciDocenteEscalafon;
    }

    public void setCiDocenteEscalafon(String ciDocenteEscalafon) {
        this.ciDocenteEscalafon = ciDocenteEscalafon;
    }
    
    
}
