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
public class EscalafonBean implements Serializable {
    private long idEscalafon;
    private String ciDocenteEscalafon;
    private long idCartilla;
    private double puntaje;
    private double puntajeAcumulado;
    private String categoria;
    private String gestion;
    
    private boolean print;

    /**
     * @return the idEscalafon
     */
    public long getIdEscalafon() {
        return idEscalafon;
    }

    /**
     * @param idEscalafon the idEscalafon to set
     */
    public void setIdEscalafon(long idEscalafon) {
        this.idEscalafon = idEscalafon;
    }

    /**
     * @return the ciDocenteEscalafon
     */
    public String getCiDocenteEscalafon() {
        return ciDocenteEscalafon;
    }

    /**
     * @param ciDocenteEscalafon the ciDocenteEscalafon to set
     */
    public void setCiDocenteEscalafon(String ciDocenteEscalafon) {
        this.ciDocenteEscalafon = ciDocenteEscalafon;
    }

    /**
     * @return the idCartilla
     */
    public long getIdCartilla() {
        return idCartilla;
    }

    /**
     * @param idCartilla the idCartilla to set
     */
    public void setIdCartilla(long idCartilla) {
        this.idCartilla = idCartilla;
    }

    /**
     * @return the puntaje
     */
    public double getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * @return the puntajeAcumulado
     */
    public double getPuntajeAcumulado() {
        return puntajeAcumulado;
    }

    /**
     * @param puntajeAcumulado the puntajeAcumulado to set
     */
    public void setPuntajeAcumulado(double puntajeAcumulado) {
        this.puntajeAcumulado = puntajeAcumulado;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the gestion
     */
    public String getGestion() {
        return gestion;
    }

    /**
     * @param gestion the gestion to set
     */
    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    /**
     * @return the print
     */
    public boolean isPrint() {
        return print;
    }

    /**
     * @param print the print to set
     */
    public void setPrint(boolean print) {
        this.print = print;
    }
    
}
