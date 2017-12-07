/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Facultad;
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
public class ImplementFacultadDAO extends HibernateDaoSupport implements IFacultadDAO,Serializable{

    @Override
    public List<Facultad> obtenerFacultads(Universidad obj) {
        return getHibernateTemplate().find("from Facultad where universidad = ? and estado!= ? order by nombre ",obj,"ELIMINADO");
    }

    @Override
    public Long crearFacultad(Facultad obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Facultad ('" + obj.getSiglaFacultad() + "','" + obj.getNombre() +"','"+obj.getUniversidad().getSiglaUniversidad()+"','"+obj.getEstado()+ "');", new Long_Crear_Facultad_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Facultad\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Crear_Facultad_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Facultad");
            return respuesta;
        }
    }

    @Override
    public Long modificarFacultad(Facultad obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_Facultad ("+obj.getIdFacultad()+",'" + obj.getSiglaFacultad() + "','" + obj.getNombre() +"','"+obj.getUniversidad().getSiglaUniversidad()+"','"+obj.getEstado()+ "');", new Long_Modificar_Facultad_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_Facultad\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Modificar_Facultad_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Facultad");
            return respuesta;
        }
    }

}
