/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.DirectorDea;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Persona;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class ImplementDirectorDeaDAO extends HibernateDaoSupport implements IDirectorDeaDAO,Serializable{

    @Override
    public List<DirectorDea> obtenerDirectorDeas() {
        return getHibernateTemplate().find("from DirectorDea where estado!= ? order by ci ","ELIMINADO");
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
    public Long modificarDirectorDea(DirectorDea obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_Director_DEA (" +obj.getIdDirectorDea()+",'"+ obj.getDocente().getCi() + "','" + simpleDateFormat.format(obj.getFechaInicio()) +"','"+simpleDateFormat.format(obj.getFechaFin())+"','"+obj.getEstado()+ "');", new Long_Modificar_Director_DEA_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_Director_DEA\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Modificar_Director_DEA_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Director_DEA");
            return respuesta;
        }
    }

    @Override
    public Long crearDirectorDea(DirectorDea obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Director_DEA ('" + obj.getDocente().getCi() + "','" + simpleDateFormat.format(obj.getFechaInicio()) +"','"+simpleDateFormat.format(obj.getFechaFin())+"','"+obj.getEstado()+ "');", new Long_Crear_Director_DEA_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Director_DEA\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Crear_Director_DEA_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Director_DEA");
            return respuesta;
        }
    }

    @Override
    public Long crearDocente(Docente obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Docente ('" + obj.getCi() + "','" + obj.getGradoAcademico() +"','"+obj.getGradoAcademicoAbreviatura()+"','"+obj.getEstado()+ "');", new Long_Crear_Docente_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Docente\n" + e.toString());
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
    }
    

    @Override
    public Long crearPersona(Persona obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Persona ('" + obj.getCi() + "','" + obj.getNombre() +"','"+obj.getApp()+"','"+obj.getApm()+"','"+obj.getCuenta()+"','"+obj.getContrasenia()+"','"+obj.getEstado()+ "');", new Long_Crear_Persona_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Persona\n" + e.toString());
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
    }

    @Override
    public DirectorDea obtenerDirectorDeaByCi(DirectorDea obj) {
        //List<DirectorDea> lista=getHibernateTemplate().find("from DirectorDea where ci = ? ",obj.getDocente().getCi() );
        List<DirectorDea> lista=getHibernateTemplate().find("from DirectorDea where docente = ? ",obj.getDocente() );
        if( lista.size()>0 ){
            return lista.get(0);
        }
        return null;
    }
    
}
