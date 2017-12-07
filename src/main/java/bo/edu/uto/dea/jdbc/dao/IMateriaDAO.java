/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Facultad;
import bo.edu.uto.dea.jhs.persistence.Materia;
import bo.edu.uto.dea.jhs.persistence.RequiereMateria;
import bo.edu.uto.dea.jhs.persistence.Universidad;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IMateriaDAO {
    List<Materia> obtenerMaterias(Carrera obj);
    Long crearMateria(Materia obj);
    Long modificarMateria(Materia obj);
    List<RequiereMateria> obtenerRequiereMaterias(Materia obj);
    
    Materia obtenerMateriaByIdMateria(Materia obj);
    Carrera obtenerCarreraByIdCarrera(Carrera obj);
    Facultad obtenerFacultadByIdFacultad(Facultad obj);
    Universidad obtenerUniversidadBySiglaUniversidad(Universidad obj);
    
    Long eliminarRequiereMateria(RequiereMateria obj);
    Long crearRequiereMateria(RequiereMateria obj);
    
    //Long obtenerFormularios(RequiereMateria obj);
}
