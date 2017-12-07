/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Facultad;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface ICarreraDAO {
    List<Carrera> obtenerCarreras(Facultad obj);
    Long crearCarrera(Carrera obj);
    Long modificarCarrera(Carrera obj);
}
