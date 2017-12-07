/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.CoordinadorDea;
import bo.edu.uto.dea.jhs.persistence.DirectorCarrera;
import bo.edu.uto.dea.jhs.persistence.DirectorDea;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Estudiante;
import bo.edu.uto.dea.jhs.persistence.Evaluacion;
import bo.edu.uto.dea.jhs.persistence.IdentificadorRegistroMateria;
import bo.edu.uto.dea.jhs.persistence.MiembroComisionEvaluadora;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.RelacionDocenteMateria;
import bo.edu.uto.dea.jhs.persistence.RelacionEstudianteMateria;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author VIIII1
 */
public class ImplementPersonaDAO extends HibernateDaoSupport implements IPersonaDAO,Serializable{
    
    private long cantidadEstructuraBeans;
    private long cantidadContenidoBeans;
    
    private long cantidadEstructuraRespuestaBeans;
    private long cantidadContenidoRespuestaBeans;
    
    private final static String[] tipoContenedores;
    
    static {
        tipoContenedores = new String[7];
        tipoContenedores[0] = "Texto";
        tipoContenedores[1] = "Pregunta";
        tipoContenedores[2] = "Columna";
        tipoContenedores[3] = "Opciones";
        tipoContenedores[4] = "Dependencia";
        tipoContenedores[5] = "Grupos_Repetidos";
        tipoContenedores[6] = "Contenedor";
    }

    @Override
    public Persona verificarUsuario(Persona obj) {
        List<Persona> personas=getHibernateTemplate().find("from Persona where cuenta = ? and contrasenia = ? ",
                obj.getCuenta(),obj.getContrasenia());
        if(personas.size()>0){
            return personas.get(0);
        }
        return null;
    }

    @Override
    public Integer tipoUsuario(Persona obj) {
        Integer respuesta=new Integer(0);
        Date fechaActual=new Date();
        Persona p;
        List<Persona> personas=getHibernateTemplate().find("from Persona where cuenta = ? and contrasenia = ? and estado = ? ",
                obj.getCuenta(),obj.getContrasenia(),"ACTIVO");
        if(personas.size()>0){
            respuesta=1;
            p=personas.get(0);
            List<Estudiante> estudiantes=getHibernateTemplate().find("from Estudiante where ci = ? and estado = ? ",
                p.getCi(),"ACTIVO" );
            if(estudiantes.size()>0){
                respuesta=respuesta+3;
            }
            List<Docente> docentes=getHibernateTemplate().find("from Docente where ci = ? and estado = ? ",
                p.getCi(),"ACTIVO" );
            if(docentes.size()>0){
                respuesta=respuesta+9;
            }
            List<DirectorCarrera> directorCarreras=getHibernateTemplate().find("from DirectorCarrera where ci = ? and fechaInicio <= ? and fechaFin >= ? and estado = ? ",
                p.getCi(),fechaActual,fechaActual,"ACTIVO" );
            if(directorCarreras.size()>0){
                respuesta=respuesta+27;
            }
            List<MiembroComisionEvaluadora> miembroComsionEvaluadoras=getHibernateTemplate().find("from MiembroComisionEvaluadora where ci = ? and fechaInicio <= ? and fechaFin >= ? and estado = ? ",
                p.getCi(),fechaActual,fechaActual,"ACTIVO" );
            if(miembroComsionEvaluadoras.size()>0){
                respuesta=respuesta+81;
            }
            List<CoordinadorDea> coordinadorDeas=getHibernateTemplate().find("from CoordinadorDea where ci = ? and fechaInicio <= ? and fechaFin >= ?and estado = ?  ",
                p.getCi(),fechaActual,fechaActual,"ACTIVO" );
            if(coordinadorDeas.size()>0){
                respuesta=respuesta+243;
            }
            List<DirectorDea> directorDea=getHibernateTemplate().find("from DirectorDea where ci = ? and fechaInicio <= ? and fechaFin >= ? and estado = ? ",
                p.getCi(),fechaActual,fechaActual,"ACTIVO" );
            if(directorDea.size()>0){
                respuesta=respuesta+729;
            }
        }
        return respuesta;
    }

