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
public class EstructuraBean implements Serializable {
    private long idEstructura;
    private String tipo;
    private String hijos;
    private String formato;
    private long idFormulario;
    private long idEstructuraPadre;
    private long idEstructuraPredecesor;
    
    public EstructuraBean() {
        this.idEstructura=0;
        this.tipo="Contenedor";
        this.hijos="";
        this.formato="";
        this.idFormulario=0;
        this.idEstructuraPadre=0;
        this.idEstructuraPredecesor=0;
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

    /**
     * @return the idFormulario
     */
    public long getIdFormulario() {
        return idFormulario;
    }

    /**
     * @param idFormulario the idFormulario to set
     */
    public void setIdFormulario(long idFormulario) {
        this.idFormulario = idFormulario;
    }

    /**
     * @return the idEstructuraPadre
     */
    public long getIdEstructuraPadre() {
        return idEstructuraPadre;
    }

    /**
     * @param idEstructuraPadre the idEstructuraPadre to set
     */
    public void setIdEstructuraPadre(long idEstructuraPadre) {
        this.idEstructuraPadre = idEstructuraPadre;
    }

    /**
     * @return the idEstructuraPredecesor
     */
    public long getIdEstructuraPredecesor() {
        return idEstructuraPredecesor;
    }

    /**
     * @param idEstructuraPredecesor the idEstructuraPredecesor to set
     */
    public void setIdEstructuraPredecesor(long idEstructuraPredecesor) {
        this.idEstructuraPredecesor = idEstructuraPredecesor;
    }
}