/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.CartillaBean;
import bo.edu.uto.dea.jsf.bean.ContenidoCartillaViewBean;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.DocenteEscalafonBean;
import bo.edu.uto.dea.jsf.bean.EscalafonBean;
import bo.edu.uto.dea.jsf.bean.EvaluacionBean;
import bo.edu.uto.dea.jsf.bean.EvaluacionEscalafonBean;
import bo.edu.uto.dea.jsf.bean.FacultadBean;
import bo.edu.uto.dea.jsf.bean.RelacionDocenteMateriaBean;
import bo.edu.uto.dea.jsf.bean.ResolucionBean;
import bo.edu.uto.dea.jsf.bean.UniversidadBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IDocenteEscalafonBO {
    List<DocenteEscalafonBean> obtenerDocenteEscalafons(CarreraBean object);
    DocenteBean obtenerDocenteByCi(DocenteBean obj);
    
    Long modificarDocente(DocenteBean obj);
    Long crearDocente(DocenteBean obj);
    
    Long modificarDocenteEscalafon(DocenteEscalafonBean obj);
    Long crearDocenteEscalafon(DocenteEscalafonBean obj);
    
    List<RelacionDocenteMateriaBean> obtenerRelacionDocenteMateriaCiGestion(DocenteEscalafonBean obj, long gestion);
    
    Long actualizarNotaEvaluacion(EvaluacionBean obj);
    
    Long Actualizar_Cartilla_Ev_Docente(CartillaBean obj);
    Long Actualizar_Cartilla_Ev_Contratado(CartillaBean obj);
    Long Actualizar_Cartilla_Ev_Ingreso(CartillaBean obj);
    Long Actualizar_Cartilla_Ev_Emerito(CartillaBean obj);
    Long Actualizar_Cartilla_Ev_Fed_Rep_Nota(CartillaBean obj);
    Long Actualizar_Cartilla_Ev_Aut_Rep_Nota(CartillaBean obj);

    Long Actualizar_Cartilla_Ev_Fed_Car_Nota(CartillaBean obj1, EvaluacionEscalafonBean obj2);
    Long Actualizar_Cartilla_Ev_Aut_Car_Nota(CartillaBean obj1, EvaluacionEscalafonBean obj2);
    
    ResolucionBean obtener_resolucion_by_ci_gestion(CartillaBean obj);
    Long actualizar_contenido_Resolucion(ResolucionBean obj);
    
    CartillaBean obtener_cartilla_by_ci_gestion(CartillaBean obj);
    List<ContenidoCartillaViewBean> Obtener_Contenido_Cartilla_Extendido1_Ci_Docente(CartillaBean obj);
    List<EscalafonBean> Obtener_Escalafon_by_Ci_gestion(CartillaBean obj);
    EscalafonBean Obtener_Escalafon_by_id_cartilla(CartillaBean obj);
    EvaluacionEscalafonBean Obtener_EvaluacionEscalafon_by_id_cartilla(CartillaBean obj);
    
    CarreraBean obtener_Carrera_by_ci(CartillaBean obj);
    FacultadBean obtener_Facultad_by_ci(CartillaBean obj);
    UniversidadBean obtener_Universidad_by_ci(CartillaBean obj);
    
    Long Actualizar_Cartilla_Refresh(CartillaBean obj);
}