/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Universidad;
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
public class ImplementUniversidadDAO extends HibernateDaoSupport implements IUniversidadDAO,Serializable{

    @Override
    public List<Universidad> obtenerUniversidads() {
        return getHibernateTemplate().find("from Universidad where estado!= ? order by nombre ","ELIMINADO");
    }

    @Override
    public Long crearUniversidad( Universidad obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from CrearUniversidad ('" + obj.getSiglaUniversidad() + "','" + obj.getNombre() +"','"+obj.getEstado()+ "');", new Long_CrearUniversidad_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception CrearUniversidad\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_CrearUniversidad_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("CrearUniversidad");
            return respuesta;
        }
        
    }


    @Override
    public Long modificarUniversidad(Universidad obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from modificarUniversidad ('" + obj.getSiglaUniversidad() + "','" + obj.getNombre() +"','"+obj.getEstado()+ "');", new Long_modificarUniversidad_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception modificarUniversidad\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_modificarUniversidad_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("modificarUniversidad");
            return respuesta;
        }
    }

}
