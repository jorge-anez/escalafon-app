/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.DirectorCarrera;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Persona;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IDirectorCarreraDAO {
    List<DirectorCarrera> obtenerDirectorCarreras(Carrera obj);
    Docente obtenerDocenteByCi(Docente obj);
    Persona obtenerPersonaByCi(Persona obj);
    DirectorCarrera obtenerDirectorCarreraByCi(DirectorCarrera obj);
    
    Long modificarDirectorCarrera(DirectorCarrera obj);
    Long crearDirectorCarrera(DirectorCarrera obj);
    Long crearDocente(Docente obj);
    Long crearPersona(Persona obj);
    
    Carrera obtenerCarreraById(Carrera obj);
}
