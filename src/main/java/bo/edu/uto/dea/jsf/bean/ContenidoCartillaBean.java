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
public class ContenidoCartillaBean implements Serializable {
    private long idContenidoCartilla;
    private EvaluacionBean evaluacionBean;
    private long idCartilla;
    private int cargaHoraria;
    private double puntajeEvaluacion;
    private double puntajeParcial;

    /**
     * @return the idContenidoCartilla
     */
    public long getIdContenidoCartilla() {
        return idContenidoCartilla;
    }

    /**
     * @param idContenidoCartilla the idContenidoCartilla to set
     */
    public void setIdContenidoCartilla(long idContenidoCartilla) {
        this.idContenidoCartilla = idContenidoCartilla;
    }

    /**
     * @return the evaluacionBean
     */
    public EvaluacionBean getEvaluacionBean() {
        return evaluacionBean;
    }

    /**
     * @param evaluacionBean the evaluacionBean to set
     */
    public void setEvaluacionBean(EvaluacionBean evaluacionBean) {
        this.evaluacionBean = evaluacionBean;
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
     * @return the cargaHoraria
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @param cargaHoraria the cargaHoraria to set
     */
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * @return the puntajeEvaluacion
     */
    public double getPuntajeEvaluacion() {
        return puntajeEvaluacion;
    }

    /**
     * @param puntajeEvaluacion the puntajeEvaluacion to set
     */
    public void setPuntajeEvaluacion(double puntajeEvaluacion) {
        this.puntajeEvaluacion = puntajeEvaluacion;
    }

    /**
     * @return the puntajeParcial
     */
    public double getPuntajeParcial() {
        return puntajeParcial;
    }

    /**
     * @param puntajeParcial the puntajeParcial to set
     */
    public void setPuntajeParcial(double puntajeParcial) {
        this.puntajeParcial = puntajeParcial;
    }
     
}
