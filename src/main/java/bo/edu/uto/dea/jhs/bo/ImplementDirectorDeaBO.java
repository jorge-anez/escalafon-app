/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IDirectorDeaDAO;
import bo.edu.uto.dea.jhs.persistence.DirectorDea;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jsf.bean.DirectorDeaBean;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.PersonaBean;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementDirectorDeaBO implements IDirectorDeaBO, Serializable {

    private IDirectorDeaDAO implementDirectorDeaDAO;

    @Override
    public List<DirectorDeaBean> obtenerDirectorDeas() {
        List<DirectorDeaBean> directorDeaBeans = new ArrayList<DirectorDeaBean>();
        for (DirectorDea obj : getImplementDirectorDeaDAO().obtenerDirectorDeas()) {
            DirectorDeaBean directorDeaBean = new DirectorDeaBean();
            directorDeaBean.setIdDirectorDea(obj.getIdDirectorDea());
            directorDeaBean.setCi(obj.getDocente().getCi());
            directorDeaBean.setFechaInicio(obj.getFechaInicio());
            directorDeaBean.setFechaFin(obj.getFechaFin());
            directorDeaBean.setEstado(obj.getEstado());

            Docente docente = new Docente();
            docente.setCi(obj.getDocente().getCi());
            docente = getImplementDirectorDeaDAO().obtenerDocenteByCi(docente);
            if (docente != null) {
                DocenteBean docenteBean = new DirectorDeaBean();
                docenteBean.setCi(docente.getCi());
                docenteBean.setGradoAcademico(docente.getGradoAcademico());
                docenteBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());

                directorDeaBean.setGradoAcademico(docente.getGradoAcademico());//--
                directorDeaBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());//--

                Persona persona = new Persona();
                persona.setCi(obj.getDocente().getCi());
                persona = getImplementDirectorDeaDAO().obtenerPersonaByCi(persona);
                if (persona != null) {
                    PersonaBean personaBean = new PersonaBean();
                    personaBean.setCi(persona.getCi());
                    personaBean.setNombre(persona.getNombre());
                    personaBean.setApp(persona.getApp());
                    personaBean.setApm(persona.getApm());
                    personaBean.setCuenta(persona.getCuenta());
                    personaBean.setContrasenia(persona.getContrasenia());
                    personaBean.setEstado(persona.getEstado());

                    directorDeaBean.setNombre(persona.getNombre());//--
                    directorDeaBean.setApp(persona.getApp());//--
                    directorDeaBean.setApm(persona.getApm());//--
                    directorDeaBean.setNombreCompleto(persona.getNombre() + " " + persona.getApp() + " " + persona.getApm());//--
                    directorDeaBean.setCuenta(persona.getCuenta());//--
                    directorDeaBean.setContrasenia(persona.getContrasenia());//--

                    docenteBean.setPersonaBean(personaBean);
                }
                directorDeaBean.setDocenteBean(docenteBean);
            }

            directorDeaBeans.add(directorDeaBean);
        }
        return directorDeaBeans;
    }

    /**
     * @return the implementDirectorDeaDAO
     */
    public IDirectorDeaDAO getImplementDirectorDeaDAO() {
        return implementDirectorDeaDAO;
    }

    /**
     * @param implementDirectorDeaDAO the implementDirectorDeaDAO to set
     */
    public void setImplementDirectorDeaDAO(IDirectorDeaDAO implementDirectorDeaDAO) {
        this.implementDirectorDeaDAO = implementDirectorDeaDAO;
    }

    @Override
    public Long modificarDirectorDea(DirectorDeaBean obj) {
        Long retorno = new Long(0);
        DirectorDea directorDea = new DirectorDea();
        directorDea.setIdDirectorDea(obj.getIdDirectorDea());

        Docente docente = new Docente();
        docente.setCi(obj.getCi());
        Persona persona = new Persona();
        persona.setCi(obj.getCi());
        docente.setPersona(persona);
        directorDea.setDocente(docente);

        directorDea.setFechaInicio(obj.getFechaInicio());
        directorDea.setFechaFin(obj.getFechaFin());
        directorDea.setEstado(obj.getEstado());


        retorno = implementDirectorDeaDAO.modificarDirectorDea(directorDea);
        return retorno;
    }

    @Override
    public Long crearDirectorDea(DirectorDeaBean obj) {
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

        respuesta = implementDirectorDeaDAO.crearPersona(persona);
        if (respuesta.longValue() == -1) {
            retorno=new Long(-1);
        } else {
            Docente docente = new Docente();
            docente.setCi(obj.getCi());
            docente.setGradoAcademico(obj.getGradoAcademico());
            docente.setGradoAcademicoAbreviatura(obj.getGradoAcademicoAbreviatura());
            docente.setEstado("ACTIVO");

            respuesta = implementDirectorDeaDAO.crearDocente(docente);

            DirectorDea directorDea = new DirectorDea();
            Docente docente1 = new Docente();
            docente1.setCi(obj.getCi());
            directorDea.setDocente(docente1);
            directorDea.setFechaInicio(obj.getFechaInicio());
            directorDea.setFechaFin(obj.getFechaFin());
            directorDea.setEstado("ACTIVO");

            retorno = implementDirectorDeaDAO.crearDirectorDea(directorDea);
        }
        return retorno;
    }

    @Override
    public DirectorDeaBean obtenerDirectorDeaByCi(DirectorDeaBean obj) {
        DirectorDeaBean directorDeaBean = new DirectorDeaBean();
        directorDeaBean.setCi(obj.getCi());
        directorDeaBean.setFechaInicio(new Date());
        directorDeaBean.setFechaFin(new Date());
        directorDeaBean.setEstado("estado");

        directorDeaBean.setGradoAcademico("grado academico");
        directorDeaBean.setGradoAcademicoAbreviatura("grado academico (Abr)");

        directorDeaBean.setNombre("nombre");
        directorDeaBean.setApp("apellido paterno");
        directorDeaBean.setApm("apellido materno");
        directorDeaBean.setCuenta("cuenta");
        directorDeaBean.setContrasenia("password");

        DirectorDea directorDea = new DirectorDea();
        Docente docente1 = new Docente();
        docente1.setCi(obj.getCi());
        directorDea.setDocente(docente1);
        directorDea = implementDirectorDeaDAO.obtenerDirectorDeaByCi(directorDea);

        if (directorDea != null) {
            directorDeaBean.setFechaInicio(directorDea.getFechaInicio());
            directorDeaBean.setFechaFin(directorDea.getFechaFin());
            directorDeaBean.setEstado(directorDea.getEstado());

            Docente docente = new Docente();
            docente.setCi(obj.getCi());
            docente = implementDirectorDeaDAO.obtenerDocenteByCi(docente);
            if (docente != null) {
                directorDeaBean.setGradoAcademico(docente.getGradoAcademico());
                directorDeaBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());

                Persona persona = new Persona();
                persona.setCi(obj.getCi());
                persona = implementDirectorDeaDAO.obtenerPersonaByCi(persona);
                if (persona != null) {
                    directorDeaBean.setNombre(persona.getNombre());
                    directorDeaBean.setApp(persona.getApp());
                    directorDeaBean.setApm(persona.getApm());
                    directorDeaBean.setCuenta(persona.getCuenta());
                    directorDeaBean.setContrasenia(persona.getContrasenia());
                }
            }
        }
        return directorDeaBean;
    }
}
