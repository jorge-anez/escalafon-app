/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;
import dea.controlador.dao_classes.CarreraDAO;
import dea.controlador.dao_classes.DocenteEscalafonDAO;
import dea.controlador.dao_classes.EscalafonDAO;
import dea.controlador.dao_classes.EvaluacionDAO;
import dea.controlador.dao_classes.FacultadDAO;
import dea.controlador.dao_classes.PersonaDAO;
import dea.controlador.dao_classes.UniversidadDAO;
import dea.modelo.Carrera;
import dea.modelo.Cartilla;
import dea.modelo.ContenidoCartilla;
import dea.modelo.Docente;
import dea.modelo.DocenteEscalafon;
import dea.modelo.DocenteRegistroMateria;
import dea.modelo.Escalafon;
import dea.modelo.Evaluacion;
import dea.modelo.Facultad;
import dea.modelo.Materia;
import dea.modelo.Persona;
import dea.modelo.Universidad;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Doppler
 */
@Component
@Scope("session")
public class DocenteEscalafonBean implements Serializable{
    @Autowired
    private DocenteEscalafonDAO docenteEscalafonDAO;
    @Autowired
    private PersonaDAO personaDAO;   
    @Autowired
    private UniversidadDAO universidadDAO;
    @Autowired
    private FacultadDAO facultadDAO;
    @Autowired
    private CarreraDAO carreraDAO;
    @Autowired
    private EvaluacionDAO evaluacionDAO;
    
    @Autowired
    private EscalafonDAO escalafonDAO;
   // private DocenteRegistroMateria registro;
    private List<DocenteEscalafon> registroList;
    private List<Universidad> universidadList;
    private List<Facultad> facultadList;
    private List<Carrera> carreraList;    
    private List<Persona> docenteList;  
    private List<Evaluacion> materiaList;
    private List<Escalafon> escalafonList;
    private List<ContenidoCartilla> contenidoCartillaList;
    private DocenteEscalafon registro;
    private String cSelected,fSelected;
    private Persona dSelected;
    private String gSelected;
    private int tipoRes;
    private Cartilla carSelected;
    private DocenteEscalafon docenteEscalafon;
    private Escalafon escalafon;
    private String resolucion,obs;
    private int nota;
    public void reset(){
        //universidadList.clear();
        //FacesContext context= FacesContext.getCurrentInstance();  
        //RequestContext.getCurrentInstance().
        //Persona p=(Persona)context.getExternalContext().getSessionMap().get("user");
        //context.getExternalContext().getSessionMap().clear();//put("user",pSelected);
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
        fSelected="";
        cSelected="";
        RequestContext.getCurrentInstance().reset(":table_header_escalafon");       
    }
    public void changeFacultad(AjaxBehaviorEvent vce){      
        String str= (String) ((UIOutput) vce.getSource()).getValue();        
        carreraList=carreraDAO.readCarrera(Long.parseLong(str));
        registroList.clear();        
        cSelected="";
        fSelected=str;
        RequestContext.getCurrentInstance().reset(":table_header_escalafon");
    }
    public void changeCarrera(AjaxBehaviorEvent vce){      
       String str= (String) ((UIOutput) vce.getSource()).getValue();       
        List<Object[]> result=docenteEscalafonDAO.getRegistro(Long.parseLong(str));
        if(registroList==null)
            registroList=new ArrayList<DocenteEscalafon>();
        else registroList.clear();
        for (Object[] e : result) {
            DocenteEscalafon r=(DocenteEscalafon) e[0];            
            Persona p=(Persona)e[1];
            Docente d=new Docente();
                    d.setCi(p.getCi());
                    d.setPersona(p);
            r.setDocente(d);           
            registroList.add(r);
        } 
        this.setcSelected(str);
    }
    public void changeGestion(AjaxBehaviorEvent vce){       
        String str= (String) ((UIOutput) vce.getSource()).getValue();        
        List<Object[]> result=getEvaluacionDAO().getMaterias(dSelected.getCi(),str);
        this.setgSelected(str);
        
        
        if(materiaList==null)
            materiaList=new ArrayList<Evaluacion>();
        else materiaList.clear();
        
        for (Object[] e : result) {            
            Evaluacion eval=(Evaluacion)e[0];         
            DocenteRegistroMateria r=(DocenteRegistroMateria)e[1];
            Materia m=(Materia) e[2];
            r.setMateria(m);
            eval.setDocenteRegistroMateria(r);            
            materiaList.add(eval);
        }
        
        
        
      //  this.pSelected=personaDAO.read(dSelected);
        
        
        
        
        
        //JOptionPane.showMessageDialog(null, str+" "+dSelected+"  "+materiaList.size());
        //carreraList=carreraDAO.readCarrera(Long.parseLong(str));
        //this.getRegistroList().clear();
        //setcSelected("");
        //RequestContext.getCurrentInstance().reset(":table_form_header");
        
    }
    
