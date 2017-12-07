/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.ComisionEvaluadora;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Facultad;
import bo.edu.uto.dea.jhs.persistence.MiembroComisionEvaluadora;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.Universidad;
import bo.edu.uto.dea.jsf.bean.ComisionEvaluadoraBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IComisionEvaluadoraDAO {
    List<ComisionEvaluadoraBean> obtenerComisionEvaluadoras(Carrera obj);
    Docente obtenerDocenteByCi(Docente obj);
    Persona obtenerPersonaByCi(Persona obj);
    ComisionEvaluadoraBean obtenerComisionEvaluadoraByIdComisionEvaluadora(ComisionEvaluadora obj);
    
    Long modificarComisionEvaluadora(ComisionEvaluadora obj);
    Long crearComisionEvaluadora(ComisionEvaluadora obj);
    //Long crearDocente(Docente obj);
    //Long crearPersona(Persona obj);
    
    Carrera obtenerCarreraByIdCarrera(Carrera obj);
    Facultad obtenerFacultadByIdFacultad(Facultad obj);
    Universidad obtenerUniversidadBySiglaUniversidad(Universidad obj);
    
    List<MiembroComisionEvaluadora> obtenerMiembroComisionEvaluadoras(ComisionEvaluadora obj);
    Long modificarMiembroComisionEvaluadora(MiembroComisionEvaluadora obj);
    
    Long crearMiembroComisionEvaluadora(MiembroComisionEvaluadora obj);
}
