/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.CarreraDAO;
import dea.controlador.dao_classes.FacultadDAO;
import dea.controlador.dao_classes.UniversidadDAO;
import dea.modelo.Carrera;
import dea.modelo.Facultad;
import dea.modelo.Universidad;
import java.io.Serializable;
import java.util.ArrayList;
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
public class CarreraBean implements Serializable{  
    
    private FacultadBean facultadBean;
    private ArrayList<Universidad> universidadList;
    private ArrayList<Facultad> facultadList; 
    private ArrayList<Carrera> carreraList;
    private String fSelected;
    private Carrera cSelected;
    
    @Autowired 
    private UniversidadDAO universidadDAO;
    @Autowired     
    private CarreraDAO carreraDAO;
    @Autowired     
    private FacultadDAO facultdadDAO;
    
    public CarreraBean() {
       
       cSelected=new Carrera();  
    } 
    public void removeItem(Carrera c){
      cSelected=new Carrera();
      cSelected.setIdCarrera(c.getIdCarrera());
      cSelected.setNombre(c.getNombre());
      cSelected.setSiglaCarrera(c.getSiglaCarrera());
      //cSelected.setFacultad(getfSelected());
      //  uSelected.setNombre(u.getNombre());
      //  uSelected.setSiglaUniversidad(u.getSiglaUniversidad());
       
    }
    public void removeItem(){
       Facultad f=new Facultad(Long.parseLong(fSelected));
       cSelected.setFacultad(f); 
       this.getCarreraDAO().delete(cSelected);
       carreraList=(ArrayList<Carrera>) carreraDAO.readCarrera(Long.parseLong(fSelected));
       // setCarreraList((ArrayList<Carrera>) carreraDAO.readCarrera(getfSelected().getIdFacultad()));
    }
    public void updateItem(){
       Facultad f=new Facultad(Long.parseLong(fSelected));
       cSelected.setFacultad(f);
       this.getCarreraDAO().update(cSelected);
       carreraList=(ArrayList<Carrera>) carreraDAO.readCarrera(Long.parseLong(fSelected));
     //   setCarreraList((ArrayList<Carrera>) carreraDAO.readCarrera(getfSelected().getIdFacultad()));
    }
    public void updateItem(Carrera f){        
      cSelected=new Carrera();
      cSelected.setIdCarrera(f.getIdCarrera());
      cSelected.setSiglaCarrera(f.getSiglaCarrera());
      cSelected.setNombre(f.getNombre());    
    }
    public void viewItem(Carrera c){
      cSelected.setNombre(c.getNombre());
      cSelected.setSiglaCarrera(c.getSiglaCarrera());
    }    
    
    public void createItem(){       
       cSelected=new Carrera();      
    }
    public void createItem(boolean op){        
        //this.getcSelected().setFacultad(getfSelected());
        Facultad f=new Facultad(Long.parseLong(fSelected));
        cSelected.setFacultad(f);
        carreraDAO.create(cSelected);
        carreraList=(ArrayList<Carrera>) carreraDAO.readCarrera(Long.parseLong(fSelected));
     //   setCarreraList((ArrayList<Carrera>) carreraDAO.readCarrera(getfSelected().getIdFacultad()));
       
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
    
    public void changeUniversidad(AjaxBehaviorEvent vce){      
        String str= (String) ((UIOutput) vce.getSource()).getValue();        
        if (str.compareTo("null")==0) {
            getCarreraList().clear(); return;
        }
        //fSelected=new Facultad();
        //fSelected.setIdFacultad(Long.parseLong(str));    
        facultadList=(ArrayList<Facultad>) facultdadDAO.readFacultdad(str);
        RequestContext.getCurrentInstance().reset(":table_form_header");
        carreraList.clear();
    }
    public void changeFacultad(AjaxBehaviorEvent vce){      
        String str= (String) ((UIOutput) vce.getSource()).getValue();        
        if (str.compareTo("null")==0) {
            getCarreraList().clear(); return;
        }
        fSelected=str;
        carreraList=(ArrayList<Carrera>) carreraDAO.readCarrera(Long.parseLong(fSelected));
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
     * @return the facultadBean
     */
    public FacultadBean getFacultadBean() {
        return facultadBean;
    }

    /**
     * @param facultadBean the facultadBean to set
     */
    public void setFacultadBean(FacultadBean facultadBean) {
        this.facultadBean = facultadBean;
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
     * @return the cSelected
     */
    public Carrera getcSelected() {
        return cSelected;
    }

    /**
     * @param cSelected the cSelected to set
     */
    public void setcSelected(Carrera cSelected) {
        this.cSelected = cSelected;
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

    
  

    


    
}

