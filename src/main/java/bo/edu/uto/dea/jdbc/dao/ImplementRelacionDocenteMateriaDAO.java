/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Estudiante;
import bo.edu.uto.dea.jhs.persistence.Evaluacion;
import bo.edu.uto.dea.jhs.persistence.Materia;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.RelacionDocenteMateria;
import bo.edu.uto.dea.jhs.persistence.RelacionEstudianteMateria;
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
public class ImplementRelacionDocenteMateriaDAO extends HibernateDaoSupport implements IRelacionDocenteMateriaDAO,Serializable{

    @Override
    public List<RelacionDocenteMateria> obtenerRelacionDocenteMaterias(Carrera obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<RelacionDocenteMateria> relacionDocenteMaterias = jdbcTemplate.query("select * from Obtener_Relacion_Docente_Materias ("+ obj.getIdCarrera() +");", new RelacionDocenteMateria_Mapper());
            return relacionDocenteMaterias;
        } catch (Exception e) {
            System.out.println("Exception Obtener_Relacion_Docente_Materias\n" + e.toString());
        }
        return null;
    }
    
    private class RelacionDocenteMateria_Mapper implements ParameterizedRowMapper<RelacionDocenteMateria> {

        @Override
        public RelacionDocenteMateria mapRow(ResultSet rs, int i) throws SQLException {
            RelacionDocenteMateria relacionDocenteMateria = new RelacionDocenteMateria();
            relacionDocenteMateria.setIdRelacionDocenteMateria(rs.getLong("id_Relacion_Docente_Materia"));
            relacionDocenteMateria.setParalelo(rs.getString("paralelo"));
            relacionDocenteMateria.setPeriodo(rs.getString("periodo"));
            relacionDocenteMateria.setTipoPeriodo(rs.getString("tipo_periodo"));
            relacionDocenteMateria.setGestion(rs.getInt("gestion"));
            relacionDocenteMateria.setFechaInicio(rs.getDate("fecha_inicio"));
            relacionDocenteMateria.setFechaFin(rs.getDate("fecha_fin"));
            relacionDocenteMateria.setHoras(rs.getInt("horas"));
            relacionDocenteMateria.setItem(rs.getString("item"));
            relacionDocenteMateria.setTipoMateria(rs.getString("tipo_materia"));
            relacionDocenteMateria.setEstado(rs.getString("estado"));
            
            Docente docente=new Docente();
            docente.setCi(rs.getString("ci"));
            relacionDocenteMateria.setDocente(docente);
            Materia materia=new Materia();
            materia.setIdMateria(rs.getLong("id_materia"));
            relacionDocenteMateria.setMateria(materia);
            
            return relacionDocenteMateria;
        }
    }
    
    @Override
    public Long crearRelacionDocenteMateria(RelacionDocenteMateria obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Relacion_Docente_Materia ('" + obj.getParalelo() + "','" + obj.getPeriodo() +"','"+obj.getTipoPeriodo()+"',"+obj.getGestion()+",'"+simpleDateFormat.format(obj.getFechaInicio())+"','"+simpleDateFormat.format(obj.getFechaFin())+"',"+obj.getHoras()+",'"+obj.getItem()+"','"+obj.getTipoMateria()+"','"+obj.getEstado()+"','"+obj.getDocente().getCi()+"',"+obj.getMateria().getIdMateria()+ ");", new Long_Crear_Relacion_Docente_Materia_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Relacion_Docente_Materia\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Crear_Relacion_Docente_Materia_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Relacion_Docente_Materia");
            return respuesta;
        }
    }
    
    @Override
    public Long modificarRelacionDocenteMateria(RelacionDocenteMateria obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_Relacion_Docente_Materia (" +obj.getIdRelacionDocenteMateria()+",'"+ obj.getParalelo() + "','" + obj.getPeriodo() +"','"+obj.getTipoPeriodo()+"',"+obj.getGestion()+",'"+simpleDateFormat.format(obj.getFechaInicio())+"','"+simpleDateFormat.format(obj.getFechaFin())+"',"+obj.getHoras()+",'"+obj.getItem()+"','"+obj.getTipoMateria()+"','"+obj.getEstado()+"','"+obj.getDocente().getCi()+"',"+obj.getMateria().getIdMateria()+ ");", new Long_Modificar_Relacion_Docente_Materia_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_Relacion_Docente_Materia\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Modificar_Relacion_Docente_Materia_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Relacion_Docente_Materia");
            return respuesta;
        }
    }
    
    @Override
    public List<RelacionEstudianteMateria> obtenerRelacionEstudianteMateriasByIdRelacionDocenteMateria(RelacionDocenteMateria obj) {
        return getHibernateTemplate().find("from RelacionEstudianteMateria where relacionDocenteMateria = ?",obj);
    }

    @Override
    public Evaluacion obtenerEvaluacionByIdRelacionDocenteMateria(RelacionDocenteMateria obj) {
        List<Evaluacion> evaluacions=getHibernateTemplate().find("from Evaluacion where relacionDocenteMateria = ?",obj);
        if( evaluacions.size()>0 ){
            return evaluacions.get(0);
        }
        return null;
    } 

    @Override
    public Evaluacion obtenerEvaluacionByIdEvaluacion(Evaluacion obj) {
        List<Evaluacion> evaluacions=getHibernateTemplate().find("from Evaluacion where idEvaluacion = ? ", obj.getIdEvaluacion() );
        if( evaluacions.size()>0 ){
            return evaluacions.get(0);
        }
        return null;
    }
    
    private class Long_Modificar_Asignar_Formulario_Relacion_Docente_Materia_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Asignar_Formulario_Relacion_Docente_Materia");
            return respuesta;
        }
    } 
    
    private class Long_Asignar_Formulario_Relacion_Docente_Materia_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Asignar_Formulario_Relacion_Docente_Materia");
            return respuesta;
        }
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
    public Docente obtenerDocenteByCi(Docente obj) {
        List<Docente> lista=getHibernateTemplate().find("from Docente where ci = ? ",obj.getCi());
        if( lista.size()>0 ){
            return lista.get(0);
        }
        return null;
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
    public List<Materia> obtenerMaterias(Carrera obj) {
        return getHibernateTemplate().find("from Materia where carrera = ? and estado!= ? order by nombre ",obj,"ELIMINADO");
    }
    

    
    @Override
    public Long crearRelacionEstudianteMateria(RelacionEstudianteMateria obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Relacion_Estudiante_Materia (" + obj.getRelacionDocenteMateria().getIdRelacionDocenteMateria() + ",'" + obj.getEstudiante().getCi()+"');", new Long_Crear_Relacion_Estudiante_Materia_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Relacion_Estudiante_Materia\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Crear_Relacion_Estudiante_Materia_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Relacion_Estudiante_Materia");
            return respuesta;
        }
    }
}
