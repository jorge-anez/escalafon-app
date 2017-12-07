/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.MateriaBean;
import bo.edu.uto.dea.jsf.bean.RequiereMateriaBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IMateriaBO {
    List<MateriaBean> obtenerMaterias(CarreraBean obj);
    Long crearMateria(MateriaBean obj);
    Long modificarMateria(MateriaBean obj);
    List<RequiereMateriaBean> obtenerRequiereMaterias(MateriaBean obj);
    
    Long eliminarRequiereMateria(RequiereMateriaBean obj);
    Long crearRequiereMateria(RequiereMateriaBean obj);
    
}
