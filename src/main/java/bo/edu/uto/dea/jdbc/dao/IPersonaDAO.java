/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.DirectorCarrera;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Evaluacion;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.RelacionDocenteMateria;
import bo.edu.uto.dea.jhs.persistence.RelacionEstudianteMateria;

import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IPersonaDAO {
    Persona verificarUsuario(Persona obj);
    Integer tipoUsuario(Persona obj);
    
    List<Persona> obtenerPersonas();
    Long crearPersona(Persona obj);
    Long modificarPersona(Persona obj);
    
    Persona obtenerPersonaByCi(Persona obj);
    
    Long verificarAsignacionFormularioEvaluacionREM(RelacionEstudianteMateria obj);
        
    Evaluacion obtenerEvaluacionByIdEvaluacion(Evaluacion obj);
    
    Long verificarAsignacionFormularioEvaluacionRDM(RelacionDocenteMateria obj);
    
    RelacionDocenteMateria obtenerRelacionDocenteMateriaById(RelacionDocenteMateria obj);
    
    DirectorCarrera obtenerDirectorCarreraByCI(DirectorCarrera obj);
    
    List<Docente> ObtenerDocenteByIdDirectorCarrera(DirectorCarrera obj);
}
