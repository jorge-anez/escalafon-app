/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.UniversidadBean;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IUniversidadBO {
    List<UniversidadBean> obtenerUniversidads();
    Long crearUniversidad(UniversidadBean obj);
    Long modificarUniversidad(UniversidadBean obj);
}
