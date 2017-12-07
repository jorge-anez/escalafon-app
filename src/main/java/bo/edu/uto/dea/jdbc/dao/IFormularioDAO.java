/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Contenido;
import bo.edu.uto.dea.jhs.persistence.Estructura;
import bo.edu.uto.dea.jhs.persistence.Formulario;
import bo.edu.uto.dea.jsf.bean.ContenidoBean;
import bo.edu.uto.dea.jsf.bean.EstructuraBean;
import bo.edu.uto.dea.jsf.bean.FormularioBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IFormularioDAO {
    List<FormularioBean> obtenerFormularios();
    Long crearFormulario(Formulario obj);
    Long modificarFormulario(Formulario obj);
    
    List<EstructuraBean> obtenerEstructurasByIdFormulario(FormularioBean obj);
    List<ContenidoBean> obtenerContenidosByIdFormulario(FormularioBean obj);
    
    Formulario obtenerFormularioByIdFormulario(Formulario obj);
    Long agregarEstructura(Estructura estructuraRef, Estructura estructura, String tipoAdicion);
    Long eliminarEstructura(Estructura estructuraRef);
    Long agregarOpcion(Contenido obj);
    Long eliminarOpcion(Contenido obj);
    Long actualizarContenido(Contenido obj);
    
    Long asignarCategoriaContenido(Contenido obj);
}
