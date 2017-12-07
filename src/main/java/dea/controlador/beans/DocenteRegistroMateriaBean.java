/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dea.controlador.beans;
import dea.controlador.dao_classes.CarreraDAO;
import dea.controlador.dao_classes.DocenteRegistroMateriaDAO;
import dea.controlador.dao_classes.EvaluacionDAO;
import dea.controlador.dao_classes.FacultadDAO;
import dea.controlador.dao_classes.MateriaDAO;
import dea.controlador.dao_classes.PersonaDAO;
import dea.controlador.dao_classes.UniversidadDAO;
import dea.modelo.Carrera;
import dea.modelo.Docente;
import dea.modelo.DocenteRegistroMateria;
import dea.modelo.Facultad;
import dea.modelo.Materia;
import dea.modelo.Persona;
import dea.modelo.Universidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 *
 * @author Doppler
 */
@Component
@Scope("session")
public class DocenteRegistroMateriaBean implements Serializable{
    @Autowired
    private DocenteRegistroMateriaDAO docenteRegistroMateriaDAO;
    @Autowired
    private PersonaDAO personaDAO;
    @Autowired
    private MateriaDAO materiaDAO;
    @Autowired
    private UniversidadDAO universidadDAO;
    @Autowired
    private FacultadDAO facultadDAO;
    @Autowired
    private CarreraDAO carreraDAO;
    @Autowired
    private EvaluacionDAO evaluacionDAO;
    private DocenteRegistroMateria registro;
    private String uSelected;
    private long fSelected,cSelected;    
    
    public void changeUniversidad(AjaxBehaviorEvent vce){      
        this.uSelected= (String) ((UIOutput) vce.getSource()).getValue();        
        this.fSelected=0;
        this.cSelected=0;        
    }
    public void changeFacultad(AjaxBehaviorEvent vce){      
        this.fSelected= (Long) ((UIOutput) vce.getSource()).getValue();
        this.cSelected=0;
    }
    public void changeCarrera(AjaxBehaviorEvent vce){      
        this.cSelected= (Long) ((UIOutput) vce.getSource()).getValue();        
    }
    public void retirarRegistro(DocenteRegistroMateria r){
        this.registro=new DocenteRegistroMateria();
        this.registro.setDocente(r.getDocente());
        this.registro.setIdDocenteRegistroMateria(r.getIdDocenteRegistroMateria());
        this.registro.setMateria(r.getMateria());       
    }
    public void retirarRegistro(){
        this.docenteRegistroMateriaDAO.delete(registro);        
    }
    public void prepareRegistro(){
        registro=new DocenteRegistroMateria();
        registro.setDocente(new Docente());
        registro.setFechaInicio(new Date());
        registro.setFechaFin(new Date());
        registro.setMateria(new Materia());        
    }
    public void detallarRegistro(DocenteRegistroMateria r){
        this.registro=r;
    }
    public void guardarRegistro(){        
        docenteRegistroMateriaDAO.create(registro);
    }    
    /**
     * @return the docenteRegistroMateriaDAO
     */
    public DocenteRegistroMateriaDAO getDocenteRegistroMateriaDAO() {
        return docenteRegistroMateriaDAO;
    }
    
