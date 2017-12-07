/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.ICoordinadorDeaDAO;
import bo.edu.uto.dea.jhs.persistence.CoordinadorDea;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jsf.bean.CoordinadorDeaBean;
import bo.edu.uto.dea.jsf.bean.PersonaBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementCoordinadorDeaBO implements ICoordinadorDeaBO, Serializable {

    private ICoordinadorDeaDAO implementCoordinadorDeaDAO;

    /**
     * @return the implementCoordinadorDeaDAO
     */
    public ICoordinadorDeaDAO getImplementCoordinadorDeaDAO() {
        return implementCoordinadorDeaDAO;
    }

    /**
     * @param implementCoordinadorDeaDAO the implementCoordinadorDeaDAO to set
     */
    public void setImplementCoordinadorDeaDAO(ICoordinadorDeaDAO implementCoordinadorDeaDAO) {
        this.implementCoordinadorDeaDAO = implementCoordinadorDeaDAO;
    }

    @Override
    public List<CoordinadorDeaBean> obtenerCoordinadorDeas() {
        List<CoordinadorDeaBean> coordinadorDeaBeans = new ArrayList<CoordinadorDeaBean>();
        for (CoordinadorDea obj : getImplementCoordinadorDeaDAO().obtenerCoordinadorDeas()) {
            CoordinadorDeaBean coordinadorDeaBean = new CoordinadorDeaBean();
            coordinadorDeaBean.setIdCoordinadorDea(obj.getIdCoordinadorDea());
            coordinadorDeaBean.setCi(obj.getPersona().getCi());
            coordinadorDeaBean.setFechaInicio(obj.getFechaInicio());
            coordinadorDeaBean.setFechaFin(obj.getFechaFin());
            coordinadorDeaBean.setEstado(obj.getEstado());

            Persona persona = new Persona();
            persona.setCi(obj.getPersona().getCi());
            persona = getImplementCoordinadorDeaDAO().obtenerPersonaByCi(persona);

            if (persona != null) {
                PersonaBean personaBean = new PersonaBean();
                personaBean.setNombre(persona.getNombre());
                personaBean.setApp(persona.getApp());
                personaBean.setApm(persona.getApm());
                personaBean.setCuenta(persona.getCuenta());
                personaBean.setContrasenia(persona.getContrasenia());

                coordinadorDeaBean.setNombre(persona.getNombre());//--
                coordinadorDeaBean.setApp(persona.getApp());//--
                coordinadorDeaBean.setApm(persona.getApm());//--
                coordinadorDeaBean.setNombreCompleto(persona.getNombre() + " " + persona.getApp() + " " + persona.getApm());//--
                coordinadorDeaBean.setCuenta(persona.getCuenta());//--
                coordinadorDeaBean.setContrasenia(persona.getContrasenia());//--

                coordinadorDeaBean.setPersonaBean(personaBean);
            }

            coordinadorDeaBeans.add(coordinadorDeaBean);
        }
        return coordinadorDeaBeans;
    }

    @Override
    public CoordinadorDeaBean obtenerCoordinadorDeaByCi(CoordinadorDeaBean obj) {
        CoordinadorDeaBean coordinadorDeaBean = new CoordinadorDeaBean();
        coordinadorDeaBean.setCi(obj.getCi());
        coordinadorDeaBean.setFechaInicio(new Date());
        coordinadorDeaBean.setFechaFin(new Date());
        coordinadorDeaBean.setEstado("estado");

        coordinadorDeaBean.setNombre("nombre");
        coordinadorDeaBean.setApp("apellido paterno");
        coordinadorDeaBean.setApm("apellido materno");
        coordinadorDeaBean.setCuenta("cuenta");
        coordinadorDeaBean.setContrasenia("password");

        CoordinadorDea coordinadorDea = new CoordinadorDea();
        Persona persona1 = new Persona();
        persona1.setCi(obj.getCi());
        coordinadorDea.setPersona(persona1);
        coordinadorDea = implementCoordinadorDeaDAO.obtenerCoordinadorDeaByCi(coordinadorDea);

        if (coordinadorDea != null) {
            coordinadorDeaBean.setFechaInicio(coordinadorDea.getFechaInicio());
            coordinadorDeaBean.setFechaFin(coordinadorDea.getFechaFin());
            coordinadorDeaBean.setEstado(coordinadorDea.getEstado());

            Persona persona = new Persona();
            persona.setCi(obj.getCi());
            persona = implementCoordinadorDeaDAO.obtenerPersonaByCi(persona);
            if (persona != null) {
                coordinadorDeaBean.setNombre(persona.getNombre());
                coordinadorDeaBean.setApp(persona.getApp());
                coordinadorDeaBean.setApm(persona.getApm());
                coordinadorDeaBean.setCuenta(persona.getCuenta());
                coordinadorDeaBean.setContrasenia(persona.getContrasenia());
            }
        }
        return coordinadorDeaBean;
    }

    @Override
    public Long modificarCoordinadorDea(CoordinadorDeaBean obj) {
        Long retorno = new Long(0);
        CoordinadorDea coordinadorDea = new CoordinadorDea();
        coordinadorDea.setIdCoordinadorDea(obj.getIdCoordinadorDea());

        Persona persona = new Persona();
        persona.setCi(obj.getCi());

        coordinadorDea.setPersona(persona);
        coordinadorDea.setFechaInicio(obj.getFechaInicio());
        coordinadorDea.setFechaFin(obj.getFechaFin());
        coordinadorDea.setEstado(obj.getEstado());

        retorno = implementCoordinadorDeaDAO.modificarCoordinadorDea(coordinadorDea);
        return retorno;
    }

    @Override
    public Long crearCoordinadorDea(CoordinadorDeaBean obj) {
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

        respuesta = implementCoordinadorDeaDAO.crearPersona(persona);

        if (respuesta.longValue() == -1) {
            retorno = new Long(-1);
        } else {
            CoordinadorDea coordinadorDea = new CoordinadorDea();
            Persona persona1 = new Persona();
            persona1.setCi(obj.getCi());
            coordinadorDea.setPersona(persona1);
            coordinadorDea.setFechaInicio(obj.getFechaInicio());
            coordinadorDea.setFechaFin(obj.getFechaFin());
            coordinadorDea.setEstado("ACTIVO");

            retorno = implementCoordinadorDeaDAO.crearCoordinadorDea(coordinadorDea);
        }
        return retorno;
    }
}
