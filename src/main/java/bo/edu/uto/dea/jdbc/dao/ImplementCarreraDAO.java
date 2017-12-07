/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Facultad;
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
public class ImplementCarreraDAO extends HibernateDaoSupport implements ICarreraDAO,Serializable{

    @Override
    public List<Carrera> obtenerCarreras(Facultad obj) {        
        return getHibernateTemplate().find("from Carrera where facultad = ? and estado!= ? order by nombre ",obj,"ELIMINADO");
    }

    @Override
    public Long crearCarrera(Carrera obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Carrera ('" + obj.getSiglaCarrera() + "','" + obj.getNombre() +"',"+obj.getFacultad().getIdFacultad()+",'"+obj.getEstado()+ "');", new Long_Crear_Carrera_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Carrera\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Crear_Carrera_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Carrera");
            return respuesta;
        }
    }

    @Override
    public Long modificarCarrera(Carrera obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_Carrera ("+obj.getIdCarrera()+",'"+ obj.getSiglaCarrera() + "','" + obj.getNombre()+"',"+obj.getFacultad().getIdFacultad() +",'"+obj.getEstado()+ "');", new Long_Modificar_Carrera_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_Carrera\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Modificar_Carrera_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Carrera");
            return respuesta;
        }
    }
}
