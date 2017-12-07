/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class TableRelacionEstudianteMateriaBean implements Serializable{
    private long idRelacionEstudianteMateria;
    private long idRelacionDocenteMateria;
    private String paralelo;
    private String periodo;
    private String tipoPeriodo;
    private int gestion;
    private Date fechaInicio;
    private Date fechaFin;
    private int horas;
    private String item;
    private String tipoMateria;
    private String estado;
    private String ci;
    private String nombreCompleto;
    private long idMateria;
    private String siglaMateria;
    private String nombreMateria;
    
    private List<IdentificadorRegistroFormularioBean> listaIdentificadorRegistroFormularioBeans;
    

    /**
     * @return the idRelacionEstudianteMateria
     */
    public long getIdRelacionEstudianteMateria() {
        return idRelacionEstudianteMateria;
    }

    /**
     * @param idRelacionEstudianteMateria the idRelacionEstudianteMateria to set
     */
    public void setIdRelacionEstudianteMateria(long idRelacionEstudianteMateria) {
        this.idRelacionEstudianteMateria = idRelacionEstudianteMateria;
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
     * @return the paralelo
     */
    public String getParalelo() {
        return paralelo;
    }

    /**
     * @param paralelo the paralelo to set
     */
    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the tipoPeriodo
     */
    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    /**
     * @param tipoPeriodo the tipoPeriodo to set
     */
    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    /**
     * @return the gestion
     */
    public int getGestion() {
        return gestion;
    }

    /**
     * @param gestion the gestion to set
     */
    public void setGestion(int gestion) {
        this.gestion = gestion;
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
     * @return the horas
     */
    public int getHoras() {
        return horas;
    }

    /**
     * @param horas the horas to set
     */
    public void setHoras(int horas) {
        this.horas = horas;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the tipoMateria
     */
    public String getTipoMateria() {
        return tipoMateria;
    }

    /**
     * @param tipoMateria the tipoMateria to set
     */
    public void setTipoMateria(String tipoMateria) {
        this.tipoMateria = tipoMateria;
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
     * @return the ci
     */
    public String getCi() {
        return ci;
    }

    /**
     * @param ci the ci to set
     */
    public void setCi(String ci) {
        this.ci = ci;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the idMateria
     */
    public long getIdMateria() {
        return idMateria;
    }

    /**
     * @param idMateria the idMateria to set
     */
    public void setIdMateria(long idMateria) {
        this.idMateria = idMateria;
    }

    /**
     * @return the siglaMateria
     */
    public String getSiglaMateria() {
        return siglaMateria;
    }

    /**
     * @param siglaMateria the siglaMateria to set
     */
    public void setSiglaMateria(String siglaMateria) {
        this.siglaMateria = siglaMateria;
    }

    /**
     * @return the nombreMateria
     */
    public String getNombreMateria() {
        return nombreMateria;
    }

    /**
     * @param nombreMateria the nombreMateria to set
     */
    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    /**
     * @return the listaIdentificadorRegistroFormularioBeans
     */
    public List<IdentificadorRegistroFormularioBean> getListaIdentificadorRegistroFormularioBeans() {
        return listaIdentificadorRegistroFormularioBeans;
    }

    /**
     * @param listaIdentificadorRegistroFormularioBeans the listaIdentificadorRegistroFormularioBeans to set
     */
    public void setListaIdentificadorRegistroFormularioBeans(List<IdentificadorRegistroFormularioBean> listaIdentificadorRegistroFormularioBeans) {
        this.listaIdentificadorRegistroFormularioBeans = listaIdentificadorRegistroFormularioBeans;
    }
}
