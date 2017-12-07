/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IDirectorCarreraDAO;
import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.DirectorCarrera;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.DirectorCarreraBean;
import bo.edu.uto.dea.jsf.bean.DirectorDeaBean;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.PersonaBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementDirectorCarreraBO implements IDirectorCarreraBO, Serializable {

    private IDirectorCarreraDAO implementDirectorCarreraDAO;

    @Override
    public List<DirectorCarreraBean> obtenerDirectorCarreras( CarreraBean obj ) {
        List<DirectorCarreraBean> directorCarreraBeans = new ArrayList<DirectorCarreraBean>();
        Carrera carrera=new Carrera();
        carrera.setIdCarrera( obj.getIdCarrera() );
        
        for (DirectorCarrera object : getImplementDirectorCarreraDAO().obtenerDirectorCarreras(carrera) ) {
            DirectorCarreraBean directorCarreraBean = new DirectorCarreraBean();
            
            directorCarreraBean.setIdDirectorCarrera(object.getIdDirectorCarrera());            
            directorCarreraBean.setGestion(object.getGestion());
            directorCarreraBean.setFechaInicio(object.getFechaInicio());
            directorCarreraBean.setFechaFin(object.getFechaFin());
            directorCarreraBean.setEstado(object.getEstado());

            Docente docente = new Docente();
            docente.setCi(object.getDocente().getCi());
            docente = getImplementDirectorCarreraDAO().obtenerDocenteByCi(docente);
            if (docente != null) {
                DocenteBean docenteBean = new DirectorDeaBean();
                docenteBean.setCi(docente.getCi());
                docenteBean.setGradoAcademico(docente.getGradoAcademico());
                docenteBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());

                directorCarreraBean.setCi(docente.getCi());//--
                directorCarreraBean.setGradoAcademico(docente.getGradoAcademico());//--
                directorCarreraBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());//--

                Persona persona = new Persona();
                persona.setCi(object.getDocente().getCi());
                persona = getImplementDirectorCarreraDAO().obtenerPersonaByCi(persona);
                if (persona != null) {
                    PersonaBean personaBean = new PersonaBean();
                    personaBean.setCi(persona.getCi());
                    personaBean.setNombre(persona.getNombre());
                    personaBean.setApp(persona.getApp());
                    personaBean.setApm(persona.getApm());
                    personaBean.setCuenta(persona.getCuenta());
                    personaBean.setContrasenia(persona.getContrasenia());
                    personaBean.setEstado(persona.getEstado());

                    directorCarreraBean.setNombre(persona.getNombre());//--
                    directorCarreraBean.setApp(persona.getApp());//--
                    directorCarreraBean.setApm(persona.getApm());//--
                    directorCarreraBean.setNombreCompleto(persona.getNombre() + " " + persona.getApp() + " " + persona.getApm());//--
                    directorCarreraBean.setCuenta(persona.getCuenta());//--
                    directorCarreraBean.setContrasenia(persona.getContrasenia());//--

                    docenteBean.setPersonaBean(personaBean);
                }
                directorCarreraBean.setDocenteBean(docenteBean);
            }
            //obtener carrera by idcar
            directorCarreraBean.setIdCarrera(obj.getIdCarrera());//--
            
            Carrera carrera1 = new Carrera();
            carrera1.setIdCarrera( object.getIdDirectorCarrera() );
            carrera1 = getImplementDirectorCarreraDAO().obtenerCarreraById(carrera1);
            if( carrera1!=null ){
                CarreraBean carreraBean=new CarreraBean();
                carreraBean.setIdCarrera(carrera1.getIdCarrera());
                carreraBean.setSiglaCarrera(carrera1.getSiglaCarrera());
                carreraBean.setNombre(carrera1.getNombre());
                carreraBean.setIdFacultad(carrera1.getFacultad().getIdFacultad());
                carreraBean.setEstado(carrera1.getEstado());
                directorCarreraBean.setCarreraBean(carreraBean);
            }

            directorCarreraBeans.add(directorCarreraBean);
        }
        return directorCarreraBeans;
    }

    @Override
    public Long modificarDirectorCarrera(DirectorCarreraBean obj) {
        Long retorno = new Long(0);
        DirectorCarrera directorCarrera = new DirectorCarrera();
        directorCarrera.setIdDirectorCarrera(obj.getIdDirectorCarrera());

        Docente docente = new Docente();
        docente.setCi(obj.getCi());
        Persona persona = new Persona();
        persona.setCi(obj.getCi());
        docente.setPersona(persona);
        directorCarrera.setDocente(docente);
        
        Carrera carrera=new Carrera();
        carrera.setIdCarrera(obj.getIdCarrera());
        directorCarrera.setCarrera(carrera);

        directorCarrera.setFechaInicio(obj.getFechaInicio());
        directorCarrera.setFechaFin(obj.getFechaFin());
        directorCarrera.setEstado(obj.getEstado());


        retorno = implementDirectorCarreraDAO.modificarDirectorCarrera(directorCarrera);
        return retorno;
    }

    @Override
    public Long crearDirectorCarrera(DirectorCarreraBean obj) {
        Long retorno = new Long(0);
        Long respuesta = new Long(0);

        Persona persona = new Persona();
        persona.setCi(obj.getCi());
        persona.setNombre(obj.getNombre());
        persona.setApp(obj.getApp());
        persona.setApm(obj.getApm());
        persona.setCuenta(obj.getCuenta());
        persona.setContrasenia(obj.getContrasenia());
        persona.setEstado("ACTIVO");

        respuesta = implementDirectorCarreraDAO.crearPersona(persona);
        if (respuesta.longValue() == -1) {
            retorno=new Long(-1);
        } else {
            Docente docente = new Docente();
            docente.setCi(obj.getCi());
            docente.setGradoAcademico(obj.getGradoAcademico());
            docente.setGradoAcademicoAbreviatura(obj.getGradoAcademicoAbreviatura());
            docente.setEstado("ACTIVO");

            respuesta = implementDirectorCarreraDAO.crearDocente(docente);

            DirectorCarrera directorCarrera = new DirectorCarrera();
            Docente docente1 = new Docente();
            docente1.setCi(obj.getCi());            
            directorCarrera.setDocente(docente1);
            
            Carrera carrera1 = new Carrera();
            carrera1.setIdCarrera(obj.getIdCarrera());            
            directorCarrera.setCarrera(carrera1);
            
            directorCarrera.setFechaInicio(obj.getFechaInicio());
            directorCarrera.setFechaFin(obj.getFechaFin());
            directorCarrera.setEstado("ACTIVO");

            retorno = implementDirectorCarreraDAO.crearDirectorCarrera(directorCarrera);
        }
        return retorno;
    }

    @Override
    public DirectorCarreraBean obtenerDirectorCarreraByCi(DirectorCarreraBean obj) {
        DirectorCarreraBean directorCarreraBean = new DirectorCarreraBean();
        directorCarreraBean.setCi(obj.getCi());
        directorCarreraBean.setFechaInicio(new Date());
        directorCarreraBean.setFechaFin(new Date());
        directorCarreraBean.setEstado("estado");

        directorCarreraBean.setGradoAcademico("grado academico");
        directorCarreraBean.setGradoAcademicoAbreviatura("grado academico (Abr)");

        directorCarreraBean.setNombre("nombre");
        directorCarreraBean.setApp("apellido paterno");
        directorCarreraBean.setApm("apellido materno");
        directorCarreraBean.setCuenta("cuenta");
        directorCarreraBean.setContrasenia("password");

        DirectorCarrera directorCarrera = new DirectorCarrera();
        Docente docente1 = new Docente();
        docente1.setCi(obj.getCi());
        directorCarrera.setDocente(docente1);
        directorCarrera = implementDirectorCarreraDAO.obtenerDirectorCarreraByCi(directorCarrera);

        if (directorCarrera != null) {
            directorCarreraBean.setFechaInicio(directorCarrera.getFechaInicio());
            directorCarreraBean.setFechaFin(directorCarrera.getFechaFin());
            directorCarreraBean.setEstado(directorCarrera.getEstado());

            Docente docente = new Docente();
            docente.setCi(obj.getCi());
            docente = implementDirectorCarreraDAO.obtenerDocenteByCi(docente);
            if (docente != null) {
                directorCarreraBean.setGradoAcademico(docente.getGradoAcademico());
                directorCarreraBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());

                Persona persona = new Persona();
                persona.setCi(obj.getCi());
                persona = implementDirectorCarreraDAO.obtenerPersonaByCi(persona);
                if (persona != null) {
                    directorCarreraBean.setNombre(persona.getNombre());
                    directorCarreraBean.setApp(persona.getApp());
                    directorCarreraBean.setApm(persona.getApm());
                    directorCarreraBean.setCuenta(persona.getCuenta());
                    directorCarreraBean.setContrasenia(persona.getContrasenia());
                }
            }
        }
        return directorCarreraBean;
    }

    /**
     * @return the implementDirectorCarreraDAO
     */
    public IDirectorCarreraDAO getImplementDirectorCarreraDAO() {
        return implementDirectorCarreraDAO;
    }

    /**
     * @param implementDirectorCarreraDAO the implementDirectorCarreraDAO to set
     */
    public void setImplementDirectorCarreraDAO(IDirectorCarreraDAO implementDirectorCarreraDAO) {
        this.implementDirectorCarreraDAO = implementDirectorCarreraDAO;
    }
}
