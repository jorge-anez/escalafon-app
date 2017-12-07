/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jhs.persistence.Estructura;
import bo.edu.uto.dea.jsf.bean.ContenidoBean;
import bo.edu.uto.dea.jsf.bean.EstructuraBean;
import bo.edu.uto.dea.jsf.bean.FormularioBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IFormularioBO {
    List<FormularioBean> obtenerFormularios();
    Long crearFormulario(FormularioBean obj);
    Long modificarFormulario(FormularioBean obj);
    
    List<EstructuraBean> obtenerEstructurasByIdFormulario(FormularioBean obj);
    List<ContenidoBean> obtenerContenidosByIdFormulario(FormularioBean obj);
    
    FormularioBean obtenerFormularioByIdFormulario(FormularioBean obj);
    Long agregarEstructura(EstructuraBean estructuraBeanRef, EstructuraBean estructuraBean, String tipoAdicion);
    Long eliminarEstructura(EstructuraBean estructuraBeanRef);
    
    Long agregarOpcion(ContenidoBean obj);
    Long eliminarOpcion(ContenidoBean obj);
    Long actualizarContenido(ContenidoBean obj);
    
    Long asignarCategoriaContenido(ContenidoBean obj);
}
