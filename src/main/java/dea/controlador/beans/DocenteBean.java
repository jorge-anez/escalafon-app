/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.DocenteDAO;
import dea.controlador.dao_classes.PersonaDAO;
import dea.modelo.Docente;
import dea.modelo.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Doppler
 */
@Component
@Scope("session")

public class DocenteBean implements Serializable{
    @Autowired   
    private PersonaDAO personaDAO;
    private List<Persona> docenteList;
    private List<Persona> userList;
    private String grado;
    private String ci;
    @Autowired
    private DocenteDAO docenteDAO;
    
    public void crearDocente(String ci){
        this.ci=ci;
    }
    public void crearDocente(){
        Persona p=new Persona(this.ci);
        Docente d=new Docente();
        d.setGradoAcademico(grado);
        d.setPersona(p);
        this.docenteDAO.create(d);
    }
    public void quitarDocente(Docente c){
        Docente x=new Docente();
        x.setCi(c.getCi());
        docenteDAO.delete(x);
    }
    /**
     * @return the personaDAO
     */
    public PersonaDAO getPersonaDAO() {
        return personaDAO;
    }

    /**
     * @param personaDAO the personaDAO to set
     */
    public void setPersonaDAO(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    /**
     * @return the docente
     */
    public List<Persona> getDocenteList() {
        List<Object[]> x=personaDAO.getDocente();
        if(docenteList==null) 
            docenteList=new ArrayList<Persona>(); 
        else 
            docenteList.clear();
        for (Object[] e : x) {
            Persona y=(Persona) e[0];
            y.setDocente((Docente) e[1]);
            docenteList.add(y);
        }
        return docenteList;
    }

    /**
     * @param docente the docente to set
     */
    public void setDocenteList(List<Persona> docenteList) {
        this.docenteList = docenteList;
    }

    /**
     * @return the grado
     */
    public String getGrado() {
        return grado;
    }

    /**
     * @param grado the grado to set
     */
    public void setGrado(String grado) {
        this.grado = grado;
    }

    /**
     * @return the ci
     */
    public String getCi() {
        return ci;
    }

    /**
     * @param ci the ci to set
     */
    public void setCi(String ci) {
        this.ci = ci;
    }

    /**
     * @return the docenteDAO
     */
    public DocenteDAO getDocenteDAO() {
        return docenteDAO;
    }

    /**
     * @param docenteDAO the docenteDAO to set
     */
    public void setDocenteDAO(DocenteDAO docenteDAO) {
        this.docenteDAO = docenteDAO;
    }

    /**
     * @return the userList
     */
    public List<Persona> getUserList() {
        userList=personaDAO.getUserNotDocente();
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<Persona> userList) {
        this.userList = userList;
    }
}
