/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.CoordinadorDea;
import bo.edu.uto.dea.jhs.persistence.DirectorDea;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Persona;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface ICoordinadorDeaDAO {
    List<CoordinadorDea> obtenerCoordinadorDeas();
    Docente obtenerDocenteByCi(Docente obj);
    Persona obtenerPersonaByCi(Persona obj);
    CoordinadorDea obtenerCoordinadorDeaByCi(CoordinadorDea obj);
    
    Long modificarCoordinadorDea(CoordinadorDea obj);
    Long crearCoordinadorDea(CoordinadorDea obj);
    Long crearDocente(Docente obj);
    Long crearPersona(Persona obj);
}
