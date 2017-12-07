/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IPersonaDAO;
import bo.edu.uto.dea.jhs.persistence.DirectorCarrera;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Evaluacion;
import bo.edu.uto.dea.jhs.persistence.IdentificadorRegistroMateria;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.RelacionDocenteMateria;
import bo.edu.uto.dea.jhs.persistence.RelacionEstudianteMateria;
import bo.edu.uto.dea.jsf.bean.CoordinadorDeaBean;
import bo.edu.uto.dea.jsf.bean.DirectorCarreraBean;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.EvaluacionBean;
import bo.edu.uto.dea.jsf.bean.IdentificadorRegistroMateriaBean;
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
public class ImplementPersonaBO implements IPersonaBO, Serializable {

    private IPersonaDAO implementPersonaDAO;

    /**
     * @return the implementPersonaDAO
     */
    public IPersonaDAO getImplementPersonaDAO() {
        return implementPersonaDAO;
    }

    /**
     * @param implementPersonaDAO the implementPersonaDAO to set
     */
    public void setImplementPersonaDAO(IPersonaDAO implementPersonaDAO) {
        this.implementPersonaDAO = implementPersonaDAO;
    }

    @Override
    public Integer verificarUsuario(PersonaBean obj) {
        Integer respuesta = new Integer(0);
        Persona persona = new Persona();
        persona.setCuenta(obj.getCuenta());
        persona.setContrasenia(obj.getContrasenia());
        persona = implementPersonaDAO.verificarUsuario(persona);
        if (persona != null) {
            obj.setCi(persona.getCi());
            obj.setNombre(persona.getNombre());
            obj.setApp(persona.getApp());
            obj.setApm(persona.getApm());
            obj.setEstado(persona.getEstado());

            obj.setStatus(true);
            obj.setMensaje("Usuario encontrado");
            respuesta = implementPersonaDAO.tipoUsuario(persona);
        } else {
            obj.setStatus(false);
            obj.setMensaje("El usuario no existe");
            respuesta = 0;
        }
        return respuesta;
    }

    @Override
    public List<PersonaBean> obtenerPersonas() {
        List<PersonaBean> personaBeans = new ArrayList<PersonaBean>();
        for (Persona obj : implementPersonaDAO.obtenerPersonas()) {
            PersonaBean personaBean = new PersonaBean();
            personaBean.setCi(obj.getCi());
            personaBean.setNombre(obj.getNombre());
            personaBean.setApp(obj.getApp());
            personaBean.setApm(obj.getApm());
            personaBean.setCuenta(obj.getCuenta());
            personaBean.setContrasenia(obj.getContrasenia());
            personaBean.setEstado(obj.getEstado());
            personaBeans.add(personaBean);
        }
        return personaBeans;
    }

    @Override
    public Long crearPersona(PersonaBean obj) {
        Long retorno = new Long(0);
        Persona persona = new Persona();
        persona.setCi(obj.getCi());
        persona.setNombre(obj.getNombre());
        persona.setApp(obj.getApp());
        persona.setApm(obj.getApm());
        persona.setCuenta(obj.getCuenta());
        persona.setContrasenia(obj.getContrasenia());
        persona.setEstado("ACTIVO");

        retorno = implementPersonaDAO.crearPersona(persona);
        return retorno;
    }

    @Override
    public Long modificarPersona(PersonaBean obj) {
        Long retorno = new Long(0);
        Persona persona = new Persona();
        persona.setCi(obj.getCi());
        persona.setNombre(obj.getNombre());
        persona.setApp(obj.getApp());
        persona.setApm(obj.getApm());
        persona.setCuenta(obj.getCuenta());
        persona.setContrasenia(obj.getContrasenia());
        persona.setEstado(obj.getEstado());

        retorno = implementPersonaDAO.modificarPersona(persona);
        return retorno;
    }

    @Override
    public PersonaBean obtenerPersonaByCi(PersonaBean obj) {
        PersonaBean personaBean = new PersonaBean();
        personaBean.setCi(obj.getCi());
        personaBean.setNombre("nombre");
        personaBean.setApp("apellido paterno");
        personaBean.setApm("apellido materno");
        personaBean.setCuenta("cuenta");
        personaBean.setContrasenia("password");
        personaBean.setEstado("estado");

        Persona persona = new Persona();
        persona.setCi(obj.getCi());
        persona = implementPersonaDAO.obtenerPersonaByCi(persona);
        if (persona != null) {
            personaBean.setNombre(persona.getNombre());
            personaBean.setApp(persona.getApp());
            personaBean.setApm(persona.getApm());
            personaBean.setCuenta(persona.getCuenta());
            personaBean.setContrasenia(persona.getContrasenia());
        }

        return personaBean;
    }



