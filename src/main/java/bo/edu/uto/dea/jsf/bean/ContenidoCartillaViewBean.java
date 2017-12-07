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
public class ContenidoCartillaViewBean implements Serializable {
    private long idContenidoCartilla;
    private int cargaHoraria;
    private double puntajeEvaluacion;
    private double puntajeParcial;
    private long idCartilla;
    private long idEvaluacion;
    
    private String siglaMateria;
    private String nombreMateria;
    private String paralelo;

    public long getIdContenidoCartilla() {
        return idContenidoCartilla;
    }

    public void setIdContenidoCartilla(long idContenidoCartilla) {
        this.idContenidoCartilla = idContenidoCartilla;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public double getPuntajeEvaluacion() {
        return puntajeEvaluacion;
    }

    public void setPuntajeEvaluacion(double puntajeEvaluacion) {
        this.puntajeEvaluacion = puntajeEvaluacion;
    }

    public double getPuntajeParcial() {
        return puntajeParcial;
    }

    public void setPuntajeParcial(double puntajeParcial) {
        this.puntajeParcial = puntajeParcial;
    }

    public long getIdCartilla() {
        return idCartilla;
    }

    public void setIdCartilla(long idCartilla) {
        this.idCartilla = idCartilla;
    }

    public long getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(long idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getSiglaMateria() {
        return siglaMateria;
    }

    public void setSiglaMateria(String siglaMateria) {
        this.siglaMateria = siglaMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }
    
    
}