    public void changeException(AjaxBehaviorEvent vce){        
        String str= (String) ((UIOutput) vce.getSource()).getValue();       
        this.tipoRes=Integer.parseInt(str);   
        if(tipoRes==0) return;                
        
        if(this.tipoRes==6||this.tipoRes==8){            
            Escalafon ant=evaluacionDAO.getEscalafonAnt(dSelected.getCi(), gSelected);
            nota=(int) ant.getPuntaje(); 
            //JOptionPane.showMessageDialog(null, nota);
        }else{
            this.carSelected=evaluacionDAO.getPuntajeEvaluacion(dSelected.getCi(), gSelected);
            nota=(int) this.carSelected.getPuntajeEscalafon();
        }        
    }
    public void updateEscalafon(){    
        if(tipoRes==0) return;
        this.escalafon=evaluacionDAO.getEscalafon(dSelected.getCi(), gSelected);        
        if(this.escalafon!=null){            
            this.escalafon.setTipoRes(this.tipoRes);
            this.escalafon.setPuntaje(this.nota);
            this.escalafon.setObs(this.obs);
            escalafonDAO.update(escalafon);
            return;
        }
        this.escalafon=new Escalafon();
        this.escalafon.setTipoRes(this.tipoRes);
        this.escalafon.setPuntaje(this.nota);
        this.escalafon.setObs(this.obs);
        this.escalafon.setGestion(carSelected.getGestion());
        this.escalafon.setCartilla(carSelected);
        this.escalafon.setDocenteEscalafon(carSelected.getDocenteEscalafon());        
        escalafonDAO.create(escalafon);          
    }
    public void retirarRegistro(DocenteEscalafon r){
        this.registro=new DocenteEscalafon();
        this.registro.setCi(r.getCi());
        this.registro.setDocente(r.getDocente());        
        this.registro.setCarrera(r.getCarrera());       
    }
    public void retirarRegistro(){
        
        this.docenteEscalafonDAO.delete(this.registro);
       List<Object[]> result=docenteEscalafonDAO.getRegistro(Long.parseLong(getcSelected()));
        if(registroList==null)
            registroList=new ArrayList<DocenteEscalafon>();
        else registroList.clear();
        for (Object[] e : result) {
            DocenteEscalafon r=(DocenteEscalafon) e[0];            
            Persona p=(Persona)e[1];
            Docente d=new Docente();
                    d.setCi(p.getCi());
                    d.setPersona(p);
            r.setDocente(d);           
            registroList.add(r);
        }        
    }
    public void prepareRegistro(){
        registro=new DocenteEscalafon();
        registro.setDocente(new Docente());
        registro.setFechaNacimiento(new Date());  
        Carrera c=new Carrera();
                c.setIdCarrera(Long.parseLong(getcSelected()));
        registro.setCarrera(c);
        
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
        //JOptionPane.showMessageDialog(null, docenteList.size());
    }
    public void guardarRegistro(){
        //JOptionPane.showMessageDialog(null, ">"+registro.getMateria().getIdMateria());
        registro.setTipo("NUEVO");
        //Cartilla c=new Cartilla();
        
        docenteEscalafonDAO.create(registro);
        
        List<Object[]> result=docenteEscalafonDAO.getRegistro(Long.parseLong(getcSelected()));
        if(registroList==null)
            registroList=new ArrayList<DocenteEscalafon>();
        else registroList.clear();
        for (Object[] e : result) {
            DocenteEscalafon r=(DocenteEscalafon) e[0];            
            Persona p=(Persona)e[1];
            Docente d=new Docente();
                    d.setCi(p.getCi());
                    d.setPersona(p);
            r.setDocente(d);           
            registroList.add(r);
        }  
    }
    public void saveResolucion(){
    
    }
    public void update(DocenteEscalafon e){
            registro=new DocenteEscalafon();
            registro.setCarrera(e.getCarrera());
            registro.setDocente(e.getDocente());
            registro.setCi(e.getCi());
            registro.setCorreoElectronico(e.getCorreoElectronico());
            registro.setFechaNacimiento(e.getFechaNacimiento());
            registro.setTipo(e.getTipo());
    }
    public void update(){
        docenteEscalafonDAO.update(registro);
        List<Object[]> result=docenteEscalafonDAO.getRegistro(Long.parseLong(getcSelected()));
        if(registroList==null)
            registroList=new ArrayList<DocenteEscalafon>();
        else registroList.clear();
        for (Object[] e : result) {
            DocenteEscalafon r=(DocenteEscalafon) e[0];            
            Persona p=(Persona)e[1];
            Docente d=new Docente();
                    d.setCi(p.getCi());
                    d.setPersona(p);
            r.setDocente(d);           
            registroList.add(r);
        }
    }
    public void evaluarDocente(String ci) throws IOException{
        //this.setdSelected(ci);
        dSelected=personaDAO.read(ci);
        this.getMateriaList().clear();
        FacesContext context= FacesContext.getCurrentInstance();   
        context.getExternalContext().redirect("cartilla.xhtml");
        //RequestContext.getCurrentInstance().reset(":table_years");
    }
    
