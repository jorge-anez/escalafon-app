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
public class EvaluacionBean implements Serializable{
    private long idEvaluacion;
    private long idRelacionDocenteMateria;
    private Double nota;

    /**
     * @return the idEvaluacion
     */
    public long getIdEvaluacion() {
        return idEvaluacion;
    }

    /**
     * @param idEvaluacion the idEvaluacion to set
     */
    public void setIdEvaluacion(long idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    /**
     * @return the idRelacionDocenteMateria
     */
    public long getIdRelacionDocenteMateria() {
        return idRelacionDocenteMateria;
    }

    /**
     * @param idRelacionDocenteMateria the idRelacionDocenteMateria to set
     */
    public void setIdRelacionDocenteMateria(long idRelacionDocenteMateria) {
        this.idRelacionDocenteMateria = idRelacionDocenteMateria;
    }

    /**
     * @return the nota
     */
    public Double getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(Double nota) {
        this.nota = nota;
    }
    
}
