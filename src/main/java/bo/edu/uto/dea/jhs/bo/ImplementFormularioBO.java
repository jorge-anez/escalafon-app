/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IFormularioDAO;
import bo.edu.uto.dea.jhs.persistence.Contenido;
import bo.edu.uto.dea.jhs.persistence.Estructura;
import bo.edu.uto.dea.jhs.persistence.Formulario;
import bo.edu.uto.dea.jsf.bean.ContenidoBean;
import bo.edu.uto.dea.jsf.bean.EstructuraBean;
import bo.edu.uto.dea.jsf.bean.FormularioBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementFormularioBO implements IFormularioBO,Serializable{
    private IFormularioDAO implementFormularioDAO;
    
    @Override
    public List<FormularioBean> obtenerFormularios() {
        List<FormularioBean> formularioBeans=new ArrayList<FormularioBean>();
        for( FormularioBean obj: implementFormularioDAO.obtenerFormularios() ){
            FormularioBean formularioBean=new FormularioBean();
            formularioBean.setIdFormulario(obj.getIdFormulario() );
            formularioBean.setNombre(obj.getNombre());
            formularioBean.setObservaciones(obj.getObservaciones());
            formularioBean.setCategoria(obj.getCategoria());
            formularioBean.setDirigido(obj.getDirigido());
            formularioBean.setEliminado(obj.isEliminado());
            formularioBean.setAprobado(obj.isAprobado());
            formularioBeans.add(formularioBean);
        }
        return formularioBeans;
    }

    @Override
    public Long crearFormulario(FormularioBean obj) {
        Long retorno=new Long(0);
        Formulario formulario=new Formulario();
        formulario.setNombre(obj.getNombre());
        formulario.setObservaciones(obj.getObservaciones());
        formulario.setCategoria(obj.getCategoria());
        formulario.setDirigido(obj.getDirigido());
        formulario.setEliminado(obj.isEliminado());
        formulario.setAprobado(obj.isAprobado());
        retorno = implementFormularioDAO.crearFormulario(formulario);
        return retorno;
    }

    @Override
    public Long modificarFormulario(FormularioBean obj) {
        Long retorno=new Long(0);
        Formulario formulario=new Formulario();
        formulario.setIdFormulario(obj.getIdFormulario());
        formulario.setNombre(obj.getNombre());
        formulario.setObservaciones(obj.getObservaciones());
        formulario.setCategoria(obj.getCategoria());
        formulario.setDirigido(obj.getDirigido());
        formulario.setEliminado(obj.isEliminado());
        formulario.setAprobado(obj.isAprobado());
        retorno = implementFormularioDAO.modificarFormulario(formulario);
        return retorno;
    }

    /**
     * @return the implementFormularioDAO
     */
    public IFormularioDAO getImplementFormularioDAO() {
        return implementFormularioDAO;
    }

    /**
     * @param implementFormularioDAO the implementFormularioDAO to set
     */
    public void setImplementFormularioDAO(IFormularioDAO implementFormularioDAO) {
        this.implementFormularioDAO = implementFormularioDAO;
    }

    @Override
    public List<EstructuraBean> obtenerEstructurasByIdFormulario(FormularioBean obj) {
        return implementFormularioDAO.obtenerEstructurasByIdFormulario(obj);
    }

    @Override
    public List<ContenidoBean> obtenerContenidosByIdFormulario(FormularioBean obj) {
        return implementFormularioDAO.obtenerContenidosByIdFormulario(obj);
    }

    @Override
    public FormularioBean obtenerFormularioByIdFormulario(FormularioBean obj) {
        //return implementFormularioDAO.obtenerFormularioByIdFormulario(obj);
        Formulario formulario=new Formulario();
        formulario.setIdFormulario(obj.getIdFormulario());
        formulario=implementFormularioDAO.obtenerFormularioByIdFormulario(formulario);
        if( formulario!=null ){
            FormularioBean formularioBean=new FormularioBean();
            formularioBean.setIdFormulario(formulario.getIdFormulario());
            formularioBean.setNombre(formulario.getNombre());
            formularioBean.setObservaciones(formulario.getObservaciones());
            formularioBean.setCategoria(formulario.getCategoria());
            formularioBean.setDirigido(formulario.getDirigido());
            formularioBean.setEliminado(formulario.isEliminado());
            formularioBean.setAprobado(formulario.isAprobado());
            return formularioBean;
        }
        else{
            return null;
        }
    }

    @Override
    public Long agregarEstructura(EstructuraBean estructuraBeanRef, EstructuraBean estructuraBean, String tipoAdicion) {
        Estructura estructuraRef=new Estructura();
        estructuraRef.setIdEstructura( estructuraBeanRef.getIdEstructura() );
        Estructura estructura=new Estructura();
        estructura.setTipo(estructuraBean.getTipo() );
        estructura.setFormato(estructuraBean.getFormato() );
        return implementFormularioDAO.agregarEstructura(estructuraRef, estructura, tipoAdicion);
    }

    @Override
    public Long eliminarEstructura(EstructuraBean estructuraBeanRef) {
        Estructura estructuraRef=new Estructura();
        estructuraRef.setIdEstructura( estructuraBeanRef.getIdEstructura() );
        return implementFormularioDAO.eliminarEstructura(estructuraRef);
    }

    @Override
    public Long agregarOpcion(ContenidoBean obj) {        
        Contenido contenido=new Contenido();
        contenido.setPregunta( obj.getPregunta() );
        contenido.setTipoDato( obj.getTipoDato() );
        contenido.setValorDefecto( obj.getValorDefecto() );
        contenido.setNotaDefecto( obj.getNotaDefecto() );
        contenido.setNotaMaxima( obj.getNotaMaxima() );
        contenido.setPonderacion( obj.getPonderacion() );
        contenido.setFormulaVerificacionRespuesta( obj.getPregunta() );
        
        Estructura estructura=new Estructura();
        estructura.setIdEstructura( obj.getIdEstructura() );
        contenido.setEstructura(estructura);
        
        System.out.println("fombrea BO "+obj.getPregunta()+","+obj.getTipoDato()+","+obj.getValorDefecto()+","+obj.getNotaDefecto()+","+obj.getPonderacion()+","+obj.getFormulaVerificacionRespuesta()+","+obj.getIdEstructura());
        
        return implementFormularioDAO.agregarOpcion(contenido);
    }

    @Override
    public Long eliminarOpcion(ContenidoBean obj) {
        Contenido contenido=new Contenido();
        contenido.setIdContenido(obj.getIdContenido() );
        
        return implementFormularioDAO.eliminarOpcion(contenido);
    }

    @Override
    public Long actualizarContenido(ContenidoBean obj) {
        Contenido contenido=new Contenido();
        contenido.setIdContenido( obj.getIdContenido() );
        contenido.setPregunta(obj.getPregunta() );
        contenido.setTipoDato(obj.getTipoDato() );
        contenido.setValorDefecto(obj.getValorDefecto() );
        contenido.setNotaDefecto(obj.getNotaDefecto() );
        contenido.setNotaMaxima(obj.getNotaMaxima() );
        contenido.setPonderacion(obj.getPonderacion() );
        contenido.setFormulaVerificacionRespuesta(obj.getFormulaVerificacionRespuesta() );
        
        Estructura estructura=new Estructura();
        estructura.setIdEstructura( obj.getIdEstructura() );
        contenido.setEstructura( estructura );
        
        Contenido contenidoPredecesor=new Contenido();
        contenidoPredecesor.setIdContenido( obj.getIdContenidoPredecesor() );
        contenido.setContenidoByIdContenidoPredecesor(contenidoPredecesor);
        
        Contenido contenidoCalifica=new Contenido();
        contenidoCalifica.setIdContenido( obj.getIdContenidoCalifica() );
        contenido.setContenidoByIdContenidoCalifica( contenidoCalifica );
        
        return implementFormularioDAO.actualizarContenido(contenido);
    }

    @Override
    public Long asignarCategoriaContenido(ContenidoBean obj) {
        Long respuesta=new Long(0);
        Contenido contenido=new Contenido();
        contenido.setIdContenido( obj.getIdContenido() );
        
        Contenido c=new Contenido();
        c.setIdContenido( obj.getIdContenidoCalifica() );
        contenido.setContenidoByIdContenidoCalifica(c);
        
        respuesta = implementFormularioDAO.asignarCategoriaContenido(contenido);
        return respuesta;
    }
}