    public void onEdit(RowEditEvent event) {
        Evaluacion eval=(Evaluacion) event.getObject();
       // JOptionPane.showMessageDialog(null, ">"+eval.getIdEvaluacion());
        Evaluacion e=new Evaluacion();
        e.setIdEvaluacion(eval.getIdEvaluacion());
        e.setNota(eval.getNota());
        e.setDocenteRegistroMateria(eval.getDocenteRegistroMateria());
        evaluacionDAO.update(e);
    //MessageUtil.addInfoMessage("car.edit", ((Car) event.getObject()).getName());
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
     * @return the registroList
     */
    public List<DocenteEscalafon> getRegistroList() {
        //RequestContext.getCurrentInstance().reset(":form_registro");
       if(registroList==null)
         registroList=new ArrayList<DocenteEscalafon>();
        //if(cSelected.length()==0) registroList=new ArrayList<DocenteEscalafon>();
        //JOptionPane.showMessageDialog(null, "raro");
        //if(cSelected.equals("")) registroList.clear();
        return registroList;
    }

    /**
     * @param registroList the registroList to set
     */
    public void setRegistroList(List<DocenteEscalafon> registroList) {
        this.registroList = registroList;
    }

    /**
     * @return the registro
     */
    public DocenteEscalafon getRegistro() {
        return registro;
    }

    /**
     * @param registro the registro to set
     */
    public void setRegistro(DocenteEscalafon registro) {
        this.registro = registro;
    }

    /**
     * @return the docenteEscalafonDAO
     */
    public DocenteEscalafonDAO getDocenteEscalafonDAO() {
        return docenteEscalafonDAO;
    }

    /**
     * @param docenteEscalafonDAO the docenteEscalafonDAO to set
     */
    public void setDocenteEscalafonDAO(DocenteEscalafonDAO docenteEscalafonDAO) {
        this.docenteEscalafonDAO = docenteEscalafonDAO;
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
     * @return the materiaList
     */
    public List<Evaluacion> getMateriaList() {
        if(materiaList==null) materiaList=new ArrayList<Evaluacion>();
        return materiaList;
    }

    /**
     * @param materiaList the materiaList to set
     */
    public void setMateriaList(List<Evaluacion> materiaList) {
        this.materiaList = materiaList;
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

    /**
     * @return the escalafonDAO
     */
    public EscalafonDAO getEscalafonDAO() {
        return escalafonDAO;
    }

    /**
     * @param escalafonDAO the escalafonDAO to set
     */
    public void setEscalafonDAO(EscalafonDAO escalafonDAO) {
        this.escalafonDAO = escalafonDAO;
    }

    /**
     * @return the escalafonList
     */
    public List<Escalafon> getEscalafonList() {
        //if(escalafonList==null) 
         //   escalafonList=new ArrayList<Escalafon>();
        this.escalafonList= escalafonDAO.getHistorial(dSelected.getCi(),gSelected);
        return escalafonList;
    }

    /**
     * @param escalafonList the escalafonList to set
     */
    public void setEscalafonList(List<Escalafon> escalafonList) {
        this.escalafonList = escalafonList;
    }

  

   

    /**
     * @return the contenidoCartillaList
     */
    public List<ContenidoCartilla> getContenidoCartillaList() {
        //if(contenidoCartillaList==null) contenidoCartillaList=new ArrayList<ContenidoCartilla>();
        this.contenidoCartillaList=evaluacionDAO.getMateriasCartilla(dSelected.getCi(), gSelected);
        return contenidoCartillaList;
    }

    /**
     * @param contenidoCartillaList the contenidoCartillaList to set
     */
    public void setContenidoCartillaList(List<ContenidoCartilla> contenidoCartillaList) {
        this.contenidoCartillaList = contenidoCartillaList;
    }

    /**
     * @return the carSelected
     */
    public Cartilla getCarSelected() {
        
        //if(carSelected==null) carSelected=new Cartilla();
        this.carSelected=evaluacionDAO.getPuntajeEvaluacion(dSelected.getCi(), gSelected);
        return carSelected;
    }

    /**
     * @param carSelected the carSelected to set
     */
    public void setCarSelected(Cartilla carSelected) {
        this.carSelected = carSelected;
    }

    /**
     * @return the docenteEscalafon
     */
    public DocenteEscalafon getDocenteEscalafon() {
        //if(docenteEscalafon==null) docenteEscalafon=new DocenteEscalafon();
        this.docenteEscalafon=evaluacionDAO.getUniversidad(dSelected.getCi());
        return docenteEscalafon;
    }

    /**
     * @param docenteEscalafon the docenteEscalafon to set
     */
    public void setDocenteEscalafon(DocenteEscalafon docenteEscalafon) {
        this.docenteEscalafon = docenteEscalafon;
    }

   

    /**
     * @return the gSelected
     */
    public String getgSelected() {
        return gSelected;
    }

    /**
     * @param gSelected the gSelected to set
     */
    public void setgSelected(String gSelected) {
        this.gSelected = gSelected;
    }

    /**
     * @return the escalafon
     */
    public Escalafon getEscalafon() {    
        escalafon=evaluacionDAO.getEscalafon(dSelected.getCi(), gSelected);
       if(escalafon==null) {
          escalafon=new Escalafon();
          escalafon.setPuntaje(0);
          escalafon.setObs("Pendiente");
       }
        return escalafon;
    }
    public boolean renderCartilla(){
        this.escalafon=evaluacionDAO.getEscalafon(dSelected.getCi(), gSelected);
        
        if(escalafon==null){
          escalafon=new Escalafon();
          escalafon.setObs("Pendiente");
        }
        return true;
    }
    /**
     * @param escalafon the escalafon to set
     */
    public void setEscalafon(Escalafon escalafon) {
        this.escalafon = escalafon;
    }

    /**
     * @return the tipoRes
     */
    public int getTipoRes() {
        return tipoRes;
    }

    /**
     * @param tipoRes the tipoRes to set
     */
    public void setTipoRes(int tipoRes) {
        this.tipoRes = tipoRes;
    }

    /**
     * @return the dSelected
     */
    public Persona getdSelected() {
        return dSelected;
    }

    /**
     * @param dSelected the dSelected to set
     */
    public void setdSelected(Persona dSelected) {
        this.dSelected = dSelected;
    }

    /**
     * @return the resolucion
     */
    public String getResolucion() {
        
        resolucion=escalafonDAO.getResolucion(dSelected.getCi(), gSelected);
        escalafon=evaluacionDAO.getEscalafon(dSelected.getCi(), gSelected);
        if(escalafon==null){
            resolucion="Aun no ha sido evaluado en esta  gestion";
        }else{
        DateFormat df=DateFormat.getDateInstance(DateFormat.LONG,new Locale("es","BOL"));        
        resolucion=resolucion.replace("#{date}",df.format(new Date()));
        resolucion=resolucion.replace("#{carrera}",carreraDAO.read(Long.parseLong(cSelected)).getNombre());
        resolucion=resolucion.replace("#{facultad}",facultadDAO.read(Long.parseLong(fSelected)).getNombre());
        resolucion=resolucion.replace("#{gestion_academica}",gSelected);
        resolucion=resolucion.replace("#{nombre_docente}",dSelected.getDocente().getGradoAcademico()+" "+ dSelected.getNombre()+" "+dSelected.getApellido());
        resolucion=resolucion.replace("#{puntaje}",""+escalafon.getPuntaje());
        resolucion=resolucion.replace("#{puntaje_acumulado}",""+escalafon.getPuntajeAcumulado());
        resolucion=resolucion.replace("#{categoria}",""+escalafon.getCategoria());
        }
        return resolucion;
    }

    /**
     * @param resolucion the resolucion to set
     */
    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
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
     * @return the nota
     */
    public int getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(int nota) {
        this.nota = nota;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }
}

