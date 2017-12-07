/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.ContenidoRespuesta;
import bo.edu.uto.dea.jhs.persistence.IdentificadorRegistroFormulario;
import bo.edu.uto.dea.jsf.bean.ContenidoBean;
import bo.edu.uto.dea.jsf.bean.ContenidoRespuestaBean;
import bo.edu.uto.dea.jsf.bean.EstructuraBean;
import bo.edu.uto.dea.jsf.bean.EstructuraRespuestaBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IIdentificadorRegistroFormularioDAO {
    List<EstructuraRespuestaBean> obtenerEstructuraRespuestaIdIdentificadorRegistroFormulario(IdentificadorRegistroFormulario obj);
    List<ContenidoRespuestaBean> obtenerContenidoRespuestaIdIdentificadorRegistroFormulario(IdentificadorRegistroFormulario obj);
    
    List<EstructuraBean> obtenerEstructuraIdIdentificadorRegistroFormulario(IdentificadorRegistroFormulario obj);
    List<ContenidoBean> obtenerContenidoIdIdentificadorRegistroFormulario(IdentificadorRegistroFormulario obj);
    
    Long actualizarContenidoRespuestaLlenado(ContenidoRespuesta obj);
}
