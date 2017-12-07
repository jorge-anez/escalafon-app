/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IFormularioBO;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VIIII1
 */
public class EstructuraRespuestaBean implements Serializable {
    
    private long idEstructuraRespuesta;
    private long idEstructura;
    private long idIdentificadorRegistroFormulario;
    private long idEstructuraRespuestaByIdEstructuraRespuestaPadre;
    private long idEstructuraRespuestaByIdEstructuraRespuestaPredecesor;
    private String tipo;
    private String hijos;
    private String formato;
    
    public EstructuraRespuestaBean() {
        this.idEstructuraRespuesta=0;
        this.idEstructura=0;
        this.tipo="Contenedor";
        this.hijos="";
        this.formato="";
        this.idIdentificadorRegistroFormulario=0;
        this.idEstructuraRespuestaByIdEstructuraRespuestaPadre=0;
        this.idEstructuraRespuestaByIdEstructuraRespuestaPredecesor=0;
    }  

    /**
     * @return the idEstructuraRespuesta
     */
    public long getIdEstructuraRespuesta() {
        return idEstructuraRespuesta;
    }

    /**
     * @param idEstructuraRespuesta the idEstructuraRespuesta to set
     */
    public void setIdEstructuraRespuesta(long idEstructuraRespuesta) {
        this.idEstructuraRespuesta = idEstructuraRespuesta;
    }

    /**
     * @return the idEstructura
     */
    public long getIdEstructura() {
        return idEstructura;
    }

    /**
     * @param idEstructura the idEstructura to set
     */
    public void setIdEstructura(long idEstructura) {
        this.idEstructura = idEstructura;
    }

    /**
     * @return the idIdentificadorRegistroFormulario
     */
    public long getIdIdentificadorRegistroFormulario() {
        return idIdentificadorRegistroFormulario;
    }

    /**
     * @param idIdentificadorRegistroFormulario the idIdentificadorRegistroFormulario to set
     */
    public void setIdIdentificadorRegistroFormulario(long idIdentificadorRegistroFormulario) {
        this.idIdentificadorRegistroFormulario = idIdentificadorRegistroFormulario;
    }

    /**
     * @return the idEstructuraRespuestaByIdEstructuraRespuestaPadre
     */
    public long getIdEstructuraRespuestaByIdEstructuraRespuestaPadre() {
        return idEstructuraRespuestaByIdEstructuraRespuestaPadre;
    }

    /**
     * @param idEstructuraRespuestaByIdEstructuraRespuestaPadre the idEstructuraRespuestaByIdEstructuraRespuestaPadre to set
     */
    public void setIdEstructuraRespuestaByIdEstructuraRespuestaPadre(long idEstructuraRespuestaByIdEstructuraRespuestaPadre) {
        this.idEstructuraRespuestaByIdEstructuraRespuestaPadre = idEstructuraRespuestaByIdEstructuraRespuestaPadre;
    }

    /**
     * @return the idEstructuraRespuestaByIdEstructuraRespuestaPredecesor
     */
    public long getIdEstructuraRespuestaByIdEstructuraRespuestaPredecesor() {
        return idEstructuraRespuestaByIdEstructuraRespuestaPredecesor;
    }

    /**
     * @param idEstructuraRespuestaByIdEstructuraRespuestaPredecesor the idEstructuraRespuestaByIdEstructuraRespuestaPredecesor to set
     */
    public void setIdEstructuraRespuestaByIdEstructuraRespuestaPredecesor(long idEstructuraRespuestaByIdEstructuraRespuestaPredecesor) {
        this.idEstructuraRespuestaByIdEstructuraRespuestaPredecesor = idEstructuraRespuestaByIdEstructuraRespuestaPredecesor;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the hijos
     */
    public String getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(String hijos) {
        this.hijos = hijos;
    }

    /**
     * @return the formato
     */
    public String getFormato() {
        return formato;
    }

    /**
     * @param formato the formato to set
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }
}