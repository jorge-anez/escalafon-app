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
public class ContenidoRespuestaBean implements Serializable{
    
    private long idContenidoRespuesta;
    private long idContenidoRespuestaCalifica;
    private long idContenidoRespuestaPredecesor;
    private long idEstructuraRespuesta;
    private long idContenido;
    private String respuesta;
    private Double nota;
    private Double calificable;
    private Double respuestaValida;

    public ContenidoRespuestaBean() {
        this.idContenidoRespuesta=0;
        this.idContenidoRespuestaCalifica=0;
        this.idContenidoRespuestaPredecesor=0;
        this.idEstructuraRespuesta=0;
        this.idContenido=0;
        this.respuesta="";
        this.nota=0.0;
        this.calificable=0.0;
        this.respuestaValida=0.0;
    }        

    /**
     * @return the idContenidoRespuesta
     */
    public long getIdContenidoRespuesta() {
        return idContenidoRespuesta;
    }

    /**
     * @param idContenidoRespuesta the idContenidoRespuesta to set
     */
    public void setIdContenidoRespuesta(long idContenidoRespuesta) {
        this.idContenidoRespuesta = idContenidoRespuesta;
    }

    /**
     * @return the idContenidoRespuestaCalifica
     */
    public long getIdContenidoRespuestaCalifica() {
        return idContenidoRespuestaCalifica;
    }

    /**
     * @param idContenidoRespuestaCalifica the idContenidoRespuestaCalifica to set
     */
    public void setIdContenidoRespuestaCalifica(long idContenidoRespuestaCalifica) {
        this.idContenidoRespuestaCalifica = idContenidoRespuestaCalifica;
    }

    /**
     * @return the idContenidoRespuestaPredecesor
     */
    public long getIdContenidoRespuestaPredecesor() {
        return idContenidoRespuestaPredecesor;
    }

    /**
     * @param idContenidoRespuestaPredecesor the idContenidoRespuestaPredecesor to set
     */
    public void setIdContenidoRespuestaPredecesor(long idContenidoRespuestaPredecesor) {
        this.idContenidoRespuestaPredecesor = idContenidoRespuestaPredecesor;
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
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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

    /**
     * @return the calificable
     */
    public Double getCalificable() {
        return calificable;
    }

    /**
     * @param calificable the calificable to set
     */
    public void setCalificable(Double calificable) {
        this.calificable = calificable;
    }

    /**
     * @return the respuestaValida
     */
    public Double getRespuestaValida() {
        return respuestaValida;
    }

    /**
     * @param respuestaValida the respuestaValida to set
     */
    public void setRespuestaValida(Double respuestaValida) {
        this.respuestaValida = respuestaValida;
    }
}
