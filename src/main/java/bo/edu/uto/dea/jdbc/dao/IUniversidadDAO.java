/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Universidad;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IUniversidadDAO {
    List<Universidad> obtenerUniversidads();
    Long crearUniversidad(Universidad obj);
    Long modificarUniversidad(Universidad obj);
}
