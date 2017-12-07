/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.FacultadBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface ICarreraBO {
    List<CarreraBean> obtenerCarreras(FacultadBean obj);
    Long crearCarrera(CarreraBean obj);
    Long modificarCarrera(CarreraBean obj);
}
