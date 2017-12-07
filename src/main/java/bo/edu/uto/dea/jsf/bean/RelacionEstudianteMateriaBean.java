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
public class RelacionEstudianteMateriaBean implements Serializable{
    private long idRelacionEstudianteMateria;
     private long idRelacionDocenteMateria;
     private EstudianteBean estudianteBean;
     private long idIdentificadorRegistroMateria;

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
     * @return the idIdentificadorRegistroMateria
     */
    public long getIdIdentificadorRegistroMateria() {
        return idIdentificadorRegistroMateria;
    }

    /**
     * @param idIdentificadorRegistroMateria the idIdentificadorRegistroMateria to set
     */
    public void setIdIdentificadorRegistroMateria(long idIdentificadorRegistroMateria) {
        this.idIdentificadorRegistroMateria = idIdentificadorRegistroMateria;
    }

    /**
     * @return the estudianteBean
     */
    public EstudianteBean getEstudianteBean() {
        return estudianteBean;
    }

    /**
     * @param estudianteBean the estudianteBean to set
     */
    public void setEstudianteBean(EstudianteBean estudianteBean) {
        this.estudianteBean = estudianteBean;
    }
     
     
}