    @Override
    public Long verificarAsignacionFormularioEvaluacionREM(RelacionEstudianteMateriaBean obj) {
        Long respuesta = new Long(0);
        RelacionEstudianteMateria relacionEstudianteMateria = new RelacionEstudianteMateria();
        relacionEstudianteMateria.setIdRelacionEstudianteMateria(obj.getIdRelacionEstudianteMateria());
        respuesta = implementPersonaDAO.verificarAsignacionFormularioEvaluacionREM(relacionEstudianteMateria);
        return respuesta;
    }
    
    @Override
    public Long verificarAsignacionFormularioEvaluacionRDM(RelacionDocenteMateriaBean obj) {
        Long respuesta = new Long(0);
        RelacionDocenteMateria relacionDocenteMateria = new RelacionDocenteMateria();
        relacionDocenteMateria.setIdRelacionDocenteMateria(obj.getIdRelacionDocenteMateria());
        respuesta = implementPersonaDAO.verificarAsignacionFormularioEvaluacionRDM(relacionDocenteMateria);
        return respuesta;
    }

    @Override
    public RelacionDocenteMateriaBean obtenerRelacionDocenteMateriaById(RelacionDocenteMateriaBean obj) {
        RelacionDocenteMateria rdm=new RelacionDocenteMateria();
        rdm.setIdRelacionDocenteMateria( obj.getIdRelacionDocenteMateria() );
        rdm=implementPersonaDAO.obtenerRelacionDocenteMateriaById(rdm);
        RelacionDocenteMateriaBean rdmb=new RelacionDocenteMateriaBean();
        rdmb.setIdRelacionDocenteMateria(rdm.getIdRelacionDocenteMateria());
        DocenteBean db=new DocenteBean();
        db.setCi( rdm.getDocente().getCi() );
        rdmb.setDocenteBean(db);
        return rdmb;
    }

    @Override
    public DirectorCarreraBean obtenerDirectorCarreraByCI(DirectorCarreraBean obj) {
        DirectorCarrera dc=new DirectorCarrera();
        Docente d=new Docente();
        d.setCi(obj.getDocenteBean().getCi());
        dc.setDocente(d);
        dc=implementPersonaDAO.obtenerDirectorCarreraByCI(dc);
        DirectorCarreraBean dcb=new DirectorCarreraBean();
        dcb.setIdDirectorCarrera( dc.getIdDirectorCarrera() );
        dcb.setCi(dc.getDocente().getCi() );
        dcb.setIdDirectorCarrera( dc.getIdDirectorCarrera() );
        dcb.setIdDirectorCarrera( dc.getIdDirectorCarrera() );
        return dcb;
    }

    @Override
    public List<DocenteBean> ObtenerDocenteByIdDirectorCarrera(DirectorCarreraBean object) {

        DirectorCarrera directorCarrera=new DirectorCarrera();
        
        Docente docente=new Docente();
        docente.setCi( object.getCi() );
        
        directorCarrera.setDocente(docente);
        
        List<DocenteBean> docenteBeans = new ArrayList<DocenteBean>();
        for (Docente obj : getImplementPersonaDAO().ObtenerDocenteByIdDirectorCarrera(directorCarrera) ) {
            DocenteBean docenteBean = new DocenteBean();
            docenteBean.setCi(obj.getCi());
            docenteBean.setGradoAcademico(obj.getGradoAcademico());
            docenteBean.setGradoAcademicoAbreviatura(obj.getGradoAcademicoAbreviatura());
            docenteBean.setEstado(obj.getEstado());

            Persona persona = new Persona();
            persona.setCi(obj.getPersona().getCi());
            persona = getImplementPersonaDAO().obtenerPersonaByCi(persona);

            if (persona != null) {
                PersonaBean personaBean = new PersonaBean();
                personaBean.setNombre(persona.getNombre());
                personaBean.setApp(persona.getApp());
                personaBean.setApm(persona.getApm());
                personaBean.setCuenta(persona.getCuenta());
                personaBean.setContrasenia(persona.getContrasenia());

                docenteBean.setNombre(persona.getNombre());//--
                docenteBean.setApp(persona.getApp());//--
                docenteBean.setApm(persona.getApm());//--
                docenteBean.setNombreCompleto(persona.getNombre() + " " + persona.getApp() + " " + persona.getApm());//--
                docenteBean.setCuenta(persona.getCuenta());//--
                docenteBean.setContrasenia(persona.getContrasenia());//--

                docenteBean.setPersonaBean(personaBean);
            }

            docenteBeans.add(docenteBean);
        }
        return docenteBeans;
    }

    
}
