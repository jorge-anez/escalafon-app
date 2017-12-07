/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.EstudianteBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IEstudianteBO {
    List<EstudianteBean> obtenerEstudiantes();
    EstudianteBean obtenerEstudianteByCi(EstudianteBean obj);
    
    Long modificarEstudiante(EstudianteBean obj);
    Long crearEstudiante(EstudianteBean obj);
}
