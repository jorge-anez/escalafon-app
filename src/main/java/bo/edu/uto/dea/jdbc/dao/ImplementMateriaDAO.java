/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Facultad;
import bo.edu.uto.dea.jhs.persistence.Materia;
import bo.edu.uto.dea.jhs.persistence.RequiereMateria;
import bo.edu.uto.dea.jhs.persistence.Universidad;
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
public class ImplementMateriaDAO extends HibernateDaoSupport implements IMateriaDAO,Serializable{

    @Override
    public List<Materia> obtenerMaterias(Carrera obj) {
        return getHibernateTemplate().find("from Materia where carrera = ? and estado!= ? order by nombre ",obj,"ELIMINADO");
    }

    @Override
    public Long crearMateria(Materia obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Materia ('" + obj.getSiglaMateria() + "','" + obj.getNombre() +"','"+obj.getEstado()+"',"+obj.getCarrera().getIdCarrera()+ ");", new Long_Crear_Materia_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Materia\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Crear_Materia_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Materia");
            return respuesta;
        }
    }

    @Override
    public Long modificarMateria(Materia obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_Materia (" +obj.getIdMateria()+",'"+ obj.getSiglaMateria() + "','" + obj.getNombre() +"','"+obj.getEstado()+"',"+obj.getCarrera().getIdCarrera()+");", new Long_Modificar_Materia_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_Materia\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Modificar_Materia_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Materia");
            return respuesta;
        }
    }

    @Override
    public List<RequiereMateria> obtenerRequiereMaterias(Materia obj) {
        return getHibernateTemplate().find("from RequiereMateria where materiaByIdMateria = ? ",obj );
    }

    @Override
    public Materia obtenerMateriaByIdMateria(Materia obj) {
        List<Materia> lista=getHibernateTemplate().find("from Materia where idMateria = ?  ",obj.getIdMateria() );
        if( lista.size()>0 ){
            return lista.get(0);
        }
        return null;
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
    public Long eliminarRequiereMateria(RequiereMateria obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Eliminar_RequiereMateria (" + obj.getIdRequiereMateria()+");", new Long_Eliminar_RequiereMateria_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Eliminar_RequiereMateria\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Eliminar_RequiereMateria_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Eliminar_RequiereMateria");
            return respuesta;
        }
    }

    @Override
    public Long crearRequiereMateria(RequiereMateria obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_RequiereMateria (" + obj.getMateriaByIdMateria().getIdMateria() + "," + obj.getMateriaByIdMateriaRequisito().getIdMateria() + ");", new Long_Crear_RequiereMateria_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_RequiereMateria\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Crear_RequiereMateria_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_RequiereMateria");
            return respuesta;
        }
    }
}
