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
public class IdentificadorRegistroMateriaBean implements Serializable{
    private long idIdentificadorRegistroMateria;

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
}
