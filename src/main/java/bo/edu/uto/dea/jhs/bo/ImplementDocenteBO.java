/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IDocenteDAO;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.PersonaBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementDocenteBO implements IDocenteBO, Serializable {

    private IDocenteDAO implementDocenteDAO;

    @Override
    public List<DocenteBean> obtenerDocentes() {
        List<DocenteBean> docenteBeans = new ArrayList<DocenteBean>();
        for (Docente obj : getImplementDocenteDAO().obtenerDocentes()) {
            DocenteBean docenteBean = new DocenteBean();
            docenteBean.setCi(obj.getCi());
            docenteBean.setGradoAcademico(obj.getGradoAcademico());
            docenteBean.setGradoAcademicoAbreviatura(obj.getGradoAcademicoAbreviatura());
            docenteBean.setEstado(obj.getEstado());

            Persona persona = new Persona();
            persona.setCi(obj.getPersona().getCi());
            persona = getImplementDocenteDAO().obtenerPersonaByCi(persona);

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

        Docente docente = new Docente();
        Persona persona1 = new Persona();
        persona1.setCi(obj.getCi());
        docente.setCi(obj.getCi());
        docente.setPersona(persona1);
        docente = implementDocenteDAO.obtenerDocenteByCi(docente);

        if (docente != null) {
            docenteBean.setGradoAcademico(docente.getGradoAcademico());
            docenteBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());
            docenteBean.setEstado(docente.getEstado());
        }
        Persona persona = new Persona();
        persona.setCi(obj.getCi());
        persona = implementDocenteDAO.obtenerPersonaByCi(persona);
        
        if (persona != null) {
            docenteBean.setNombre(persona.getNombre());
            docenteBean.setApp(persona.getApp());
            docenteBean.setApm(persona.getApm());
            docenteBean.setCuenta(persona.getCuenta());
            docenteBean.setContrasenia(persona.getContrasenia());
            docenteBean.setNombreCompleto(persona.getNombre()+" "+persona.getApp()+" "+persona.getApm());
        }


        return docenteBean;
    }

    @Override
    public Long modificarDocente(DocenteBean obj) {
        Long retorno = new Long(0);
        Docente docente = new Docente();
        docente.setCi(obj.getCi());

        Persona persona = new Persona();
        persona.setCi(obj.getCi());

        docente.setPersona(persona);
        docente.setGradoAcademico(obj.getGradoAcademico());
        docente.setGradoAcademicoAbreviatura(obj.getGradoAcademicoAbreviatura());
        docente.setEstado(obj.getEstado());

        retorno = implementDocenteDAO.modificarDocente(docente);
        return retorno;
    }

    @Override
    public Long crearDocente(DocenteBean obj) {
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

        respuesta = implementDocenteDAO.crearPersona(persona);
        if (respuesta.longValue() == -1) {
            retorno = new Long(-1);
        } else {
            Docente docente = new Docente();
            docente.setCi(obj.getCi());
            Persona persona1 = new Persona();
            persona1.setCi(obj.getCi());
            docente.setPersona(persona1);
            docente.setGradoAcademico(obj.getGradoAcademico());
            docente.setGradoAcademicoAbreviatura(obj.getGradoAcademicoAbreviatura());
            docente.setEstado("ACTIVO");

            retorno = implementDocenteDAO.crearDocente(docente);
        }
        return retorno;
    }

    /**
     * @return the implementDocenteDAO
     */
    public IDocenteDAO getImplementDocenteDAO() {
        return implementDocenteDAO;
    }

    /**
     * @param implementDocenteDAO the implementDocenteDAO to set
     */
    public void setImplementDocenteDAO(IDocenteDAO implementDocenteDAO) {
        this.implementDocenteDAO = implementDocenteDAO;
    }
}
