/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.FacultadBean;
import bo.edu.uto.dea.jsf.bean.UniversidadBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IFacultadBO {
    List<FacultadBean> obtenerFacultads(UniversidadBean obj);
    Long crearFacultad(FacultadBean obj);
    Long modificarFacultad(FacultadBean obj);
}
