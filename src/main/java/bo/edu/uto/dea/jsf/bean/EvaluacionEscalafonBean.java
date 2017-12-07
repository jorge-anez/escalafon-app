/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.ICarreraBO;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VIIII1
 */
public class EvaluacionEscalafonBean implements Serializable {

    private long idEvaluacionEscalafon;
    private long idCartilla;
    private String tipoEvaluacion;
    private String cargo;
    private String lugar;
    private double nota;

    public long getIdEvaluacionEscalafon() {
        return idEvaluacionEscalafon;
    }

    public void setIdEvaluacionEscalafon(long idEvaluacionEscalafon) {
        this.idEvaluacionEscalafon = idEvaluacionEscalafon;
    }

    public long getIdCartilla() {
        return idCartilla;
    }

    public void setIdCartilla(long idCartilla) {
        this.idCartilla = idCartilla;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
     
}
