/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.EstudianteBean;
import bo.edu.uto.dea.jsf.bean.MateriaBean;
import bo.edu.uto.dea.jsf.bean.RelacionDocenteMateriaBean;
import bo.edu.uto.dea.jsf.bean.RelacionEstudianteMateriaBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IRelacionDocenteMateriaBO {
    List<RelacionDocenteMateriaBean> obtenerRelacionDocenteMaterias(CarreraBean obj);
    List<MateriaBean> obtenerMaterias(CarreraBean obj);
    DocenteBean obtenerDocenteByCi(DocenteBean obj);
    Long crearRelacionDocenteMateria(RelacionDocenteMateriaBean obj);
    Long modificarRelacionDocenteMateria(RelacionDocenteMateriaBean obj);
    
    List<RelacionEstudianteMateriaBean> obtenerRelacionEstudianteMateriasByIdRelacionDocenteMateria(RelacionDocenteMateriaBean obj);
    Long crearRelacionEstudianteMateria(RelacionEstudianteMateriaBean obj);
        
    EstudianteBean obtenerEstudianteByCi(EstudianteBean obj);
}
