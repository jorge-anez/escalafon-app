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
public class RequiereMateriaBean implements Serializable{
    private long idRequiereMateria;
    private long idMateriaByIdMateria;
    private long idMateriaByIdMateriaRequisito;
    
    private String universidadMateriaRequisito;
    private String facultadMateriaRequisito;
    private String carreraMateriaRequisito;
    private String materiaMateriaRequisito;

    /**
     * @return the idRequiereMateria
     */
    public long getIdRequiereMateria() {
        return idRequiereMateria;
    }

    /**
     * @param idRequiereMateria the idRequiereMateria to set
     */
    public void setIdRequiereMateria(long idRequiereMateria) {
        this.idRequiereMateria = idRequiereMateria;
    }

    /**
     * @return the idMateriaByIdMateria
     */
    public long getIdMateriaByIdMateria() {
        return idMateriaByIdMateria;
    }

    /**
     * @param idMateriaByIdMateria the idMateriaByIdMateria to set
     */
    public void setIdMateriaByIdMateria(long idMateriaByIdMateria) {
        this.idMateriaByIdMateria = idMateriaByIdMateria;
    }

    /**
     * @return the idMateriaByIdMateriaRequisito
     */
    public long getIdMateriaByIdMateriaRequisito() {
        return idMateriaByIdMateriaRequisito;
    }

    /**
     * @param idMateriaByIdMateriaRequisito the idMateriaByIdMateriaRequisito to set
     */
    public void setIdMateriaByIdMateriaRequisito(long idMateriaByIdMateriaRequisito) {
        this.idMateriaByIdMateriaRequisito = idMateriaByIdMateriaRequisito;
    }

    /**
     * @return the universidadMateriaRequisito
     */
    public String getUniversidadMateriaRequisito() {
        return universidadMateriaRequisito;
    }

    /**
     * @param universidadMateriaRequisito the universidadMateriaRequisito to set
     */
    public void setUniversidadMateriaRequisito(String universidadMateriaRequisito) {
        this.universidadMateriaRequisito = universidadMateriaRequisito;
    }

    /**
     * @return the facultadMateriaRequisito
     */
    public String getFacultadMateriaRequisito() {
        return facultadMateriaRequisito;
    }

    /**
     * @param facultadMateriaRequisito the facultadMateriaRequisito to set
     */
    public void setFacultadMateriaRequisito(String facultadMateriaRequisito) {
        this.facultadMateriaRequisito = facultadMateriaRequisito;
    }

    /**
     * @return the carreraMateriaRequisito
     */
    public String getCarreraMateriaRequisito() {
        return carreraMateriaRequisito;
    }

    /**
     * @param carreraMateriaRequisito the carreraMateriaRequisito to set
     */
    public void setCarreraMateriaRequisito(String carreraMateriaRequisito) {
        this.carreraMateriaRequisito = carreraMateriaRequisito;
    }

    /**
     * @return the materiaMateriaRequisito
     */
    public String getMateriaMateriaRequisito() {
        return materiaMateriaRequisito;
    }

    /**
     * @param materiaMateriaRequisito the materiaMateriaRequisito to set
     */
    public void setMateriaMateriaRequisito(String materiaMateriaRequisito) {
        this.materiaMateriaRequisito = materiaMateriaRequisito;
    }
    
}
