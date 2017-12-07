/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.CarreraDAO;
import dea.controlador.dao_classes.FacultadDAO;
import dea.controlador.dao_classes.MateriaDAO;
import dea.controlador.dao_classes.UniversidadDAO;
import dea.modelo.Carrera;
import dea.modelo.Facultad;
import dea.modelo.Materia;
import dea.modelo.Universidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;
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
public class MateriaBean implements Serializable{  
    
   
    private ArrayList<Universidad> universidadList;
    private ArrayList<Facultad> facultadList; 
    private ArrayList<Carrera> carreraList;
    private ArrayList<Materia> materiaList;
    private Materia mSelected;
   // private Universidad uSelected;
    private String cSelected;  
    
    @Autowired 
    private UniversidadDAO universidadDAO;
    @Autowired     
    private CarreraDAO carreraDAO;
    @Autowired     
    private FacultadDAO facultdadDAO;
    @Autowired     
    private MateriaDAO materiaDAO;
    
    
    public MateriaBean() {
      
    } 
    public void removeItem(Materia m){
   
      mSelected=new Materia();
      mSelected.setIdMateria(m.getIdMateria());
      mSelected.setNombre(m.getNombre());
      mSelected.setSiglaMateria(m.getSiglaMateria());
     // mSelected.setCarrera(cSelected);      
    }
    public void removeItem(){
     //  JOptionPane.showMessageDialog(null, "joder "+mSelected.getIdMateria());
        Carrera c=new Carrera();
        c.setIdCarrera(Long.parseLong(cSelected));
        mSelected.setCarrera(c);
        this.getMateriaDAO().delete(mSelected);
        materiaList=(ArrayList<Materia>) materiaDAO.readMateria(Long.parseLong(cSelected));
    }
    public void updateItem(){
        Carrera c=new Carrera();
        c.setIdCarrera(Long.parseLong(cSelected));
        mSelected.setCarrera(c);
        this.getMateriaDAO().update(mSelected);  
        materiaList=(ArrayList<Materia>) materiaDAO.readMateria(Long.parseLong(cSelected));
    }
    public void updateItem(Materia f){      
      mSelected=new Materia();
      mSelected.setIdMateria(f.getIdMateria());
      mSelected.setSiglaMateria(f.getSiglaMateria());
      mSelected.setNombre(f.getNombre());        
    }
    public void viewItem(Materia c){
      mSelected=new Materia();
      mSelected.setNombre(c.getNombre());
      mSelected.setSiglaMateria(c.getSiglaMateria());
    }    
    public void createItem(){       
        setmSelected(new Materia());
    }
    public void createItem(boolean op){        
      //  this.getmSelected().setCarrera(cSelected);
        Carrera c=new Carrera();
        c.setIdCarrera(Long.parseLong(cSelected));
        mSelected.setCarrera(c);
        getMateriaDAO().create(this.getmSelected());
       materiaList=(ArrayList<Materia>) materiaDAO.readMateria(Long.parseLong(cSelected));
       
    }

    /**
     * @return the universidadList
     */
    public void leerFacultad(String sigla){
     //   this.getFacultdadDAO().readFacultdad(sigla);
    }
    public void leerFacultad(ValueChangeEvent event){
        //JOptionPane.showMessageDialog(null, "joder "+event.getNewValue().toString());
        //this.getFacultdadDAO().readFacultdad(u);
    }
    public ArrayList<Facultad> options(){
        FacesContext facesContext = FacesContext.getCurrentInstance(); 
       //FacultadBean b=(FacultadBean)facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "facultadBean");
       //return new ArrayList<Facultad>(b.getFacultadList());
        return new ArrayList<Facultad>();
    }
    
    public void changeUniversidad(AjaxBehaviorEvent vce){      
        String str= (String) ((UIOutput) vce.getSource()).getValue();        
        if (str.compareTo("null")==0) {
            getCarreraList().clear(); return;
        }         
        facultadList=(ArrayList<Facultad>) facultdadDAO.readFacultdad(str);
        RequestContext.getCurrentInstance().reset(":table_form_header");
        carreraList.clear();
        materiaList.clear();
    }
    public void changeFacultad(AjaxBehaviorEvent vce){      
        String str= (String) ((UIOutput) vce.getSource()).getValue();        
        if (str.compareTo("null")==0) {
            getCarreraList().clear(); return;
        }
        //fSelected=str;
        carreraList=(ArrayList<Carrera>) carreraDAO.readCarrera(Long.parseLong(str));
        materiaList.clear();
    }
    public void changeCarrera(AjaxBehaviorEvent vce){      
        String str= (String) ((UIOutput) vce.getSource()).getValue();        
        if (str.compareTo("null")==0) {
            getCarreraList().clear(); return;
        }
        materiaList=(ArrayList<Materia>) materiaDAO.readMateria(Long.parseLong(str));
        cSelected=str;
        //fSelected=str;
       // carreraList=(ArrayList<Carrera>) carreraDAO.readCarrera(Long.parseLong(str));
    }
    /**
     * @return the materiaList
     */
    public ArrayList<Materia> getMateriaList() {
        if(materiaList==null)
            materiaList=new ArrayList<Materia>();
        return materiaList;
    }

    /**
     * @param materiaList the materiaList to set
     */
    public void setMateriaList(ArrayList<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    /**
     * @return the mSelected
     */
    public Materia getmSelected() {
        return mSelected;
    }

    /**
     * @param mSelected the mSelected to set
     */
    public void setmSelected(Materia mSelected) {
        this.mSelected = mSelected;
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
     * @return the universidadList
     */
    public ArrayList<Universidad> getUniversidadList() {
        universidadList=(ArrayList<Universidad>) universidadDAO.readAll();
        return universidadList;
    }

    /**
     * @param universidadList the universidadList to set
     */
    public void setUniversidadList(ArrayList<Universidad> universidadList) {
        this.universidadList = universidadList;
    }

    /**
     * @return the facultadList
     */
    public ArrayList<Facultad> getFacultadList() {
        return facultadList;
    }

    /**
     * @param facultadList the facultadList to set
     */
    public void setFacultadList(ArrayList<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    /**
     * @return the carreraList
     */
    public ArrayList<Carrera> getCarreraList() {
        if(carreraList==null)
            carreraList=new ArrayList<Carrera>();
        return carreraList;
    }

    /**
     * @param carreraList the carreraList to set
     */
    public void setCarreraList(ArrayList<Carrera> carreraList) {
        this.carreraList = carreraList;
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
     * @return the facultdadDAO
     */
    public FacultadDAO getFacultdadDAO() {
        return facultdadDAO;
    }

    /**
     * @param facultdadDAO the facultdadDAO to set
     */
    public void setFacultdadDAO(FacultadDAO facultdadDAO) {
        this.facultdadDAO = facultdadDAO;
    }


    


    
}

