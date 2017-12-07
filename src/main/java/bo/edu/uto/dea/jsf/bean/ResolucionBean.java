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
public class ResolucionBean implements Serializable {
    private long idResolucion;
    private long idCartilla;
    private String contenido;
    private String gestion;
    private double puntajeEscalafon;
    private double puntajeAcumulado;
    private String categoria;
    private String tipoResolucion;

    /**
     * @return the idResolucion
     */
    public long getIdResolucion() {
        return idResolucion;
    }

    /**
     * @param idResolucion the idResolucion to set
     */
    public void setIdResolucion(long idResolucion) {
        this.idResolucion = idResolucion;
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
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
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
     * @return the puntajeEscalafon
     */
    public double getPuntajeEscalafon() {
        return puntajeEscalafon;
    }

    /**
     * @param puntajeEscalafon the puntajeEscalafon to set
     */
    public void setPuntajeEscalafon(double puntajeEscalafon) {
        this.puntajeEscalafon = puntajeEscalafon;
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
     * @return the tipoResolucion
     */
    public String getTipoResolucion() {
        return tipoResolucion;
    }

    /**
     * @param tipoResolucion the tipoResolucion to set
     */
    public void setTipoResolucion(String tipoResolucion) {
        this.tipoResolucion = tipoResolucion;
    }
    
}
