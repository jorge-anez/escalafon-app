/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.ComisionEvaluadora;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Facultad;
import bo.edu.uto.dea.jhs.persistence.MiembroComisionEvaluadora;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.Universidad;
import bo.edu.uto.dea.jsf.bean.ComisionEvaluadoraBean;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class ImplementComisionEvaluadoraDAO extends HibernateDaoSupport implements IComisionEvaluadoraDAO,Serializable{

    @Override
    public List<ComisionEvaluadoraBean> obtenerComisionEvaluadoras( Carrera obj ) {
        /*return getHibernateTemplate().find("from ComisionEvaluadora where carrera = ? and estado!= ? ",obj,"ELIMINADO");*/
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<ComisionEvaluadoraBean> comisionEvaluadoraBeans = jdbcTemplate.query("select * from Obtener_comision_evaluadoras (" + obj.getIdCarrera() +");", new Comision_Evaluadora_Mapper());
            return comisionEvaluadoraBeans;
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Obtener_comision_evaluadoras\n" + e.toString());
        }
        return null;
    }
    private class Comision_Evaluadora_Mapper implements ParameterizedRowMapper<ComisionEvaluadoraBean> {

        @Override
        public ComisionEvaluadoraBean mapRow(ResultSet rs, int i) throws SQLException {
            ComisionEvaluadoraBean comisionEvaluadoraBean = new ComisionEvaluadoraBean();
            comisionEvaluadoraBean.setIdComisionEvaluadora(rs.getLong("id_comision_evaluadora"));
            comisionEvaluadoraBean.setIdCarreraBean(rs.getLong("id_carrera"));
            comisionEvaluadoraBean.setIdComisionEvaluadoraEvaluar(rs.getLong("id_comision_evaluadora_evaluar"));
            comisionEvaluadoraBean.setGestion(rs.getInt("gestion"));
            comisionEvaluadoraBean.setFechaInicio(rs.getDate("fecha_inicio"));
            comisionEvaluadoraBean.setFechaFin(rs.getDate("fecha_fin"));
            comisionEvaluadoraBean.setEstado(rs.getString("estado"));
            return comisionEvaluadoraBean;
        }
    }

    @Override
    public ComisionEvaluadoraBean obtenerComisionEvaluadoraByIdComisionEvaluadora(ComisionEvaluadora obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<ComisionEvaluadoraBean> comisionEvaluadoraBeans = jdbcTemplate.query("select * from Obtener_comision_evaluadora_by_id_Comision_Evaluadora (" + obj.getIdComisionEvaluadora() +");", new Comision_Evaluadora_Mapper());
            if( comisionEvaluadoraBeans.size()>0 ){
                return comisionEvaluadoraBeans.get(0);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Obtener_comision_evaluadora_by_id_Comision_Evaluadora\n" + e.toString());
        }
        return null;
    }

    @Override
    public Docente obtenerDocenteByCi(Docente obj) {
        List<Docente> lista=getHibernateTemplate().find("from Docente where ci = ? ",obj.getCi());
        if( lista.size()>0 ){
            return lista.get(0);
        }
        return null;
    }

    @Override
    public Persona obtenerPersonaByCi(Persona obj) {
        List<Persona> lista=getHibernateTemplate().find("from Persona where ci = ? ",obj.getCi());
        if( lista.size()>0 ){
            return lista.get(0);
        }
        return null;
    }

    @Override
    public Long modificarComisionEvaluadora(ComisionEvaluadora obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Long respuesta = new Long(0);
        try {
            long idComisionEvaluadoraEvaluar=obj.getComisionEvaluadora().getIdComisionEvaluadora();
            if( idComisionEvaluadoraEvaluar==0 || idComisionEvaluadoraEvaluar==(-1) ){
                idComisionEvaluadoraEvaluar=-1;
            }
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_comision_evaluadora (" +obj.getIdComisionEvaluadora()+","+ obj.getCarrera().getIdCarrera() + ","+idComisionEvaluadoraEvaluar+","+obj.getGestion()+",'" + simpleDateFormat.format(obj.getFechaInicio()) +"','"+simpleDateFormat.format(obj.getFechaFin())+"','"+obj.getEstado()+ "');", new Long_Modificar_comision_evaluadora_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_comision_evaluadora\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Modificar_comision_evaluadora_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_comision_evaluadora");
            return respuesta;
        }
    }

    @Override
    public Carrera obtenerCarreraByIdCarrera(Carrera obj) {
        List<Carrera> lista=getHibernateTemplate().find("from Carrera where idCarrera = ? ",obj.getIdCarrera() );
        if( lista.size()>0 ){
            return lista.get(0);
        }
        return null;
    }
    
    @Override
    public Facultad obtenerFacultadByIdFacultad(Facultad obj) {
        List<Facultad> lista=getHibernateTemplate().find("from Facultad where idFacultad = ? ",obj.getIdFacultad() );
        if( lista.size()>0 ){
            return lista.get(0);
        }
        return null;
    }

    @Override
    public Universidad obtenerUniversidadBySiglaUniversidad(Universidad obj) {
        List<Universidad> lista=getHibernateTemplate().find("from Universidad where siglaUniversidad = ? ",obj.getSiglaUniversidad() );
        if( lista.size()>0 ){
            return lista.get(0);
        }
        return null;
    }

    @Override
    public Long crearComisionEvaluadora(ComisionEvaluadora obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Long respuesta = new Long(0);
        try {
            long idComisionEvaluadoraEvaluar=obj.getComisionEvaluadora().getIdComisionEvaluadora();
            if( idComisionEvaluadoraEvaluar==0 || idComisionEvaluadoraEvaluar==(-1) ){
                idComisionEvaluadoraEvaluar=-1;
            }
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_comision_evaluadora (" +obj.getCarrera().getIdCarrera()+","+idComisionEvaluadoraEvaluar+","+obj.getGestion()+ ",'" + simpleDateFormat.format(obj.getFechaInicio()) +"','"+simpleDateFormat.format(obj.getFechaFin())+"','"+obj.getEstado()+ "');", new Long_Crear_comision_evaluadora_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_comision_evaluadora\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Crear_comision_evaluadora_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_comision_evaluadora");
            return respuesta;
        }
    }

    /*@Override
    public Long crearDocente(Docente obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Docente ('" + obj.getCi() + "','" + obj.getGradoAcademico() +"','"+obj.getGradoAcademicoAbreviatura()+"','"+obj.getEstado()+ "');", new Long_Crear_Docente_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception Crear_Docente\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Crear_Docente_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Docente");
            return respuesta;
        }
    }*/
    

    /*@Override
    public Long crearPersona(Persona obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Persona ('" + obj.getCi() + "','" + obj.getNombre() +"','"+obj.getApp()+"','"+obj.getApm()+"','"+obj.getCuenta()+"','"+obj.getContrasenia()+"','"+obj.getEstado()+ "');", new Long_Crear_Persona_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception Crear_Persona\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Crear_Persona_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Persona");
            return respuesta;
        }
    }*/

    /*@Override
    public DirectorCarrera obtenerDirectorCarreraByCi(DirectorCarrera obj) {
        //List<DirectorDea> lista=getHibernateTemplate().find("from DirectorCarrera where ci = ? ",obj.getDocente().getCi() );
        List<DirectorCarrera> lista=getHibernateTemplate().find("from DirectorCarrera where docente = ? ",obj.getDocente() );
        if( lista.size()>0 ){
            return lista.get(0);
        }
        return null;
    }*/

    /*@Override
    public Long crearDocente(Docente obj) {
    DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
    JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
    Long respuesta = new Long(0);
    try {
    List<Long> respuestas = jdbcTemplate.query("select * from Crear_Docente ('" + obj.getCi() + "','" + obj.getGradoAcademico() +"','"+obj.getGradoAcademicoAbreviatura()+"','"+obj.getEstado()+ "');", new Long_Crear_Docente_Mapper() );
    respuesta = respuestas.get(0);
    } catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Exception Crear_Docente\n" + e.toString());
    }
    return respuesta;
    }
    private class Long_Crear_Docente_Mapper implements ParameterizedRowMapper<Long> {
    @Override
    public Long mapRow(ResultSet rs, int i) throws SQLException {
    Long respuesta;
    respuesta = rs.getLong("Crear_Docente");
    return respuesta;
    }
    }*/
    /*@Override
    public Long crearPersona(Persona obj) {
    DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
    JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
    Long respuesta = new Long(0);
    try {
    List<Long> respuestas = jdbcTemplate.query("select * from Crear_Persona ('" + obj.getCi() + "','" + obj.getNombre() +"','"+obj.getApp()+"','"+obj.getApm()+"','"+obj.getCuenta()+"','"+obj.getContrasenia()+"','"+obj.getEstado()+ "');", new Long_Crear_Persona_Mapper() );
    respuesta = respuestas.get(0);
    } catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Exception Crear_Persona\n" + e.toString());
    }
    return respuesta;
    }
    private class Long_Crear_Persona_Mapper implements ParameterizedRowMapper<Long> {
    @Override
    public Long mapRow(ResultSet rs, int i) throws SQLException {
    Long respuesta;
    respuesta = rs.getLong("Crear_Persona");
    return respuesta;
    }
    }*/
    /*@Override
    public DirectorCarrera obtenerDirectorCarreraByCi(DirectorCarrera obj) {
    //List<DirectorDea> lista=getHibernateTemplate().find("from DirectorCarrera where ci = ? ",obj.getDocente().getCi() );
    List<DirectorCarrera> lista=getHibernateTemplate().find("from DirectorCarrera where docente = ? ",obj.getDocente() );
    if( lista.size()>0 ){
    return lista.get(0);
    }
    return null;
    }*/
    @Override
    public List<MiembroComisionEvaluadora> obtenerMiembroComisionEvaluadoras(ComisionEvaluadora obj) {
        return getHibernateTemplate().find("from MiembroComisionEvaluadora where comisionEvaluadora = ? ",obj);
    }
    
    @Override
    public Long modificarMiembroComisionEvaluadora(MiembroComisionEvaluadora obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Long respuesta = new Long(0);
        try {
            long idComisionEvaluadoraEvaluar=obj.getComisionEvaluadora().getIdComisionEvaluadora();
            if( idComisionEvaluadoraEvaluar==0 || idComisionEvaluadoraEvaluar==(-1) ){
                idComisionEvaluadoraEvaluar=-1;
            }
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_Miembro_Comision_Evaluadora (" +obj.getIdMiembroComisionEvaluadora()+",'"+ obj.getDocente().getCi()+ "',"+ obj.getComisionEvaluadora().getIdComisionEvaluadora() +",'" + simpleDateFormat.format(obj.getFechaInicio()) +"','"+simpleDateFormat.format(obj.getFechaFin())+"','"+obj.getEstado()+ "');", new Long_Modificar_Miembro_Comision_Evaluadora_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_Miembro_Comision_Evaluadora\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Modificar_Miembro_Comision_Evaluadora_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Miembro_Comision_Evaluadora");
            return respuesta;
        }
    }
    
    @Override
    public Long crearMiembroComisionEvaluadora(MiembroComisionEvaluadora obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Long respuesta = new Long(0);
        try {
            long idComisionEvaluadoraEvaluar=obj.getComisionEvaluadora().getIdComisionEvaluadora();
            if( idComisionEvaluadoraEvaluar==0 || idComisionEvaluadoraEvaluar==(-1) ){
                idComisionEvaluadoraEvaluar=-1;
            }
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Miembro_Comision_Evaluadora ('"+ obj.getDocente().getCi()+ "',"+ obj.getComisionEvaluadora().getIdComisionEvaluadora() +",'" + simpleDateFormat.format(obj.getFechaInicio()) +"','"+simpleDateFormat.format(obj.getFechaFin())+"','"+obj.getEstado()+ "');", new Long_Crear_Miembro_Comision_Evaluadora_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Miembro_Comision_Evaluadora\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Crear_Miembro_Comision_Evaluadora_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Miembro_Comision_Evaluadora");
            return respuesta;
        }
    }
}