    @Override
    public List<Persona> obtenerPersonas() {
        return getHibernateTemplate().find("from Persona where estado!= ? ","ELIMINADO");
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

    /**
     * @return the cantidadEstructuraBeans
     */
    public long getCantidadEstructuraBeans() {
        return cantidadEstructuraBeans;
    }

    /**
     * @param cantidadEstructuraBeans the cantidadEstructuraBeans to set
     */
    public void setCantidadEstructuraBeans(long cantidadEstructuraBeans) {
        this.cantidadEstructuraBeans = cantidadEstructuraBeans;
    }

    /**
     * @return the cantidadContenidoBeans
     */
    public long getCantidadContenidoBeans() {
        return cantidadContenidoBeans;
    }

    /**
     * @param cantidadContenidoBeans the cantidadContenidoBeans to set
     */
    public void setCantidadContenidoBeans(long cantidadContenidoBeans) {
        this.cantidadContenidoBeans = cantidadContenidoBeans;
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
    public Long modificarPersona(Persona obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_Persona ('" + obj.getCi() + "','" + obj.getNombre() +"','"+obj.getApp()+"','"+obj.getApm()+"','"+obj.getCuenta()+"','"+obj.getContrasenia()+"','"+obj.getEstado()+ "');", new Long_Modificar_Persona_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_Persona\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Modificar_Persona_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Persona");
            return respuesta;
        }
    }

    
    
