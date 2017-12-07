/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jdbc.dao;

import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Cartilla;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.DocenteEscalafon;
import bo.edu.uto.dea.jhs.persistence.Evaluacion;
import bo.edu.uto.dea.jhs.persistence.EvaluacionEscalafon;
import bo.edu.uto.dea.jhs.persistence.Materia;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.RelacionDocenteMateria;
import bo.edu.uto.dea.jhs.persistence.Resolucion;
import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.ContenidoCartillaViewBean;
import bo.edu.uto.dea.jsf.bean.EscalafonBean;
import bo.edu.uto.dea.jsf.bean.EvaluacionEscalafonBean;
import bo.edu.uto.dea.jsf.bean.FacultadBean;
import bo.edu.uto.dea.jsf.bean.UniversidadBean;
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
public class ImplementDocenteEscalafonDAO extends HibernateDaoSupport implements IDocenteEscalafonDAO,Serializable{

    @Override
    public List<DocenteEscalafon> obtenerDocenteEscalafons( Carrera obj ) {
        return getHibernateTemplate().find("from DocenteEscalafon where carrera = ? ",obj);
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

    @Override
    public Carrera obtenerCarreraByIdCarrera(Carrera obj) {
        List<Carrera> list=getHibernateTemplate().find("from Carrera where idCarrera = ? ",obj.getIdCarrera());
        if( list.size()>0 ){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Long modificarDocenteEscalafon(DocenteEscalafon obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Long respuesta = new Long(0);
        try {
            
            List<Long> respuestas = jdbcTemplate.query("select * from Modificar_Docente_Escalafon ('"+obj.getCi()+"','" + simpleDateFormat.format(obj.getFechaNacimiento())  + "','" + obj.getCorreoElectronico() +"','"+obj.getTipo()+"',"+obj.getCarrera().getIdCarrera()+ ");", new Long_Modificar_Docente_Escalafon_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Modificar_Docente_Escalafon\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Modificar_Docente_Escalafon_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Modificar_Docente_Escalafon");
            return respuesta;
        }
    }

    @Override
    public Long crearDocenteEscalafon(DocenteEscalafon obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Long respuesta = new Long(0);
        try {
            
            List<Long> respuestas = jdbcTemplate.query("select * from Crear_Docente_Escalafon ('"+obj.getCi()+"','" + simpleDateFormat.format(obj.getFechaNacimiento())  + "','" + obj.getCorreoElectronico() +"','"+obj.getTipo()+"',"+obj.getCarrera().getIdCarrera()+ ");", new Long_Crear_Docente_Escalafon_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Crear_Docente_Escalafon\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Crear_Docente_Escalafon_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Crear_Docente_Escalafon");
            return respuesta;
        }
    }
    
    @Override
    public List<RelacionDocenteMateria> obtenerRelacionDocenteMateriaCiGestion(DocenteEscalafon obj,long gestion) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<RelacionDocenteMateria> relacionDocenteMaterias = jdbcTemplate.query("select * from obtenerRelacionDocenteMateriaCiGestion ('"+ obj.getCi() +"',"+gestion+");", new RelacionDocenteMateria_Mapper());
            return relacionDocenteMaterias;
        } catch (Exception e) {
            System.out.println("Exception obtenerRelacionDocenteMateriaCiGestion\n" + e.toString());
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
    public Materia obtenerMateriaByIdMateria(Materia obj) {
        List<Materia> lista=getHibernateTemplate().find("from Materia where idMateria = ?  ",obj.getIdMateria() );
        if( lista.size()>0 ){
            return lista.get(0);
        }
        return null;
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
    public Long actualizarNotaEvaluacion(Evaluacion obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Long respuesta = new Long(0);
        try {
            
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Nota_Evaluacion ("+obj.getIdEvaluacion()+"," + obj.getNota()+ ");", new Long_Actualizar_Nota_Evaluacion_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Nota_Evaluacion\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Actualizar_Nota_Evaluacion_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Nota_Evaluacion");
            return respuesta;
        }
    }

    @Override
    public Long Actualizar_Cartilla_Ev_Docente(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Cartilla_Ev_Docente ('"+obj.getDocenteEscalafon().getCi()+"','" + obj.getGestion() + "');", new Long_Actualizar_Cartilla_Ev_Docente_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Cartilla_Ev_Docente\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Actualizar_Cartilla_Ev_Docente_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Cartilla_Ev_Docente");
            return respuesta;
        }
    }
    
    @Override
    public Long Actualizar_Cartilla_Ev_Contratado(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Cartilla_Ev_Contratado ('"+obj.getDocenteEscalafon().getCi()+"','" + obj.getGestion() + "');", new Long_Actualizar_Cartilla_Ev_Contratado_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Cartilla_Ev_Contratado\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Actualizar_Cartilla_Ev_Contratado_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Cartilla_Ev_Contratado");
            return respuesta;
        }
    }
    
    @Override
    public Long Actualizar_Cartilla_Ev_Ingreso(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Cartilla_Ev_Ingreso ('"+obj.getDocenteEscalafon().getCi()+"','" + obj.getGestion() + "');", new Long_Actualizar_Cartilla_Ev_Ingreso_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Cartilla_Ev_Ingreso\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Actualizar_Cartilla_Ev_Ingreso_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Cartilla_Ev_Ingreso");
            return respuesta;
        }
    }
    
    @Override
    public Long Actualizar_Cartilla_Ev_Emerito(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Cartilla_Ev_Emerito ('"+obj.getDocenteEscalafon().getCi()+"','" + obj.getGestion() + "');", new Long_Actualizar_Cartilla_Ev_Emerito_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Cartilla_Ev_Emerito\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Actualizar_Cartilla_Ev_Emerito_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Cartilla_Ev_Emerito");
            return respuesta;
        }
    }
    
    @Override
    public Long Actualizar_Cartilla_Ev_Fed_Rep_Nota(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Cartilla_Ev_Fed_Rep_Nota ('"+obj.getDocenteEscalafon().getCi()+"','" + obj.getGestion() + "');", new Long_Actualizar_Cartilla_Ev_Fed_Rep_Nota_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Cartilla_Ev_Fed_Rep_Nota\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Actualizar_Cartilla_Ev_Fed_Rep_Nota_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Cartilla_Ev_Fed_Rep_Nota");
            return respuesta;
        }
    }
    
    @Override
    public Long Actualizar_Cartilla_Ev_Aut_Rep_Nota(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Cartilla_Ev_Aut_Rep_Nota ('"+obj.getDocenteEscalafon().getCi()+"','" + obj.getGestion() + "');", new Long_Actualizar_Cartilla_Ev_Aut_Rep_Nota_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Cartilla_Ev_Aut_Rep_Nota\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Actualizar_Cartilla_Ev_Aut_Rep_Nota_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Cartilla_Ev_Aut_Rep_Nota");
            return respuesta;
        }
    }
    
    @Override
    public Long Actualizar_Cartilla_Ev_Fed_Car_Nota( Cartilla obj1 ,EvaluacionEscalafon obj2 ) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Cartilla_Ev_Fed_Car_Nota ('"+obj1.getDocenteEscalafon().getCi()+"','" + obj1.getGestion() +"','"+obj2.getCargo()+"','"+obj2.getLugar()+"',"+ obj2.getNota() + ");", new Long_Actualizar_Cartilla_Ev_Fed_Car_Nota_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Cartilla_Ev_Fed_Car_Nota\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Actualizar_Cartilla_Ev_Fed_Car_Nota_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Cartilla_Ev_Fed_Car_Nota");
            return respuesta;
        }
    }
    
    @Override
    public Long Actualizar_Cartilla_Ev_Aut_Car_Nota( Cartilla obj1 ,EvaluacionEscalafon obj2 ) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Cartilla_Ev_Aut_Car_Nota ('"+obj1.getDocenteEscalafon().getCi()+"','" + obj1.getGestion() +"','"+obj2.getCargo()+"','"+obj2.getLugar()+"',"+ obj2.getNota() + ");", new Long_Actualizar_Cartilla_Ev_Aut_Car_Nota_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Cartilla_Ev_Aut_Car_Nota\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_Actualizar_Cartilla_Ev_Aut_Car_Nota_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Cartilla_Ev_Aut_Car_Nota");
            return respuesta;
        }
    }
    
    @Override
    public Long verificar_para_ingreso(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from verificar_para_ingreso ('"+obj.getDocenteEscalafon().getCi()+"','" + obj.getGestion() + "');", new Long_verificar_para_ingreso_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception verificar_para_ingreso\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_verificar_para_ingreso_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("verificar_para_ingreso");
            return respuesta;
        }
    }
    
    @Override
    public Long verificar_para_Contratado(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from verificar_para_Contratado ('"+obj.getDocenteEscalafon().getCi()+"','" + obj.getGestion() + "');", new Long_verificar_para_Contratado_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception verificar_para_Contratado\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_verificar_para_Contratado_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("verificar_para_Contratado");
            return respuesta;
        }
    }
    
    @Override
    public Long verificar_para_Docente(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from verificar_para_Docente ('"+obj.getDocenteEscalafon().getCi()+"','" + obj.getGestion() + "');", new Long_verificar_para_Docente_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception verificar_para_Docente\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_verificar_para_Docente_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("verificar_para_Docente");
            return respuesta;
        }
    }
    
    @Override
    public Long actualizar_contenido_Resolucion(Resolucion obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from actualizar_contenido_Resolucion ('"+obj.getCartilla().getIdCartilla()+"','" + obj.getContenido() + "');", new Long_actualizar_contenido_Resolucion_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception actualizar_contenido_Resolucion\n" + e.toString());
        }
        return respuesta;
    }
    
    private class Long_actualizar_contenido_Resolucion_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("actualizar_contenido_Resolucion");
            return respuesta;
        }
    }

    @Override
    public Resolucion obtener_resolucion_by_ci_gestion(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<Resolucion> list = jdbcTemplate.query("select * from obtener_resolucion_by_ci_gestion ('"+ obj.getDocenteEscalafon().getCi()+"','"+obj.getGestion()+"');", new Resolucion_Mapper());
            if( list.size()>0 ){
                return list.get(0);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception obtener_resolucion_by_ci_gestion\n" + e.toString());
        }
        return null;
    }
    
    private class Resolucion_Mapper implements ParameterizedRowMapper<Resolucion> {

        @Override
        public Resolucion mapRow(ResultSet rs, int i) throws SQLException {
            Resolucion r=new Resolucion();
            r.setIdResolucion( rs.getLong("id_Resolucion") );
            r.setContenido(rs.getString("contenido") );
            r.setGestion(rs.getString("gestion") );
            r.setPuntajeEscalafon(rs.getLong("puntaje_escalafon") );
            r.setPuntajeAcumulado(rs.getLong("puntaje_acumulado") );
            r.setCategoria(rs.getString("categoria") );
            r.setTipoResolucion(rs.getString("tipo_resolucion") );
            Cartilla c=new Cartilla();
            c.setIdCartilla(rs.getLong("id_cartilla"));
            r.setCartilla(c );
            return r;
        }
    }

    @Override
    public Cartilla obtener_cartilla_by_ci_gestion(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<Cartilla> list = jdbcTemplate.query("select * from obtener_cartilla_by_ci_gestion ('"+ obj.getDocenteEscalafon().getCi()+"','"+obj.getGestion()+"');", new Cartilla_Mapper());
            if( list.size()>0 ){
                return list.get(0);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception obtener_cartilla_by_ci_gestion\n" + e.toString());
        }
        return null;
    }
    
    private class Cartilla_Mapper implements ParameterizedRowMapper<Cartilla> {

        @Override
        public Cartilla mapRow(ResultSet rs, int i) throws SQLException {
            Cartilla r=new Cartilla();
            r.setIdCartilla(rs.getLong("id_Cartilla"));
            r.setTotalHoras(rs.getDouble("total_horas"));
            r.setTotalParcial(rs.getDouble("total_parcial"));
            r.setNotaPromedio(rs.getDouble("nota_promedio"));
            r.setPuntajeEscalafon(rs.getDouble("puntaje_escalafon"));
            r.setGestion(rs.getString("gestion"));
            DocenteEscalafon de=new DocenteEscalafon();
            de.setCi(rs.getString("ci"));
            r.setDocenteEscalafon(de);
            return r;
        }
    }

    @Override
    public List<ContenidoCartillaViewBean> Obtener_Contenido_Cartilla_Extendido1_Ci_Docente(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<ContenidoCartillaViewBean> list = jdbcTemplate.query("select * from Obtener_Contenido_Cartilla_Extendido1_Ci_Docente ("+ obj.getIdCartilla()+");", new ContenidoCartillaViewBean_Mapper());
            return list;
        } catch (Exception e) {
            System.out.println("Exception Obtener_Contenido_Cartilla_Extendido1_Ci_Docente\n" + e.toString());
        }
        return null;
    }
    
    private class ContenidoCartillaViewBean_Mapper implements ParameterizedRowMapper<ContenidoCartillaViewBean> {

        @Override
        public ContenidoCartillaViewBean mapRow(ResultSet rs, int i) throws SQLException {
            ContenidoCartillaViewBean r=new ContenidoCartillaViewBean();
            r.setIdContenidoCartilla(rs.getLong("id_Contenido_Cartilla"));
            r.setCargaHoraria(rs.getInt("carga_horaria"));
            r.setPuntajeEvaluacion(rs.getDouble("puntaje_evaluacion"));
            r.setPuntajeParcial(rs.getDouble("puntaje_parcial"));
            r.setIdCartilla(rs.getLong("id_Cartilla"));
            r.setIdEvaluacion(rs.getLong("id_Evaluacion"));
            r.setSiglaMateria(rs.getString("sigla_materia"));
            r.setNombreMateria(rs.getString("nombre_materia"));
            r.setParalelo(rs.getString("paralelo"));
            return r;
        }
    }

    @Override
    public List<EscalafonBean> Obtener_Escalafon_by_Ci_gestion(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<EscalafonBean> list = jdbcTemplate.query("select * from Obtener_Escalafon_by_Ci_gestion ('"+ obj.getDocenteEscalafon().getCi()+"','"+obj.getGestion()+"');", new EscalafonBean_Mapper());
            return list;
        } catch (Exception e) {
            System.out.println("Exception Obtener_Escalafon_by_Ci_gestion\n" + e.toString());
        }
        return null;
    }

    @Override
    public EscalafonBean Obtener_Escalafon_by_id_cartilla(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<EscalafonBean> list = jdbcTemplate.query("select * from Obtener_Escalafon_by_id_cartilla ("+ obj.getIdCartilla()+");", new EscalafonBean_Mapper());
            if( list.size()>0 ){
                return list.get(0);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception Obtener_Escalafon_by_id_cartilla\n" + e.toString());
        }
        return null;
    }

    
    private class EscalafonBean_Mapper implements ParameterizedRowMapper<EscalafonBean> {

        @Override
        public EscalafonBean mapRow(ResultSet rs, int i) throws SQLException {
            EscalafonBean r=new EscalafonBean();
            r.setIdEscalafon(rs.getLong("id_Escalafon"));
            r.setPuntaje(rs.getDouble("puntaje"));
            r.setPuntajeAcumulado(rs.getDouble("puntaje_acumulado"));
            r.setCategoria(rs.getString("categoria"));
            r.setGestion(rs.getString("gestion"));
            r.setIdCartilla(rs.getLong("id_Cartilla"));
            r.setCiDocenteEscalafon(rs.getString("ci"));
            return r;
        }
    }

    @Override
    public EvaluacionEscalafonBean Obtener_EvaluacionEscalafon_by_id_cartilla(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<EvaluacionEscalafonBean> list = jdbcTemplate.query("select * from Obtener_EvaluacionEscalafon_by_id_cartilla ("+ obj.getIdCartilla()+");", new EvaluacionEscalafonBean_Mapper());
            if( list.size()>0 ){
                return list.get(0);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception Obtener_EvaluacionEscalafon_by_id_cartilla\n" + e.toString());
        }
        return null;
    }
    
    private class EvaluacionEscalafonBean_Mapper implements ParameterizedRowMapper<EvaluacionEscalafonBean> {

        @Override
        public EvaluacionEscalafonBean mapRow(ResultSet rs, int i) throws SQLException {
            EvaluacionEscalafonBean r=new EvaluacionEscalafonBean();
            r.setIdEvaluacionEscalafon(rs.getLong("id_Evaluacion_Escalafon"));
            r.setTipoEvaluacion(rs.getString("tipo_evaluacion"));
            r.setCargo(rs.getString("cargo"));
            r.setLugar(rs.getString("lugar"));
            r.setNota(rs.getDouble("nota"));
            r.setIdCartilla(rs.getLong("id_cartilla"));
            return r;
        }
    }

    @Override
    public CarreraBean obtener_Carrera_by_ci(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<CarreraBean> list = jdbcTemplate.query("select * from obtener_Carrera_by_ci ('"+ obj.getDocenteEscalafon().getCi()+"');", new CarreraBean_Mapper());
            if( list.size()>0 ){
                return list.get(0);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception obtener_Carrera_by_ci\n" + e.toString());
        }
        return null;
    }
    private class CarreraBean_Mapper implements ParameterizedRowMapper<CarreraBean> {

        @Override
        public CarreraBean mapRow(ResultSet rs, int i) throws SQLException {
            CarreraBean r=new CarreraBean();
            r.setIdCarrera(rs.getLong("id_carrera"));
            r.setSiglaCarrera(rs.getString("sigla_carrera"));
            r.setNombre(rs.getString("nombre"));
            r.setIdFacultad(rs.getLong("id_facultad"));
            r.setEstado(rs.getString("estado"));
            return r;
        }
    }

    @Override
    public FacultadBean obtener_Facultad_by_ci(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<FacultadBean> list = jdbcTemplate.query("select * from obtener_Facultad_by_ci ('"+ obj.getDocenteEscalafon().getCi()+"');", new FacultadBean_Mapper());
            if( list.size()>0 ){
                return list.get(0);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception obtener_Facultad_by_ci\n" + e.toString());
        }
        return null;
    }
    private class FacultadBean_Mapper implements ParameterizedRowMapper<FacultadBean> {

        @Override
        public FacultadBean mapRow(ResultSet rs, int i) throws SQLException {
            FacultadBean r=new FacultadBean();
            r.setIdFacultad(rs.getLong("id_facultad"));
            r.setSiglaFacultad(rs.getString("sigla_facultad"));
            r.setNombre(rs.getString("nombre"));
            r.setSiglaUniversidad(rs.getString("sigla_universidad"));
            r.setEstado(rs.getString("estado"));
            return r;
        }
    }

    @Override
    public UniversidadBean obtener_Universidad_by_ci(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        try {
            List<UniversidadBean> list = jdbcTemplate.query("select * from obtener_Universidad_by_ci ('"+ obj.getDocenteEscalafon().getCi()+"');", new UniversidadBean_Mapper());
            if( list.size()>0 ){
                return list.get(0);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception obtener_Universidad_by_ci\n" + e.toString());
        }
        return null;
    }
    
    private class UniversidadBean_Mapper implements ParameterizedRowMapper<UniversidadBean> {

        @Override
        public UniversidadBean mapRow(ResultSet rs, int i) throws SQLException {
            UniversidadBean r=new UniversidadBean();
            r.setSiglaUniversidad(rs.getString("sigla_universidad"));
            r.setNombre(rs.getString("nombre"));
            r.setEstado(rs.getString("estado"));
            return r;
        }
    }

    @Override
    public Long Actualizar_Cartilla_Refresh(Cartilla obj) {
        DataSource dataSource=SessionFactoryUtils.getDataSource( getSessionFactory() );
        JdbcTemplate jdbcTemplate=new JdbcTemplate( dataSource );
        Long respuesta = new Long(0);
        try {
            List<Long> respuestas = jdbcTemplate.query("select * from Actualizar_Cartilla_Refresh ('"+obj.getDocenteEscalafon().getCi()+"','" + obj.getGestion() + "');", new Long_Actualizar_Cartilla_Refresh_Mapper() );
            respuesta = respuestas.get(0);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, );
            System.out.println("Exception Actualizar_Cartilla_Refresh\n" + e.toString());
        }
        return respuesta;
    }
    private class Long_Actualizar_Cartilla_Refresh_Mapper implements ParameterizedRowMapper<Long> {

        @Override
        public Long mapRow(ResultSet rs, int i) throws SQLException {
            Long respuesta;
            respuesta = rs.getLong("Actualizar_Cartilla_Refresh");
            return respuesta;
        }
    }
    
}
