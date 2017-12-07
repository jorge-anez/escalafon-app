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
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author VIIII1
 */
public class ImplementIdentificadorRegistroFormularioDAO extends HibernateDaoSupport implements IIdentificadorRegistroFormularioDAO,Serializable{
    @Override
    public List<EstructuraRespuestaBean> obtenerEstructuraRespuestaIdIdentificadorRegistroFormulario(IdentificadorRegistroFormulario obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<EstructuraRespuestaBean> estructuraRespuestaBeans = jdbcTemplate.query("select * from Obtener_EstructuraRespuestaIdIdentificadorRegistroFormulario (" + obj.getIdIdentificadorRegistroFormulario()+ ");", new Estructura_Respuesta_Mapper());
            if (estructuraRespuestaBeans.size() > 0) {
                return estructuraRespuestaBeans;
            } else {
                return null;
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Obtener_EstructuraRespuestaIdIdentificadorRegistroFormulario\n" + e.toString());
        }
        return null;
    }
    private class Estructura_Respuesta_Mapper implements ParameterizedRowMapper<EstructuraRespuestaBean> {

        @Override
        public EstructuraRespuestaBean mapRow(ResultSet rs, int i) throws SQLException {
            EstructuraRespuestaBean erb=new EstructuraRespuestaBean();
            erb.setIdEstructuraRespuesta(rs.getLong("id_Estructura_Respuesta"));
            erb.setTipo(rs.getString("tipo"));
            erb.setHijos(rs.getString("hijos"));
            erb.setFormato(rs.getString("formato"));
            erb.setIdEstructura(rs.getLong("id_Estructura"));
            erb.setIdEstructuraRespuestaByIdEstructuraRespuestaPadre(rs.getLong("id_Estructura_Respuesta_Padre"));
            erb.setIdEstructuraRespuestaByIdEstructuraRespuestaPredecesor(rs.getLong("id_Estructura_Respuesta_Predecesor"));
            erb.setIdIdentificadorRegistroFormulario(rs.getLong("id_Identificador_Registro_Formulario"));
            return erb;
        }
    }
    
    @Override
    public List<ContenidoRespuestaBean> obtenerContenidoRespuestaIdIdentificadorRegistroFormulario(IdentificadorRegistroFormulario obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<ContenidoRespuestaBean> contenidoRespuestaBeans = jdbcTemplate.query("select * from Obtener_ContenidoRespuestaIdIdentificadorRegistroFormulario (" + obj.getIdIdentificadorRegistroFormulario()+ ");", new Contenido_Respuesta_Mapper());
            if (contenidoRespuestaBeans.size() > 0) {
                return contenidoRespuestaBeans;
            } else {
                return null;
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Obtener_ContenidoRespuestaIdIdentificadorRegistroFormulario\n" + e.toString());
        }
        return null;
    }
    private class Contenido_Respuesta_Mapper implements ParameterizedRowMapper<ContenidoRespuestaBean> {

        @Override
        public ContenidoRespuestaBean mapRow(ResultSet rs, int i) throws SQLException {
            ContenidoRespuestaBean crb=new ContenidoRespuestaBean();
            crb.setIdContenidoRespuesta(rs.getLong("id_contenido_respuesta"));
            crb.setRespuesta(rs.getString("respuesta"));
            crb.setNota(rs.getDouble("nota"));
            crb.setCalificable(rs.getDouble("calificable"));
            crb.setRespuestaValida(rs.getDouble("respuesta_valida"));
            crb.setIdContenido(rs.getLong("id_contenido"));
            crb.setIdEstructuraRespuesta(rs.getLong("id_estructura_respuesta"));
            crb.setIdContenidoRespuestaPredecesor(rs.getLong("id_contenido_respuesta_predecesor"));
            crb.setIdContenidoRespuestaCalifica(rs.getLong("id_contenido_respuesta_califica"));
            return crb;
        }
    }
    
    @Override
    public List<EstructuraBean> obtenerEstructuraIdIdentificadorRegistroFormulario(IdentificadorRegistroFormulario obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<EstructuraBean> estructuraBeans = jdbcTemplate.query("select * from obtenerEstructuraIdIdentificadorRegistroFormulario (" + obj.getIdIdentificadorRegistroFormulario()+ ");", new Estructura_Mapper());
            if (estructuraBeans.size() > 0) {
                return estructuraBeans;
            } else {
                return null;
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception obtenerEstructuraIdIdentificadorRegistroFormulario\n" + e.toString());
        }
        return null;
    }
    private class Estructura_Mapper implements ParameterizedRowMapper<EstructuraBean> {

        @Override
        public EstructuraBean mapRow(ResultSet rs, int i) throws SQLException {
            EstructuraBean estructuraBean = new EstructuraBean();
            estructuraBean.setIdEstructura(rs.getLong("id_Estructura"));
            estructuraBean.setTipo(rs.getString("tipo"));
            estructuraBean.setHijos(rs.getString("hijos"));
            estructuraBean.setFormato(rs.getString("formato"));
            estructuraBean.setIdFormulario(rs.getLong("id_Formulario"));
            estructuraBean.setIdEstructuraPadre(rs.getLong("id_Estructura_Padre"));
            estructuraBean.setIdEstructuraPredecesor(rs.getLong("id_Estructura_Predecesor"));
            return estructuraBean;
        }
    }
    
    @Override
    public List<ContenidoBean> obtenerContenidoIdIdentificadorRegistroFormulario(IdentificadorRegistroFormulario obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<ContenidoBean> contenidoBeans = jdbcTemplate.query("select * from obtenerContenidoIdIdentificadorRegistroFormulario (" + obj.getIdIdentificadorRegistroFormulario()+ ");", new Contenido_Mapper());
            if (contenidoBeans.size() > 0) {
                return contenidoBeans;
            } else {
                return null;
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception obtenerContenidoIdIdentificadorRegistroFormulario\n" + e.toString());
        }
        return null;
    }
    
    private class Contenido_Mapper implements ParameterizedRowMapper<ContenidoBean> {

        @Override
        public ContenidoBean mapRow(ResultSet rs, int i) throws SQLException {
            ContenidoBean contenidoBean=new ContenidoBean();
            contenidoBean.setIdContenido(rs.getLong("id_Contenido"));
            contenidoBean.setPregunta(rs.getString("Pregunta"));
            contenidoBean.setTipoDato(rs.getString("tipo_dato"));
            contenidoBean.setValorDefecto(rs.getString("valor_defecto"));
            contenidoBean.setNotaDefecto(rs.getDouble("nota_defecto"));
            contenidoBean.setNotaMaxima(rs.getDouble("nota_maxima"));
            contenidoBean.setPonderacion(rs.getString("ponderacion"));
            contenidoBean.setFormulaVerificacionRespuesta(rs.getString("formula_verificacion_respuesta"));
            contenidoBean.setIdEstructura(rs.getLong("id_Estructura"));
            contenidoBean.setIdContenidoPredecesor(rs.getLong("id_Contenido_Predecesor"));
            contenidoBean.setIdContenidoCalifica(rs.getLong("id_Contenido_califica"));
            return contenidoBean;
        }
    }

    @Override
    public Long actualizarContenidoRespuestaLlenado(ContenidoRespuesta obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Contenido_Respuesta_Llenado (" + obj.getIdContenidoRespuesta() + ",'" + obj.getRespuesta()+ "');", new Long_Actualizar_Contenido_Respuesta_Llenado_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Contenido_Respuesta_Llenado\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Actualizar_Contenido_Respuesta_Llenado_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Contenido_Respuesta_Llenado");
            return respuesta;
        }
    }
}
