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
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author VIIII1
 */
public class ImplementFormularioDAO extends HibernateDaoSupport implements IFormularioDAO,Serializable{

    @Override
    public List<FormularioBean> obtenerFormularios() {
        //return getHibernateTemplate().find("from Formulario where eliminado= ? order by nombre ",false );
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<FormularioBean> formularioBeans = jdbcTemplate.query("select * from Obtener_Formularios ();", new Formulario_Mapper());
            return formularioBeans;
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Obtener_Formularios\n" + e.toString());
        }
        return null;
    }
    
    private class Formulario_Mapper implements ParameterizedRowMapper<FormularioBean> {

        @Override
        public FormularioBean mapRow(ResultSet rs, int i) throws SQLException {
            FormularioBean formularioBean=new FormularioBean();
            formularioBean.setIdFormulario(rs.getLong("id_Formulario"));
            formularioBean.setNombre(rs.getString("nombre"));
            formularioBean.setObservaciones(rs.getString("observaciones"));
            formularioBean.setCategoria(rs.getString("categoria"));
            formularioBean.setDirigido(rs.getString("dirigido"));
            formularioBean.setEliminado(rs.getBoolean("eliminado"));
            formularioBean.setAprobado(rs.getBoolean("aprobado"));
            return formularioBean;
        }
    }

    @Override
    public Formulario obtenerFormularioByIdFormulario(Formulario obj) {
        List<Formulario> formularios=getHibernateTemplate().find("from Formulario where idFormulario = ? ",obj.getIdFormulario() );
        if( formularios.size()>0 ){
            return formularios.get(0);
        }
        return null;
        /*DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<FormularioBean> estructuraBeans = jdbcTemplate.query("select * from Obtener_Formulario_By_Id_Formulario (" + obj.getIdFormulario()+ ");", new Formulario_Mapper());
            if (estructuraBeans.size() > 0) {
                return estructuraBeans.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception Obtener_Formulario_By_Id_Formulario\n" + e.toString());
        }
        return null;*/
    }
    

    @Override
    public Long crearFormulario( Formulario obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Formulario ('" + obj.getNombre() + "','" + obj.getObservaciones() +"','"+obj.getCategoria()+"','"+obj.getDirigido()+"',"+obj.isEliminado()+","+obj.isAprobado()+ ");", new Long_Crear_Formulario_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Formulario\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Crear_Formulario_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Formulario");
            return respuesta;
        }
        
    }


    @Override
    public Long modificarFormulario(Formulario obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_Formulario (" +obj.getIdFormulario()+",'"+ obj.getNombre() + "','" + obj.getObservaciones() +"','"+obj.getCategoria()+"','"+obj.getDirigido()+"',"+obj.isEliminado()+","+obj.isAprobado()+ ");", new Long_Modificar_Formulario_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_Formulario\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Modificar_Formulario_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Formulario");
            return respuesta;
        }
    }
    
    @Override
    public List<EstructuraBean> obtenerEstructurasByIdFormulario(FormularioBean obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<EstructuraBean> estructuraBeans = jdbcTemplate.query("select * from Obtener_Estructura_Id_Formulario (" + obj.getIdFormulario()+ ");", new Estructura_Mapper());
            if (estructuraBeans.size() > 0) {
                return estructuraBeans;
            } else {
                return null;
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Obtener_Estructura_Id_Formulario\n" + e.toString());
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
    public List<ContenidoBean> obtenerContenidosByIdFormulario(FormularioBean obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<ContenidoBean> contenidoBeans = jdbcTemplate.query("select * from Obtener_Contenido_Id_Formulario (" + obj.getIdFormulario()+ ");", new Contenido_Mapper());
            if (contenidoBeans.size() > 0) {
                return contenidoBeans;
            } else {
                return null;
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Obtener_Contenido_Id_Formulario\n" + e.toString());
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
    public Long agregarEstructura(Estructura estructuraRef, Estructura estructura, String tipoAdicion) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Agregar_Estructura (" +estructuraRef.getIdEstructura()+",'"+ estructura.getTipo() + "','" + estructura.getFormato() +"','"+tipoAdicion+"');", new Long_Agregar_Estructura_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Agregar_Estructura\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Agregar_Estructura_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Agregar_Estructura");
            return respuesta;
        }
    }

    @Override
    public Long eliminarEstructura(Estructura estructuraRef) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Eliminar_Estructura (" +estructuraRef.getIdEstructura()+");", new Long_Eliminar_Estructura_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Eliminar_Estructura\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Eliminar_Estructura_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Eliminar_Estructura");
            return respuesta;
        }
    }

    @Override
    public Long agregarOpcion(Contenido obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Agregar_Opcion ('" +obj.getPregunta()+"','"+obj.getTipoDato()+"','"+obj.getValorDefecto()+"',"+obj.getNotaDefecto()+","+obj.getNotaMaxima()+",'"+obj.getPonderacion()+"','"+obj.getFormulaVerificacionRespuesta()+"',"+obj.getEstructura().getIdEstructura()+");", new Long_Agregar_Opcion_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Exception Agregar_Opcion\n" + e.toString());
            System.out.println("Agregar_Opcion: "+e.toString());
        }
        
        return respuesta;
    }
    
    private class Long_Agregar_Opcion_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Agregar_Opcion");
            return respuesta;
        }
    }

    @Override
    public Long eliminarOpcion(Contenido obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Eliminar_Opcion (" +obj.getIdContenido()+");", new Long_Eliminar_Opcion_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Exception Eliminar_Opcion\n" + e.toString());
            System.out.println("Exception Eliminar_Opcion\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Eliminar_Opcion_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Eliminar_Opcion");
            return respuesta;
        }
    }

    @Override
    public Long actualizarContenido(Contenido obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Contenido (" +obj.getIdContenido()+",'"+obj.getPregunta()+"','"+obj.getTipoDato()+"','"+obj.getValorDefecto()+"',"+obj.getNotaDefecto()+","+obj.getNotaMaxima()+",'"+obj.getPonderacion()+"','"+obj.getFormulaVerificacionRespuesta()+"',"+obj.getEstructura().getIdEstructura()+","+obj.getContenidoByIdContenidoPredecesor().getIdContenido()+","+obj.getContenidoByIdContenidoCalifica().getIdContenido()+");", new Long_Actualizar_Contenido_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Contenido\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Actualizar_Contenido_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Contenido");
            return respuesta;
        }
    }
    
    @Override
    public Long asignarCategoriaContenido(Contenido obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Asignar_Categoria_Contenido (" +obj.getIdContenido()+","+obj.getContenidoByIdContenidoCalifica().getIdContenido()+");", new Long_Asignar_Categoria_Contenido_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Asignar_Categoria_Contenido\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Asignar_Categoria_Contenido_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Asignar_Categoria_Contenido");
            return respuesta;
        }
    }
}
