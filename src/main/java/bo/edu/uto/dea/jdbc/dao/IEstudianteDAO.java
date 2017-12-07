/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Estudiante;
import bo.edu.uto.dea.jhs.persistence.Persona;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IEstudianteDAO {
    List<Estudiante> obtenerEstudiantes();
    Estudiante obtenerEstudianteByCi(Estudiante obj);
    Persona obtenerPersonaByCi(Persona obj);
    
    Long modificarEstudiante(Estudiante obj);
    Long crearEstudiante(Estudiante obj);
    Long crearPersona(Persona obj);
}