    /**
     * @param docenteRegistroMateriaDAO the docenteRegistroMateriaDAO to set
     */
    public void setDocenteRegistroMateriaDAO(DocenteRegistroMateriaDAO docenteRegistroMateriaDAO) {
        this.docenteRegistroMateriaDAO = docenteRegistroMateriaDAO;
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
     * @return the materiaDAO
     */
    public MateriaDAO getMateriaDAO() {
        return materiaDAO;
    }

    /**
     * @param materiaDAO the materiaDAO to set
     */
    public void setMateriaDAO(MateriaDAO materiaDAO) {
        this.materiaDAO = materiaDAO;
    }

    /**
     * @return the universidadDAO
     */
    public UniversidadDAO getUniversidadDAO() {
        return universidadDAO;
    }

    /**
     * @param universidadDAO the universidadDAO to set
     */
    public void setUniversidadDAO(UniversidadDAO universidadDAO) {
        this.universidadDAO = universidadDAO;
    }

    /**
     * @return the facultadDAO
     */
    public FacultadDAO getFacultadDAO() {
        return facultadDAO;
    }

    /**
     * @param facultadDAO the facultadDAO to set
     */
    public void setFacultadDAO(FacultadDAO facultadDAO) {
        this.facultadDAO = facultadDAO;
    }

    /**
     * @return the carreraDAO
     */
    public CarreraDAO getCarreraDAO() {
        return carreraDAO;
    }

    /**
     * @param carreraDAO the carreraDAO to set
     */
    public void setCarreraDAO(CarreraDAO carreraDAO) {
        this.carreraDAO = carreraDAO;
    }

    /**
     * @return the universidadList
     */
    public List<Universidad> getUniversidadList() {        
        return universidadDAO.readAll();        
    }   

    /**
     * @return the facultadList
     */
    public List<Facultad> getFacultadList() {
        if(this.uSelected!=null)
            return facultadDAO.readFacultdad(this.uSelected);
        else
            return new ArrayList<Facultad>();
    }
    
    /**
     * @return the carreraList
     */
    public List<Carrera> getCarreraList() {
            return carreraDAO.readCarrera(this.fSelected);
    }
    
    /**
     * @return the registro
     */
    public DocenteRegistroMateria getRegistro() {
       if(registro==null){
            registro=new DocenteRegistroMateria();
            registro.setMateria(new Materia());
        }        
        return registro;
    }

    /**
     * @param registro the registro to set
     */
    public void setRegistro(DocenteRegistroMateria registro) {
        this.registro = registro;
    }

    /**
     * @return the docenteList
     */
    public List<Persona> getDocenteList() {
        List<Persona> docenteList=new ArrayList<Persona>();
        List<Object[]> result=personaDAO.getDocente();
        for (Object[] e : result) {
            Persona p=(Persona) e[0];
            Docente d=(Docente) e[1];
            p.setDocente(d);
            docenteList.add(p);
        }        
        return docenteList;
    }
    
    /**
     * @return the materiaList
     */
    public List<Materia> getMateriaList() {
        return materiaDAO.readMateria(this.cSelected);        
    }    

    /**
     * @return the registroList
     */
    public List<DocenteRegistroMateria> getRegistroList() {      
        List<DocenteRegistroMateria> registroList=new ArrayList<DocenteRegistroMateria>();
        List<Object[]> result=docenteRegistroMateriaDAO.getRegistro(this.cSelected);        
        for (Object[] e : result) {
            DocenteRegistroMateria r=(DocenteRegistroMateria) e[0];
            Materia m=(Materia) e[1];
            Persona p=(Persona)e[2];
            Docente d=new Docente();
                    d.setCi(p.getCi());
                    d.setPersona(p);
            r.setDocente(d);
            r.setMateria(m);
            registroList.add(r);
        }
        return registroList;
    }

    /**
     * @return the uSelected
     */
    public String getuSelected() {
        return uSelected;
    }

    /**
     * @param uSelected the uSelected to set
     */
    public void setuSelected(String uSelected) {
        this.uSelected = uSelected;
    }

    /**
     * @return the fSelected
     */
    public long getfSelected() {
        return fSelected;
    }

    /**
     * @param fSelected the fSelected to set
     */
    public void setfSelected(long fSelected) {
        this.fSelected = fSelected;
    }

    /**
     * @return the cSelected
     */
    public long getcSelected() {
        return cSelected;
    }
    /**
     * @param cSelected the cSelected to set
     */
    public void setcSelected(long cSelected) {
        this.cSelected = cSelected;
    }

    /**
     * @return the evaluacionDAO
     */
    public EvaluacionDAO getEvaluacionDAO() {
        return evaluacionDAO;
    }

    /**
     * @param evaluacionDAO the evaluacionDAO to set
     */
    public void setEvaluacionDAO(EvaluacionDAO evaluacionDAO) {
        this.evaluacionDAO = evaluacionDAO;
    }
}
