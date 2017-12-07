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
public class ContenidoBean implements Serializable{
    private long idContenido;
    private String pregunta;
    private String tipoDato;
    private String valorDefecto;
    private Double notaDefecto;
    private Double notaMaxima;
    private String ponderacion;
    private String formulaVerificacionRespuesta;
    private long idEstructura;
    private long idContenidoPredecesor;
    private long idContenidoCalifica;

    public ContenidoBean() {
        this.idContenido=0;
        this.pregunta="";
        this.tipoDato="";
        this.valorDefecto="";
        this.notaDefecto=0.0;
        this.notaMaxima=0.0;
        this.ponderacion="";
        this.formulaVerificacionRespuesta="";
        this.idEstructura=0;
        this.idContenidoPredecesor='0';
        this.idContenidoCalifica=0;
    }

    /**
     * @return the idContenido
     */
    public long getIdContenido() {
        return idContenido;
    }

    /**
     * @param idContenido the idContenido to set
     */
    public void setIdContenido(long idContenido) {
        this.idContenido = idContenido;
    }

    /**
     * @return the pregunta
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * @return the tipoDato
     */
    public String getTipoDato() {
        return tipoDato;
    }

    /**
     * @param tipoDato the tipoDato to set
     */
    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    /**
     * @return the valorDefecto
     */
    public String getValorDefecto() {
        return valorDefecto;
    }

    /**
     * @param valorDefecto the valorDefecto to set
     */
    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    /**
     * @return the notaDefecto
     */
    public Double getNotaDefecto() {
        return notaDefecto;
    }

    /**
     * @param notaDefecto the notaDefecto to set
     */
    public void setNotaDefecto(Double notaDefecto) {
        this.notaDefecto = notaDefecto;
    }

    /**
     * @return the notaMaxima
     */
    public Double getNotaMaxima() {
        return notaMaxima;
    }

    /**
     * @param notaMaxima the notaMaxima to set
     */
    public void setNotaMaxima(Double notaMaxima) {
        this.notaMaxima = notaMaxima;
    }

    /**
     * @return the ponderacion
     */
    public String getPonderacion() {
        return ponderacion;
    }

    /**
     * @param ponderacion the ponderacion to set
     */
    public void setPonderacion(String ponderacion) {
        this.ponderacion = ponderacion;
    }

    /**
     * @return the formulaVerificacionRespuesta
     */
    public String getFormulaVerificacionRespuesta() {
        return formulaVerificacionRespuesta;
    }

    /**
     * @param formulaVerificacionRespuesta the formulaVerificacionRespuesta to set
     */
    public void setFormulaVerificacionRespuesta(String formulaVerificacionRespuesta) {
        this.formulaVerificacionRespuesta = formulaVerificacionRespuesta;
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
     * @return the idContenidoPredecesor
     */
    public long getIdContenidoPredecesor() {
        return idContenidoPredecesor;
    }

    /**
     * @param idContenidoPredecesor the idContenidoPredecesor to set
     */
    public void setIdContenidoPredecesor(long idContenidoPredecesor) {
        this.idContenidoPredecesor = idContenidoPredecesor;
    }

    /**
     * @return the idContenidoCalifica
     */
    public long getIdContenidoCalifica() {
        return idContenidoCalifica;
    }

    /**
     * @param idContenidoCalifica the idContenidoCalifica to set
     */
    public void setIdContenidoCalifica(long idContenidoCalifica) {
        this.idContenidoCalifica = idContenidoCalifica;
    }
        
}
