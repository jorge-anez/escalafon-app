/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.DirectorCarreraBean;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.PersonaBean;
import bo.edu.uto.dea.jsf.bean.RelacionDocenteMateriaBean;
import bo.edu.uto.dea.jsf.bean.RelacionEstudianteMateriaBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IPersonaBO {
    Integer verificarUsuario(PersonaBean obj);
    
    List<PersonaBean> obtenerPersonas();
    Long crearPersona(PersonaBean obj);
    Long modificarPersona(PersonaBean obj);
    
    PersonaBean obtenerPersonaByCi(PersonaBean obj);
    
    Long verificarAsignacionFormularioEvaluacionREM(RelacionEstudianteMateriaBean obj);

    Long verificarAsignacionFormularioEvaluacionRDM(RelacionDocenteMateriaBean obj);
    
    RelacionDocenteMateriaBean obtenerRelacionDocenteMateriaById(RelacionDocenteMateriaBean obj);
    DirectorCarreraBean obtenerDirectorCarreraByCI(DirectorCarreraBean obj);
    
    List<DocenteBean> ObtenerDocenteByIdDirectorCarrera(DirectorCarreraBean obj);
}
