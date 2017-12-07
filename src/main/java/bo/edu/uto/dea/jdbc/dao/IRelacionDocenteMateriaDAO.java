/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Estudiante;
import bo.edu.uto.dea.jhs.persistence.Evaluacion;
import bo.edu.uto.dea.jhs.persistence.Materia;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.RelacionDocenteMateria;
import bo.edu.uto.dea.jhs.persistence.RelacionEstudianteMateria;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IRelacionDocenteMateriaDAO {
    List<RelacionDocenteMateria> obtenerRelacionDocenteMaterias(Carrera obj);
    Materia obtenerMateriaByIdMateria(Materia obj);
    Docente obtenerDocenteByCi(Docente obj);
    Estudiante obtenerEstudianteByCi(Estudiante obj);
    Persona obtenerPersonaByCi(Persona obj);
    Evaluacion obtenerEvaluacionByIdRelacionDocenteMateria(RelacionDocenteMateria obj);
    
    List<Materia> obtenerMaterias(Carrera obj);
    Long crearRelacionDocenteMateria(RelacionDocenteMateria obj);
    Long modificarRelacionDocenteMateria(RelacionDocenteMateria obj);
    
    List<RelacionEstudianteMateria> obtenerRelacionEstudianteMateriasByIdRelacionDocenteMateria(RelacionDocenteMateria obj);
    Long crearRelacionEstudianteMateria(RelacionEstudianteMateria obj);
    
    Evaluacion obtenerEvaluacionByIdEvaluacion(Evaluacion obj);
    
}