    @Override
    public Long verificarAsignacionFormularioEvaluacionREM(RelacionEstudianteMateria obj) {
        //return getHibernateTemplate().find("from ComisionEvaluadora where carrera = ? and estado!= ? ",obj,"ELIMINADO");
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta=new Long(0);
        try {
            List<Long> list = jdbcTemplate.query("select * from verificarAsignacionFormularioEvaluacionREM (" + obj.getIdRelacionEstudianteMateria() +");", new Long_verificarAsignacionFormularioEvaluacionREM_Mapper());
            if( list.size()>0 ){
                respuesta=list.get(0);
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception verificarAsignacionFormularioEvaluacionREM\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_verificarAsignacionFormularioEvaluacionREM_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta=new Long(0);
            respuesta=rs.getLong("verificarAsignacionFormularioEvaluacionREM");
            return respuesta;
        }
    }
    
    private class Long_Crear_Identificador_Registro_Formulario_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta=new Long(0);
            respuesta=rs.getLong("Crear_Identificador_Registro_Formulario");
            return respuesta;
        }
    }

    
    
    private class Long_Agregar_Estructura_Respuesta_Padre_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta=new Long(0);
            respuesta=rs.getLong("Agregar_Estructura_Respuesta_Padre");
            return respuesta;
        }
    }

        
    private class Long_Crear_Contenido_Respuesta_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta=new Long(0);
            respuesta=rs.getLong("Crear_Contenido_Respuesta");
            return respuesta;
        }
    }

        
    private class Long_Agregar_Estructura_Respuesta_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta=new Long(0);
            respuesta=rs.getLong("Agregar_Estructura_Respuesta");
            return respuesta;
        }
    }

    @Override
    public Evaluacion obtenerEvaluacionByIdEvaluacion(Evaluacion obj) {
        List<Evaluacion> evaluacions=getHibernateTemplate().find("from Evaluacion where idEvaluacion = ? ", obj.getIdEvaluacion() );
        if( evaluacions.size()>0 ){
            return evaluacions.get(0);
        }
        return null;
    }

    @Override
    public Long verificarAsignacionFormularioEvaluacionRDM(RelacionDocenteMateria obj) {
        //return getHibernateTemplate().find("from ComisionEvaluadora where carrera = ? and estado!= ? ",obj,"ELIMINADO");
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta=new Long(0);
        try {
            List<Long> list = jdbcTemplate.query("select * from VerificarAsignacionFormularioEvaluacionRDM (" + obj.getIdRelacionDocenteMateria() +");", new Long_VerificarAsignacionFormularioEvaluacionRDM_Mapper());
            if( list.size()>0 ){
                respuesta=list.get(0);
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception VerificarAsignacionFormularioEvaluacionRDM\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_VerificarAsignacionFormularioEvaluacionRDM_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta=new Long(0);
            respuesta=rs.getLong("VerificarAsignacionFormularioEvaluacionRDM");
            return respuesta;
        }
    }

    @Override
    public RelacionDocenteMateria obtenerRelacionDocenteMateriaById(RelacionDocenteMateria obj) {
        List<RelacionDocenteMateria> list=getHibernateTemplate().find("from RelacionDocenteMateria where idRelacionDocenteMateria = ? ",obj.getIdRelacionDocenteMateria());
        if( list.size()>0 ){
            return list.get(0);
        }
        return null;
    }

    @Override
    public DirectorCarrera obtenerDirectorCarreraByCI(DirectorCarrera obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<DirectorCarrera> directorCarreras = jdbcTemplate.query("select * from Obtener_Director_Carrera_By_CI ('" + obj.getDocente().getCi() +"');", new DirectorCarrera_Mapper());
            if( directorCarreras.size()>0 ){
                return directorCarreras.get(0);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Obtener_Director_Carrera_By_CI\n" + e.toString());
        }
        return null;
    }
    
    private class DirectorCarrera_Mapper implements ParameterizedRowMapper<DirectorCarrera> {

        @Override
        public DirectorCarrera mapRow(ResultSet rs, int i) throws SQLException {
            DirectorCarrera r=new DirectorCarrera();
            r.setIdDirectorCarrera( rs.getLong("id_Director_Carrera") );
            Docente d=new Docente();
            d.setCi(rs.getString("ci"));
            r.setDocente(d );
            Carrera c=new Carrera();
            c.setIdCarrera( rs.getLong("id_carrera") );
            r.setCarrera(c );
            r.setGestion(rs.getInt("gestion") );
            r.setFechaInicio(rs.getDate("fecha_inicio") );
            r.setFechaFin(rs.getDate("fecha_fin") );
            r.setEstado(rs.getString("estado") );
            return r;
        }
    }
    
    public long ObtenerIdDirectorCarreraByCI(DirectorCarrera obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            
            List<Long> list = jdbcTemplate.query("select * from ObtenerIdDirectorCarreraByCI ('" + obj.getDocente().getCi() +"');", new Long_ObtenerIdDirectorCarreraByCI_Mapper());
            if( list.size()>0 ){
                return list.get(0).longValue();
            }
            else{
                return 0;
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception ObtenerIdDirectorCarreraByCI\n" + e.toString());
        }
        return 0;
    }
    private class Long_ObtenerIdDirectorCarreraByCI_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("ObtenerIdDirectorCarreraByCI");
            return respuesta;
        }
    }

    @Override
    public List<Docente> ObtenerDocenteByIdDirectorCarrera(DirectorCarrera obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        long id_diretor_carrera=new Long(0);
        
        try {
            id_diretor_carrera=ObtenerIdDirectorCarreraByCI( obj );
            if(id_diretor_carrera==0)
                return null;
            
            List<Docente> docentes = jdbcTemplate.query("select * from ObtenerDocenteByIdDirectorCarrera (" + id_diretor_carrera +");", new DocenteMapper());
            if( docentes.size()>0 ){
                return docentes;
            }
            else{
                return null;
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception ObtenerDocenteByIdDirectorCarrera\n" + e.toString());
        }
        return null;
    }
    private class DocenteMapper implements ParameterizedRowMapper<Docente> {

        @Override
        public Docente mapRow(ResultSet rs, int i) throws SQLException {
            Docente r=new Docente();
            r.setCi(rs.getString("ci"));
            r.setGradoAcademico(rs.getString("grado_academico"));
            r.setGradoAcademico(rs.getString("grado_academico_abreviatura"));
            r.setEstado(rs.getString("estado"));
            return r;
        }
        
    }
    
}
