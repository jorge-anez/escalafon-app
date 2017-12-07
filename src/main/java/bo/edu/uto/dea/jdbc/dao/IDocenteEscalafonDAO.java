/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Cartilla;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.DocenteEscalafon;
import bo.edu.uto.dea.jhs.persistence.Evaluacion;
import bo.edu.uto.dea.jhs.persistence.EvaluacionEscalafon;
import bo.edu.uto.dea.jhs.persistence.Materia;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.RelacionDocenteMateria;
import bo.edu.uto.dea.jhs.persistence.Resolucion;
import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.ContenidoCartillaViewBean;
import bo.edu.uto.dea.jsf.bean.EscalafonBean;
import bo.edu.uto.dea.jsf.bean.EvaluacionEscalafonBean;
import bo.edu.uto.dea.jsf.bean.FacultadBean;
import bo.edu.uto.dea.jsf.bean.UniversidadBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IDocenteEscalafonDAO {
    List<DocenteEscalafon> obtenerDocenteEscalafons(Carrera obj);
    Docente obtenerDocenteByCi(Docente obj);
    Persona obtenerPersonaByCi(Persona obj);
    
    Carrera obtenerCarreraByIdCarrera(Carrera obj);
    
    Long modificarDocente(Docente obj);
    Long crearDocente(Docente obj);
    Long crearPersona(Persona obj);
    
    Long modificarDocenteEscalafon(DocenteEscalafon obj);
    Long crearDocenteEscalafon(DocenteEscalafon obj);
    
    List<RelacionDocenteMateria> obtenerRelacionDocenteMateriaCiGestion(DocenteEscalafon obj, long gestion);
    Materia obtenerMateriaByIdMateria(Materia obj);
    Evaluacion obtenerEvaluacionByIdRelacionDocenteMateria(RelacionDocenteMateria obj);
    
    Long actualizarNotaEvaluacion(Evaluacion obj);
    
    Long Actualizar_Cartilla_Ev_Docente(Cartilla obj);
    Long Actualizar_Cartilla_Ev_Contratado(Cartilla obj);
    Long Actualizar_Cartilla_Ev_Ingreso(Cartilla obj);
    Long Actualizar_Cartilla_Ev_Emerito(Cartilla obj);
    Long Actualizar_Cartilla_Ev_Fed_Rep_Nota(Cartilla obj);
    Long Actualizar_Cartilla_Ev_Aut_Rep_Nota(Cartilla obj);

    Long Actualizar_Cartilla_Ev_Fed_Car_Nota(Cartilla obj1, EvaluacionEscalafon obj2);
    Long Actualizar_Cartilla_Ev_Aut_Car_Nota(Cartilla obj1, EvaluacionEscalafon obj2);

    Long verificar_para_ingreso(Cartilla obj);
    Long verificar_para_Contratado(Cartilla obj);
    Long verificar_para_Docente(Cartilla obj);
    
    Long actualizar_contenido_Resolucion(Resolucion obj);
    
    Resolucion obtener_resolucion_by_ci_gestion(Cartilla obj);
    Cartilla obtener_cartilla_by_ci_gestion(Cartilla obj);
    List<ContenidoCartillaViewBean> Obtener_Contenido_Cartilla_Extendido1_Ci_Docente(Cartilla obj);
    
    List<EscalafonBean> Obtener_Escalafon_by_Ci_gestion(Cartilla obj);
    EscalafonBean Obtener_Escalafon_by_id_cartilla(Cartilla obj);
    EvaluacionEscalafonBean Obtener_EvaluacionEscalafon_by_id_cartilla(Cartilla obj);
    
    CarreraBean obtener_Carrera_by_ci(Cartilla obj);
    FacultadBean obtener_Facultad_by_ci(Cartilla obj);
    UniversidadBean obtener_Universidad_by_ci(Cartilla obj);
    
    Long Actualizar_Cartilla_Refresh(Cartilla obj);
}

