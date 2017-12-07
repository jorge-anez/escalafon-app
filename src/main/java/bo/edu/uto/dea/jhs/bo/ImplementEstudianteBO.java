/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IDocenteDAO;
import bo.edu.uto.dea.jdbc.dao.IEstudianteDAO;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Estudiante;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.EstudianteBean;
import bo.edu.uto.dea.jsf.bean.PersonaBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementEstudianteBO implements IEstudianteBO, Serializable {

    private IEstudianteDAO implementEstudianteDAO;

    @Override
    public List<EstudianteBean> obtenerEstudiantes() {
        List<EstudianteBean> estudianteBeans = new ArrayList<EstudianteBean>();
        for (Estudiante obj : getImplementEstudianteDAO().obtenerEstudiantes() ) {
            EstudianteBean estudianteBean = new EstudianteBean();
            estudianteBean.setCi(obj.getCi());
            estudianteBean.setEstado(obj.getEstado());

            Persona persona = new Persona();
            persona.setCi(obj.getPersona().getCi());
            persona = getImplementEstudianteDAO().obtenerPersonaByCi(persona);

            if (persona != null) {
                PersonaBean personaBean = new PersonaBean();
                personaBean.setNombre(persona.getNombre());
                personaBean.setApp(persona.getApp());
                personaBean.setApm(persona.getApm());
                personaBean.setCuenta(persona.getCuenta());
                personaBean.setContrasenia(persona.getContrasenia());

                estudianteBean.setNombre(persona.getNombre());//--
                estudianteBean.setApp(persona.getApp());//--
                estudianteBean.setApm(persona.getApm());//--
                estudianteBean.setNombreCompleto(persona.getNombre() + " " + persona.getApp() + " " + persona.getApm());//--
                estudianteBean.setCuenta(persona.getCuenta());//--
                estudianteBean.setContrasenia(persona.getContrasenia());//--

                estudianteBean.setPersonaBean(personaBean);
            }

            estudianteBeans.add(estudianteBean);
        }
        return estudianteBeans;
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

        Estudiante estudiante = new Estudiante();
        Persona persona1 = new Persona();
        persona1.setCi(obj.getCi());
        estudiante.setCi(obj.getCi());
        estudiante.setPersona(persona1);
        estudiante = implementEstudianteDAO.obtenerEstudianteByCi(estudiante);

        if (estudiante != null) {
            estudianteBean.setEstado(estudiante.getEstado());

            Persona persona = new Persona();
            persona.setCi(obj.getCi());
            persona = implementEstudianteDAO.obtenerPersonaByCi(persona);
            if (persona != null) {
                estudianteBean.setNombre(persona.getNombre());
                estudianteBean.setApp(persona.getApp());
                estudianteBean.setApm(persona.getApm());
                estudianteBean.setCuenta(persona.getCuenta());
                estudianteBean.setContrasenia(persona.getContrasenia());
            }
        }
        return estudianteBean;
    }

    @Override
    public Long modificarEstudiante(EstudianteBean obj) {
        Long retorno = new Long(0);
        Estudiante estudiante = new Estudiante();
        estudiante.setCi(obj.getCi());

        Persona persona = new Persona();
        persona.setCi(obj.getCi());

        estudiante.setPersona(persona);
        estudiante.setEstado(obj.getEstado());

        retorno = implementEstudianteDAO.modificarEstudiante(estudiante);
        return retorno;
    }

    @Override
    public Long crearEstudiante(EstudianteBean obj) {
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

        respuesta = implementEstudianteDAO.crearPersona(persona);

        if (respuesta.longValue() == -1) {
            retorno = new Long(-1);
        } else {
            Estudiante estudiante = new Estudiante();
            Persona persona1 = new Persona();
            persona1.setCi(obj.getCi());
            estudiante.setCi(obj.getCi());
            estudiante.setPersona(persona1);
            estudiante.setEstado("ACTIVO");

            retorno = implementEstudianteDAO.crearEstudiante(estudiante);
        }
        return retorno;
    }

    /**
     * @return the implementEstudianteDAO
     */
    public IEstudianteDAO getImplementEstudianteDAO() {
        return implementEstudianteDAO;
    }

    /**
     * @param implementEstudianteDAO the implementEstudianteDAO to set
     */
    public void setImplementEstudianteDAO(IEstudianteDAO implementEstudianteDAO) {
        this.implementEstudianteDAO = implementEstudianteDAO;
    }
}
