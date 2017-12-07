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
public class MiembroComisionEvaluadoraBean implements Serializable{

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private String ci;
    private DocenteBean docenteBean;
    //private ComisionEvaluadoraBean comisionEvaluadoraBean;
    private long idComisionEvaluadoraBean;
    private long idMiembroComisionEvaluadora;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    
    private final static String[] estados;

    static {
        estados = new String[4];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "FINALIZADO";
        estados[3] = "ELIMINADO";
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
     * @return the docenteBean
     */
    public DocenteBean getDocenteBean() {
        return docenteBean;
    }

    /**
     * @param docenteBean the docenteBean to set
     */
    public void setDocenteBean(DocenteBean docenteBean) {
        this.docenteBean = docenteBean;
    }

    /**
     * @return the idMiembroComisionEvaluadora
     */
    public long getIdMiembroComisionEvaluadora() {
        return idMiembroComisionEvaluadora;
    }

    /**
     * @param idMiembroComisionEvaluadora the idMiembroComisionEvaluadora to set
     */
    public void setIdMiembroComisionEvaluadora(long idMiembroComisionEvaluadora) {
        this.idMiembroComisionEvaluadora = idMiembroComisionEvaluadora;
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
     * @return the idComisionEvaluadoraBean
     */
    public long getIdComisionEvaluadoraBean() {
        return idComisionEvaluadoraBean;
    }

    /**
     * @param idComisionEvaluadoraBean the idComisionEvaluadoraBean to set
     */
    public void setIdComisionEvaluadoraBean(long idComisionEvaluadoraBean) {
        this.idComisionEvaluadoraBean = idComisionEvaluadoraBean;
    }
}
