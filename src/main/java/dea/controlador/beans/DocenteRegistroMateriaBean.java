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
import dea.modelo.Evaluacion;
import dea.modelo.Facultad;
import dea.modelo.Materia;
import dea.modelo.Persona;
import dea.modelo.Universidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.PostLoad;
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;
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
    
   // private DocenteRegistroMateria registro;
    private List<DocenteRegistroMateria> registroList;
    private List<Universidad> universidadList;
    private List<Facultad> facultadList;
    private List<Carrera> carreraList;
    private List<Materia> materiaList;
    private List<Persona> docenteList;    
    private DocenteRegistroMateria registro;
    private String uSelected,fSelected,cSelected;
   
    /*
    @PostConstruct
    public void reset(){
        JOptionPane.showMessageDialog(null,"PostLoad" );
        FacesContext context= FacesContext.getCurrentInstance();         
        Map session=context.getExternalContext().getSessionMap();        
        for(Object e:session.values())
           if(e instanceof dea.controlador.beans.DocenteRegistroMateriaBean){
               DocenteRegistroMateriaBean x=(DocenteRegistroMateriaBean) e;
               x.getRegistroList().clear();                  
           }  
    }
    */
    public DocenteRegistroMateriaBean(){        
              
      //  JOptionPane.showMessageDialog(null,cad );
        //context.getExternalContext().getSessionMap().put("user",p);
        //RequestContext.getCurrentInstance().reset(":table_header_escalafon"); 
    }
    public void changeUniversidad(AjaxBehaviorEvent vce){      
        String str= (String) ((UIOutput) vce.getSource()).getValue();
        facultadList=facultadDAO.readFacultdad(str);
        if(carreraList==null)
            carreraList=new ArrayList<Carrera>();
        else carreraList.clear();
        this.getRegistroList().clear();
        
        RequestContext.getCurrentInstance().reset(":table_form_header");
        
        
    }
    public void changeFacultad(AjaxBehaviorEvent vce){      
        String str= (String) ((UIOutput) vce.getSource()).getValue();        
        carreraList=carreraDAO.readCarrera(Long.parseLong(str));
        this.getRegistroList().clear();
        
        RequestContext.getCurrentInstance().reset(":table_form_header");
    }
    public void changeCarrera(AjaxBehaviorEvent vce){      
        String str= (String) ((UIOutput) vce.getSource()).getValue();
        //JOptionPane.showMessageDialog(null, str);
        materiaList=materiaDAO.readMateria(Long.parseLong(str));
       // carreraList=carreraDAO.readCarrera(Long.parseLong(str));
        List<Object[]> result=docenteRegistroMateriaDAO.getRegistro(Long.parseLong(str));
        if(registroList==null)
            registroList=new ArrayList<DocenteRegistroMateria>();
        else registroList.clear();
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
       this.cSelected=str;
       
    }
    public void retirarRegistro(DocenteRegistroMateria r){
        this.registro=new DocenteRegistroMateria();
        this.registro.setDocente(r.getDocente());
        this.registro.setIdDocenteRegistroMateria(r.getIdDocenteRegistroMateria());
        this.registro.setMateria(r.getMateria());
       /* this.registro.setParalelo(r.getParalelo());
        this.registro.setPeriodo(r.getPeriodo());
        this.registro.setTipoPeriodo(r.getTipoPeriodo());
        this.registro.setTipoMateria(r.getTipoMateria());
        this.registro.setGestion(r.getGestion());
        */
    }
    public void retirarRegistro(){
        this.docenteRegistroMateriaDAO.delete(registro);
        List<Object[]> result=docenteRegistroMateriaDAO.getRegistro(Long.parseLong(this.cSelected));
        if(registroList==null)
            registroList=new ArrayList<DocenteRegistroMateria>();
        else registroList.clear();
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
    }
    public void prepareRegistro(){
        registro=new DocenteRegistroMateria();
        registro.setDocente(new Docente());
        registro.setFechaInicio(new Date());
        registro.setFechaFin(new Date());
        registro.setMateria(new Materia());
        
        if(docenteList==null)
               docenteList=new ArrayList<Persona>();
        else docenteList.clear();
        List<Object[]> result=personaDAO.getDocente();
        for (Object[] e : result) {
            Persona p=(Persona) e[0];
            Docente d=(Docente) e[1];
            p.setDocente(d);
            docenteList.add(p);
        }
    }
    public void guardarRegistro(){
        //JOptionPane.showMessageDialog(null, ">"+registro.getMateria().getIdMateria());
        docenteRegistroMateriaDAO.create(registro);
      /*  Evaluacion eval=new Evaluacion();
                   eval.setNota(0.0);
                   eval.setDocenteRegistroMateria(registro);
        evaluacionDAO.create(eval);*/
        List<Object[]> result=docenteRegistroMateriaDAO.getRegistro(Long.parseLong(this.cSelected));
        if(registroList==null)
            registroList=new ArrayList<DocenteRegistroMateria>();
        else registroList.clear();
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
      //  JOptionPane.showMessageDialog(null, "good");
        universidadList=universidadDAO.readAll();
        return universidadList;
    }

    /**
     * @param universidadList the universidadList to set
     */
    public void setUniversidadList(List<Universidad> universidadList) {
        this.universidadList = universidadList;
    }

    /**
     * @return the facultadList
     */
    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    /**
     * @param facultadList the facultadList to set
     */
    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    /**
     * @return the carreraList
     */
    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    /**
     * @param carreraList the carreraList to set
     */
    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
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
        return docenteList;
    }

    /**
     * @param docenteList the docenteList to set
     */
    public void setDocenteList(List<Persona> docenteList) {
        this.docenteList = docenteList;
    }

    /**
     * @return the materiaList
     */
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    /**
     * @param materiaList the materiaList to set
     */
    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    /**
     * @return the registroList
     */
    public List<DocenteRegistroMateria> getRegistroList() {
       /*List<Object[]> result=docenteRegistroMateriaDAO.getRegistro(21L);
        if(registroList==null)
            registroList=new ArrayList<DocenteRegistroMateria>();
        else registroList.clear();
        for (Object[] e : result) {
            DocenteRegistroMateria r=(DocenteRegistroMateria) e[0];
            Materia m=(Materia) e[1];
            Persona p=(Persona)e[2];
            r.setDocente(new Docente(p));
            r.setMateria(m);
            registroList.add(r);
        }*/
        if(registroList==null)
          registroList=new ArrayList<DocenteRegistroMateria>();
       
        return registroList;
    }

    /**
     * @param registroList the registroList to set
     */
    public void setRegistroList(List<DocenteRegistroMateria> registroList) {
        this.registroList = registroList;
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
    public String getfSelected() {
        return fSelected;
    }

    /**
     * @param fSelected the fSelected to set
     */
    public void setfSelected(String fSelected) {
        this.fSelected = fSelected;
    }

    /**
     * @return the cSelected
     */
    public String getcSelected() {
        return cSelected;
    }

    /**
     * @param cSelected the cSelected to set
     */
    public void setcSelected(String cSelected) {
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
