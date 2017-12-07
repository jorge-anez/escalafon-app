/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IIdentificadorRegistroFormularioDAO;
import bo.edu.uto.dea.jhs.persistence.ContenidoRespuesta;
import bo.edu.uto.dea.jhs.persistence.IdentificadorRegistroFormulario;
import bo.edu.uto.dea.jsf.bean.ContenidoBean;
import bo.edu.uto.dea.jsf.bean.ContenidoRespuestaBean;
import bo.edu.uto.dea.jsf.bean.EstructuraBean;
import bo.edu.uto.dea.jsf.bean.EstructuraRespuestaBean;
import bo.edu.uto.dea.jsf.bean.IdentificadorRegistroFormularioBean;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementIdentificadorRegistroFormularioBO implements IIdentificadorRegistroFormularioBO,Serializable{
    private IIdentificadorRegistroFormularioDAO implementIdentificadorRegistroFormularioDAO;

    /**
     * @return the implementIdentificadorRegistroFormularioDAO
     */
    public IIdentificadorRegistroFormularioDAO getImplementIdentificadorRegistroFormularioDAO() {
        return implementIdentificadorRegistroFormularioDAO;
    }

    /**
     * @param implementIdentificadorRegistroFormularioDAO the implementIdentificadorRegistroFormularioDAO to set
     */
    public void setImplementIdentificadorRegistroFormularioDAO(IIdentificadorRegistroFormularioDAO implementIdentificadorRegistroFormularioDAO) {
        this.implementIdentificadorRegistroFormularioDAO = implementIdentificadorRegistroFormularioDAO;
    }
    
    @Override
    public List<EstructuraBean> obtenerEstructuraIdIdentificadorRegistroFormulario(IdentificadorRegistroFormularioBean obj) {
        IdentificadorRegistroFormulario irf=new IdentificadorRegistroFormulario();
        irf.setIdIdentificadorRegistroFormulario(obj.getIdIdentificadorRegistroFormulario());
        return implementIdentificadorRegistroFormularioDAO.obtenerEstructuraIdIdentificadorRegistroFormulario(irf);
    }

    @Override
    public List<ContenidoBean> obtenerContenidoIdIdentificadorRegistroFormulario(IdentificadorRegistroFormularioBean obj) {
        IdentificadorRegistroFormulario irf=new IdentificadorRegistroFormulario();
        irf.setIdIdentificadorRegistroFormulario(obj.getIdIdentificadorRegistroFormulario());
        return implementIdentificadorRegistroFormularioDAO.obtenerContenidoIdIdentificadorRegistroFormulario(irf);
    }
    
    @Override
    public List<EstructuraRespuestaBean> obtenerEstructuraRespuestaIdIdentificadorRegistroFormulario(IdentificadorRegistroFormularioBean obj) {
        IdentificadorRegistroFormulario irf=new IdentificadorRegistroFormulario();
        irf.setIdIdentificadorRegistroFormulario( obj.getIdIdentificadorRegistroFormulario() );
        return implementIdentificadorRegistroFormularioDAO.obtenerEstructuraRespuestaIdIdentificadorRegistroFormulario(irf);
    }

    @Override
    public List<ContenidoRespuestaBean> obtenerContenidoRespuestaIdIdentificadorRegistroFormulario(IdentificadorRegistroFormularioBean obj) {
        IdentificadorRegistroFormulario irf=new IdentificadorRegistroFormulario();
        irf.setIdIdentificadorRegistroFormulario( obj.getIdIdentificadorRegistroFormulario() );
        return implementIdentificadorRegistroFormularioDAO.obtenerContenidoRespuestaIdIdentificadorRegistroFormulario(irf);
    }

    @Override
    public Long actualizarContenidoRespuestaLlenado(ContenidoRespuestaBean obj) {
        Long respuesta=new Long(0);
        ContenidoRespuesta cr=new ContenidoRespuesta();
        cr.setIdContenidoRespuesta( obj.getIdContenidoRespuesta() );
        cr.setRespuesta( obj.getRespuesta() );
        respuesta=implementIdentificadorRegistroFormularioDAO.actualizarContenidoRespuestaLlenado(cr);
        return respuesta;
    }
    
    
}
