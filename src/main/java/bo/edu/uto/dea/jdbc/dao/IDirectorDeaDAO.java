/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.DirectorDea;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Persona;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IDirectorDeaDAO {
    List<DirectorDea> obtenerDirectorDeas();
    Docente obtenerDocenteByCi(Docente obj);
    Persona obtenerPersonaByCi(Persona obj);
    DirectorDea obtenerDirectorDeaByCi(DirectorDea obj);
    
    Long modificarDirectorDea(DirectorDea obj);
    Long crearDirectorDea(DirectorDea obj);
    Long crearDocente(Docente obj);
    Long crearPersona(Persona obj);
    
}
