/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.ContenidoBean;
import bo.edu.uto.dea.jsf.bean.ContenidoRespuestaBean;
import bo.edu.uto.dea.jsf.bean.EstructuraBean;
import bo.edu.uto.dea.jsf.bean.EstructuraRespuestaBean;
import bo.edu.uto.dea.jsf.bean.IdentificadorRegistroFormularioBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IIdentificadorRegistroFormularioBO {
    List<EstructuraBean> obtenerEstructuraIdIdentificadorRegistroFormulario(IdentificadorRegistroFormularioBean obj);
    List<ContenidoBean> obtenerContenidoIdIdentificadorRegistroFormulario(IdentificadorRegistroFormularioBean obj);
    
    List<EstructuraRespuestaBean> obtenerEstructuraRespuestaIdIdentificadorRegistroFormulario(IdentificadorRegistroFormularioBean obj);
    List<ContenidoRespuestaBean> obtenerContenidoRespuestaIdIdentificadorRegistroFormulario(IdentificadorRegistroFormularioBean obj);
    
    Long actualizarContenidoRespuestaLlenado(ContenidoRespuestaBean obj);
}
