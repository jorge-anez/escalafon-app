/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.DirectorDeaBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IDirectorDeaBO {
    List<DirectorDeaBean> obtenerDirectorDeas();
    DirectorDeaBean obtenerDirectorDeaByCi(DirectorDeaBean obj);
    
    Long modificarDirectorDea(DirectorDeaBean obj);
    Long crearDirectorDea(DirectorDeaBean obj);
}
