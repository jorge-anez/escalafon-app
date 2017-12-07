/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.CoordinadorDea;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Persona;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author VIIII1
 */
public class ImplementDocenteDAO extends HibernateDaoSupport implements IDocenteDAO,Serializable{

    @Override
    public List<Docente> obtenerDocentes() {
        return getHibernateTemplate().find("from Docente where estado!= ? order by ci ","ELIMINADO");
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
    public Long modificarDocente(Docente obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_Docente ('"+obj.getCi()+"','" + obj.getGradoAcademico() + "','" + obj.getGradoAcademicoAbreviatura() +"','"+obj.getEstado()+ "');", new Long_Modificar_Docente_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_Docente\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Modificar_Docente_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Docente");
            return respuesta;
        }
    }

    @Override
    public Long crearDocente(Docente obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Docente ('"+obj.getCi()+"','" + obj.getGradoAcademico() + "','" + obj.getGradoAcademicoAbreviatura() +"','"+obj.getEstado()+ "');", new Long_Crear_Docente_Mapper() );
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
    
}
