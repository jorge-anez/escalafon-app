/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Facultad;
import bo.edu.uto.dea.jhs.persistence.Universidad;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IFacultadDAO {
    List<Facultad> obtenerFacultads(Universidad obj);
    Long crearFacultad(Facultad obj);
    Long modificarFacultad(Facultad obj);
}
