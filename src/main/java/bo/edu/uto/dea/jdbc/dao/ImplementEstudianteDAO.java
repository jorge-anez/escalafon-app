/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Estudiante;
import bo.edu.uto.dea.jhs.persistence.Persona;
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
public class ImplementEstudianteDAO extends HibernateDaoSupport implements IEstudianteDAO,Serializable{

    @Override
    public List<Estudiante> obtenerEstudiantes() {
        return getHibernateTemplate().find("from Estudiante where estado!= ? order by ci ","ELIMINADO");
    }

    @Override
    public Estudiante obtenerEstudianteByCi(Estudiante obj) {
        List<Estudiante> lista=getHibernateTemplate().find("from Estudiante where ci = ? ",obj.getCi());
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
    public Long modificarEstudiante(Estudiante obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_Estudiante ('"+obj.getCi()+"','"+obj.getEstado()+ "');", new Long_Modificar_Estudiante_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_Estudiante\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Modificar_Estudiante_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Estudiante");
            return respuesta;
        }
    }

    @Override
    public Long crearEstudiante(Estudiante obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Estudiante ('"+obj.getCi()+"','"+obj.getEstado()+ "');", new Long_Crear_Estudiante_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Estudiante\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Crear_Estudiante_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Estudiante");
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
