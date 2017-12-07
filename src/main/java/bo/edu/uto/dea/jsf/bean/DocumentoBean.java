/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author VIIII1
 */
public class DocumentoBean implements Serializable{

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private long idDocumento;
    private EvaluacionBean evaluacionBean;
    private FormularioBean formularioBean;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    
    //
    private String linkLlenado;
    private String linkVista;
    
    private boolean showLinkLlenado;
    private boolean showLinkVista;
    
    private final static String[] estados;

    static {
        estados = new String[2];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
    }

    /**
     * @return the idDocumento
     */
    public long getIdDocumento() {
        return idDocumento;
    }

    /**
     * @param idDocumento the idDocumento to set
     */
    public void setIdDocumento(long idDocumento) {
        this.idDocumento = idDocumento;
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
     * @return the formularioBean
     */
    public FormularioBean getFormularioBean() {
        return formularioBean;
    }

    /**
     * @param formularioBean the formularioBean to set
     */
    public void setFormularioBean(FormularioBean formularioBean) {
        this.formularioBean = formularioBean;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the linkVista
     */
    public String getLinkVista() {
        return linkVista;
    }

    /**
     * @param linkVista the linkVista to set
     */
    public void setLinkVista(String linkVista) {
        this.linkVista = linkVista;
    }

    /**
     * @return the linkLlenado
     */
    public String getLinkLlenado() {
        return linkLlenado;
    }

    /**
     * @param linkLlenado the linkLlenado to set
     */
    public void setLinkLlenado(String linkLlenado) {
        this.linkLlenado = linkLlenado;
    }

    /**
     * @return the showLinkLlenado
     */
    public boolean isShowLinkLlenado() {
        return showLinkLlenado;
    }

    /**
     * @param showLinkLlenado the showLinkLlenado to set
     */
    public void setShowLinkLlenado(boolean showLinkLlenado) {
        this.showLinkLlenado = showLinkLlenado;
    }

    /**
     * @return the showLinkVista
     */
    public boolean isShowLinkVista() {
        return showLinkVista;
    }

    /**
     * @param showLinkVista the showLinkVista to set
     */
    public void setShowLinkVista(boolean showLinkVista) {
        this.showLinkVista = showLinkVista;
    }
    
}
