/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.CoordinadorDeaBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface ICoordinadorDeaBO {
    List<CoordinadorDeaBean> obtenerCoordinadorDeas();
    CoordinadorDeaBean obtenerCoordinadorDeaByCi(CoordinadorDeaBean obj);
    
    Long modificarCoordinadorDea(CoordinadorDeaBean obj);
    Long crearCoordinadorDea(CoordinadorDeaBean obj);
}
