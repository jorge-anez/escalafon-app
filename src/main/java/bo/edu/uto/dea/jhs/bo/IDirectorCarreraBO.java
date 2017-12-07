/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.DirectorCarreraBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IDirectorCarreraBO {
    List<DirectorCarreraBean> obtenerDirectorCarreras(CarreraBean obj);
    DirectorCarreraBean obtenerDirectorCarreraByCi(DirectorCarreraBean obj);
    
    Long modificarDirectorCarrera(DirectorCarreraBean obj);
    Long crearDirectorCarrera(DirectorCarreraBean obj);
}
