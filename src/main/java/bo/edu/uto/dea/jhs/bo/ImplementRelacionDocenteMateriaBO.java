/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IRelacionDocenteMateriaDAO;
import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Estudiante;
import bo.edu.uto.dea.jhs.persistence.Evaluacion;
import bo.edu.uto.dea.jhs.persistence.Materia;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.RelacionDocenteMateria;
import bo.edu.uto.dea.jhs.persistence.RelacionEstudianteMateria;
import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.EstudianteBean;
import bo.edu.uto.dea.jsf.bean.EvaluacionBean;
import bo.edu.uto.dea.jsf.bean.MateriaBean;
import bo.edu.uto.dea.jsf.bean.PersonaBean;
import bo.edu.uto.dea.jsf.bean.RelacionDocenteMateriaBean;
import bo.edu.uto.dea.jsf.bean.RelacionEstudianteMateriaBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementRelacionDocenteMateriaBO implements IRelacionDocenteMateriaBO, Serializable {

    private IRelacionDocenteMateriaDAO implementRelacionDocenteMateriaDAO;

    @Override
    public List<RelacionDocenteMateriaBean> obtenerRelacionDocenteMaterias(CarreraBean obj) {
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(obj.getIdCarrera());

        List<RelacionDocenteMateriaBean> relacionDocenteMateriaBeans = new ArrayList<RelacionDocenteMateriaBean>();
        for (RelacionDocenteMateria object : implementRelacionDocenteMateriaDAO.obtenerRelacionDocenteMaterias(carrera)) {
            RelacionDocenteMateriaBean relacionDocenteMateriaBean = new RelacionDocenteMateriaBean();
            relacionDocenteMateriaBean.setIdRelacionDocenteMateria(object.getIdRelacionDocenteMateria());
            relacionDocenteMateriaBean.setParalelo(object.getParalelo());
            relacionDocenteMateriaBean.setPeriodo(object.getPeriodo());
            relacionDocenteMateriaBean.setTipoPeriodo(object.getTipoPeriodo());
            relacionDocenteMateriaBean.setGestion(object.getGestion());
            relacionDocenteMateriaBean.setFechaInicio(object.getFechaInicio());
            relacionDocenteMateriaBean.setFechaFin(object.getFechaFin());
            relacionDocenteMateriaBean.setHoras(object.getHoras());
            relacionDocenteMateriaBean.setItem(object.getItem());
            relacionDocenteMateriaBean.setTipoMateria(object.getTipoMateria());
            relacionDocenteMateriaBean.setEstado(object.getEstado());

            //
            DocenteBean docenteBean = new DocenteBean();
            docenteBean.setCi(object.getDocente().getCi());
            docenteBean.setGradoAcademico("grado academico");
            docenteBean.setGradoAcademicoAbreviatura("grado academico (Abr)");
            docenteBean.setEstado("estado");

            docenteBean.setNombre("nombre");
            docenteBean.setApp("apellido paterno");
            docenteBean.setApm("apellido materno");
            docenteBean.setCuenta("cuenta");
            docenteBean.setContrasenia("password");
            docenteBean.setNombreCompleto("nombre completo");

            Docente docente = new Docente();
            Persona persona1 = new Persona();
            persona1.setCi(object.getDocente().getCi());
            docente.setCi(object.getDocente().getCi());
            docente.setPersona(persona1);
            docente = implementRelacionDocenteMateriaDAO.obtenerDocenteByCi(docente);

            if (docente != null) {
                docenteBean.setGradoAcademico(docente.getGradoAcademico());
                docenteBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());
                docenteBean.setEstado(docente.getEstado());

                Persona persona = new Persona();
                persona.setCi(object.getDocente().getCi());
                persona = implementRelacionDocenteMateriaDAO.obtenerPersonaByCi(persona);
                if (persona != null) {
                    System.out.println("nooooo "+object.getDocente().getCi());
                    docenteBean.setNombre(persona.getNombre());
                    docenteBean.setApp(persona.getApp());
                    docenteBean.setApm(persona.getApm());
                    docenteBean.setCuenta(persona.getCuenta());
                    docenteBean.setContrasenia(persona.getContrasenia());
                    docenteBean.setNombreCompleto( persona.getNombre()+" "+persona.getApp()+" "+persona.getApm() );
                }
            }
            //
            MateriaBean materiaBean=new MateriaBean();
            materiaBean.setEstado("estado");
            materiaBean.setSiglaMateria("sigla materia");
            materiaBean.setNombre("nombre");
            Materia materia=new Materia();
            materia.setIdMateria( object.getMateria().getIdMateria() );
            materia=implementRelacionDocenteMateriaDAO.obtenerMateriaByIdMateria(materia);
            if( materia!=null ){
                materiaBean.setIdMateria(materia.getIdMateria());
                materiaBean.setSiglaMateria(materia.getSiglaMateria());
                materiaBean.setNombre(materia.getNombre());
                materiaBean.setEstado(materia.getEstado());
                materiaBean.setIdCarrera(materia.getCarrera().getIdCarrera());
            }
            //
            EvaluacionBean evaluacionBean=new EvaluacionBean();
            evaluacionBean.setIdEvaluacion(0);
            evaluacionBean.setIdRelacionDocenteMateria( object.getIdRelacionDocenteMateria() );
            evaluacionBean.setNota(0.0);
            Evaluacion evaluacion=implementRelacionDocenteMateriaDAO.obtenerEvaluacionByIdRelacionDocenteMateria(object);object.getIdRelacionDocenteMateria();
            if( evaluacion!=null ){
                evaluacionBean.setIdEvaluacion( evaluacion.getIdEvaluacion() );
                evaluacionBean.setIdRelacionDocenteMateria(evaluacion.getRelacionDocenteMateria().getIdRelacionDocenteMateria() );
                evaluacionBean.setNota(evaluacion.getNota() );
            }
            //

            relacionDocenteMateriaBean.setDocenteBean( docenteBean );
            relacionDocenteMateriaBean.setMateriaBean( materiaBean );
            relacionDocenteMateriaBean.setEvaluacionBean( evaluacionBean );

            relacionDocenteMateriaBeans.add(relacionDocenteMateriaBean);
        }
        return relacionDocenteMateriaBeans;
    }
    
    @Override
    public List<MateriaBean> obtenerMaterias(CarreraBean obj) {
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(obj.getIdCarrera());

        List<MateriaBean> materiaBeans = new ArrayList<MateriaBean>();
        for (Materia object : implementRelacionDocenteMateriaDAO.obtenerMaterias(carrera) ) {
            MateriaBean materiaBean = new MateriaBean();
            materiaBean.setIdMateria(object.getIdMateria());
            materiaBean.setSiglaMateria(object.getSiglaMateria());
            materiaBean.setNombre(object.getNombre());
            materiaBean.setIdCarrera(object.getCarrera().getIdCarrera());
            materiaBean.setEstado(object.getEstado());
            materiaBeans.add(materiaBean);
        }
        return materiaBeans;
    }
    
    @Override
    public DocenteBean obtenerDocenteByCi(DocenteBean obj) {
        DocenteBean docenteBean = new DocenteBean();
        docenteBean.setCi(obj.getCi());
        docenteBean.setGradoAcademico("grado academico");
        docenteBean.setGradoAcademicoAbreviatura("grado academico (Abr)");
        docenteBean.setEstado("estado");

        docenteBean.setNombre("nombre");
        docenteBean.setApp("apellido paterno");
        docenteBean.setApm("apellido materno");
        docenteBean.setCuenta("cuenta");
        docenteBean.setContrasenia("password");
        docenteBean.setNombreCompleto("nombre completo");

        Docente docente = new Docente();
        Persona persona1 = new Persona();
        persona1.setCi(obj.getCi());
        docente.setCi(obj.getCi());
        docente.setPersona(persona1);
        docente = implementRelacionDocenteMateriaDAO.obtenerDocenteByCi(docente);

        if (docente != null) {
            docenteBean.setGradoAcademico(docente.getGradoAcademico());
            docenteBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());
            docenteBean.setEstado(docente.getEstado());

            Persona persona = new Persona();
            persona.setCi(obj.getCi());
            persona = implementRelacionDocenteMateriaDAO.obtenerPersonaByCi(persona);
            if (persona != null) {
                docenteBean.setNombre(persona.getNombre());
                docenteBean.setApp(persona.getApp());
                docenteBean.setApm(persona.getApm());
                docenteBean.setCuenta(persona.getCuenta());
                docenteBean.setContrasenia(persona.getContrasenia());
                docenteBean.setNombreCompleto(persona.getNombre()+" "+persona.getApp()+" "+persona.getApm());
            }
        }
        return docenteBean;
    }
    
    @Override
    public Long crearRelacionDocenteMateria(RelacionDocenteMateriaBean obj) {
        Long retorno = new Long(0);
        Materia materia=new Materia();
        materia.setIdMateria(obj.getMateriaBean().getIdMateria());
        
        Docente docente=new Docente();
        docente.setCi( obj.getDocenteBean().getCi() );
        
        RelacionDocenteMateria relacionDocenteMateria=new RelacionDocenteMateria();
        relacionDocenteMateria.setParalelo(obj.getParalelo());
        relacionDocenteMateria.setPeriodo(obj.getPeriodo());
        relacionDocenteMateria.setTipoPeriodo(obj.getTipoPeriodo());
        relacionDocenteMateria.setGestion(obj.getGestion());
        relacionDocenteMateria.setFechaInicio(obj.getFechaInicio());
        relacionDocenteMateria.setFechaFin(obj.getFechaFin());
        relacionDocenteMateria.setHoras(obj.getHoras());
        relacionDocenteMateria.setItem(obj.getItem());
        relacionDocenteMateria.setTipoMateria(obj.getTipoMateria());
        relacionDocenteMateria.setEstado("ACTIVO");
        relacionDocenteMateria.setDocente( docente );
        relacionDocenteMateria.setMateria( materia );
        
        retorno = implementRelacionDocenteMateriaDAO.crearRelacionDocenteMateria(relacionDocenteMateria);
        return retorno;
    }
    
    @Override
    public Long modificarRelacionDocenteMateria(RelacionDocenteMateriaBean obj) {
        Long retorno = new Long(0);
        Materia materia=new Materia();
        materia.setIdMateria(obj.getMateriaBean().getIdMateria());
        
        Docente docente=new Docente();
        docente.setCi( obj.getDocenteBean().getCi() );
        
        RelacionDocenteMateria relacionDocenteMateria=new RelacionDocenteMateria();
        relacionDocenteMateria.setIdRelacionDocenteMateria(obj.getIdRelacionDocenteMateria());
        relacionDocenteMateria.setParalelo(obj.getParalelo());
        relacionDocenteMateria.setPeriodo(obj.getPeriodo());
        relacionDocenteMateria.setTipoPeriodo(obj.getTipoPeriodo());
        relacionDocenteMateria.setGestion(obj.getGestion());
        relacionDocenteMateria.setFechaInicio(obj.getFechaInicio());
        relacionDocenteMateria.setFechaFin(obj.getFechaFin());
        relacionDocenteMateria.setHoras(obj.getHoras());
        relacionDocenteMateria.setItem(obj.getItem());
        relacionDocenteMateria.setTipoMateria(obj.getTipoMateria());
        relacionDocenteMateria.setEstado(obj.getEstado());
        relacionDocenteMateria.setDocente( docente );
        relacionDocenteMateria.setMateria( materia );
        
        retorno = implementRelacionDocenteMateriaDAO.modificarRelacionDocenteMateria(relacionDocenteMateria);
        return retorno;
    }

    @Override
    public List<RelacionEstudianteMateriaBean> obtenerRelacionEstudianteMateriasByIdRelacionDocenteMateria(RelacionDocenteMateriaBean obj) {
        RelacionDocenteMateria relacionDocenteMateria=new RelacionDocenteMateria();
        relacionDocenteMateria.setIdRelacionDocenteMateria( obj.getIdRelacionDocenteMateria() );
        List<RelacionEstudianteMateriaBean> relacionEstudianteMateriaBeans=new ArrayList<RelacionEstudianteMateriaBean>();
        for( RelacionEstudianteMateria object : implementRelacionDocenteMateriaDAO.obtenerRelacionEstudianteMateriasByIdRelacionDocenteMateria(relacionDocenteMateria) ){
            RelacionEstudianteMateriaBean relacionEstudianteMateriaBean=new RelacionEstudianteMateriaBean();
            relacionEstudianteMateriaBean.setIdRelacionEstudianteMateria(object.getIdRelacionEstudianteMateria() );
            relacionEstudianteMateriaBean.setIdIdentificadorRegistroMateria( object.getIdRelacionEstudianteMateria() );
            relacionEstudianteMateriaBean.setIdRelacionDocenteMateria( obj.getIdRelacionDocenteMateria() );
            
            //
            EstudianteBean estudianteBean=new EstudianteBean();
            estudianteBean.setCi( object.getEstudiante().getCi() );
            estudianteBean.setEstado("estado");

            estudianteBean.setNombre("nombre");
            estudianteBean.setApp("apellido paterno");
            estudianteBean.setApm("apellido materno");
            estudianteBean.setCuenta("cuenta");
            estudianteBean.setContrasenia("password");
            estudianteBean.setNombreCompleto("nombre completo");

            Estudiante estudiante=new Estudiante();
            estudiante.setCi( object.getEstudiante().getCi() );
            Persona persona1 = new Persona();
            persona1.setCi(object.getEstudiante().getCi());
            estudiante.setPersona(persona1);
            estudiante = implementRelacionDocenteMateriaDAO.obtenerEstudianteByCi(estudiante);

            if (estudiante != null) {
                estudianteBean.setEstado(estudiante.getEstado());

                Persona persona = new Persona();
                persona.setCi( object.getEstudiante().getCi() );
                persona = implementRelacionDocenteMateriaDAO.obtenerPersonaByCi(persona);
                if (persona != null) {
                    System.out.println("nooooo "+object.getEstudiante().getCi());
                    estudianteBean.setNombre(persona.getNombre());
                    estudianteBean.setApp(persona.getApp());
                    estudianteBean.setApm(persona.getApm());
                    estudianteBean.setCuenta(persona.getCuenta());
                    estudianteBean.setContrasenia(persona.getContrasenia());
                    estudianteBean.setNombreCompleto( persona.getNombre()+" "+persona.getApp()+" "+persona.getApm() );
                }
            } 
            relacionEstudianteMateriaBean.setEstudianteBean(estudianteBean);
            
            relacionEstudianteMateriaBeans.add(relacionEstudianteMateriaBean);
        }
        return relacionEstudianteMateriaBeans;
    }

    /**
     * @return the implementRelacionDocenteMateriaDAO
     */
    public IRelacionDocenteMateriaDAO getImplementRelacionDocenteMateriaDAO() {
        return implementRelacionDocenteMateriaDAO;
    }

    /**
     * @param implementRelacionDocenteMateriaDAO the
     * implementRelacionDocenteMateriaDAO to set
     */
    public void setImplementRelacionDocenteMateriaDAO(IRelacionDocenteMateriaDAO implementRelacionDocenteMateriaDAO) {
        this.implementRelacionDocenteMateriaDAO = implementRelacionDocenteMateriaDAO;
    }
    
    @Override
    public EstudianteBean obtenerEstudianteByCi(EstudianteBean obj) {
        EstudianteBean estudianteBean = new EstudianteBean();
        estudianteBean.setCi(obj.getCi());
        estudianteBean.setEstado("estado");

        estudianteBean.setNombre("nombre");
        estudianteBean.setApp("apellido paterno");
        estudianteBean.setApm("apellido materno");
        estudianteBean.setCuenta("cuenta");
        estudianteBean.setContrasenia("password");
        estudianteBean.setNombreCompleto("nombre completo");

        Estudiante estudiante = new Estudiante();
        Persona persona1 = new Persona();
        persona1.setCi(obj.getCi());
        estudiante.setCi(obj.getCi());
        estudiante.setPersona(persona1);
        estudiante = implementRelacionDocenteMateriaDAO.obtenerEstudianteByCi(estudiante);

        if (estudiante != null) {
            estudianteBean.setEstado(estudiante.getEstado());

            Persona persona = new Persona();
            persona.setCi(obj.getCi());
            persona = implementRelacionDocenteMateriaDAO.obtenerPersonaByCi(persona);
            if (persona != null) {
                estudianteBean.setNombre(persona.getNombre());
                estudianteBean.setApp(persona.getApp());
                estudianteBean.setApm(persona.getApm());
                estudianteBean.setCuenta(persona.getCuenta());
                estudianteBean.setContrasenia(persona.getContrasenia());
                estudianteBean.setNombreCompleto(persona.getNombre()+" "+persona.getApp()+" "+persona.getApm());
            }
        }
        return estudianteBean;
    }
    
    @Override
    public Long crearRelacionEstudianteMateria(RelacionEstudianteMateriaBean obj) {
        Long retorno = new Long(0);
        RelacionDocenteMateria relacionDocenteMateria=new RelacionDocenteMateria();
        relacionDocenteMateria.setIdRelacionDocenteMateria( obj.getIdRelacionDocenteMateria() );
        
        Estudiante estudiante=new Estudiante();
        estudiante.setCi( obj.getEstudianteBean().getCi() );
        
        RelacionEstudianteMateria relacionEstudianteMateria=new RelacionEstudianteMateria();
        relacionEstudianteMateria.setRelacionDocenteMateria( relacionDocenteMateria );
        relacionEstudianteMateria.setEstudiante( estudiante );
        
        retorno = implementRelacionDocenteMateriaDAO.crearRelacionEstudianteMateria(relacionEstudianteMateria);
        return retorno;
    }
}
